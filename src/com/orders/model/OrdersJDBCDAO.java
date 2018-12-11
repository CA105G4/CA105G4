package com.orders.model;

import java.util.*;
import java.sql.*;

public class OrdersJDBCDAO implements OrdersDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA105G4";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO ORDERS(ORDID, MEMID, BRAID, NUMOFROOM, NUMOFGUEST) VALUES (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(ord_seq.NEXTVAL), 6, '0'), ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM ORDERS";
	private static final String GET_ONE_STMT = "SELECT * FROM ORDERS WHERE ordID = ?";

	private static final String UPDATE = "UPDATE ORDERS SET ORDSTATE=? WHERE ORDID = ?";

	@Override
	public void insert(OrdersVO ordersVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);	
			/*INSERT INTO ORDERS(ORDID, MEMID, BRAID, NUMOFROOM, NUMOFGUEST) 
			 * VALUES (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(ord_seq.NEXTVAL), 6, '0'), ?, ?, ?, ?)*/
			
			pstmt.setString(1, ordersVO.getMemID());
			pstmt.setString(2, ordersVO.getBraID());
			pstmt.setInt(3, ordersVO.getNumOfRoom());
			pstmt.setInt(4, ordersVO.getNumOfGuest());
			
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
	public void update(OrdersVO ordersVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);	//UPDATE ORDERS SET ORDSTATE = ? WHERE ORDID = '?'
			
			pstmt.setInt(1, ordersVO.getOrdState());
			System.out.println(ordersVO.getOrdState());
			pstmt.setString(2, ordersVO.getOrdID());
			System.out.println(ordersVO.getOrdID());
			
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
	public OrdersVO findByPrimaryKey(String ordID) {
		OrdersVO ordersVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);	//SELECT * FROM ORDERS WHERE ordID = ?
			
			pstmt.setString(1, ordID);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ordersVO = new OrdersVO();
				ordersVO.setOrdID(rs.getString("ORDID"));
				ordersVO.setMemID(rs.getString("MEMID"));
				ordersVO.setBraID(rs.getString("BRAID"));
				ordersVO.setNumOfRoom(rs.getInt("NUMOFROOM"));
				ordersVO.setOrdType(rs.getInt("ORDTYPE"));
				ordersVO.setNumOfGuest(rs.getInt("NUMOFGUEST"));
				ordersVO.setAmount(rs.getInt("AMOUNT"));
				ordersVO.setBond(rs.getInt("BOND"));
				ordersVO.setPayment(rs.getInt("PAYMENT"));
				ordersVO.setOrdState(rs.getInt("ORDSTATE"));
				ordersVO.setOrdTime(rs.getDate("ORDTIME"));
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
		
		return ordersVO;
	}

	@Override
	public List<OrdersVO> getAll() {
		List<OrdersVO> list = new ArrayList<OrdersVO>();
		OrdersVO ordersVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);	//SELECT * FROM ORDERS
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ordersVO = new OrdersVO();
				ordersVO.setOrdID(rs.getString("ORDID"));
				ordersVO.setMemID(rs.getString("MEMID"));
				ordersVO.setBraID(rs.getString("BRAID"));
				ordersVO.setNumOfRoom(rs.getInt("NUMOFROOM"));
				ordersVO.setOrdType(rs.getInt("ORDTYPE"));
				ordersVO.setNumOfGuest(rs.getInt("NUMOFGUEST"));
				ordersVO.setAmount(rs.getInt("AMOUNT"));
				ordersVO.setBond(rs.getInt("BOND"));
				ordersVO.setPayment(rs.getInt("PAYMENT"));
				ordersVO.setOrdState(rs.getInt("ORDSTATE"));
				ordersVO.setOrdTime(rs.getDate("ORDTIME"));
				list.add(ordersVO);	// Store the row in the list
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
	
	
	
	//測試執行方法
	public static void main(String[] args) {
		OrdersJDBCDAO dao = new OrdersJDBCDAO();
		
		//新增insert
//		OrdersVO ordersVO01 = new OrdersVO();
//		ordersVO01.setMemID("M0001");
//		ordersVO01.setBraID("B01");
//		ordersVO01.setNumOfRoom(2);
//		ordersVO01.setNumOfGuest(6);
//		dao.insert(ordersVO01);
//		System.out.println("新增成功!!");
		
		
		//修改update
//		OrdersVO ordersVO02 = new OrdersVO();
//		ordersVO02.setOrdState(3);
//		ordersVO02.setOrdID("20181209-000003");
//		dao.update(ordersVO02);
//		System.out.println("修改成功!!");
		
		//查詢一筆
		OrdersVO ordersVO03 = dao.findByPrimaryKey("20181209-000003");
		System.out.println(ordersVO03.getOrdID());
		System.out.println(ordersVO03.getMemID());
		System.out.println(ordersVO03.getBraID());
		System.out.println(ordersVO03.getNumOfRoom());
		System.out.println(ordersVO03.getOrdType());
		System.out.println(ordersVO03.getNumOfGuest());
		System.out.println(ordersVO03.getAmount());
		System.out.println(ordersVO03.getBond());
		System.out.println(ordersVO03.getPayment());
		System.out.println(ordersVO03.getOrdState());
		System.out.println(ordersVO03.getOrdTime());
		System.out.println("=====================================");
		
		//查詢全部
		List<OrdersVO> list = dao.getAll();
		for(OrdersVO ord : list) {
			System.out.println(ord.getOrdID());
			System.out.println(ord.getMemID());
			System.out.println(ord.getBraID());
			System.out.println(ord.getNumOfRoom());
			System.out.println(ord.getOrdType());
			System.out.println(ord.getNumOfGuest());
			System.out.println(ord.getAmount());
			System.out.println(ord.getBond());
			System.out.println(ord.getPayment());
			System.out.println(ord.getOrdState());
			System.out.println(ord.getOrdTime());
		}
		
		
	}


}
