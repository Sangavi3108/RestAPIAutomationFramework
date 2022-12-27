package Runnner;

import java.io.File;

import org.junit.AfterClass;
import org.junit.runner.RunWith;



import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions
(features= "src/test/java/Features",
glue = {"StepDefinitions"},
monochrome = true,
plugin = {"pretty", "html:target/htmlReports/report.html",
		"json:target/jsonReports/report.json"
        }
//,tags= "@DeletePlace"
		)
public class TestRunner {
	
	
}