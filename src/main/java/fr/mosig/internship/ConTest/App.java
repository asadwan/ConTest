package fr.mosig.internship.ConTest;

import fr.mosig.internship.ConTest.generators.AllTransitionsTestSubpathGenerator;
import fr.mosig.internship.ConTest.generators.TestSubpathGenerator;
import fr.mosig.internship.ConTest.loaders.*;
import fr.mosig.internship.ConTest.model.TestPath;
import fr.mosig.internship.ConTest.utils.Config;
import fr.mosig.internship.chsm.chsm.*;
import fr.mosig.internship.cdl.cdl.*;


public class App {
	
    public static void main( String[] args ) {

    	Coverage.ContextCriteria contextCoverage = Coverage.ContextCriteria.ALL_SITUATIONS;
    	Config config = Config.getInstance();
    	config.load();
    	ModelLoader devModelLoader = new DevModelLoader();
    	ModelLoader contextModelLoader = new ContextModelLoader();
    	Chsm devModel = devModelLoader.load(config.getDevModelPath());
    	Cdl contextModel = contextModelLoader.load(config.getContextModelPath());

		TestSubpathGenerator generator = new AllTransitionsTestSubpathGenerator();
		for(Statemachine sm: devModel.getStatemachines()) {
			map.put(sm.getName(), generator.generate(sm));
		}

		Set<TestPath> fullPaths = new HashSet<>();
		TestPathAggregator aggregator = new TestPathAggregator(map);
		for(TestPath path: map.get("ABSTRACT_SM")) {
			fullPaths.addAll(aggregator.aggregate(path));
		}

		for(TestPath path: fullPaths) {
			path.print();
		}
    	Chsm devModel = (Chsm) devModelLoader.load(config.getDevModelPath());
    	ContextModel contextModel = (ContextModel) contextModelLoader.load(config.getContextModelPath());
    	System.out.println(devModel.getName());
    	System.out.println(contextModel.getName());
    	
    }
}
