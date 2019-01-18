/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.server.test;

import org.junit.Test;

import beta.server.entity.Communication;

import static beta.server.entity.Communication.Type.EMAIL;
import static beta.server.entity.Communication.Type.PHONE;
import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 * @author jens.papenhagen
 */
public class CommunicationTest {

    public static Communication makeValidCommunication() {
        Communication validCommunication = new Communication(EMAIL, "max.mustermann@gmail.com");
        assertThat(validCommunication.getViolationMessage()).as("valid Communication").isNull();
        return validCommunication;
    }

    @Test
    public void testValidMessage() {
        Communication makeValidCommunication = makeValidCommunication();
        assertThat(makeValidCommunication.getViolationMessage()).as("Communication with valid values").isNull();
    }

    @Test
    public void testValidEmail() {
        Communication makeValidCommunication = makeValidCommunication();
        makeValidCommunication.setType(EMAIL);
        makeValidCommunication.setIdentifier("test@test.de");
        assertThat(makeValidCommunication.getViolationMessage()).as("Communication have a vaild email").isNull();
    }

    @Test
    public void validPhoneNumber() {
        Communication makeValidCommunication = makeValidCommunication();
        makeValidCommunication.setType(PHONE);
        makeValidCommunication.setIdentifier("040 1234567");
        assertThat(makeValidCommunication.getViolationMessage()).as("Communication with valid phonenumber").isNull();
    }

    @Test
    public void testNonValidEmail() {
        Communication makeInvalidCommunication = makeValidCommunication();
        makeInvalidCommunication.setType(EMAIL);
        makeInvalidCommunication.setIdentifier("falscheemail@@test.de");
        assertThat(makeInvalidCommunication.getViolationMessage()).as("Communication with nonvalid email").isNotBlank();
    }

    @Test
    public void testNonValidPhonenumber() {
        Communication makeInvalidCommunication = makeValidCommunication();
        makeInvalidCommunication.setType(PHONE);
        makeInvalidCommunication.setIdentifier("0123586Buchstaben");
        assertThat(makeInvalidCommunication.getViolationMessage()).as("Communication with nonvalid phonenumber").isNotBlank();
    }

    @Test
    public void phoneNumbers() {
        String phonePattern = Communication.PHONE_PATTERN;
        assertThat(phonePattern).as("phonePattern").isNotBlank();
        assertThat("012 345").matches(phonePattern);
        assertThat("  012 345").doesNotMatch(phonePattern);
        assertThat("  012 345  ").doesNotMatch(phonePattern);
        assertThat("012 345   ").doesNotMatch(phonePattern);
        assertThat("0123 12345").matches(phonePattern);

        assertThat("0049 123 12345").matches(phonePattern);
        assertThat("+49 (123) 12345").matches(phonePattern);

        assertThat("+49 (123) 12345").matches(phonePattern);
    }

}

