package com.android.activityDetail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActivityDetailJDBCDAO implements ActivityDetailDAO_interface{
	
	private static final String DRIVER="oracle.jdbc.driver.OracleDriver";
	private static final String URL="jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER="CA105G4";
	private static final String PASSWORD="123456";

	private static final String INSERT_SQL ="INSERT INTO ActivityDetail(actID,rtID,Discount) VALUES(?,?,?)";
	private static final String UPDATE_SQL="UPDATE ActivityDetail set Discount=? where actID=? and rtID=?";
	private static final String DELETE_SQL="DELETE FROM ActivityDetail where actID=?";
	private static final String GET_ALL_SQL="SELECT * FROM ActivityDetail ORDER BY actID";

	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void insert(ActivityDetailVO activityDetailVO) {
		Connection con =null;
		PreparedStatement pstmt =null;
		
		try {
			con =DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt=con.prepareStatement(INSERT_SQL);
			
			pstmt.setString(1,activityDetailVO.getActID());
			pstmt.setString(2, activityDetailVO.getRtID());
			pstmt.setFloat(3,activityDetailVO.getDiscount());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
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
	public void update(ActivityDetailVO activityDetailVO) {
		Connection con =null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_SQL);
			
			pstmt.setFloat(1, activityDetailVO.getDiscount());
			pstmt.setString(2,activityDetailVO.getActID());
			pstmt.setString(3, activityDetailVO.getRtID());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
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
	public void delete(String actID) {
		Connection con = null;
		PreparedStatement pstmt =null;
		
		try {
			con =DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt =con.prepareStatement(DELETE_SQL);
			
			pstmt.setString(1, actID);
			pstmt.executeUpdate();
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
		
	}
	
	@Override
	public List<ActivityDetailVO> getAll() {
		List<ActivityDetailVO>list =new ArrayList<>();
		ActivityDetailVO adVO =null;
		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		
		try {
			con =DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_SQL);
			rs= pstmt.executeQuery();
			
			while(rs.next()) {
			adVO =new ActivityDetailVO();
			
			adVO.setActID(rs.getString("actID"));
			adVO.setRtID(rs.getString("rtID"));
			adVO.setDiscount(rs.getFloat("Discount"));
			
			list.add(adVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!= null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(con!= null) {
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
			ActivityDetailJDBCDAO  dao =new ActivityDetailJDBCDAO();
			
			//新增
			ActivityDetailVO vo =new ActivityDetailVO();
			
			vo.setActID("A0003");
			vo.setRtID("RT04");
			vo.setDiscount(0.99f);
			dao.insert(vo);
			System.out.println("Insert Succesfully!");
			System.out.println("===========");
			//修改
			
			ActivityDetailVO vo2 =new ActivityDetailVO();
			
			vo2.setDiscount(0.55f);
			vo2.setActID("A0001");
			vo2.setRtID("RT01");
			dao.update(vo2);
			System.out.println("Update Succesfully!");
			System.out.println("===========");
			//刪除
			dao.delete("A0003");
			System.out.println("Delete Succesfully!");
			System.out.println("===========");
			//找全部
			List<ActivityDetailVO> list =dao.getAll();
			
			for(ActivityDetailVO adVO:list) {
				System.out.print(adVO.getActID()+" ");
				System.out.print(adVO.getRtID()+" ");
				System.out.println(adVO.getDiscount());
			}
			
			
	}
}
