package com.xyz.chapter6;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

@SuppressWarnings("unused")
public class SimpleMovieLister {
	
	private static Log log = LogFactory.getLog(SimpleMovieLister.class);
	
	private MovieFinder movieFinder;
	
	@Autowired
	public void setMovieFinder(MovieFinder movieFinder) {
		log.info("MovieFinder: " + movieFinder.toString());
		this.movieFinder = movieFinder;
	}

}
