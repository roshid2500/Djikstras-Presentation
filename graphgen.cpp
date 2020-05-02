#include <iostream>
#include <fstream>
#include <stdlib.h>
#include <vector>

using namespace std;

int main(int argc, char* argv[]){
	//string type = argv[1];
	unsigned long nodes = stoi(argv[1]);
	unsigned long maxedges = stoi(argv[2]);
	unsigned long maxdist = stoi(argv[3]);
	ofstream outfile(argv[4]);
	outfile << nodes <<  "\n";
	vector<unsigned long> emp;
	for(unsigned long i = 0; i < nodes; i++){emp.push_back(0);}
	vector<vector<unsigned long>> matrix(nodes,emp);
	unsigned long dist;
	unsigned long num;
	unsigned long node;
	for(unsigned long i = 0; i < nodes;i++){
		num = rand() % maxedges + 1;
		for(unsigned long k = 0;k < num;){
			node = rand() % nodes - 1;
			if(node != i && matrix[i][node] == 0){
				dist = rand() % maxdist + 1;
				matrix[i][node] = dist;
				k++;
			}
		}
	}
	for(unsigned long i = 0; i < nodes;i++){
		//outfile << i;
		for(unsigned long k = 0; k < nodes;k++){
			if(matrix[i][k] != 0){outfile << " " << k << "," << matrix[i][k] << " ";}
			if(matrix[k][i] != 0){outfile << " " << k << "," << matrix[k][i] << " ";}

		}
		outfile << "\n";
	}
}
