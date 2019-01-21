package beta.server.test;

import org.junit.Before;
import org.junit.Test;

import beta.server.entity.Address;

import static beta.server.entity.Country.GERMANY;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Testing the ViolationMessages
 *
 * @author lucas.huelsen
 */
public class AddressTest {

    private Address address;

    @Before
    public void executedBeforeEach() {
        address = new Address();
    }

    @Test
    public void testGetViolationMessages() {
        Address testAddress = new Address();
        testAddress.setStreet("street");
        testAddress.setCity("city");
        testAddress.setZipCode("12345");
        testAddress.setCountry(GERMANY);

        assertThat(testAddress.getStreet()).as("Address without street").isNotBlank();
        assertThat(testAddress.getStreet()).as("Address is null").isNotNull();
        assertThat(testAddress.getStreet()).as("Address is to short").hasSize(6);
        assertThat(testAddress.getStreet()).as("Address is not the same").isEqualTo("street");
        assertThat(testAddress.getViolationMessage()).as("Address with valid values").isNull();
    }

    @Test
    public void testGetViolationMessagesNonValid() {
        address.setCity("city");
        address.setZipCode("12345");
        address.setCountry(GERMANY);
        assertThat(address.getViolationMessage()).as("Address without street").isNotBlank();
    }

    @Test
    public void testGetViolationMessagesNonValid2() {
        address.setStreet("street");
        address.setZipCode("12345");
        address.setCountry(GERMANY);
        assertThat(address.getViolationMessage()).as("Address without city").isNotBlank();
    }

    @Test
    public void testGetViolationMessagesNonValid3() {
        address.setStreet("street");
        address.setCity("city");
        address.setCountry(GERMANY);
        assertThat(address.getViolationMessage()).as("Address without zipcode").isNotBlank();
    }

    @Test
    public void noCountrySet() {
        address.setStreet("street");
        address.setCity("city");
        address.setZipCode("12345");
        address.setIsoCountry(null);
        assertThat(address.getViolationMessage()).as("Address without Iso Country").isNotBlank();
    }

}
