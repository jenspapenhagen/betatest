/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.server.itest;

import java.util.List;
import javax.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utils for handling the in-memory db H2 more infos:
 * https://en.wikipedia.org/wiki/H2_(DBMS)
 *
 * @author jens papenhagen
 */
public class Utils {

    private static final Logger L = LoggerFactory.getLogger(Utils.class);

    /**
     * Clears the content of one or more H2 database, keeping the structure and
     * resetting all sequences. This method uses H2 native SQL commands. Tran
     *
     * @param ems the entitymanager of the database.
     */
    public static void clearH2Db(EntityManager... ems) {

        if (ems == null) {
            L.info("No entitymanagers supplierd, ignoring clear");
            return;
        }

        for (EntityManager em : ems) {
            L.info("Clearing EntityManager {}", em);
            L.debug("Disabing foraign key constraints");
            em.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();

            List<String> tables = em.createNativeQuery("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES where TABLE_SCHEMA='PUBLIC'").getResultList();
            tables.stream().forEach((table) -> {
                L.debug("Truncating Table {}", table);
                em.createNativeQuery("TRUNCATE TABLE " + table).executeUpdate();
            });

            List<String> sequences = em.createNativeQuery("SELECT SEQUENCE_NAME FROM INFORMATION_SCHEMA.SEQUENCES WHERE SEQUENCE_SCHEMA='PUBLIC'").getResultList();
            sequences.stream().forEach((sequence) -> {
                L.debug("Resetting Sequence {}", sequence);
                em.createNativeQuery("ALTER SEQUENCE " + sequence + " RESTART WITH 1").executeUpdate();
            });
            
            L.debug("Enabling foraign key constraints");
            em.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
        }
    }
}
