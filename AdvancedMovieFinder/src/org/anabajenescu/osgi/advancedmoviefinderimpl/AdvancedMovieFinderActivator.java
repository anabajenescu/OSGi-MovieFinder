package org.anabajenescu.osgi.advancedmoviefinderimpl;

import java.util.Dictionary;
import java.util.Properties;

import org.anabajenescu.osgi.moviefinder.MovieFinder;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class AdvancedMovieFinderActivator implements BundleActivator {

	private ServiceRegistration<?> serviceRegistration;

	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("AdvancedMovieFinderActivator: start");
		
		MovieFinder movieFinder = new AdvancedMovieFinderImpl();
		Dictionary properties = new Properties();
		properties.put("category", "mist");

		System.out.println("AdvancedMovieFinderActivator: Preparing to register MovieFinder service");
		serviceRegistration = bundleContext.registerService(MovieFinder.class.getName(), movieFinder, properties);
		System.out.println("AdvancedMovieFinderActivator: MovieFinder service registered successfully!");
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("AdvancedMovieFinderActivator: Preparing to unregister service");
		serviceRegistration.unregister();
		System.out.println("AdvancedMovieFinderActivator: Service unregistered successfully!");
	}

}
