package Compra;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;


public class CompraProduto {
	String baseurl;
	WebDriver driver;
	String pastaprint;
	private StringBuffer verificationErrors = new StringBuffer();

	public void Print(String nomePrint) throws IOException {
		File foto = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(foto, new File("C:\\Eclipse\\TesteInmetrics\\TesteAutomacao\\target\\Evidencias\\"
				+ pastaprint + "\\" + nomePrint + ".png"));

	}

	@Before
	public void Iniciar() {
		baseurl = "https://demo.cs-cart.com";
		System.setProperty("webdriver.chrome.driver",
				"C:\\Eclipse\\TesteInmetrics\\TesteAutomacao\\driver\\chrome\\chromedriver.exe");
		driver = new ChromeDriver();
		pastaprint = new SimpleDateFormat("yyyy-MM-dd HH-mm").format(Calendar.getInstance().getTime());
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
	}

	@After
	public void finalizar() {
		driver.quit();

	}
	
	
	@Given("^usuario acessa o site de ecommerce \"([^\"]*)\"$")
	public void usuario_acessa_o_site_de_ecommerce(String arg1) throws Throwable {
		driver.get(baseurl);
		System.out.println("Passo - 1 Abre o Site");
		Print("Evidencia-01 - Compra - Passo 1 - Home Site");

	}

	@Then("^Clica no menu e seleciona Electronics e depois Laptops e seleciona o modelo desejado$")
	public void clica_no_menu_e_seleciona_Electronics_e_depois_Laptops_e_seleciona_o_modelo_desejado()
			throws Throwable {
		driver.findElement(By.xpath("(//a[contains(text(),'Electronics')])[3]")).click();
		driver.findElement(By.xpath(".//*[@id='category_products_11']/ul/li[1]/a/img")).click();
		driver.findElement(By.xpath("//div[@id='category_products_11']/ul/li[2]/a/span")).click();
		driver.findElement(By.id("det_img_220")).click();
		System.out.println("Passo - 2 Efetua a Busca e seleciona o Produto");
		Print("Evidencia-01 - Compra - Passo 2 - Efetua a Busca e seleciona o Produto");
	}

	@Then("^Efetua a validacao do produto antes da compra$")
	public void efetua_a_validacao_do_produto_antes_da_compra() throws Throwable {
		try {
			assertEquals("Series 7 Chronos 14” Notebook",
					driver.findElement(By.cssSelector("h1.ty-product-block-title > bdi")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals("1,099.99", driver.findElement(By.id("sec_discounted_price_220")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

		System.out.println("Passo - 3 Valida o produto antes da Compra");
		Print("Evidencia-01 - Compra - Passo 3 Valida o produto antes da Compra");
	}

	@And("^clica em Buy Now With (\\d+)-Click$")
	public void clica_em_Buy_Now_With_Click(int arg1) throws Throwable {

		driver.findElement(By.cssSelector("#opener_call_request_220 > span")).click();

		System.out.println("Passo - 4 Seleciona Comprar com 1 Clique");
		Print("Evidencia-01 - Compra - Passo 4 - Comprar com 1 Clique");
	}

	@Then("^usuario preenche os dados \"([^\"]*)\" e  \"([^\"]*)\"$")
	public void usuario_preenche_os_dados_e(String arg1, String arg2) throws Throwable {
		driver.findElement(By.id("call_data_call_request_220_name")).clear();
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		driver.findElement(By.id("call_data_call_request_220_name")).sendKeys("User000Name");
		driver.findElement(By.id("call_data_call_request_220_phone")).click();
		driver.findElement(By.id("call_data_call_request_220_phone")).sendKeys("5511999991234");
		System.out.println("Passo - 5 Preenche os dados");
		Print("Evidencia-01 - Compra - Passo 5 - Preenche os Dados");
	}

	@Then("^clica em Submit$")
	public void clica_em_Submit() throws Throwable {
		driver.findElement(By.xpath(".//*[@id='form_call_request_220']/div[8]/button")).click();
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		try {
			assertEquals("×NoticeWe have received your request! Our manager will contact you soon.",
					driver.findElement(By.xpath("//div[@id='tygh_container']/div[3]/div")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		System.out.println("Passo - 6 Clica em Submit e valida a Compra");
		Print("Evidencia-01 - Compra - Passo 6 Clica em Submit e valida a Compra");
	}

}
