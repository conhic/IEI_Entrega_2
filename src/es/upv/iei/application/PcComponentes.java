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
import java.util.List;

/**
 * Created by Connor on 25/11/2016.
 */
public class PcComponentes implements Filtro{
    private final String website = "https://www.pccomponentes.com/smartphone-moviles-";
    private final String exePath = "C:\\Selenium\\chromedriver_win32\\chromedriver.exe";

    public List<Mobile> find(String model){
        String suffix;

        switch (model){
            case "LG":
                suffix = "lg";
                break;
            case "SAMSUNG":
                suffix = "samsung";
                break;
            case "SONY":
                suffix = "sony";
                break;
            case "HUAWEI":
                suffix = "huawei";
                break;
            case "MOTOROLA":
                suffix = "motorola";
                break;
            case "ONEPLUS":
                suffix = "oneplus";
                break;
            case "LENOVO":
                suffix = "lenovo";
                break;
            case "APPLE":
                suffix = "apple";
                break;
            default:
                throw new Error("Este modelo no existe");
        }

        System.setProperty("webdriver.chrome.driver", exePath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        driver.get(website + suffix);

        // Paso 3 Cerrar la ventana de cookies
        driver.findElement(By.xpath("//*[@id=\"familia-secundaria\"]/div[5]/div/div/div[2]/button")).click();

        // Paso 4 buscar y pulsar el elemento ver más
        JavascriptExecutor jse = (JavascriptExecutor)driver;

        WebElement btnMas = driver.findElement(By.id("btnMore"));
        Actions action = new Actions(driver);

        while(btnMas.isDisplayed()){
            action.moveToElement(btnMas);
            action.perform();

            jse.executeScript("window.scrollBy(0,100)", "");
            btnMas.click();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        //Guardar webelements correspondientes a los móviles de la busqueda
        List<WebElement> mobiles = new ArrayList<>();
        mobiles = driver.findElements(By.className("tarjeta-articulo"));

        //Iterar la lista de móviles y crear una lista de objetos Mobile.
        List<Mobile> result = new ArrayList<>();
        for(WebElement webElement : mobiles){
            WebElement filter = webElement.findElement(By.xpath("div/a[@itemprop='url']"));
            Mobile mobile = new Mobile( filter.getAttribute("data-name"),
                                        filter.getAttribute("data-price"));

            result.add(mobile);
        }

        //Return lista de móviles
        return result;
    }
}
