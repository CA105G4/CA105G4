package com.android.coupon.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CouponDAO implements CouponDAO_interface{
	
	private static final String GET_DISCOUNT_SQL = "SELECT discount FROM coupon WHERE cpnId = ?";
	private static final String UPDATE_SQL = "UPDATE coupon set quantity = ? where cpnID = ?";
	private static final String GET_ALL_SQL = "SELECT cpnId, discount, quantity, appQuantity from coupon ORDER BY cpnId DESC";
    private static final String GET_ALL_QUANTITY_SQL = "select cpnId, discount, quantity, appQuantity from coupon where quantity != 0";
    
    private static final String GET_COUPON_BY_MEMID_SQL = "select * from coupon inner join couponRecord " + 
    		"on coupon.cpnId = couponRecord.cpnId where memId = ? order by coupon.cpnid desc";
    private static final String GET_COUPON_SQL = "select * from coupon left join couponRecord " + 
    		"on coupon.cpnId = couponRecord.cpnId order by coupon.cpnid desc";
    
	//連線池
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CA105G4DB");
		} catch (NamingException e) {
			e.printStackTrace();
		} 
	}
    
	@Override
	public void update(String cpnId, Integer quantity) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_SQL);
			
			pstmt.setInt(1, quantity);
			pstmt.setString(2, cpnId);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public Integer getCouponDiscount(String cpnId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_DISCOUNT_SQL);
			
			pstmt.setString(1, cpnId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return 0;
	}
	
	@Override
	public List<CouponVO> getAll() {
		List<CouponVO> list = new ArrayList<CouponVO>();
		CouponVO couponVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_SQL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				couponVO = new CouponVO();
				
				couponVO.setCpnID(rs.getString("cpnID"));
				couponVO.setDiscount(rs.getInt("discount"));
				couponVO.setQuantity(rs.getInt("quantity"));
				couponVO.setAppQuantity(rs.getInt("appQuantity"));
				
				list.add(couponVO);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	
		return list;
	}
	
	public List<CouponVO> getQuantityAll(){
		List<CouponVO> list = new ArrayList<CouponVO>();
		CouponVO couponVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_QUANTITY_SQL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				couponVO = new CouponVO();
				
				couponVO.setCpnID(rs.getString("cpnID"));
				couponVO.setDiscount(rs.getInt("discount"));
				couponVO.setQuantity(rs.getInt("quantity"));
				couponVO.setAppQuantity(rs.getInt("appQuantity"));
				
				list.add(couponVO);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	
		return list;
	}
	
	public List<Coupon> getCouponByMemId(String memId){
		List<Coupon> list = new ArrayList<Coupon>();
		Coupon coupon = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_COUPON_BY_MEMID_SQL);
			
			pstmt.setString(1, memId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				coupon = new Coupon();
				
				coupon.setCpnID(rs.getString("cpnID"));
				coupon.setDiscount(rs.getInt("discount"));
				coupon.setQuantity(rs.getInt("quantity"));
				coupon.setAppQuantity(rs.getInt("appQuantity"));
				coupon.setCpnState(rs.getInt("cpnState"));
				
				list.add(coupon);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	
		return list;
	}
	
	public List<Coupon> getCoupon(){
		List<Coupon> list = new ArrayList<Coupon>();
		Coupon coupon = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_COUPON_SQL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				coupon = new Coupon();
				
				coupon.setCpnID(rs.getString("cpnID"));
				coupon.setDiscount(rs.getInt("discount"));
				coupon.setQuantity(rs.getInt("quantity"));
				coupon.setAppQuantity(rs.getInt("appQuantity"));
				coupon.setMemID(rs.getString("memId"));
				coupon.setCpnState(rs.getInt("cpnState"));
				
				list.add(coupon);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	
		return list;
	}
}
