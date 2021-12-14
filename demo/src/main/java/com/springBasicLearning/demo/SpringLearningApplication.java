package com.springBasicLearning.demo;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;

@Configuration
@ComponentScan("com.springBasicLearning.demo")
public class SpringLearningApplication {

	public static void main(String[] args) {
		//Spring will handle
		//BinarySearchImpl binarySearch = new BinarySearchImpl(new QuickSortAlgorithm());
		
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringLearningApplication.class);
				
		
		BinarySearchImpl binarySearch = applicationContext.getBean(BinarySearchImpl.class);//.getBean("BinarySearchImpl");
		
		int result = binarySearch.binarySearch(new int[]{2,9,0,10,12,3,5}, 3);
		
		System.out.println(result);
		applicationContext.close();
	}

}
