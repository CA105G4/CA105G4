package com.roomType.model;

import java.util.*;
import java.sql.*;
import java.sql.Date;

public class RoomTypeJDBCDAO implements RoomTypeDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA105G4";
	String passwd = "123456";
	
	private static final String INSERT_STMT = "INSERT INTO RoomType(rtID,braID,rtName,rtPic,rtIntro,rtMinimum,rtLimit,weeklyprice,holidayprice,balance,total) values('RT'||LPAD(to_char(rt_seq.NEXTVAL),2,'0'),?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM RoomType";
	private static final String GET_ONE_STMT = "SELECT * FROM RoomType WHERE RTID = ?";
	
	private static final String DELETE = "DELETE FROM RoomType where RTID = ?";
	private static final String UPDATE = "UPDATE RoomType SET BRAID=?, RTNAME=?, RTPIC = ?, RTINTRO=?, RTMINIMUM=?, RTLIMIT=?, WEEKLYPRICE=?, HOLIDAYPRICE=?, BALANCE=?, TOTAL=?  WHERE RTID = ?";
	
	/**[Gina]訂單交易用**/
	private static final String UPDATE_ROOMBALANCE ="UPDATE RoomType SET BALANCE=? WHERE RTID=?"; 
	/**[Gina]訂單交易用**/
	
	/**[Gina]訂單前端用**/
	private static final String FIND_BY_BRANCH ="SELECT RTID, RTNAME FROM ROOMTYPE WHERE BRAID=?";
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
			//SELECT * FROM RoomType WHERE RTID = ?
			
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
	
	
	@Override
	public void updateRoomBalance(String rtID, Date checkIn, Date checkOut, java.sql.Connection con, Map<String,Integer> rtIDandNumMap) {
		System.out.println("房型編號" + rtID);
		System.out.println("入住日期" + checkIn);
		System.out.println("退房日期" + checkOut);
		
		PreparedStatement pstmt = null;
		
		try {
		
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
				int rtNum = rtIDandNumMap.get(rtID);
				
				Integer roomnumint = new Integer(beforeroomnum-rtNum);
				
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
	
	@Override
	public List<RoomTypeVO> findRoomTypeByBranch(String braID) {
		List<RoomTypeVO> list = new ArrayList<RoomTypeVO>();
		RoomTypeVO roomTypeVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_BY_BRANCH);	
			//SELECT * FROM RoomType WHERE RTID = ?
			
			pstmt.setString(1, braID);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				roomTypeVO = new RoomTypeVO();
				roomTypeVO.setRtID(rs.getString("RTID"));
				roomTypeVO.setRtName(rs.getString("RTNAME"));	
				
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
	
	@Override
	public int findWeekpriceByrtID(String rtID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int weekpriceByrtID = 0;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_WEEKPRICE_BY_RTID);	
			//SELECT WEEKLYPRICE FROM ROOMTYPE WHERE RTID=?
			
			pstmt.setString(1, rtID);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				weekpriceByrtID = rs.getInt("WEEKLYPRICE");
				System.out.println(weekpriceByrtID);
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
		
		return weekpriceByrtID;
	}

	@Override
	public int findHollydaypriceByrtID(String rtID) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int HollydaypriceByrtID = 0;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_HOLIDAYPRICE_BY_RTID);	
			//SELECT WEEKLYPRICE FROM ROOMTYPE WHERE RTID=?
			
			pstmt.setString(1, rtID);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				HollydaypriceByrtID = rs.getInt("HOLIDAYPRICE");
				System.out.println(HollydaypriceByrtID);
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

		return HollydaypriceByrtID;
	}
	
	/**[Gina]排程器使用，在當天11:59分，將當天房型數量變回原房型數量**/
	@Override
	public void returnRoomNum(String balance, String rtID) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_ROOMBALANCE);
			//UPDATE RoomType SET BALANCE=? WHERE RTID=?
			
			pstmt.setString(1, balance);
			pstmt.setString(2, rtID);
			
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
	
	/**[Gina]取消訂單用，找到該訂單明細中的房型，並把原本有預約的房型數量+1**/
	@Override
	public void cancelOrderChangeRoomBalance(String rtID, Date checkIn, Date checkOut) {
		System.out.println("房型編號" + rtID);
		System.out.println("入住日期" + checkIn);
		System.out.println("退房日期" + checkOut);
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
//		roomTypeVO.setTotal(3);
//		dao.insert(roomTypeVO);
//		System.out.println("新增成功!!");
		
		//修改
//		RoomTypeVO roomTypeVO02 = new RoomTypeVO();
//		roomTypeVO02.setRtID("RT10");
//		roomTypeVO02.setBraID("B02");
//		roomTypeVO02.setRtName("頂級總統套房");
//		
//		try {
//			roomTypeVO02.setRtPic(new BLOB().writeBlob("images/IMG01.jpg"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		roomTypeVO02.setRtIntro("山莊最頂級的總統級套房");
//		roomTypeVO02.setRtMinimum(2);
//		roomTypeVO02.setRtLimit(3);
//		roomTypeVO02.setWeeklyPrice(5000);
//		roomTypeVO02.setHolidayPrice(6500);
//		roomTypeVO02.setTotal(3);
//		
//		dao.update(roomTypeVO02);
//		
//		System.out.println("修改成功!!");
		
		//刪除
//		dao.delete("RT12");
//		System.out.println("刪除成功!!");
		
		//查詢一筆
//		RoomTypeVO roomTypeVO03 = dao.findByPrimaryKey("RT01");
//		System.out.println(roomTypeVO03.getRtID());
//		System.out.println(roomTypeVO03.getBraID());
//		System.out.println(roomTypeVO03.getRtName());
//		
//		new BLOB().readBlob(roomTypeVO03.getRtPic(),"input/IMG01.jpg");
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
		
		//修改剩餘房間數
//		RoomTypeVO roomTypeVO02 = new RoomTypeVO();
//		
//		dao.updateRoomBalance("03030303030303030303030303030303030303030303030303030303030303", "RT10");
//		
//		System.out.println("修改成功!!");		
		
//		List<RoomTypeVO> rtlist = dao.findRoomTypeByBranch("B01");
//		for(RoomTypeVO rtVO : rtlist) {
//			System.out.println(rtVO.getRtID());
//			System.out.println(rtVO.getRtName());
//			System.out.println("=========================");
//		}
		
		int wprice = dao.findWeekpriceByrtID("RT02");
		System.out.println(wprice);
		System.out.println("=======================");
		int hprice = dao.findHollydaypriceByrtID("RT02");
		System.out.println(hprice);
		
	}

	@Override
	public Set<RoomTypeVO> getAllInSet() {
		// TODO Auto-generated method stub
		return null;
	}

}
