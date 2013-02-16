package de.pekars.rest;

import static org.junit.Assert.*;

import org.junit.Test;

public class ParcelTypeTest {

	
	@Test
	public void testIsEncoded(){
		try {
			assertEquals(true, ParcelType.isEncoded(ParcelType.ENCRYPTED_READ));
			assertEquals(true, ParcelType.isEncoded(ParcelType.ENCRYPTED_WRITE));
			
			assertEquals(false, ParcelType.isEncoded(ParcelType.PLAIN_READ));
			assertEquals(false, ParcelType.isEncoded(ParcelType.PLAIN_WRITE));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
