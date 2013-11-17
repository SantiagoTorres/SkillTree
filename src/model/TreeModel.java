package model;
/*
 * TreeModel.java: contains the data model for the SkillTree App
 *
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
	//methods
	
	public int addSkill(SkillModel skillToAdd){
		this.skillList.add(skillToAdd);
    this.requirementsList.addAll(skillToAdd.getParentLinks());
		return 0;		
	}

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
	}

	public ArrayList<SkillModel> getList(){
		return this.skillList;
	}
}
