/*
 * Relation.class: A literal implementation of a relation set. An object contains a list of edges, 
 * 				   and a list of vertices. Lists are passed to DisplayRelation class to be processed.
 * 
 * @Author Joanna Godawa
 * @Version 12/27/2023
 */
public class Relation {
	private String[] edges;
	private String vertices;
	private int[][] mat;
	
	public Relation(String[] edges, String verticies, boolean renderGraph) {
		this.edges = edges;
		this.vertices = verticies;
		this.mat = generateMatrix();
		if(renderGraph)
			renderGraph();
	}
	
	/*
	 * generateMatrix: a method used in the Relation constructor, meant to produce a 2D array representation 
	 *                 of a relation. Rows will contain a 1 if element n points to element p, and 0 if not. 
	 *                 Method relies on the knowledge that ASCII lower case a is equal to int val 97.
	 *                 Used to construct an object of RelationMatrix type.
	 */
	private int[][] generateMatrix() {
		int[][] mat = new int[vertices.length()][vertices.length()];
		
		for(int i = 0; i < mat.length; i++) {	//  initialize matrix, all values are zero. 
			for(int j = 0; j < mat[i].length; j++) {
				mat[i][j] = 0;
			}
		}
		
		for(String edge: edges) {
			int n = edge.charAt(1) - 97;
			int p = edge.charAt(3) - 97;
			mat[n][p] = 1;
		}
		
		return mat;
	}
	
	private void renderGraph() {
		@SuppressWarnings("unused")
		DisplayRelation d = new DisplayRelation(vertices, edges);
	}
	
	public void printMatrix() {
		System.out.print("  ");
		for(int i = 0; i < mat.length; i++) {
			System.out.print((char)(i + 97) + " ");
		}
		System.out.println();
		
		for(int i = 0; i < mat.length; i++) {
			System.out.print((char)(i + 97) + " ");
			for(int j = 0; j < mat[i].length; j++) {
				System.out.print(mat[i][j] + " ");
			}
			System.out.println();
		}
	}
}
