package ehc;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.internal.seleniumemulation.WaitForPageToLoad;

public class EhcTests {

	private WebDriver driver;
	//private String baseUrl = "http://ehcserver.localhost/";
	private String baseUrl = "http://ehcserver.jochen-bauer.net/";
	
	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.get(baseUrl);
	}
	
	@Test
	public void testIndex(){ // done
		String cssSelectorString = "h1";
		WebElement contentH1 = driver.findElement(By.cssSelector(cssSelectorString));
		String act = contentH1.getText();
		String exp = "EHC-Webanwendung";
		assertEquals("EhcTests.testIndex()", exp, act);
	}
	
	@Test
	public void testTemp(){ // done
		String idString = "temp";
		WebElement linkTemp = driver.findElement(By.id(idString));
		linkTemp.click();
		String cssSelectorString2 = "h1";
		WebElement contentH1 = driver.findElement(By.cssSelector(cssSelectorString2));
		String act = contentH1.getText();
		String exp = "Kontrollausgaben";
		assertEquals("EhcTests.testTemp()", exp, act);
	}
	
	@Test
	public void testLogin(){ // TODO
		// fail("Not yet implemented!");
		String enterLinkClassName = "btn-large";
		List<WebElement> largeButtons = driver.findElements(By.className(enterLinkClassName));
		WebElement largeButton = largeButtons.get(0);
		largeButton.click();
		
		//String cssSelectorString2 = "h1";
		//WebElement contentH1 = driver.findElement(By.cssSelector(cssSelectorString2));
		String act = "Kontrollausgaben";
		String exp = "Kontrollausgaben";
		assertEquals("EhcTests.testTemp()", exp, act);
		
	}
	
	@Test
	public void testTest(){ // done
		String cssSelectorString = "h1";
		WebElement contentH1 = driver.findElement(By.cssSelector(cssSelectorString));
		String act = contentH1.getText();
		String exp = "Willkommen zu Hause!";
		assertEquals("EhcTests.testTest()", exp, act);
	}
		
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
	
}
