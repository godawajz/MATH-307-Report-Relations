import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.util.mxCellRenderer;

/*
 * DisplayRelation: Uses JGraphT and awt + swing libraries to create a program which graphically
 * 						  displays the Relation, RelationMatrix, and RelationList classes. 
 * 
 * @author Joanna Godawa
 * @version 2/27/2023
 */
public class DisplayRelation extends javax.swing.JFrame {
	
	private static final long serialVersionUID = 1L;
	private DefaultDirectedGraph<String, DefaultEdge> d;
	private JFrame j;
	private ImageIcon defaultI;
	
/*
 * Constructor for Relation class
 */
	public DisplayRelation(String vertex, String[] edges) {
		initGraph(vertex, edges);
		initFrame();
		this.j.setVisible(true);
	}
	
/*
 * Constructor for Matrix class
 */
	public DisplayRelation(int[][] matrix) {
		initGraph(matrix);
		initFrame();
		this.j.setVisible(true);
	}
	
/*
 * Constructor for Adjacency class
 */
	public DisplayRelation(LinkedList<LinkedList<String>> adjacency) {
		initGraph(adjacency);
		initFrame();
		this.j.setVisible(true);
	}
	
/*
 * initFrame: Method called by each unique constructor type in order to initialize a viewing panel
 * 			  			with an image of the JGraphT representation of a relation stored both as a 
 * 						file in the working directory and a JLabel for visual output.
 */
	@SuppressWarnings("unused")
	private void initFrame() {
		JFrame j = new JFrame();
		Graphics g = j.getGraphics();
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setSize(800, 800);
		j.setBackground(Color.WHITE);
		
		Image img;
		try {
			img = ImageIO.read(new File("graph.png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		
		JLabel graph = new JLabel(this.defaultI);
		j.add(graph);
		
		
		this.j = j;
	}
	
/*
 * WriteBufferedImage: generates an image with JGraphXAdapter given a directed graph.
 * 
 * @return String: the name of the file's path within the working directory.
 */
	public String WriteBufferedImage() throws IOException {
		
	    JGraphXAdapter<String, DefaultEdge> graphAdapter = 
	      new JGraphXAdapter<String, DefaultEdge>(this.d);
	    mxIGraphLayout layout = new mxCircleLayout(graphAdapter);
	    layout.execute(graphAdapter.getDefaultParent());
	    
	    BufferedImage image = 
	      mxCellRenderer.createBufferedImage(graphAdapter, null, 2, Color.WHITE, true, null);
	    File imgFile = new File("graph.png");
	    ImageIO.write(image, "PNG", imgFile);
	    return("graph.png");
	}
	
/*
 * initGraph: The Relation class's version of the initGraph method. Directly translates the list of 
 * 						vertices and of edges into a DefaultDirectedGraph object.
 * 
 * @param String vertex: a string storing the list of vertices in the graph, stored in format ABCDEF
 * 		  String[] edges: The edges in the relation stored in format (A,B).
 * 
 * @return DefaultDirectedGraph<String, DefaultEdge>
 */
	public DefaultDirectedGraph<String, DefaultEdge> initGraph(String vertex, String[] edges){
		Graph<String, DefaultEdge> directedGraph =
                new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
		
		String[] vertices = vertex.split("");
		for(String v: vertices) {
			directedGraph.addVertex(v);
		}
		for(String edge: edges) {
			String n = edge.charAt(1) + "";
			String p = edge.charAt(3) + "";
			directedGraph.addEdge(n, p);
		}
            
        this.d = (DefaultDirectedGraph<String, DefaultEdge>) directedGraph;
            
        try {
        	String fileName = this.WriteBufferedImage();
	        this.defaultI = new ImageIcon(fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
            
        return (DefaultDirectedGraph<String, DefaultEdge>) directedGraph;
	}
	
/*
 * initGraph: The RelationMatrix class's version of the initGraph method. Translates the given matrix 
 * 						into a list of vertices (ASCII a,b,c,...) given position of row, and column.
 * 						Edges calculated on the value (1,0) of two edge's intersection in a table.
 * 
 * @param int[][] matrix: holds values 1 or 0 based on whether two vertices point to one another.
 * 
 * @return DefaultDirectedGraph<String, DefaultEdge>
 */
	public DefaultDirectedGraph<String, DefaultEdge> initGraph(int[][] matrix){
		Graph<String, DefaultEdge> directedGraph =
                new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
		
		for(int i = 0; i < matrix.length; i++) {
			char vertex = (char) (97 + i);
			directedGraph.addVertex(vertex + "");
		}
		
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++) {
				if(matrix[i][j] == 1) {
					String n = (char) (97 + i) + "";
					String p = (char) (97 + j) + "";
					directedGraph.addEdge(n, p);
				}}}
		  
        this.d = (DefaultDirectedGraph<String, DefaultEdge>) directedGraph;
            
        try {
        	String fileName = this.WriteBufferedImage();
	        this.defaultI = new ImageIcon(fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
            
        return (DefaultDirectedGraph<String, DefaultEdge>) directedGraph;
	}
	
/*
 * initGraph: The RelationAdjacency class's version of the initGraph method. Translates the 2D LinkedList 
 * 						into a list of vertices (the values of each unique head within the list), and a list
 * 						of edges (the head value paired with each subsequent vertex in a respective list)
 * 
 * @param LinkedList<LinkedList<String>> adjacency: the adjacency list representation of the matrix
 * 
 * @return DefaultDirectedGraph<String, DefaultEdge>
 */
	public DefaultDirectedGraph<String, DefaultEdge> initGraph(LinkedList<LinkedList<String>> adjacency){
		Graph<String, DefaultEdge> directedGraph =
                new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
		
		Iterator<LinkedList<String>> i = adjacency.iterator();
		
		while(i.hasNext()) {
			LinkedList<String> temp = i.next();
			Iterator<String> j = temp.iterator();
			String vertex = j.next();
			directedGraph.addVertex(vertex);
			while(j.hasNext()) {
				String toPoint = j.next();
				try {
					directedGraph.addEdge(vertex, toPoint);
					}
				catch(Exception e) {
					directedGraph.addVertex(toPoint);
					directedGraph.addEdge(vertex, toPoint);
				}
			}}
		  
        this.d = (DefaultDirectedGraph<String, DefaultEdge>) directedGraph;
            
        try {
        	String fileName = this.WriteBufferedImage();
	        this.defaultI = new ImageIcon(fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
            
        return (DefaultDirectedGraph<String, DefaultEdge>) directedGraph;
	}
	
}


