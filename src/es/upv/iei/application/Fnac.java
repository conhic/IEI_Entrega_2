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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class Fnac {

    private String exePath;
    private WebDriver driver;

    public Fnac(){}

    public void find(String model_name) {
        String exePath = "/Users/path/Downloads/chromedriver";
        System.setProperty("webdriver.chrome.driver", exePath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);

        String base_url = "http://busqueda.tv-informatica-telefonia-foto.fnac.es/Smartphones-Libres/";
        String url;
        switch (model_name){
            case "LG":
                url = base_url + "Smartphones-LG/n39894";
                driver.get(url);
            break;

            case "SAMSUNG":
                url = base_url + "Smartphones-Samsung/n39889";
                driver.get(url);
            break;

            case "SONY":
                url = base_url + "Smartphones-Sony/n39895";
                driver.get(url);
            break;

            case "HUAWEI":
                url = base_url + "Smartphones-Huawei/n39896";
                driver.get(url);
            break;

            case "MOTOROLA":
                url = base_url + "Smartphones-Lenovo-Motorola/n40116";
                driver.get(url);
            break;

            case "LENOVO":
                url = base_url + "Smartphones-Lenovo-Motorola/n40116";
                driver.get(url);
                break;

            case "APPLE":
                url = base_url + "Smartphones-Apple/n39890";
                driver.get(url);
            break;

            default:
            break;
        }

        // Paso 1 cerrar Cookies
        driver.findElement(By.xpath("//*[@id=\"htmlPopinCookies\"]/p/i")).click();


        // Paso 2 Pasar por todas las páginas de resultado y recoger todos los móviles
        WebElement more_button = driver.findElement(By.xpath("/html/body/div[4]/div/div[3]/div[2]/span[2]/ul/li[3]/a"));

        Actions actions = new Actions(driver);
        actions.moveToElement(more_button);
        actions.perform();

        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,100)", "");

        String price="";

        while(more_button.isDisplayed()){
            try {
                WaitForAjax();
                //coger parametros de los moviles por cada pagina
                ArrayList<WebElement> results = new ArrayList<>();
                results.addAll(driver.findElements(By.className("Article-itemGroup")));

                for (int i=0; i<results.size(); i++)
                {
                    WebElement current_element = results.get(i); // elemento actual de la lista

                    String name = current_element.findElement(By.xpath(".//div[2]/div/p[1]/a")).getText();

                    try {
                         price = current_element.findElement(By.xpath(".//div[3]/div/div[2]/div/div[1]/a")).getText();
                    }catch(Exception e){}

                    System.out.println("name: " + name + "price: " + price);
                }
                //next page
                more_button.click();
            }catch(Exception e) {}
        }
        driver.quit();
    }

    public void WaitForAjax() throws InterruptedException {
        while (true) {
            Boolean ajaxIsComplete = (Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active == 0");
            if (ajaxIsComplete) {
                break;
            }
            Thread.sleep(300);
        }
    }
}
