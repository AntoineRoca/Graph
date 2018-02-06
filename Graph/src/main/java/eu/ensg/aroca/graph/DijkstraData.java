package eu.ensg.aroca.graph;

import java.util.List;

/**
* DijkstraData class to represent data for Dijkstra's algorithm.
*
*@author Antoine Roca
*@version 1.0
*@see NodeTest for tests
*/
public class DijkstraData {
	
	private Node considered;
	private long currentWeight;
	private List<Node> currentPath;
	
	public DijkstraData(Node considered, long currentWeight, List<Node> currentPath) {
		this.considered = considered;
		this.currentWeight = currentWeight;
		this.currentPath = currentPath;
	}

	public long getCurrentWeight() {return currentWeight;}
	public void setCurrentWeight(long currentWeight) {this.currentWeight = currentWeight;}
	public List<Node> getCurrentPath() {return currentPath;}
	public void setCurrentPath(List<Node> currentPath) {this.currentPath = currentPath;}
	public Node getConsidered() {return considered;}
	
	@Override
	public boolean equals(Object obj) {
		if (this==obj) {return true;} //case the same pointer.
		if (obj.getClass()!=this.getClass()) {return false;} //case types different.
		DijkstraData no = (DijkstraData) obj;
		return this.considered.equals(no.getConsidered());
	}
	
	
}
