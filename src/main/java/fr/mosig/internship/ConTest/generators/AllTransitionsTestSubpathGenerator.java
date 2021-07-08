/**
 * 
 */
package fr.mosig.internship.ConTest.generators;

import java.util.HashSet;
import java.util.Set;

import fr.mosig.internship.ConTest.model.TestPath;
import fr.mosig.internship.chsm.chsm.State;
import fr.mosig.internship.chsm.chsm.Statemachine;
import fr.mosig.internship.chsm.chsm.Transition;

/**
 * @author adwanab
 * Generate test sub-paths from a state machine according to the all-transitions coverage criterion
 */
public class AllTransitionsTestSubpathGenerator implements TestSubpathGenerator {

	@Override
	public Set<TestPath> generate(Statemachine sm) {
		Set<Transition> visitedTransitions = new HashSet<Transition>();
		Set<Transition> transitions = getTransitions(sm);
		Set<TestPath> testSubpaths = new HashSet<TestPath>();
		
		while(!visitedTransitions.containsAll(transitions)) {
			TestPath testPath = new TestPath();
			State currentState = sm.getStates().get(0);
			while(!currentState.getTransitions().isEmpty()) {
				testPath.add(currentState);
				if(visitedTransitions.containsAll(currentState.getTransitions())) {
					testPath.add(currentState.getTransitions().get(0));
					currentState = currentState.getTransitions().get(0).getTarget();
				} else {
					for(Transition transition: currentState.getTransitions()) {
						if(!visitedTransitions.contains(transition)) {
							testPath.add(transition);
							visitedTransitions.add(transition);
							currentState = transition.getTarget();
							break;
						}
					}
				}
			}
			testPath.add(currentState);
			testSubpaths.add(testPath);
		}
		return testSubpaths;
	}
	
	private Set<Transition> getTransitions(Statemachine sm) {
		Set<Transition> transitions = new HashSet<>();
		for(State state: sm.getStates()) {
			transitions.addAll(state.getTransitions());
		}
		return transitions;
	}

}
