package beta.server.test;


import beta.server.assist.GeneratedAddress;
import beta.server.assist.NameGenerator;
import beta.server.entity.Contact;

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Testing NameGenerator
 *
 * @author jens.papenhagen
 */
public class TestNameGenerator {

    private NameGenerator gen = new NameGenerator();

    @Test
    public void testMakeAddress() {
        GeneratedAddress makeAddress = gen.makeAddress();
        assertThat(makeAddress).as("null").isNotNull();
    }

    @Test
    public void testMakeCompanyName() {
        String makeCompanyName = gen.makeCompanyName();
        assertThat(makeCompanyName).as("null").isNotNull();
        assertThat(makeCompanyName.length() != 0).as("to short").isTrue();
    }

    @Test
    public void testMakeName() {
        Contact makeName = gen.makeName();
        assertThat(makeName).as("null").isNotNull();
        assertThat(makeName.getFirstName()).as("firstname to short").isNotBlank();
        assertThat(makeName.getLastName()).as("lastname to short").isNotBlank();
        assertThat(makeName.getSex()).as("no gender").isNotNull();

    }

}
