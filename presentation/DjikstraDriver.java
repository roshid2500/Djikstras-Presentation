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
		List<String> lines = new ArrayList<String>(); 
		Scanner x; 
		//get the lines of the file 
		String f = args[0];
		try {
			x = new Scanner(new File(f));
			while(x.hasNext()) 
				lines.add(x.nextLine()); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		GraphList g = new GraphList(lines.size() - 1);
		int size = 0; 
		
		
		
		for(int z = 0; z < lines.size(); z++) {
			String[] parts = lines.get(z).split(" ");
			
			if(parts.length == 1 && !parts[0].contains(","))
				size = Integer.valueOf(parts[0]);
			
			else {
				for(int i = 0; i < parts.length; i++) {
					String[] parts2 = parts[i].split(",");
					g.addEdge(z - 1, Integer.valueOf(parts2[0]), Integer.valueOf(parts2[1]));
				}
				
			}
		}
		
		
		
		g.prettyPrint();
		System.out.println(); 
		
		//ADD IN START STATE AND DEST STATE
		int start = 0, dest= 19; 
		
		System.out.println("USING A HEAP");
		long startTime2 = System.nanoTime();
		DjikstrasHeap d1 = new DjikstrasHeap(g, start, dest);
		long endTime2 = System.nanoTime();
		System.out.println("time: " + (endTime2 - startTime2));
		System.out.println(); 
		
		
		System.out.println("USING A list");
		long startTime = System.nanoTime();
		DjikstrasList d = new DjikstrasList(g, start, dest);
		long endTime = System.nanoTime();
		System.out.println("time: " + (endTime - startTime));
		System.out.println(); 
		
		
	}
}
