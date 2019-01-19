package beta.server.itest;

import beta.server.eao.ContactEao;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.assertj.core.api.Assertions;

import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.slf4j.LoggerFactory;

@RunWith(Arquillian.class)
public class WebIT extends ArquillianSeleniumProjectArchive {

    @ArquillianResource
    private URL deploymentUrl;

    @Drone
    private WebDriver browser;

    @FindBy(id = "dataTable")
    private WebElement inText;

    private static final org.slf4j.Logger L = LoggerFactory.getLogger(WebIT.class);

    @Before
    public void startingBrowser() {
        //starting the browser
        browser.get(deploymentUrl.toExternalForm());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            L.error("InterruptedException on sleep {}", ex.getMessage());
        }
    }

    @Test
    @RunAsClient
    public void clickDemo() throws InterruptedException {
        //check if there is an Datatable
        assertThat(inText.isDisplayed()).as("datatable is not displayed").isTrue();
        assertThat(inText.getText()).as("datatableis empty").isNotBlank();
    }

}
