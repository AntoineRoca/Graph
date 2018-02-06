package eu.ensg.aroca.graph;

import java.util.ArrayList;
import java.util.List;

/**
* GraphSuper class to represent any graph (abstract class, as takes no consideration for orientation).
*
*@author Antoine Roca
*@version 1.0
*/

public abstract class GraphSuper {

	protected List<Node> nodes;
	protected List<Vertice> vertices;
	protected boolean isConnectedComponent;
	
	public List<Node> getNodes() {return nodes;}
	public List<Vertice> getVertices() {return vertices;}
	
	public void addNode(Node node) {this.nodes.add(node);}
	public void addVertice(Vertice vertice) {this.vertices.add(vertice);}
	
	public Node getNode(int i) {return this.nodes.get(i);}
	public  Vertice getVertice(int i) {return this.vertices.get(i);}
	
	public void delNode(Node node) {this.nodes.remove(node);}
	public void delNode(int i) {this.nodes.remove(i);}
	public void delVertice(Vertice vertice) {this.vertices.remove(vertice);}
	public void delVertice(int i) {this.vertices.remove(i);}
	
	public void setComponent(boolean bool) {this.isConnectedComponent=bool;}
	
	public GraphSuper() {
		this.nodes=new ArrayList<Node>();
		this.vertices=new ArrayList<Vertice>();
		this.isConnectedComponent=false;
	}
	
	/**
	*connectedComponent returns the list of the connected components of this graph.
	*return this if it is fully connected
	*
	*@return List<GraphSuper> the list of connected components of the graph.
	*/
	public List<GraphSuper> connectedComponent(){
		List<GraphSuper> connected = new ArrayList<GraphSuper>();
		if (isConnectedComponent){
			connected.add(this);
			return connected;
			}
		List<Node> temp =(List<Node>) ((ArrayList<Node>) this.nodes).clone();
		while(!temp.isEmpty()){
			Node start = temp.get(0);
			Graph temp_graph = new Graph();
			temp_graph.setComponent(true);
			temp_graph.addNode(start);
			List<Vertice> arcs= start.getArcs();
			for (Vertice vertice : arcs){
				temp_graph.addVertice(vertice);
			}
			temp.remove(start);
			List<Node> predecessors = start.predecessors();
			for (Node pred :predecessors) {
				if (!temp_graph.getNodes().contains(pred)) {
					temp_graph.addNode(pred);
					arcs= pred.getArcs();
					for (Vertice vertice : arcs){
						if(!temp_graph.getVertices().contains(vertice)) {
							temp_graph.addVertice(vertice);
						}
					}
				}
				temp.remove(pred);
			}
			List<Node> successors = start.successors();
			for (Node succs :successors) {
				if (!temp_graph.getNodes().contains(succs)) {
					temp_graph.addNode(succs);
					arcs= succs.getArcs();
					for (Vertice vertice : arcs){
						if(!temp_graph.getVertices().contains(vertice)) {
							temp_graph.addVertice(vertice);
						}
					}
				}
				temp.remove(succs);
			}
			connected.add(temp_graph);
		}
		if (connected.size()==1) {
			connected.clear();
			connected.add(this);
			this.isConnectedComponent=true;
		}
		return connected;
	}
	/**
	*valueNext calculates the next values for a given node.
	*
	*@param data the node's data taken for calculating next values
	*
	*@return boolean : is the final node reached and with the shortest path.
	*/
	protected abstract List<DijkstraData> valueNext(DijkstraData data);
	
	/**
	*toAdd component for Dijkstra's algorithm.
	*Tells of new nodes which needs to be added as not calculated yet
	*
	*@param additional The list of new nodes.
	*@param current The current list of nodes (calculated or not).
	*
	*@return aajouter the list of nodes to add
	*/
	protected List<DijkstraData> toAdd(List<DijkstraData> additional, List<DijkstraData> current) {
		List<DijkstraData> aajouter = new ArrayList<DijkstraData>();
		for (DijkstraData data : additional){
			if(!current.contains(data)){
				aajouter.add(data);
			}
		}
		return aajouter;
	}
	
	/**
	*update function that updates the nodes data once the calculation is done.
	*
	*@param additional The list of new nodes.
	*@param current The current list of nodes (calculated or not).
	*
	*@return current the list of nodes as in parameter with the modifications.
	*/
	protected List<DijkstraData> update(List<DijkstraData> additional, List<DijkstraData> current) {
		for (DijkstraData data : additional){
			if(current.contains(data)){
				int i=current.indexOf(data);
				current.get(i).setCurrentPath(data.getCurrentPath());
				current.get(i).setCurrentWeight(data.getCurrentWeight());
			}
		}
		return current;
	}
	
	/**
	*summitReachedFinal function says if the end node is reached with the best path.
	*
	*@param current The current list of nodes (calculated or not).
	*@param end The node of the end of the path
	*
	*@return boolean : is the final node reached and with the shortest path.
	*/
	protected boolean summitReachedFinal(List<DijkstraData> current, Node end) {
		DijkstraData bait=new DijkstraData(end, 0, null);
		if (current.contains(bait)) {
			long minWeight = current.get(0).getCurrentWeight();
			long destWeight = current.get(current.indexOf(bait)).getCurrentWeight();
			for (DijkstraData data : current) {
				if (data.getCurrentWeight()<minWeight) {minWeight=data.getCurrentWeight();}
				if (data.getConsidered().equals(end)) {destWeight=data.getCurrentWeight();}
				if (minWeight<destWeight) {return false;}
			}
			return minWeight>=destWeight;
		}
		else {
			return false;
		}
	}
	
	/**
	*selectNode select the next node to calculate.
	*
	*@param current The current list of nodes (not calculated).
	*
	*@return DijkstraData the data for the note to calculate.
	*/
	protected DijkstraData selectNode(List<DijkstraData> current) {
		long minWeight = current.get(0).getCurrentWeight();
		int minIndex = 0;
		for (DijkstraData data : current) {
			if (data.getCurrentWeight()<minWeight) {
				minIndex=current.indexOf(data);
				minWeight=data.getCurrentWeight();
			}
		}
		return current.get(minIndex);
	}
	
	/**
	*isReachable function calculates if the end can be found from the start for this graph.
	*
	*@param start The starting node of the desired path.
	*@param end The node of the end of the path
	*
	*@return boolean : can the final node be reached.
	*/
	protected boolean isReacheable(Node start, Node end) {
		if (this.getNodes().contains(end) && this.getNodes().contains(start)) {
			List<GraphSuper> connected = this.connectedComponent();
			for (GraphSuper graph : connected) {
				if(graph.getNodes().contains(end) && graph.getNodes().contains(start)) {
					return true;}
			}
		}
		return false;
	}
	
	/**
	*shortestPath calculates the shortest path from the start node to the end node.
	*
	*@param start The node starting the path.
	*@param end The node of the end of the path
	*
	*@return List<Node> the list of nodes composing the path.
	*/
	public List<Node> shortestPath(Node start,Node end){
		if (!this.isReacheable(start, end)) {return new ArrayList<Node>();}
		else {
			List<DijkstraData> current =new ArrayList<DijkstraData>();
			List<DijkstraData> notCalculated =new ArrayList<DijkstraData>();
			ArrayList<Node> path = new ArrayList<Node>();
			path.add(start);
			DijkstraData startData= new DijkstraData(start, 0,path);
			current.add(startData);
			notCalculated.add(startData);
			while(!this.summitReachedFinal(current, end)) {
				if(notCalculated.size()==0) {break;} 
				//Case of loose ends ex: A-B-C-D
				//                         | 
				//                         E  
				//summitReachedFinal would falsely not find the shortest path as
				// E = 3 and D = 4
				DijkstraData chosenOne= this.selectNode(notCalculated);
				notCalculated.remove(chosenOne); //ChoseOne will be calculated
				List<DijkstraData> nextStep= this.valueNext(chosenOne);
				List<DijkstraData> dataToAdd= this.toAdd(nextStep, current);
				current=this.update(nextStep, current);
				current.addAll(dataToAdd);
				notCalculated.addAll(dataToAdd);
				}
			//Getting the current path of the end once done
			return current.get(current.indexOf(new DijkstraData(end, 0, path))).getCurrentPath();
		}
	}
	
}
