package com.orders.model;

import java.util.*;

import com.orderDetail.model.OrderDetailJDBCDAO;
import com.orderDetail.model.OrderDetailVO;

import java.sql.*;
import java.sql.Date;

public class OrdersJDBCDAO implements OrdersDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA105G4";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO ORDERS(ORDID, MEMID, BRAID, NUMOFROOM, ORDTYPE, NUMOFGUEST, AMOUNT, BOND, PAYMENT) VALUES (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(ord_seq.NEXTVAL), 6, '0'), ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM ORDERS";
	private static final String GET_ONE_STMT = "SELECT * FROM ORDERS WHERE ordID = ?";

	private static final String UPDATE = "UPDATE ORDERS SET  MEMID=?, BRAID=?, NUMOFROOM=?, ORDTYPE=?, NUMOFGUEST=?, AMOUNT=?, BOND=?, PAYMENT=?, ORDSTATE=?, ORDTIME=? WHERE ORDID=?";

	private static final String GET_OrderDetail_ByOrders_STMT = "SELECT * FROM ORDERDETAIL WHERE ORDID=? order by ODID";
	
	//CHECKIN
	private static final String GET_CHECKIN_ByOrdJoinOD_STMT = "SELECT * FROM ORDERS LEFT JOIN ORDERDETAIL ON ORDERS.ORDID=ORDERDETAIL.ORDID WHERE CHECKIN=? AND BRAID=? ORDER BY ORDERS.ORDID";
	
	//CHECKOUT
	private static final String GET_CHECKOUT_ByOrdJoinOD_STMT = "SELECT * FROM ORDERS LEFT JOIN ORDERDETAIL ON ORDERS.ORDID=ORDERDETAIL.ORDID WHERE CHECKOUT=? AND BRAID=? ORDER BY ORDERS.ORDID";
	//[CHECKIN]更改訂單狀態
	private static final String UPDATE_ordState_ByordID = "UPDATE ORDERS SET ORDSTATE=? WHERE ORDID=?";

	//[addOrders_Step2]查詢
	private static final String SELECT_NEWORDERID_ByMemID = "SELECT ORDID FROM ORDERS WHERE MEMID=? ORDER BY ORDID DESC";

	//用分店查找訂單
	private static final String SELECT_ORDERS_BybraID = "SELECT * FROM ORDERS WHERE BRAID=?";
	
	//用分店及訂單種類ordType(0線上,1臨櫃)查找訂單
	private static final String SELECT_ORDERS_ByordType01 = "SELECT * FROM ORDERS WHERE BRAID=? AND (ordType=0 OR ordType=1)";
	
	//用分店及訂單種類ordType(2打工換宿)查找訂單
	private static final String SELECT_ORDERS_ByordType2 = "SELECT * FROM ORDERS WHERE BRAID=? AND ordType=2";
	
	//用分店及訂單狀態ordState(3退訂)查找訂單
	private static final String SELECT_ORDERS_ByordState3 = "SELECT * FROM ORDERS WHERE BRAID=? AND ordState=3";
	
	//用會員memID及訂單狀態ordState(0預訂)查找訂單
	private static final String SELECT_ORDERS_BymemIDordSta0 = "SELECT * FROM ORDERS WHERE MEMID=? AND ordState=0";
	
	//用會員memID及訂單狀態ordState(1入住2退房3退房)查找訂單
	private static final String SELECT_ORDERS_BymemIDordSta123 = "SELECT * FROM ORDERS WHERE MEMID=? AND (ordState=1 OR ordState=2 OR ordState=3)";
			
	@Override
	public void insert(OrdersVO ordersVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);	
			/*INSERT INTO ORDERS(ORDID, MEMID, BRAID, NUMOFROOM, ORDTYPE, NUMOFGUEST, AMOUNT, BOND, PAYMENT) 
			 * VALUES (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(ord_seq.NEXTVAL), 6, '0'), ?, ?, ?, ?, ?, ?, ?, ?)*/
			
			pstmt.setString(1, ordersVO.getMemID());
			pstmt.setString(2, ordersVO.getBraID());
			pstmt.setInt(3, ordersVO.getNumOfRoom());
			pstmt.setInt(4, ordersVO.getOrdType());
			pstmt.setInt(5, ordersVO.getNumOfGuest());
			pstmt.setInt(6, ordersVO.getAmount());
			pstmt.setInt(7, ordersVO.getBond());
			pstmt.setInt(8, ordersVO.getPayment());
			
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
			pstmt = con.prepareStatement(UPDATE);	
			//UPDATE ORDERS SET  MEMID=?, BRAID=?, NUMOFROOM=?, ORDTYPE=?, NUMOFGUEST=?, AMOUNT=?, BOND=?, PAYMENT=?, ORDSTATE=?, ORDTIME=? WHERE ORDID=?
			
			pstmt.setString(1, ordersVO.getMemID());
			pstmt.setString(2, ordersVO.getBraID());
			pstmt.setInt(3, ordersVO.getNumOfRoom());
			pstmt.setInt(4, ordersVO.getOrdType());
			pstmt.setInt(5, ordersVO.getNumOfGuest());
			pstmt.setInt(6, ordersVO.getAmount());
			pstmt.setInt(7, ordersVO.getBond());
			pstmt.setInt(8, ordersVO.getPayment());
			pstmt.setInt(9, ordersVO.getOrdState());
			pstmt.setDate(10, ordersVO.getOrdTime());
			
			pstmt.setString(11, ordersVO.getOrdID());
			
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
	
	@Override
	public Set<OrderDetailVO> getOrderDetailByOrders(String ordID) {
		Set<OrderDetailVO> set = new LinkedHashSet<OrderDetailVO>();
		OrderDetailVO orderDetailVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_OrderDetail_ByOrders_STMT);	//SELECT * FROM ORDERDETAIL WHERE ORDID=? order by ODID
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
			e.printStackTrace();
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
	public void insertwithOrderDetail(OrdersVO ordersVO, List<OrderDetailVO> odlist, Map<String,Integer> rtIDandNumMap) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			/****關閉autoCommit連線****/
			con.setAutoCommit(false);
			
			/****新增訂單先****/
			String cols[] = {"ORDID"};	
			pstmt = con.prepareStatement(INSERT_STMT, cols);  //綁定自增主鍵
			/*INSERT INTO ORDERS(ORDID, MEMID, BRAID, NUMOFROOM, ORDTYPE, NUMOFGUEST, AMOUNT, BOND, PAYMENT) 
			 * VALUES (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(ord_seq.NEXTVAL), 6, '0'), ?, ?, ?, ?, ?, ?, ?, ?)*/
			pstmt.setString(1, ordersVO.getMemID());
			pstmt.setString(2, ordersVO.getBraID());
			pstmt.setInt(3, ordersVO.getNumOfRoom());
			pstmt.setInt(4, ordersVO.getOrdType());
			pstmt.setInt(5, ordersVO.getNumOfGuest());
			pstmt.setInt(6, ordersVO.getAmount());
			pstmt.setInt(7, ordersVO.getBond());
			pstmt.setInt(8, ordersVO.getPayment());
			
			pstmt.executeUpdate();
			
			/****取得當前新增的自增主鍵****/
			String now_ordID = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				now_ordID = rs.getString(1);
				System.out.println("自增主鍵值= " + now_ordID + "剛新增成功的訂單標號");
			}else {
				System.out.println("未取得自增主鍵值!");
			}
			
			rs.close();
			
			/****新增訂單明細****/
			OrderDetailJDBCDAO oddao = new OrderDetailJDBCDAO();
			System.out.println("list.size()-A="+ odlist.size() + "筆明細同時被新增");	//有幾個明細就跑幾次
			
			for(OrderDetailVO orderDetailVO : odlist) {
				orderDetailVO.setOrdID(now_ordID);	//將取到的主鍵set進去orderDetailVO的ordID中
				oddao.insertwithOrders(orderDetailVO, con, rtIDandNumMap);	//請明細的dao呼叫同時新增明細的方法
			}
			
			con.commit();
			
			System.out.println("新增訂單編號 " + now_ordID + " 時，共有明細 " + odlist.size() + " 筆同時被新增");
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (Exception e) {
				if (con != null) {
					try {
						System.err.print("Transaction is being ");
						System.err.println("rolled back-由-訂單");
						con.rollback();
					} catch (SQLException excep) {
						throw new RuntimeException("rollback error occured. " + excep.getMessage());
					}
				}
			throw new RuntimeException("A database error occured. " + e.getMessage());
			// Clean up JDBC resources
		}finally {
			
			try {
				con.setAutoCommit(true);
			} catch (SQLException e1) {
				e1.printStackTrace();
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

		
	}
	
	@Override
	public List<OrdersCheckInOutVO> findCheckInByOrdJoinOD(Date checkIn, String braID) {
		List<OrdersCheckInOutVO> list = new ArrayList<OrdersCheckInOutVO>();
		OrdersCheckInOutVO ordCheckInOutVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_CHECKIN_ByOrdJoinOD_STMT);	
			//SELECT * FROM ORDERS LEFT JOIN ORDERDETAIL ON ORDERS.ORDID=ORDERDETAIL.ORDID WHERE CHECKIN=? AND BRAID=? ORDER BY ORDERS.ORDID
			
			pstmt.setDate(1, checkIn);
			pstmt.setString(2, braID);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				//訂單
				ordCheckInOutVO = new OrdersCheckInOutVO();
				ordCheckInOutVO.setOrdID(rs.getString("ORDID"));
				ordCheckInOutVO.setMemID(rs.getString("MEMID"));
				ordCheckInOutVO.setBraID(rs.getString("BRAID"));
				ordCheckInOutVO.setNumOfRoom(rs.getInt("NUMOFROOM"));
				ordCheckInOutVO.setOrdType(rs.getInt("ORDTYPE"));
				ordCheckInOutVO.setNumOfGuest(rs.getInt("NUMOFGUEST"));
				ordCheckInOutVO.setAmount(rs.getInt("AMOUNT"));
				ordCheckInOutVO.setBond(rs.getInt("BOND"));
				ordCheckInOutVO.setPayment(rs.getInt("PAYMENT"));
				ordCheckInOutVO.setOrdState(rs.getInt("ORDSTATE"));
				ordCheckInOutVO.setOrdTime(rs.getDate("ORDTIME"));
				//訂單明細
				ordCheckInOutVO.setOdID(rs.getInt("ODID"));
				ordCheckInOutVO.setRoomID(rs.getString("ROOMID"));
				ordCheckInOutVO.setOrdID(rs.getString("RTID"));
				ordCheckInOutVO.setCheckIn(rs.getDate("CHECKIN"));
				ordCheckInOutVO.setCheckOut(rs.getDate("CHECKOUT"));
				ordCheckInOutVO.setRtName(rs.getString("RTNAME"));
				ordCheckInOutVO.setEvaluates(rs.getDouble("EVALUATES"));
				ordCheckInOutVO.setSpecial(rs.getInt("SPECIAL"));
				
				list.add(ordCheckInOutVO);	// Store the row in the list
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
	public List<OrdersCheckInOutVO> findCheckOut_ByOrdJoinOD(Date checkOut, String braID) {
		List<OrdersCheckInOutVO> list = new ArrayList<OrdersCheckInOutVO>();
		OrdersCheckInOutVO ordCheckInOutVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_CHECKOUT_ByOrdJoinOD_STMT);	
			//SELECT * FROM ORDERS LEFT JOIN ORDERDETAIL ON ORDERS.ORDID=ORDERDETAIL.ORDID WHERE CHECKIN=? AND BRAID=? ORDER BY ORDERS.ORDID
			
			pstmt.setDate(1, checkOut);
			pstmt.setString(2, braID);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				//訂單
				ordCheckInOutVO = new OrdersCheckInOutVO();
				ordCheckInOutVO.setOrdID(rs.getString("ORDID"));
				ordCheckInOutVO.setMemID(rs.getString("MEMID"));
				ordCheckInOutVO.setBraID(rs.getString("BRAID"));
				ordCheckInOutVO.setNumOfRoom(rs.getInt("NUMOFROOM"));
				ordCheckInOutVO.setOrdType(rs.getInt("ORDTYPE"));
				ordCheckInOutVO.setNumOfGuest(rs.getInt("NUMOFGUEST"));
				ordCheckInOutVO.setAmount(rs.getInt("AMOUNT"));
				ordCheckInOutVO.setBond(rs.getInt("BOND"));
				ordCheckInOutVO.setPayment(rs.getInt("PAYMENT"));
				ordCheckInOutVO.setOrdState(rs.getInt("ORDSTATE"));
				ordCheckInOutVO.setOrdTime(rs.getDate("ORDTIME"));
				//訂單明細
				ordCheckInOutVO.setOdID(rs.getInt("ODID"));
				ordCheckInOutVO.setRoomID(rs.getString("ROOMID"));
				ordCheckInOutVO.setOrdID(rs.getString("RTID"));
				ordCheckInOutVO.setCheckIn(rs.getDate("CHECKIN"));
				ordCheckInOutVO.setCheckOut(rs.getDate("CHECKOUT"));
				ordCheckInOutVO.setRtName(rs.getString("RTNAME"));
				ordCheckInOutVO.setEvaluates(rs.getDouble("EVALUATES"));
				ordCheckInOutVO.setSpecial(rs.getInt("SPECIAL"));
				
				list.add(ordCheckInOutVO);	// Store the row in the list
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
	public void updateOrdState(Integer ordState, String ordID) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_ordState_ByordID);	
			//UPDATE ORDERS SET ORDSTATE=? WHERE ORDID=?			
			
			pstmt.setInt(1, ordState);
			pstmt.setString(2, ordID);
			
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
	public String findNewOrderID(String memID) {
		String ordID="";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_NEWORDERID_ByMemID);	//SELECT * FROM ORDERS
			pstmt.setString(1, memID);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				ordID = rs.getString(1);
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

		return ordID;
	}

	//用分店查找訂單
	@Override
	public List<OrdersVO> findBybraID(String braID) {
		List<OrdersVO> list = new ArrayList<OrdersVO>();
		OrdersVO ordersVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_ORDERS_BybraID);
			//SELECT * FROM ORDERS WHERE BRAID=?
			
			pstmt.setString(1, braID);
			
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
				list.add(ordersVO);	
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
	
	//用分店及訂單種類ordType(0線上,1臨櫃)查找訂單
	@Override
	public List<OrdersVO> findByordType01(String braID) {
		List<OrdersVO> list = new ArrayList<OrdersVO>();
		OrdersVO ordersVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_ORDERS_ByordType01);
			//SELECT * FROM ORDERS WHERE BRAID=?
			
			pstmt.setString(1, braID);
			
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
				list.add(ordersVO);	
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

	//用分店及訂單種類ordType(2打工換宿)查找訂單
	@Override
	public List<OrdersVO> findByordType2(String braID) {
		List<OrdersVO> list = new ArrayList<OrdersVO>();
		OrdersVO ordersVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_ORDERS_ByordType2);
			//SELECT * FROM ORDERS WHERE BRAID=?
			
			pstmt.setString(1, braID);
			
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
				list.add(ordersVO);	
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

	//用分店及訂單狀態ordState(3退訂)查找訂單
	@Override
	public List<OrdersVO> findByordState3(String braID) {
		List<OrdersVO> list = new ArrayList<OrdersVO>();
		OrdersVO ordersVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_ORDERS_ByordState3);
			//SELECT * FROM ORDERS WHERE BRAID=?
			
			pstmt.setString(1, braID);
			
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
				list.add(ordersVO);	
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
	
	//用會員memID及訂單狀態ordState(0預訂)查找訂單
	@Override
	public List<OrdersVO> findOrdersBymemIDordState0(String memID) {
		List<OrdersVO> list = new ArrayList<OrdersVO>();
		OrdersVO ordersVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_ORDERS_BymemIDordSta0);
			//SELECT * FROM ORDERS WHERE BRAID=?
			
			pstmt.setString(1, memID);
			
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
				list.add(ordersVO);	
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
	
	//用會員memID及訂單狀態ordState(1入住2退房3退房)查找訂單
	@Override
	public List<OrdersVO> findOrdersBymemIDordState123(String memID) {
		List<OrdersVO> list = new ArrayList<OrdersVO>();
		OrdersVO ordersVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_ORDERS_BymemIDordSta123);
			//SELECT * FROM ORDERS WHERE BRAID=?
			
			pstmt.setString(1, memID);
			
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
				list.add(ordersVO);	
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
		
//		//新增insert
//		/**新增的訂單**/
//		OrdersVO ordersVO = new OrdersVO();
//		ordersVO.setMemID("M0001");
//		ordersVO.setBraID("B01");
//		ordersVO.setNumOfRoom(2);
//		ordersVO.setOrdType(0);;
//		ordersVO.setNumOfGuest(6);
//		ordersVO.setAmount(3000);
//		ordersVO.setBond(2550);
//		ordersVO.setPayment(0);
//		
//		/**同時新增的訂單明細**/
//		List<OrderDetailVO> odlist = new ArrayList<OrderDetailVO>();
//		
//		OrderDetailVO odVO01 = new OrderDetailVO();	//第一張明細		
//		odVO01.setRtID("RT02");
//		odVO01.setCheckIn(Date.valueOf("2018-12-31"));
//		odVO01.setCheckOut(Date.valueOf("2019-01-02"));
//		odVO01.setSpecial(1);
//		
//		odlist.add(odVO01);
//		
//		OrderDetailVO odVO02 = new OrderDetailVO();	//第一張明細		
//		odVO02.setRtID("RT01");
//		odVO02.setCheckIn(Date.valueOf("2018-12-31"));
//		odVO02.setCheckOut(Date.valueOf("2019-01-02"));
//		odVO02.setSpecial(1);
//		
//		odlist.add(odVO02);	
//		
//		dao.insertwithOrderDetail(ordersVO, odlist);		
//		System.out.println("新增訂單!!");
		
		
		//修改update
//		OrdersVO ordersVO02 = new OrdersVO();
//		
//		ordersVO02.setMemID("M0003");
//		ordersVO02.setBraID("B02");
//		ordersVO02.setNumOfRoom(2);
//		ordersVO02.setOrdType(1);
//		ordersVO02.setNumOfGuest(4);
//		ordersVO02.setAmount(10000);
//		ordersVO02.setBond(1100);
//		ordersVO02.setPayment(1);
//		ordersVO02.setOrdState(0);
//		ordersVO02.setOrdTime(Date.valueOf("2018-12-19"));
//		
//		ordersVO02.setOrdID("20181219-000002");
//		
//		dao.update(ordersVO02);
//		
//		System.out.println("修改成功!!");
		
		//查詢一筆
//		OrdersVO ordersVO03 = dao.findByPrimaryKey("20181219-000002");
//		System.out.println(ordersVO03.getOrdID());
//		System.out.println(ordersVO03.getMemID());
//		System.out.println(ordersVO03.getBraID());
//		System.out.println(ordersVO03.getNumOfRoom());
//		System.out.println(ordersVO03.getOrdType());
//		System.out.println(ordersVO03.getNumOfGuest());
//		System.out.println(ordersVO03.getAmount());
//		System.out.println(ordersVO03.getBond());
//		System.out.println(ordersVO03.getPayment());
//		System.out.println(ordersVO03.getOrdState());
//		System.out.println(ordersVO03.getOrdTime());
//		System.out.println("=====================================");
		
		//查詢全部
//		List<OrdersVO> list = dao.findBybraID("B01");
//		List<OrdersVO> list = dao.findByordType01("B01");
//		List<OrdersVO> list = dao.findByordType2("B01");
//		List<OrdersVO> list = dao.findByordState3("B01");
		
//		List<OrdersVO> list = dao.findOrdersBymemIDordState0("M0002");
		List<OrdersVO> list = dao.findOrdersBymemIDordState123("M0002");
		
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
		
		//查詢單筆訂單的明細
//		Set<OrderDetailVO> set = dao.getOrderDetailByOrders("20181223-000002");
//		
//		for(OrderDetailVO od : set) {
//		System.out.println(od.getOdID());
//		System.out.println(od.getRoomID());
//		System.out.println(od.getOrdID());
//		System.out.println(od.getRtID());
//		System.out.println(od.getCheckIn());
//		System.out.println(od.getCheckOut());
//		System.out.println(od.getRtName());
//		System.out.println(od.getEvaluates());
//		System.out.println(od.getSpecial());
//		System.out.println("---------------------------");
//		}
		
//		List<OrdersCheckInOutVO> list =dao.findCheckInByOrdJoinOD(Date.valueOf("2018-12-29"), "B01");
////		List<OrdersCheckInOutVO> list =dao.findCheckOut_ByOrdJoinOD(Date.valueOf("2018-12-30"), "B01");
//		for(OrdersCheckInOutVO ordcheckIn : list) {
//			System.out.println(ordcheckIn.getOrdID());
//			System.out.println(ordcheckIn.getMemID());
//			System.out.println(ordcheckIn.getBraID());
//			System.out.println(ordcheckIn.getNumOfRoom());
//			System.out.println(ordcheckIn.getOrdType());
//			System.out.println(ordcheckIn.getNumOfGuest());
//			System.out.println(ordcheckIn.getAmount());
//			System.out.println(ordcheckIn.getBond());
//			System.out.println(ordcheckIn.getPayment());
//			System.out.println(ordcheckIn.getOrdState());
//			System.out.println(ordcheckIn.getOrdTime());
//			
//			System.out.println(ordcheckIn.getOdID());
//			System.out.println(ordcheckIn.getRoomID());
//			System.out.println(ordcheckIn.getOrdID());
//			System.out.println(ordcheckIn.getRtID());
//			System.out.println(ordcheckIn.getCheckIn());
//			System.out.println(ordcheckIn.getCheckOut());
//			System.out.println(ordcheckIn.getRtName());
//			System.out.println(ordcheckIn.getEvaluates());
//			System.out.println(ordcheckIn.getSpecial());
//			System.out.println("===================");
//		}
		
//		String ordID = dao.findNewOrderID("M0001");
//		System.out.println("取出的最新一筆訂單編號為:"+ordID);
		
		
	}



}
