CC = javac
all: *.java
	${CC} *.java
clean:
	rm *.class
