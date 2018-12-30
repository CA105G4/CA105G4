package com.orderDetail.model;

import java.util.*;

import com.orders.model.OrdersVO;
import com.roomType.model.RoomTypeJDBCDAO;

import java.sql.*;
import java.sql.Date;

public class OrderDetailJDBCDAO implements OrderDetailDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA105G4";
	String passwd = "123456";
	
	private static final String INSERT_STMT = "INSERT INTO ORDERDETAIL(ODID, ORDID, RTID, CHECKIN, CHECKOUT, SPECIAL) VALUES (od_seq.NEXTVAL, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM OrderDetail";
	private static final String GET_ONE_STMT = "SELECT * FROM OrderDetail WHERE ODID = ?";
	
	private static final String DELETE = "DELETE FROM OrderDetail where ODID = ?";
	private static final String UPDATE = "UPDATE OrderDetail SET roomID=?, ordID=?, rtID=?, checkIn=?, checkOut=?, EVALUATES=?, special=? WHERE ODID = ?";
	
	private static final String GET_OrderDetail_ByOrders_STMT = "SELECT * FROM ORDERDETAIL WHERE ORDID=? order by ODID";
	
	@Override
	public void insert(OrderDetailVO orderDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, orderDetailVO.getOrdID());
			pstmt.setString(2, orderDetailVO.getRtID());
			pstmt.setDate(3, orderDetailVO.getCheckIn());
			pstmt.setDate(4, orderDetailVO.getCheckOut());
			pstmt.setInt(5, orderDetailVO.getSpecial());
			
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
	public void update(OrderDetailVO orderDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);	
			/*"UPDATE OrderDetail SET roomID=?, ordID=?, rtID=?, checkIn=?, 
			checkOut=?, rtName=?, EVALUATES=?, special=? WHERE ODID = ?" */
			
			pstmt.setString(1, orderDetailVO.getRoomID());
			pstmt.setString(2, orderDetailVO.getOrdID());
			pstmt.setString(3, orderDetailVO.getRtID());
			pstmt.setDate(4, orderDetailVO.getCheckIn());
			pstmt.setDate(5, orderDetailVO.getCheckOut());
			pstmt.setDouble(6, orderDetailVO.getEvaluates());
			pstmt.setInt(7, orderDetailVO.getSpecial());
			
			pstmt.setInt(8, orderDetailVO.getOdID());
			
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
	public void delete(Integer odID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);	//DELETE FROM OrderDetail where ODID = ?
			
			pstmt.setInt(1, odID);
			System.out.println(odID);
			
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
	public OrderDetailVO findByPrimaryKey(Integer odID) {
		OrderDetailVO orderDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);	//SELECT * FROM OrderDetail WHERE ODID = ?
			
			pstmt.setInt(1, odID);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				orderDetailVO = new OrderDetailVO();
				orderDetailVO.setOdID(rs.getInt("ODID"));
				orderDetailVO.setRoomID(rs.getString("ROOMID"));
				orderDetailVO.setOrdID(rs.getString("ORDID"));
				orderDetailVO.setOrdID(rs.getString("RTID"));
				orderDetailVO.setCheckIn(rs.getDate("CHECKIN"));
				orderDetailVO.setCheckOut(rs.getDate("CHECKOUT"));
				orderDetailVO.setRtName(rs.getString("RTNAME"));
				orderDetailVO.setEvaluates(rs.getDouble("EVALUATES"));
				orderDetailVO.setSpecial(rs.getInt("SPECIAL"));
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
		
		return orderDetailVO;
	}

	@Override
	public List<OrderDetailVO> getALL() {
		List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
		OrderDetailVO orderDetailVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);	//SELECT * FROM OrderDetail
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				orderDetailVO = new OrderDetailVO();
				orderDetailVO.setOdID(rs.getInt("ODID"));
				orderDetailVO.setRoomID(rs.getString("ROOMID"));
				orderDetailVO.setOrdID(rs.getString("ORDID"));
				orderDetailVO.setOrdID(rs.getString("RTID"));
				orderDetailVO.setCheckIn(rs.getDate("CHECKIN"));
				orderDetailVO.setCheckOut(rs.getDate("CHECKOUT"));
				orderDetailVO.setRtName(rs.getString("RTNAME"));
				orderDetailVO.setEvaluates(rs.getDouble("EVALUATES"));
				orderDetailVO.setSpecial(rs.getInt("SPECIAL"));
				list.add(orderDetailVO);
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
	public Set<OrderDetailVO> findByOrders(String ordID) {
		Set<OrderDetailVO> set = new LinkedHashSet<OrderDetailVO>();
		OrderDetailVO orderDetailVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_OrderDetail_ByOrders_STMT);	//SELECT * FROM ORDERDETAIL WHERE ORDID=?
			
			pstmt.setString(1, ordID);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				orderDetailVO = new OrderDetailVO();
				orderDetailVO.setOdID(rs.getInt("ODID"));
				orderDetailVO.setRoomID(rs.getString("ROOMID"));
				orderDetailVO.setOrdID(rs.getString("ORDID"));
				orderDetailVO.setOrdID(rs.getString("RTID"));
				orderDetailVO.setCheckIn(rs.getDate("CHECKIN"));
				orderDetailVO.setCheckOut(rs.getDate("CHECKOUT"));
				orderDetailVO.setRtName(rs.getString("RTNAME"));
				orderDetailVO.setEvaluates(rs.getDouble("EVALUATES"));
				orderDetailVO.setSpecial(rs.getInt("SPECIAL"));
				set.add(orderDetailVO);
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
		
		return set;
	}
	
	
	@Override
	public void insertwithOrders(OrderDetailVO orderDetailVO, Connection con) {
		
		PreparedStatement pstmt = null;
		
		try {
			
			pstmt = con.prepareStatement(INSERT_STMT);
			//INSERT INTO ORDERDETAIL(ODID, ORDID, RTID, CHECKIN, CHECKOUT, SPECIAL) VALUES (od_seq.NEXTVAL, ?, ?, ?, ?, ?)
			
			pstmt.setString(1, orderDetailVO.getOrdID());
			pstmt.setString(2, orderDetailVO.getRtID());
			pstmt.setDate(3, orderDetailVO.getCheckIn());
			pstmt.setDate(4, orderDetailVO.getCheckOut());
			pstmt.setInt(5, orderDetailVO.getSpecial());
			
			pstmt.executeUpdate();
			
			/****去更改房型狀態****/
			RoomTypeJDBCDAO rtdao = new RoomTypeJDBCDAO();
			rtdao.updateRoomBalance(orderDetailVO.getRtID(), orderDetailVO.getCheckIn(), orderDetailVO.getCheckOut(), con);
			System.out.println("房型編號" + orderDetailVO.getRtID());
			System.out.println("入住日期" + orderDetailVO.getCheckIn());
			System.out.println("退房日期" + orderDetailVO.getCheckOut());
			
			
			
		} catch (Exception e) {
			if (con != null) {
				try {
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-訂單明細");
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
		OrderDetailJDBCDAO dao = new OrderDetailJDBCDAO();
		
		
//		//新增
//		Date checkin = Date.valueOf("2019-01-15");
//		Date checkout = Date.valueOf("2019-01-16");
//		
//		OrderDetailVO od01 = new OrderDetailVO();
//		od01.setOrdID("20181223-000008");
//		od01.setRtID("RT02");
//		od01.setCheckIn(checkin);
//		od01.setCheckOut(checkout);
//		od01.setSpecial(1);
//		
//		dao.insert(od01);
//		System.out.println("新增成功!!");
		
		//修改
//		OrderDetailVO od02 = new OrderDetailVO();
//		od02.setRoomID("R00002");
//		od02.setOrdID("20181223-000010");
//		od02.setRtID("RT02");
//		
//		od02.setCheckIn(Date.valueOf("2018-11-10"));
//		od02.setCheckOut(Date.valueOf("2018-11-12"));	
//		
//		od02.setEvaluates(8.0);
//		od02.setSpecial(1);
//		od02.setOdID(1005);
//		
//		dao.update(od02);
//		System.out.println("修改成功!!!");
		
		//刪除	!!!! 記得要commit;	!!!	
//		dao.delete(1012);
//		System.out.println("成功刪除!!");
		
		//查詢一筆
//		OrderDetailVO od03 = dao.findByPrimaryKey(1009);
//		System.out.println(od03.getOdID());
//		System.out.println(od03.getRoomID());
//		System.out.println(od03.getOrdID());
//		System.out.println(od03.getRtID());
//		System.out.println(od03.getCheckIn());
//		System.out.println(od03.getCheckOut());
//		System.out.println(od03.getRtName());
//		System.out.println(od03.getEvaluates());
//		System.out.println(od03.getSpecial());
//		System.out.println("========================");
		
		//查詢多筆
//		List<OrderDetailVO> list = dao.getALL();
//		for(OrderDetailVO od : list) {
//			System.out.println(od.getOdID());
//			System.out.println(od.getRoomID());
//			System.out.println(od.getOrdID());
//			System.out.println(od.getRtID());
//			System.out.println(od.getCheckIn());
//			System.out.println(od.getCheckOut());
//			System.out.println(od.getRtName());
//			System.out.println(od.getEvaluates());
//			System.out.println(od.getSpecial());
//			System.out.println("---------------------------");
//		}
		
		//查找單筆訂單的明細
		Set<OrderDetailVO> set = dao.findByOrders("20181223-000002");
		
		for(OrderDetailVO od : set) {
		System.out.println(od.getOdID());
		System.out.println(od.getRoomID());
		System.out.println(od.getOrdID());
		System.out.println(od.getRtID());
		System.out.println(od.getCheckIn());
		System.out.println(od.getCheckOut());
		System.out.println(od.getRtName());
		System.out.println(od.getEvaluates());
		System.out.println(od.getSpecial());
		System.out.println("---------------------------");
		}
		
		
	}



}
