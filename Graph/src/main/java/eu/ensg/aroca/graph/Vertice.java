package eu.ensg.aroca.graph;

import java.util.ArrayList;
import java.util.List;

/**
* Vertice class to represent vertices of the graph.
*
*@author Antoine Roca
*@version 1.0
*/

public class Vertice<T> {

	private T t;
	private long poids;
	private List<Node> ends;
	private boolean sens;

	public T getT() {return t;}
	public long getPoids() {return poids;}
	public void setPoids(int poids) {this.poids = poids;}
	public List<Node> getEnds() {return ends;}
	//sens is true if from the first Node to the second and false if bidirectionnal (no backswards)
	public boolean getSens() {return this.sens;}
	public void setSens(boolean sens) {this.sens = sens;}
	//Setters for the Nodes of the Vertice. Should be used when the constructor without ends as param is used
	public void setFirstEnd(Node start) {this.ends.set(0,start);}
	public void setSecondEnd(Node end) {this.ends.set(1,end);}
	public Node getFirstEnd() {return ends.get(0);}
	public Node getSecondEnd() {return ends.get(1);}
	
	/**
	*Constructor without the ending points of the vertex.
	*The ending points are set to null.
	*
	*@param t The generic data
	*@param poids The vertex weight
	*@param sens Has this vertex a direction (true: first point to second, false: bidirectionnal)
	*/
	public Vertice (T t, long poids, boolean sens) {
		this.t=t;
		this.poids=poids;
		this.sens=sens;
		this.ends= new ArrayList<Node>();
		this.ends.add(null);
		this.ends.add(null);
	}

	/**
	*Constructor with the ending points of the vertex.
	*The ending points are set to null.
	*
	*@param t The generic data
	*@param poids The vertex weight
	*@param ends The list of two nodes composing the ends of the vertex
	*@param sens Has this vertex a direction (true: first point to second, false: bidirectionnal)
	*/
	public Vertice(T t, long poids, List<Node> ends, boolean sens) {
		super();
		this.t = t;
		this.poids = poids;
		this.ends = ends;
		this.sens = sens;
	}

	@Override
	protected Object clone(){
		Vertice<T> newbie = new Vertice<T>(this.t,this.poids,this.ends,this.sens);
		return newbie;
	}

	@Override
	public boolean equals(Object obj) {
		if (this==obj) {return true;} //case the same pointer.
		if (obj.getClass()!=this.getClass()) {return false;} //case types different.
		Vertice<T> vo = (Vertice<T>) obj;
		Boolean same_ends=this.getEnds().equals(vo.getEnds());
		return same_ends &&vo.getT().equals(this.t) && vo.getPoids()==this.poids && (vo.getSens()==this.sens);
	}

	

}
