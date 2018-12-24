package com.couponRecord.model;

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

public class CouponRecordDAO implements CouponRecord_interface{

private static DataSource ds =null;	
	
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CA105G4DB");
		} catch (NamingException e) {
			e.printStackTrace();
		} 
	}

	
    private static final String INSERT_SQL = "INSERT INTO couponRecord (memID, cpnID) VALUES (?, ?)";
    private static final String UPDATE_SQL = "UPDATE couponRecord set cpnState = 1 where memID = ? and cpnID = ?";
    private static final String FIND_MEMBER_RECORD_SQL = "SELECT * from couponRecord where memID = ?";
    
  
	@Override
	public void insert(CouponRecordVO couponRecordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(INSERT_SQL);
			
			pstmt.setString(1, couponRecordVO.getMemID());
			pstmt.setString(2, couponRecordVO.getCpnID());
			
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
	public void update(String memID, String cpnID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_SQL);
			
			pstmt.setString(1, memID);
			pstmt.setString(2, cpnID);
			
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
	public List<CouponRecordVO> findByMemID(String memID) {
		List<CouponRecordVO> list = new ArrayList<CouponRecordVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(FIND_MEMBER_RECORD_SQL);

			pstmt.setString(1, memID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CouponRecordVO couponRecordVO = new CouponRecordVO();
				
				couponRecordVO.setMemID(rs.getString("memID"));
				couponRecordVO.setCpnID(rs.getString("cpnID"));
				couponRecordVO.setCpnState(rs.getInt("cpnState"));
				
				list.add(couponRecordVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
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
