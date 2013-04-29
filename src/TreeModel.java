/*
 * TreeModel.java: contains the data model for the SkillTree App
 *
 * Author: Santiago Torres
 * Date:   29 apr 2013
 */
import java.lang.*;

public class TreeModel{
	//properties
	private ArrayList<SkillModel> skillList;
	//methods
	
	public int addSkill(SkillModel skillToAdd){
		this.skillList.add(skillToAdd);	
		return 0;		
	}

	public int removeSkill(SkillModel skillToRemove){
		this.skillList.remove(skillToRemove);
		return 0;
	}

	public int removeBranch(SkillModel root){
		System.out.println("missing implementation");
	}
	
	public TreeModel(SkillModel root){
		this.skillList = new ArrayList<Skillmodel>(1);
		this.skillList.add(root);
	}
}
