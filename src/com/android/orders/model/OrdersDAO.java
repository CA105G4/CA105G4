package com.android.orders.model;

import java.util.*;
import java.sql.*;
import java.sql.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.android.orderDetail.model.OrderDetailDAO;
import com.android.orderDetail.model.OrderDetailVO;



public class OrdersDAO implements OrdersDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/CA105G4DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}

	private static final String INSERT_STMT = "INSERT INTO ORDERS(ORDID, MEMID, BRAID, NUMOFROOM, ORDTYPE, NUMOFGUEST, AMOUNT, BOND, PAYMENT) VALUES (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(ord_seq.NEXTVAL), 6, '0'), ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM ORDERS";
	private static final String GET_ONE_STMT = "SELECT * FROM ORDERS WHERE ordID = ?";

	private static final String UPDATE = "UPDATE ORDERS SET  MEMID=?, BRAID=?, NUMOFROOM=?, ORDTYPE=?, NUMOFGUEST=?, AMOUNT=?, BOND=?, PAYMENT=?, ORDSTATE=? WHERE ORDID=?";
	
	//查詢單筆訂單的明細
	private static final String GET_OrderDetail_ByOrders_STMT = "SELECT * FROM ORDERDETAIL WHERE ORDID=? order by ODID";
	
	//CHECKIN
	private static final String GET_CHECKIN_ByOrdJoinOD_STMT = "SELECT * FROM ORDERS LEFT JOIN ORDERDETAIL ON ORDERS.ORDID=ORDERDETAIL.ORDID WHERE CHECKIN=? AND BRAID=? ORDER BY ORDERS.ORDID";
	
	//CHECKOUT
	private static final String GET_CHECKOUT_ByOrdJoinOD_STMT = "SELECT * FROM ORDERS LEFT JOIN ORDERDETAIL ON ORDERS.ORDID=ORDERDETAIL.ORDID WHERE CHECKOUT=? AND BRAID=? ORDER BY ORDERS.ORDID";
	//[CHECKIN]更改訂單狀態
	private static final String UPDATE_ordState_ByordID = "UPDATE ORDERS SET ORDSTATE=? WHERE ORDID=?";
	
	//找出要 checkin 的會員 => checin時間 + 會員編號
	private static final String GET_CHECKIN_BY_MEMID_SQL = "SELECT * FROM ORDERS LEFT JOIN ORDERDETAIL ON ORDERS.ORDID = ORDERDETAIL.ORDID WHERE MEMID = ? AND CHECKIN = ?";
	
	//修改訂單總金額 => 用訂單明細查詢
	private static final String UPDATE_ORDER_AMOUNT_SQL = "UPDATE ORDERS SET AMOUNT = ? WHERE ORDID = ?";
	
	@Override
	public void insert(OrdersVO ordersVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);	
			//UPDATE ORDERS SET  MEMID=?, BRAID=?, NUMOFROOM=?, ORDTYPE=?, NUMOFGUEST=?, AMOUNT=?, BOND=?, PAYMENT=? WHERE ORDID=?
			
			pstmt.setString(1, ordersVO.getMemID());
			pstmt.setString(2, ordersVO.getBraID());
			pstmt.setInt(3, ordersVO.getNumOfRoom());
			pstmt.setInt(4, ordersVO.getOrdType());
			pstmt.setInt(5, ordersVO.getNumOfGuest());
			pstmt.setInt(6, ordersVO.getAmount());
			pstmt.setInt(7, ordersVO.getBond());
			pstmt.setInt(8, ordersVO.getPayment());
			pstmt.setInt(9, ordersVO.getOrdState());
			
			pstmt.setString(10, ordersVO.getOrdID());
			
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
	public OrdersVO findByPrimaryKey(String ordID) {
		OrdersVO ordersVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
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
			con = ds.getConnection();
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
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_OrderDetail_ByOrders_STMT);	//SELECT * FROM ORDERDETAIL WHERE ORDID=? order by ODID
			pstmt.setString(1, ordID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				orderDetailVO = new OrderDetailVO();
				orderDetailVO.setOdID(rs.getInt("ODID"));
				orderDetailVO.setRoomID(rs.getString("ROOMID"));
				orderDetailVO.setOrdID(rs.getString("ORDID"));
				orderDetailVO.setRtID(rs.getString("RTID"));
				orderDetailVO.setCheckIn(rs.getDate("CHECKIN"));
				orderDetailVO.setCheckOut(rs.getDate("CHECKOUT"));
				orderDetailVO.setRtName(rs.getString("RTNAME"));
				orderDetailVO.setEvaluates(rs.getDouble("EVALUATES"));
				orderDetailVO.setSpecial(rs.getInt("SPECIAL"));
				set.add(orderDetailVO);
			}
			
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
			con = ds.getConnection();
			
			/****關閉autoCommit連線****/
			con.setAutoCommit(false);
			
			/****新增訂單先****/
			String cols[] = {"ORDID"};	
			pstmt = con.prepareStatement(INSERT_STMT, cols);  //綁定自增主鍵
			/*INSERT INTO ORDERS(ORDID, MEMID, BRAID, NUMOFROOM, ORDTYPE, NUMOFGUEST, AMOUNT, BOND, PAYMENT) 
			 * VALUES (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(ord_seq.NEXTVAL), 6, '0'), ?, ?, ?, ?, ?, ?, ?, ?)*/
System.out.println("oDAO-MemID:"+ordersVO.getMemID());			
			pstmt.setString(1, ordersVO.getMemID());
			
System.out.println("oDAO-BraID:"+ordersVO.getBraID());				
			pstmt.setString(2, ordersVO.getBraID());
			
System.out.println("oDAO-NumOfRoom:"+ordersVO.getNumOfRoom());				
			pstmt.setInt(3, ordersVO.getNumOfRoom());
			
System.out.println("oDAO-OrdType:"+ordersVO.getOrdType());				
			pstmt.setInt(4, ordersVO.getOrdType());
			
System.out.println("oDAO-NumOfGuest:"+ordersVO.getNumOfGuest());			
			pstmt.setInt(5, ordersVO.getNumOfGuest());
			
System.out.println("oDAO-Amount:"+ordersVO.getAmount());		
			pstmt.setInt(6, ordersVO.getAmount());
			
System.out.println("oDAO-Bond:"+ordersVO.getBond());			
			pstmt.setInt(7, ordersVO.getBond());
			
System.out.println("oDAO-Payment:"+ordersVO.getPayment());		
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
			OrderDetailDAO oddao = new OrderDetailDAO();
			System.out.println("list.size()-A="+ odlist.size() + "筆明細同時被新增");	//有幾個明細就跑幾次
			
			for(OrderDetailVO orderDetailVO : odlist) {
				orderDetailVO.setOrdID(now_ordID);	//將取到的主鍵set進去orderDetailVO的ordID中
				oddao.insertwithOrders(orderDetailVO, con, rtIDandNumMap);	//請明細的dao呼叫同時新增明細的方法
			}
			
			con.commit();
			
			System.out.println("新增訂單編號 " + now_ordID + " 時，共有明細 " + odlist.size() + " 筆同時被新增");
			
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
		System.out.println("dao:"+checkIn);
		System.out.println("dao:"+braID);
		List<OrdersCheckInOutVO> list = new ArrayList<OrdersCheckInOutVO>();
		OrdersCheckInOutVO ordCheckInOutVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_CHECKIN_ByOrdJoinOD_STMT);	
			//SELECT * FROM ORDERS LEFT JOIN ORDERDETAIL ON ORDERS.ORDID=ORDERDETAIL.ORDID WHERE CHECKIN=? AND BRAID=? ORDER BY ORDERS.ORDID
			
			pstmt.setDate(1, checkIn);
			pstmt.setString(2, braID);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				//訂單
				ordCheckInOutVO = new OrdersCheckInOutVO();
				ordCheckInOutVO.setOrdID(rs.getString("ORDID"));
				System.out.println(rs.getString("ORDID"));
				ordCheckInOutVO.setMemID(rs.getString("MEMID"));
				System.out.println(rs.getString("MEMID"));
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
				System.out.println(rs.getString("ROOMID"));
				ordCheckInOutVO.setRtID(rs.getString("RTID"));
				ordCheckInOutVO.setCheckIn(rs.getDate("CHECKIN"));
				ordCheckInOutVO.setCheckOut(rs.getDate("CHECKOUT"));
				ordCheckInOutVO.setRtName(rs.getString("RTNAME"));
				ordCheckInOutVO.setEvaluates(rs.getDouble("EVALUATES"));
				ordCheckInOutVO.setSpecial(rs.getInt("SPECIAL"));
				
				list.add(ordCheckInOutVO);	// Store the row in the list
			}
//			System.out.println("list.toString():"+list.toString());
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

			con = ds.getConnection();
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
				ordCheckInOutVO.setRtID(rs.getString("RTID"));
				ordCheckInOutVO.setCheckIn(rs.getDate("CHECKIN"));
				ordCheckInOutVO.setCheckOut(rs.getDate("CHECKOUT"));
				ordCheckInOutVO.setRtName(rs.getString("RTNAME"));
				ordCheckInOutVO.setEvaluates(rs.getDouble("EVALUATES"));
				ordCheckInOutVO.setSpecial(rs.getInt("SPECIAL"));
				
				list.add(ordCheckInOutVO);	// Store the row in the list
			}
			System.out.println(list.toString());
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
System.out.println("訂單收到ordState:"+ordState);
System.out.println("訂單收到ordID:"+ordID);
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_ordState_ByordID);	
			//UPDATE ORDERS SET ORDSTATE=? WHERE ORDID=?				
			
			pstmt.setInt(1, ordState);
			pstmt.setString(2, ordID);
			
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
	public boolean updateOrdersAmount(Integer amount, String ordId) {     //修改總金額
		
		boolean success = false;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_ORDER_AMOUNT_SQL);	
			
			pstmt.setInt(1, amount);
			pstmt.setString(2, ordId);
			
			int i = pstmt.executeUpdate();
			if(i == 0)
				success = false;
			else
				success = true;

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
		return success;
	}
	
	public OrdersCheckInOutVO findCheckinMember(String memId, Date checkin) {   //找出checkin 的會員
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		OrdersCheckInOutVO ordCheckInOutVO = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_CHECKIN_BY_MEMID_SQL);	
			
			pstmt.setString(1, memId);
			pstmt.setDate(2, checkin);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
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
				ordCheckInOutVO.setRtID(rs.getString("RTID"));
				ordCheckInOutVO.setCheckIn(rs.getDate("CHECKIN"));
				ordCheckInOutVO.setCheckOut(rs.getDate("CHECKOUT"));
				ordCheckInOutVO.setRtName(rs.getString("RTNAME"));
				ordCheckInOutVO.setEvaluates(rs.getDouble("EVALUATES"));
				ordCheckInOutVO.setSpecial(rs.getInt("SPECIAL"));
			}
			
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
			// Clean up JDBC resources
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
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
		return ordCheckInOutVO;
	}
}
