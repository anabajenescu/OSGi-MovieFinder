package org.anabajenescu.osgi.simplemoviefinderimpl;

import java.util.ArrayList;
import java.util.List;

import org.anabajenescu.osgi.moviefinder.Movie;
import org.anabajenescu.osgi.moviefinder.MovieFinder;

public class SimpleMovieFinderImpl implements MovieFinder{
	
	private static final List<Movie> MOVIES = populateMovies();

	@Override
	public List<Movie> findAll() {
		System.out.println("SimpleMovieFinderImpl: Get Simple Movies");

		return MOVIES;
	}
	
	private static List<Movie> populateMovies() {
		List<Movie> movies = new ArrayList<>();
		movies.add(new Movie("The Lord of the Rings: The Fellowship of the Ring", "Peter Jackson"));
		movies.add(new Movie("The Hobbit: An Unexpected Journey", "Peter Jackson"));
		
		return movies;
	}

}
