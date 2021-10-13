package org.anabajenescu.osgi.moviefinder;

import java.util.List;

import org.anabajenescu.osgi.movielistener.MovieListener;
import org.anabajenescu.osgi.movielistener.Movies;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.ServiceTracker;

public class MovieListerActivator implements BundleActivator {

	private static final long SLEEP_TIME = 10000;
	private static final String MOVIE_NAME = "Movie name: ";
	private static final String DIRECTED_BY = " - directed by: ";
	private static final String SEPARATOR = "; ";
	
	private static BundleContext context;
	private Movies movies;
	
	private boolean stop = false;
	
	static BundleContext getContext() {
		return context;
	}

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("MovieListerActivator: start");
		stop = false;
		movies = new Movies();
		
		bundleContext.addBundleListener(new MovieListener(movies));
		
		new Thread(() -> {
			while (!stop) {
				try {
					Thread.sleep(SLEEP_TIME);
					StringBuffer buffer = new StringBuffer("\n");
					
					for (MovieFinder movieFinder : movies.getAllMovies()) {
						List<Movie> allMovies = movieFinder.findAll();
						allMovies.forEach(m -> buffer.append(MOVIE_NAME + m.getName() + DIRECTED_BY + m.getDirectedBy() + SEPARATOR));
					}
					
					System.out.println(buffer.toString());
					
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		}).start();
		
		MovieListerActivator.context = bundleContext;
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("MovieListerActivator: stop");
		stop = true;
		
		MovieListerActivator.context = null;
	}

}

//Version 2 - Search movies directed by desired director
//public class MovieListerActivator implements BundleActivator {
//
//	private static BundleContext context;
//	
//	static BundleContext getContext() {
//		return context;
//	}
//	
//	private ServiceTracker<MovieFinder, MovieFinder> serviceTracker;
//	private ServiceRegistration<?> serviceRegistration;
//
//	@Override
//	public void start(BundleContext bundleContext) throws Exception {
//		System.out.println("MovieListerActivator: start");
//		serviceTracker = new ServiceTracker<MovieFinder, MovieFinder>(bundleContext, MovieFinder.class.getName(), null);
//		serviceTracker.open();
//
//		System.out.println("MovieListerActivator: Create and register MovieListerImpl");
//		final MovieLister movieLister = new MovieListerImpl(serviceTracker.getService());
//		serviceRegistration = context.registerService(MovieLister.class.getName(), movieLister, null);
//
//		System.out.println("MovieListerActivator: Search using that lister");
//		search(movieLister);
//		
//		MovieListerActivator.context = bundleContext;
//	}
//
//	@Override
//	public void stop(BundleContext bundleContext) throws Exception {
//		System.out.println("MovieListerActivator: Uunregister serviceRegistration");
//		serviceRegistration.unregister();
//
//		System.out.println("MovieListerActivator: Close serviceTracker");
//		serviceTracker.close();
//		
//		MovieListerActivator.context = null;
//	}
//	
//	private void search(MovieLister movieLister) {
//		System.out.println("MovieListerActivator: Start searching");
//		
//		// search for movies directed by a director from SimpleMovieFinder
//		List<Movie> movies = movieLister.listByDirector("Peter Jackson");
//		if (movies.isEmpty()) {
//			System.err.println("MovieListerActivator:Could not get any movies");
//		} else {
//			movies.forEach(m -> System.out.println("MovieListerActivator: Movie name: " + m.getName()));
//		}
//		
//		// search for movies directed by a director from AdvancedMovieFinder
//		movies = movieLister.listByDirector("Taika Waititi");
//		if (movies.isEmpty()) {
//			System.err.println("MovieListerActivator:Could not get any movies");
//		} else {
//			movies.forEach(m -> System.out.println("MovieListerActivator: Movie name: " + m.getName()));
//		}
//	}
//}