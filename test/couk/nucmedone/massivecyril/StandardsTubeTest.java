package couk.nucmedone.massivecyril;

import junit.framework.Assert;
import junit.framework.TestCase;
import couk.nucmedone.common.base.DoublePlus;
import couk.nucmedone.massivecyril.shared.labtest.AbstractCountingTube;
import couk.nucmedone.massivecyril.shared.labtest.StandardsTube;

public class StandardsTubeTest extends TestCase {
	
	private StandardsTube tube;
	private double tolerance = 0.0000001;
	
	public StandardsTubeTest(String s){
		super(s);
		tube = new StandardsTube("Tube");
	}
	
	public void testSetTracerVolume(){
		tube.setEmptyWeight(new DoublePlus(5.6, AbstractCountingTube.WEIGHT_ERROR));
		tube.setFullWeight(new DoublePlus(6.1, AbstractCountingTube.WEIGHT_ERROR));
		DoublePlus dil = new DoublePlus(0.5 / 500, 1);
		double dens = 1;
		tube.setTracerVolume(dil, dens);
		double actual = tube.getTracerVolume().value();
		double expected = 0.0005;
		Assert.assertEquals(expected, actual, tolerance);
	}
}