package com.springBasicLearning.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.springBasicLearning.demo.interfaces.SortAlgorithm;

@Component
public class BinarySearchImpl {
	
	@Autowired
	@Qualifier("bubbleSortAlgorithm")
	private SortAlgorithm sortAlgorithm;
	
	 	
	/*public BinarySearchImpl(SortAlgorithm sortAlgorithm) {
		this.sortAlgorithm = sortAlgorithm;
	}*/

	public int binarySearch(int[] numbers, int numberToSearch) {
		//Sort the array
		//Bubble Sort
		System.out.println(sortAlgorithm);
		int[] sortedNumbers = sortAlgorithm.sort(numbers);
		
		//Search the array
		return 3;
	}

}
