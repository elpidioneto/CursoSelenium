package br.ce.elpidio.test;
import static br.ce.elpidio.core.DriverFactory.getDriver;
import static br.ce.elpidio.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ce.elpidio.core.DSL;

public class DesafioComboPrime {
	private DSL dsl;

	@Before
	public void inicializa() {
		getDriver().get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml");
		dsl = new DSL();
	}

	@After
	public void finaliza() {
		killDriver();
	}

	@Test
	public void deveInteragirComCombo() {
		dsl.selecionarComboPrime("j_idt726:console", "Xbox One");
		Assert.assertEquals("Xbox One", dsl.obterTexto("j_idt726:console_label"));
	}
}
