/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.server.itest;

import beta.server.BetaService;
import java.io.File;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.importer.ExplodedImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.Coordinate;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import static org.jboss.shrinkwrap.resolver.api.maven.ScopeType.RUNTIME;
import org.jboss.shrinkwrap.resolver.api.maven.coordinate.MavenDependencies;

/**
 *
 * @author jay
 */
public class ArquillianProjectArchive {

    @Deployment(testable = false)
    public static WebArchive createDeployment() {

        Package projectPackage = BetaService.class.getPackage();
        Package itestPackage = Utils.class.getPackage();

        File[] libs = Maven.resolver()
                .loadPomFromFile("pom.xml")
                .importRuntimeDependencies()
                .addDependency(MavenDependencies.createDependency("org.assertj:assertj-core", RUNTIME, false)) // AssertJ Fluent Assertions                
                .resolve()
                .withTransitivity().asFile();

        WebArchive war = ShrinkWrap.create(WebArchive.class, "beta-test.war")
                .addPackages(true, Filters.excludePaths("beta.server.itest", "beta.server.test"), projectPackage)
                .addClass(ArquillianProjectArchive.class)
                .addClass(Utils.class)
                .addClass(Coordinate.class) // Need this cause of the maven resolver is part of the deployment
                .addAsResource("beta/server/assist")
                .addAsLibraries(libs);

        // Only way to add all files under webapp
        war.merge(ShrinkWrap.create(GenericArchive.class).as(ExplodedImporter.class)
                .importDirectory("src/main/webapp").as(GenericArchive.class),
                "/", Filters.includeAll());

        return war;
    }
}
