#include <iostream>
#include <fstream>
#include <stdlib.h>
#include <vector>

using namespace std;

int main(int argc, char* argv[]){
	string type = argv[1];
	unsigned long nodes = stoi(argv[2]);
	unsigned long maxedges = stoi(argv[3]);
	unsigned long maxdist = stoi(argv[4]);
	if(nodes == 0 || maxedges == 0 || maxedges >= nodes || maxdist == 0){printf("Invalid values\n");return 0;}
	ofstream outfile(argv[5]);
	outfile << nodes <<  "\n";
	vector<unsigned long> emp;
	for(unsigned long i = 0; i < nodes; i++){emp.push_back(0);}
	vector<vector<unsigned long>> matrix(nodes,emp);
	vector<vector<unsigned long>> matrix2 = matrix;
	unsigned long dist;
	unsigned long num;
	unsigned long node;
	vector<unsigned long> empspaces(nodes,0);
	srand(time(NULL));
	if(type == "undirected"){
		for(unsigned long i = 0; i < nodes;i++){
			num = rand() % maxedges + 1;
			printf("num: %lu  empspace: %lu   result: %lu\n",num,empspaces[i],num-empspaces[i]);
			if(num - empspaces[i] <= 0 || num - empspaces[i] > nodes){num = 0;}
			else{
				num = num - empspaces[i];
				for(unsigned long k = 0;k < num;k++){
					//printf("i: %lu   k: %lu num: %lu\n",i,k,num);
					node = rand() % nodes - 1;
					if(empspaces[node] == nodes){continue;}
					if(empspaces[i] == nodes){break;}
					if(node != i && matrix[i][node] == 0){
						dist = rand() % maxdist + 1;
						matrix[i][node] = dist;
						empspaces[i]++;
						matrix[node][i] = dist;
						empspaces[node]++;
						//k++;
					}
				}	
			}
		}
		printf("matrix completed\n");
		for(unsigned long i = 0; i < nodes;i++){
			//outfile << i;
			for(unsigned long k = 0; k < nodes;k++){
				if(matrix[i][k] != 0){outfile << " " << k << "," << matrix[i][k] << " ";}
				//if(matrix[k][i] != 0){outfile << " " << k << "," << matrix[k][i] << " ";}
	
			}
			outfile << "\n";
		}
	}else if(type == "directed"){
		for(unsigned long i = 0; i < nodes;i++){
			num = rand() % maxedges + 1;
			for(unsigned long k = 0;k < num;k++){
				node = rand() % nodes - 1;
				if(node != i && matrix[i][node] == 0){
					dist = rand() % maxdist + 1;
					matrix[i][node] = dist;
					empspaces[i]++;
					//k++;
				}
			}
		}
		//printf("first matrix done\n");
		for(unsigned long i = 0; i < nodes;i++){
			num = rand() % maxedges + 1;
			//printf("num: %lu  empspace: %lu   result: %lu\n",num,empspaces[i],num-empspaces[i]);
			if(num - empspaces[i] <= 0 || num - empspaces[i] > nodes){num = 0;}
			else{
				num = num - empspaces[i];
				for(unsigned long k = 0;k < num;k++){
					node = rand() % nodes - 1;
					if(empspaces[i] == nodes){break;}
					if(node != i && matrix2[i][node] == 0){
						dist = rand() % maxdist + 1;
						matrix2[i][node] = dist;
						//k++;
					}
				}
			}
		}
		for(unsigned long i = 0; i < nodes;i++){
			//outfile << i;
			for(unsigned long k = 0; k < nodes;k++){
				if(matrix[i][k] != 0){outfile << " " << k << "," << matrix[i][k] << " ";}
				if(matrix2[i][k] != 0 && matrix[i][k] == 0){outfile << " " << k << "," << matrix2[i][k] << " ";}
	
			}
			outfile << "\n";
		}

	}
}
