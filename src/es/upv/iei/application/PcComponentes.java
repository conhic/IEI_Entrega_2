package es.upv.iei.application;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

/**
 * Created by Connor on 25/11/2016.
 */
public class PcComponentes {
    private String exePath;

    public PcComponentes(String exePath){
        this.exePath = exePath;
    }

    public void find(){
        System.setProperty("webdriver.chrome.driver", exePath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        driver.get("http://www.pccomponentes.com");

        //Paso 1 introducir la cadena de búsqueda
        String searchText = "Móviles\n";
        WebElement searchInputBox = driver.findElement(By.name("query"));
        searchInputBox.sendKeys(searchText);

        //Paso 2 esperar a los resultados de búsqueda
        WebDriverWait waiting = new WebDriverWait(driver, 10);
        waiting.until(ExpectedConditions.presenceOfElementLocated(By.id("resultados-busqueda")));

        // Paso 3 Cerrar la ventana de cookies
        driver.findElement(By.xpath("//*[@id=\"resultados-busqueda\"]/div[5]/div/div/div[2]/button")).click();

        // Paso 4 buscar y pulsar el elemento ver más
        WebElement elementoMas = driver.findElement(By.xpath("//*[@id=\"filterMenuLateral\"]/div/div/div[11]/a"));
        Actions actions = new Actions(driver);
        actions.moveToElement(elementoMas); //moverse hacia ver más
        actions.perform();
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,100)", "");
        elementoMas.click();

        // Paso 5 esperar a que salga el radio botón de LG y hacer scroll
        //waiting = new WebDriverWait(driver, 20);
        //waiting.until( ExpectedConditions.presenceOfElementLocated( By.xpath("//*[@id=\"acc-fil-0\"]/div/ul/li[3]/a")));

        //Paso 5 Pulsar elemento ver más
        WebElement element = driver.findElement(By.xpath("//*[@id=\"acc-fil-0\"]/div/a"));
        actions.moveToElement(element); //moverse hacia ver más
        actions.perform();
        jse.executeScript("window.scrollBy(0,100)", "");
        element.click(); // hacer click

        //Paso 5 Pulsar elemento LG
        element = driver.findElement(By.xpath("//*[@id=\"acc-fil-0\"]/div/ul/li[3]/a"));
        actions = new Actions(driver);
        actions.moveToElement(element); //moverse hacia LG
        actions.perform();
        jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,100)", "");
        element.click(); // hacer click


        // Paso 7 esperar a que muestre los telefonos LG
        waiting = new WebDriverWait(driver, 10);
        waiting.until(ExpectedConditions.presenceOfElementLocated( By.xpath("//a[@data-id='3']")));

        // Paso 8 Obtener todos los elementos que aparecen en la primera página
        ArrayList<WebElement> resultados2= (ArrayList<WebElement>)
                driver.findElements(
                        By.xpath("//*[@id=\"articleListContent\"]/div"));

        System.out.println("Resultados " + resultados2.size());

        // Paso 9 Iterar sobre la lista para obtener las características de los artículos
        WebElement actual_Elemento, navegacion2;
        for (int i=0; i< resultados2.size(); i++)
        {
            actual_Elemento = resultados2.get(i); // elemento actual de la lista.
            System.out.println("Elemento: " + i);
            navegacion2 =actual_Elemento.findElement(By.xpath("./descendant::a"));
            System.out.println("Por navegación2: " + navegacion2.getAttribute("data-name").toString());
            System.out.println("Por navegación2: " + navegacion2.getAttribute("data-price").toString() );
            System.out.println("Qué nodo :" + navegacion2.toString());

            // de forma equivalente a xpath pero con css
            //navegacion2 = actual_Elemento.findElement(By.cssSelector());
            //System.out.println("Qué nodo :" +navegacion2.toString());
            //System.out.println("Por navegación2: " + navegacion2.getAttribute("data-name").toString());
            //System.out.println("Por navegación2: " + navegacion2.getAttribute("data-price").toString() );

            // si está disponible o no, se buscar en tarjeta-articulo__elementos-adicionales
            navegacion2 = actual_Elemento.findElement(By.xpath("//*[@id=\"articleListContent\"]/div/div[1]/article/div/div[3]"));
            System.out.println("Por navegación 2 " + navegacion2.getText()); // el texto indica si está disponible o no
            System.out.println("-------------------------------------------");
        }
    }
}
