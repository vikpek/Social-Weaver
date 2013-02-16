package de.pekars.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/parcel")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public class RestWebService {
	
	private static final Logger logger = LoggerFactory
			.getLogger(RestWebService.class);
	@POST
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	public Parcel putUser(Parcel parcel) {
		return parcel;
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
