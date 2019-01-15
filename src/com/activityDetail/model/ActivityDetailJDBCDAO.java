package com.activityDetail.model;

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

	private static final String INSERT_SQL ="INSERT INTO ActivityDetail(actID,rtID,discount) VALUES(?,?,?)";
	private static final String UPDATE_SQL="UPDATE ActivityDetail set discount=? where actID=? and rtID=?";
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
			
			pstmt.setString(1,activityDetailVO.getactID());
			pstmt.setString(2, activityDetailVO.getrtID());
			pstmt.setFloat(3,activityDetailVO.getdiscount());
			
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
			
			pstmt.setFloat(1, activityDetailVO.getdiscount());
			pstmt.setString(2,activityDetailVO.getactID());
			pstmt.setString(3, activityDetailVO.getrtID());
			
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
	public void delete(String actID,String rtID) {
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
			
			adVO.setactID(rs.getString("actID"));
			adVO.setrtID(rs.getString("rtID"));
			adVO.setdiscount(rs.getFloat("discount"));
			
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
			
			vo.setactID("A0003");
			vo.setrtID("RT04");
			vo.setdiscount(0.99f);
			dao.insert(vo);
			System.out.println("Insert Succesfully!");
			System.out.println("===========");
			//修改
			
			ActivityDetailVO vo2 =new ActivityDetailVO();
			
			vo2.setdiscount(0.55f);
			vo2.setactID("A0001");
			vo2.setrtID("RT01");
			dao.update(vo2);
			System.out.println("Update Succesfully!");
			System.out.println("===========");
			//刪除
			dao.delete("A0003","RT03");
			System.out.println("Delete Succesfully!");
			System.out.println("===========");
			//找全部
			List<ActivityDetailVO> list =dao.getAll();
			
			for(ActivityDetailVO adVO:list) {
				System.out.print(adVO.getactID()+" ");
				System.out.print(adVO.getrtID()+" ");
				System.out.println(adVO.getdiscount());
			}
			
			
	}

	@Override
	public ActivityDetailVO findByPK(String actID) {
		return null;
	}

	@Override
	public void insert2(ActivityDetailVO adVO, Connection con) {
		// TODO Auto-generated method stub
		
	}
}
