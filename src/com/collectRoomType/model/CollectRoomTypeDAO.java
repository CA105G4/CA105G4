package com.collectRoomType.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CollectRoomTypeDAO implements CollectRoomType_interface{
	
	private static DataSource ds =null;	
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CA105G4DB");
		} catch (NamingException e) {
			e.printStackTrace();
		} 
	}
	
    private static final String INSERT_SQL = "INSERT INTO CollectRoomType(memID,rtID) VALUES (?, ?)";
    private static final String FIND_MEMBER_COLLECT_SQL = "SELECT * from CollectRoomType where memID = ?";
    private static final String DELETE_SQL = "DELETE from CollectRoomType where memID = ? AND rtID = ?";
    
    
	
	@Override
	public void insert(CollectRoomTypeVO collectRoomTypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(INSERT_SQL);
			
			pstmt.setString(1, collectRoomTypeVO.getMemID());
			pstmt.setString(2, collectRoomTypeVO.getRtID());
			
			pstmt.executeUpdate();
			
		}  catch (SQLException e) {
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
	public void delete(String memID, String rtID) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(DELETE_SQL);
			
			pstmt.setString(1, memID);
			pstmt.setString(2, rtID);
			
			pstmt.executeUpdate();
			
		}  catch (SQLException e) {
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
	public List<CollectRoomTypeVO> findByMemID(String memID) {
		List<CollectRoomTypeVO> list = new ArrayList<CollectRoomTypeVO>();
		CollectRoomTypeVO collectRoomTypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(FIND_MEMBER_COLLECT_SQL);
			
			pstmt.setString(1, memID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				collectRoomTypeVO = new CollectRoomTypeVO();
				
				collectRoomTypeVO.setMemID(rs.getString("memID"));
				collectRoomTypeVO.setRtID(rs.getString("rtID"));
				
				list.add(collectRoomTypeVO);
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
		return list;
	}


}
