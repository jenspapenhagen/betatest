
import beta.server.assist.GeneratedAddress;
import beta.server.assist.Name;
import beta.server.assist.NameGenerator;

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
    public void testM() {
        Name makeName = gen.makeName();
        assertThat(makeName).as("null").isNotNull();
        assertThat(makeName.getFirst()).as("firstname to short").isNotBlank();
        assertThat(makeName.getLast()).as("lastname to short").isNotBlank();
        assertThat(makeName.getGender()).as("no gender").isNotNull();

    }

}
