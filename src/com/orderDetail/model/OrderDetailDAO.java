package com.orderDetail.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

import com.roomType.model.RoomTypeDAO;


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
	
	//[Gina]{CHECKIN} 訂單明細加入房間編號
	private static final String UPDATE_roomID_ByodID ="UPDATE OrderDetail SET roomID=? WHERE ODID = ?";
	
	//[Gina]{加床}再訂單明細中改加床
	private static final String UPDATE_SPECIAL_ByodID ="UPDATE OrderDetail SET SPECIAL=? WHERE ODID = ?";
	
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
				orderDetailVO.setRtID(rs.getString("RTID"));
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
				orderDetailVO.setRtID(rs.getString("RTID"));
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
				orderDetailVO.setRtID(rs.getString("RTID"));
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
	public void insertwithOrders(OrderDetailVO orderDetailVO, Connection con, Map<String,Integer> rtIDandNumMap) {
		
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
			rtdao.updateRoomBalance(orderDetailVO.getRtID(), orderDetailVO.getCheckIn(), orderDetailVO.getCheckOut(), con, rtIDandNumMap);
			System.out.println("od房型編號" + orderDetailVO.getRtID());
			System.out.println("od入住日期" + orderDetailVO.getCheckIn());
			System.out.println("od退房日期" + orderDetailVO.getCheckOut());
			
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
	
	@Override
	public void updateRoomID(String roomID, Integer odID) {
System.out.println("訂單明細收到roomID:"+roomID);
System.out.println("訂單明細收到odID:"+odID);
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_roomID_ByodID);	
			//UPDATE OrderDetail SET roomID=? WHERE ODID = ?
			
			pstmt.setString(1, roomID);
			pstmt.setInt(2, odID);
			
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
	public void updateSpecial(Integer special, Integer odID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_SPECIAL_ByodID);	
			//UPDATE OrderDetail SET SPECIAL=? WHERE ODID = ?
			
			pstmt.setInt(1, special);
			pstmt.setInt(2, odID);
			
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
	
}
