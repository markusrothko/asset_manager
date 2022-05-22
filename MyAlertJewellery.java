/*
Markus Bowie, 19841205-0075
Carl Sunnberg 19990330-3395
*/

import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

//Subclass MyAlertJewellery to Superclass Alert
public class MyAlertJewellery extends Alert {
	private TextField nameField = new TextField();
	private TextField gemField = new TextField();
	private CheckBox box = new CheckBox("Gold");

	public MyAlertJewellery() {
		super(AlertType.CONFIRMATION);

		// Creation of 3 textfields in a grid(3 rows)
		GridPane grid = new GridPane();
		grid.addRow(0, new Label("Name:"), nameField);
		grid.addRow(1, new Label("Number of Gems:"), gemField);
		grid.addRow(2, box);
		getDialogPane().setContent(grid);

		// Check to se what value the user is entering, WIP
		gemField.textProperty().addListener((obs, oldText, newText) -> {
			if (newText.matches("^[0-9]*[0-9][0-9]*$")) {
				gemField.setText(newText);
			} else {
				gemField.setText(oldText);
			}
		});
	}

	// Return the entered name of the jewellery object
	public String getName() {

		String correctedName = nameField.getText();
		String s1 = correctedName.substring(0, 1).toUpperCase();
		String nameCapitalized = s1 + correctedName.substring(1);
		return nameCapitalized;
	}

	// return the entered number of gems
	public int getNumberOfGems() {
		return Integer.parseInt(gemField.getText());
	}

	// return true or false(gold or not gold(silver)) depending checked box or not
	public boolean gold() {
		return box.isSelected();
	}

}