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

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.authorityRecord.model.AuthorityRecordVO;








public class AuthorityDAO implements AuthorityDAO_interface {
	
	
	
	private static final String INSERT_SQL = 
    		"INSERT INTO Authority (authid, authName)VALUES(auth_seq.NEXTVAL,?)";
    private static final String UPDATE_SQL = "UPDATE Authority set authName = ? where authID = ?";
   //權限要更新???
    private static final String GET_ALL_SQL = "SELECT * from Authority";
    private static final String GET_ONE_SQL = "SELECT authID,authName from Authority where authID = ?";
    private static final String GET_Emps_ByAuthID = "SELECT authID,empID FROM authorityRecord where authID = ? order by empID";
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
	public void insert(AuthorityVO authorityVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con=ds.getConnection();
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
			con=ds.getConnection();
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
			con=ds.getConnection();
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
	
			con=ds.getConnection();
		
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
			con=ds.getConnection();
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
	

	
}
	
