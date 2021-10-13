package org.anabajenescu.osgi.advancedmoviefinderimpl;

import java.util.ArrayList;
import java.util.List;

import org.anabajenescu.osgi.moviefinder.Movie;
import org.anabajenescu.osgi.moviefinder.MovieFinder;

public class AdvancedMovieFinderImpl implements MovieFinder{
	
	private static final List<Movie> MOVIES = populateMovies();

	@Override
	public List<Movie> findAll() {
		System.out.println("AdvancedMovieFinderImpl: Get Advanced Movies");

		return MOVIES;
	}
	
	private static List<Movie> populateMovies() {
		List<Movie> movies = new ArrayList<>();
		movies.add(new Movie("Doctor Strange", "Scott Derrickson"));
		movies.add(new Movie("Thor: Ragnarok", "Taika Waititi"));
		
		return movies;
	}

}