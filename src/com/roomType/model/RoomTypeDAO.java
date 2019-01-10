package com.roomType.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;
import java.sql.Date;

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
	
	private static final String INSERT_STMT = "INSERT INTO RoomType(rtID,braID,rtName,rtPic,rtIntro,rtMinimum,rtLimit,weeklyprice,holidayprice,balance,total) values('RT'||LPAD(to_char(rt_seq.NEXTVAL),2,'0'),?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM RoomType";
	private static final String GET_ONE_STMT = "SELECT * FROM RoomType WHERE RTID = ?";
	
	private static final String DELETE = "DELETE FROM RoomType where RTID = ?";
	private static final String UPDATE = "UPDATE RoomType SET BRAID=?, RTNAME=?, RTPIC = ?, RTINTRO=?, RTMINIMUM=?, RTLIMIT=?, WEEKLYPRICE=?, HOLIDAYPRICE=?, BALANCE=?, TOTAL=?  WHERE RTID = ?";

	/**[Gina]訂單交易用**/
	private static final String UPDATE_ROOMBALANCE ="UPDATE RoomType SET BALANCE=? WHERE RTID=?"; 
	/**[Gina]訂單交易用**/
	
	/**[Gina]訂單前端用**/
	private static final String FIND_BY_BRANCH ="SELECT RTID, RTNAME, balance, total FROM ROOMTYPE WHERE BRAID=?";
	/**[Gina]訂單前端用**/
	
	/**[Gina]用房型找平日價格**/
	private static final String FIND_WEEKPRICE_BY_RTID ="SELECT WEEKLYPRICE FROM ROOMTYPE WHERE RTID=?";
	/**[Gina]用房型找平日價格**/
	
	/**[Gina]用房型找假日價格**/
	private static final String FIND_HOLIDAYPRICE_BY_RTID ="SELECT HOLIDAYPRICE FROM ROOMTYPE WHERE RTID=?";
	/**[Gina]用房型找假日價格**/
	
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
				}
				String balance = roomnum.toString();
				System.out.println(">=10"+balance);
				pstmt.setString(9, balance);
			}else {
				StringBuffer roomnum = new StringBuffer(roomTypeVO.getTotal());
				for(int i=0; i<31; i++) {
					roomnum.append("0"+roomTypeVO.getTotal());
				}
				String balance = roomnum.toString();
				System.out.println("<10"+balance);
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
	
	/**[Gina]訂單交易用**/
	@Override
	public void updateRoomBalance(String rtID, Date checkIn, Date checkOut, java.sql.Connection con, Map<String,Integer> rtIDandNumMap) {
		System.out.println("rt房型編號" + rtID);
		System.out.println("rt入住日期" + checkIn);
		System.out.println("rt退房日期" + checkOut);
		
		PreparedStatement pstmt = null;
		
		try {
		
			pstmt = con.prepareStatement(UPDATE_ROOMBALANCE);
			//UPDATE RoomType SET BALANCE=? WHERE RTID=?
			
			//先用主鍵找出的原剩餘房間數
			RoomTypeDAO rtdao = new RoomTypeDAO();
			RoomTypeVO findrtbalanceVO = rtdao.findByPrimaryKey(rtID);
			String beforebalance = findrtbalanceVO.getBalance();
			System.out.println("rt用主鍵找出的原剩餘房間數："+beforebalance);
			
			//先判斷住房時間有幾天
			java.util.Date checkin = (java.util.Date)checkIn;
			Long checkinlong = checkin.getTime();
			System.out.println(checkinlong);
			
			java.util.Date checkout = (java.util.Date)checkOut;
			Long checkoutlong = checkout.getTime();
			System.out.println(checkoutlong);
			
			int totalday = (int)((checkoutlong-checkinlong)/1000/60/60/24);
			System.out.println("rt住房時間有幾天:" + totalday);
			
			/***checkIn***/
			String checkinstring = checkIn.toString();
			//幾年
			int checkinyear = new Integer(checkinstring.substring(0,4));
//			System.out.println("rt入住年：" + checkinyear);
			//幾月
			int checkinmonth = new Integer(checkinstring.substring(5,7));
//			System.out.println("rt入住月：" + checkinmonth);
			//幾日	
			int checkinday = new Integer(checkinstring.substring(8));
//			System.out.println("rt入住日期：" + checkinday);
			//當月有幾天
			Calendar checkincal = new GregorianCalendar(checkinyear, checkinmonth-1, checkinday);
			int checkinDayofMonth = checkincal.getActualMaximum(Calendar.DAY_OF_MONTH);
//			System.out.println("rt-GregorianCalendar:" + checkincal.get(Calendar.YEAR) +"年"+ (checkincal.get(Calendar.MONTH)+1) +"月"+ checkincal.get(Calendar.DATE));
//			System.out.println("rt入住當月天數：" + checkinDayofMonth);
//			System.out.println("=======================");
			
			/***checkOut***/
			String checkoutstring = checkOut.toString();
			//幾年
			int checkoutyear = new Integer(checkoutstring.substring(0,4));
//			System.out.println("rt入住年：" + checkoutyear);		
			//幾月
			int checkoutmonth = new Integer(checkoutstring.substring(5,7));
//			System.out.println("rt入住月：" + checkoutmonth);		
			//幾日
			int checkoutday = new Integer(checkoutstring.substring(8));
//			System.out.println("rt退房日期：" + checkoutday);
			//當月有幾天
			Calendar checkoutcal = new GregorianCalendar(checkoutyear, checkoutmonth-1, checkoutday);
			int checkoutDayofMonth = checkoutcal.getActualMaximum(Calendar.DAY_OF_MONTH);
//			System.out.println("rt-GregorianCalendar:" + checkoutcal.get(Calendar.YEAR) +"年"+ (checkoutcal.get(Calendar.MONTH)+1) +"月"+ checkoutcal.get(Calendar.DATE));
//			System.out.println("rt退房當月天數：" + checkoutDayofMonth);	
//			System.out.println("=======================");
			
			//開始計算要修改那些欄位>>使用StringBuffer
			StringBuffer afterbalance = new StringBuffer(beforebalance);
			
			for(int i=1; i<=totalday ;i++) {
				//先找到要扣的當天房間數量 
				if((checkinday*2)>(checkinDayofMonth*2)) {
					checkinday = 1;
				}
				
				int beforeroomnum = new Integer(beforebalance.substring((checkinday*2-2),(checkinday*2)));
				System.out.println("rt找到要扣的當天房間數量："+beforeroomnum);
				
				int checkindaybindex = (checkinday*2)-2;
				int checkindayeindex = checkinday*2;
				//從map拿出數量:找出使用者選擇了幾間這種房型
				int rtNum = rtIDandNumMap.get(rtID);
System.out.println("使用者選擇了幾間這:"+rtNum);
				Integer roomnumint = new Integer(beforeroomnum-rtNum);
				
				String roomnumstr = null;
				if(roomnumint<10) {
					roomnumstr = "0" + roomnumint.toString();
				}else {
					roomnumstr = roomnumint.toString();
				}	
				System.out.println(roomnumstr);
	
				afterbalance.replace(checkindaybindex, checkindayeindex, roomnumstr);
				System.out.println("rt新的剩餘房間術欄位" + afterbalance);
				
				checkinday++;
			}
			
			//把扣好的剩餘房間數欄位還有房型id放到SQL指令裡面去
			pstmt.setString(1, afterbalance.toString());
			pstmt.setString(2, rtID);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			if (con != null) {
				try {
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-更改房型剩餘數量");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
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

		}
		
	}
	
	/**[Gina]訂單前端用**/
	@Override
	public List<RoomTypeVO> findRoomTypeByBranch(String braID) {
		List<RoomTypeVO> list = new ArrayList<RoomTypeVO>();
		RoomTypeVO roomTypeVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_BRANCH);	
			//SELECT * FROM RoomType WHERE RTID = ?
			
			pstmt.setString(1, braID);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				roomTypeVO = new RoomTypeVO();
				roomTypeVO.setRtID(rs.getString("RTID"));
				roomTypeVO.setRtName(rs.getString("RTNAME"));
				roomTypeVO.setBalance(rs.getString("balance"));
				roomTypeVO.setTotal(rs.getInt("total"));
				
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
	
	/**[Gina]用房型找平日價格**/
	@Override
	public int findWeekpriceByrtID(String rtID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int weekpriceByrtID = 0;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_WEEKPRICE_BY_RTID);	
			//SELECT WEEKLYPRICE FROM ROOMTYPE WHERE RTID=?
			
			pstmt.setString(1, rtID);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				weekpriceByrtID = rs.getInt("WEEKLYPRICE");
				System.out.println(weekpriceByrtID);
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
		
		return weekpriceByrtID;
	}
	
	/**[Gina]用房型找假日價格**/
	@Override
	public int findHollydaypriceByrtID(String rtID) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int HollydaypriceByrtID = 0;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_HOLIDAYPRICE_BY_RTID);	
			//SELECT WEEKLYPRICE FROM ROOMTYPE WHERE RTID=?
			
			pstmt.setString(1, rtID);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				HollydaypriceByrtID = rs.getInt("HOLIDAYPRICE");
				System.out.println(HollydaypriceByrtID);
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

		return HollydaypriceByrtID;
	}

	/**[Gina]排程器使用，在當天11:59分，將當天房型數量變回原房型數量**/
	@Override
	public void returnRoomNum(String balance, String rtID) {
//		System.out.println("DAO收到balance:"+balance);
//		System.out.println("DAO收到rtID:"+rtID);
		
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

	/**[Gina]取消訂單用，找到該訂單明細中的房型，並把原本有預約的房型數量+1**/
	@Override
	public void cancelOrderChangeRoomBalance(String rtID, Date checkIn, Date checkOut) {
		System.out.println("房型編號" + rtID);
		System.out.println("入住日期" + checkIn);
		System.out.println("退房日期" + checkOut);
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_ROOMBALANCE);
			//UPDATE RoomType SET BALANCE=? WHERE RTID=?
			
			//先用主鍵找出的原剩餘房間數
			RoomTypeJDBCDAO rtdao = new RoomTypeJDBCDAO();
			RoomTypeVO findrtbalanceVO = rtdao.findByPrimaryKey(rtID);
			String beforebalance = findrtbalanceVO.getBalance();
			System.out.println("用主鍵找出的原剩餘房間數："+beforebalance);
			
			//先判斷住房時間有幾天
			java.util.Date checkin = (java.util.Date)checkIn;
			Long checkinlong = checkin.getTime();
			System.out.println(checkinlong);
			
			java.util.Date checkout = (java.util.Date)checkOut;
			Long checkoutlong = checkout.getTime();
			System.out.println(checkoutlong);
			
			int totalday = (int)((checkoutlong-checkinlong)/1000/60/60/24);
			System.out.println("住房時間有幾天:" + totalday);
			
			/***checkIn***/
			String checkinstring = checkIn.toString();
			//幾年
			int checkinyear = new Integer(checkinstring.substring(0,4));
			System.out.println("入住年：" + checkinyear);
			//幾月
			int checkinmonth = new Integer(checkinstring.substring(5,7));
			System.out.println("入住月：" + checkinmonth);
			//幾日	
			int checkinday = new Integer(checkinstring.substring(8));
			System.out.println("入住日期：" + checkinday);
			//當月有幾天
			Calendar checkincal = new GregorianCalendar(checkinyear, checkinmonth-1, checkinday);
			int checkinDayofMonth = checkincal.getActualMaximum(Calendar.DAY_OF_MONTH);
			System.out.println("GregorianCalendar:" + checkincal.get(Calendar.YEAR) +"年"+ (checkincal.get(Calendar.MONTH)+1) +"月"+ checkincal.get(Calendar.DATE));
			System.out.println("入住當月天數：" + checkinDayofMonth);
			System.out.println("=======================");
			
			/***checkOut***/
			String checkoutstring = checkOut.toString();
			//幾年
			int checkoutyear = new Integer(checkoutstring.substring(0,4));
			System.out.println("入住年：" + checkoutyear);		
			//幾月
			int checkoutmonth = new Integer(checkoutstring.substring(5,7));
			System.out.println("入住月：" + checkoutmonth);		
			//幾日
			int checkoutday = new Integer(checkoutstring.substring(8));
			System.out.println("退房日期：" + checkoutday);
			//當月有幾天
			Calendar checkoutcal = new GregorianCalendar(checkoutyear, checkoutmonth-1, checkoutday);
			int checkoutDayofMonth = checkoutcal.getActualMaximum(Calendar.DAY_OF_MONTH);
			System.out.println("GregorianCalendar:" + checkoutcal.get(Calendar.YEAR) +"年"+ (checkoutcal.get(Calendar.MONTH)+1) +"月"+ checkoutcal.get(Calendar.DATE));
			System.out.println("退房當月天數：" + checkoutDayofMonth);	
			System.out.println("=======================");
			
			//開始計算要修改那些欄位>>使用StringBuffer
			StringBuffer afterbalance = new StringBuffer(beforebalance);
			
			for(int i=1; i<=totalday ;i++) {
				//先找到要扣的當天房間數量 29
				if((checkinday*2)>(checkinDayofMonth*2)) {
					checkinday = 1;
				}
				
				int beforeroomnum = new Integer(beforebalance.substring((checkinday*2-2),(checkinday*2)));
				System.out.println("找到要扣的當天房間數量："+beforeroomnum);
				
				int checkindaybindex = (checkinday*2)-2;
				int checkindayeindex = checkinday*2;
				//從map拿出數量:找出使用者選擇了幾間這種房型
				
				Integer roomnumint = new Integer(beforeroomnum+1);
				
				String roomnumstr = null;
				if(roomnumint<10) {
					roomnumstr = "0" + roomnumint.toString();
				}else {
					roomnumstr = roomnumint.toString();
				}	
				System.out.println(roomnumstr);
	
				afterbalance.replace(checkindaybindex, checkindayeindex, roomnumstr);
				System.out.println("新的剩餘房間術欄位" + afterbalance);
				
				checkinday++;
			}
			
			//把扣好的剩餘房間數欄位還有房型id放到SQL指令裡面去
			pstmt.setString(1, afterbalance.toString());
			pstmt.setString(2, rtID);
			
			pstmt.executeUpdate();

		} catch (Exception e) {
			if (con != null) {
				try {
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-更改房型剩餘數量");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
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

		}
	}
	
}
