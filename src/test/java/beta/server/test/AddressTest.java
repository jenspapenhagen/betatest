package beta.server.test;


import org.junit.Before;
import org.junit.Test;

import beta.server.entity.Address;

import static beta.server.entity.Country.GERMANY;
import static org.assertj.core.api.Assertions.assertThat;

/**
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
        Address address = new Address();
        address.setStreet("street");
        address.setCity("city");
        address.setZipCode("12345");
        address.setCountry(GERMANY);
//        Assert.assertEquals("Address with valid values","streee",address.getStreet());
        
        
      assertThat(address.getStreet()).isNotNull().isNotBlank().hasSize(6).isEqualTo("street");
      assertThat(address.getViolationMessage()).as("Address with valid values").isNull();
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
