package movielister;

import java.util.Dictionary;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleListener;

import moviefinder.MovieFinder;

public class MovieListener implements BundleListener{
	
	private static final String FINDER_EXTENSION_NAME = "Finder-Extension-Name";
	private static final String EXTENSION_CLASS = "Extension-Class";
	
	private final Movies movies;

	public MovieListener(final Movies movies) {
		this.movies = movies;
	}
	
	
	@Override
	public void bundleChanged(final BundleEvent event) {
		Bundle bundle = event.getBundle();

		System.out.println("MovieListener[ Event type = " + event.getType() + "; bundle = " + bundle + 
				"; source = " + event.getSource() + " ]");

		if (event.getType() == BundleEvent.STARTED) {
			if (isExtension(bundle)) {
				addExtension(bundle);
			}
		}

		if (event.getType() == BundleEvent.STOPPED) {
			if (isExtension(bundle)) {
				removeExtension(bundle);
			}
		}
	}
	
	private boolean isExtension(Bundle bundle) {
		Dictionary<String, String> dictionary = bundle.getHeaders();
		
		if (dictionary.get(FINDER_EXTENSION_NAME) == null) {
			System.err.println("MovieListener: Bundle " + bundle.getSymbolicName() + " isn't a MovieFinder Extension");
			
			return false;
		}
		
		System.out.println("MovieListener: Bundle " + bundle.getSymbolicName() + " is a MovieFinder Extension");
		
		return true;
	}
	
	private void addExtension(Bundle bundle) {
		System.out.println("MovieListener: Add MovieFinder extension: " + bundle.getSymbolicName());
		
		Dictionary<String, String> dictionary = bundle.getHeaders();
		String extensionName = dictionary.get(FINDER_EXTENSION_NAME);
		String className = dictionary.get(EXTENSION_CLASS);
		
		try {
			Class<?> clazz = bundle.loadClass(className);
			movies.addMovie(extensionName, (MovieFinder)clazz.getDeclaredConstructor().newInstance());
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	
	private void removeExtension(Bundle bundle) {
		System.out.println("MovieListener: Remove MovieFinder extension: " + bundle.getSymbolicName());
		
		Dictionary<String, String> dictionary = bundle.getHeaders();
		movies.removeMovie(dictionary.get(FINDER_EXTENSION_NAME));
	}

}
