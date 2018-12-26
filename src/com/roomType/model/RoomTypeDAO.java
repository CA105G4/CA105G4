package com.roomType.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.room.model.RoomVO;

import tool.BLOB;
import java.io.IOException;
import java.sql.*;

public class RoomTypeDAO implements RoomTypeDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/CA105G4DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO RoomType(rtID,braID,rtName,rtPic,rtIntro,rtMinimum,rtLimit,weeklyprice,holidayprice,total) values('RT'||LPAD(to_char(rt_seq.NEXTVAL),2,'0'),?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM RoomType";
	private static final String GET_ONE_STMT = "SELECT * FROM RoomType WHERE RTID = ?";
	
	private static final String DELETE = "DELETE FROM RoomType where RTID = ?";
	private static final String UPDATE = "UPDATE RoomType SET BRAID=?, RTNAME=?, RTPIC = ?, RTINTRO=?, RTMINIMUM=?, RTLIMIT=?, WEEKLYPRICE=?, HOLIDAYPRICE=?, BALANCE=?, TOTAL=?  WHERE RTID = ?";

	private static final String UPDATE_ROOMBALANCE ="UPDATE RoomType SET BALANCE=? WHERE RTID=?"; 
	
	@Override
	public void insert(RoomTypeVO roomTypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, roomTypeVO.getBraID());
			pstmt.setString(2, roomTypeVO.getRtName());
			pstmt.setBytes(3, roomTypeVO.getRtPic());
			pstmt.setString(4, roomTypeVO.getRtIntro());
			pstmt.setInt(5, roomTypeVO.getRtMinimum());
			pstmt.setInt(6, roomTypeVO.getRtLimit());
			pstmt.setInt(7, roomTypeVO.getWeeklyPrice());
			pstmt.setInt(8, roomTypeVO.getHolidayPrice());
			
			int roomnumber = new Integer(roomTypeVO.getTotal());
			System.out.println(roomnumber);
			if(roomnumber>=10) {
				StringBuffer roomnum = new StringBuffer(roomTypeVO.getTotal());
				for(int i=0; i<31; i++) {
					roomnum.append(roomTypeVO.getTotal());
					System.out.println(roomnum);
				}
				String balance = roomnum.toString();
				System.out.println(balance);
				pstmt.setString(9, balance);
			}else {
				StringBuffer roomnum = new StringBuffer(roomTypeVO.getTotal());
				for(int i=0; i<31; i++) {
					roomnum.append("0"+roomTypeVO.getTotal());
					System.out.println(roomnum);
				}
				String balance = roomnum.toString();
				System.out.println(balance);
				pstmt.setString(9, balance);
			}
			
			pstmt.setInt(10, roomTypeVO.getTotal());
			
			pstmt.executeUpdate();
			
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			/*UPDATE RoomType SET BRAID=?, RTNAME=?, RTPIC = ?, RTINTRO=?, RTMINIMUM=?, RTLIMIT=?, 
			 * WEEKLYPRICE=?, HOLIDAYPRICE=?, BALANCE=?, TOTAL=?  WHERE RTID = ?
			*/
			pstmt.setString(1, roomTypeVO.getBraID());
			pstmt.setString(2, roomTypeVO.getRtName());
			pstmt.setBytes(3, roomTypeVO.getRtPic());
			pstmt.setString(4, roomTypeVO.getRtIntro());
			pstmt.setInt(5, roomTypeVO.getRtMinimum());
			pstmt.setInt(6, roomTypeVO.getRtLimit());
			pstmt.setInt(7, roomTypeVO.getWeeklyPrice());
			pstmt.setInt(8, roomTypeVO.getHolidayPrice());
			
			int roomnumber = new Integer(roomTypeVO.getTotal());
			System.out.println(roomnumber);
			if(roomnumber>=10) {
				StringBuffer roomnum = new StringBuffer(roomTypeVO.getTotal());
				for(int i=0; i<31; i++) {
					roomnum.append(roomTypeVO.getTotal());
					System.out.println(roomnum);
				}
				String balance = roomnum.toString();
				System.out.println(balance);
				pstmt.setString(9, balance);
			}else {
				StringBuffer roomnum = new StringBuffer(roomTypeVO.getTotal());
				for(int i=0; i<31; i++) {
					roomnum.append("0"+roomTypeVO.getTotal());
					System.out.println(roomnum);
				}
				String balance = roomnum.toString();
				System.out.println(balance);
				pstmt.setString(9, balance);
			}
			
			pstmt.setInt(10, roomTypeVO.getTotal());
			
			pstmt.setString(11, roomTypeVO.getRtID());
			
			pstmt.executeUpdate();
			
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, rtID);
			
			pstmt.executeUpdate();
			
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
			con = ds.getConnection();
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
			con = ds.getConnection();
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
	
	@Override
	public void updateRoomBalance(String balance, String rtID) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_ROOMBALANCE);
			//UPDATE RoomType SET BALANCE=? WHERE RTID=?

			pstmt.setString(1, balance);
			pstmt.setString(2, rtID);
			
			pstmt.executeUpdate();
			
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
	
//	public static void main(String[] args) {
//		RoomTypeDAO dao = new RoomTypeDAO();
		
		//新增
//		RoomTypeVO roomTypeVO = new RoomTypeVO();
//		roomTypeVO.setBraID("B02");
//		roomTypeVO.setRtName("超級二人房");
//		
//		try {
//			roomTypeVO.setRtPic(new BLOB().writeBlob("images/IMG01.jpg"));
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
//		
//		try {
//			roomTypeVO02.setRtPic(new BLOB().writeBlob("images/IMG01.jpg"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		roomTypeVO02.setRtID("RT01");
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
//		List<RoomTypeVO> list = dao.getAll();
//		
//		for(RoomTypeVO rt : list) {
//			System.out.println(rt.getRtID());
//			System.out.println(rt.getBraID());
//			System.out.println(rt.getRtName());
//			
//			new BLOB().readBlob(roomTypeVO03.getRtPic(),"input/cat2.jpg");
//			
//			System.out.println(rt.getRtIntro());
//			System.out.println(rt.getRtMinimum());
//			System.out.println(rt.getRtLimit());
//			System.out.println(rt.getWeeklyPrice());
//			System.out.println(rt.getHolidayPrice());
//			System.out.println(rt.getBalance());
//			System.out.println(rt.getTotal());
//			System.out.println("=========================");
//		}	
//	}
}
