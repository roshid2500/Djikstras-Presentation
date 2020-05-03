#include <iostream>
#include <fstream>
#include <stdlib.h>
#include <vector>
#include <algorithm>
//#include <set>

using namespace std;

int main(int argc, char* argv[]){
	string comp = argv[1];
	string type = argv[2];
	unsigned long nodes = stoi(argv[3]);
	unsigned long maxedges = stoi(argv[4]);
	unsigned long maxdist = stoi(argv[5]);
	if(nodes == 0 || maxedges == 0 || maxedges >= nodes || maxdist == 0){printf("Invalid values\n");return 0;}
	ofstream outfile(argv[6]);
	outfile << nodes <<  "\n";
	vector<unsigned long> emp;
	for(unsigned long i = 0; i < nodes; i++){emp.push_back(0);}
	vector<vector<unsigned long>> matrix(nodes,emp);
	vector<vector<unsigned long>> matrix2 = matrix;
	unsigned long dist;
	unsigned long num;
	unsigned long node;
	vector<unsigned long> empspaces(nodes,0);
	int connect = 0;
	int added = 1;
	vector<unsigned long> mapped;
	srand(time(NULL));
	if(type == "undirected"){
		if(comp == "complete"){
			num = nodes;
			for(unsigned long i = 0; i < nodes;i++){
				//num = nodes - 1;
				//printf("num: %lu  empspace: %lu   result: %lu\n",num,empspaces[i],num-empspaces[i]);
				//num = num - empspaces[i];
				for(unsigned long k = 0;k < num;k++){
					//printf("i: %lu   k: %lu num: %lu\n",i,k,num);
					if(k != i && matrix[i][k] == 0){
						dist = rand() % maxdist + 1;
						matrix[i][k] = dist;
						//empspaces[i]++;
						matrix[k][i] = dist;
						//empspaces[node]++;
						//k++;
					}
				}	
			}
		}else{
			for(unsigned long i = 0; i < nodes;i++){
				num = rand() % maxedges + 1;
				//printf("num: %lu  empspace: %lu   result: %lu\n",num,empspaces[i],num-empspaces[i]);
				if(num - empspaces[i] <= 0 || num - empspaces[i] > nodes){num = 0;}
				else{
					num = num - empspaces[i];
					for(unsigned long k = 0;k < num;k++){
						//printf("i: %lu   k: %lu num: %lu\n",i,k,num);
						node = rand() % nodes;
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
			//check for connectivity
			mapped.push_back(0);
			for(unsigned long i = 0;i < nodes;i++){if(matrix[0][i] != 0){mapped.push_back(i);}}
			//printf("zero updated\n");
			while(connect == 0){
				while(added == 1){		//cycle to add things.
					added = 0;
					for(unsigned long i = 0; i < nodes;i++){	//search nodes
						//printf("about to search mapped\n");
						if(find(mapped.begin(),mapped.end(),i) != mapped.end()){	//if node in mapped
					//		printf("mapped searched\n");
							for(unsigned long k = 0;k < nodes;k++){			//map node destinations
								//printf("in for loop\n");
								if(matrix[i][k] != 0 && find(mapped.begin(),mapped.end(),k) == mapped.end()){mapped.push_back(k);added = 1;}
							}
						  //     	printf("after if statement\n");	
						}

					}
				}
				//printf("stuff added\n");
				//printf("mapped size: %lu\n",mapped.size());
				if(mapped.size() >= nodes){connect = 1;}
				else{
					unsigned long other;
					for(unsigned long i = 0;i < nodes;i++){
						if(find(mapped.begin(),mapped.end(),i) == mapped.end()){other = i;break;}
					}
					unsigned long con = mapped[rand() % mapped.size()];
					//printf("con: %lu \n",con);
					dist = rand() % maxdist + 1;
					matrix[con][other] = dist;
					matrix[other][con] = dist;
					added = 1;	
				}
			}
		}
		//printf("matrix completed\n");
		//printf("mapped size: %lu\n",mapped.size());
		for(unsigned long i = 0; i < nodes;i++){
			//outfile << i;
			for(unsigned long k = 0; k < nodes;k++){
				if(matrix[i][k] != 0){outfile << k << "," << matrix[i][k] << " ";}
				//if(matrix[k][i] != 0){outfile << " " << k << "," << matrix[k][i] << " ";}
	
			}
			outfile << "\n";
		}
	}else if(type == "directed"){
		if(comp == "complete"){
			num = nodes;
			for(unsigned long i = 0; i < nodes;i++){
				//num = rand() % maxedges + 1;
				for(unsigned long k = 0;k < num;k++){
					//node = rand() % nodes - 1;
					if(k != i && matrix[i][k] == 0){
						dist = rand() % maxdist + 1;
						matrix[i][k] = dist;
						//empspaces[i]++;
						//k++;
					}
				}
			}
		}else{
			for(unsigned long i = 0; i < nodes;i++){
				num = rand() % maxedges + 1;
				for(unsigned long k = 0;k < num;k++){
					node = rand() % nodes;
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

			//check for connectivity
			mapped.push_back(0);
			for(unsigned long i = 0;i < nodes;i++){
				if(matrix[0][i] != 0){mapped.push_back(i);}
				if(matrix2[0][i] != 0){mapped.push_back(i);}
			}
			//printf("zero updated\n");
			while(connect == 0){
				while(added == 1){		//cycle to add things.
					added = 0;
					for(unsigned long i = 0; i < nodes;i++){	//search nodes
						//printf("about to search mapped\n");
						if(find(mapped.begin(),mapped.end(),i) != mapped.end()){	//if node in mapped
					//		printf("mapped searched\n");
							for(unsigned long k = 0;k < nodes;k++){			//map node destinations
								//printf("in for loop\n");
								if(matrix[i][k] != 0 && find(mapped.begin(),mapped.end(),k) == mapped.end()){mapped.push_back(k);added = 1;}
								if(matrix2[i][k] != 0 && find(mapped.begin(),mapped.end(),k) == mapped.end()){mapped.push_back(k);added = 1;}
							}
						  //     	printf("after if statement\n");	
						}

					}
				}
				//printf("stuff added\n");
				//printf("mapped size: %lu\n",mapped.size());
				if(mapped.size() >= nodes){connect = 1;}
				else{
					unsigned long other;
					for(unsigned long i = 0;i < nodes;i++){
						if(find(mapped.begin(),mapped.end(),i) == mapped.end()){other = i;break;}
					}
					unsigned long con = mapped[rand() % mapped.size()];
					//printf("con: %lu \n",con);
					dist = rand() % maxdist + 1;
					matrix[con][other] = dist;
					matrix[other][con] = dist;
					added = 1;	
				}
			}

		}
		//printf("going into i loop\n");
		for(unsigned long i = 0; i < nodes;i++){
			//outfile << i;
			for(unsigned long k = 0; k < nodes;k++){
				//printf("put infile\n");
				if(matrix[i][k] != 0){outfile << k << "," << matrix[i][k] << " ";}
				if(comp != "complete"){if(matrix2[i][k] != 0 && matrix[i][k] == 0){outfile << k << "," << matrix2[i][k] << " ";}}
	
			}
			outfile << "\n";
		}

	}
}
