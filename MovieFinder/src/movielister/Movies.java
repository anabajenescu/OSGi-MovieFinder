package movielister;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import moviefinder.MovieFinder;

public class Movies {

	private final Map<String, MovieFinder> movies = new HashMap<>();
	
	public Movies() {}
	
	public synchronized void addMovie(final String name, final MovieFinder movieFinder) {
		movies.put(name, movieFinder);
	}

	public synchronized void removeMovie(final String name) {
		movies.remove(name);
	}

	public synchronized Collection<MovieFinder> getAllMovies() {
		return movies.values();
	}
}
