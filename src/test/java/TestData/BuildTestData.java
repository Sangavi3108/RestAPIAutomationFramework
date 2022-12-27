package TestData;

import java.util.ArrayList;
import java.util.List;

import Pojo.Addplace;
import Pojo.Location;

public class BuildTestData {

	public Addplace AddPlacePayload(String name, String address, String phone)
	{
		Addplace AP = new Addplace();
		AP.setAccuracy(50);
		AP.setAddress(address);
		AP.setLanguage("French-IN");
		
		AP.setName(name);
		AP.setPhone_number(phone);
		AP.setWebsite("http://google.com");
		List<String> type = new ArrayList<String>();
		type.add("Shoe");
		type.add("Park");
		
		Location L = new Location();
		L.setLat(-38.383494);
		L.setLng(33.427362);
		AP.setLocation(L);
		return AP;
	}
	
	public String DeletePlacePayload(String Placeid)
	{
		return "{\r\n"
				+ "    \"place_id\": \""+Placeid+"\"\r\n"
				+ "}" ;
	}
	
}
