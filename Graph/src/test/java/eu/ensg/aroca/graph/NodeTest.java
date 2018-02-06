package eu.ensg.aroca.graph;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
* JUnit test class for the Nodes.
*
*@author Antoine Roca
*@version 1.0
*@see Node
*/

@RunWith(MockitoJUnitRunner.class)
public class NodeTest {

	public Vertice mockedVertice(List<Node> ends, boolean sens) {
		Vertice mocked = Mockito.mock(Vertice.class);
		Mockito.when(mocked.getFirstEnd()).thenReturn(ends.get(0));
		Mockito.when(mocked.getSecondEnd()).thenReturn(ends.get(1));
		Mockito.when(mocked.getSens()).thenReturn(sens);
		return mocked;
	}
	
	@Test
	/**
	*Method to test the clone function.
	*/
	public void testClone() {
		Node<String> node=new Node<String>("chaine");
		assertTrue(node.equals(node.clone()));
		assertFalse(node==node.clone());
	}
	
	@Test
	/**
	*Method to test the equals function.
	*/
	public void testEquals() {
		Node<String> node=new Node<String>("chaine");
		assertTrue(node.equals(node));
		Node<String> node_t=new Node<String>("chaine");
		assertTrue(node.equals(node_t));
		assertFalse(node.equals("chaine"));
		Node<String> node_f_s= new Node<String>("autrechaine");
		assertFalse(node.equals(node_f_s));
		List<Node> ends= new ArrayList<Node>();
		ends.add(null);
		ends.add(null);
		Node <String> node_f_a=(Node<String>) node_t.clone();
		node_f_a.addVertice(mockedVertice(ends,true));
		assertFalse(node.equals(node_f_a));
		Node<Integer> node_f_t = new Node<Integer>(new Integer(1));
		assertFalse(node.equals(node_f_t));
	}
	
	@Test
	/**
	*Method to test the inVertice function.
	*/
	public void testInVertice() {
		// A -> B
		Node <String> a=new Node<String>("a");
		Node <String> b=new Node<String>("b");
		List<Node> ab= new ArrayList<Node>();
		ab.add(a);
		ab.add(b);
		Vertice mockedVertice=mockedVertice(ab,true);
		b.addVertice(mockedVertice);
		a.addVertice(mockedVertice);
		assertTrue(b.inVertice().contains(mockedVertice));
		List<Node> ba= new ArrayList<Node>();
		ba.add(b);
		ba.add(a);
		Vertice mockedVertice_f=mockedVertice(ba,true);
		a.addVertice(mockedVertice_f);
		b.addVertice(mockedVertice_f);
		assertFalse(b.inVertice().contains(mockedVertice_f));
		Vertice mockedVertice_b=mockedVertice(ba,false);
		a.addVertice(mockedVertice_b);
		b.addVertice(mockedVertice_b);
		assertTrue(b.inVertice().contains(mockedVertice_b));
	}
	
	@Test
	/**
	*Method to test the outVertice function.
	*/
	public void testOutVertice() {
		Node <String> a=new Node<String>("a");
		Node <String> b=new Node<String>("b");
		List<Node> ab= new ArrayList<Node>();
		ab.add(a);
		ab.add(b);
		Vertice mockedVertice=mockedVertice(ab,true);
		a.addVertice(mockedVertice);
		assertTrue(a.outVertice().contains(mockedVertice));
		List<Node> ba= new ArrayList<Node>();
		ba.add(b);
		ba.add(a);
		Vertice mockedVertice_f=mockedVertice(ba,true);
		a.addVertice(mockedVertice_f);
		assertFalse(a.outVertice().contains(mockedVertice_f));
		Vertice mockedVertice_b=mockedVertice(ba,false);
		a.addVertice(mockedVertice_b);
		assertTrue(a.outVertice().contains(mockedVertice_b));
	}
	
	@Test
	/**
	*Method to test the successeur_1 function.
	*/
	public void testSuccessor_1() {
		Node <String> a=new Node<String>("a");
		Node <String> b=new Node<String>("b");
		Node <String> c=new Node<String>("c");
		List<Node> ab= new ArrayList<Node>();
		ab.add(a);
		ab.add(b);
		Vertice mockedVertice=mockedVertice(ab,true);
		a.addVertice(mockedVertice);
		assertTrue(a.successor_1().contains(b));
		List<Node> bc= new ArrayList<Node>();
		bc.add(b);
		bc.add(c);
		Vertice mockedVertice_f=mockedVertice(bc,true);
		b.addVertice(mockedVertice_f);
		assertFalse(a.successor_1().contains(c));
		List<Node> ca= new ArrayList<Node>();
		ca.add(c);
		ca.add(a);
		Vertice mockedVertice_ca=mockedVertice(ca,true);
		a.addVertice(mockedVertice_ca);
		assertFalse(a.successor_1().contains(c));
		Vertice mockedVertice_b=mockedVertice(ca,false);
		a.addVertice(mockedVertice_b);
		assertTrue(a.successor_1().contains(c));
	}
	
	@Test
	/**
	*Method to test the predecesseur_1 function.
	*/
	public void testPredecessor_1() {
		Node <String> a=new Node<String>("a");
		Node <String> b=new Node<String>("b");
		Node <String> c=new Node<String>("c");
		List<Node> ab= new ArrayList<Node>();
		ab.add(a);
		ab.add(b);
		Vertice mockedVertice=mockedVertice(ab,true);
		b.addVertice(mockedVertice);
		assertTrue(b.predecessor_1().contains(a));
		List<Node> ca= new ArrayList<Node>();
		ca.add(c);
		ca.add(a);
		Vertice mockedVertice_f=mockedVertice(ca,false);
		a.addVertice(mockedVertice_f);
		assertFalse(b.predecessor_1().contains(c));
		List<Node> bc= new ArrayList<Node>();
		bc.add(b);
		bc.add(c);
		Vertice mockedVertice_bc=mockedVertice(bc,true);
		b.addVertice(mockedVertice_bc);
		assertFalse(b.predecessor_1().contains(c));
		Vertice mockedVertice_bc_t=mockedVertice(bc,false);
		b.addVertice(mockedVertice_bc_t);
		assertTrue(b.predecessor_1().contains(c));
	}
	
	@Test
	/**
	*Method to test the successeurs function.
	*/ 
	public void testSuccessors() {
		//Graph topo:
		// A -> B -> C -> D
		//      |    |
		//      v    v
		//      E -> F
		//We take B as current point.
		Node <String> a=new Node<String>("a");
		Node <String> b=new Node<String>("b");
		Node <String> c=new Node<String>("c");
		Node <String> d=new Node<String>("d");
		Node <String> e=new Node<String>("e");
		Node <String> f=new Node<String>("f");
		List<Node> ab_p= new ArrayList<Node>();
		ab_p.add(a);
		ab_p.add(b);
		Vertice ab=mockedVertice(ab_p,true);
		List<Node> bc_p= new ArrayList<Node>();
		bc_p.add(b);
		bc_p.add(c);
		Vertice bc=mockedVertice(bc_p,true);
		List<Node> be_p= new ArrayList<Node>();
		be_p.add(b);
		be_p.add(e);
		Vertice be=mockedVertice(be_p,true);
		List<Node> cd_p= new ArrayList<Node>();
		cd_p.add(c);
		cd_p.add(d);
		Vertice cd=mockedVertice(cd_p,true);
		List<Node> cf_p= new ArrayList<Node>();
		cf_p.add(c);
		cf_p.add(f);
		Vertice cf=mockedVertice(cf_p,true);
		List<Node> ef_p= new ArrayList<Node>();
		ef_p.add(e);
		ef_p.add(f);
		Vertice ef=mockedVertice(ef_p,true);
		a.addVertice(ab);
		b.addVertice(bc);
		b.addVertice(ab);
		b.addVertice(be);
		c.addVertice(bc);
		c.addVertice(cf);
		c.addVertice(cd);
		d.addVertice(cd);
		e.addVertice(be);
		e.addVertice(ef);
		f.addVertice(cf);
		f.addVertice(ef);
		//Getting calculated successors
		List<Node> success = b.successors();
		assertTrue(success.size()==4);
		assertTrue(success.contains(c));
		assertTrue(success.contains(d));
		assertTrue(success.contains(e));
		assertTrue(success.contains(f));
	}

	@Test
	/**
	*Method to test the predecesseurs function.
	*/
	public void testPredecessors() {
		//Graph topo:
		// A -> B -> C -> D
		//      ^    ^
		//      |    |
		//      E -> F
		//We take C as current point.
		Node <String> a=new Node<String>("a");
		Node <String> b=new Node<String>("b");
		Node <String> c=new Node<String>("c");
		Node <String> d=new Node<String>("d");
		Node <String> e=new Node<String>("e");
		Node <String> f=new Node<String>("f");
		List<Node> ab_p= new ArrayList<Node>();
		ab_p.add(a);
		ab_p.add(b);
		Vertice ab=mockedVertice(ab_p,true);
		List<Node> bc_p= new ArrayList<Node>();
		bc_p.add(b);
		bc_p.add(c);
		Vertice bc=mockedVertice(bc_p,true);
		List<Node> eb_p= new ArrayList<Node>();
		eb_p.add(e);
		eb_p.add(b);
		Vertice eb=mockedVertice(eb_p,true);
		List<Node> cd_p= new ArrayList<Node>();
		cd_p.add(c);
		cd_p.add(d);
		Vertice cd=mockedVertice(cd_p,true);
		List<Node> fc_p= new ArrayList<Node>();
		fc_p.add(f);
		fc_p.add(c);
		Vertice fc=mockedVertice(fc_p,true);
		List<Node> ef_p= new ArrayList<Node>();
		ef_p.add(e);
		ef_p.add(f);
		Vertice ef=mockedVertice(ef_p,true);
		a.addVertice(ab);
		b.addVertice(bc);
		b.addVertice(ab);
		b.addVertice(eb);
		c.addVertice(bc);
		c.addVertice(fc);
		c.addVertice(cd);
		d.addVertice(cd);
		e.addVertice(eb);
		e.addVertice(ef);
		f.addVertice(fc);
		f.addVertice(ef);
		//Getting calculated successors
		List<Node> prece = c.predecessors();
		assertTrue(prece.size()==4);	
		assertTrue(prece.contains(a));
		assertTrue(prece.contains(b));
		assertTrue(prece.contains(e));
		assertTrue(prece.contains(f));
	}

	@Test
	/**
	*Method to test the neighbour function.
	*/
	public void testNeighbours() {
		//Graph topo:
		// A -> B -> C -> D
		//      |    |
		//      v    v
		//      E -> F
		//We take B as current point.
		Node <String> a=new Node<String>("a");
		Node <String> b=new Node<String>("b");
		Node <String> c=new Node<String>("c");
		Node <String> d=new Node<String>("d");
		Node <String> e=new Node<String>("e");
		Node <String> f=new Node<String>("f");
		List<Node> ab_p= new ArrayList<Node>();
		ab_p.add(a);
		ab_p.add(b);
		Vertice ab=mockedVertice(ab_p,true);
		List<Node> bc_p= new ArrayList<Node>();
		bc_p.add(b);
		bc_p.add(c);
		Vertice bc=mockedVertice(bc_p,true);
		List<Node> be_p= new ArrayList<Node>();
		be_p.add(b);
		be_p.add(e);
		Vertice be=mockedVertice(be_p,true);
		List<Node> cd_p= new ArrayList<Node>();
		cd_p.add(c);
		cd_p.add(d);
		Vertice cd=mockedVertice(cd_p,true);
		List<Node> cf_p= new ArrayList<Node>();
		cf_p.add(c);
		cf_p.add(f);
		Vertice cf=mockedVertice(cf_p,true);
		List<Node> ef_p= new ArrayList<Node>();
		ef_p.add(e);
		ef_p.add(f);
		Vertice ef=mockedVertice(ef_p,true);
		a.addVertice(ab);
		b.addVertice(bc);
		b.addVertice(ab);
		b.addVertice(be);
		c.addVertice(bc);
		c.addVertice(cf);
		c.addVertice(cd);
		d.addVertice(cd);
		e.addVertice(be);
		e.addVertice(ef);
		f.addVertice(cf);
		f.addVertice(ef);
		List<Node> a_neighbour = a.neighbours();
		List<Node> b_neighbour = b.neighbours();
		List<Node> c_neighbour = c.neighbours();
		List<Node> d_neighbour = d.neighbours();
		List<Node> e_neighbour = e.neighbours();
		List<Node> f_neighbour = f.neighbours();
		assertTrue(a_neighbour.size()==1);
		assertTrue(b_neighbour.size()==3);
		assertTrue(c_neighbour.size()==3);
		assertTrue(d_neighbour.size()==1);
		assertTrue(e_neighbour.size()==2);
		assertTrue(f_neighbour.size()==2);
		assertTrue(a_neighbour.contains(b));
		assertTrue(b_neighbour.contains(a));
		assertTrue(b_neighbour.contains(c));
		assertTrue(b_neighbour.contains(e));
		assertTrue(c_neighbour.contains(b));
		assertTrue(c_neighbour.contains(f));
		assertTrue(c_neighbour.contains(d));
		assertTrue(d_neighbour.contains(c));
		assertTrue(e_neighbour.contains(b));
		assertTrue(e_neighbour.contains(f));
		assertTrue(f_neighbour.contains(c));
		assertTrue(f_neighbour.contains(e));
		
	}
}
