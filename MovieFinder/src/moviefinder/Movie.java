package moviefinder;

public class Movie {
	
	private final String name;
	private final String directedBy;
	
	public Movie(String name, String directedBy) {
		this.name = name;
		this.directedBy = directedBy;
	}

	public String getName() {
		return name;
	}
	
	public String getDirectedBy() {
		return directedBy;
	}
	
	

}
