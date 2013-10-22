package couk.nucmedone.massivecyril.shared.labtest;

import couk.nucmedone.common.base.DoublePlus;

/**
 * A class to hold parameter for counting reference standard flasks
 * in nuclear counting tests such as EDTA GFR and RCM.
 * 
 * @author neil@pukka-j.com
 * 
 */
public class Flask {

	private DoublePlus flaskVolume, tracerVolume;
	
	// TODO: Set the flask volume error
	public static final double FLASK_ERROR = 0.5; //ml
	
	// TODO: Set tracer volume error
	public static final double TRACER_ERROR = 0.05; //ml
	
	public Flask(DoublePlus flaskVolume, DoublePlus tracerVolume){
		
		this.flaskVolume = flaskVolume;
		this.tracerVolume = tracerVolume;
		
	}
	
	/**
	 * Set the volume (in millilitres) of total fluid in the flask the standard
	 * is contained in.
	 * 
	 * @param vol The container volume in millilitres.
	 */
	public void setFlaskVolume(double vol) {
		flaskVolume = new DoublePlus(vol, FLASK_ERROR);
	}
	
	/**
	 * Get the volume (in millilitres) of total fluid in the flask the standard
	 * is contained in.
	 * 
	 * @return The container volume in millilitres.
	 */
	public DoublePlus getFlaskVolume(){
		return flaskVolume;
	}	
	
	/**
	 * Set the volume (in millilitres) of active tracer added to the standard
	 * flask.
	 * 
	 * @param vol
	 *            The volume of active tracer in millilitres.
	 */
	public void setTracerVolume(double vol) {
		tracerVolume = new DoublePlus(vol, TRACER_ERROR);
	}

	/**
	 * Get the volume (in millilitres) of active tracer added to the standard
	 * flask.
	 * 
	 * @return The volume of active tracer in millilitres.
	 */
	public DoublePlus getTracerVolume() {
		return tracerVolume;
	}
	
}
