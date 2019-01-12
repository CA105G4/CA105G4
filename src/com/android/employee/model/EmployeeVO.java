package com.android.employee.model;

public class EmployeeVO implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	private String empID;
	private String braID;
	private String empName;
	private String empJob;
	private String empTel;
	private Integer empState;
//	private byte[] empPic;
	private String empAcc;
	private String empPsw;
	
	public String getEmpAcc() {
		return empAcc;
	}
	public void setEmpAcc(String empAcc) {
		this.empAcc = empAcc;
	}
	public String getEmpPsw() {
		return empPsw;
	}
	public void setEmpPsw(String empPsw) {
		this.empPsw = empPsw;
	}
	public String getEmpID() {
		return empID;
	}
	public void setEmpID(String empID) {
		this.empID = empID;
	}
	public String getBraID() {
		return braID;
	}
	public void setBraID(String braID) {
		this.braID = braID;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpJob() {
		return empJob;
	}
	public void setEmpJob(String empJob) {
		this.empJob = empJob;
	}
	public String getEmpTel() {
		return empTel;
	}
	public void setEmpTel(String empTel) {
		this.empTel = empTel;
	}
	public Integer getEmpState() {
		return empState;
	}
	public void setEmpState(Integer empState) {
		this.empState = empState;
	}

	
	
}
