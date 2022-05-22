/*
Markus Bowie, 19841205-0075
Carl Sunnberg 19990330-3395
*/

//Subclass Jewellery to Superclass Valuable
public class Jewellery extends Valuable {

	// Constructor for the Jewellery object
	public Jewellery(String name, boolean gold, int numberOfGems) {
		super(name);
		if (name.trim().isEmpty())
			throw new NullPointerException("Error, enter a name");
		this.gold = gold;
		this.numberOfGems = numberOfGems;
	}

	// Variables for the object
	private boolean gold;
	private int numberOfGems;

	// return value of the object depending on gold or not gold(silver)
	public double getValue() {
		if (gold == true) {
			return 2000 + (getNumberOfGems() * 500);
		} else {
			return 700 + (getNumberOfGems() * 500);

		}

	}

	// return string of gold or silver depending on boolean
	public String getMaterial() {
		if (gold == true) {
			return "gold";
		} else {
			return "silver";
		}
	}

	// return the number of gems in the object
	public int getNumberOfGems() {
		return numberOfGems;
	}

	// Override for the superclass calculation of value
	@Override
	protected double calculateValue() {
		return 0;
	}

	// A toString method for printing of object
	@Override
	public String toString() {
		return name + " Value: " + getValueWithVat() + " Material: " + getMaterial() + " Number of Gems: "
				+ numberOfGems;
	}
}
