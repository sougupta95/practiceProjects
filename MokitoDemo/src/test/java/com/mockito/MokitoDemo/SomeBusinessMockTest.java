package com.mockito.MokitoDemo;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SomeBusinessMockTest {

	@Test
	void testFindGreatest() {
		DataService dataService = mock(DataService.class);
		
		//dataService.retrieveAllData()=> new int[] {23,3,15};
		
		when(dataService.retrieveAllData()).thenReturn( new int[] {23,3,15});
		
		SomeBusinessClass business = new SomeBusinessClass(dataService);
		
		int greatest = business.findGreatest();
		assertEquals(23, greatest);
		
	}

}


