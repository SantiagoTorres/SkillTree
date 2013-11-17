all: skill_tree
	
model:
	make -C model all

view: model
	make -C view all

skill_tree: model view
	javac -classpath . SkillTree.java


jar:
	jar -cvfm ../SkillTree.jar ../MANIFEST.MF model/*.class view/*.class *.class

clean: 
	make -C model clean
	make -C view clean
	rm -rf *.class
	rm -rf *~
	rm -rf *.sw*
