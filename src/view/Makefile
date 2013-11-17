all: SkillView.class SkillDetailView.java CreateSkill.class SkillToolTip.class TreeView.class
	
SkillDetailView.class:
	javac -classpath .. SkillDetailView.java

SkillView.class MainWindow.java:
	javac -classpath .. SkillView.java MainWindow.java

CreateSkill.class SkillToolTip.class TreeView.class:
	javac -classpath .. CreateSkill.java SkillToolTip.java TreeView.java

clean: 
	rm -rf *.class
	rm -rf *~
	rm -rf *.sw*
