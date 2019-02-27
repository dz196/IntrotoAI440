package recitation;

public class ProblemSet12 {

	class Neighbor{
		public int vertex;
		public Neighbor next;
	}
	class Vertex{
		String name;
		Neighbor neighbors;
	}
	class Graph{
		Vertex[] vertices;
		// returns an array of indegrees of the vertices i.e return [i] is the 
		// number of edges that are directed IN TO vertex i
		public int[] indegrees(){
			//FILL IN THIS METHOD
			int [] indegrees = new int[vertices.length];
			for (int i = 0; i < indegrees.length; i ++){
				for (Neighbor tmp = vertices[i].neighbors; tmp != null; tmp= tmp.next){
					int vertex = tmp.vertex;
					indegrees[vertex] += 1;
				}
			}
			return indegrees;
		}
	}
}
