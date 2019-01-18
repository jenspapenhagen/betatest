/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.server.eao;

import beta.server.assist.SingletonDatabase;
import beta.server.entity.Address;
import beta.server.entity.Contact;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ejb.Stateless;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author oliver.guenther
 */
@Stateless
public class ContactEao {

    @Inject
    private SingletonDatabase db;

    private final Logger L = LoggerFactory.getLogger(ContactEao.class);
    
    /**
     * Returns a random contact.
     *
     * @return a random contact.
     */
    public Contact findAny() {
        Random r = new Random();
        int size = db.allContacts().size();
        return db.allContacts().get(r.nextInt(size));
    }

    /**
     * Returns all contacts.
     *
     * @return all contacts.
     */
    public List<Contact> findAll() {
          L.info("Hashcode in findall {} ", System.identityHashCode(db.allContacts()));
        return new ArrayList<>(db.allContacts());
    }

    /**
     * Returns all contacts, within a range.
     *
     * @param start start in the total result
     * @param limit amount to return
     * @return null if the start is bigger than the colection, all contacts,
     * within a range.
     */
    public List<Contact> findAll(int start, int limit) {
        if (start >= db.allContacts.size()) {
            return null;
        }

        return db.allContacts.subList(start, limit);
    }

    /**
     * Searching for Contact with this name first or lastname is not import
     *
     * @param searchString
     * @return a List of Contact with this SearchString
     */
    public List<Contact> find(String searchString) {
        System.out.println("find: " + db.allContacts.size());
        List<Contact> firstNameList = db.allContacts.stream()
                .filter(c -> c.getFirstName().contains(searchString))
                .collect(Collectors.toList());

        List<Contact> lastNameList = db.allContacts.stream()
                .filter(c -> c.getLastName().contains(searchString))
                .collect(Collectors.toList());

        db.allContacts.stream().flatMap(c -> c.getAddresses().stream().filter(a -> a.getStreet().contains(searchString)));

        Map<Contact, List<Address>> collect = db.allContacts.stream().collect(Collectors.toMap(c -> c,
                c -> c.getAddresses().stream().filter(a -> a.getStreet().contains(searchString))
                        .collect(Collectors.toList())
        ));

        System.out.println("size collect" + collect.size());

        List<Contact> collect1 = collect.entrySet().stream()
                .filter(v -> !v.getValue().isEmpty())
                .map(c -> c.getKey())
                .collect(Collectors.toList());
        System.out.println("size collect1" + collect1.size());

        //return the lastNameList or the compain of the 2 List
        if (firstNameList.isEmpty()) {
            return lastNameList;
        } else {
            return Stream.concat(firstNameList.stream(), lastNameList.stream())
                    .collect(Collectors.toList());
        }
    }

}
