/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.server.itest.eao;

import beta.server.eao.ContactEao;
import beta.server.entity.Contact;
import beta.server.itest.ArquillianProjectArchive;
import beta.server.itest.Utils;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.junit.Arquillian;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * ContactEao test
 *
 * @author jens papenhagen
 */
@RunWith(Arquillian.class)
public class ContactEaoIT extends ArquillianProjectArchive {

    @Inject
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    @Inject
    private ContactEao eao;

    @After
    public void teardown() throws Exception {
        utx.begin();
        em.joinTransaction();
        Utils.clearH2Db(em);
        utx.commit();
    }

    /*
    //TODO
     * not working jet because there is no persistence-unit in this project
     */
    //@Test
    public void testFindAny() {
        Contact contact = eao.findAny();
        assertThat(contact).as("Contact is null").isNotNull();
    }

    //@Test
    public void testFindAll() {
        List<Contact> findAll = eao.findAll();
        assertThat(findAll).as("Contact List is null").isNotNull();
        assertThat(findAll).as("Contact List is empty").isNotEmpty();
    }

}
