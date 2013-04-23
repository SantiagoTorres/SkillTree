import javax.swing.*;
import java.awt.*;
public class JFrameTester {
	

	public static void main(String[] args) {
	    //TreeView treeView;
	    SkillToolTip skillToolTip;
	    JFrame f = new JFrame("A JFrame");
		/*treeView =  new TreeView(30,30,f.getContentPane());*/
	    //	GridBagConstraints c = new GridBagConstraints();
		/*skillToolTip = new SkillToolTip(new SkillModel(),f.getContentPane());*/
		SkillDetailView Sdk = new SkillDetailView(new SkillModel());
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(250, 250);
		f.setLocation(300,200);
		f.pack();
		f.setVisible(true);
				          
	}
	    
}
