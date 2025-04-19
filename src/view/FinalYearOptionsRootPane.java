package view;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import model.StudentProfile;


public class FinalYearOptionsRootPane extends BorderPane {

	private CreateStudentProfilePane cspp;
	private FinalYearOptionsMenuBar mstmb;
	private SelectModulePane smp;
	private ReserveModulePane rmp;
	private OverviewSelectionPane osp;
	private TabPane tp, tp2, tp3, tp4;
	private StudentProfile model;
	
	public FinalYearOptionsRootPane() {
		//create tab pane and disable tabs from being closed
		tp = new TabPane();
		tp.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		
		tp2 =new TabPane();
		tp2.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		
		tp3 = new TabPane();
		tp3.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		
		tp4 = new TabPane();
		tp4.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		
		//create panes
		cspp = new CreateStudentProfilePane();
		smp = new SelectModulePane();
		rmp = new ReserveModulePane();
		osp = new OverviewSelectionPane();
		//create tabs with panes added
		Tab t1 = new Tab("Create Profile", cspp);
		Tab t2 = new Tab("Select Module", smp);
		Tab t3 = new Tab("Reserve Module", rmp);
		Tab t4 = new Tab("Overview Selection", osp);
		//add tabs to tab pane
		tp.getTabs().addAll(t1,t2,t3,t4);
		
		//create menu bar
		mstmb = new FinalYearOptionsMenuBar();
		
		//add menu bar and tab pane to this root pane
		this.setTop(mstmb);
		this.setCenter(tp);
		
		
	}

	//methods allowing sub-containers to be accessed by the controller.
	public CreateStudentProfilePane getCreateStudentProfilePane() {
		return cspp;
	}
	
	public FinalYearOptionsMenuBar getModuleSelectionToolMenuBar() {
		return mstmb;
	}
	
	public SelectModulePane getSelectModulePane() {
		return smp;
	}
	
	public ReserveModulePane getReserveModulePane() {
		return rmp;
	}
	
	public OverviewSelectionPane getOverviewselectionPane() {
		return osp;
	}
	
	//method to allow the controller to change tabs
	public void changeTab(int index) {
		tp.getSelectionModel().select(index);
	}
	
	public void clearContents() {
		model = new StudentProfile();
		osp.reset();
		smp.reset();
		rmp.reset();
		
		
	}
	public StudentProfile getContents(StudentProfile profile) {
		return profile;
	}
	public void setContents(StudentProfile profile) {
		tp.setUserData(profile);
		tp2.setUserData(profile);
		tp3.setUserData(profile);
		tp4.setUserData(profile);
	}

	
}
