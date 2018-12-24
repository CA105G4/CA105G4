package com.employee.model;

import java.util.List;

//EmployeeVO
public class EmployeeService {

	private EmployeeDAO_interface dao;

	public EmployeeService() {
		dao = new EmployeeJDBCDAO();
}
	public EmployeeVO addEmp(String braID, String empName, String empJob,
			String empTel,byte[] empPic, String empAcc, String empPsw) {

		EmployeeVO employeeVO = new EmployeeVO();
		
		employeeVO.setBraID(braID);
		employeeVO.setEmpName(empName);
		employeeVO.setEmpJob(empJob);
		employeeVO.setEmpTel(empTel);
		employeeVO.setEmpAcc(empAcc);
		employeeVO.setEmPsw(empPsw);
		employeeVO.setEmpPic(empPic);
		dao.insert(employeeVO);

		return employeeVO;
	}

	public EmployeeVO updateEmp(String braID, String empName, String empJob,
			String empTel, Integer empState, String empAcc, String empPsw,String empID ) {

		EmployeeVO employeeVO = new EmployeeVO();

		employeeVO.setBraID(braID);
		employeeVO.setEmpName(empName);
		employeeVO.setEmpJob(empJob);
		employeeVO.setEmpTel(empTel);
		employeeVO.setEmpState(empState);
		employeeVO.setEmpAcc(empAcc);
		employeeVO.setEmPsw(empPsw);
		employeeVO.setEmpID(empID);
		dao.update(employeeVO);

		return employeeVO;
	}

	

	public EmployeeVO getOneEmp(String empID) {
		return dao.findByPK(empID);
	}

	public List<EmployeeVO> getAll() {
		return dao.getAll();
	}
}