package beta.server.test;

import org.junit.Before;
import org.junit.Test;

import beta.server.entity.*;
import beta.server.assist.Generator;
import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 * @author lucas.huelsen
 */
public class ContactTest {

    private Contact contact;

    private Generator GEN = new Generator();

    @Before
    public void executeBeforeEach() {
        contact = GEN.makeContact();
    }

    @Test
    public void testGetViolationMessages() {
        assertThat(contact.getViolationMessage()).as("Contact with valid values").isNull();
    }

    @Test
    public void testGetViolationMessagesNonValid() {
        contact.setLastName("");
        assertThat(contact.getViolationMessage()).as("Contact without lastName").isNotBlank();
    }

    @Test
    public void testGetViolationMessagesNonValid2() {
        contact.getAddresses().add(new Address());
        assertThat(contact.getViolationMessage()).as("Contact with invalid address").isNotBlank();
    }


}