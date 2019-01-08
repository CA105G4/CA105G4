package com.android.couponRecord.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CouponRecordJDBCDAO implements CouponRecord_interface{

	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "CA105G4"; 
	private static final String PASSWORD = "123456"; 
	
	private static final String SELECT_SQL = "SELECT * FROM couponRecord WHERE memId = ? and cpnId = ?";
    private static final String INSERT_SQL = "INSERT INTO couponRecord (memID, cpnID) VALUES (?, ?)";
    private static final String UPDATE_SQL = "UPDATE couponRecord set cpnState = 1 where memID = ? and cpnID = ?";
    private static final String FIND_MEMBER_RECORD_ALL_SQL = "SELECT * from couponRecord where memID = ? ORDER BY cpnId DESC";
    
    static {
    	try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}    	
    }

	@Override
	public void insert(CouponRecordVO couponRecordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_SQL);
			
			pstmt.setString(1, couponRecordVO.getMemID());
			pstmt.setString(2, couponRecordVO.getCpnID());
			
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
	public void update(String memId, String cpnId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_SQL);
			
			pstmt.setString(1, memId);
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
	public List<CouponRecordVO> findByMemId(String memId) {
		List<CouponRecordVO> list = new ArrayList<CouponRecordVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_MEMBER_RECORD_ALL_SQL);

			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CouponRecordVO couponRecordVO = new CouponRecordVO();
				
				couponRecordVO.setMemID(rs.getString("memId"));
				couponRecordVO.setCpnID(rs.getString("cpnId"));
				couponRecordVO.setCpnState(rs.getInt("cpnState"));
				
				list.add(couponRecordVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
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
	
	@Override
	public boolean findByMemIdCpnId(String memId, String cpnId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(SELECT_SQL);
			
			pstmt.setString(1, memId);
			pstmt.setString(2, cpnId);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return true;
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
		return false;
	}

	public static void main(String[] args) {
		CouponRecordJDBCDAO dao = new CouponRecordJDBCDAO();
		
		// 新增
//		CouponRecordVO couponRecordVO1 = new CouponRecordVO();
//		couponRecordVO1.setMemID("M0002");
//		couponRecordVO1.setCpnID("C0003");
//		dao.insert(couponRecordVO1);
//		System.out.println("新增成功");
//		System.out.println("------------------");
		
		// 修改優惠券使用狀態
//		dao.update("M0002", "C0002");
//		System.out.println("修改成功");
//		System.out.println("------------------");
		
//		System.out.println(dao.findByMemIdCpnId("M0001", "C0001"));
//		System.out.println(dao.findByMemIdCpnId("M0003", "C0002"));
		
		// 找出會員全部的優惠券
//		String memId = "M0001";
//		List<CouponRecordVO> list = dao.findByMemId("M0001");
//		System.out.println("會員編號 " + memId + " 所有的優惠券：");
//		for(CouponRecordVO aCouponRecord : list) {
//			System.out.print(aCouponRecord.getCpnID() + ", ");
//			System.out.println((aCouponRecord.getCpnState() == 1) ? "使用" : "未使用");
//		}
//		System.out.println("搜尋成功");
//		System.out.println("------------------");
	}
}
