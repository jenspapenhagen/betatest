/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.server.assist;

import java.util.List;
import javax.ejb.Singleton;

import beta.server.entity.Contact;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Diese Classe darf nicht direkt verwendet werden.
 *
 * @author oliver.guenther
 */
@Singleton
public class SingletonDatabase {

    private static final Logger L = LoggerFactory.getLogger(SingletonDatabase.class);

    public List<Contact> allContacts = new ArrayList<>();

    private int contactCount = 200;

    @PostConstruct
    public void init() {
        Generator gen = new Generator();
        for (int i = 0; i < contactCount; i++) {
            L.info("adding");
            allContacts.add(gen.makeContactWithId(i, i, i));
        }
        L.info("allContacts size {}", allContacts.size());

    }

}
