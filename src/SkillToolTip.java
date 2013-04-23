import javax.swing.*;
import java.awt.*;
/**
 * This Class is the toolTips that show the Skill
 * @author Mike "The Mechanic" Kelly
 * */

public class SkillToolTip extends JToolTip{
    
    private SkillModel sm;

    
    private Box vbox;
    private JLabel nameLabel = new JLabel();
    private JLabel description = new JLabel();
    private JSeparator ndSEPmil = new JSeparator(); //Separator from name and desc from milestones
    private JRadioButton[] milestones;
    
    public SkillToolTip(SkillModel sm){
	super();
	this.sm = sm;
	nameLabel.setText("<html><p>"+this.sm.getName().toUpperCase()+"</p></html>");
	description.setText("<html><p>"+this.sm.getDetails()[this.sm.getLevel()]+"</p></html>");
	milestones=getRadioMilestones(this.sm.getMilestones(),this.sm.getAchieved());
	this.vbox=makeTheBox(Box.createVerticalBox());
	this.add(vbox);
    }

    private JRadioButton[] getRadioMilestones(String[] milestones,int[] achieved){
	JRadioButton[] jrb= new JRadioButton[milestones.length];
	for(int i=0;i<milestones.length;i++){
	    if(achieved[i]==1){
		jrb[i]=new JRadioButton("<html>"+milestones[i]+"</html>",true);
	    }else{
		jrb[i]=new JRadioButton("<html>"+milestones[i]+"</html>",false);
	    }
	}
	return jrb;
    }

    private Box makeTheBox(Box v){
	v.add(this.nameLabel);
	v.add(new JLabel("\n"));
	v.add(this.description);
	v.add(new JLabel("\n"));
	v.add(ndSEPmil);
	for(int i=0;i<this.milestones.length;i++){
	    v.add(this.milestones[i]);
	}
	return v;
    }

}