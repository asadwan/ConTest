package fr.mosig.internship.ConTest.loaders;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import fr.mosig.internship.chsm.*;
import fr.mosig.internship.chsm.chsm.Chsm;

import com.google.inject.Injector;

public class DevModelLoader implements ModelLoader {

	@Override
	public <T extends EObject> T load(String path) {
		new org.eclipse.emf.mwe.utils.StandaloneSetup().setPlatformUri("../");
		Injector injector = new ChsmStandaloneSetup().createInjectorAndDoEMFRegistration();
		XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
		resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);
		Resource resource = resourceSet.getResource(
		    URI.createURI("platform:/resource/Testing/models/dev_model.hsm"), true);
		return Chsm.class.cast(resource.getContents().get(0));		
	}

}
