package ehc;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class EhcTests {

	private WebDriver driver;
	private String baseUrl = "http://ehcserver.jochen-bauer.net/";
	private EhcTestsConfig config; 

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		config = new EhcTestsConfig();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.get(baseUrl);
	}

	@Test
	public void testIndex() { // test call url with login
		String cssSelectorString = "h1";
		WebElement contentH1 = driver.findElement(By.cssSelector(cssSelectorString));
		String act = contentH1.getText();
		String exp = "Einloggen";
		assertEquals("EhcTests.testIndex()", exp, act);
	}

	@Test
	public void testLogin() { 
		WebElement email = driver.findElement(By.name("identity"));
		email.sendKeys(config.username);
		WebElement passwort = driver.findElement(By.name("credential"));
		passwort.sendKeys(config.password);
		passwort.submit();
		String cssSelectorString = "h1";
		WebElement h1Title = driver.findElement(By.cssSelector(cssSelectorString));
		String act = h1Title.getText();
		String exp = "Willkommen!";
		assertEquals("LogIn Test", exp, act);
	}

	@Test
	public void testLoginFalse(){ // done, // TODO more tests
		WebElement email = driver.findElement(By.name("identity"));
		email.sendKeys("someone@somewhere.net");
		WebElement passwort = driver.findElement(By.name("credential"));
		passwort.sendKeys("passwort");
		passwort.submit();
		String cssSelectorString = "h1";
		WebElement h1Title = driver.findElement(By.cssSelector(cssSelectorString));
		String act = h1Title.getText();
		String exp = "Willkommen!";
		boolean unequal = false;
		if (act.compareTo(exp) != 0) {
			unequal = true;
		}
		assertTrue(unequal);
	}

	@Test
	public void testTest() { // done
		driver.get(baseUrl + "ehometest");
		String cssSelectorString = "h1";
		WebElement contentH1 = driver.findElement(By.cssSelector(cssSelectorString));
		String act = contentH1.getText();
		String exp = "Test";
		assertEquals("EhcTests.testTest()", exp, act);
	}
	
	@Test
	public void testLogout(){ // TODO
		//fail("Not yet implemented!");
		
		driver.manage().window().maximize();
			
			// log in
			WebElement email = driver.findElement(By.name("identity"));
			email.sendKeys(config.username);
			WebElement passwort = driver.findElement(By.name("credential"));
			passwort.sendKeys(config.password);
			passwort.submit();
			
			//scroll down to the button <<Ausloggen>>
			JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("javascript:window.scrollBy(200,600)");
	        
	        // clik on button <<Ausloggen>>
			WebElement btnAusloggen =  driver.findElement(By.linkText("Ausloggen"));
			assertNotNull("testLogout", btnAusloggen); // assert that the button exists
			btnAusloggen.click();
			
			// verify that the log in page  is shown 
			String cssSelectorString = "h1";
			WebElement contentH1 = driver.findElement(By.cssSelector(cssSelectorString));
			String act = contentH1.getText();
			String exp = "Einloggen";
			
			assertEquals("testLogout", exp, act);
	}

	@Test
	public void testEhomeJson(){
		//fail("Not yet implemented!");
		driver.get(baseUrl + "ehomejson");
		String cssSelectorString = "pre";
		WebElement pre = driver.findElement(By.cssSelector(cssSelectorString));
		String exp = "{\"connection\":\"ok\"}";
		String act = pre.getText();
		assertEquals("EhcTests.testEhomeJson()", exp, act);
	}
	
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

}
