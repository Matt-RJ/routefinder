package application;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;


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
    private Button cancelButton;
    
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

    @FXML
    private Label hoverOverTownName;
    
    @FXML
    private TextArea pathListTextBox;
    
    @FXML
    private TextArea waypointListTexBox;
    
    @FXML
    private TextField danger;

    @FXML
    private TextField ease;

    @FXML
    private TextField distance;
    
    @FXML
    private Button previousRouteButton;

    @FXML
    private Button nextRouteButton;
    
    @FXML
    private Label permutationNumberLabel;
    
    // a list of waypoints to be used.
    private ArrayList<Node<?>> townsToGoThrough = new ArrayList<Node<?>>();
    
    // a list of all possible permutations.
    private ArrayList<ArrayList<Node<?>>> allPermutations = new ArrayList<ArrayList<Node<?>>>();
    
    private int currentPermutation = 0;
    
    private ArrayList<Line> highlightedPathLines = new ArrayList<Line>();
    
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
    	
    	createNewTownButton(town);
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
    	Node<?> startTown =  Main.graph.getNodeByTownName(firstTownConnect.getText());
    	Node<?> endTown = Main.graph.getNodeByTownName(secondTownConnect.getText());
    	
    	// create a new edge with ease, danger and distance supplied from GUI.
    	Edge edge = new Edge(Integer.parseInt(distance.getText()), Integer.parseInt(ease.getText()), Integer.parseInt(danger.getText()));
    	edge.setSource(startTown);
    	edge.setDest(endTown);
    	
    	// connect the two towns and add a line between them.
    	addLineBetweenConnectedTowns(startTown, endTown);
    	Main.graph.connect(startTown, endTown, edge);
    	clearConnectTownTextBoxes();
    }
    
    public void addLineBetweenConnectedTowns(Node<?> startTown, Node<?> endTown) {
    	Line edgeLine = new Line();
    	edgeLine.setStyle("-fx-view-order: -100");
    	edgeLine.setStrokeWidth(3);
    	edgeLine.setStartX(((Town) startTown.getContents()).getX());
    	edgeLine.setStartY(((Town) startTown.getContents()).getY());
    	edgeLine.setEndX(((Town) endTown.getContents()).getX());
    	edgeLine.setEndY(((Town) endTown.getContents()).getY());
    	
    	// When a town button is hovered over.
    	edgeLine.setOnMouseEntered(new EventHandler<MouseEvent>() {
    		public void handle(MouseEvent event) {
    			Edge thisEdge = startTown.getAdjMap().get(endTown);
    			hoverOverTownName.setText("DA: "+thisEdge.getDanger()+" DI: "+thisEdge.getDistance()+" EA: "+thisEdge.getEase());
    			hoverOverTownName.setLayoutX(event.getX()+25);
    			hoverOverTownName.setLayoutY(event.getY()-25);
    			hoverOverTownName.setVisible(true);
    			hoverOverTownName.toFront();
    		}
    	});
    	
    	// When a  town button is hovered over AND THEN EXITED.
    	edgeLine.setOnMouseExited(new EventHandler<MouseEvent>() {
    		public void handle(MouseEvent event) {
    			hoverOverTownName.setVisible(false);
    		}
    	});
    	
    	mapPane.getChildren().add(edgeLine);
    	//edgeLine.toBack();
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
    		connectTownsButton.setVisible(true);
    		
    	} else {
    		addTownPane.setVisible(true);
    		connectTownsButton.setVisible(false);
    		addTownsButton.setText("Finish");
    	}
    }
    
    public void clearAddTownTextBoxes() {
    	addTownName.setText("");
    	addTownX.setText("");
    	addTownY.setText("");
    }
    
    public void clearConnectTownTextBoxes() {
    	firstTownConnect.setText("");
    	secondTownConnect.setText("");
    	danger.setText("");
    	ease.setText("");
    	distance.setText("");
    }
    
    @FXML
    void connectTownsClicked(MouseEvent event) {
    	if (connectTownsPane.visibleProperty().get()) {
    		connectTownsPane.setVisible(false);
    		connectTownsButton.setText("Connect Towns");
    		addTownsButton.setVisible(true);
    		clearConnectTownTextBoxes();
    	} else {
    		connectTownsPane.setVisible(true);
    		connectTownsButton.setText("Finish");
    		addTownsButton.setVisible(false);
    	}
    }
    
    @FXML
    void pressToStartClicked(MouseEvent event) {
    	pressToStartButton.setVisible(false);
    }

    @FXML
    void findRouteButtonClicked(MouseEvent event) {
    	pathListTextBox.setText("");
    	
    	// removing any data relating to permutations and previous routes displayed
    	previousRouteButton.setDisable(true);
    	nextRouteButton.setDisable(true);
    	permutationNumberLabel.setText("");
    	allPermutations.clear();
    	
    	// declaring the start and end town if selected.
    	if (fromTownName.getText().length() == 0 || toTownName.getText().length() == 0) return;
    	Node<?> startTown =  Main.graph.getNodeByTownName(fromTownName.getText());
    	Node<?> endTown = Main.graph.getNodeByTownName(toTownName.getText());
    	
    	// determining which path type is selected via radio buttons.
    	String pathType = "";
    	if (distanceRadio.isSelected()) pathType = "distance";
    	else if (easeRadio.isSelected()) pathType = "ease";
    	else pathType = "danger";
    	
    	// Highlighting and listing the edges and town names.
    	
    	if (townsToGoThrough.size() > 0 ) {
    		highlightAndListRoute(Main.graph.findShortestPathWithWaypoints(startTown, endTown, pathType, townsToGoThrough));
    	} else {
    		highlightAndListRoute(Main.graph.findShortestPath(startTown, endTown, pathType));
    	}
    }
    
    public void highlightAndListRoute(ArrayList<Node<?>> path) {
    	pathListTextBox.setText("");
    	
    	// remove old highlighted path and clear the highlighted path list.
    	for (int i = 0; i < highlightedPathLines.size(); i++) {
    		mapPane.getChildren().remove(highlightedPathLines.get(i));
    	}
    	highlightedPathLines.clear();
    	
    	for (int i = 0; i < path.size(); i++) {
    		
    		Line edgeLine = new Line();
    		edgeLine.setStyle("-fx-stroke: red");
    		highlightedPathLines.add(edgeLine);
        	edgeLine.setStrokeWidth(5);
        	
        	
        	Town townFromNode = ((Town) path.get(i).getContents());
    		pathListTextBox.setText(pathListTextBox.getText()+townFromNode.getName()+", \n");
    		
    		
        	// if there's no next node to add a line between
        	if (i == path.size()-1) {
        		return;
        	}
        	
        	edgeLine.setStartX((((Town) path.get(i).getContents()).getX()));
        	edgeLine.setStartY((((Town) path.get(i).getContents()).getY()));
        	
        	edgeLine.setEndX((((Town) path.get(i+1).getContents()).getX()));
        	edgeLine.setEndY((((Town) path.get(i+1).getContents()).getY()));
        	mapPane.getChildren().add(edgeLine);
    		
    		
    	}
    	
    	updateOrder();
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
    
    public void createTownButtons() {
    	mapPane.getChildren().forEach(node -> {
    		if (node.getOpacity() < 0.3) {
    			String townName = ((Labeled) node).getText();
    			((ButtonBase) node).setOnAction(new TownButtonHandler(this, townName));
    		}
    	});
    }
    
    @FXML
    void cancelButtonClicked(MouseEvent event) {
    	cleanState();
    	for (int i = 0; i < highlightedPathLines.size(); i++) {
    		mapPane.getChildren().remove(highlightedPathLines.get(i));
    	}
    	highlightedPathLines.clear();
    }
    
    public void cleanState() {
    	fromTownName.setText("");
    	toTownName.setText("");
    	townsToGoThrough.clear();
    	waypointListTexBox.setText("");
    	pathListTextBox.setText("");
    	previousRouteButton.setDisable(true);
    	nextRouteButton.setDisable(true);
    	allPermutations.clear();
    }
    
    public void createNewTownButton(Town town) {
    	
    	Button button = new Button();
    	button.setPrefHeight(townButton.getHeight());
    	button.setPrefWidth(townButton.getWidth());
    	button.setLayoutX(town.getX()-townButton.getHeight()/2);
    	button.setLayoutY(town.getY()-townButton.getWidth()/2);
    	button.setStyle("-fx-view-order: 100");
    	button.setText(town.getName());
    	
    	// When a town button is hovered over.
    	button.setOnMouseEntered(new EventHandler<MouseEvent>() {
    		public void handle(MouseEvent event) {
    			hoverOverTownName.setText(town.getName());
    			hoverOverTownName.setLayoutX(button.getLayoutX()+25);
    			hoverOverTownName.setLayoutY(button.getLayoutY()-25);
    			hoverOverTownName.setVisible(true);
    			hoverOverTownName.toFront();
    		}
    	});
    	
    	// When a  town button is hovered over AND THEN EXITED.
    	button.setOnMouseExited(new EventHandler<MouseEvent>() {
    		public void handle(MouseEvent event) {
    			hoverOverTownName.setVisible(false);
    		}
    	});
    	
    	
    	/**
    	 * When a town button is clicked, it will deal with it in a number of ways
    	 * depending on what the state of the program is. 
    	 */
    	button.setOnMouseClicked(new EventHandler<MouseEvent>() {
    		
    		public void handle(MouseEvent event) {
    			if (connectTownsPane.visibleProperty().get()) {
    				townSelectedForConnection(town);
    			}
    			else {
    				// first town selected (start point)
    				if (fromTownName.getText().length() == 0) {
    					fromTownName.setText(town.getName());
    				// second town selected (end point)
    				} else if (toTownName.getText().length() == 0) {
    					toTownName.setText(town.getName());
    				} else {
    					// the next down selected will be a waypoint. If it already exists as a waypoint, start point or end point it is not added again.
    					if (!townsToGoThrough.contains(Main.graph.getNodeByTownName(town.getName())) || (!toTownName.getText().equals(town.getName()) && !fromTownName.getText().equals(town.getName())) ) {
    						townsToGoThrough.add(Main.graph.getNodeByTownName(town.getName()));
    						waypointListTexBox.setText(waypointListTexBox.getText()+town.getName()+", \n");
    					}
    				}
    			}
    		}
    	});
    	
    	mapPane.getChildren().add(button);
    	updateOrder();
    }
    
    public void updateOrder() {
    	for (int i = 0; i < mapPane.getChildren().size(); i++) {
    		if (mapPane.getChildren().get(i) instanceof Button) {
    			mapPane.getChildren().get(i).toFront();
    		}
    	}
    }
    
    @FXML
    void newButtonClicked(ActionEvent event) {
    	Main.graph = new Graph();
    	clearMapPane();
    	
    }
    
    @FXML
    void quitButtonClicked(ActionEvent event) {

    }

    @FXML
    void saveButtonClicked(ActionEvent event) throws IOException {
    	Main.saveGraph();
    }
    
    @FXML
    void loadButtonClicked(ActionEvent event) throws FileNotFoundException, IOException {
    	cleanState();
    	Main.loadGraph();
    	ArrayList<Node<?>> nodes = Main.graph.getNodes();
    	ArrayList<Edge> edges = Main.graph.getEdges();
    	clearMapPane();
    	
    	for (int i = 0; i < nodes.size(); i++) {
    		createNewTownButton(((Town) nodes.get(i).getContents()));
    	}
    	
    	for (int i = 0; i < edges.size(); i++) {
    		addLineBetweenConnectedTowns(edges.get(i).getSource(), edges.get(i).getDest());
    	}
    }
    
    public void clearMapPane() {
    	ArrayList<Object> nodesToRemove = new ArrayList<>();
    	
    	for (int i = 0; i < mapPane.getChildren().size(); i++) {
    		if (mapPane.getChildren().get(i) instanceof Button || mapPane.getChildren().get(i) instanceof Line) {
    			nodesToRemove.add(mapPane.getChildren().get(i));
    		}
    	}
    	mapPane.getChildren().removeAll(nodesToRemove);
    }
    
    @FXML
    void previousRouteButtonClicked(MouseEvent event) {
    	// a condition to allow wrapping the viewing of permutations
    	if (currentPermutation == 0) {
    		currentPermutation = allPermutations.size();
    	}
    	
    	currentPermutation = currentPermutation - 1;
    	permutationNumberLabel.setText(Integer.toString(currentPermutation+1));
    	highlightAndListRoute(allPermutations.get(currentPermutation));
    }
    
    @FXML
    void nextRouteButtonClicked(MouseEvent event) {
    	// a condition to allow wrapping the viewing of permutations
    	if (currentPermutation + 1 > allPermutations.size() - 1) {
    		currentPermutation = -1;
    	}
    	
    	currentPermutation = currentPermutation + 1;
    	permutationNumberLabel.setText(Integer.toString(currentPermutation+1));
    	highlightAndListRoute(allPermutations.get(currentPermutation));
    }
    
    @FXML
    void findAllRouteButtonClicked(MouseEvent event) {
    	if (fromTownName.getText().length() == 0 || toTownName.getText().length() == 0) return;
    	Node<?> startTown =  Main.graph.getNodeByTownName(fromTownName.getText());
    	Node<?> endTown = Main.graph.getNodeByTownName(toTownName.getText());
    	
    	permutationNumberLabel.setText(Integer.toString(1));
    	allPermutations = Main.graph.findPathPermutations(startTown, endTown, new ArrayList<Node<?>>());
    	highlightAndListRoute(allPermutations.get(0));
    	previousRouteButton.setDisable(false);
    	nextRouteButton.setDisable(false);
    }
}
