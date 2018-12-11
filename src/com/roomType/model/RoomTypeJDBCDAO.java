package com.roomType.model;

import java.util.*;
import tool.BLOB;
import java.io.IOException;
import java.sql.*;

public class RoomTypeJDBCDAO implements RoomTypeDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA105G4";
	String passwd = "123456";
	
	private static final String INSERT_STMT = "INSERT INTO RoomType(rtID,braID,rtName,rtPic,rtIntro,rtMinimum,rtLimit,weeklyprice,holidayprice,total) values('RT'||LPAD(to_char(rt_seq.NEXTVAL),2,'0'),?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM RoomType";
	private static final String GET_ONE_STMT = "SELECT * FROM RoomType WHERE RTID = ?";
	
	private static final String DELETE = "DELETE FROM RoomType where RTID = ?";
	private static final String UPDATE = "UPDATE RoomType SET Total = ? WHERE RTID = ?";
	
	
	@Override
	public void insert(RoomTypeVO roomTypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, roomTypeVO.getBraID());
			pstmt.setString(2, roomTypeVO.getRtName());
			pstmt.setBytes(3, roomTypeVO.getRtPic());
			pstmt.setString(4, roomTypeVO.getRtIntro());
			pstmt.setInt(5, roomTypeVO.getRtMinimum());
			pstmt.setInt(6, roomTypeVO.getRtLimit());
			pstmt.setInt(7, roomTypeVO.getWeeklyPrice());
			pstmt.setInt(8, roomTypeVO.getHolidayPrice());
			pstmt.setInt(9, roomTypeVO.getTotal());
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
			// Clean up JDBC resources
		}finally {
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
	
	@Override
	public void update(RoomTypeVO roomTypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, roomTypeVO.getTotal());
			pstmt.setString(2, roomTypeVO.getRtID());
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
			// Clean up JDBC resources
		}finally {
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
	
	@Override
	public void delete(String rtID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, rtID);
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
			// Clean up JDBC resources
		}finally {
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
	
	@Override
	public RoomTypeVO findByPrimaryKey(String rtID) {
		RoomTypeVO roomTypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, rtID);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				roomTypeVO = new RoomTypeVO();
				roomTypeVO.setRtID(rs.getString("RTID"));
				roomTypeVO.setBraID(rs.getString("BRAID"));
				roomTypeVO.setRtName(rs.getString("RTNAME"));
				
				roomTypeVO.setRtPic(rs.getBytes("RTPIC"));
				
				roomTypeVO.setRtIntro(rs.getString("RTINTRO"));
				roomTypeVO.setRtMinimum(rs.getInt("RtMinimum"));
				roomTypeVO.setRtLimit(rs.getInt("RtLimit"));
				roomTypeVO.setWeeklyPrice(rs.getInt("WeeklyPrice"));
				roomTypeVO.setHolidayPrice(rs.getInt("HolidayPrice"));
				roomTypeVO.setBalance(rs.getString("Balance"));
				roomTypeVO.setTotal(rs.getInt("Total"));				
			}
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
			// Clean up JDBC resources
		}finally {
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
		
		return roomTypeVO;
	}
	
	@Override
	public List<RoomTypeVO> getAll() {
		List<RoomTypeVO> list = new ArrayList<RoomTypeVO>();
		RoomTypeVO roomTypeVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				roomTypeVO = new RoomTypeVO();
				roomTypeVO.setRtID(rs.getString("RTID"));
				roomTypeVO.setBraID(rs.getString("BRAID"));
				roomTypeVO.setRtName(rs.getString("RTNAME"));
				roomTypeVO.setRtPic(rs.getBytes("RTPIC"));
				roomTypeVO.setRtIntro(rs.getString("RTINTRO"));
				roomTypeVO.setRtMinimum(rs.getInt("RtMinimum"));
				roomTypeVO.setRtLimit(rs.getInt("RtLimit"));
				roomTypeVO.setWeeklyPrice(rs.getInt("WeeklyPrice"));
				roomTypeVO.setHolidayPrice(rs.getInt("HolidayPrice"));
				roomTypeVO.setBalance(rs.getString("Balance"));
				roomTypeVO.setTotal(rs.getInt("Total"));
				
				list.add(roomTypeVO);
			}
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
			// Clean up JDBC resources
		}finally {
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
		
		return list;
	}
	
	
	public static void main(String[] args) {
		RoomTypeJDBCDAO dao = new RoomTypeJDBCDAO();
		
		//新增
//		RoomTypeVO roomTypeVO = new RoomTypeVO();
//		roomTypeVO.setBraID("B02");
//		roomTypeVO.setRtName("超級二人房");
//		
//		try {
//			roomTypeVO.setRtPic(new BLOB().writeBlob("images/cat.jpg"));
//		} catch (IOException e) {
//			System.out.println("上傳失敗!!");
//			e.printStackTrace();
//		}
//		
//		roomTypeVO.setRtIntro("全台灣最屌的二人房");
//		roomTypeVO.setRtMinimum(2);
//		roomTypeVO.setRtLimit(3);
//		roomTypeVO.setWeeklyPrice(5000);
//		roomTypeVO.setHolidayPrice(5500);
//		roomTypeVO.setTotal(10);
//		dao.insert(roomTypeVO);
//		System.out.println("新增成功!!");
		
		//修改
//		RoomTypeVO roomTypeVO02 = new RoomTypeVO();
//		roomTypeVO02.setTotal(20);
//		roomTypeVO02.setRtID("RT12");
//		dao.update(roomTypeVO02);
//		System.out.println("修改成功!!");
		
		//刪除
//		dao.delete("RT12");
//		System.out.println("刪除成功!!");
		
		//查詢一筆
//		RoomTypeVO roomTypeVO03 = dao.findByPrimaryKey("RT13");
//		System.out.println(roomTypeVO03.getRtID());
//		System.out.println(roomTypeVO03.getBraID());
//		System.out.println(roomTypeVO03.getRtName());
//		
//		new BLOB().readBlob(roomTypeVO03.getRtPic(),"input/cat2.jpg");
//
//		System.out.println(roomTypeVO03.getRtIntro());
//		System.out.println(roomTypeVO03.getRtMinimum());
//		System.out.println(roomTypeVO03.getRtLimit());
//		System.out.println(roomTypeVO03.getWeeklyPrice());
//		System.out.println(roomTypeVO03.getHolidayPrice());
//		System.out.println(roomTypeVO03.getBalance());
//		System.out.println(roomTypeVO03.getTotal());
		
		//查詢多筆
		List<RoomTypeVO> list = dao.getAll();
		
		for(RoomTypeVO rt : list) {
			System.out.println(rt.getRtID());
			System.out.println(rt.getBraID());
			System.out.println(rt.getRtName());
			
//			new BLOB().readBlob(roomTypeVO03.getRtPic(),"input/cat2.jpg");
			
			System.out.println(rt.getRtIntro());
			System.out.println(rt.getRtMinimum());
			System.out.println(rt.getRtLimit());
			System.out.println(rt.getWeeklyPrice());
			System.out.println(rt.getHolidayPrice());
			System.out.println(rt.getBalance());
			System.out.println(rt.getTotal());
			System.out.println("=========================");
		}	
	}
}
