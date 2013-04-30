/*
 *  CreateSkill.java
 *  A windowed view-controller that creates SkillModel instances for the SkillTree project
 *  
 *  Authors: Santiago Torres Arias & Miguel Angel Kelly Delgado
 *  Date:    apr 26, 2013
 */
import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.event.*;
public class CreateSkill {
	private JFrame thePanel;
	private JLabel name;
	private JLabel MaxLevel;
	private JTextField nameField;
	private JTextField maxLevelField;
	private JTabbedPane descriptionsPane;
	private ArrayList<JTextArea> descriptionsFields;
	private JList Requirements;
	private JList Milestones;
	private JList Parents;
	private JButton okButton;
	private JButton cancelButton;

	public CreateSkill(TreeModel theTreeView){
		Box vbox = new Box(BoxLayout.Y_AXIS);
		Box hbox = new Box(BoxLayout.X_AXIS);
		Box nameBox = new Box(BoxLayout.X_AXIS);
		Box MaxLVLBox = new Box(BoxLayout.X_AXIS);
		Box milestoneReqs = new Box(BoxLayout.X_AXIS);
		Box desParents = new Box(BoxLayout.Y_AXIS);
		Box OkCancel = new Box(BoxLayout.X_AXIS);

		thePanel = new JFrame("Create a Skill");
		this.name = new JLabel("Name: ");
		this.MaxLevel = new JLabel("Max lvl: ");

		this.nameField = new JTextField(30);
		this.nameField.setMaximumSize(new Dimension(300,15));

		this.maxLevelField = new JTextField(30);
		this.maxLevelField.setMaximumSize(new Dimension(300,15));

		this.descriptionsPane = new JTabbedPane();
		this.descriptionsPane.setMaximumSize(new Dimension(300,300));
		this.descriptionsFields = new ArrayList<JTextArea>(1);
		this.maxLevelField.addActionListener(new levelListener(this.descriptionsPane,this.descriptionsFields));

		this.Requirements = new JList();
		


		this.Milestones = new JList();
		

		this.Parents = new JList(traverseTreeForNames(theTreeView));
		this.Parents.addListSelectionListener(new ListListener(this.Milestones,theTreeView));
		this.okButton = new JButton("Ok");
		this.cancelButton = new JButton("Cancel");
		
		this.descriptionsFields.add(new JTextArea());
		this.descriptionsPane.addTab("0",this.descriptionsFields.get(0));

		nameBox.add(name);
		nameBox.add(nameField);
		nameBox.add(Box.createHorizontalGlue());
		
		MaxLVLBox.add(MaxLevel);
		MaxLVLBox.add(maxLevelField);
		MaxLVLBox.add(Box.createHorizontalGlue());


		milestoneReqs.add(Requirements);
		milestoneReqs.add(Milestones);
		milestoneReqs.add(Box.createHorizontalGlue());

		vbox.add(nameBox);
		vbox.add(MaxLVLBox);
		vbox.add(Box.createVerticalGlue());
		vbox.add(milestoneReqs);
		
		OkCancel.add(cancelButton);
		OkCancel.add(okButton);

		desParents.add(descriptionsPane);
		desParents.add(Parents);
		desParents.add(OkCancel);

		//hbox.add(vbox);
		//hbox.add(desParents);

		//thePanel.add(vbox,BorderLayout.WEST);
		//thePanel.add(desParents,BorderLayout.CENTER);
		thePanel.getContentPane().setLayout(new BoxLayout(thePanel.getContentPane(),BoxLayout.X_AXIS));
		thePanel.add(vbox);
		thePanel.add(desParents);
		thePanel.getContentPane().setMaximumSize(new Dimension(600,400));
		thePanel.pack();
		thePanel.setVisible(true);
	}

	private String[] traverseTreeForNames(TreeModel tree){
		ArrayList<String> names = new ArrayList<String>(tree.getList().size());
		SkillModel ref;
		ArrayList<SkillModel> treeList = tree.getList(); 
		for(int i=0;i<treeList.size();i++){
			ref = treeList.get(i);
			names.add(ref.getName());
		}
		return names.<String>toArray(new String[tree.getList().size()]);
	}

	private String[] traverseTreeForRequirements(TreeModel tree, int[] indexes){
		//String[] names = traverseTreeForNames(tree);
		String[] Milestones;
		SkillModel ref;
		ArrayList<SkillModel> treeList = tree.getList();
		ArrayList<String> requirements = new ArrayList<String>();
		for(int i=0;i<indexes.length;i++){
			ref = treeList.get(indexes[i]);
			Milestones = ref.getMilestones();
			for(int j=0;j<Milestones.length;j++){
				requirements.add(new String(ref.getName() + " - " + Milestones[j]));
			}
		
		}
		return requirements.<String>toArray(new String[requirements.size()]);
	}

	private class ListListener implements ListSelectionListener{
		private JList listToUpdate;
		TreeModel localModel;	
		public ListListener(JList listToUpdate,TreeModel model){
			super();
			this.listToUpdate = listToUpdate;
			this.localModel = model;
		}

		public void valueChanged(ListSelectionEvent e){
			if(e.getValueIsAdjusting()){
				return; //lets wait for non shitty messages
			}
			JList source = ((JList)e.getSource());
			int[] indexes = source.getSelectedIndices();
			System.out.println(source.getSelectedIndices().length);
			String[] Requirements = traverseTreeForRequirements(this.localModel,indexes);
			this.listToUpdate.setListData(Requirements);
			this.listToUpdate.validate();
		}
	}

	private class levelListener implements ActionListener{
		private JTabbedPane descriptionsPane;
		private int noOfTabs;
		private ArrayList<JTextArea> descriptionTexts;
		public levelListener(JTabbedPane descriptionsPane, ArrayList<JTextArea> descriptionTexts){
			this.descriptionsPane=descriptionsPane;
			this.descriptionTexts= descriptionTexts;
			this.noOfTabs=this.descriptionTexts.size();
		}

		public void actionPerformed(ActionEvent e){
			int miniWatchdog = 0;
			this.noOfTabs = Integer.parseInt(e.getActionCommand());
			if(this.noOfTabs<1){
				this.noOfTabs=1;
			}
			System.out.println(e.getActionCommand());
			while(this.noOfTabs!=this.descriptionsPane.getTabCount()){
				System.out.println("Current Tabs: " +this.noOfTabs + "/" + this.descriptionsPane.getTabCount());
				if(this.noOfTabs>this.descriptionsPane.getTabCount()){
					descriptionTexts.add(new JTextArea());
					descriptionsPane.addTab(""+this.descriptionsPane.getTabCount(),descriptionTexts.get(this.descriptionsPane.getTabCount()));
					//this.noOfTabs++;
				}else if(this.noOfTabs<this.descriptionsPane.getTabCount()){
					descriptionsPane.remove(this.noOfTabs);
					//this.noOfTabs--;
				}
				miniWatchdog++;
				if(miniWatchdog > 10){
					break;
				}
					
		
			}
		}
	}

}
