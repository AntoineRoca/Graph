package eu.ensg.aroca.graph;

import java.util.ArrayList;
import java.util.List;

/**
* Node class to represent a graph's nodes.
*
*@author Antoine Roca
*@version 1.0
*/

public class Node<T> {

	private T t;
	private List<Vertice> arcs;
	
	public T getT() {return t;}
	public void addVertice(Vertice vertice) {this.arcs.add(vertice);}
	public void delVertice(Vertice vertice) {this.arcs.remove(vertice);}
	public void delVertice(int index) {this.arcs.remove(index);}
	public List<Vertice> getArcs() {return this.arcs;}
	
	/**
	*Constructor without the ending points of the vertex.
	*The ending points are set to null.
	*
	*@deprecated USED ONLY FOR TEST!
	*/
	public Node() {
		this.t=null;
		this.arcs=new ArrayList<Vertice>();
	}

	/**
	*Constructor without Vertex entering of leaving the vertex.
	*
	*@param t The generic data.
	*/
	public Node(T t) {
		this.t=t;
		this.arcs=new ArrayList<Vertice>();
	}

	/**
	*Constructor with Vertex.
	*
	*@param t The generic data.
	*@param arcs List of all the Vertices leaving or entering the node.
	*/
	public Node(T t, List<Vertice> arcs) {
		this.t=t;
		this.arcs=arcs;
	}
	
	@Override
	protected Object clone(){
		Node<T> newbie = new Node<T>(this.t,this.arcs);
		return newbie;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this==obj) {return true;} //case the same pointer.
		if (obj.getClass()!=this.getClass()) {return false;} //case types different.
		Node<T> no = (Node<T>) obj;
		return no.getT().equals(this.t) && no.getArcs().equals(this.arcs) ;
	}

	/**
	*inVertice function that gives the vertices going to the node.
	*Includes bidirectionnal vertices.
	*
	*@return the list of entering vertices
	*/
	public List<Vertice> inVertice() {
		ArrayList<Vertice> in = new ArrayList<Vertice>();
		for ( Vertice vertice : arcs) {
			if (this.equals(vertice.getSecondEnd())){in.add(vertice);}
			else {
			if (!vertice.getSens() && this.equals(vertice.getFirstEnd())){in.add(vertice);}
			}
		}
		return in;
	}

	/**
	*outVertice function that gives the vertices leaving the node.
	*Includes bidirectionnal vertices.
	*
	*@return the list of leaving vertices.
	*/
	public List<Vertice> outVertice() {
		ArrayList<Vertice> out = new ArrayList<Vertice>();
		for ( Vertice vertice : arcs) {
			if (this.equals(vertice.getFirstEnd())){out.add(vertice);}
			else {
			if (!vertice.getSens() && this.equals(vertice.getSecondEnd())){out.add(vertice);}
			}
		}
		return out;
	}

	/**
	*Returns the first rank successors of the point.
	*(Includes bidirectionnal vertices).
	*
	*@return the list of first-rank successor.
	*/
	public List<Node> successor_1() {
		List<Vertice> out = this.outVertice();
		List<Node> succes = new ArrayList<Node>();
		for (Vertice vertice : out) {
			if (!vertice.getSens() && this.equals(vertice.getSecondEnd())){ succes.add(vertice.getFirstEnd());}
			else{
				if(!succes.contains(vertice.getSecondEnd())) {
					succes.add(vertice.getSecondEnd());
					}
			}
		}
		return succes;
	}

	/**
	*Returns the first rank predecessor of the point.
	*(Includes bidirectionnal vertices).
	*
	*@return the list of first-rank predecessor.
	*/
	public List<Node> predecessor_1() {
		List<Vertice> out = this.inVertice();
		List<Node> pred = new ArrayList<Node>();
		for (Vertice vertice : out) {
			if (!vertice.getSens() && this.equals(vertice.getFirstEnd())){pred.add(vertice.getSecondEnd());}
			else{
				if(!pred.contains(vertice.getFirstEnd())) {
					pred.add(vertice.getFirstEnd());
					}
				}
		}
		return pred;
	}
	
	/**
	*Returns ALL successors of the point (no rank limit).
	*(Includes bidirectionnal vertices).
	*
	*@return the list of successors.
	*/
	public List<Node> successors() {
		List<Node> rank_1=this.successor_1();
		if(!rank_1.isEmpty()) {
			List<Node> result =(List<Node>) ((ArrayList<Node>) rank_1).clone();
			while(!rank_1.isEmpty()) {
				Node node = rank_1.get(0);
				List<Node> succes = node.successor_1();
				for(Node succe : succes) {
					if(!rank_1.contains(succe)) {
						result.add(succe);
						rank_1.add(succe);
					}
				}
				rank_1.remove(node);
			}
			return result;
		}
		return rank_1;
	}
	
	/**
	*Returns ALL predessors of the point (no rank limit).
	*(Includes bidirectionnal vertices).
	*
	*@return the list of predessors.
	*/
	public List<Node> predecessors() {
		List<Node> rank_1=this.predecessor_1();
		if(!rank_1.isEmpty()) {
			List<Node> result =(List<Node>) ((ArrayList<Node>) rank_1).clone();
			while(!rank_1.isEmpty()) {
				Node node = rank_1.get(0);
				List<Node> succes = node.predecessor_1();
				for(Node succe : succes) {
					if(!rank_1.contains(succe)) {
						result.add(succe);
						rank_1.add(succe);
					}
				}
				rank_1.remove(node);
			}
			return result;
		}
		return rank_1;
	}
	
	/**
	*Returns the neighbours of a point.
	*
	*@return the list of neighbours.
	*/
	public List<Node> neighbours() {
		List<Node> init= new ArrayList<Node>();
		for (Vertice vertice : arcs){
			if (vertice.getFirstEnd().equals(this)){init.add(vertice.getSecondEnd());}
			if (vertice.getSecondEnd().equals(this)){init.add(vertice.getFirstEnd());}
		}
		return init;
	}
		
}
