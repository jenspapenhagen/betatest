/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.server.controller;

import beta.server.eao.ContactEao;
import beta.server.entity.Contact;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jens.papenhagen
 */
@Named
@ViewScoped
public class ContactDynamicController implements Serializable {

    private static final Logger L = LoggerFactory.getLogger(ContactDynamicController.class);

    @Inject
    private ContactEao eao;

    private Contact contact;

    private TreeNode root;

    @PostConstruct
    public void init() {
        L.info("init");
        contact = eao.findAny();
        if (contact == null) {
            L.error("Contact is null");
        }

        root = new DefaultTreeNode("Root", null);
        TreeNode node0 = new DefaultTreeNode(contact.getSex(), root);
        TreeNode node1 = new DefaultTreeNode("Node 1", root);

        //the title of the Root Node
        root.getChildren().add(new DefaultTreeNode(contact.toFullName()));
    }

    public TreeNode getRoot() {
        return root;
    }

}
