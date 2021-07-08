package fr.mosig.internship.ConTest;

import java.lang.String;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import fr.mosig.internship.ConTest.model.TestPath;
import fr.mosig.internship.ConTest.model.TestPathElement;
import fr.mosig.internship.chsm.chsm.SuperState;
import fr.mosig.internship.chsm.chsm.Transition;

public class TestPathAggregator {
	
	private Map<String, Set<TestPath>> pathsMap;
	
	public TestPathAggregator(Map<String, Set<TestPath>> pathsMap) {
		this.pathsMap = pathsMap;
	}

	public Set<TestPath> aggregate(TestPath subpath) {
		Set<TestPath> aggregatedPaths = new HashSet<TestPath>();
		int superStateElIndex = this.getSuperStateElementIndex(subpath);
		if(superStateElIndex != -1) { // path has super state
			for(TestPath path: this.aggregate(subpath, superStateElIndex)) {
				aggregatedPaths.addAll(aggregate(path));
			}
		} else {
			aggregatedPaths.add(subpath);
		}

		return aggregatedPaths;
	}
	
	private Set<TestPath> aggregate(TestPath subpath, int superStateIndex) {
		Set<TestPath> aggregatedPaths = new HashSet<TestPath>();
		TestPathElement superStateEl = subpath.getElements().get(superStateIndex);
		SuperState superState = (SuperState) superStateEl.getState();
		for(TestPath path: pathsMap.get(superState.getAbstracts().getName())) {
			Transition externalTransition = getExternalTransition(path);
			if(externalTransition.getTarget().getName().equals(superStateEl.getTransition().getTarget().getName())) {
				aggregatedPaths.add(subpath.expand(superStateIndex, path));
			}
		}
		
		return aggregatedPaths;
	}
	
	private Transition getExternalTransition(TestPath path) {
		for(TestPathElement el: path.getElements()) {
			if(el.getTransition() != null && el.getTransition().isExternal()) {
				return el.getTransition();
			}
		}
		return null;
	}
 
	
	private int getSuperStateElementIndex(TestPath path) {
		for (TestPathElement el: path.getElements()) { 
			if(el.getState() instanceof SuperState) {
				return path.getElements().indexOf(el);
			}
		}
		return -1;
	}
}
