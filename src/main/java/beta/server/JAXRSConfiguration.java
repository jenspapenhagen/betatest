/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.server;

import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.eclipse.microprofile.auth.LoginConfig;

/**
 * building a REST with JWT
 *
 * @author jens.papenhagen
 */
@LoginConfig(authMethod = "MP-JWT")
@ApplicationPath("resources")
@DeclareRoles({"USER", "ADMIN"})
public class JAXRSConfiguration extends Application {

}
