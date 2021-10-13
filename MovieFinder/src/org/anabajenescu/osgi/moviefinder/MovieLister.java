package org.anabajenescu.osgi.moviefinder;

import java.util.List;

public interface MovieLister {
	
	List<Movie> listByDirector(String name);

}
