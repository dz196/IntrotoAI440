package apps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import structures.Graph;

public class MSTDriver {
	
	static BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String [] args) throws IOException{
		
		System.out.println("Enter name of Graph File: ");
		String file = keyboard.readLine();
		Graph graph = new Graph(file);
		graph.print();
		PartialTreeList list = MST.initialize(graph);
		System.out.println(MST.execute(list));
		//Vertex v = 
	}
	

}