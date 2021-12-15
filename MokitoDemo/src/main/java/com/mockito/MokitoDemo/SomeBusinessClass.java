package com.mockito.MokitoDemo;

public class SomeBusinessClass {
	private DataService dataService;
	
	public SomeBusinessClass(DataService dataService) {
		super();
		this.dataService = dataService;
	}
	int findGreatest() {
		int[] data = dataService.retrieveAllData();
		int greatest = Integer.MIN_VALUE;
		for(int value : data) {
			if(value > greatest) {
				greatest = value;
			}
		}
		return greatest;
	}
}
