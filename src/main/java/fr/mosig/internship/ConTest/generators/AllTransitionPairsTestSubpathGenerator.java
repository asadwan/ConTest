/**
 * 
 */
package fr.mosig.internship.ConTest.generators;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.mosig.internship.ConTest.model.TestPath;
import fr.mosig.internship.ConTest.model.TransitionPair;
import fr.mosig.internship.ConTest.utils.Util;
import fr.mosig.internship.chsm.chsm.State;
import fr.mosig.internship.chsm.chsm.Statemachine;
import fr.mosig.internship.chsm.chsm.hsm.Transition;

/**
 * @author asadwan
 * Generates test sub-paths from a state machine according to the all-transitions-pairs coverage criterion
 */
public class AllTransitionPairsTestSubpathGenerator implements TestSubpathGenerator {
	
	private List<TransitionPair> transitionPairs;

	@Override
	public Set<TestPath> generate(Statemachine sm) {
		Set<TestPath> subpaths = new HashSet<>();
		transitionPairs = getTransitionPairs(sm);
		while(!isAllTransitionPairsCovered()) {
			TestPath subpath = new TestPath();
			State current = sm.getStates().get(0);
			subpath.add(current);
			while(current != null && !current.getTransitions().isEmpty()) {
				Boolean currentPairsCovered = true;
				State target;
				outerLoop:
				for(Transition transition: current.getTransitions()) {
					target = transition.getTarget();
					for(Transition secTransition: target.getTransitions()) {
						TransitionPair pair = getTransitionPair(transition, secTransition);
						if(pair != null && !pair.isCovered()) {
							pair.setCovered(true);
							currentPairsCovered = false;
							subpath.add(transition);
							subpath.add(target);
							subpath.add(secTransition);
							subpath.add(secTransition.getTarget());
							current = secTransition.getTarget();
							break outerLoop;
						}
					}
				}
				if(currentPairsCovered) {
					int randomTransitionIndex = Util.randInt(0, current.getTransitions().size() - 1);
					Transition random = current.getTransitions().get(randomTransitionIndex);
					subpath.add(random);
					subpath.add(random.getTarget());
					current = random.getTarget();
				}
			}
			subpaths.add(subpath);
		}
		return subpaths;
	}

	
	private List<TransitionPair> getTransitionPairs(Statemachine sm) {
		List<TransitionPair> pairs = new ArrayList<>();
		for (State state: sm.getStates()) {
			for(Transition firstTransition: state.getTransitions()) {
				if(firstTransition.getTarget() != null) {
					for(Transition secondTransition: firstTransition.getTarget().getTransitions()) {
						pairs.add(new TransitionPair(firstTransition, secondTransition));
					}
				}
			}
		}
		return pairs;
	}
	
	private Boolean isAllTransitionPairsCovered() {
		for(TransitionPair pair: transitionPairs) {
			if(!pair.isCovered()) {
				return false;
			}
		}
		return true;
	}
	
	private TransitionPair getTransitionPair(Transition first, Transition second) {
		for(TransitionPair pair: transitionPairs) {
			if(pair.getFirst() == first && pair.getSecond() == second) {
				return pair;
			}
		}
		return null;
	}
}
