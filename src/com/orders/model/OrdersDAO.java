package com.orders.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.orderDetail.model.OrderDetailDAO;
import com.orderDetail.model.OrderDetailVO;



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

	private static final String UPDATE = "UPDATE ORDERS SET  MEMID=?, BRAID=?, NUMOFROOM=?, ORDTYPE=?, NUMOFGUEST=?, AMOUNT=?, BOND=?, PAYMENT=?, ORDSTATE=?, ORDTIME=? WHERE ORDID=?";
	
	private static final String GET_OrderDetail_ByOrders_STMT = "SELECT * FROM ORDERDETAIL WHERE ORDID=? order by ODID";
	
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
			pstmt.setDate(10, ordersVO.getOrdTime());
			
			pstmt.setString(11, ordersVO.getOrdID());
			
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
				orderDetailVO.setOrdID(rs.getString("RTID"));
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
	public void insertwithOrderDetail(OrdersVO ordersVO, List<OrderDetailVO> odlist) {
		
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
System.out.println("DAO-MemID:"+ordersVO.getMemID());			
			pstmt.setString(1, ordersVO.getMemID());
			
System.out.println("DAO-BraID:"+ordersVO.getBraID());				
			pstmt.setString(2, ordersVO.getBraID());
			
System.out.println("DAO-NumOfRoom:"+ordersVO.getNumOfRoom());				
			pstmt.setInt(3, ordersVO.getNumOfRoom());
			
System.out.println("DAO-OrdType:"+ordersVO.getOrdType());				
			pstmt.setInt(4, ordersVO.getOrdType());
			
System.out.println("DAO-NumOfGuest:"+ordersVO.getNumOfGuest());			
			pstmt.setInt(5, ordersVO.getNumOfGuest());
			
System.out.println("DAO-Amount:"+ordersVO.getAmount());		
			pstmt.setInt(6, ordersVO.getAmount());
			
System.out.println("DAO-Bond:"+ordersVO.getBond());			
			pstmt.setInt(7, ordersVO.getBond());
			
System.out.println("DAO-Payment:"+ordersVO.getPayment());		
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
				oddao.insertwithOrders(orderDetailVO, con);	//請明細的dao呼叫同時新增明細的方法
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
	
}
