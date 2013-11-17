default:
	make -C src all
jar: 
	make -C src all
	make -C src jar
			@echo "now run your program with java -jar SkillTree.jar"
clean: 
	make -C src clean
	rm -rf *.jar
	rm -rf *.class
	rm -rf *~
