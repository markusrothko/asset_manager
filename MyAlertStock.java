/*
Markus Bowie, 19841205-0075
Carl Sunnberg 19990330-3395
*/

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class MyAlertStock extends Alert {
	private TextField nameField = new TextField();
	private TextField rateField = new TextField();
	private TextField numberField = new TextField();

	public MyAlertStock() {
		super(AlertType.CONFIRMATION);

		// attaches the various labels and input fields to the grid
		GridPane grid = new GridPane();
		grid.addRow(0, new Label("Name:"), nameField);
		grid.addRow(1, new Label("Stock Rate:"), rateField);
		grid.addRow(2, new Label("Number of Stocks:"), numberField);

		getDialogPane().setContent(grid);

		// This modifies which input is accepted by the numberField TextField by using a
		// regEx
		numberField.textProperty().addListener((obs, oldText, newText) -> {
			if (newText.matches("[1-9][0-9]*")) {
				numberField.setText(newText);
			} else {
				numberField.setText(oldText);
			}
		});

		// This modifies which input is accepted by the rateField TextField by using a
		// regEx
		rateField.textProperty().addListener((obs, oldText, newText) -> {
			if (newText.matches("^([0-9]+\\.?[0-9]*|[0-9]*\\.[0-9]+)$")) {
				rateField.setText(newText);
			} else {
				rateField.setText(oldText);
			}
		});
	}

	// Changes the first letter of the input to a capital letter
	public String getName() {

		String correctedName = nameField.getText();
		String s1 = correctedName.substring(0, 1).toUpperCase();
		String nameCapitalized = s1 + correctedName.substring(1);
		return nameCapitalized;
	}

	// the getter for the stockRate double which the user has inputted in the
	// rateField Text Field
	public double getStockRate() {
		return Double.parseDouble(rateField.getText());
	}

	// the getter for the numberOfStocks int which the user has inputted in the
	// numberField Text Field
	public int getNumberOfStocks() {
		return Integer.parseInt(numberField.getText());
	}
}