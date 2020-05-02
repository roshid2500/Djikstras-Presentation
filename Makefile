CFLAGS = -Wall -DBSD -DNDEBUG

all: graphgen

graphgen: graphgen.o
	g++ $(CFLAGS) -o $@ graphgen.o

graphgen.o: graphgen.cpp
	g++ $(CFLAGS) -c $*.cpp
	
clean: 
	rm -rf *.o graphgen
	rm output.txt
