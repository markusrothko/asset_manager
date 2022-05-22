/*
Markus Bowie, 19841205-0075
Carl Sunnberg 19990330-3395
*/

//Subclass Apparatus to Superclass Valuable
public class Apparatus extends Valuable {

	// Constructor for the Jewellery object
	public Apparatus(String name, double salesPrice, double wear) {
		super(name);
		if (name.trim().isEmpty())
			throw new NullPointerException("Error, enter a name");
		if (wear < 1 || wear > 10)
			throw new IllegalArgumentException("Error, enter a number 1-10!");
		this.salesPrice = salesPrice;
		this.wear = wear;
	}

	// Variables for the object
	private double salesPrice;
	private double wear;

	public double getValue() {
		return (getWear() / 10 * getSalesPrice());
	}

	// return the sales price in the object
	public double getSalesPrice() {
		return salesPrice;
	}

	// return the wear(1-10) of the object
	public double getWear() {
		return wear;
	}

	// Override for the superclass calculation of value
	@Override
	protected double calculateValue() {
		return 0;
	}

	// A toString method for printing of object
	@Override
	public String toString() {
		return name + " Value: " + getValueWithVat() + " Sales price: " + salesPrice + " Condition: " + wear;
	}
}