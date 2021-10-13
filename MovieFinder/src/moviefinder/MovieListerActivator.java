package moviefinder;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import movielister.MovieListener;
import movielister.Movies;

public class MovieListerActivator implements BundleActivator {

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
				
			}
		}).start();
		
		
		
		MovieListerActivator.context = bundleContext;
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		MovieListerActivator.context = null;
	}

}
