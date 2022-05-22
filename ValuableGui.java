/*
Markus Bowie, 19841205-0075
Carl Sunnberg 19990330-3395
*/

import java.util.*;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ValuableGui extends Application {

	private ArrayList<Valuable> allValuables = new ArrayList<>();
	private TextArea display;
	private RadioButton sortByNameButton = new RadioButton("Name");
	private RadioButton sortByValueButton = new RadioButton("Value");

	// sets the stage
	public void start(Stage primaryStage) {
		BorderPane root = new BorderPane();
		root.setStyle("-fx-font-size: 18");

		display = new TextArea();
		display.setEditable(false);
		root.setCenter(display);
		// this adds a title to the GUI window
		primaryStage.setTitle("Inventory of Valuables");

		// adds top horizontal box, only used for the "valuables" label
		HBox top = new HBox();
		top.setPadding(new Insets(5));
		top.getChildren().add(new Label("Valuables"));
		top.getChildren().addAll();
		root.setTop(top);
		top.setAlignment(Pos.CENTER);
//adds vertical box for the sorting options
		VBox right = new VBox();
		right.setPadding(new Insets(5));
		right.getChildren().add(new Label("Sorting"));
		ToggleGroup group = new ToggleGroup();

		// controls what happens when the sorting radio buttons are fired
		sortByNameButton.setSelected(true);
		right.getChildren().addAll(sortByNameButton, sortByValueButton);
		root.setRight(right);

		sortByNameButton.setToggleGroup(group);
		sortByValueButton.setToggleGroup(group);

		sortByNameButton.setOnAction(new NyHanteraresortByNameButton());
		sortByValueButton.setOnAction(new NyHanteraresortByValueButton());

		// adds menu items to the drop-down menu
		MenuItem menuItem1 = new MenuItem("Jewellery");
		MenuItem menuItem2 = new MenuItem("Stock");
		MenuItem menuItem3 = new MenuItem("Apparatus");

		// sets up labels and buttons for the bottom controls
		FlowPane bottom = new FlowPane();
		root.setBottom(bottom);
		Label newLbl = new Label("New:");
		MenuButton menuButton = new MenuButton("Select Valuable:", null, menuItem1, menuItem2, menuItem3);
		bottom.getChildren().add(newLbl);
		bottom.getChildren().add(menuButton);
//connects menu items to events
		menuItem1.setOnAction(new NyHanterareJewellery());
		menuItem2.setOnAction(new NyHanterareStock());
		menuItem3.setOnAction(new NyHanterareApparatus());
//the show button plus events
		Button showBtn = new Button("Show");
		bottom.getChildren().add(showBtn);
		showBtn.setOnAction(new VisaHanterare());
		bottom.setAlignment(Pos.CENTER);
		bottom.setHgap(5);
		bottom.setPadding(new Insets(5));
//the financial crisis button
		Button crisisBtn = new Button("Financial Crisis");
		bottom.getChildren().add(crisisBtn);
		crisisBtn.setOnAction(new VisaHanterareCrisis());
		bottom.setAlignment(Pos.CENTER);
		bottom.setHgap(5);
		bottom.setPadding(new Insets(5));

		/*
		 * Button populateBtn = new Button("Populate");
		 * bottom.getChildren().add(populateBtn); populateBtn.setOnAction(new
		 * VisaHanterarePopulate()); bottom.setAlignment(Pos.CENTER); bottom.setHgap(5);
		 * bottom.setPadding(new Insets(5));
		 */

		Scene scene = new Scene(root, 400, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	// event for sort by name button
	class NyHanteraresortByNameButton implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event) {
			display.clear();
			sorting();
		}
	}

	// event for sort by value button
	class NyHanteraresortByValueButton implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event) {
			display.clear();
			sorting();
		}
	}

	// sorting method
	public void sorting() {
		if (sortByNameButton.isSelected()) {
			allValuables.sort((a1, a2) -> a1.getName().compareTo(a2.getName()));
		} else if (sortByValueButton.isSelected()) {
			Collections.sort(allValuables, new Comparator<Valuable>() {
				@Override
				public int compare(Valuable v1, Valuable v2) {
					return Double.compare(v1.getValue(), v2.getValue());
				}
			});
		}
	}

	public void sortByValue() {
		if (sortByValueButton.isSelected()) {
		}
	}

	// register new jewellery method
	class NyHanterareJewellery implements EventHandler<ActionEvent> {
		String name;

		public void handle(ActionEvent event) {
			try {
				MyAlertJewellery dialog = new MyAlertJewellery();
				Optional<ButtonType> result = dialog.showAndWait();
				if (result.isPresent() && result.get() == ButtonType.OK) {
					if (name.trim().isEmpty()) {
						Alert msg = new Alert(AlertType.ERROR, "Empty name!");
						msg.showAndWait();
					}
					name = dialog.getName();
					int numberOfGems = dialog.getNumberOfGems();
					boolean gold = dialog.gold();
					Jewellery jwl = new Jewellery(name, gold, numberOfGems);
					allValuables.add(jwl);
				}
			} catch (NumberFormatException e) {
				Alert msg = new Alert(AlertType.ERROR);
				msg.setContentText("Error, incorrect input!");
				msg.showAndWait();
			} catch (NullPointerException e) {
				Alert msg = new Alert(AlertType.ERROR);
				msg.setContentText("Error, enter all fields please");
				msg.showAndWait();
			}

		}
	}

	// register new stock method
	class NyHanterareStock implements EventHandler<ActionEvent> {
		String name;

		public void handle(ActionEvent event) {
			try {
				MyAlertStock dialog = new MyAlertStock();
				Optional<ButtonType> result = dialog.showAndWait();
				if (result.isPresent() && result.get() == ButtonType.OK) {
					if (name.trim().isEmpty()) {
						Alert msg = new Alert(AlertType.ERROR, "Empty name!");
						msg.showAndWait();
					}
					name = dialog.getName();
					double stockRate = dialog.getStockRate();
					int numberOfStocks = dialog.getNumberOfStocks();
					Stock stck = new Stock(name, stockRate, numberOfStocks);
					allValuables.add(stck);
				}
			} catch (NumberFormatException e) {
				Alert msg = new Alert(AlertType.ERROR);
				msg.setContentText("Error, incorrect input!");
				msg.showAndWait();
			} catch (NullPointerException e) {
				Alert msg = new Alert(AlertType.ERROR);
				msg.setContentText("Error, enter all fields please");
				msg.showAndWait();
			}

		}
	}

	// register new apparatus method
	class NyHanterareApparatus implements EventHandler<ActionEvent> {
		String name;

		public void handle(ActionEvent event) {
			try {
				MyAlertApparatus dialog = new MyAlertApparatus();
				Optional<ButtonType> result = dialog.showAndWait();
				if (result.isPresent() && result.get() == ButtonType.OK) {
					if (name.trim().isEmpty()) {
						Alert msg = new Alert(AlertType.ERROR, "Empty name!");
						msg.showAndWait();
					}
					name = dialog.getName();
					double salesPrice = dialog.getSalesPrice();
					int wear = dialog.getWear();
					Apparatus apr = new Apparatus(name, salesPrice, wear);
					allValuables.add(apr);
				}
			} catch (NumberFormatException e) {
				Alert msg = new Alert(AlertType.ERROR);
				msg.setContentText("Error, incorrect input!");
				msg.showAndWait();
			} catch (NullPointerException e) {
				Alert msg = new Alert(AlertType.ERROR);
				msg.setContentText("Error, enter all fields please");
				msg.showAndWait();
			}

		}
	}

	// show button event
	class VisaHanterare implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event) {
			display.clear();
			for (Valuable vbl : allValuables)
				display.appendText(vbl.toString() + "\n");
		}
	}

	// financial crisis event
	class VisaHanterareCrisis implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event) {
			financialCrisis();
			display.clear();
			display.appendText("Financial Crisis!");

		}
	}
	/*
	 * 
	 * class VisaHanterarePopulate implements EventHandler<ActionEvent> { public
	 * void handle(ActionEvent event) { display.clear();
	 * display.appendText("Populated!"); populate();
	 * 
	 * } }
	 */

	// financial crisis method
	public void financialCrisis() {

		for (int i = 0; i < allValuables.size(); i++) {
			allValuables.get(i).setStockRate(0.00);
		}
	}

	/*
	 * private void populate() { allValuables.add(new Stock("Colgate", 107, 500));
	 * allValuables.add(new Stock("Ericsson", 34, 250)); allValuables.add(new
	 * Stock("Siemens", 23, 100)); allValuables.add(new Stock("Apple", 12, 4000));
	 * allValuables.add(new Jewellery("Ring", false, 20)); allValuables.add(new
	 * Jewellery("Necklace", true, 150)); allValuables.add(new Jewellery("Bracelet",
	 * false, 10)); allValuables.add(new Apparatus("yo", 32, 2));
	 * allValuables.add(new Apparatus("iPad", 20, 9)); allValuables.add(new
	 * Apparatus("Keyboard", 4000, 8));
	 * 
	 * 
	 * }
	 */
//the main method for the GUI/program
	public static void main(String[] args) {
		launch(args);

	}
}
