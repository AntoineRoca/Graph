package eu.ensg.aroca.graph;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

public class DijkstraDataTest {

	@Test
	public void testEquals() {
		Node <String> a=new Node<String>("a");
		Node <String> b=new Node<String>("b");
		List<Node> lista = new ArrayList<Node>();
		List<Node> listb = new ArrayList<Node>();
		lista.add(b);
		DijkstraData a_data= new DijkstraData(a, 0, lista);
		DijkstraData b_data= new DijkstraData(b, 0, lista);
		DijkstraData c_data= new DijkstraData(a, 0, lista);
		DijkstraData d_data= new DijkstraData(a, 1, lista);
		DijkstraData e_data= new DijkstraData(a, 0, listb);
		assertTrue(a_data.equals(a_data));
		assertFalse(a_data.equals(b_data));
		assertTrue(a_data.equals(c_data));
		assertTrue(a_data.equals(d_data));
		assertTrue(a_data.equals(e_data));
		assertFalse(a_data.equals("False"));
	}

}
