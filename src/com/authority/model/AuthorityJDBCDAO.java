package com.authority.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.authorityRecord.model.AuthorityRecordVO;








public class AuthorityJDBCDAO implements AuthorityDAO_interface {
	
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "CA105G4"; 
	private static final String PASSWORD = "123456"; 
	
	private static final String INSERT_SQL = 
    		"INSERT INTO Authority (authid, authName)VALUES(auth_seq.NEXTVAL,?)";
    private static final String UPDATE_SQL = "UPDATE Authority set authName = ? where authID = ?";
   //權限要更新???
    private static final String GET_ALL_SQL = "SELECT * from Authority";
    private static final String GET_ONE_SQL = "SELECT authID,authName from Authority where authID = ?";
    private static final String GET_Emps_ByAuthID = "SELECT authID,empID FROM authorityRecord where authID = ? order by empID";
    static {
    	try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}    	
    }
	
    @Override
	public void insert(AuthorityVO authorityVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_SQL);
			
			
			pstmt.setString(1, authorityVO.getAuthName());
			
			
			
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
	public void update(AuthorityVO authorityVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_SQL);
			
			pstmt.setString(1, authorityVO.getAuthName());
			pstmt.setInt(2, authorityVO.getAuthID());
			
			
			
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
	public AuthorityVO findByPK(Integer authID) {
		 AuthorityVO authorityVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_SQL);
			
			pstmt.setInt(1, authID);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				authorityVO = new AuthorityVO();
				
				authorityVO.setAuthID(rs.getInt("authID"));
				authorityVO.setAuthName(rs.getString("authName"));
				
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
		return authorityVO;
	}

	
	public Set<AuthorityRecordVO> getEmpsByAuthID(Integer authID) {
		Set<AuthorityRecordVO> set = new LinkedHashSet<AuthorityRecordVO>();
		AuthorityRecordVO authorityRecordVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = DriverManager.getConnection(URL, USER, PASSWORD);
		
			pstmt = con.prepareStatement(GET_Emps_ByAuthID);
			pstmt.setInt(1, authID);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				authorityRecordVO = new AuthorityRecordVO();
				authorityRecordVO.setAuthID(rs.getInt("authID"));
				authorityRecordVO.setEmpID(rs.getString("empID"));
				
				set.add(authorityRecordVO); // Store the row in the vector
			}
	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public List<AuthorityVO> getAll() {
		List<AuthorityVO> list = new ArrayList<AuthorityVO>();
		AuthorityVO authorityVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_SQL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				authorityVO = new AuthorityVO();
				
				authorityVO.setAuthID(rs.getInt("authID"));
				authorityVO.setAuthName(rs.getString("authName"));
				
				
				list.add(authorityVO);
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
		AuthorityJDBCDAO dao = new AuthorityJDBCDAO();
	//new
//		AuthorityVO authorityVO= new AuthorityVO();
//			authorityVO.setAuthName("廁所管理");
//			dao.insert(authorityVO);
//			System.out.println("新增成功!!");
//	
//		//修改	
//			AuthorityVO authorityVO02= new AuthorityVO();	
//			authorityVO02.setAuthName("客房服務");
//			authorityVO02.setAuthID(1015);
//			
//			dao.update(authorityVO02);
//			System.out.println("修改成功!!");
//			
//		//查詢1筆
//			AuthorityVO authorityVO03= dao.findByPK(1001);	
//			System.out.println(authorityVO03.getAuthID());
//			System.out.println(authorityVO03.getAuthName());
//			
//			
//			//查詢多筆
			List<AuthorityVO> list = dao.getAll();
			for(AuthorityVO rt : list) {
				System.out.println(rt.getAuthID());
				System.out.println(rt.getAuthName());
				System.out.println("=========================");
				
				
			}	
	}
	
}
	
