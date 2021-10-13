package advancedmoviefinder;

import java.util.Dictionary;
import java.util.Properties;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import moviefinder.MovieFinder;

public class AdvancedMovieFinderActivator implements BundleActivator {

	private static BundleContext context;

	private ServiceRegistration<?> serviceRegistration;
	
	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("AdvancedMovieFinderActivator: start");
		
		MovieFinder movieFinder = new AdvancedMovieFinderImpl();
		Dictionary properties = new Properties();
		properties.put("category", "misc"); //TODO check this

		System.out.println("AdvancedMovieFinderActivator: Preparing to register MovieFinder service");
		serviceRegistration = context.registerService(MovieFinder.class.getName(), movieFinder, properties);
		System.out.println("AdvancedMovieFinderActivator: MovieFinder service registered successfully!");
		
		AdvancedMovieFinderActivator.context = bundleContext;
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("AdvancedMovieFinderActivator: Preparing to unregister service");
		serviceRegistration.unregister();
		System.out.println("AdvancedMovieFinderActivator: Service unregistered successfully!");
		
		AdvancedMovieFinderActivator.context = null;
	}

}
