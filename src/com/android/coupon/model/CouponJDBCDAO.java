package com.android.coupon.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CouponJDBCDAO implements CouponDAO_interface{

	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "CA105G4"; 
	private static final String PASSWORD = "123456"; 
	
	private static final String GET_DISCOUNT_SQL = "SELECT discount FROM coupon WHERE cpnId = ?";
	private static final String UPDATE_SQL = "UPDATE coupon set quantity = ? where cpnID = ?";
    private static final String GET_ALL_SQL = "SELECT cpnId, discount, quantity, appQuantity from coupon ORDER BY cpnId DESC";
    private static final String GET_ALL_QUANTITY_SQL = "select cpnId, discount, quantity, appQuantity from coupon where quantity != 0";
    
    private static final String GET_COUPON_BY_MEMID_SQL = "select * from coupon inner join couponRecord " + 
    		"on coupon.cpnId = couponRecord.cpnId where memId = ? order by coupon.cpnid desc";
    private static final String GET_COUPON_SQL = "select * from coupon left join couponRecord " + 
    		"on coupon.cpnId = couponRecord.cpnId order by coupon.cpnid desc";
    
    
    static {
    	try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}    	
    }
    
	@Override
	public void update(String cpnId, Integer quantity) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_COUPON_BY_MEMID_SQL);
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
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
	
	public static void main(String[] args) {
		CouponJDBCDAO dao = new CouponJDBCDAO();
		
		// 優惠券被領取 -1
//		dao.update("C0001", 9);
		
//		System.out.println(dao.getCouponDiscount("C0001"));
		
//		// 查詢全部
//		List<CouponVO> list = dao.getAll();
//		for(CouponVO aCouponVO : list) {
//			System.out.println(aCouponVO.getCpnID());
//			System.out.println(aCouponVO.getDiscount());
//			System.out.println(aCouponVO.getQuantity());
//			System.out.println(aCouponVO.getAppQuantity());
//			System.out.println("--------------------------------");
//		}
//		System.out.println("查詢全部成功");
//		System.out.println("-------------------");
		
		//用會員編號查詢
//		String memId = "C0001";
//		List<Coupon> list = dao.getCouponByMemId(memId);
//		for(Coupon aCoupon : list) {
//			System.out.println(aCoupon.getCpnID());
//			System.out.println(aCoupon.getDiscount());
//			System.out.println(aCoupon.getQuantity());
//			System.out.println(aCoupon.getAppQuantity());
//			System.out.println("--------------------------------");
//		}
//		System.out.println("查詢全部成功");
//		System.out.println("-------------------");
		
		List<Coupon> list = dao.getCoupon();
		for(Coupon aCoupon : list) {
			System.out.println(aCoupon.getCpnID());
			System.out.println(aCoupon.getDiscount());
			System.out.println(aCoupon.getQuantity());
			System.out.println(aCoupon.getAppQuantity());
			System.out.println(aCoupon.getMemID());
			System.out.println(aCoupon.getCpnState());
			System.out.println("--------------------------------");
		}
		System.out.println("查詢全部成功");
		System.out.println("-------------------");
		
		// 查詢全部
//		List<CouponVO> list = dao.getQuantityAll();
//		for(CouponVO aCouponVO : list) {
//			System.out.println(aCouponVO.getCpnID());
//			System.out.println(aCouponVO.getDiscount());
//			System.out.println(aCouponVO.getQuantity());
//			System.out.println(aCouponVO.getAppQuantity());
//			System.out.println("--------------------------------");
//		}
//		System.out.println("查詢全部成功");
//		System.out.println("-------------------");
	}
}
