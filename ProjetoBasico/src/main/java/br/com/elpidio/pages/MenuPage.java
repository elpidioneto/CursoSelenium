package br.com.elpidio.pages;

import br.com.elpidio.core.BasePage;

public class MenuPage extends BasePage {

	public void acessarTelaInserirConta() {
		clicarLinks("Contas");
		clicarLinks("Adicionar");
	}
	
	public void acessarTelaListarConta() {
		clicarLinks("Contas");
		clicarLinks("Listar");
	}
	
	public void acessarTelaInserirMovimentacao() {
		clicarLinks("Criar Movimentação");
	}
	
	public void acessarTelaResumo() {
		clicarLinks("Resumo Mensal");
	}
	
	public void acessarHome() {
		clicarLinks("Home");
	}
}
