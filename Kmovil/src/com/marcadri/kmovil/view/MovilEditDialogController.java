package com.marcadri.kmovil.view;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.controlsfx.dialog.Dialogs;

import com.marcadri.kmovil.model.Movil;

/**
 * Dialog to edit details of a person.
 * 
 * @author Marco Jakob
 */
public class MovilEditDialogController {

    @FXML
    private TextField modeloField;
    @FXML
    private TextField precioField;
    @FXML
    private TextField procesadorField;
    @FXML
    private TextField ramField;
    @FXML
    private TextField romField;
    @FXML
    private TextField pantallaField;
    @FXML
    private TextField bateriaField;


    private Stage dialogStage;
    private Movil movil;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the person to be edited in the dialog.
     * 
     * @param person
     */
    public void setMovil(Movil movil) {
        this.movil = movil;

        modeloField.setText(movil.getModelo());
        precioField.setText(movil.getPrecio());
        procesadorField.setText(movil.getProcesador());
        ramField.setText(movil.getRAM());
        romField.setText(movil.getROM());
        pantallaField.setText(movil.getPantalla());
        bateriaField.setText(movil.getBateria());

    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
	private void handleOk() {
		if (isInputValid()) {
			movil.setModelo(modeloField.getText());
			movil.setPrecio(precioField.getText());
			movil.setProcesador(procesadorField.getText());
			movil.setRAM(ramField.getText());
			movil.setROM(romField.getText());
			movil.setPantalla(pantallaField.getText());
			movil.setBateria(bateriaField.getText());

			okClicked = true;
			dialogStage.close();
		}
	}

	/**
	 * Called when the user clicks cancel.
	 */
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	/**
	 * Validates the user input in the text fields.
	 * 
	 * @return true if the input is valid
	 */
	private boolean isInputValid() {
		String errorMessage = "";

		if (modeloField.getText() == null || modeloField.getText().length() == 0) {
			errorMessage += "Modelo no valido";
		}
		if (precioField.getText() == null || precioField.getText().length() == 0) {
			errorMessage += "Precio no valido";
		}
		if (procesadorField.getText() == null || procesadorField.getText().length() == 0) {
			errorMessage += "Procesador no valido";
		}

		if (ramField.getText() == null || ramField.getText().length() == 0) {
			errorMessage += "RAM no valida";
		}

		if (romField.getText() == null || romField.getText().length() == 0) {
			errorMessage += "ROM no valida";
		}

		if (pantallaField.getText() == null || pantallaField.getText().length() == 0) {
			errorMessage += "Pantalla no valida";
		}

		if (bateriaField.getText() == null || bateriaField.getText().length() == 0) {
			errorMessage += "Bateria no valida";
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Show the error message.
			Dialogs.create().title("Invalid Fields").masthead("Please correct invalid fields").message(errorMessage)
					.showError();
			return false;
		}
	}
}