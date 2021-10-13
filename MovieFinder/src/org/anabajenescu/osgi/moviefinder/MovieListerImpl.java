package org.anabajenescu.osgi.moviefinder;

import java.util.List;
import java.util.stream.Collectors;

import org.osgi.util.tracker.ServiceTracker;

public class MovieListerImpl implements MovieLister {

	private final ServiceTracker<MovieFinder, MovieFinder> serviceTracker;
	
	public MovieListerImpl(ServiceTracker<MovieFinder, MovieFinder> serviceTracker) {
		this.serviceTracker = serviceTracker;
	}
	
	@Override
	public List<Movie> listByDirector(String name) {
		System.out.println("MovieListerImpl.listByDirector with name " + name + " - preparing to get service");

		MovieFinder movieFinder = serviceTracker.getService();
		if(movieFinder == null) {
			System.err.println("MovieListerImpl: movieFinder instance is null! Won't perform the search!");
			
			return null;
		} else {
			System.out.println("MovieListerImpl: movieFinder instance found! Start searching for movies!");
			
			return searchMovies(name, movieFinder);
		}
	}
	
	private List<Movie> searchMovies(String name, MovieFinder movieFinder) {
		List<Movie> allMovies = movieFinder.findAll();
		
		return allMovies.stream().filter(m -> m.getDirectedBy().equals(name)).collect(Collectors.toList());
	}

}
