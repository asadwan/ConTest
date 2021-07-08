package fr.mosig.internship.ConTest.model;

import fr.mosig.internship.chsm.chsm.Transition;

public class TransitionPair {
	
	private Transition first;
	private Transition second;
	private Boolean covered = false;
	


	public TransitionPair(Transition first, Transition second) {
		this.first = first;
		this.second = second;
	}

	public Transition getFirst() {
		return first;
	}

	public Transition getSecond() {
		return second;
	}
	
	public void setCovered(Boolean covered) {
		this.covered = covered;
	}
	
	public Boolean isCovered() {
		return covered;
	}
	
}
