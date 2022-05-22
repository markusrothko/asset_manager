/*
Markus Bowie, 19841205-0075
Carl Sunnberg 19990330-3395
*/

public abstract class Valuable {

	protected String name;

	public Valuable(String name) {
		this.name = name;
	}

	// the methods below are used to add VAT to the item value
	public double getValue() {
		return calculateValue() * 1.25;
	}

	public double getValueWithVat() {
		return getValue() * 1.25;
	}

	abstract protected double calculateValue();

	public String getName() {
		return name;
	}

	public void changeName(String x) {
		name = x;
	}

	// this method is used for the "Financial Crisis" function
	public void setStockRate(double stockRate) {
		stockRate = 0.00;

	}

	// this is the toString method which is not used in this form
	@Override
	public String toString() {
		return "Valuable [type = " + name + ", value = " + getValueWithVat() + "]";
	}

}