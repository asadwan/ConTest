package fr.mosig.internship.ConTest.model;

import java.util.ArrayList;
import java.util.List;

import fr.mosig.internship.chsm.chsm.State;
import fr.mosig.internship.chsm.chsm.Transition;


public class TestPath {
	
	private List<TestPathElement> elements = new ArrayList<TestPathElement>();
	
	public void add(State state) {
		TestPathElement el = new TestPathElement(state);
		elements.add(el);
	}
	
	public void add(Transition transition) {
		if(elements.size() > 0) {
			if(elements.get(elements.size() - 1).getTransition() == null) {
				elements.get(elements.size() - 1).setTransition(transition);
			} else {
				System.out.println("Unable to add transition, consecutive transitions are not allowed");
			}
		} else {
			System.out.println("Unable to add transition, test path is empty");
		}
	}
	
	public void print() {
		for(TestPathElement el: elements) {
			System.out.println("state -> " + el.getState().getName());			
			if(el.getTransition() != null) {
				System.out.println("  event -> " +
						(el.getTransition().getOn() == null ? "null" : el.getTransition().getOn().getName()));
			}
		}
		System.out.println("");
		System.out.println("----------------------------------------------------------");
		System.out.println("");
	}
	
	public List<TestPathElement> getElements() {
		return elements;
	}
	
	private void setElements(List<TestPathElement> elements) {
		this.elements = elements;
	}
	
	public TestPath expand(int superStateIndex, TestPath path) {
		TestPath expanded = new TestPath();
		TestPathElement superStateEl = this.getElements().get(superStateIndex);
		Transition superStateTransition = superStateEl.getTransition();
		expanded.setElements(this.elements);
		expanded.getElements().remove(superStateEl);
		for(int i = 0; i < path.getElements().size(); i++ ) {
			TestPathElement elToAdd = path.getElements().get(i);
			if(elToAdd.getTransition() != null && elToAdd.getTransition().isExternal()) {
				elToAdd = new TestPathElement(elToAdd.getState(), superStateTransition);
				expanded.elements.add(i + superStateIndex, elToAdd);
				break;
			};
			expanded.elements.add(i + superStateIndex, elToAdd);
		}
		return expanded;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof TestPath)) return false;
		TestPath path = (TestPath)obj;
		if(path.getElements().size() != this.elements.size()) return false;
		for(int i = 0; i < this.elements.size(); i++) {
			if(this.elements.get(i) != this.getElements().get(i)) {
				return false;
			}
		}
		return true;
	}



}
