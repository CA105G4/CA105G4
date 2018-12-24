package com.coupon.model;

import java.sql.Connection;
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

private static DataSource ds =null;	
	
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CA105G4DB");
		} catch (NamingException e) {
			e.printStackTrace();
		} 
	}
	
    private static final String INSERT_SQL = "INSERT INTO Coupon(cpnID,cpnPic,discount,quantity,appquantity) VALUES ('C'||LPAD(to_char(cpn_seq.NEXTVAL), 4, '0'), ?, ?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE Coupon set cpnPic=? discount=? quantity=? appQuantity = ? where cpnID = ?";
    private static final String GET_ALL_SQL = "SELECT * from coupon";
    private static final String GET_ONE_SQL = "SELECT * from coupon where cpnID = ?";
    
	
	
	@Override
	public void insert(CouponVO couponVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(INSERT_SQL);
			
			pstmt.setBytes(1, couponVO.getCpnPic());
			pstmt.setInt(2, couponVO.getDiscount());
			pstmt.setInt(3, couponVO.getQuantity());
			pstmt.setInt(4, couponVO.getAppQuantity());
			
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
	public void update(CouponVO couponVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_SQL);
			
			pstmt.setBytes(1, couponVO.getCpnPic());
			pstmt.setInt(2, couponVO.getDiscount());
			pstmt.setInt(3, couponVO.getQuantity());
			pstmt.setInt(4, couponVO.getAppQuantity());
			pstmt.setString(5, couponVO.getCpnID());
			
			
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
	public CouponVO findByPK(String cpnID) {
		CouponVO couponVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_SQL);
			
			pstmt.setString(1, cpnID);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				couponVO = new CouponVO();
				
				couponVO.setCpnID(rs.getString("cpnID"));
				couponVO.setCpnPic(rs.getBytes("cpnPic"));
				couponVO.setDiscount(rs.getInt("discount"));
				couponVO.setQuantity(rs.getInt("quantity"));
				couponVO.setAppQuantity(rs.getInt("appQuantity"));
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
		return couponVO;
	}

	@Override
	public List<CouponVO> getAll() {
		List<CouponVO> list = new ArrayList<CouponVO>();
		CouponVO couponVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_SQL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				couponVO = new CouponVO();
								
				couponVO.setCpnID(rs.getString("cpnID"));
				couponVO.setCpnPic(rs.getBytes("cpnPic"));
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

	}
