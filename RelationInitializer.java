import java.util.Scanner;
import java.util.LinkedList;
public class RelationInitializer {
	private static Scanner in = new Scanner(System.in);
	
	public static void input() {
		char userChoice;
		// =================================== begin input =======================================
		System.out.println("Enter input format: Edges[e], Matrix[m], adjacency[a]");
		userChoice = in.next().charAt(0);
		
		if(userChoice == 'e' || userChoice == 'E') {
			@SuppressWarnings("unused")
			Relation r = constructRelation();
		}
		else if(userChoice == 'm' || userChoice == 'M') {
			@SuppressWarnings("unused")
			RelationMatrix rm = constructRelationMatrix();
		}
		else if(userChoice == 'a' || userChoice == 'A') {
			@SuppressWarnings("unused")
			RelationAdjacency ra = constructRelationAdjacency();
		}
	}
	
	private static RelationAdjacency constructRelationAdjacency() {
		LinkedList<LinkedList<String>> adjacency = new LinkedList<LinkedList<String>>();
		LinkedList<String> temp = new LinkedList<String>();
		
		int num;
		System.out.println("Enter number of vertices: ");
		num = in.nextInt();
		
		System.out.println("Enter each vertex and the vertices it points to (format: a b c d). Hit enter between lines.");
		in.nextLine();
		for(int i = 0; i < num; i++) {
			String rawIn = in.nextLine();
			String[] splitIn = rawIn.split(" ");
			temp.clear();
			for(int j = 0; j < splitIn.length; j++) 
				temp.add(splitIn[j]);
			
			@SuppressWarnings("unchecked")
			LinkedList<String> tempClone = (LinkedList<String>) temp.clone();
			adjacency.add(tempClone);
		}
		
		boolean renderGraph = false;
		System.out.println("Input complete. Launch graph software? [Y][N]");
		if(in.next().equalsIgnoreCase("y"))
			renderGraph = true;
		
		return new RelationAdjacency(adjacency, renderGraph);
	}

	public static Relation constructRelation() {
		
		String[] edges;   // String array containing the input edges
		int numEdges = 0;   // Size of input edges.
		String vertices = "";   // Vertices in graph
		
		System.out.println("Enter number of edges: ");
		numEdges = in.nextInt();
		edges = new String[numEdges];
		
		System.out.println("Enter " + edges + " edges, in (format: (x,y). Hit enter between entries.");
		for(int i = 0; i < numEdges; i++) {
			String temp = in.next();
			edges[i] = temp;
			
			if(vertices.indexOf(temp.charAt(1)) == -1) 
				vertices+= temp.charAt(1);
			
			if(vertices.indexOf(temp.charAt(3)) == -1) 
				vertices+= temp.charAt(3);
		}
		
		
		boolean renderGraph = false;
		System.out.println("Input complete. Launch graph software? [Y][N]");
		if(in.next().equalsIgnoreCase("y"))
			renderGraph = true;
		
		Relation r = new Relation(edges, vertices, renderGraph);
		return r;
	}
	
	public static RelationMatrix constructRelationMatrix() {
		int[][] matrix;
		int n;
		System.out.println("Enter matrix dimension n (mat = nxn): n= ");
		n = in.nextInt();
		matrix = new int[n][n];
		System.out.println("Enter each value in matrix (1 or 0), separated by spaces (format: 1 0 1 0). Hit enter between lines.");
		in.nextLine();
		for(int i = 0; i < n; i++) {
			System.out.println("Row " + (i + 1) + ": " );
			
			String rawIn = in.nextLine();
			String[] listIn = rawIn.split(" ");
			for(int j = 0; j < n; j++) {
				matrix[i][j] = Integer.parseInt(listIn[j]);
			}
		}
		boolean renderGraph = false;
		System.out.println("Input complete. Launch graph software? [Y][N]");
		if(in.next().equalsIgnoreCase("y"))
			renderGraph = true;
		
		RelationMatrix r = new RelationMatrix(matrix, renderGraph);
		return r;
	}
	
	
	
	public static void main(String[] args) {
		input();
	}
}

