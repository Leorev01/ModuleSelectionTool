package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;


public class StudentProfile implements Iterable<Module>, Serializable{

	private String studentPnumber;
	private Name studentName;
	private String studentEmail;
	private LocalDate studentDate;
	private Course studentCourse;
	private Set<Module> selectedModules;
	private Set<Module> reservedModules;
	
	public StudentProfile() {
		studentPnumber = "";
		studentName = new Name();
		studentEmail = "";
		studentDate = null;
		studentCourse = null;
		selectedModules = new TreeSet<Module>();
		reservedModules = new TreeSet<Module>();
	}
	
	public String getStudentPnumber() {
		return studentPnumber;
	}
	
	public void setStudentPnumber(String studentPnumber) {
		this.studentPnumber = studentPnumber;
	}
	
	public Name getStudentName() {
		return studentName;
	}
	
	public void setStudentName(Name studentName) {
		this.studentName = studentName;
	}
	
	public String getStudentEmail() {
		return studentEmail;
	}
	
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	
	public LocalDate getSubmissionDate() {
		return studentDate;
	}
	
	public void setSubmissionDate(LocalDate studentDate) {
		this.studentDate = studentDate;
	}
	
	public Course getStudentCourse() {
		return studentCourse;
	}
	
	public void setStudentCourse(Course studentCourse) {
		this.studentCourse = studentCourse;
	}
	
	public boolean addSelectedModule(Module m) {
		return selectedModules.add(m);
	}
	
	public Set<Module> getAllSelectedModules() {
		return selectedModules;
	}
	
	public void clearSelectedModules() {
		selectedModules.clear();
	}
	
	public boolean addReservedModule(Module m) {
		return reservedModules.add(m);
	}
	
	public Set<Module> getAllReservedModules() {
		return reservedModules;
	}
	
	public void clearReservedModules() {
		reservedModules.clear();
	}
	
	@Override
	public String toString() {
		return "StudentProfile:[Pnumber=" + studentPnumber + ", studentName="
				+ studentName + ", studentEmail=" + studentEmail + ", studentDate="
				+ studentDate + ", studentCourse=" + studentCourse.actualToString() 
				+ ", selectedModules=" + selectedModules
				+ ", reservedModules=" + reservedModules + "]";
	}
	@Override
	public Iterator<Module> iterator() {
		return selectedModules.iterator();
		
	}
	@Override
	public int hashCode() {
		final int HASH_MULTIPLIER = 29; 
		int h = HASH_MULTIPLIER * studentPnumber.hashCode()+ studentName.hashCode();
		h = HASH_MULTIPLIER * h + studentEmail.hashCode() + studentDate.hashCode() + studentCourse.hashCode()
		+selectedModules.hashCode()+reservedModules.hashCode();
		
		return h;
}
	
}
