package br.com.elpidio.tests;

import static br.com.elpidio.utils.DataUtils.obterDataFormatada;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.elpidio.core.BaseTest;
import br.com.elpidio.pages.MenuPage;
import br.com.elpidio.pages.MovimentacaoPage;
import br.com.elpidio.utils.DataUtils;
public class MovimentacaoTest extends BaseTest {
	private MenuPage menuPage = new MenuPage();
	private MovimentacaoPage movPage= new MovimentacaoPage();
	
	@Test
	public void test1_InserirMovimentacao() {
		menuPage.acessarTelaInserirMovimentacao();
		
		movPage.setDataDaMovimentacao(obterDataFormatada(new Date()));
		movPage.setDataDoPagamento(obterDataFormatada(new Date()));
		movPage.setDescricao("Movimentação do teste");
		movPage.setInteressado("Interessado Qualquer");
		movPage.setValor("500");
		movPage.setConta("Conta para movimentacoes");
		movPage.setSituacao("pago");
		movPage.salvar();

		Assert.assertEquals("Movimentação adicionada com sucesso!", movPage.obterMensagemSucesso());
	}
	
	@Test
	public void test2_CamposObrigatorios() {
		menuPage.acessarTelaInserirMovimentacao();
		
		movPage.salvar();
		List<String> erros = movPage.obterErros();
		
		Assert.assertEquals(6, erros.size());
		Assert.assertTrue(erros.containsAll(Arrays.asList(
				"Data da Movimentação é obrigatório","Data do pagamento é obrigatório",
				"Descrição é obrigatório","Interessado é obrigatório",
				"Valor é obrigatório","Valor deve ser um número")));
	}
	
	@Test
	public void test3_InserirMovimentacaoFutura() {
		menuPage.acessarTelaInserirMovimentacao();
		
		Date dataFutura = DataUtils.dataComDiferencaDeDias(5);
		
		movPage.setDataDaMovimentacao(obterDataFormatada(dataFutura));
		movPage.setDataDoPagamento(obterDataFormatada(dataFutura));
		movPage.setDescricao("Movimentação do teste");
		movPage.setInteressado("Interessado Qualquer");
		movPage.setValor("500");
		movPage.setConta("Conta para movimentacoes");
		movPage.setSituacao("pago");
		movPage.salvar();
		
		List<String> erro = movPage.obterErros();

		Assert.assertEquals(1, erro.size());
		Assert.assertTrue(erro.contains("Data da Movimentação deve ser menor ou igual à data atual"));
	}

}
