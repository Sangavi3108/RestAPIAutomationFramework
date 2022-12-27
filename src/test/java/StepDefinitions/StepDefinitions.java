package StepDefinitions;

import java.util.ArrayList;
import java.util.List;
import Pojo.Addplace;
import Pojo.Location;
import TestData.APIResources;
import TestData.BuildTestData;
import TestData.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static io.restassured.RestAssured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;
import static org.junit.Assert.*;

public class StepDefinitions extends Utils {

	RequestSpecification ReqS;
	ResponseSpecification ResS;
	static String id;
	Response response;
	BuildTestData TD = new BuildTestData();

	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String address, String phone) throws Exception {

		ReqS = given().spec(TestRequestSpecification()).body(TD.AddPlacePayload(name, address, phone));
	}

	@When("user calls {string} with {string} request")
	public void user_calls_with_post_request(String resourcename, String httpmethod) {

		APIResources Res = APIResources.valueOf(resourcename);

		if (httpmethod.equalsIgnoreCase("Get"))
			response = ReqS.spec(ReqS).when().log().all().get(Res.getResource()).then().log().all().extract()
					.response();
		else if (httpmethod.equalsIgnoreCase("Post"))
			response = ReqS.spec(ReqS).when().log().all().post(Res.getResource()).then().log().all().extract()
					.response();
		else if (httpmethod.equalsIgnoreCase("Put"))
			response = ReqS.spec(ReqS).when().log().all().put(Res.getResource()).then().log().all().extract()
					.response();
		else if (httpmethod.equalsIgnoreCase("Delete"))
			response = ReqS.spec(ReqS).when().log().all().delete(Res.getResource()).then().log().all().extract()
					.response();

	}

	@SuppressWarnings("deprecation")
	@Then("API call is succesful with {int} status code")
	public void api_call_is_succesful_with_status_code(Integer int1) {

		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in Response body is {string}")
	public void in_response_body_is(String Key, String Value) {

		Assert.assertEquals(getJsonPath(response, Key), Value);

	}

	@Then("verify {string} of {string} {string} using {string} with {string} method")
	public void verify_of_using_with_method(String attribute, String responseattr, String ExpectedValue,
			String resourcename, String httpmethod) {

		id = getJsonPath(response, attribute);
		APIResources resname = APIResources.valueOf(resourcename);
		ReqS = given().spec(ReqS).queryParam("place_id", id);
		if (httpmethod.equals("get"))
			response = ReqS.when().get(resname.getResource()).then().log().all().extract().response();
		String Actualvalue = getJsonPath(response, responseattr);
		System.out.println("Actual Value : " + Actualvalue);
		Assert.assertEquals(ExpectedValue, Actualvalue);

	}
	
	@Given("Delete place Payload")
	public void delete_place_payload() throws Exception {
	    ReqS = given().spec(TestRequestSpecification()).body(TD.DeletePlacePayload(id));
	}

}
