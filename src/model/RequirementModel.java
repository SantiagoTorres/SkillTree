package model;
/**
 * Requirement Model: Contains information about an specific skill under the tree
 *
 * The skills are meant to be verteces inside a graph, edges should be 
 * requirements on a *possibly* unweighted graph (for now, I will see if it is
 * updateable).
 *
 * Author: Santiago Torres, Miguel Kelly
 * Date: 29 apr 2013
 */
import java.lang.*;
import model.*;

/**
 * Requirement Model class
 *
 * Contains two references to source and destination, contains a reference to a
 * "weight" object if needed 
 */
public class RequirementModel{
  private SkillModel source;      //<This is the parent of the requirement
  private SkillModel destination; //<This is the son 
  private int nWeight;            //<This is a numeric weight, for convenience
  
  //empty constructor, avoid this one, use the one with arguments
  public RequirementModel(){
    source=null;
    destination=null; //<This is the son 
    nWeight=1;            //<This is a numeric weight, for convenience
  }  

  //full constructor it's not validated yet
  public RequirementModel(SkillModel source, SkillModel destination,
                          int weight){
    this.source=source;
    this.destination=destination; 
    this.nWeight=weight;               
  }  
        
        //setParentLinks: resets link array	
  public int setWeight(int weight){
    this.nWeight = weight;
    return 0;
  }

  public int setSource(SkillModel source){
    this.source = source;
    return 0;
  }

  public int setDestination(SkillModel destination){
    this.destination = destination;
    return 0;
  }

  public int getWeight(){
    return this.nWeight;
  }

  public SkillModel getSource(){
    return this.source;
  }

  public SkillModel getDestination(){
    return this.destination;
  }


  public String toString(){
		return "Link status:" +
           "\n  Source:      " + this.source.getName() +  
			     "\n  Destination: " + this.destination.getName() + 
			     "\n  Weight:      " + this.nWeight;
	}
}
