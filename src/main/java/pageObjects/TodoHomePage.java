package pageObjects;

import dataProviders.ConfigFileReader;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import wrapperMethods.wrapperClass;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class TodoHomePage {
    wrapperClass wrapper;
    WebDriverWait wait ;
    Random random = new Random();
    ConfigFileReader configFileReader = new ConfigFileReader();
    private final int i =0;

    public TodoHomePage(WebDriver driver) {
        PageFactory.initElements(driver, this); wait = new WebDriverWait(driver, Duration.ofSeconds(7)) ;
    }

    @FindBy(how = How.CLASS_NAME,using = "new-todo")
    private WebElement txtbx_TODO;

    @FindBy(how = How.XPATH,using = "//input[@type=\"checkbox\" and @class=\"toggle\"][1]")
    private WebElement select_FIRSTTODO;

    @FindBy(how = How.XPATH,using = "//li[@class=\"todo\"]//label")
    private WebElement first_TODO;

    @FindBy(how = How.XPATH,using = "//li[@class=\"todo editing\"]")
    private WebElement edit_TODO;

    @FindBy(how = How.XPATH,using = "//li[@class=\"todo completed editing\"]")
    private WebElement edit_CompletedTODO;

    @FindBy(how = How.XPATH,using = "//li[@class=\"todo editing\"]/input")
    private WebElement input_EDITTODO;

    @FindBy(how = How.XPATH,using = "//li[@class=\"todo completed editing\"]/input")
    private WebElement input_EDITCOMPLETETODO;

    @FindBy(how = How.XPATH,using = "//button[@class=\"destroy\"][1]")
    private WebElement delete_FIRSTTODO;

    @FindBy(how = How.XPATH,using = "//label[@for=\"toggle-all\"]")
    private WebElement complete_TODO;

    @FindBy(how = How.CLASS_NAME,using = "clear-completed")
    private WebElement clear_TODO;

    @FindBy(how = How.LINK_TEXT,using = "Active")
    private WebElement active_TODO;

    @FindBy(how = How.LINK_TEXT,using = "Completed")
    private WebElement completed_TODO;

    @FindBy(how = How.LINK_TEXT,using = "All")
    private WebElement all_TODO;

    @FindBy(how = How.XPATH,using = "//li[@class=\"todo\"]")
    private List<WebElement> active_TODOCount;

    @FindBy(how = How.XPATH,using = "//button[@class=\"destroy\"]")
    private List<WebElement> delete_TODOlist;

    @FindBy(how = How.XPATH,using = "//li[@class=\"todo completed\"]")
    private List<WebElement> completed_TODOCount;

    @FindBy(how = How.XPATH,using = "//li[@class=\"todo completed\"]")
    private WebElement completedTODO;

    @FindBy(how =How.XPATH,using="//span[@class=\"todo-count\"]/Strong")
    private WebElement todo_COUNT;

    @FindBy(how =How.XPATH,using="//ul[@class=\"todo-list\"]/li")
    private List<WebElement> all_todo_COUNT;

    public void enter_Name(String name) {
        txtbx_TODO.sendKeys(name);
        txtbx_TODO.sendKeys((Keys.ENTER));
    }

    public void delete_FirstTodo(WebDriver driver) throws InterruptedException {
        wrapper = new wrapperClass(driver);
        wrapper.moveToElement(driver,select_FIRSTTODO);
        Thread.sleep(3000);
        delete_FIRSTTODO.click();
    }

    public void mark_AllTodoCompleted()
    {
        complete_TODO.click();

    }

    public void clear_AllTodoCompleted()
    {
        clear_TODO.click();
    }

    public void show_ActiveTodo()
    {
        active_TODO.click();
    }

    public void show_CompletedTodo()
    {
        completed_TODO.click();
    }

    public void show_allTodo()
    {
        all_TODO.click();
    }
    public int active_TODOCount()
    {
        return active_TODOCount.size();
    }

    public int completed_TODOCount()
    {
       return completed_TODOCount.size();
    }

    public int todo_COUNT()
    {
        if(todo_COUNT.getText().isEmpty())
        return i;
        else
        return Integer.parseInt(todo_COUNT.getText());
    }

    public void completeRandom_TODO(WebDriver driver)
    {
        int todolist = all_todo_COUNT.size();

        int randomTodo = random.nextInt(todolist)+1;
        WebElement random_TODO = driver.findElement(By.xpath("//li["+randomTodo+"]//input[@type=\"checkbox\" and @class=\"toggle\"]"));
        random_TODO.click();
    }

    public void edit_FirstTodo(WebDriver driver) throws InterruptedException {
        wrapper = new wrapperClass(driver);
        wrapper.doubleClickAndDelete(driver, first_TODO);
        wait.until(ExpectedConditions.visibilityOf(edit_TODO));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(configFileReader.getImplicitlyWait()));
        input_EDITTODO.sendKeys(random_Text(),(Keys.ENTER));
    }

    public void completeAndEditRandom_TODO(WebDriver driver) throws InterruptedException {
        int todolist = all_todo_COUNT.size();

        int randomProduct = random.nextInt(todolist)+1;
        WebElement random_TODO = driver.findElement(By.xpath("//li["+randomProduct+"]//input[@type=\"checkbox\" and @class=\"toggle\"]"));
        random_TODO.click();
        wrapper = new wrapperClass(driver);
        wrapper.doubleClickAndDelete(driver, completedTODO);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(configFileReader.getImplicitlyWait()));
        wait.until(ExpectedConditions.visibilityOf(edit_CompletedTODO));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(configFileReader.getImplicitlyWait()));
        input_EDITCOMPLETETODO.sendKeys(random_Text(),(Keys.ENTER));
    }

    public void delete_RandomTodo(WebDriver driver) throws InterruptedException {
        wrapper = new wrapperClass(driver);
        int todolist = all_todo_COUNT.size();
        int randomTodo = random.nextInt(todolist)+1;
        WebElement random_TODO = driver.findElement(By.xpath("//li["+randomTodo+"]//input[@type=\"checkbox\" and @class=\"toggle\"]"));
        wrapper.moveToElement(driver,random_TODO);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(configFileReader.getImplicitlyWait()));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li["+ randomTodo+"]//button[@class=\"destroy\"]"))).click();
    }

    public String random_Text()
    {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 7;
        for(int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }
        String randomString = sb.toString();
        return randomString;
    }

    public void verifyTodoCount() throws InterruptedException {

        int active = active_TODOCount();
        int completed = completed_TODOCount();
        int allTodo = todo_COUNT();
        if(active_TODOCount.size()==0 && completed_TODOCount.size()==0 && todo_COUNT.getText().isEmpty())
        {
            System.out.println("Todo Is empty");
        }
        else if (active_TODOCount.size()>0 && completed_TODOCount.size()==0)
        {
            System.out.println("Active Todo: " + active);
            System.out.println("Items left in Todo: " + allTodo);
        }
        else if (active_TODOCount.size()==0 && completed_TODOCount.size()>0)
        {
            System.out.println("completed Todo: " + completed);
            System.out.println("Items left in Todo: " + allTodo);
        }
        else if (active_TODOCount.size()>0 && completed_TODOCount.size()>0)
        {
            System.out.println("Active Todo: " + active);
            System.out.println("Items left in Todo: " + allTodo);
            System.out.println("Completed Todos: " + completed);
        }

    }
}
