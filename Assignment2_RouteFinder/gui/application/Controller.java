package application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/*
 * @author Adam Smith
 */
public class Controller {

	@FXML
    private ScrollPane throughPathList;

    @FXML
    private Label throughLabel;

    @FXML
    private ScrollPane routePathList;

    @FXML
    private Label pathLabel;

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
    private Label toTownName;

    @FXML
    private Label throughTownName;

    @FXML
    private Button addTownsButton;

    @FXML
    private AnchorPane mapPane;

    @FXML
    private Button pressToStartButton;

    @FXML
    private AnchorPane addTownPane;

    @FXML
    private Button addThisTown;

    @FXML
    private TextField addTownName;

    @FXML
    private TextField addTownX;

    @FXML
    private TextField addTownY;

    @FXML
    private Button townButton;

    @FXML
    private Button connectTownsButton;

    @FXML
    private AnchorPane connectTownsPane;

    @FXML
    private TextField firstTownConnect;

    @FXML
    private TextField secondTownConnect;
    
    /**
	 * Grabs the x,y value of the mouse when called, converting from the original double to int (for Town class)
	 * and finally to string to be displayed in text field.
	 * @param event - The 'addTown' being clicked.
	 */
    @FXML
    void addThisTownButton(MouseEvent event) {
    	Town town = new Town(addTownName.getText(), Integer.parseInt(addTownX.getText()), Integer.parseInt(addTownY.getText()));
    	Main.graph.getTowns().add(town);
    	Main.graph.addNode(new Node<Town>(town));
    	
    	// TODO: create a new town button at x,y
    	Button button = new Button();
    	button.setPrefHeight(townButton.getHeight());
    	button.setPrefWidth(townButton.getWidth());
    	button.setLayoutX(town.getX()-townButton.getHeight()/2);
    	button.setLayoutY(town.getY()-townButton.getWidth()/2);
    	button.setOpacity(0.5);
    	button.setText(town.getName());
    	
    	button.setOnAction(new EventHandler<ActionEvent>() {
    		
    		public void handle(ActionEvent event) {
    			if (connectTownsPane.visibleProperty().get()) {
    				townSelectedForConnection(town);
    			}
    			else {
    				if (fromTownName.getText().length() == 0) {
    					fromTownName.setText(town.getName());
    				} else {
    					toTownName.setText(town.getName());
    				}
    			}
    		}
    		
    	});
    	
    	mapPane.getChildren().add(button);
    	clearAddTownTextBoxes();
    }
   
    public void townSelectedForConnection(Town town) {
    	if (firstTownConnect.getText().length() == 0) {
    		firstTownConnect.setText(town.getName());
    	} else if (secondTownConnect.getText().length() == 0) {
    		secondTownConnect.setText(town.getName());
    	}
    }
    
    /**
	 * Given that TWO towns have been already selected, for the first town the method
	 * goes through each node in the graph's node collection, comparing the town's name
	 * (in the node's contents) with the name in the text field of the FXML. If it matches,
	 * it does the same for the second text field. This process is to get the Node object
	 * to get the NodeID and connect the two Nodes...
	 * @param town - The town the button represents which was pressed.
	 */
    @FXML
    void connectTwoTowns(MouseEvent event) {
    	if (firstTownConnect.getText().length() == 0 || secondTownConnect.getText().length() == 0) return;
    	Main.graph.getNodes().forEach(firstNodeTown -> {
			if (((Town) firstNodeTown.getContents()).getName() == firstTownConnect.getText()) {
				Main.graph.getNodes().forEach(secondNodeTown -> {
					if (((Town) secondNodeTown.getContents()).getName() == secondTownConnect.getText()) {
						Main.graph.connect(firstNodeTown, secondNodeTown, new Edge());
					}
				});
			}
		});
    }
    
    /**
	 * Makes visible/invisible the 'addTownsPane' and clears all the text fields.
	 * @param event - The "Add Towns" button being clicked.
	 */
    @FXML
    void addTownsClicked(MouseEvent event) {
    	if (addTownPane.visibleProperty().get()) {
    		addTownPane.setVisible(false);
    		addTownsButton.setText("Add Towns");
    		clearAddTownTextBoxes();
    	} else {
    		addTownPane.setVisible(true);
    		addTownsButton.setText("Finish");
    	}
    }
    
    public void clearAddTownTextBoxes() {
    	addTownName.setText("");
    	addTownX.setText("");
    	addTownY.setText("");
    }
    
    @FXML
    void connectTownsClicked(MouseEvent event) {
    	if (connectTownsPane.visibleProperty().get()) {
    		connectTownsPane.setVisible(false);
    		connectTownsButton.setText("Connect Towns");
    		clearAddTownTextBoxes();
    	} else {
    		connectTownsPane.setVisible(true);
    		connectTownsButton.setText("Finish");
    	}
    }
    
    @FXML
    void connectTheseTownsClicked(MouseEvent event) {
    	
    }
    
    @FXML
    void pressToStartClicked(MouseEvent event) {
    	pressToStartButton.setVisible(false);
    }

    @FXML
    void findRouteButtonClicked(MouseEvent event) {
    	
    }
    
    @FXML
    void townSelected() {
		System.out.println("Heelo");
    }
    
    /**
	 * Grabs the x,y value of the mouse when called, converting from the original double to int (for Town class)
	 * and finally to string to be displayed in text field.
	 * @param event - The 'mapPane' being clicked.
	 */
    @FXML
    void passCoordinates(MouseEvent event) {
    	if (addTownPane.visibleProperty().get()) {
    		addTownX.setText(Integer.toString((int) event.getX())); // Need to convert from double to int to string
    		addTownY.setText(Integer.toString((int) event.getY()));
    	}
    }
    
    public void selectingPath(String town) {
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
