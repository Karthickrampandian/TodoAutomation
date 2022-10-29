package Definitions;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;


import dataProviders.ConfigFileReader;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.TodoHomePage;

public class LoginPageDefinitions {

    private static WebDriver driver;
    TodoHomePage todo;
    public final static int TIMEOUT = 10;
    private static String Browser;
    private final static String ExpectedTitle= "Vue.js • TodoMVC";
    ConfigFileReader config  = new ConfigFileReader();

    @Before
    public void setUp() {
        Browser = config.launchBrowser();
        if(Browser.equalsIgnoreCase("chrome"))
        {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        else if (Browser.equalsIgnoreCase("firefox"))
        {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        else if (Browser.equalsIgnoreCase("safari"))
        {
            WebDriverManager.safaridriver().setup();
            driver = new SafariDriver();
        }
        else if (Browser.equalsIgnoreCase("edge"))
        {
            WebDriverManager.edgedriver().avoidBrowserDetection().setup();
            driver = new EdgeDriver();
        }
        else
        {
            throw new RuntimeException("Not a valid browser");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
        driver.manage().window().maximize();
    }

    @Given("User is on TODO Homepage {string} and verify title")
    public void user_is_on_hrm_login_page(String url) {
        driver.get(config.getApplicationUrl(url));
        String ActualTitle = driver.getTitle();
        Assert.assertEquals(ActualTitle,ExpectedTitle,"Title is matching");
    }

    @When("User enters todo {string}")
    public void enterTodo(String todotext) throws InterruptedException {
        todo = new TodoHomePage(driver);
        todo.enter_Name(todotext);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(config.getImplicitlyWait()));
    }

    @When("User adds multiple todo items in same session")
    public void multipleTodo(DataTable table) throws InterruptedException {
        todo = new TodoHomePage(driver);
        int i =0;
        List<String> details = table.asList(String.class);
       for(String todoText : details)
       {
           String parameter = details.get(i++);
           todo.enter_Name(parameter);
       }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(config.getImplicitlyWait()));
    }

    @Then("Edit First Todo with random Text")
    public void editTODO() throws InterruptedException {
        todo = new TodoHomePage(driver);
        todo.edit_FirstTodo(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(config.getImplicitlyWait()));
    }

    @Then("Delete it")
    public void deletefirstTodo() throws InterruptedException {
        todo = new TodoHomePage(driver);
        todo.delete_FirstTodo(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(config.getImplicitlyWait()));
    }

    @When("Mark all Todo as completed and verify items count")
    public void markAllCompleted() throws InterruptedException {
        todo.mark_AllTodoCompleted();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(config.getImplicitlyWait()));
        Assert.assertEquals(todo.active_TODOCount(), todo.todo_COUNT(),"Completed Count matches with left out list");
    }

    @And("Clear all completed Todo's")
    public void clearAllCompleted() throws InterruptedException {
        todo.clear_AllTodoCompleted();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(config.getImplicitlyWait()));
    }

    @Then("Show Active Todo's")
    public void showActiveTodo() throws InterruptedException {
        todo.show_ActiveTodo();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(config.getImplicitlyWait()));
    }

    @Then("Show Completed Todo's")
    public void showCompletedTodo() throws InterruptedException {
        todo.show_CompletedTodo();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(config.getImplicitlyWait()));
    }

    @Then("Show All Todo's")
    public void showAllTodo() throws InterruptedException {
        todo.show_allTodo();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(config.getImplicitlyWait()));
    }

    @Then("mark random Todo as completed")
    public void markRandomTodoAsCompleted() throws InterruptedException {
        todo = new TodoHomePage(driver);
        todo.completeRandom_TODO(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(config.getImplicitlyWait()));
    }

    @Then("edit a random Todo and mark it as completed")
    public void editAndCompleteaRandomTodo() throws InterruptedException {
        todo = new TodoHomePage(driver);
        todo.completeAndEditRandom_TODO(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(config.getImplicitlyWait()));
    }

    @Then("Delete a random Todo")
    public void deleteaRandomTodo() throws InterruptedException {
        todo = new TodoHomePage(driver);
        todo.delete_RandomTodo(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(config.getImplicitlyWait()));
    }

    @When("Verify count")
    public void verifyCount() throws InterruptedException {
        todo = new TodoHomePage(driver);
        todo.verifyTodoCount();
    }

    @Given("^User navigates to TODO Homepage \"([^\"]*)\" using \"([^\"]*)\" and verify title$")
    public void crossbrowserTesting(String URL, String Browser) {
        if(Browser.equalsIgnoreCase("chrome"))
        {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        else if (Browser.equalsIgnoreCase("firefox"))
        {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        else if (Browser.equalsIgnoreCase("safari"))
        {
            WebDriverManager.safaridriver().setup();
            driver = new SafariDriver();
        }
        else
        {
            throw new RuntimeException("Not a valid browser");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
        driver.manage().window().maximize();
        driver.get(config.getApplicationUrl(URL));
        String ActualTitle = driver.getTitle();
        Assert.assertEquals(ActualTitle,ExpectedTitle,"Title is matching");
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

}