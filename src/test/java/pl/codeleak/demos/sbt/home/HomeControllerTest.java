package pl.codeleak.demos.sbt.home;

import org.junit.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.codeleak.selenium.support.SeleniumTest;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static pl.codeleak.selenium.support.CaseFormat.toLowerUnderscore;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "server.port=9090", webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//@SeleniumTest(baseUrl = "http://localhost:9090")
public class HomeControllerTest {

    private static WebDriver driver;

    private HomePage homePage;

    @BeforeClass
    public static void init() throws MalformedURLException {
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.firefox());
    }
    
    @Before
    public void setUp() throws Exception {
        driver.get("http://localhost:9090");
        homePage = PageFactory.initElements(driver, HomePage.class);
    }

    @Test
    public void containsActuatorLinks() {
        homePage.assertThat()
                .hasActuatorLink("autoconfig", "beans", "configprops", "dump", "env", "health", "info", "metrics", "mappings", "trace")
                .hasNoActuatorLink("shutdown");
    }

    @Test
    @Ignore
    public void failingTest() {
        homePage.assertThat()
                .hasNoActuatorLink("autoconfig");
    }

    public static void shutdown() {
        driver.quit();
    }
}
