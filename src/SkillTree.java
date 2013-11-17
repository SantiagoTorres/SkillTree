import javax.swing.*;
import java.awt.*;
import javax.imageio.*;
import model.*;
import view.*;
public class SkillTree {
	

	public static void main(String[] args) {

		TreeView treeView;
		MainWindow mainWindow;
		CreateSkill theSkill;
		TreeModel treeModel = new TreeModel(new SkillModel());
		JLabel theLabel = new JLabel("lulz");
		SkillView skillView = new SkillView(new String("name"),new String("0/1"),"../lib/resources/images/wolf.png");
		treeView =  new TreeView(2,2);
		treeView.addComponentAt(skillView,0,0);
		mainWindow = new MainWindow(treeView,treeModel);
		theSkill = new CreateSkill(treeModel);
	}
	
}
