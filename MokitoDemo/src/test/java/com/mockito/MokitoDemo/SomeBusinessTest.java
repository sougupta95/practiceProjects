package com.mockito.MokitoDemo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SomeBusinessTest {

	@Test
	void testFindGreatest() {
		SomeBusinessClass business = new SomeBusinessClass(new DataServiceStub());
		
		int greatest = business.findGreatest();
		assertEquals(40, greatest);
		
	}

}

class DataServiceStub implements DataService{
	@Override
	public int[] retrieveAllData(){
		return new int[] {24,12,40};
		}
	
}
