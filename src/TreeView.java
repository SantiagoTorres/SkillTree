import javax.swing.*;
import java.awt.*;
public class TreeView {

	private Container pane;	
	private int y;
	private int x;

	public TreeView(int x, int y, Container pane) {
		      
		this.pane = pane;
		GridBagConstraints c = new GridBagConstraints();
		pane.setLayout(new GridLayout(x,y));
		pane.add(new JTextArea(10, 40),c);
			          
	}

	public boolean addComponentAt(Component component, int x, int y){
		return true;

	}
	    
}
