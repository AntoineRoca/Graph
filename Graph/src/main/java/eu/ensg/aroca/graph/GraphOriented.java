package eu.ensg.aroca.graph;

import java.util.ArrayList;
import java.util.List;

/**
* GraphOriented class to represent oriented graph.
*
*@author Antoine Roca
*@version 1.0
*/

public class GraphOriented  extends GraphSuper {

	public GraphOriented() {
		super();
	}

	@Override
	protected List<DijkstraData> valueNext(DijkstraData data){
		Node current=data.getConsidered();
		List<Vertice> successor = current.outVertice();
		List<DijkstraData> output= new ArrayList<DijkstraData>();
		for (Vertice vertice : successor){
			Node node=new Node();
			if(vertice.getFirstEnd().equals(current)){node=vertice.getSecondEnd();}
			else{node=vertice.getFirstEnd();}
			List<Node> path =(List<Node>) ((ArrayList<Node>) data.getCurrentPath()).clone();
			path.add(node);
			output.add(new DijkstraData(node, data.getCurrentWeight()+vertice.getPoids(), path));
		}
		return output;
	}
	
	public Graph convert() {
		Graph oriented = new Graph();
		for (Node node : this.getNodes()) {
			oriented.addNode(node);
		}
		for (Vertice vertice : this.getVertices()) {
			vertice.setSens(false);
			oriented.addVertice(vertice);
		}
		return oriented;
	}
}
