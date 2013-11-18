package view;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import model.*;

public class MainWindow {
	TreeView treeView;
	JFrame mainFrame;
	JMenuBar menuBar;
	Box buttonBox;

	public MainWindow(TreeView theTreeView, TreeModel tree) {
		Box theBox = new Box(BoxLayout.Y_AXIS);
		mainFrame = new JFrame("Skill Tree Ver 0.0a");
    skillAddListener addDialog = new skillAddListener(tree);
		JMenu JMenuRef;
		BufferedImage image;
    JMenuItem newMenuItem;
    JMenuItem loadMenuItem;
    JMenuItem addMenuItem;
    JMenuItem removeMenuItem;
    JMenuItem removeBranchMenuItem;
    JMenuItem exitMenuItem;
    JMenuItem helpMenuItem;
    JMenuItem aboutMenuItem;

		try{
			image = ImageIO.read(new File("../../lib/resources/images/wolf.png"));
		}catch(Exception e){
			image = new BufferedImage(40,40,1);
		}
		//initializing the menu bar
		menuBar = new JMenuBar();
		JMenuRef = new JMenu("File");//file
    newMenuItem = new JMenuItem("New");
		JMenuRef.add(newMenuItem);
    loadMenuItem = new JMenuItem("Load");
		JMenuRef.add(loadMenuItem);
		JMenuRef.addSeparator();
    
    addMenuItem = new JMenuItem("Add");
    addMenuItem.addActionListener(addDialog);
		JMenuRef.add(addMenuItem);
    removeMenuItem = new JMenuItem("Remove");
		JMenuRef.add(removeMenuItem);
    removeBranchMenuItem = new JMenuItem("Remove Branch");
		JMenuRef.add(removeBranchMenuItem);
		JMenuRef.addSeparator();

    exitMenuItem = new JMenuItem("Exit");
		JMenuRef.add(exitMenuItem);
		menuBar.add(JMenuRef);

    
		JMenuRef = new JMenu("Help"); //help
    aboutMenuItem = new JMenuItem("About");
		JMenuRef.add(aboutMenuItem);
    helpMenuItem = new JMenuItem("Help");
		JMenuRef.add(helpMenuItem);
		menuBar.add(JMenuRef);
		
    mainFrame.setJMenuBar(menuBar);
		
		buttonBox = new Box(BoxLayout.X_AXIS);
		buttonBox.add(new JButton("Add",
          new ImageIcon(image.getScaledInstance(50,50,Image.SCALE_SMOOTH))));
		buttonBox.add(new JButton("Remove",
          new ImageIcon(image.getScaledInstance(50,50,Image.SCALE_SMOOTH))));
		theBox.add(buttonBox);
		

		this.treeView =  theTreeView;
		theBox.add(this.treeView);

		
		mainFrame.add(theBox);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(250, 250);
		mainFrame.setLocation(300,200);
		mainFrame.pack();
		mainFrame.setVisible(true);
				          
	}
  
  private class skillAddListener implements ActionListener{
    private CreateSkill skillToCreate;
    private TreeModel tree;

    public skillAddListener(TreeModel tree){
      super();
      this.tree = tree;
    }
   
    public void actionPerformed(ActionEvent e){
      this.skillToCreate = new CreateSkill(tree);
    } 

  } 
}
