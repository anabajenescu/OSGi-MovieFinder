package org.anabajenescu.osgi.simplemoviefinderimpl;

import org.anabajenescu.osgi.moviefinder.MovieFinder;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class SimpleMovieFinderActivator implements BundleActivator {

	private ServiceRegistration<?> serviceRegistration;

	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("SimpleMovieFinderActivator: start");
		
		MovieFinder movieFinder = new SimpleMovieFinderImpl();

		System.out.println("SimpleMovieFinderActivator: Preparing to register MovieFinder service");
		serviceRegistration = bundleContext.registerService(MovieFinder.class.getName(), movieFinder, null);
		System.out.println("SimpleMovieFinderActivator: MovieFinder service registered successfully!");
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("SimpleMovieFinderActivator: Preparing to unregister service");
		serviceRegistration.unregister();
		System.out.println("SimpleMovieFinderActivator: Service unregistered successfully!");
	}

}
