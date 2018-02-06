package eu.ensg.aroca.graph;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

public class GraphTest {

	public Vertice mockedVertice(List<Node> ends, boolean sens) {
		Vertice mocked = Mockito.mock(Vertice.class);
		Mockito.when(mocked.getFirstEnd()).thenReturn(ends.get(0));
		Mockito.when(mocked.getSecondEnd()).thenReturn(ends.get(1));
		Mockito.when(mocked.getPoids()).thenReturn(1l);
		Mockito.when(mocked.getSens()).thenReturn(sens);
		return mocked;
	}
	
	@Test
	public void testConectedComponent() {
		//Graph topo:
		// A - B - C - D
		//     |   
		//     E   F
		//We take B as current point.
		
		//Errors when using mocks due to lack of mocked functions
		Node <String> a=new Node<String>("a");
		Node <String> b=new Node<String>("b");
		Node <String> c=new Node<String>("d");
		Node <String> d=new Node<String>("e");
		Node <String> e=new Node<String>("f");
		Node <String> f=new Node<String>("g");
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
		a.addVertice(ab);
		b.addVertice(be);
		b.addVertice(ab);
		b.addVertice(bc);
		c.addVertice(bc);
		c.addVertice(cd);
		d.addVertice(cd);
		e.addVertice(be);
		
		Graph graph = new Graph();
		graph.addNode(a);
		graph.addNode(b);
		graph.addNode(c);
		graph.addNode(d);
		graph.addNode(e);
		graph.addNode(f);
		graph.addVertice(ab);
		graph.addVertice(bc);
		graph.addVertice(cd);
		graph.addVertice(be);
		
		assertTrue(graph.connectedComponent().size()==2);
	}
	
	@Test
	public void testValueNext() {
		//Graph topo:
		// A - B - C - D
		//     |   |
		//     E - F
		//We take B as current point.
		Node <String> a=Mockito.mock(Node.class);
		Node <String> b=Mockito.mock(Node.class);
		Node <String> c=Mockito.mock(Node.class);
		Node <String> d=Mockito.mock(Node.class);
		Node <String> e=Mockito.mock(Node.class);
		Node <String> f=Mockito.mock(Node.class);
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
		List<Vertice> a_list = new ArrayList<Vertice>();
		a_list.add(ab);
		Mockito.when(a.getArcs()).thenReturn(a_list);
		List<Vertice> b_list = new ArrayList<Vertice>();
		b_list.add(bc);
		b_list.add(ab);
		b_list.add(be);
		Mockito.when(b.getArcs()).thenReturn(b_list);
		List<Vertice> c_list = new ArrayList<Vertice>();
		c_list.add(bc);
		c_list.add(cf);
		c_list.add(cd);
		Mockito.when(c.getArcs()).thenReturn(c_list);
		List<Vertice> d_list = new ArrayList<Vertice>();
		d_list.add(cd);
		Mockito.when(d.getArcs()).thenReturn(d_list);
		List<Vertice> e_list = new ArrayList<Vertice>();
		e_list.add(be);
		e_list.add(ef);
		Mockito.when(e.getArcs()).thenReturn(e_list);
		List<Vertice> f_list = new ArrayList<Vertice>();
		f_list.add(cf);
		f_list.add(ef);
		Mockito.when(f.getArcs()).thenReturn(f_list);
		Graph graph = new Graph();
		graph.addNode(a);
		graph.addNode(b);
		graph.addNode(c);
		graph.addNode(d);
		graph.addNode(e);
		graph.addNode(f);
		graph.addVertice(ab);
		graph.addVertice(bc);
		graph.addVertice(cd);
		graph.addVertice(be);
		graph.addVertice(ef);
		graph.addVertice(cf);
		
		List<Node> path= new ArrayList<Node>();
		path.add(a);
		DijkstraData pointTest =new DijkstraData(b, 1, path);
		assertTrue(graph.valueNext(pointTest).size()==3);
		DijkstraData a_data =new DijkstraData(a, 2, path);
		DijkstraData c_data =new DijkstraData(c, 2, path);
		DijkstraData e_data =new DijkstraData(e, 2, path);
		assertTrue(graph.valueNext(pointTest).contains(a_data));
		assertTrue(graph.valueNext(pointTest).contains(c_data));
		assertTrue(graph.valueNext(pointTest).contains(e_data));
		
	}
	
	@Test
	public void toAdd() {
		//Graph topo:
		// A - B - C - D
		//     |   |
		//     E - F
		//We take B as current point.
		Node <String> a=Mockito.mock(Node.class);
		Node <String> b=Mockito.mock(Node.class);
		Node <String> c=Mockito.mock(Node.class);
		Node <String> d=Mockito.mock(Node.class);
		Node <String> e=Mockito.mock(Node.class);
		Node <String> f=Mockito.mock(Node.class);
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
		List<Vertice> a_list = new ArrayList<Vertice>();
		a_list.add(ab);
		Mockito.when(a.getArcs()).thenReturn(a_list);
		List<Vertice> b_list = new ArrayList<Vertice>();
		b_list.add(bc);
		b_list.add(ab);
		b_list.add(be);
		Mockito.when(b.getArcs()).thenReturn(b_list);
		List<Vertice> c_list = new ArrayList<Vertice>();
		c_list.add(bc);
		c_list.add(cf);
		c_list.add(cd);
		Mockito.when(c.getArcs()).thenReturn(c_list);
		List<Vertice> d_list = new ArrayList<Vertice>();
		d_list.add(cd);
		Mockito.when(d.getArcs()).thenReturn(d_list);
		List<Vertice> e_list = new ArrayList<Vertice>();
		e_list.add(be);
		e_list.add(ef);
		Mockito.when(e.getArcs()).thenReturn(e_list);
		List<Vertice> f_list = new ArrayList<Vertice>();
		f_list.add(cf);
		f_list.add(ef);
		Mockito.when(f.getArcs()).thenReturn(f_list);
		Graph graph = new Graph();
		graph.addNode(a);
		graph.addNode(b);
		graph.addNode(c);
		graph.addNode(d);
		graph.addNode(e);
		graph.addNode(f);
		graph.addVertice(ab);
		graph.addVertice(bc);
		graph.addVertice(cd);
		graph.addVertice(be);
		graph.addVertice(ef);
		graph.addVertice(cf);
		
		List<Node> path= new ArrayList<Node>();
		path.add(a);
		DijkstraData b_data =new DijkstraData(b, 1, path);
		DijkstraData a_data =new DijkstraData(a, 2, path);
		DijkstraData c_data =new DijkstraData(c, 2, path);
		DijkstraData e_data =new DijkstraData(e, 2, path);
		List<DijkstraData> current = new ArrayList<DijkstraData>();
		current.add(b_data);
		List<DijkstraData> new_data_1 = new ArrayList<DijkstraData>();
		new_data_1.add(c_data);
		assertTrue(graph.toAdd(new_data_1, current).contains(c_data));
		List<DijkstraData> new_data_2 = new ArrayList<DijkstraData>();
		new_data_2.add(c_data);
		new_data_2.add(b_data);
		List<DijkstraData> listout = graph.toAdd(new_data_1, current);
		assertTrue(listout.size()==1);
	}

	@Test
	public void testUpdate() {
		//Graph topo:
		// A - B - C - D
		//     |   |
		//     E - F
		//We take B as current point.
		Node <String> a=Mockito.mock(Node.class);
		Node <String> b=Mockito.mock(Node.class);
		Node <String> c=Mockito.mock(Node.class);
		Node <String> d=Mockito.mock(Node.class);
		Node <String> e=Mockito.mock(Node.class);
		Node <String> f=Mockito.mock(Node.class);
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
		List<Vertice> a_list = new ArrayList<Vertice>();
		a_list.add(ab);
		Mockito.when(a.getArcs()).thenReturn(a_list);
		List<Vertice> b_list = new ArrayList<Vertice>();
		b_list.add(bc);
		b_list.add(ab);
		b_list.add(be);
		Mockito.when(b.getArcs()).thenReturn(b_list);
		List<Vertice> c_list = new ArrayList<Vertice>();
		c_list.add(bc);
		c_list.add(cf);
		c_list.add(cd);
		Mockito.when(c.getArcs()).thenReturn(c_list);
		List<Vertice> d_list = new ArrayList<Vertice>();
		d_list.add(cd);
		Mockito.when(d.getArcs()).thenReturn(d_list);
		List<Vertice> e_list = new ArrayList<Vertice>();
		e_list.add(be);
		e_list.add(ef);
		Mockito.when(e.getArcs()).thenReturn(e_list);
		List<Vertice> f_list = new ArrayList<Vertice>();
		f_list.add(cf);
		f_list.add(ef);
		Mockito.when(f.getArcs()).thenReturn(f_list);
		Graph graph = new Graph();
		graph.addNode(a);
		graph.addNode(b);
		graph.addNode(c);
		graph.addNode(d);
		graph.addNode(e);
		graph.addNode(f);
		graph.addVertice(ab);
		graph.addVertice(bc);
		graph.addVertice(cd);
		graph.addVertice(be);
		graph.addVertice(ef);
		graph.addVertice(cf);
		
		List<Node> path= new ArrayList<Node>();
		path.add(a);
		DijkstraData b_data =new DijkstraData(b, 1, path);
		DijkstraData a_data =new DijkstraData(a, 2, path);
		DijkstraData c_data =new DijkstraData(c, 2, path);
		DijkstraData e_data =new DijkstraData(e, 2, path);
		List<DijkstraData> current = new ArrayList<DijkstraData>();
		current.add(b_data);
		List<DijkstraData> new_data_1 = new ArrayList<DijkstraData>();
		new_data_1.add(c_data);
		assertFalse(graph.update(new_data_1, current).contains(c_data));
	}
	
	@Test
	public void testSummitReachedFinal() {
		//Graph topo:
		// A - B - C - D
		//     |   |
		//     E - F
		//We take B as current point.
		Node <String> a=Mockito.mock(Node.class);
		Node <String> b=Mockito.mock(Node.class);
		Node <String> c=Mockito.mock(Node.class);
		Node <String> d=Mockito.mock(Node.class);
		Node <String> e=Mockito.mock(Node.class);
		Node <String> f=Mockito.mock(Node.class);
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
		List<Vertice> a_list = new ArrayList<Vertice>();
		a_list.add(ab);
		Mockito.when(a.getArcs()).thenReturn(a_list);
		List<Vertice> b_list = new ArrayList<Vertice>();
		b_list.add(bc);
		b_list.add(ab);
		b_list.add(be);
		Mockito.when(b.getArcs()).thenReturn(b_list);
		List<Vertice> c_list = new ArrayList<Vertice>();
		c_list.add(bc);
		c_list.add(cf);
		c_list.add(cd);
		Mockito.when(c.getArcs()).thenReturn(c_list);
		List<Vertice> d_list = new ArrayList<Vertice>();
		d_list.add(cd);
		Mockito.when(d.getArcs()).thenReturn(d_list);
		List<Vertice> e_list = new ArrayList<Vertice>();
		e_list.add(be);
		e_list.add(ef);
		Mockito.when(e.getArcs()).thenReturn(e_list);
		List<Vertice> f_list = new ArrayList<Vertice>();
		f_list.add(cf);
		f_list.add(ef);
		Mockito.when(f.getArcs()).thenReturn(f_list);
		Graph graph = new Graph();
		graph.addNode(a);
		graph.addNode(b);
		graph.addNode(c);
		graph.addNode(d);
		graph.addNode(e);
		graph.addNode(f);
		graph.addVertice(ab);
		graph.addVertice(bc);
		graph.addVertice(cd);
		graph.addVertice(be);
		graph.addVertice(ef);
		graph.addVertice(cf);
		
		List<Node> path= new ArrayList<Node>();
		path.add(a);
		DijkstraData b_data =new DijkstraData(b, 1, path);
		DijkstraData a_data =new DijkstraData(a, 2, path);
		DijkstraData c_data =new DijkstraData(c, 2, path);
		DijkstraData e_data =new DijkstraData(e, 2, path);
		List<DijkstraData> current = new ArrayList<DijkstraData>();
		current.add(e_data);
		current.add(b_data);
		assertFalse(graph.summitReachedFinal(current, e));
		assertTrue(graph.summitReachedFinal(current, b));
	}
	
	@Test
	public void testSelectNode() {
		//Graph topo:
		// A - B - C - D
		//     |   |
		//     E - F
		//We take B as current point.
		Node <String> a=Mockito.mock(Node.class);
		Node <String> b=Mockito.mock(Node.class);
		Node <String> c=Mockito.mock(Node.class);
		Node <String> d=Mockito.mock(Node.class);
		Node <String> e=Mockito.mock(Node.class);
		Node <String> f=Mockito.mock(Node.class);
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
		
		List<Vertice> a_list = new ArrayList<Vertice>();
		a_list.add(ab);
		Mockito.when(a.getArcs()).thenReturn(a_list);
		List<Vertice> b_list = new ArrayList<Vertice>();
		b_list.add(bc);
		b_list.add(ab);
		b_list.add(be);
		Mockito.when(b.getArcs()).thenReturn(b_list);
		List<Vertice> c_list = new ArrayList<Vertice>();
		c_list.add(bc);
		c_list.add(cf);
		c_list.add(cd);
		Mockito.when(c.getArcs()).thenReturn(c_list);
		List<Vertice> d_list = new ArrayList<Vertice>();
		d_list.add(cd);
		Mockito.when(d.getArcs()).thenReturn(d_list);
		List<Vertice> e_list = new ArrayList<Vertice>();
		e_list.add(be);
		e_list.add(ef);
		Mockito.when(e.getArcs()).thenReturn(e_list);
		List<Vertice> f_list = new ArrayList<Vertice>();
		f_list.add(cf);
		f_list.add(ef);
		Mockito.when(f.getArcs()).thenReturn(f_list);
		Graph graph = new Graph();
		graph.addNode(a);
		graph.addNode(b);
		graph.addNode(c);
		graph.addNode(d);
		graph.addNode(e);
		graph.addNode(f);
		graph.addVertice(ab);
		graph.addVertice(bc);
		graph.addVertice(cd);
		graph.addVertice(be);
		graph.addVertice(ef);
		graph.addVertice(cf);
		
		List<Node> path= new ArrayList<Node>();
		path.add(a);
		DijkstraData b_data =new DijkstraData(b, 1, path);
		DijkstraData a_data =new DijkstraData(a, 2, path);
		DijkstraData c_data =new DijkstraData(c, 2, path);
		DijkstraData e_data =new DijkstraData(e, 2, path);
		List<DijkstraData> current = new ArrayList<DijkstraData>();
		current.add(e_data);
		current.add(b_data);
		assertFalse(graph.selectNode(current).equals(e_data));
		assertTrue(graph.selectNode(current).equals(b_data));
	}
	
	@Test
	public void testIsReacheable() {
		//Graph topo:
		// A - B - C - D
		//     |   
		//     E   F
		//We take B as current point.
		Node <String> a=new Node<String>("a");
		Node <String> b=new Node<String>("b");
		Node <String> c=new Node<String>("d");
		Node <String> d=new Node<String>("e");
		Node <String> e=new Node<String>("f");
		Node <String> f=new Node<String>("g");
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
		a.addVertice(ab);
		b.addVertice(be);
		b.addVertice(ab);
		b.addVertice(bc);
		c.addVertice(bc);
		c.addVertice(cd);
		d.addVertice(cd);
		e.addVertice(be);
		
		Graph graph = new Graph();
		graph.addNode(a);
		graph.addNode(b);
		graph.addNode(c);
		graph.addNode(d);
		graph.addNode(e);
		graph.addNode(f);
		graph.addVertice(ab);
		graph.addVertice(bc);
		graph.addVertice(cd);
		graph.addVertice(be);
		
		assertFalse(graph.isReacheable(a, f));
		assertTrue(graph.isReacheable(a, b));
	}
	
	@Test
	public void testShortestPath() {
		//Graph topo:
		// A - B - C - D
		//     |   
		//     E   F
		//We take B as current point.
		Node <String> a=new Node<String>("a");
		Node <String> b=new Node<String>("b");
		Node <String> c=new Node<String>("d");
		Node <String> d=new Node<String>("e");
		Node <String> e=new Node<String>("f");
		Node <String> f=new Node<String>("g");
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
		a.addVertice(ab);
		b.addVertice(be);
		b.addVertice(ab);
		b.addVertice(bc);
		c.addVertice(bc);
		c.addVertice(cd);
		d.addVertice(cd);
		e.addVertice(be);
		
		Graph graph = new Graph();
		graph.addNode(a);
		graph.addNode(b);
		graph.addNode(c);
		graph.addNode(d);
		graph.addNode(e);
		graph.addNode(f);
		graph.addVertice(ab);
		graph.addVertice(bc);
		graph.addVertice(cd);
		graph.addVertice(be);
		
		List<Node> pathaf= new ArrayList<Node>();
		assertTrue(graph.shortestPath(a, f).equals(pathaf));
		List<Node> pathad= new ArrayList<Node>();
		pathad.add(a);
		pathad.add(b);
		pathad.add(c);
		pathad.add(d);
		assertTrue(graph.shortestPath(a, d).equals(pathad));
	}
}
