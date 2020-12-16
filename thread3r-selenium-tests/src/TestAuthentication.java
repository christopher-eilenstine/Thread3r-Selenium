import java.util.regex.Pattern;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TestAuthentication {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	System.setProperty("webdriver.chrome.driver", "/Users/ceilenstine/Eclipse/workspace-thread3r-selenium/chromedriver");
    driver = new ChromeDriver();
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testLoginPage() throws Exception {
    driver.get("http://csci4830.ddns.net:8080/thread3r-frontend/");
    driver.findElement(By.linkText("Login")).click();
    driver.findElement(By.name("username")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("ceilenstine");
    Thread.sleep(1000);
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("password");
    Thread.sleep(1000);
    driver.findElement(By.xpath("//div[@id='root']/div/div/div/div/form/div[3]/button/span")).click();
    Thread.sleep(2000);
    driver.findElement(By.linkText("Log Out")).click();
    Thread.sleep(1000);
  }
  
  @Test
  public void testRegisterPageUsernameTaken() throws Exception {
	driver.get("http://csci4830.ddns.net:8080/thread3r-frontend/");
	driver.findElement(By.linkText("Register")).click();
	driver.findElement(By.name("username")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("ceilenstine");
	Thread.sleep(1000);
	driver.findElement(By.name("email")).clear();
	driver.findElement(By.name("email")).sendKeys("ceilenstine@unomaha.edu");
	driver.findElement(By.name("password")).clear();
	driver.findElement(By.name("password")).sendKeys("password");
	Thread.sleep(1000);
	driver.findElement(By.xpath("//div[@id='root']/div/div/div/div/form/div/div[4]/button")).click();
	Thread.sleep(2000);
  }
  
  @Test
  public void testRegisterPageEmailTaken() throws Exception {
	Random random = new Random();
	driver.get("http://csci4830.ddns.net:8080/thread3r-frontend/");
	driver.findElement(By.linkText("Register")).click();
	driver.findElement(By.name("username")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("user" + random.nextInt(Integer.MAX_VALUE));
	Thread.sleep(1000);
	driver.findElement(By.name("email")).clear();
	driver.findElement(By.name("email")).sendKeys("ceilenstine@unomaha.edu");
	driver.findElement(By.name("password")).clear();
	driver.findElement(By.name("password")).sendKeys("password");
	Thread.sleep(1000);
	driver.findElement(By.xpath("//div[@id='root']/div/div/div/div/form/div/div[4]/button")).click();
	Thread.sleep(2000);
  }
  
  @Test
  public void testRegisterPageSuccessful() throws Exception {
	Random random = new Random();
	String username = "user" + random.nextInt(Integer.MAX_VALUE);
	driver.get("http://csci4830.ddns.net:8080/thread3r-frontend/");
	driver.findElement(By.linkText("Register")).click();
	driver.findElement(By.name("username")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys(username);
	Thread.sleep(1000);
	driver.findElement(By.name("email")).clear();
	driver.findElement(By.name("email")).sendKeys(username + "@gmail.com");
	driver.findElement(By.name("password")).clear();
	driver.findElement(By.name("password")).sendKeys("password");
	Thread.sleep(1000);
	driver.findElement(By.xpath("//div[@id='root']/div/div/div/div/form/div/div[4]/button")).click();
	Thread.sleep(2000);
    driver.findElement(By.linkText("Login")).click();
    driver.findElement(By.name("username")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys(username);
    Thread.sleep(1000);
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("password");
    Thread.sleep(1000);
    driver.findElement(By.xpath("//div[@id='root']/div/div/div/div/form/div[3]/button/span")).click();
    Thread.sleep(2000);
    driver.findElement(By.linkText("Log Out")).click();
    Thread.sleep(1000);
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
