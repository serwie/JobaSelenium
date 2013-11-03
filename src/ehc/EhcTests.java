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
	// private String baseUrl = "http://ehcserver.localhost/";
	private String baseUrl = "http://ehcserver.jochen-bauer.net/";

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.get(baseUrl);
	}

	@Test
	public void testIndex() { // done
		String cssSelectorString = "h1";
		WebElement contentH1 = driver.findElement(By
				.cssSelector(cssSelectorString));
		String act = contentH1.getText();
		String exp = "EHC-Webanwendung";
		assertEquals("EhcTests.testIndex()", exp, act);
	}

	@Test
	public void testTemp() { // TODO
		String idString = "temp";
		WebElement linkTemp = driver.findElement(By.id(idString));
		linkTemp.click();
		String cssSelectorString2 = "h1";
		WebElement contentH1 = driver.findElement(By
				.cssSelector(cssSelectorString2));
		String act = contentH1.getText();
		String exp = "Kontrollausgaben";
		assertEquals("EhcTests.testTemp()", exp, act);
	}

	/**
	 * Methode, um zu überprüfen, ob man nach dem Drücken des "Eintreten"
	 * Buttons auf der Login Seite landet.
	 */
	@Test
	public void testLoginButton(){ // done
		// fail("Not yet implemented!");
		// Suchen aller großen Knöpfe auf der Seite
		String enterLinkClassName = "btn-large";
		List<WebElement> largeButtons = driver.findElements(By
				.className(enterLinkClassName));
		WebElement largeButton = largeButtons.get(0);

		// Klicken auf den ersten Knopf (der Knopf "Eintreten")
		largeButton.click();

		// Überprüfen, ob man danach auf der LogIn Seite landet
		String cssSelectorString2 = "h1";
		WebElement contentH1 = driver.findElement(By
				.cssSelector(cssSelectorString2));
		String act = contentH1.getText();
		String exp = "Einloggen";

		assertEquals("EhcTests.testTemp()", exp, act);

	}

	@Test
	public void testRightLogIn() { // done
		/**
		 * Methode, um zu überprüfen, ob man nach der Eingabe der RICHTIGEN
		 * Login Daten im Cockpit Menue landet.
		 */

		// Suchen aller großen Knöpfe auf der Seite
		String enterLinkClassName = "btn-large";
		List<WebElement> largeButtons = driver.findElements(By
				.className(enterLinkClassName));
		WebElement largeButton = largeButtons.get(0);

		// Klicken auf den ersten Knopf (der Knopf "Eintreten")
		largeButton.click();

		// Man befindet sich nun auf der Seite, wo man die Email und das
		// Passwort eingeben muss

		// Finden des Email Elements
		WebElement email = driver.findElement(By.name("identity"));
		// Eingeben der Email
		email.sendKeys("guest@jochen-bauer.net");

		// Finden des Passwort Elements
		WebElement passwort = driver.findElement(By.name("credential"));
		// Eingeben des Passworts
		passwort.sendKeys("geheim");

		// Drücken auf das "Eingabe" Feld
		passwort.submit();

		// Hier sollte man eingeloggt sein (Überprüfung über h1)
		String cssSelectorString = "h1";

		WebElement h1Title = driver.findElement(By
				.cssSelector(cssSelectorString));

		String act = h1Title.getText();
		String exp = "Cockpit";

		assertEquals("LogIn Test", exp, act);

	}

	@Test
	public void testFalseLogIn(){ // done
		/**
		 * Methode, um zu überprüfen, dass man nach der Eingabe der FALSCHEN
		 * Login Daten NICHT im Cockpit Menue landet.
		 */

		// Suchen aller großen Knöpfe auf der Seite
		String enterLinkClassName = "btn-large";
		List<WebElement> largeButtons = driver.findElements(By
				.className(enterLinkClassName));
		WebElement largeButton = largeButtons.get(0);

		// Klicken auf den ersten Knopf (der Knopf "Eintreten")
		largeButton.click();

		// Man befindet sich nun auf der Seite, wo man die Email und das
		// Passwort eingeben muss

		// Finden des Email Elements
		WebElement email = driver.findElement(By.name("identity"));
		// Eingeben der Email
		email.sendKeys("someone@somewhere.net");

		// Finden des Passwort Elements
		WebElement passwort = driver.findElement(By.name("credential"));
		// Eingeben des Passworts
		passwort.sendKeys("passwort");

		// Drücken auf das "Eingabe" Feld
		passwort.submit();

		// Hier sollte man eingeloggt sein (Überprüfung über h1)
		String cssSelectorString = "h1";

		WebElement h1Title = driver.findElement(By
				.cssSelector(cssSelectorString));

		String act = h1Title.getText();
		String exp = "Cockpit";

		boolean unequal = false;

		if (act.compareTo(exp) != 0) {
			unequal = true;
		}

		assertTrue(unequal);

	}

	@Test
	public void testTest() { // done
		String cssSelectorString = "h1";
		WebElement contentH1 = driver.findElement(By
				.cssSelector(cssSelectorString));
		String act = contentH1.getText();
		String exp = "Willkommen zu Hause!";
		assertEquals("EhcTests.testTest()", exp, act);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

}
