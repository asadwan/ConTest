package fr.mosig.internship.ConTest;

import fr.mosig.internship.ConTest.loaders.*;
import fr.mosig.internship.ConTest.utils.Config;
import fr.mosig.internship.chsm.chsm.Chsm;
import fr.mosig.internship.cdl.cdl.*;


public class App {
	
    public static void main( String[] args ) {
    	Coverage.ContextCriteria contextCoverage = Coverage.ContextCriteria.ALL_SITUATIONS;
    	
    	Config config = Config.getInstance();
    	config.load();
    	
    	ModelLoader devModelLoader = new DevModelLoader();
    	ModelLoader contextModelLoader = new ContextModelLoader();
    	Chsm devModel = (Chsm) devModelLoader.load(config.getDevModelPath());
    	ContextModel contextModel = (ContextModel) contextModelLoader.load(config.getContextModelPath());
    	System.out.println(devModel.getName());
    	System.out.println(contextModel.getName());
    	
    }
}
