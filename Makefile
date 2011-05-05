# Compile all java and create jar
all: alexandria euclid
	jar cf euclid.jar bin/com/alexandria/math/*.class bin/com/alexandria/euclid/*.class

# compile complex seperately as it gets missed by the compiler
alexandria: Complex.class
	javac -d bin -sourcepath src/com/alexandria/math/*.java

# compile Euclid seperately as it gets missed by the compiler
euclid: Euclid.class
	javac -d bin -sourcepath src/com/alexandria/euclid/*.java

Euclid.class:
	javac -d bin -classpath /opt/jruby/lib/jruby.jar src/com/alexandria/euclid/Euclid.java

Complex.class:
	javac -d bin src/com/alexandria/math/Complex.java

# Clean and Compile
refresh: clean all

#Remove compiled files
clean:
	rm -rf bin
	mkdir bin
