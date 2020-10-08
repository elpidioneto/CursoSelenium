package br.ce.elpidio.test;
import static br.ce.elpidio.core.DriverFactory.getDriver;
import static br.ce.elpidio.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import br.ce.elpidio.core.DSL;


public class TestePrime {
	private DSL dsl;
	
	@Before
	public void inicializa() {
		getDriver().get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml");
		dsl= new DSL();
	}

	@After
	public void finaliza() {
		killDriver();;
	}

	@Test
	public void deveInteragirComPrime() {
		
		dsl.clicarBotao(By.xpath("//input[@id='j_idt726:console:0']/../..//span"));
		Assert.assertTrue(dsl.isRadioMarcado("j_idt726:console:0"));
		
	}
	
	}
