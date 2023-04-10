/* RelationMatrix: A matrix-based implementation of a Relation object. Constructor takes in a 2d integer 
 * 				array as a form of edge storage for the object, as well as a boolean to allow for a 
 *     			user to decide whether or not a graph will be rendered in a separate window as a JGraphT
 *     			object. 
 * 
 * @Author Joanna Godawa
 * @Version 03/02/2023
 */
public class RelationMatrix {
	private int[][] matrix;
	public RelationMatrix(int[][] matrix, boolean renderGraph) {
		this.matrix = matrix;
		printMatrix();
		if(renderGraph)
			renderGraph();
	}
	
	public void printMatrix() {
		System.out.print("  ");
		for(int i = 0; i < matrix.length; i++) {
			System.out.print((char)(i + 97) + " ");
		}
		System.out.println();
		
		for(int i = 0; i < matrix.length; i++) {
			System.out.print((char)(i + 97) + " ");
			for(int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	private void renderGraph() {
		@SuppressWarnings("unused")
		DisplayRelation d = new DisplayRelation(this.matrix);
	}
}
