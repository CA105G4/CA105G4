package com.orderDetail.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.orders.model.OrdersVO;
import com.roomType.model.RoomTypeDAO;

import java.sql.*;
import java.sql.Date;

public class OrderDetailDAO implements OrderDetailDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/CA105G4DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, orderDetailVO.getOrdID());
			pstmt.setString(2, orderDetailVO.getRtID());
			pstmt.setDate(3, orderDetailVO.getCheckIn());
			pstmt.setDate(4, orderDetailVO.getCheckOut());
			pstmt.setInt(5, orderDetailVO.getSpecial());
			
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
	public void update(OrderDetailVO orderDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);	//DELETE FROM OrderDetail where ODID = ?
			
			pstmt.setInt(1, odID);
			System.out.println(odID);
			
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
	public OrderDetailVO findByPrimaryKey(Integer odID) {
		OrderDetailVO orderDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
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
			con = ds.getConnection();
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
			con = ds.getConnection();
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
			RoomTypeDAO rtdao = new RoomTypeDAO();
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
	
}
