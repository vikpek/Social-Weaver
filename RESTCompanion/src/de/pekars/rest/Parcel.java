package de.pekars.rest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Parcel {

	private int parcelType;
	private String parcelContent;

	public int getParcelType() {
		return parcelType;
	}

	public void setParcelType(int parcelType) {
		this.parcelType = parcelType;
	}

	public String getParcelContent() {
		return parcelContent;
	}

	public void setParcelContent(String parcelContent) {
		this.parcelContent = parcelContent;
	}

	@Override
	public String toString() {
		return "Parcel [parcelType=" + parcelType + ", parcelContent="
				+ parcelContent + "]";
	}
	
	
}
