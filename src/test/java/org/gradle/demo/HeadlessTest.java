package org.gradle.demo;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;

import static org.junit.Assert.assertEquals;


public class HeadlessTest {

    @BeforeClass
    public static void setupClass() {
        ChromeDriverManager.getInstance().setup();
    }

    @Test
    public void HeadlessChromeDriverTest() throws IOException {

        //System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("http://localhost:8081/webdemo");

        driver.findElement(By.id("say-hello-text-input")).sendKeys("Dolly");
        driver.findElement(By.id("say-hello-button")).click();

        assertEquals("Hello Page", driver.getTitle());
        assertEquals("Hello, Dolly!", driver.findElement(By.tagName("h2")).getText());

        driver.quit();
    }
}
