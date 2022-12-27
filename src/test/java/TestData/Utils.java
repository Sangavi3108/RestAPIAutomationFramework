package TestData;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Properties;

import javax.imageio.stream.FileImageInputStream;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	RequestSpecification req;
 

	public RequestSpecification TestRequestSpecification() throws Exception {

		PrintStream log = null;
		int Counter = 0;

		if (Counter == 0) {
			log = CreateLoggerFile();
			Counter++;
		}
		req = new RequestSpecBuilder().setBaseUri(GetProperty("BaseURI")).addHeader("Content-Type", "application/json")
				.addQueryParam("key", "qaclick123").addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log)).build();

		return req;

	}

	private PrintStream CreateLoggerFile() throws FileNotFoundException {
		String fname = new SimpleDateFormat("yyyyMMddHHmm'.txt'").format(new Date());
		FileOutputStream F = new FileOutputStream(fname, true);
		PrintStream log = new PrintStream(F);
		return log;

	}

	public static String GetProperty(String Key) throws IOException {
		Properties prop = new Properties();
		FileInputStream Fis = new FileInputStream(
				"C:\\Users\\003J6O744\\eclipse-workspace\\RestAssuredBDD\\src\\test\\java\\TestData\\Global.properties");
		prop.load(Fis);
		return prop.get(Key).toString();

	}
	
	public String getJsonPath(Response Res, String Key)
	{
		String SRes = Res.asString();
		JsonPath js = new JsonPath(SRes);
		return js.get(Key).toString();
		
	}
}
