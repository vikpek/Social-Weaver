package de.pekars.rest;

public interface ParcelProcesser {

	/**
	 * Receives an encrypted and valid parcel that only includes reading
	 * operation (and therefore not needs to be synchronized.)
	 * 
	 * @param parcel
	 */
	public void processReadParcel(String parcel);

	/**
	 * Receives an encrypted and valid parcel that only includes write
	 * operations (and therefore needs to be synchronized.)
	 * 
	 * @param parcel
	 */
	public void processWriteParcel(String parcel);

}
