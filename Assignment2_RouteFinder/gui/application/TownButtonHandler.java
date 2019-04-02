package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

class TownButtonHandler implements EventHandler<ActionEvent> {
	private String town;
	private Controller controller;
	
    TownButtonHandler(Controller controller, String town) {
        this.town = town;
    }
    
    @Override
    public void handle(ActionEvent event) {
        System.out.println(town);
        //controller.selectingPath(town);
    }
}
