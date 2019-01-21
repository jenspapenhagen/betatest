/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.web.presenter;

import beta.server.eao.ContactEao;
import beta.server.entity.Address;
import beta.server.entity.Contact;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller for Contract
 *
 * @author jens.papenhagen
 */
@Named
@ViewScoped
public class ContactPresenter implements Serializable {

    private static final Logger L = LoggerFactory.getLogger(ContactPresenter.class);

    @Inject
    private ContactEao eao;

    private List<Contact> contacts = new ArrayList<>();

    public List<Contact> getContacts() {
        return contacts;
    }

    @PostConstruct
    public void init() {
        if (eao.findAll().isEmpty()) {
            L.error("EAO is empty");
        }
        contacts.addAll(eao.findAll());
        L.debug("Having {} in the Contact List", contacts.size());
    }

    public String getFirstStreet(Contact contact) {
        Address address = contact.getAddresses().get(0);
        if (address == null) {
            return "Keine Straße eingetragen";
        }

        return address.getStreet();
    }

    public String getFirstZipCode(Contact contact) {
        Address address = contact.getAddresses().get(0);
        if (address == null) {
            return "Keine Straße eingetragen";
        }

        return address.getZipCode();
    }
}
