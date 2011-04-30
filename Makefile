all: math euclid Euclid.class

math:
	javac -d bin -sourcepath src/com/alexandria/math/*.java

euclid: 
	javac -d bin -sourcepath src/com/alexandria/euclid/*.java

Euclid.class:
	javac -verbose -d bin -classpath /opt/jruby/lib/jruby.jar src/com/alexandria/euclid/Euclid.java
