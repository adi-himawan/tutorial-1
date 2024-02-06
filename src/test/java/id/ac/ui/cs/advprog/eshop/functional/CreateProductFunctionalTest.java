package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class CreateProductFunctionalTest {
    /**
     * The port number assigned to the running application during test execution.
     * Set automatically during each test run by Spring Framework's test context.
     */
    @LocalServerPort
    private int serverPort;

    /**
     * The base URL for testing. Default to {@code http://localhost}.
     */
    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void createAndFindProduct_fromHomePage(ChromeDriver driver) throws Exception {
        driver.get(baseUrl + "/product");

        // Membuka halaman create product dari halaman home.
        driver.findElement(By.id("productList")).click(); 
        driver.findElement(By.id("createProduct")).click(); 

        // Mengisi form create product lalu menekan tombol submit.
        driver.findElement(By.id("nameInput")).sendKeys("Sampo Cap Bambang");
        driver.findElement(By.id("quantityInput")).sendKeys(String.valueOf(100));
        driver.findElement(By.id("submit")).click();

        List<String> dataRows = extractTableRowData(driver);

        // Memeriksa apakah halaman product list menampilkan data serta button yang tepat.
        assertEquals("Sampo Cap Bambang", dataRows.get(0));
        assertEquals("100", dataRows.get(1));
        assertEquals("Edit", dataRows.get(2));
        assertEquals("Delete", dataRows.get(3));
    }

    List<String> extractTableRowData(WebDriver driver) {
        List<String> dataRows = new ArrayList<>();
        
        // Find all rows.
        List<WebElement> rows = driver.findElements(By.xpath("//tr"));

        for (WebElement row: rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));

            // Extract data from each cell.
            for (WebElement cell: cells) {
                dataRows.add(cell.getText());
            }
        }
        return dataRows;
    }
}
