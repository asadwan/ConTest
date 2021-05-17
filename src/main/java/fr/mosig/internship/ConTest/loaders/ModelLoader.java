/**
 * 
 */
package fr.mosig.internship.ConTest.loaders;

import org.eclipse.emf.ecore.EObject;

/**
 * @author asadwan
 *	loads an ECORE model from the file system
 */
public interface ModelLoader {
	
	public <T extends EObject> T load(String path);
}
