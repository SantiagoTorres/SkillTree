package model;
/**
 * Skill Model: Contains information about an specific skill under the tree
 *
 * The skills are meant to be verteces inside a graph, edges should be 
 * requirements on a *possibly* unweighted graph (for now, I will see if it is
 * updateable).
 *
 * Author: Santiago Torres, Miguel Kelly
 * Date: 29 apr 2013
 */
import java.lang.*;
import java.util.*;
import model.*;
/**
 * Skill Model class
 *
 * Contains the definition of the vertex 
 */
public class SkillModel{
  private String name;           //<the name of the vertex (or skill)
  private ArrayList<RequirementModel> parent;//<The name of the parent 
  private ArrayList<RequirementModel> sons;  //< The name of the son (or sons)
  private String[] details;      //< Information about the skill
  private int level;             //< the current level on this skill  
  private int maxLevel;          //< the maximum level on the skill
  private String[] milestones;   //< Some achievements inside the level
  private int[] achieved;        //< Boolean flags for the milestones
  private String[] requirements; //< External Milestones from other skills
  private boolean isActive;      //< Is this skill available to train?
  
  //empty constructor, avoid this one, use the one with arguments
  public SkillModel(){
    String emptyDetails[] = {"empty"};
    ArrayList<RequirementModel> emptySkills =
        new ArrayList<RequirementModel>();
    System.out.println("Skills are not meant to be created with an empty constructor");
    this.name = new String("Empty");
    this.parent = emptySkills;
    this.sons = emptySkills;
    this.details = emptyDetails;
    this.level=0;
    this.maxLevel=10;
    this.milestones=emptyDetails;
    this.achieved = new int[1];
    this.requirements = emptyDetails;
    this.isActive=false;
  }  

  //full constructor it's not validated yet
  public SkillModel(String name, String[] details, int maxLevel,
                    String[] milestones, String[] requirements){
    this.name = name;
    this.details = details;
    this.level=0;
    this.maxLevel=maxLevel;
    this.milestones=milestones;
    this.achieved = new int[milestones.length];
    this.requirements = requirements;
    this.isActive=false;
  }	
        
 
  //some SETTER methods
  //setParentLinks: resets link array	
  public int setParentLinks(ArrayList<RequirementModel> newLinks){
    this.parent = newLinks;
    return 0;
  }


  //GETTER methods  
  public ArrayList<RequirementModel> getParentLinks(){
    return this.parent;
  }

  public ArrayList<RequirementModel> getSonLinks(){
    return this.sons;
  }
  
  public SkillModel[] getParents(){
    SkillModel parents[] = new SkillModel[this.parent.size()];
    for(int i=0;i<parents.length;i++){
      parents[i] = this.parent.get(i).getSource();
    }
    return parents;
  }

  public SkillModel[] getSons(){
    SkillModel sons[] = new SkillModel[this.sons.size()];
    for(int i=0;i<sons.length;i++){
      sons[i] = this.sons.get(i).getSource();
    }
    return sons;
  }

  public String getName(){
    return this.name;
  }

  public String[] getDetails(){
    return this.details;
  }

  public String[] getMilestones(){
    return this.milestones;
  }

  public int[] getAchieved(){
    return this.achieved;
  }

  public int getLevel(){
    return this.level;
  }

  public int getMaxLevel(){
    return this.maxLevel;
  }
    
  public String[] getRequirements(){
    return this.requirements;
  }

  
  //MISC methods
  public void setAchieved(int num){
	  if(this.achieved[num]==0){
      this.achieved[num]=1;
    }else{
      this.achieved[num]=0;
    }
    for(int i=0;i<this.achieved.length;i++){
      System.out.println(this.achieved[i]);
    }
  }

  public RequirementModel addSon(SkillModel son){
    RequirementModel newLink = new RequirementModel(this,son,1);
    this.sons.add(newLink);
    return newLink;
  }


  public void addParentLink(RequirementModel link){
    this.parent.add(link);
  }
	//simple toString() method for debugging purposes.
  public String toString(){
		return this.name +  
			"\n Parents:      " + this.parent.size() + 
			"\n Sons:         " + this.sons.size() + 
			"\n Details:      " + this.details.length +
      "\n Level:        " + this.level + "/" + this.maxLevel + 
			"\n Milestones:   " + this.milestones.length + 
			"\n Achievement:  " + this.achieved.length + 
			"\n requirements: " + this.requirements.length + 
			"\n isActive?     " + this.isActive;
	}
}
