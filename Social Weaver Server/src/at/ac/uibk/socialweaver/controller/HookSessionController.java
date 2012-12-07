/**
 * 
 */
package at.ac.uibk.socialweaver.controller;

import java.util.HashMap;

/**
 * @author Viktor Pekar
 * 
 *         Manages different HookController for different sessions.
 *         HookSessionController is implemented as singleton and any
 *         HookOperation should happen under its scope.
 */
public class HookSessionController {

	private static HookSessionController instance = new HookSessionController();
	HashMap<String, HookController> hookControllerMap = new HashMap<String, HookController>();

	private HookSessionController() {
	}

	public static HookSessionController getInstance() {
		return instance;
	}
}
