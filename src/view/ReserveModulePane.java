package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Module;

public class ReserveModulePane extends VBox{

	Label lab1, lab2, lab3;
	ListView<Module> text1, text2;
	Button but1, but2, but3;
	
	
	public ReserveModulePane() {
		
		lab1 = new Label("Unselected Block 3/4 modules");
		text1 = new ListView<Module>();
		text1.setPrefSize(350, 350);
		VBox vbox1 = new VBox(lab1,text1);
		text1.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		
		lab2 = new Label("Unselected Block 3/4 modules");
		text2 = new ListView<Module>();
		text2.setPrefSize(350, 350);
		VBox vbox2 = new VBox(lab2,text2);
		text2.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		HBox hbox1 = new HBox(vbox1, vbox2);
		hbox1.setSpacing(15);
		hbox1.setPadding(new Insets(10,10,10,10));
		hbox1.setAlignment(Pos.CENTER);
		
		lab3 = new Label("Reserve one optional module");
		but1 = new Button("Add");
		
		but1.setPrefSize(80, 10);
		but2 = new Button("Remove");
		but2.setPrefSize(80, 10);
		
		but3 = new Button("Confirm");
		but3.setOnAction(e -> confirm());
		but3.setPrefSize(80, 10);
		
		HBox hbox2 = new HBox(lab3, but1, but2, but3);
		hbox2.setSpacing(15);
		hbox2.setAlignment(Pos.CENTER);
		
		this.getChildren().addAll(hbox1,hbox2);
		this.setSpacing(15);
		this.setAlignment(Pos.CENTER);
		
	}
	public void addReserveModules(ObservableList<Module> module) {
		
		text1.getItems().addAll(module);
	}
	
	public void addModule() {
		if(text2.getItems().size() < 1) {
			text2.getItems().add(text1.getSelectionModel().getSelectedItem());
			text1.getItems().remove(text1.getSelectionModel().getSelectedItem());
		}
		
	}
	public void removeModule() {
		if(text2.getItems().size()>0) {
			if(text2.getSelectionModel().getSelectedItem()!= null) {
				text1.getItems().add(text2.getSelectionModel().getSelectedItem());
				text2.getItems().remove(text2.getSelectionModel().getSelectedItem());
			}
			
		}

	}
	public ObservableList<Module> getModule() {
		
			return text2.getItems();
		
	}
	public void confirm() {
		getModule();
	}
	public void addAddHandler(EventHandler<ActionEvent> handler) {
		but1.setOnAction(handler);
	}
	public void addRemoveHandler(EventHandler<ActionEvent> handler) {
		but2.setOnAction(handler);
	}
	
	public void addReserveModuleHandler(EventHandler<ActionEvent> handler) {
		but3.setOnAction(handler);
	}
	public void reset() {
		text1.getItems().clear();
		text2.getItems().clear();
		
	}
}
