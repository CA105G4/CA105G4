package com.coupon.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tool.BLOBJDBC;

public class CouponJDBCDAO implements CouponDAO_interface{

	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "Test_Project"; 
	private static final String PASSWORD = "123456"; 
	
    private static final String INSERT_SQL = "INSERT INTO coupon VALUES ('C'||LPAD(to_char(cpn_seq.NEXTVAL), 4, '0'), ?, ?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE coupon set appQuantity = ? where cpnID = ?";
    private static final String GET_ALL_SQL = "SELECT * from coupon";
    private static final String GET_ONE_SQL = "SELECT * from coupon where cpnID = ?";
    
	
    static {
    	try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}    	
    }
	
	@Override
	public void insert(CouponVO couponVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_SQL);
			
			pstmt.setInt(1, couponVO.getAppQuantity());
			pstmt.setString(2, couponVO.getCpnID());
			
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
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_SQL);
			
			pstmt.setString(1, cpnID);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				couponVO = new CouponVO();
				
				couponVO.setCpnID(rs.getString("cpnID"));
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

	public static void main(String[] args) {
		CouponJDBCDAO dao = new CouponJDBCDAO();
		
		// 新增
		CouponVO couponVO1 = new CouponVO();
		couponVO1.setCpnPic(new BLOBJDBC().writePicture("images/logo5.png"));
		couponVO1.setDiscount(500);
		couponVO1.setQuantity(20);
		couponVO1.setAppQuantity(20);
		
		dao.insert(couponVO1);
		System.out.println("新增成功");
		System.out.println("------------------");
		
		// 修改
		CouponVO couponVO2 = new CouponVO();
		
		couponVO2.setAppQuantity(10);
		couponVO2.setCpnID("C0002");
		dao.update(couponVO2);
		
		System.out.println("修改成功");
		System.out.println("--------------------");		
		
		// 查詢單筆
		CouponVO couponVO3 = dao.findByPK("C0003");
		
		System.out.println(couponVO3.getCpnID());
		System.out.println(couponVO3.getDiscount());
		System.out.println(couponVO3.getQuantity());
		System.out.println(couponVO3.getAppQuantity());
		
		System.out.println("查詢單筆成功");
		System.out.println("------------------");
		
		// 查詢全部
		List<CouponVO> list = dao.getAll();
		for(CouponVO aCouponVO : list) {
			System.out.println(aCouponVO.getCpnID());
			System.out.println(aCouponVO.getDiscount());
			System.out.println(aCouponVO.getQuantity());
			System.out.println(aCouponVO.getAppQuantity());
			System.out.println();
		}
		System.out.println("查詢全部成功");
		System.out.println("-------------------");
	}
}
