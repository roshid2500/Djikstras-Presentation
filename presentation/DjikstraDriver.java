package presentation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class DjikstraDriver {
	public static void main(String[] args) {
		System.out.println(args[0]);
		GraphList g = new GraphList(5);
		List<String> lines = new ArrayList<String>(); 
		Scanner x; 
		//get the lines of the file 
		String f = args[0];
		try {
			x = new Scanner(new File(f));
			while(x.hasNext()) 
				lines.add(x.nextLine()); 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int size = 0; 
		for(int z = 0; z < lines.size(); z++) {
			System.out.println(lines.get(z));
			String[] parts = lines.get(z).split(" ");
			if(parts.length == 1 && !parts[0].contains(","))
				size = Integer.valueOf(parts[0]);
			else {
				for(int i = 0; i < parts.length; i++) {
					String[] parts2 = parts[i].split(",");
					g.addEdge(z, Integer.valueOf(parts2[0]), Integer.valueOf(parts2[1]));
				}
			}
		}
		g.prettyPrint();
		System.out.println(); 
		
		//ADD IN START STATE AND DEST STATE
		int start = 0, dest= 0; 
		Djikstras d = new Djikstras(g, start, dest);
		
	}
}
