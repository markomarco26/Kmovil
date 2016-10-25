package com.marcadri.kmovil.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import com.marcadri.kmovil.MainApp;
import com.marcadri.kmovil.model.Movil;

public class MovilOverviewController {
	@FXML
	private TableView<Movil> movilTable;
	@FXML
	private TableColumn<Movil, String> modeloColumn;
	@FXML
	private TableColumn<Movil, String> precioColumn;

	@FXML
	private Label modeloLabel;
	@FXML
	private Label precioLabel;
	@FXML
	private Label procesadorLabel;
	@FXML
	private Label ramLabel;
	@FXML
	private Label romLabel;
	@FXML
	private Label pantallaLabel;
	@FXML
	private Label bateriaLabel;

	// Reference to the main application.
	private MainApp mainApp;

	/**
	 * The constructor. The constructor is called before the initialize()
	 * method.
	 */
	public MovilOverviewController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		// Initialize the person table with the two columns.
		modeloColumn.setCellValueFactory(cellData -> cellData.getValue().modeloProperty());
		precioColumn.setCellValueFactory(cellData -> cellData.getValue().precioProperty());

		showMovilDetails(null);

		// Listen for selection changes and show the person details when
		// changed.
		movilTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showMovilDetails(newValue));
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		// Add observable list data to the table
		movilTable.setItems(mainApp.getMovilData());
	}

	private void showMovilDetails(Movil mov) {
		if (mov != null) {
			// Fill the labels with info from the person object.
			modeloLabel.setText(mov.getModelo());
			precioLabel.setText(mov.getPrecio());
			procesadorLabel.setText(mov.getProcesador());
			ramLabel.setText(mov.getRAM());
			romLabel.setText(mov.getROM());
			pantallaLabel.setText(mov.getPantalla());
			bateriaLabel.setText(mov.getBateria());

			// TODO: We need a way to convert the birthday into a String!
			// birthdayLabel.setText(...);
		} else {
			// Person is null, remove all the text.
			modeloLabel.setText("");
			precioLabel.setText("");
			procesadorLabel.setText("");
			ramLabel.setText("");
			romLabel.setText("");
			pantallaLabel.setText("");
			bateriaLabel.setText("");
		}
	}

	@FXML
	private void handleDeleteMovil() {
		int selectedIndex = movilTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			movilTable.getItems().remove(selectedIndex);
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("No seleccionado");
			alert.setHeaderText("No has seleccionado ningun móvil");
			alert.setContentText("Porfavor selecciona un movil que eliminar");

			alert.showAndWait();
		}
	}
	@FXML
	private void handleNewMovil() {
	    Movil tempMovil = new Movil();
	    boolean okClicked = mainApp.showMovilEditDialog(tempMovil);
	    if (okClicked) {
	        mainApp.getMovilData().add(tempMovil);
	    }
	}

	/**
	 * Called when the user clicks the edit button. Opens a dialog to edit
	 * details for the selected person.
	 */
	@FXML
	private void handleEditMovil() {
	    Movil selectedMovil = movilTable.getSelectionModel().getSelectedItem();
	    if (selectedMovil != null) {
	        boolean okClicked = mainApp.showMovilEditDialog(selectedMovil);
	        if (okClicked) {
	            showMovilDetails(selectedMovil);
	        }

	    } else {
	        // Nothing selected.
	    	Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("No seleccionado");
			alert.setHeaderText("No has seleccionado ningun móvil");
			alert.setContentText("Porfavor selecciona un movil en la tabla");

			alert.showAndWait();
	    }
	}
}