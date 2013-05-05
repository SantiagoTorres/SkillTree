/**
 * This class make the Skill Detail View Window
 * @author Mike "The Mechanic" Kelly & Santiago Torres Arias
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class SkillDetailView extends JFrame{
    
    private SkillModel sm;
    private JLabel name;
    private JLabel level;
    private JProgressBar progress;
    private JScrollPane reqScrollPane;
    private JScrollPane millScrollPane;
    private JProgressBar mileProgress;
    private JLabel detail;
    private JScrollPane parentScrollPane;
    private JScrollPane sonsScrollPane;
    private JButton closeButton;

    public SkillDetailView(SkillModel sm){
	super("Skill Detail: "+sm.getName());
	int milestoneNumber;
	this.sm = sm;
	Box topVBox=Box.createVerticalBox();
	Box topHBox=Box.createHorizontalBox();
	Box downHBox=Box.createHorizontalBox();
	Box reqVBox=Box.createVerticalBox();
	Box mileVBox=Box.createVerticalBox();
	Box rightVBox=Box.createVerticalBox();
	Box detailsVBox=Box.createVerticalBox();
	Box rightDownHBox=Box.createHorizontalBox();
	Box parentVBox=Box.createVerticalBox();
	Box sonsVBox=Box.createVerticalBox();

	this.name = new JLabel(this.sm.getName()+"  ");
	String levelSt = new String("level: "+this.sm.getLevel()+"  ");
	this.level= new JLabel(levelSt);
	this.progress = new JProgressBar(0,this.sm.getMaxLevel());
	this.progress.setValue(this.sm.getLevel());
	//this.progress.setValue(5);
	//this.reqScrollPane.setAlignmentX(LEFT_ALIGMENT);
	String[] reqString=this.sm.getRequirements();
	Box reqScrollPaneVBox=Box.createVerticalBox();
	if(reqString!=null){
		for(int i=0;i<this.sm.getRequirements().length;i++){
	    		reqScrollPaneVBox.add(new JLabel("<html> * "+reqString[i]+"</html>"));
		}
	}
	this.reqScrollPane = new JScrollPane(reqScrollPaneVBox);
	this.reqScrollPane.setPreferredSize(new Dimension(250,250));
	
	String[] mileString=this.sm.getMilestones();
	int[] achieved=this.sm.getAchieved();
	int mileProg=0;
	milestoneNumber = mileString.length;
	if(milestoneNumber < 1){ //basic overflow check, avoiding exceptions...
		milestoneNumber = 1;
	}
	this.mileProgress= new JProgressBar(1,milestoneNumber);
	
	Box mileStoneVBox=Box.createVerticalBox();
	for(int i=0;i<mileString.length;i++){
	    if(achieved[i]==1){
		JRadioButton jrb = new JRadioButton("<html>"+mileString[i]+"</html>",
						    true);
		jrb.addActionListener(new milestoneClickListener(i,sm));
		mileStoneVBox.add(jrb);
		mileProg++;
	    }else{
		JRadioButton jrb2 = new JRadioButton("<html>"+mileString[i]+"</html>",
						     false);
		jrb2.addActionListener(new milestoneClickListener(i,sm));
		mileStoneVBox.add(jrb2);
	    }
	}
	this.millScrollPane= new JScrollPane(mileStoneVBox);
	this.millScrollPane.setPreferredSize(new Dimension(450,250));
	this.mileProgress.setValue(mileProg);
	this.mileProgress.setString(mileProg+"/"+mileString.length);
	this.detail=new JLabel("<html><p>"+
			       this.sm.getDetails()[this.sm.getLevel()]+
			       "</p></html>");
	SkillModel[] parent = this.sm.getParents();
	SkillModel[] sons = this.sm.getSons();
	Box parentScrollPaneVBox=Box.createVerticalBox();
	Box sonsScrollPaneVBox=Box.createVerticalBox();
	if(parent != null){ //simple NPE avoidance for the parents reference, since it can be built on null
		for(int i=0;i<parent.length;i++){
	 	   	parentScrollPaneVBox.add(new JLabel(parent[i].getName()));
		}
	}
	if(sons != null){ //simple NPE exception avoidance for the sons reference (since it can be built on null)
		for(int i=0;i<sons.length;i++){
	    		sonsScrollPaneVBox.add(new JLabel(sons[i].getName()));
		}
	}

	this.parentScrollPane=new JScrollPane();
	this.parentScrollPane.setPreferredSize(new Dimension(120,120));
	this.sonsScrollPane=new JScrollPane();
	this.sonsScrollPane.setPreferredSize(new Dimension(120,120));
	/******/

	
	/**/
       	parentVBox.add(new JLabel("\n"));
	parentVBox.add(new JLabel("<html><p> Parents </p></html>"));
	parentVBox.add(Box.createRigidArea(new Dimension(0,5)));
	parentVBox.add(this.parentScrollPane);
	sonsVBox.add(new JLabel("\n"));
	sonsVBox.add(new JLabel("<html><p> Sons </p></html>"));
	sonsVBox.add(Box.createRigidArea(new Dimension(0,5)));
	sonsVBox.add(this.sonsScrollPane);
	rightDownHBox.add(parentVBox);
	rightDownHBox.add(Box.createRigidArea(new Dimension(5,0)));
	rightDownHBox.add(sonsVBox);
	
	detailsVBox.add(new JSeparator());
	this.detail.setPreferredSize(new Dimension(250,40));
	detailsVBox.add(Box.createRigidArea(new Dimension(0,10)));
	detailsVBox.add(new JLabel("Detail:"));
	detailsVBox.add(Box.createRigidArea(new Dimension(0,3)));
	detailsVBox.add(this.detail);
	detailsVBox.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
	closeButton = new JButton("Close");
	closeButton.addActionListener(new closeButtonListener(this));
	detailsVBox.add(closeButton);

	//JScrollPane JSp = new JScrollPane();
	//JSp.setPreferredSize(new Dimension(250,0));
	//JLabel jl=new JLabel("<html><p>ME CAGO EN TU PUTA MADRE NO SE QUE CHINGADOS ESTA PASANDO AQUI SOLO SE QUE ESTA COSA ES UNA MIERDA.SANTIAGO YA ESTA HASTA LA MADRE. ESTO ES UNA DESCRIPCION</p></html>");
	//detailsVBox.add(JSp);
	//jl.setPreferredSize(new Dimension(250,30));
	this.add(detailsVBox,BorderLayout.PAGE_END);
    //rightVBox.add(new JSeparator());
	rightVBox.add(rightDownHBox/*,BorderLayout.SOUTH*/);
	
	//mileVBox.add(new JLabel("\n"));
	mileVBox.add(this.mileProgress);
	mileVBox.add(Box.createRigidArea(new Dimension(0,5)));
	mileVBox.add(new JLabel("Milestones"));
	mileVBox.add(Box.createRigidArea(new Dimension(0,5)));
	mileVBox.add(this.millScrollPane);
	reqVBox.add(new JLabel("Requirements"));
	reqVBox.add(Box.createRigidArea(new Dimension(0,5)));
	reqVBox.add(this.reqScrollPane);
	reqVBox.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
	mileVBox.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
	rightVBox.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
	this.add(reqVBox,BorderLayout.WEST);
	this.add(mileVBox,BorderLayout.CENTER);
	this.add(rightVBox,BorderLayout.EAST);
	
	//topVBox.setMinimumSize(new Dimension(350,350));
	topHBox.add(this.name);
	topHBox.add(this.level);
	topHBox.add(this.progress);
	topVBox.add(new JLabel("\n"));
	topVBox.add(topHBox);
	topVBox.add(new JLabel("\n"));
	topVBox.add(new JSeparator());
	//topVBox.add(downHBox);
	topVBox.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
	//this.add(topHBox,BorderLayout.NORTH);
	//this.add(new JLabel("\n"),BorderLayout.NORTH);
	//this.add(new JSeparator(),BorderLayout.NORTH);
	this.add(topVBox,BorderLayout.NORTH);
	//this.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
	//this.add(downHBox,BorderLayout.CENTER);
	//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//this.setSize(350, 350);
	this.setLocation(100,500);
	this.pack();
	this.setVisible(true);
    }


    private class milestoneClickListener implements ActionListener{
	private int num;
	private SkillModel sm;
	
	public milestoneClickListener(int i,SkillModel sm){
	    this.num=i;
	    this.sm=sm;
	}
	
	public void actionPerformed(ActionEvent e){
	    this.sm.setAchieved(num);
	}

    }
    private class closeButtonListener implements ActionListener{
	private SkillDetailView parent;
	
	public closeButtonListener(SkillDetailView parent){
		super();
		this.parent = parent;
	}

	public void actionPerformed(ActionEvent e){
		parent.dispose();
	}
    }

}
