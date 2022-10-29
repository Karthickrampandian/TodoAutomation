package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags = "", features = {"src/test/resources/features"},
        glue = {"Definitions"},
        plugin = {})

public class CucumberRunnerTests extends AbstractTestNGCucumberTests {

}