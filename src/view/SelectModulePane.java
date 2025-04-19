package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.Block;
import model.Course;
import model.Module;
import model.StudentProfile;

public class SelectModulePane extends GridPane {
	
	
	private Label b1, b2, b3, b4, b5 ,b6;
	
//	private StackPane block1, block2, block3, block4; TextArea t1,t2,t3,block5, block6;
	private TextField block5, block6;

	private ListView<Module> block1, block2, block3, block4;
	private ObservableList<Module> modules;
	
	private Block bl1;
	private Block bl2;
	private Block bl3;
	
	private Button but1, but2, but3, but4;
	
	
	
	public SelectModulePane() {
		
		
		modules = FXCollections.observableArrayList();
		
		b1 = new Label("Selected Block 1 modules");
		b2 = new Label("Selected Block 2 modules");

		
		block1 = new ListView<Module>();
		block2 = new ListView<Module>();
		block1.getSelectionModel().setSelectionMode(null);
		block2.getSelectionModel().setSelectionMode(null);
		
		block1.setPrefSize(400, 150);
	
		block2.setPrefSize(400, 150);
		
		
		VBox box1 = new VBox(b1,block1);
		VBox box2 = new VBox(b2, block2);
		VBox box3 = new VBox(box1, box2);
		box3.setSpacing(15);
		
		b3 = new Label("Unselected Block 3/4 modules");
		b4 = new Label("Selected Block 3/4 modules");
		
		block3 = new ListView<Module>();
		block4 = new ListView<Module>();
		block3.setPrefSize(400, 130);
		block4.setPrefSize(400, 130);
		block3.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		block4.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		b5 = new Label("Block 3/4");
		but1 = new Button("Add");
//		but1.setOnAction(e -> addModules());
		but1.setPrefSize(80, 10);
		but2 = new Button("Remove");
//		but2.setOnAction(e -> removeModules());
		but2.setPrefSize(80, 10);
		HBox hbox2 = new HBox(b5, but1, but2);
		hbox2.setSpacing(15);
		hbox2.setAlignment(Pos.CENTER);
		
		VBox box4 = new VBox(b3, block3);
		VBox box5 = new VBox(b4, block4);
		VBox box6 = new VBox(box4, hbox2, box5);
		box6.setAlignment(Pos.CENTER);
		box6.setSpacing(15);
		
		HBox hbox1 = new HBox(box3,box6);
		hbox1.setSpacing(15);
		hbox1.setPadding(new Insets(15,15,15,15));

		b6 = new Label("Current credits:");
		block6 = new TextField("0");
		block6.setPrefSize(50,10);
		block6.setEditable(false);
		HBox credits = new HBox(b6, block6);
		credits.setAlignment(Pos.CENTER);
		credits.setSpacing(10);
		
		but3 = new Button("Reset");
//		but3.setOnAction(e -> reset());
		but3.setPrefSize(80, 10);
		but4 = new Button("Submit");
		
		but4.setPrefSize(80, 10);
		
		HBox submit = new HBox(but3, but4);
		submit.setSpacing(15);
		submit.setAlignment(Pos.CENTER);
		VBox last = new VBox(credits,submit);
		last.setSpacing(30);
		hbox1.setAlignment(Pos.CENTER);
		VBox last2 = new VBox(hbox1, last);
		
		bl1 = Block.BLOCK_1;
		bl2 = Block.BLOCK_2;
		bl3 = Block.BLOCK_3_4;
		
		
		this.setAlignment(Pos.CENTER);
		GridPane.setHgrow(b1, Priority.ALWAYS);
		GridPane.setVgrow(b1, Priority.ALWAYS);
		GridPane.setHgrow(block1, Priority.ALWAYS);
		GridPane.setVgrow(block1, Priority.ALWAYS);
		GridPane.setHgrow(b2, Priority.ALWAYS);
		GridPane.setVgrow(b2, Priority.ALWAYS);
		GridPane.setHgrow(block2, Priority.ALWAYS);
		GridPane.setVgrow(block2, Priority.ALWAYS);
		GridPane.setHgrow(b3, Priority.ALWAYS);
		GridPane.setVgrow(b3, Priority.ALWAYS);
		GridPane.setHgrow(block3, Priority.ALWAYS);
		GridPane.setVgrow(block3, Priority.ALWAYS);
		GridPane.setHgrow(b4, Priority.ALWAYS);
		GridPane.setVgrow(b4, Priority.ALWAYS);
		GridPane.setHgrow(b5, Priority.ALWAYS);
		GridPane.setVgrow(b5, Priority.ALWAYS);
		GridPane.setHgrow(block4, Priority.ALWAYS);
		GridPane.setVgrow(block4, Priority.ALWAYS);
		GridPane.setHgrow(block6, Priority.ALWAYS);
		GridPane.setVgrow(block6, Priority.ALWAYS);
		GridPane.setHgrow(b6, Priority.ALWAYS);
		GridPane.setVgrow(b6, Priority.ALWAYS);
		GridPane.setHgrow(but1, Priority.ALWAYS);
		GridPane.setVgrow(but1, Priority.ALWAYS);
		GridPane.setHgrow(but2, Priority.ALWAYS);
		GridPane.setVgrow(but2, Priority.ALWAYS);
		GridPane.setHgrow(but3, Priority.ALWAYS);
		GridPane.setVgrow(but3, Priority.ALWAYS);
		GridPane.setHgrow(but4, Priority.ALWAYS);
		GridPane.setVgrow(but4, Priority.ALWAYS);
		GridPane.setHgrow(box1, Priority.ALWAYS);
		GridPane.setVgrow(box1, Priority.ALWAYS);
		GridPane.setHgrow(box2, Priority.ALWAYS);
		GridPane.setVgrow(box2, Priority.ALWAYS);
		GridPane.setHgrow(box3, Priority.ALWAYS);
		GridPane.setVgrow(box3, Priority.ALWAYS);
		GridPane.setHgrow(box4, Priority.ALWAYS);
		GridPane.setVgrow(box4, Priority.ALWAYS);
		GridPane.setHgrow(box5, Priority.ALWAYS);
		GridPane.setVgrow(box5, Priority.ALWAYS);
		GridPane.setHgrow(box6, Priority.ALWAYS);
		GridPane.setVgrow(box6, Priority.ALWAYS);
		GridPane.setHgrow(hbox1, Priority.ALWAYS);
		GridPane.setVgrow(hbox1, Priority.ALWAYS);
		GridPane.setHgrow(last, Priority.ALWAYS);
		GridPane.setVgrow(last, Priority.ALWAYS);
		GridPane.setHgrow(last2, Priority.ALWAYS);
		GridPane.setVgrow(last2, Priority.ALWAYS);
		GridPane.setHgrow(credits, Priority.ALWAYS);
		GridPane.setVgrow(credits, Priority.ALWAYS);
		GridPane.setHgrow(submit, Priority.ALWAYS);
		GridPane.setVgrow(submit, Priority.ALWAYS);
		this.setPadding(new Insets(10,10,20,10));
		this.getChildren().addAll(last2);
		
	}
	
	
	public void setModules(Course course) {

		modules.clear();
		course.getAllModulesOnCourse().forEach(e -> modules.add(e));
		block1.getItems().clear();
		block2.getItems().clear();
		block3.getItems().clear();
		block4.getItems().clear();
		block6.setText("90");
		for(int i = 0; i< modules.size(); i++) {
			
			if(modules.get(i).getRunPlan().equals(bl1)) {
				block1.getItems().add(modules.get(i));
				
			}
			if(modules.get(i).getRunPlan().equals(bl2)) {
				block2.getItems().add(modules.get(i));
			}
			if(modules.get(i).getRunPlan().equals(bl3)
					&& modules.get(i).isMandatory() == false) {
				block3.getItems().add(modules.get(i));
			}
			if(modules.get(i).getRunPlan().equals(bl3)
					&& modules.get(i).isMandatory() == true) {
				block4.getItems().add(modules.get(i));
			}
		}
		
	}
	public void addModule(Module module) {
		if(block4.getItems().size()<2) {
			block4.getItems().add(module);
			block3.getItems().remove(module);
		}
	}
	
	public void addModules() {
		
		if(block4.getItems().size() < 2 ){
			block4.getItems().add(block3.getSelectionModel().getSelectedItem());
			block3.getItems().removeAll(block3.getSelectionModel().getSelectedItems());
			
			
			
				
			
		}
		
	}
	public void addAddModulesHandler(EventHandler<ActionEvent> handler) {
		but1.setOnAction(handler);
	}
	public void addRemoveModulesHandler(EventHandler<ActionEvent> handler) {
		but2.setOnAction(handler);
	}
	public void addResetModulesHandler(EventHandler<ActionEvent> handler) {
		but3.setOnAction(handler);
	}
	
	
	public void removeModules() {
		if(block4.getItems().size()>1 && block4.getSelectionModel().getSelectedItem().isMandatory() == false) {
			block3.getItems().addAll(block4.getSelectionModel().getSelectedItems());
			block4.getItems().removeAll(block4.getSelectionModel().getSelectedItems());
			
			
			
		}
	}
	public void removeModule(Module m) {
		if(block4.getSelectionModel().getSelectedItem().isMandatory() == false) {
			block4.getItems().remove(m);
			block3.getItems().add(m);
		}
	}
	public ObservableList<Module> getModules() {
		 ObservableList<Module> modules2 = FXCollections.observableArrayList();
		 
		 modules2.addAll(block1.getItems());
		 modules2.addAll(block2.getItems());
		 modules2.addAll(block4.getItems());
		 return modules2;
	}
	
	public void reset() {
		block3.getItems().addAll(block4.getItems());
		block4.getItems().removeAll(block4.getItems());
		
	}
	public Module getRemoveItem() {
		return block4.getSelectionModel().getSelectedItem();
	}
	public Module getAddItem() {
		return block3.getSelectionModel().getSelectedItem();
	}
	public String getCredits() {
		return block6.getText();
	}
	public void addSubmitHandler(EventHandler<ActionEvent> handler) {
		but4.setOnAction(handler);
	}
	public ObservableList<Module> getReserveModules(){
		return block3.getItems();
	}
	public ObservableList<Module> getSelectedModules(){
		return block4.getItems();
	}
	public void setCredits(int credit) {
		block6.setText(""+credit);
	}
	
}
