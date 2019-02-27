package apps;

import structures.*;
import structures.Vertex.Neighbor;
import java.util.ArrayList;
import apps.PartialTree.Arc;

public class MST {
	
	/**
	 * Initializes the algorithm by building single-vertex partial trees
	 * 
	 * @param graph Graph for which the MST is to be found
	 * @return The initial partial tree list
	 */
	public static PartialTreeList initialize(Graph graph) {
	
		/* COMPLETE THIS METHOD */
		PartialTreeList list= new PartialTreeList(); 
		for (int i = 0; i < graph.vertices.length; i ++){
			PartialTree tree = new PartialTree(graph.vertices[i]);
			graph.vertices[i].parent = graph.vertices[i];
			Vertex v1 = graph.vertices[i];
			MinHeap<Arc> minheap = new MinHeap<Arc>(); 
			for ( Neighbor tmp = graph.vertices[i].neighbors; tmp != null; tmp = tmp.next){
				Vertex v2 = tmp.vertex;
				int weight = tmp.weight;
				Arc arc = new Arc(v1, v2, weight);
				minheap.insert(arc);
				//tree.getArcs().insert(arc);
			}
			tree.getArcs().merge(minheap);
			System.out.println(tree.toString());
			list.append(tree);
		}
		return list;
	}

	/**
	 * Executes the algorithm on a graph, starting with the initial partial tree list
	 * 
	 * @param ptlist Initial partial tree list
	 * @return Array list of all arcs that are in the MST - sequence of arcs is irrelevant
	 */
	public static ArrayList<PartialTree.Arc> execute(PartialTreeList ptlist) {
		
		/* COMPLETE THIS METHOD */
		ArrayList<PartialTree.Arc> list = new ArrayList<PartialTree.Arc>();
		/*Arc arc = tree.getArcs().deleteMin();
		Vertex v1 = arc.v1;
		Vertex v2 = arc.v2;
		PartialTree remove = ptlist.removeTreeContaining(v2);
		System.out.println(arc.toString() + " is a component of the MST");
		tree.merge(remove);
		ptlist.append(tree);*/
		while (ptlist.size() > 1){
			System.out.println("SIZE: " + ptlist.size());
			PartialTree tree = ptlist.remove();
			System.out.println("TREE: " + tree.toString());
			//System.out.println("REACHED HERE");
			while (!tree.getArcs().isEmpty()){
				Arc arc = tree.getArcs().deleteMin();
				System.out.println("ARC: " + arc.toString());
				//Vertex v1 = arc.v1;
				Vertex v2 = arc.v2; 
				PartialTree remove = null;
				remove = ptlist.removeTreeContaining(v2);
				if (remove == null){ System.out.println("Tree is null");}
				if (remove != null){
					System.out.println("Remove: " + remove.toString());
					list.add(arc);
					System.out.println("ARCadded: " + arc.toString());
					//tree.merge(remove);
					remove.merge(tree);
					ptlist.append(remove);
					System.out.println("TREE2nd: " + tree.toString());
					break;
				}
				//System.out.println("REACHED HERE2");
			}
		}
		return list;
	}
	/*private static ArrayList<PartialTree.Arc> RecRemove(PartialTree tree, PartialTreeList ptlist, ArrayList<PartialTree.Arc> list )
	{
		while (ptlist.size() > 1){
			PartialTree tree = ptlist.remove(); 
			System.out.println("TREE: " + tree.toString());
			if (tree.getArcs().isEmpty()){
				return list;
			}
			Arc arc = tree.getArcs().deleteMin(); 
			System.out.println("ARC: " + arc.toString());
			Vertex v1 = arc.v1;
			Vertex v2 = arc.v2; 
			PartialTree remove = null;
			remove = ptlist.removeTreeContaining(v2);
			if (remove == null){
				return RecRemove(tree, ptlist, list);
			}
			System.out.println("PartialTree: " + remove.toString());
			System.out.println(arc.toString() + " is a component of the MST");
			list.add(arc);
			tree.merge(remove);
			ptlist.append(tree);
			System.out.println("TREE2nd: " + tree.toString());
			return list;
		}
	}*/
		
}
