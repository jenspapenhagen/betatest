package beta.server.test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * First IntegrationPreTest
 *
 * @author jens papenhagen
 */
import beta.server.itest.WebIT;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class IntegrationPreTest {

    @Test
    public void createDeployment() {
        Assertions.assertThat(WebIT.createDeployment()).isNotNull();
    }

}
