package de.pekars.rest;


/**
 * 
 * @author vikpek
 *
 * 
 */
public class ParcelType {

	private static final int ENCR = 20;
	private static final int PLAIN = 10;
	
	public static final int ENCRYPTED_READ = ENCR + 1;
	public static final int PLAIN_READ = PLAIN + 1;
	
	public static final int ENCRYPTED_WRITE = ENCR + 2;
	public static final int PLAIN_WRITE = PLAIN + 2;
	
	public static boolean isEncoded(int type) throws Exception{
		if(getIntegerAtPosition(0, type) == ENCR){
			return true;
		}else if(getIntegerAtPosition(0, type) == PLAIN){
			return false;
		}else{
			throw new Exception("Error while parsing ParcelType: " + type);
		}
	}
	
	public static boolean isEncoded(Parcel parcel) throws Exception{
		return isEncoded(parcel.getParcelType());
	}
	
	private static int getIntegerAtPosition(int position, int type){
		String s = Integer.toString(type);
		char[] charArr = s.toCharArray();
		int t = Integer.valueOf(String.valueOf(charArr[position])).intValue();
		for(int i = 1; i < (charArr.length - position); i++){
			t = t * 10;
		}
		
		return t; 
	}
}
