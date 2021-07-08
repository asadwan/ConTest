package fr.mosig.internship.ConTest.generators;

import java.util.Set;

import fr.mosig.internship.ConTest.model.TestPath;
import fr.mosig.internship.chsm.chsm.Statemachine;

public interface TestSubpathGenerator {
	public Set<TestPath> generate(Statemachine sm);
}
