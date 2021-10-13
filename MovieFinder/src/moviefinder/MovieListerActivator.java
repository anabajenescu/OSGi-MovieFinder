package moviefinder;

import java.util.List;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import movielister.MovieListener;
import movielister.Movies;

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

	//TODO register services and perform searches
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
