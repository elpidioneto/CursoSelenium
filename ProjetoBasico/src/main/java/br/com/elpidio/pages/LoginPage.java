package br.com.elpidio.pages;

import br.com.elpidio.core.BasePage;
import br.com.elpidio.core.DriverFactory;

public class LoginPage extends BasePage {

	public void acessarTelaInicial() {
		DriverFactory.getDriver().get("https://srbarriga.herokuapp.com/login");
	}

	public void setEmail(String email) {
		escreve("email", email);
	}

	public void setSenha(String senha) {
		escreve("senha", senha);
	}

	public void entrar() {
		clicarBotaoPorTexto("Entrar");
	}
	
	public void reset() {
		clicarLinks("reset");
	}

}
