package fr.mosig.internship.ConTest.model;

import import fr.mosig.internship.chsm.chsm.*;

public class TestPathElement {
	
	private State state;
	private Transition transition;
	
	public TestPathElement(State state, Transition transition) {
		super();
		this.state = state;
		this.transition = transition;
	}
	
	public TestPathElement(State state) {
		super();
		this.state = state;
	}


	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Transition getTransition() {
		return transition;
	}

	public void setTransition(Transition transition) {
		this.transition = transition;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof TestPathElement)) return false;
		TestPathElement el = (TestPathElement)obj;
		if(this.state != el.getState() || this.transition != el.getTransition()) return false;
		return true;
	}
	
}
