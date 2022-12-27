package StepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;


public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws Exception
	{
		StepDefinitions s = new StepDefinitions();
		if(StepDefinitions.id==null)
		{
			s.add_place_payload_with("Janani", "B49 , MMDA , Chennai", "9544401195");
			s.user_calls_with_post_request("AddPlace", "post");
			s.api_call_is_succesful_with_status_code(200);
			s.verify_of_using_with_method("place_id", "name", "Janani", "GetPlace", "get");
		}

		
	}
	

}
