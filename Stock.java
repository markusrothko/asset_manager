/*
Markus Bowie, 19841205-0075
Carl Sunnberg 19990330-3395
*/

//this declares the class and lets it inherit characteristics from its superclass
public class Stock extends Valuable {

	// this is the constructor
	public Stock(String name, double stockRate, int numberOfStocks) {
		super(name);
		if (name.trim().isEmpty())
			throw new NullPointerException("Error, enter a name");
		this.stockRate = stockRate;
		this.numberOfStocks = numberOfStocks;
	}

	public double stockRate;
	private int numberOfStocks;

	// three getter methods
	public double getStockRate() {
		return stockRate;
	}

	public int getNumberOfStocks() {
		return numberOfStocks;
	}

	public double getValue() {
		return stockRate * numberOfStocks;
	}

	// the following three methods override the corresponding methods in the
	// superclass
	@Override
	protected double calculateValue() {
		return 0;
	}

	@Override
	public String toString() {
		return name + " Value: " + getValueWithVat() + " Number of stocks: " + getNumberOfStocks() + " Stock rate: "
				+ getStockRate();
	}

	@Override
	public void setStockRate(double stockRate) {
		this.stockRate = stockRate;

	}

}