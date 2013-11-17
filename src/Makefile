all: SkillTree.class CreateSkill.class MainWindow.class SkillModel.class SkillToolTip.class SkillView.class TreeModel.class TreeView.class
	
SkillTree.class: CreateSkill.class MainWindow.class SkillModel.class SkillToolTip.class SkillView.class TreeModel.class TreeView.class
	javac SkillTree.java

CreateSkill.class:
	javac CreateSkill.java

MainWindow.class: 
	javac MainWindow.java

SkillModel.class:
	javac SkillModel.java 

SkillToolTip.class: 
	javac SkillToolTip.java

SkillView.class:
	javac SkillView.java

TreeModel.class:
	javac TreeModel.java

TreeView.class:
	javac TreeView.java

jar:
	jar -cvfm ../SkillTree.jar ../MANIFEST.MF *.class 

clean: 
	rm -rf *.class
	rm -rf *~
	rm -rf *.sw*
