package es.upv.iei.application;

import com.google.common.base.Predicate;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

/**
 * Created by Connor on 18/11/2016.
 */
public class Application {
    private final String exePath = "C:\\Selenium\\chromedriver_win32\\chromedriver.exe";

    private PcComponentes pcComponentes = new PcComponentes(exePath);

    public static void main(String[] args){
        Application app = new Application();
        app.pcComponentes.find();
    }
}
