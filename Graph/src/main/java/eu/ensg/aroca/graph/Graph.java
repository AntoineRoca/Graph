package eu.ensg.aroca.graph;

import java.util.ArrayList;
import java.util.List;

public class Graph extends GraphSuper {

	public Graph() {
		super();
	}

	@Override
	protected List<DijkstraData> valueNext(DijkstraData data){
		Node current=data.getConsidered();
		List<Vertice> neighbour = current.getArcs();
		List<DijkstraData> output= new ArrayList<DijkstraData>();
		for (Vertice vertice : neighbour){
			Node node=new Node();
			if(vertice.getFirstEnd().equals(current)){node=vertice.getSecondEnd();}
			else{node=vertice.getFirstEnd();}
			List<Node> path =(List<Node>) ((ArrayList<Node>) data.getCurrentPath()).clone();
			path.add(node);
			output.add(new DijkstraData(node, data.getCurrentWeight()+vertice.getPoids(), path));
		}
		return output;
	}
	
	public GraphOriented convert() {
		GraphOriented oriented = new GraphOriented();
		for (Node node : this.getNodes()) {
			oriented.addNode(node);
		}
		for (Vertice vertice : this.getVertices()) {
			vertice.setSens(true);
			oriented.addVertice(vertice);
		}
		return oriented;
	}
}
