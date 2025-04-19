package view;

import java.io.Serializable;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import model.Course;
import model.Name;
import model.StudentProfile;

public class OverviewSelectionPane extends VBox implements Serializable{

	public TextArea text1,text2,text3;
	
	public Button button1;
	
	public OverviewSelectionPane() {
		
		text1 = new TextArea("Profile will appear here");
		text1.setPrefSize(600, 175);
		
		text2 = new TextArea("Selected modules will appear here");
		text2.setPrefSize(400, 175);
		
		text3 = new TextArea("Reserved modules will appear here");
		text3.setPrefSize(400, 175);
		
		
		
		
		HBox hbox1 = new HBox(text2,text3);
		hbox1.setSpacing(25);
		hbox1.setAlignment(Pos.CENTER);
		
		button1= new Button("Save Overview");
		button1.setPrefSize(100, 20);
		
		this.getChildren().addAll(text1,hbox1, button1);
		this.setPadding(new Insets(40,40,40,40));
		this.setSpacing(25);
		this.setAlignment(Pos.CENTER);
		
	}
	
	public void setProfile(String name, String pnumber, String email, String date, String course) {
		if(date==null) {
			date = "No date set";
		}
		text1.setText("Name: "+name + "\nPNo: " + pnumber+"\nEmail: "+email+"\nDate: " + date+"\nCourse: " + course);
	}
	public void setSelectedModules(StudentProfile student) {
		text2.setText("Selected modules:\n=========\n");
		
			student.getAllSelectedModules().forEach(e -> text2.appendText("Module Code: "+e.getModuleCode()+" Module Name: "+e.getModuleName()+"\n"
					+ " Credits: "+e.getModuleCredits() + " Mandatory on your course? "+ e.isMandatory() +" Block: " + e.getRunPlan()+"\n\n"));
		
		
	}
	public void setReservedModules(StudentProfile student) {
		text3.setText("Reserved modules:\n========\n");
		student.getAllReservedModules().forEach(e -> text3.appendText("Module Code: "+e.getModuleCode()+" Module Name: "+e.getModuleName()+"\n"
				+ " Credits: "+e.getModuleCredits() + " Mandatory on your course? "+ e.isMandatory() +" Block: " + e.getRunPlan()+"\n\n"));;
	}
	public void reset() {
		text1.setText("Profile will appear here");
		text2.setText("Selected modules will appear here");
		text3.setText("Reserved modules will appear here");
	}
	public String getProfile() {
		return text1.getText();
	}
	public String getSelectedModules() {
		return text2.getText();
	}
	public String getReservedModules() {
		return text3.getText();
	}
	public void addSaveHandler(EventHandler <ActionEvent> a) {
		button1.setOnAction(a);
	}
}
