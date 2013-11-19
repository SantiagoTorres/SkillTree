package controller;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import model.*;
import view.*;

public class TreeController{

	//private Container pane;	
	private TreeView theView;
	private TreeModel tree;

	/*public TreeView(int x, int y, Container pane) {
		      
		this.pane = pane;
		pane.setLayout(new GridLayout(x,y));
		pane.add(new JTextArea(10, 40));
	}
*/
	public TreeController(TreeModel tree){
		this.tree = tree;
	}

  public TreeView populateAndReturnView(){
    Iterator<SkillModel> skillIterator = this.tree.getList().iterator();
    ArrayList<SkillModel> skillsForLevel;
    SkillModel currentSkill;
    SkillView currentSkillView;
    int[] currentIndexPerLevel = new int[tree.getTreeWidth()];
    this.theView = new TreeView((tree.getTreeHeight()*2)+1,
                                (tree.getTreeWidth()*2)+1);
    int level,x,width,hspacing;
    if(!tree.getList().isEmpty()){
    
      //initialize root node location.
      currentSkill = tree.getRoot();
      level = 0;
      width = tree.getTreeWidth()*2+1;
      x = tree.getTreeWidth();
      currentSkillView = new SkillView(currentSkill.getName(),
                                       "" + currentSkill.getLevel(),
                                       "../../../lib/Resources/Images/wolf.png"
                                       );
      this.theView.addComponentAt(currentSkillView,x,level);
      skillsForLevel = new ArrayList<SkillModel>();
      skillsForLevel.add(currentSkill);
      

      while(level <= tree.getTreeHeight()){
        level ++;
        skillsForLevel = CalculateListForLevel(skillsForLevel);
        x = ((2*skillsForLevel.size())+1)/width;
        hspacing = x;
        if(hspacing == 0){
          hspacing++;
        }
        skillIterator = skillsForLevel.iterator();
        while(skillIterator.hasNext()){
          currentSkill = skillIterator.next();
          currentSkillView = new SkillView(currentSkill.getName(),
                                       "" + currentSkill.getLevel(),
                                       "../../../lib/Resources/Images/wolf.png"
                                       );
          this.theView.addComponentAt(currentSkillView,x,level*2);
          System.out.println("Adding Skill: " + currentSkill.getName() +
                              "At ("+x+","+level+")");
          x+=2*hspacing;
        }
      }
    }

    System.out.println("I guess this is: " + this.theView);
    return this.theView;
  }

  private ArrayList<SkillModel> CalculateListForLevel(
                                  ArrayList<SkillModel> upperLevel){
    ArrayList<SkillModel> newLevel = new ArrayList(this.tree.getTreeWidth());
    Iterator<SkillModel> upperSkills = upperLevel.iterator();
    SkillModel currentSkill;
    while(upperSkills.hasNext()){
      currentSkill = upperSkills.next();
      Iterator<RequirementModel> links = currentSkill.getNext().iterator();
      while(links.hasNext()){
        newLevel.add(links.next().getDestination());
      }
    }
    return newLevel;
  }
	    
}
