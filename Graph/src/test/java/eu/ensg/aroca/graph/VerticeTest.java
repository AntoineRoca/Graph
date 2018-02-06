package eu.ensg.aroca.graph;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

/**
* JUnit test class for the vertice.
*
*@author Antoine Roca
*@version 1.0
*@see Vertice
*/

public class VerticeTest {

	@Test
	/**
	*Method to test the clone function.
	*/
	public void testClone() {
		ArrayList<Node> ends = new ArrayList<Node>();
		ends.add(new Node());
		ends.add(new Node());	
		Vertice<String> vertice_test= new Vertice<String>("chaine",1,ends,true);
		assertTrue(vertice_test.equals(vertice_test.clone()));
		assertFalse(vertice_test==vertice_test.clone());
	}
	
	@Test
	/**
	*Method to test the equals function.
	*/
	public void testEquals() {
		ArrayList<Node> ends = new ArrayList<Node>();
		ends.add(new Node());
		ends.add(new Node());	
		Vertice<String> vertice_test= new Vertice<String>("chaine",1,ends,true);
		Vertice<String> vertice_test_t= new Vertice<String>("chaine",1,ends,true);
		Vertice<Integer> vertice_test_f_t=new Vertice<Integer>(new Integer(1),1,ends,true);
		Vertice<String> vertice_test_f_s= new Vertice<String>("err",1,ends,true);
		Vertice<String> vertice_test_f_p= new Vertice<String>("chaine",2,ends,true);
		Vertice<String> vertice_test_f_b= new Vertice<String>("chaine",1,ends,false);
		Vertice<String> vertice_test_f_e= new Vertice<String>("chaine",1,(ArrayList<Node>)ends.clone(),false);
		assertFalse(vertice_test.equals("a"));
		assertTrue(vertice_test.equals(vertice_test));
		assertTrue(vertice_test.equals(vertice_test_t));
		assertFalse(vertice_test.equals(vertice_test_f_t));
		assertFalse(vertice_test.equals(vertice_test_f_s));
		assertFalse(vertice_test.equals(vertice_test_f_p));
		assertFalse(vertice_test.equals(vertice_test_f_b));
		assertFalse(vertice_test.equals(vertice_test_f_e));
	}

}
