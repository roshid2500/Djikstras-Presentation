GraphList and Node classes are just the Graph data structure. It was built using 
HashMap to hold the adjacency lists of nodes. Otherwise, it is a basic build 
for an adjacency list. 

HeapNode and ListNode are used by the Djikstra's heap and list implementations 
respectively. They simply hold a vertex and its weight in relation to another
node. 

DjikstrasList and DjikstrasHeap are the two implementations for Djikstra's 
algorithm. As there name states, they use a list and heap respectively, 
in order to choose the minimum weighted edge. 

In order to run this program you must input in an input file to the driver class
which is named DjikstrasDriver. The input file must be in a similar form as 
the input files in the directory ../graphs/ or ../graphs2 
The driver will read the input file and create the graph data structure 
with all the stated edges. After that, it will calculate the minimum weighted
path to the desired destination and print that out. 
In order to choose the starting node and destination node you must set those 
where it is marked in the driver. 

*NOTE you must compile all these files together. 