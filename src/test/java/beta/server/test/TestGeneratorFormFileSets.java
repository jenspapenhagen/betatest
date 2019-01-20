package beta.server.test;

import beta.server.assist.GeneratorFormFileSets;
import beta.server.entity.Address;
import beta.server.entity.Contact;

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Testing GeneratorFormFileSets
 *
 * @author jens.papenhagen
 */
public class TestGeneratorFormFileSets {

    private GeneratorFormFileSets gen = new GeneratorFormFileSets();

    @Test
    public void testMakeAddress() {
        Address makeAddress = gen.makeAddress();
        assertThat(makeAddress).as("null").isNotNull();
    }

    @Test
    public void testMakeContact() {
        Contact makeName = gen.makeContact();
        assertThat(makeName).as("null").isNotNull();
        assertThat(makeName.getFirstName()).as("firstname to short").isNotBlank();
        assertThat(makeName.getLastName()).as("lastname to short").isNotBlank();
        assertThat(makeName.getSex()).as("no gender").isNotNull();
    }

}
