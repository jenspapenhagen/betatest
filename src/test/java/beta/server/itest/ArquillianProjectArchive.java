/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.server.itest;

import beta.server.BetaService;
import java.io.File;

import org.jboss.arquillian.container.test.api.Deployment;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import org.jboss.shrinkwrap.resolver.api.Coordinate;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;

import static org.jboss.shrinkwrap.resolver.api.maven.ScopeType.RUNTIME;
import static org.jboss.shrinkwrap.api.Filters.exclude;

import org.jboss.shrinkwrap.resolver.api.maven.coordinate.MavenDependencies;

/**
 * deploying a WebArchive with Arquillian that can be easly extends in Test
 * Classes
 *
 * @author jens papenhagen
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

        WebArchive war = ShrinkWrap.create(WebArchive.class, "beta-itest.war")
                .addPackages(true, exclude(itestPackage), projectPackage)
                .addClass(ArquillianProjectArchive.class)
                .addClass(Utils.class)
                .addClass(Coordinate.class) // Need this cause of the maven resolver is part of the deployment
                .addAsResource("beta/server/assist")
                .addAsResource(new ClassLoaderAsset("META-INF/test-persistence.xml"), "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsLibraries(libs);

        return war;
    }
}
