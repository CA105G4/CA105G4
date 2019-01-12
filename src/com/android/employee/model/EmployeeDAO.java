package com.android.employee.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class EmployeeDAO implements EmployeeDAO_interface {
	
	private static final String INSERT_SQL = 
    		"INSERT INTO Employee (empID,braID,empName,empJob,empTel, empAcc, empPsw,empPic)VALUES('E'||LPAD(to_char(emp_seq.NEXTVAL), 4, '0'), ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE Employee set braID = ?,empName = ?,empJob = ?,empTel = ?,empState = ?,empAcc = ?,empPsw = ? where empID = ?";
    //可修改 分店ID 姓名 職稱 電話 狀態 帳號 密碼
    private static final String GET_ALL_SQL = "SELECT empID, braID, empName, empJob, empTel, empState, empAcc, empPsw from Employee";
    private static final String FIND_BY_MEMACC = "SELECT * from Employee where empAcc = ?";
    private static final String GET_ONE_SQL = "SELECT empID, braID, empName, empJob, empTel, empState, empPic, empAcc, empPsw from Employee where empID = ?";

    private static final String GET_EMPLOYEE_BY_ACC_PSW = "select * from employee where empAcc = ? and empPsw = ?";
    private static final String GET_EMP_BRANAME_SQL = "select braName from employee left join branch on employee.braId = branch.braId where empId = ?";
    
    private static DataSource ds = null;

    static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CA105G4DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
    
	@Override
	public void insert(EmployeeVO employeeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(INSERT_SQL);
			
			pstmt.setString(1, employeeVO.getBraID());
			pstmt.setString(2, employeeVO.getEmpName());
			pstmt.setString(3, employeeVO.getEmpJob());
			pstmt.setString(4, employeeVO.getEmpTel());
			pstmt.setString(5, employeeVO.getEmpAcc());			
			pstmt.setString(6, employeeVO.getEmpPsw());
//			pstmt.setBytes(7, employeeVO.getEmpPic());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void update(EmployeeVO employeeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_SQL);
			
			pstmt.setString(1, employeeVO.getBraID());
			pstmt.setString(2, employeeVO.getEmpName());
			pstmt.setString(3, employeeVO.getEmpJob());
			pstmt.setString(4, employeeVO.getEmpTel());
			pstmt.setInt(5, employeeVO.getEmpState());
			pstmt.setString(6, employeeVO.getEmpAcc());
			pstmt.setString(7, employeeVO.getEmpPsw());
			pstmt.setString(8, employeeVO.getEmpID());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public String findAcc(String empAcc) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String empID =null;
		
		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_MEMACC);
			
			pstmt.setString(1,empAcc);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				empID=rs.getString("empID");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return empID;
	}

	@Override
	public EmployeeVO findByPK(String empID) {
		EmployeeVO employeeVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_SQL);
			
			pstmt.setString(1, empID);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				employeeVO = new EmployeeVO();
				
				employeeVO.setEmpID(rs.getString("empID"));
				employeeVO.setEmpName(rs.getString("empName"));
				employeeVO.setEmpJob(rs.getString("empJob"));
				employeeVO.setBraID(rs.getString("braID"));
				employeeVO.setEmpTel(rs.getString("empTel"));
				employeeVO.setEmpState(rs.getInt("empState"));
//				employeeVO.setEmpPic(rs.getBytes("EmpPic"));
				employeeVO.setEmpAcc(rs.getString("EmpAcc"));
				employeeVO.setEmpPsw(rs.getString("EmpPsw"));				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	
		return employeeVO;
	}

	@Override
	public List<EmployeeVO> getAll() {
		List<EmployeeVO> list = new ArrayList<EmployeeVO>();
		EmployeeVO employeeVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_SQL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				employeeVO = new EmployeeVO();
				
				employeeVO.setEmpID(rs.getString("empID"));
				employeeVO.setEmpName(rs.getString("empName"));
				employeeVO.setEmpJob(rs.getString("empJob"));
				employeeVO.setBraID(rs.getString("braID"));			
				employeeVO.setEmpTel(rs.getString("empTel"));
				employeeVO.setEmpState(rs.getInt("empState"));
				employeeVO.setEmpAcc(rs.getString("empAcc"));
				employeeVO.setEmpPsw(rs.getString("empPsw"));
				
				list.add(employeeVO);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	
		return list;
	}
	

	@Override
	public EmployeeVO getEmpByAccPsw(String empAcc, String empPsw) {

		EmployeeVO employeeVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_EMPLOYEE_BY_ACC_PSW);
			
			pstmt.setString(1, empAcc);
			pstmt.setString(2, empPsw);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				employeeVO = new EmployeeVO();
				
				employeeVO.setEmpID(rs.getString("empId"));
				employeeVO.setEmpName(rs.getString("empName"));
				employeeVO.setEmpJob(rs.getString("empJob"));
				employeeVO.setBraID(rs.getString("braID"));			
				employeeVO.setEmpTel(rs.getString("empTel"));
				employeeVO.setEmpState(rs.getInt("empState"));
				employeeVO.setEmpAcc(rs.getString("empAcc"));
				employeeVO.setEmpPsw(rs.getString("empPsw"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	
		return employeeVO;
	}
	
	@Override
	public String getEmpBraName(String empId) {

		String braName = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_EMP_BRANAME_SQL);
			
			pstmt.setString(1, empId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				braName = rs.getString("braName");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	
		return braName;
	}
}
