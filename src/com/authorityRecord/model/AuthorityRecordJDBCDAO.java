package com.authorityRecord.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class AuthorityRecordJDBCDAO implements AuthorityRecordDAO_interface {

	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "CA105G4";
	private static final String PASSWORD = "123456";

	private static final String INSERT_SQL = "INSERT INTO AuthorityRecord (authid, empid)VALUES(?,?)";
	private static final String UPDATE_SQL = "UPDATE AuthorityRecord set authid = ? where empID = ?";
	// 以員工編號 還是權限編號 修改????
	private static final String GET_ALL_SQL = "SELECT * from AuthorityRecord";

	private static final String DELETE = "DELETE FROM AUTHORITYRECORD where empID = ?";
	private static final String FIND_BY_PK = "SELECT authID,empID  from AuthorityRecord where empID = ?";

	//用員工編號查權限編號
	private static final String GET_AUTHID_BY_EMPID = "SELECT * FROM AUTHORITYRECORD WHERE EMPID = ? ORDER BY AUTHID";
	
	private static final String GET_ONE_BY_EMPID_AUTHID = "SELECT * FROM AUTHORITYRECORD WHERE EMPID = ? AND AUTHID = ?";
	private static final String DELETE_ONE_BY_EMPID_AUTHID = "DELETE AUTHORITYRECORD WHERE EMPID = ? AND AUTHID = ?";
	
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(AuthorityRecordVO authorityRecordVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_SQL);

			pstmt.setInt(1, authorityRecordVO.getAuthID());
			pstmt.setString(2, authorityRecordVO.getEmpID());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public Set<AuthorityRecordVO> getAuthIDByEmpIDSet(String empID) {
		Set<AuthorityRecordVO> set = new LinkedHashSet<AuthorityRecordVO>();
		AuthorityRecordVO authorityRecordVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			pstmt = con.prepareStatement(GET_AUTHID_BY_EMPID);
			pstmt.setString(1, empID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				authorityRecordVO = new AuthorityRecordVO();
				authorityRecordVO.setEmpID(rs.getString("empID"));
				authorityRecordVO.setAuthID(rs.getInt("authID"));
				set.add(authorityRecordVO); // Store the row in the vector
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return set;
	}

	@Override
	public void update(AuthorityRecordVO authorityRecordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_SQL);

			pstmt.setInt(1, authorityRecordVO.getAuthID());
			pstmt.setString(1, authorityRecordVO.getEmpID());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void delete(String empID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, empID);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	public AuthorityRecordVO findByPK(String empID) {
		AuthorityRecordVO authorityRecordVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);

			pstmt.setString(1, empID);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				authorityRecordVO = new AuthorityRecordVO();

				authorityRecordVO.setAuthID(rs.getInt("authID"));
				authorityRecordVO.setEmpID(rs.getString("empID"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return authorityRecordVO;

	}

	@Override
	public List<AuthorityRecordVO> getAll() {
		List<AuthorityRecordVO> list = new ArrayList<AuthorityRecordVO>();
		AuthorityRecordVO authorityRecordVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_SQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				authorityRecordVO = new AuthorityRecordVO();

				authorityRecordVO.setAuthID(rs.getInt("authID"));
				authorityRecordVO.setEmpID(rs.getString("empID"));

				list.add(authorityRecordVO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
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
	public List<AuthorityRecordVO> getAuthIdByEmpIdList(String empId) {
		List<AuthorityRecordVO> list = new ArrayList<AuthorityRecordVO>();
		AuthorityRecordVO authorityRecordVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_AUTHID_BY_EMPID);
			
			pstmt.setString(1, empId);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				authorityRecordVO = new AuthorityRecordVO();

				authorityRecordVO.setAuthID(rs.getInt("authID"));
				authorityRecordVO.setEmpID(rs.getString("empID"));

				list.add(authorityRecordVO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
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
	public AuthorityRecordVO getOneEmpAuth(String empID, Integer authID) {
		AuthorityRecordVO authorityRecordVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_BY_EMPID_AUTHID);

			pstmt.setString(1, empID);
			pstmt.setInt(2, authID);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				authorityRecordVO = new AuthorityRecordVO();

				authorityRecordVO.setAuthID(rs.getInt("authID"));
				authorityRecordVO.setEmpID(rs.getString("empID"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return authorityRecordVO;
	}

	@Override
	public void deleteByEmpIdAuthId(String empID, Integer authID) {
		AuthorityRecordVO authorityRecordVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_ONE_BY_EMPID_AUTHID);

			pstmt.setString(1, empID);
			pstmt.setInt(2, authID);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		AuthorityRecordJDBCDAO dao = new AuthorityRecordJDBCDAO();
		// new
//			AuthorityRecordVO authorityRecordVO= new AuthorityRecordVO();
//				authorityRecordVO.setAuthID(1004);
//				authorityRecordVO.setEmpID("E0005");
//				dao.insert(authorityRecordVO);
//				System.out.println("新增成功!!");

		// 修改
//				AuthorityRecordVO authorityRecordVO02= new AuthorityRecordVO();	
//				authorityRecordVO02.setAuthID(1001);
//				authorityRecordVO02.setEmpID("E0005");
//				dao.update(authorityRecordVO02);
//				System.out.println("修改成功!!");
//				

////				
//				//查詢多筆
//		List<AuthorityRecordVO> list = dao.getAll();
//		for (AuthorityRecordVO rt : list) {
//			System.out.println(rt.getAuthID());
//			System.out.println(rt.getEmpID());
//			System.out.println("=========================");
//		}

		dao.deleteByEmpIdAuthId("E0002", 1014);
		System.out.println("success");
	}
}
