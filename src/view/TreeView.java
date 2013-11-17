package view;

import javax.swing.*;
import java.awt.*;
import model.*;
import view.*;
public class TreeView extends Panel{

	//private Container pane;	
	private int y;
	private int x;

	/*public TreeView(int x, int y, Container pane) {
		      
		this.pane = pane;
		pane.setLayout(new GridLayout(x,y));
		pane.add(new JTextArea(10, 40));
	}
*/
	public TreeView(int x, int y){
		this.setLayout(new GridLayout(x,y));
	}
	public boolean addComponentAt(Component component, int x, int y){
		this.add(component);
		return true;

	}
	    
}
