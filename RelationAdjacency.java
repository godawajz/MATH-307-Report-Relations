import java.util.Iterator;
import java.util.LinkedList;
public class RelationAdjacency {
	private LinkedList<LinkedList<String>> adjacency;
	
	public RelationAdjacency(LinkedList<LinkedList<String>> adjacency, boolean renderGraph) {
		this.adjacency = adjacency;
		printList();
		
		if(renderGraph)
			renderGraph();
	}
	
	private void printList() {
		Iterator<LinkedList<String>> i = adjacency.iterator();
		while(i.hasNext()) {
			LinkedList<String> temp = (LinkedList<String>) i.next();
			Iterator<String> j = temp.iterator();
			System.out.print(j.next() + " | ");
			
			while(j.hasNext()) {
				System.out.print(j.next() + " ");
			}
			System.out.println();
		}
	}
	
	private void renderGraph() {
		@SuppressWarnings("unused")
		DisplayRelation d = new DisplayRelation(this.adjacency);
	}
}
