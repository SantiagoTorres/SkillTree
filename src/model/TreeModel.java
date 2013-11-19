package model;
/*
 * TreeModel.java: contains the data model for the SkillTree App
 *  
 *  Consider that the first element of the tree is probably the source, 
 *  relocation methods might be offered and implemented in the future
 * Author: Santiago Torres
 * Date:   29 apr 2013
 */
import java.lang.*;
import java.util.*;
import model.*;

public class TreeModel{
  //properties
  private ArrayList<SkillModel> skillList;
  private ArrayList<RequirementModel> requirementsList;
  private boolean isBFSRecent;//this flag will be used to avoid recalculating
                              //the tree when it is not needed
                              //methods
  private int height;
  private int maxWidth;

  public int addSkill(SkillModel skillToAdd){
    Iterator<SkillModel> skillIterator = skillList.iterator();
    if(skillList.isEmpty()){
      skillList.add(skillToAdd);
      return 0;
    }
    while(skillIterator.hasNext()){
      if(skillToAdd.getName().equals(skillIterator.next().getName())){
        System.out.println(
            "[Error]:Skills with the same name are being added");
        return 1;
      }
    }
    this.skillList.add(skillToAdd);
    if(this.requirementsList!=null){
      this.requirementsList.addAll(skillToAdd.getParentLinks());
    }
    this.isBFSRecent=false;
    return 0;		
  }

  //TODO: remove skill should remove trailing verteces
  public int removeSkill(SkillModel skillToRemove){
    this.skillList.remove(skillToRemove);
    return 0;
  }

  public int removeBranch(SkillModel root){
    System.out.println("missing implementation");
    return 1;
  }
	
  public TreeModel(SkillModel root){
    this.skillList = new ArrayList<SkillModel>(1);
    this.skillList.add(root);
    this.isBFSRecent=false;
  }

  public TreeModel(){
    this.skillList = new ArrayList<SkillModel>();
    this.skillList.clear(); //i shouldn't be forced to do this right? right?
    this.isBFSRecent=false;
  }

  public ArrayList<SkillModel> getList(){
    return this.skillList;
  }

  //actual fun methods
  //
  /**
   * This method builds a BFS tree using the existing Skills on the tree,
   * this is usually a helper function to help us mapping the skills in a 
   * grid.
   *
   * \returns the root of the BFS tree built
   */
  public SkillModel buildBFS(){
    LinkedList<SkillModel> processQueue;
    SkillModel vertex,currentVertex;
    Iterator<RequirementModel> edges;
    Iterator<SkillModel> skillIterator;
    RequirementModel currentEdge;
    int widthHelper,distance;

    if(this.isBFSRecent==false){
      processQueue = new LinkedList<SkillModel>();
      this.height = 0;
      this.maxWidth = 0;
      //initialize the graph for breadth first search
      skillIterator = skillList.iterator();
      while(skillIterator.hasNext()){
        skillIterator.next().initForBFS();
      }

      vertex = this.skillList.get(0);
      vertex.visit(null,0);
      widthHelper = 0;
      distance = 0;
      processQueue.addLast(vertex);//add root
      System.out.println("Current size: " + processQueue.size());
      while(processQueue.size()>=1){
        //dequeue a object for processing
        currentVertex = processQueue.removeFirst();
        System.out.println("Current Vertex: " + currentVertex.getName()); 
        
        //if we progressed to a new depth of thre tree
        if(distance<currentVertex.getDistance()){
          distance = currentVertex.getDistance();
          widthHelper = 0; //return the width count to 0
        
        }
        //discover new nodes.
        edges = currentVertex.getSonLinks().iterator();
        while(edges.hasNext()){
          //for all of the elements connected to the tree
          currentEdge = edges.next();
          vertex = currentEdge.getDestination();
          System.out.println("Edge: " + currentEdge.getSource().getName() + 
              "===> " + currentEdge.getDestination().getName());
          //enqueue for processing, and update the current vertex
          if(vertex.isVisited() == false){
            
            vertex.visit(currentEdge,currentVertex.getDistance()+1);
            currentVertex.addToNext(currentEdge);
            processQueue.addLast(vertex);
            widthHelper++;
          }

        }

        if(widthHelper>this.maxWidth){
          this.maxWidth = widthHelper;
        }
      }
      this.height = distance;

      this.isBFSRecent=true;
    }
    return this.skillList.get(0);
  }

  
  /**
   * This function telies on the buildBFS function, it will calculate the 
   * widest level of the tree, so we can estimate the size of the grid.
   *
   * \returns an integer representing the width of the widest section of the
   * tree, this is not necesarilly the lowest level
   */
  public int getTreeWidth(){
    return this.maxWidth;
  }


  /** 
   * This function traverses the constructed BFS in order to find the longest 
   * branch, this is usually a helper function to help us build the grid.
   *
   * \retunrs an integer of the number of skills on the longest branch
   */
  public int getTreeHeight(){
    return this.height;
  }

  public SkillModel getRoot(){
    return this.skillList.get(0);
  }
}
