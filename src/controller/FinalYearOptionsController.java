package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import model.Block;
import model.Course;
import model.Module;
import model.StudentProfile;
import view.FinalYearOptionsRootPane;
import view.OverviewSelectionPane;
import view.ReserveModulePane;
import view.SelectModulePane;
import view.CreateStudentProfilePane;
import view.FinalYearOptionsMenuBar;

public class FinalYearOptionsController {

	//fields to be used throughout class
	private FinalYearOptionsRootPane view;
	private StudentProfile model;
	
	private CreateStudentProfilePane cspp;
	private SelectModulePane smp;
	private ReserveModulePane rmp;
	private OverviewSelectionPane osp;
	private FinalYearOptionsMenuBar mstmb;

	

	public FinalYearOptionsController(FinalYearOptionsRootPane view, StudentProfile model) {
		//initialise view and model fields
		this.view = view;
		this.model = model;

		//initialise view subcontainer fields
		cspp = view.getCreateStudentProfilePane();
		mstmb = view.getModuleSelectionToolMenuBar();
		smp = view.getSelectModulePane();
		rmp = view.getReserveModulePane();
		osp = view.getOverviewselectionPane();
		
		//add courses to combobox in create student profile pane using the buildModulesAndCourses helper method below
		cspp.addCourseDataToComboBox(buildModulesAndCourses());

		//attach event handlers to view using private helper method
		this.attachEventHandlers();
	}

	
	//helper method - used to attach event handlers
	private void attachEventHandlers() {
		//attach an event handler to the create student profile pane
		cspp.addCreateStudentProfileHandler(new CreateStudentProfileHandler());
		smp.addSubmitHandler(new SelectStudentModules());
		smp.addResetModulesHandler(new resetEventModules());
		smp.addRemoveModulesHandler(new removeModule());
		rmp.addReserveModuleHandler(new ReserveModules());
		smp.addAddModulesHandler(new addModule());
		rmp.addAddHandler(new addReserveModule());
		rmp.addRemoveHandler(new removeReserveModule());
		osp.addSaveHandler(new SaveMenuHandler());
		//attach an event handler to the menu bar that closes the application
		mstmb.addExitHandler(e -> System.exit(0));
		mstmb.addLoadHandler(new LoadMenuHandler());
		mstmb.addSaveHandler(new SaveMenuHandler());
		mstmb.addAboutHandler(e -> this.alertDialogBuilder(AlertType.INFORMATION, "Information Dialog", null, "Final Year Module Selection Tool"));
		
	}
	
	//event handler (currently empty), which can be used for creating a profile
	private class CreateStudentProfileHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			model = new StudentProfile();
			model.setStudentCourse(cspp.getSelectedCourse());
			model.setStudentEmail(cspp.getStudentEmail());
			model.setStudentName(cspp.getStudentName());
			model.setStudentPnumber(cspp.getStudentPnumber());
			model.setSubmissionDate(cspp.getStudentDate());
			
			smp.setModules(model.getStudentCourse());
			
			view.changeTab(1);
			
		}
	}
	private class removeReserveModule implements EventHandler<ActionEvent>{
		public void handle(ActionEvent e) {
			rmp.removeModule();
		}
	}
	private class addReserveModule implements EventHandler<ActionEvent>{
		public void handle(ActionEvent e) {
			rmp.addModule();
		}
	}
	private class addModule implements EventHandler<ActionEvent>{
		public void handle(ActionEvent e) {
			smp.addModule(smp.getAddItem());
			smp.setCredits(120);
		}
	}
	public class removeModule implements EventHandler<ActionEvent>{
		public void handle(ActionEvent e) {
			
			smp.removeModule(smp.getRemoveItem());
			smp.setCredits(90);
		}
	}
	
	private class resetEventModules implements EventHandler<ActionEvent>{
		public void handle(ActionEvent e) {
			smp.setModules(model.getStudentCourse());
		}
	}
	
	private class SelectStudentModules implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			if(smp.getCredits().equals("120")) {
			smp.getModules().forEach(m -> model.addSelectedModule(m));
			rmp.addReserveModules(smp.getReserveModules());
			
			
			view.changeTab(2);
			}
			
		}
	}
	
	private class ReserveModules implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			if(rmp.getModule().size() == 1) {
				rmp.confirm();
				model.addReservedModule(rmp.getModule().get(0));
				model.setSubmissionDate(LocalDate.now());
				osp.setProfile(model.getStudentName().toString(), model.getStudentPnumber().toString(), model.getStudentEmail().toString(), model.getSubmissionDate().toString(), model.getStudentCourse().toString());
				osp.setSelectedModules(model);
				osp.setReservedModules(model);
				view.changeTab(3);
			}
			
		}
	}
	private class SaveMenuHandler implements EventHandler<ActionEvent> {

		public void handle(ActionEvent e) {
			//save the data model
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("studentProfileObj.dat"))) {

				oos.writeObject(model);
				oos.flush();
				oos.close();
				
				alertDialogBuilder(AlertType.INFORMATION, "Information Dialog", "Save success", "Student Profile saved to studentProfileObj.dat");
			}
			catch (IOException ioExcep){
				System.out.println("Error saving");
			}
			
		}
	}
	
	private class LoadMenuHandler implements EventHandler<ActionEvent> {

		public void handle(ActionEvent e) {
			//load in the data model
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("studentProfileObj.dat"))) {

				model = (StudentProfile) ois.readObject();	

				alertDialogBuilder(AlertType.INFORMATION, "Information Dialog", "Load success", "Student Profile loaded from studentProfileObj.dat");
			}
			catch (IOException ioExcep){
				System.out.println("Error loading");
			}
			catch (ClassNotFoundException c) {
				System.out.println("Class Not found");
			}
			
			view.clearContents();
			cspp.setcourse(model.getStudentCourse());
			cspp.setEmail(model.getStudentEmail());
			cspp.setFirstName(model.getStudentName().getFirstName());
			cspp.setLastName(model.getStudentName().getFamilyName());
			cspp.setPnum(model.getStudentPnumber());
			cspp.setDate(model.getSubmissionDate());;
			
			

		}
	}

	private void alertDialogBuilder(AlertType type, String title, String header, String content) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}
	


	//helper method - builds modules and course data and returns courses within an array
	private Course[] buildModulesAndCourses() {
		Module ctec3701 = new Module("CTEC3701", "Software Development: Methods & Standards", 30, true, Block.BLOCK_1);

		Module ctec3702 = new Module("CTEC3702", "Big Data and Machine Learning", 30, true, Block.BLOCK_2);
		Module ctec3703 = new Module("CTEC3703", "Mobile App Development and Big Data", 30, true, Block.BLOCK_2);

		Module ctec3451 = new Module("CTEC3451", "Development Project", 30, true, Block.BLOCK_3_4);

		Module ctec3704 = new Module("CTEC3704", "Functional Programming", 30, false, Block.BLOCK_3_4);
		Module ctec3705 = new Module("CTEC3705", "Advanced Web Development", 30, false, Block.BLOCK_3_4);

		Module imat3711 = new Module("IMAT3711", "Privacy and Data Protection", 30, false, Block.BLOCK_3_4);
		Module imat3722 = new Module("IMAT3722", "Fuzzy Logic and Inference Systems", 30, false, Block.BLOCK_3_4);

		Module ctec3706 = new Module("CTEC3706", "Embedded Systems and IoT", 30, false, Block.BLOCK_3_4);


		Course compSci = new Course("Computer Science");
		compSci.addModule(ctec3701);
		compSci.addModule(ctec3702);
		compSci.addModule(ctec3451);
		compSci.addModule(ctec3704);
		compSci.addModule(ctec3705);
		compSci.addModule(imat3711);
		compSci.addModule(imat3722);

		Course softEng = new Course("Software Engineering");
		softEng.addModule(ctec3701);
		softEng.addModule(ctec3703);
		softEng.addModule(ctec3451);
		softEng.addModule(ctec3704);
		softEng.addModule(ctec3705);
		softEng.addModule(ctec3706);

		Course[] courses = new Course[2];
		courses[0] = compSci;
		courses[1] = softEng;

		return courses;
	}

}
