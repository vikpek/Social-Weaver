/**
 * 
 */
package at.ac.uibk.socialweaver.domain;

import java.util.Date;

/**
 * @author Viktor Pekar
 * 
 * Hook is the class that contains an unique reference to an element. 
 * (Type of the element is not known and only defined by the id)
 * 
 */
public class Hook {

	private long hookId;
	private String elementId; 
	private String userId;
	private Date ts;
	private HookContent hookContent;
}
