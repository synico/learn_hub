package com.xyz.chapter6;

public class MovieFinder {
	
	private String movieName;
	
	private String producer;

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("movieName: " + this.getMovieName());
		sb.append(" | ");
		sb.append("producer: " + this.getProducer());
		return sb.toString();
	}
	
}
