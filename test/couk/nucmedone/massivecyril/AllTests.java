package couk.nucmedone.massivecyril;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(BSATest.class);
		suite.addTestSuite(CorrectedCountsTest.class);
		suite.addTestSuite(DoublePlusTest.class);
		suite.addTestSuite(ErrorsTest.class);
		suite.addTestSuite(GFRTest.class);
		suite.addTestSuite(InjectionTest.class);
		suite.addTestSuite(StandardsTubeTest.class);
		//$JUnit-END$
		return suite;
	}

}
