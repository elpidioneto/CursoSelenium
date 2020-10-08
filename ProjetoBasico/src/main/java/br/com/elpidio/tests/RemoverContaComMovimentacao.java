package br.com.elpidio.tests;

import org.junit.Assert;
import org.junit.Test;

import br.com.elpidio.core.BaseTest;
import br.com.elpidio.pages.ContasPage;
import br.com.elpidio.pages.MenuPage;

public class RemoverContaComMovimentacao extends BaseTest {
	MenuPage menuPage = new MenuPage();
	ContasPage contasPage = new ContasPage();
	@Test
	public void testExcluirContaComMovimentacao() {
		menuPage.acessarTelaListarConta();
		
		contasPage.clicarExcluirConta("Conta com movimentacao");
		
		Assert.assertEquals("Conta em uso na movimentações", contasPage.obterMensagemErro());
	}
}
