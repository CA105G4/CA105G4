package com.collectRoomType.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CollectRoomTypeJDBCDAO implements CollectRoomType_interface{

	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "CA105G4"; 
	private static final String PASSWORD = "123456"; 
	
    private static final String INSERT_SQL = "INSERT INTO CollectRoomType VALUES (?, ?)";
    private static final String FIND_MEMBER_COLLECT_SQL = "SELECT * from CollectRoomType where memID = ?";
    private static final String DELETE_SQL = "DELETE from CollectRoomType where memID = ? AND rtID = ?";
    
    static {
    	try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}    	
    }
	
	@Override
	public void insert(CollectRoomTypeVO collectRoomTypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_SQL);
			
			pstmt.setString(1, collectRoomTypeVO.getMemID());
			pstmt.setString(2, collectRoomTypeVO.getRtID());
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
	public void delete(String memID, String rtID) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_SQL);
			
			pstmt.setString(1, memID);
			pstmt.setString(2, rtID);
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
	public List<CollectRoomTypeVO> findByMemID(String memID) {
		List<CollectRoomTypeVO> list = new ArrayList<CollectRoomTypeVO>();
		CollectRoomTypeVO collectRoomTypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_MEMBER_COLLECT_SQL);
			
			pstmt.setString(1, memID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				collectRoomTypeVO = new CollectRoomTypeVO();
				
				collectRoomTypeVO.setMemID(rs.getString("memID"));
				collectRoomTypeVO.setRtID(rs.getString("rtID"));
				
				list.add(collectRoomTypeVO);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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

	public static void main(String[] args) {
		CollectRoomTypeJDBCDAO dao = new CollectRoomTypeJDBCDAO();
		
		// 新增
//		CollectRoomTypeVO collectRoomTypeVO1 = new CollectRoomTypeVO();
//		collectRoomTypeVO1.setMemID("M0001");
//		collectRoomTypeVO1.setRtID("RT02");
//		dao.insert(collectRoomTypeVO1);
//		System.out.println("新增成功");
//		System.out.println("------------------");
		
		// 刪除
//		dao.delete("M0001", "RT01");
//		System.out.println("刪除成功");
//		System.out.println("------------------");	
		
		// 找出會員所有收藏的房型
		String memID = "M0001";
		List<CollectRoomTypeVO> list = dao.findByMemID(memID);
		System.out.println("會員編號 " + memID + " 所收藏房型：");
		for(CollectRoomTypeVO aCollectRoomTypeVO : list) {
			System.out.println(aCollectRoomTypeVO.getRtID());
		}
		System.out.println("搜尋成功");
		System.out.println("------------------");
	}
}
