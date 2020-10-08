package br.ce.elpidio.test;
import static br.ce.elpidio.core.DriverFactory.getDriver;
import static br.ce.elpidio.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.ce.elpidio.core.DSL;

public class TesteAjax {

	private DSL dsl;

	@Before
	public void inicializa() {
		getDriver().get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml");
		dsl = new DSL();

	}

	@After
	public void finaliza() {
		killDriver();
	}
	
	@Test
	public void testAjax() {
		dsl.escreve("j_idt727:name","Teste");
		dsl.clicarBotao("j_idt727:j_idt730");
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		//wait.until(ExpectedConditions.textToBe(By.id("j_idt727:display"), "Teste"));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("j_idt805_start")));
		Assert.assertEquals("Teste", dsl.obterTexto("j_idt727:display"));
	}
}
