package com.springBasicLearning.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.springBasicLearning.demo.interfaces.SortAlgorithm;

@Component
@Qualifier("bubbleSortAlgorithm")
public class BubbleSortAlgorithm implements SortAlgorithm {
	
	public int[] sort(int[] input) {
		
		//TO DO Sorting logic
		return input;
	}

}
