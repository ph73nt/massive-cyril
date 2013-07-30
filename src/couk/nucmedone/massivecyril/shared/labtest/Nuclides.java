package couk.nucmedone.massivecyril.shared.labtest;

class Nuclides {
	
	private double halfLife;
	private String name;
	private String code;
	
	public Nuclides(String code, String name, double halfLife){
		this.code = code;
		this.name = name;
		this.halfLife = halfLife;
	}
	
	/**
	 * Half-life of the nuclide in seconds.
	 * 
	 * @return The half-life (s)
	 */
	public double halfLife(){
		return halfLife;
	}
	
	public String name(){
		return name;
	}
	
	public String code(){
		return code;
	}
	
	public String toString() {
		return name;
	}

}