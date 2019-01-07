package com.employee.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tool.BLOB;

public class EmployeeJDBCDAO implements EmployeeDAO_interface {
	
	
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "CA105G4"; 
	private static final String PASSWORD = "123456"; 
	
	private static final String INSERT_SQL = 
    		"INSERT INTO Employee (empID,braID,empName,empJob,empTel, empAcc, empPsw,empPic)VALUES('E'||LPAD(to_char(emp_seq.NEXTVAL), 4, '0'), ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE Employee set braID = ?,empName = ?,empJob = ?,empTel = ?,empState = ?,empAcc = ?,empPsw = ? where empID = ?";
    //可修改 分店ID 姓名 職稱 電話 狀態 帳號 密碼
    private static final String GET_ALL_SQL = "SELECT empID, braID, empName, empJob, empTel, empState, empAcc, empPsw from Employee";
    private static final String GET_ONE_SQL = "SELECT empID, braID, empName, empJob, empTel, empState, empPic, empAcc, empPsw from Employee where empID = ?";
    private static final String FIND_BY_MEMACC = "SELECT * from Employee where empAcc = ?";
    static {
    	try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}    	
    }
    
	@Override
	public void insert(EmployeeVO employeeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_SQL);
			
			pstmt.setString(1, employeeVO.getBraID());
			pstmt.setString(2, employeeVO.getEmpName());
			pstmt.setString(3, employeeVO.getEmpJob());
			pstmt.setString(4, employeeVO.getEmpTel());
			pstmt.setString(5, employeeVO.getEmpAcc());			
			pstmt.setString(6, employeeVO.getEmpPsw());
			pstmt.setBytes(7, employeeVO.getEmpPic());
			
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
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
				employeeVO.setEmpPic(rs.getBytes("EmpPic"));
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
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
	
	public static void main(String[] args) {
		EmployeeJDBCDAO dao = new EmployeeJDBCDAO();
		
//		//新增
		EmployeeVO employeeVO= new EmployeeVO();
		 employeeVO.setBraID("B00");
		 employeeVO.setEmpName("阿翔");
		 employeeVO.setEmpJob("總管理處");
		 employeeVO.setEmpTel("095776677");
		 employeeVO.setEmpPic(new BLOB().writeBlob("images/panta.jpg"));
		 employeeVO.setEmpAcc("aaa");
		 employeeVO.setEmpPsw("bbb");
		 dao.insert(employeeVO);
		 System.out.println("新增成功");
		 
		 //修改
//		 EmployeeVO employeeVO02= new EmployeeVO();
//		 	employeeVO02.setBraID("B02");
//			employeeVO02.setEmpName("元元");
//			employeeVO02.setEmpJob("員工");
//			employeeVO02.setEmpTel("095796677");
//			employeeVO02.setEmpState(0);
//			employeeVO02.setEmpAcc("CCC");
//			employeeVO02.setempPsw("CCC");
//			employeeVO02.setEmpID("E0003");
//			dao.update(employeeVO02);
//			System.out.println("修改成功!!");
//			
//			
//			//查詢一筆
//			EmployeeVO employeeVO03= dao.findByPK("E0007");
//			System.out.println(employeeVO03.getEmpID());
//			System.out.println(employeeVO03.getEmpName());
//			System.out.println(employeeVO03.getEmpJob());
//			System.out.println(employeeVO03.getBraID());
//			System.out.println(employeeVO03.getEmpTel());
//			System.out.println(employeeVO03.getEmpState());
//			new BLOB().readBlob(employeeVO03.getEmpPic(),"input/panta.jpg");
//			System.out.println(employeeVO03.getEmpAcc());
//			System.out.println(employeeVO03.getempPsw());
//			
//			//查詢多筆	
			List<EmployeeVO> list = dao.getAll();
			for(EmployeeVO rt : list) {
				System.out.println(rt.getEmpID());
				System.out.println(rt.getBraID());
				System.out.println(rt.getEmpName());
				System.out.println(rt.getEmpJob());
				System.out.println(rt.getEmpTel());
				System.out.println(rt.getEmpState());
				System.out.println(rt.getEmpPic());
				System.out.println(rt.getEmpAcc());
				System.out.println(rt.getEmpPsw());
				
				System.out.println("=========================");
			}
	}
}
