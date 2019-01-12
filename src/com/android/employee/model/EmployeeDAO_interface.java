package com.android.employee.model;

import java.util.List;

public interface EmployeeDAO_interface {

	public void insert(EmployeeVO employeeVO); //新增員工
	public void update(EmployeeVO employeeVO); //修改員工
	public EmployeeVO findByPK(String empID); //用PK找到對應的員工
	public List<EmployeeVO> getAll(); //取得全部員工列表
	public String findAcc(String memAcc);
	
	//是否有這個員工
	public EmployeeVO getEmpByAccPsw(String empAcc, String empPsw);
	
	public String getEmpBraName(String empId);   //查員工在哪個分店
	
}
