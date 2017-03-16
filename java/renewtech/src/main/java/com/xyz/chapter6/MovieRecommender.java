package com.xyz.chapter6;

import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("unused")
public class MovieRecommender {
	
	private MovieCatalog movieCatalog;
	
	private CustomerPreferenceDao customerPreferenceDao;
	
	@Autowired
	public void prepare(MovieCatalog movieCatalog, CustomerPreferenceDao customerPreferenceDao) {
		this.movieCatalog = movieCatalog;
		this.customerPreferenceDao = customerPreferenceDao;
	}

}
