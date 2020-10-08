package br.com.elpidio.tests;

import org.junit.Assert;
import org.junit.Test;

import br.com.elpidio.core.BaseTest;
import br.com.elpidio.pages.HomePage;
import br.com.elpidio.pages.MenuPage;

public class SaldoTest extends BaseTest {
	private HomePage homePage = new HomePage();
	private MenuPage menu = new MenuPage();
	@Test
	public void testSaldoConta() {
		menu.acessarHome();
		Assert.assertEquals("534.00", homePage.obterSaldoConta("Conta para saldo"));
	}
}
