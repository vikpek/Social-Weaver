package de.pekars.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;

@Path("/parcel")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public class RestWebService {

	@POST
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	public Parcel putUser(JAXBElement<Parcel> parcel) {
		return parcel.getValue();
	}

	@POST
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	public Parcel postUser(JAXBElement<Parcel> parcel) {
		return parcel.getValue();
	}

	
	@Path("/test")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Parcel getTest() {
		Parcel p = new Parcel();
		p.setParcelContent("sdsdfsdf");
		p.setParcelType(123);
		return p;
	}
}
