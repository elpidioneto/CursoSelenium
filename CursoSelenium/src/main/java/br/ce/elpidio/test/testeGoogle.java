package br.ce.elpidio.test;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class testeGoogle {
	private WebDriver driver;

	@Before
	public void inicializa() {
		driver = new FirefoxDriver();
	}

	@After
	public void finaliza() {
		driver.quit();
	}

	@Test
	public void teste() {

		// WebDriver driver = new ChromeDriver();
		//	WebDriver driver = new InternetExplorerDriver();
		driver.get("http://www.google.com");
		Assert.assertEquals("Google", driver.getTitle());
		driver.quit();
	}
}
