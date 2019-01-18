package beta.server.itest;

import beta.server.eao.ContactEao;
import java.net.URL;
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


@RunWith(Arquillian.class)
public class WebIT extends ArquillianProjectArchive {

//    @Inject
    private ContactEao contactEao;

    @ArquillianResource
    private URL deploymentUrl;

    @Drone
    private WebDriver browser;

    @FindBy(id = "in")
    private WebElement inText;

    @FindBy(id = "demo")
    private WebElement demoLink;

    @Ignore
    @Test
    public void findAll() throws InterruptedException {
        Assertions.assertThat(contactEao.findAll()).as("ContactsEao.findAll()").isNotNull().isNotEmpty();
    }

    @Test
    @RunAsClient
    public void clickDemo() throws InterruptedException {
        browser.get(deploymentUrl.toExternalForm());
        Thread.sleep(1000);
        demoLink.click();
        Thread.sleep(1000);
        assertThat(inText.isDisplayed()).isTrue();
    }

}
