package application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

public class Controller {

	@FXML
    private RadioButton dangerousRadio;

    @FXML
    private ToggleGroup pathRadioButtons;

    @FXML
    private RadioButton easeRadio;

    @FXML
    private RadioButton distanceRadio;

    @FXML
    private Button findRouteButton;

    @FXML
    private Label fromLabel;

    @FXML
    private Label fromTownName;

    @FXML
    private Label toLabel;

    @FXML
    private Label toTownName;

    @FXML
    private Label throughLabel;

    @FXML
    private Line toArrow2;

    @FXML
    private Line toArrow1;

    @FXML
    private Line toArrow3;

    @FXML
    private Label throughTownName;

    @FXML
    private Line throughArrow1;

    @FXML
    private Line throughArrow2;

    @FXML
    private Line throughArrow3;
    
    @FXML
    private Button pressToStartButton;

    @FXML
    private AnchorPane mapPane;
    
    @FXML
    private AnchorPane addTownPane;
    
    @FXML
    private Button addTownsButton;

    @FXML
    private TextField addTownName;

    @FXML
    private TextField addTownX;

    @FXML
    private TextField addTownY;

    @FXML
    void addThisTownButton(MouseEvent event) {
    	new Town();
    }

    @FXML
    void addTownsClicked(MouseEvent event) {
    	addTownPane.setVisible(true);
    	addTownsButton.setText("Finish");
    }
    
    @FXML
    void pressToStartClicked(MouseEvent event) {
    	pressToStartButton.setVisible(false);
    	
    }

    @FXML
    void findRouteButtonClicked(MouseEvent event) {
    	
    }
    
    @FXML
    void passCoordinates(MouseEvent event) {
    	System.out.println(event.getX());
    	System.out.println(event.getY());
    }
    
    public void selectingPath(String town) {
    	System.out.println("hellllo");
//    	if (fromTownName.getText().length() == 0) {
//    		fromTownName.setText(town);
//    	} else if (toTownName.getText().length() == 0) {
//    		toTownName.setText(town);
//    	} else {
//    		throughTownName.setText(town);
//    	}
    }
    
    public void createTownButtons() {
    	mapPane.getChildren().forEach(node -> {
    		if (node.getOpacity() < 0.3) {
    			String townName = ((Labeled) node).getText();
    			((ButtonBase) node).setOnAction(new TownButtonHandler(this, townName));
    		}
    	});
    }
    
    
}
