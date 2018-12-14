package com.couponRecord.model;

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
	
    private static final String INSERT_SQL = "INSERT INTO couponRecord (memID, cpnID) VALUES (?, ?)";
    private static final String UPDATE_SQL = "UPDATE couponRecord set cpnState = 1 where memID = ? and cpnID = ?";
    private static final String FIND_MEMBER_RECORD_SQL = "SELECT * from couponRecord where memID = ?";
    
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
	public void update(String memID, String cpnID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_SQL);
			
			pstmt.setString(1, memID);
			pstmt.setString(2, cpnID);
			
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
	public List<CouponRecordVO> findByMemID(String memID) {
		List<CouponRecordVO> list = new ArrayList<CouponRecordVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_MEMBER_RECORD_SQL);

			pstmt.setString(1, memID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CouponRecordVO couponRecordVO = new CouponRecordVO();
				
				couponRecordVO.setMemID(rs.getString("memID"));
				couponRecordVO.setCpnID(rs.getString("cpnID"));
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
		
		// 找出會員全部的優惠券
		String memID = "M0001";
		List<CouponRecordVO> list = dao.findByMemID(memID);
		System.out.println("會員編號 " + memID + " 所有的優惠券：");
		for(CouponRecordVO aCouponRecord : list) {
			System.out.print(aCouponRecord.getCpnID() + ", ");
			System.out.println((aCouponRecord.getCpnState() == 1) ? "使用" : "未使用");
		}
		System.out.println("搜尋成功");
		System.out.println("------------------");
	}
}
