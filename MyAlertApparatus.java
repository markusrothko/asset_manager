/*
Markus Bowie, 19841205-0075
Carl Sunnberg 19990330-3395
*/

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

//Subclass MyAlertApparatus to Superclass Alert
public class MyAlertApparatus extends Alert {
	private TextField nameField = new TextField();
	private TextField salesPriceField = new TextField();
	private TextField wearField = new TextField();

	// Constructor
	public MyAlertApparatus() {
		super(AlertType.CONFIRMATION);

		// Creation of 3 textfields in a grid(3 rows)
		GridPane grid = new GridPane();
		grid.addRow(0, new Label("Name:"), nameField);
		grid.addRow(1, new Label("Sales Price:"), salesPriceField);
		grid.addRow(2, new Label("Wear:"), wearField);
		getDialogPane().setContent(grid);

		// Check to se what value the user is entering(WIP)
		wearField.textProperty().addListener((obs, oldText, newText) -> {
			if (newText.matches("^[0-1]?[0-9]$")) {
				wearField.setText(newText);
			} else {
				wearField.setText(oldText);
			}
		});
	}

	// Return the entered name of the apparatus object
	public String getName() {

		String correctedName = nameField.getText();
		String s1 = correctedName.substring(0, 1).toUpperCase();
		String nameCapitalized = s1 + correctedName.substring(1);
		return nameCapitalized;
	}

	// return the entered salesprice
	public double getSalesPrice() {
		return Double.parseDouble(salesPriceField.getText());
	}

	// return the entered wear
	public int getWear() {
		return Integer.parseInt(wearField.getText());
	}

}