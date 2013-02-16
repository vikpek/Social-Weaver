package de.pekars.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Uses the #ParcelProcessorAdapter to inject instances and distributes parsed
 * parcels.
 * 
 * @author vikpek
 * 
 */

@Stateless
public class ParcelProcessor {

	@EJB
	ParcelProcessAdapter parcelProcessAdapter;
	
	public void distributeParcel(Parcel parcel){
		
	}

}
