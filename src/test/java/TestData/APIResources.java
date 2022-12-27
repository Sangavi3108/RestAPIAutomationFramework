package TestData;

public enum APIResources {

	AddPlace("/maps/api/place/add/json"),
	DeletePlace("/maps/api/place/delete/json"),
	GetPlace("/maps/api/place/get/json");
	String resource;
	
	private APIResources(String resource) {
		this.resource=resource;
	}
	
	public String getResource()
	{
		return resource;
	}
}
