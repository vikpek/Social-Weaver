package at.ac.uibk.sowe.anchors;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * RSAnchor in Social Weaver Webservice
 * User: Viktor Pekar
 * Date: 27.03.13
 */

@Stateless
@Path(value = "/anchor/")
@Produces(MediaType.APPLICATION_JSON)
public class RSAnchor {

    @EJB
    AnchorService anchorService;

    @Path(value = "get")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<AnchorEntity> getAnchorBeans() {
        return anchorService.getAllAnchors();
    }

    @Path(value = "create")
    @PUT
    public AnchorEntity create(@QueryParam("anchorId") String anchorId) {
        AnchorEntity ab = new AnchorEntity();
        ab.setAnchorOid(123);
        anchorService.addAnchor(ab);
        return ab;
    }
//
//
//    public static void main(String[] args) throws IOException {
//        HttpServer server = HttpServerFactory.create("http://localhost:9998/");
//        server.start();
//
//        System.in.read();
//        System.out.println("Stopping server");
//        server.stop(0);
//        System.out.println("Server stopped");
//    }
}