import javax.swing.*;
import java.awt.*;
public class JFrameTester {
	

	public static void main(String[] args) {
		TreeView treeView;
		JFrame f = new JFrame("A JFrame");
		treeView =  new TreeView(30,30,f.getContentPane());
		GridBagConstraints c = new GridBagConstraints();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(250, 250);
		f.setLocation(300,200);
		f.pack();
		f.setVisible(true);
				          
	}
	    
}
