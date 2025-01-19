package tests;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import core.DSL;
import core.DriverFactory;
import page.FormularioPage;

public class TesteCadastrar {

	FormularioPage page;
	WebDriver driver;
	DSL dsl;

	public TesteCadastrar() {
		// TODO Auto-generated constructor stub
	}

	@Before
	public void inicializa() {

		DriverFactory.getDriver()
				.get("file://" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		page = new FormularioPage();
		dsl = new DSL();

	}

	@After
	public void finaliza() {

		DriverFactory.killDriver();
	}

	@Test
	public void deveRealizarCadastroComSucesso() {

		page.setNome("Wagner");
		page.setSobrenome("Costa");
		page.setSexoMasculino();
		page.setComidaPizza();
		page.setEscolaridade("Mestrado");
		page.setEsporte("Natacao");
		page.sugestoes("Comida = Lasanha " + "Esporte = JIUJITSU");
		page.cadastrar();

		Assert.assertEquals("Cadastrado!", page.obterResultadoCadastro());
		Assert.assertEquals("Wagner", page.obterNomeCadastro());
		Assert.assertEquals("Costa", page.obterSobrenomeCadastro());
		Assert.assertEquals("Masculino", page.obterSexoCadastro());
		Assert.assertEquals("Pizza", page.obterComidaCadastro());
		Assert.assertEquals("mestrado", page.obterEscolaridadeCadastro());
		Assert.assertEquals("Natacao", page.obterEsportesCadastro());
		Assert.assertEquals("Comida = Lasanha " + "Esporte = JIUJITSU", page.obterSugestaoCadastro());
	}

	/****** Validar as regras do Formulario ******/

	@Test
	public void validarnomeObrigatorio() {

		page.setSobrenome("Macedo");
		page.cadastrar();
		Assert.assertEquals("Nome eh obrigatorio", dsl.alertaObterTexto());

	}

	@Test
	public void validarSobrenomeObrigatorio() {

		page.setNome("Vladimir");
		page.cadastrar();
		Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTexto());

	}

	@Test
	public void validarSexoObrigatorio() {

		page.setNome("Vladimir");
		page.setSobrenome("Macedo");
		page.cadastrar();
		Assert.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTexto());

	}

	@Test
	public void validarComidaFavorita() {

		page.setNome("Vladimir");
		page.setSobrenome("Macedo");
		page.setSexoMasculino();
		page.setComidaCarne();
		page.setComidaVegetariano();
		page.cadastrar();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTexto());

	}

	@Test
	public void validarEsportes() {

		page.setNome("Vladimir");
		page.setSobrenome("Macedo");
		page.setSexoMasculino();
		page.setComidaCarne();
		page.setEsporte("Futebol", "O que eh esporte?");
		page.cadastrar();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTexto());
	}
}

/************************* Regras ************************/
/*
 * Campo "Nome" - "Nome eh obrigatorio" Campo "Sobrenome" -
 * "Sobrenome eh obrigatorio" Radio "Sexo" obrigatório - "Sexo eh obrigatorio"
 * Check "Comida favorita" (Carne + Vegetariano) -
 * "Tem certeza que voce eh vegetariano?" Esporte (Karate + O que eh esporte?) -
 * "Voce faz esporte ou nao?"
 */
