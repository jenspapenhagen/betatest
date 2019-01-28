/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.web.presenter;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.jwt.JsonWebToken;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SecureResource testing JWT
 *
 * @author jens.papenhagen
 */
@Path("secure")
public class SecureResource {

    @Inject
    @ConfigProperty(name = "message")
    private String message;

    @Inject
    private JsonWebToken callerPrincipal;

    private static final Logger L = LoggerFactory.getLogger(SecureResource.class);

    @GET
    @RolesAllowed("USER")
    public Response message() {
        L.info(callerPrincipal.getIssuer());
        L.info(callerPrincipal.getRawToken());
        L.info(callerPrincipal.getTokenID());

        return Response
                .ok(callerPrincipal.getName() + " is allowed to read message: " + message)
                .build();
    }

    @GET
    @Path("/admin")
    @RolesAllowed("ADMIN")
    public Response adminMessage() {
        return Response
                .ok(callerPrincipal.getName() + " is an ADMIN: " + message)
                .build();
    }

}
