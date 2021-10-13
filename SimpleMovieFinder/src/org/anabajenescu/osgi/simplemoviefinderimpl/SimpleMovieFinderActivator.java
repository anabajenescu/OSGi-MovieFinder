package org.anabajenescu.osgi.simplemoviefinderimpl;

import java.util.Dictionary;
import java.util.Properties;

import org.anabajenescu.osgi.moviefinder.MovieFinder;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class SimpleMovieFinderActivator implements BundleActivator {

	private static BundleContext context;
	
	private ServiceRegistration<?> serviceRegistration;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("SimpleMovieFinderActivator: start");
		
		MovieFinder movieFinder = new SimpleMovieFinderImpl();
		Dictionary properties = new Properties();
		properties.put("category", "misc"); //TODO check this

		System.out.println("SimpleMovieFinderActivator: Preparing to register MovieFinder service");
		serviceRegistration = context.registerService(MovieFinder.class.getName(), movieFinder, properties);
		System.out.println("SimpleMovieFinderActivator: MovieFinder service registered successfully!");
		
		SimpleMovieFinderActivator.context = bundleContext;
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("SimpleMovieFinderActivator: Preparing to unregister service");
		serviceRegistration.unregister();
		System.out.println("SimpleMovieFinderActivator: Service unregistered successfully!");
		
		SimpleMovieFinderActivator.context = null;
	}

}
