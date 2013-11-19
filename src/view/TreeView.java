package view;

import javax.swing.*;
import java.awt.*;
import model.*;
import view.*;
public class TreeView extends Panel{

	//private Container pane;	
	private int y;
	private int x;
  private GridBagConstraints defaultConstraints;
	/*public TreeView(int x, int y, Container pane) {
		      
		this.pane = pane;
		pane.setLayout(new GridLayout(x,y));
		pane.add(new JTextArea(10, 40));
	}
*/
	public TreeView(int x, int y){
    this.x=x;
    this.y=y;
    this.setLayout(new GridBagLayout());
    this.defaultConstraints = new GridBagConstraints();
    this.defaultConstraints.gridwidth=1;
    this.defaultConstraints.gridheight=1;
		//this.setLayout(new GridLayout(x,y));
    //System.out.println("Creating a " + x + " X " + y + " grid"); 
	}

	public boolean addComponentAt(Component component, int x, int y){
		this.defaultConstraints.gridx=x;
    this.defaultConstraints.gridy=y;
    this.add(component,this.defaultConstraints);
		return true;

	}
	    
}
