default:
	make -C src all
jar: 
	make -C src all
	jar -cvfm SkillTree.jar MANIFEST.MF src/SkillTree.class
			@echo "now run your program with java -jar SkillTree.jar"
clean: 
	make -C src clean
	rm -rf *.jar
	rm -rf *.class
