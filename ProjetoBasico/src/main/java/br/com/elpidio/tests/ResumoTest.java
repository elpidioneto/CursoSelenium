package br.com.elpidio.tests;

import static br.com.elpidio.core.DriverFactory.getDriver;

import org.junit.Assert;
import org.junit.Test;

import br.com.elpidio.core.BaseTest;
import br.com.elpidio.pages.MenuPage;
import br.com.elpidio.pages.ResumoPage;

public class ResumoTest extends BaseTest {

	private MenuPage menuPage = new MenuPage();
	private ResumoPage resumoPage = new ResumoPage();

	@Test
	public void test1_ExcluirMovimentacao() {
		menuPage.acessarTelaResumo();

		resumoPage.excluirMovimentacao();

		Assert.assertEquals("Movimentação removida com sucesso!", resumoPage.obterMensagemSucesso());
	}

	@Test
	public void test2_ResumoMensal() {
		menuPage.acessarTelaResumo();

		Assert.assertEquals("Seu Barriga - Extrato", getDriver().getTitle());
	}
	
	@Test
	public void test3_MovimentacaoVazia() {
		menuPage.acessarTelaResumo();
		
		resumoPage.selecionarAno("2016");
		resumoPage.buscar();
		
		Assert.assertEquals("", resumoPage.retornarTextoTabela());
	}
}
