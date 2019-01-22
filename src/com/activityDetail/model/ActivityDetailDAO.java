package com.activityDetail.model;

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

public class ActivityDetailDAO implements ActivityDetailDAO_interface {

	private static final String INSERT_SQL = "INSERT INTO ActivityDetail(actID,rtID,discount) VALUES(?,?,?)";
	private static final String UPDATE_SQL = "UPDATE ActivityDetail set discount=? where actID=? and rtID=?";
	private static final String DELETE_SQL = "DELETE FROM ActivityDetail where actID=? and rtID=?";
	private static final String FIND_BY_PK ="SELECT * FROM ActivityDetail WHERE actID=?";
	private static final String GET_ALL_SQL = "SELECT * FROM ActivityDetail ORDER BY actID";

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
	public void insert(ActivityDetailVO activityDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(INSERT_SQL);

			pstmt.setString(1, activityDetailVO.getactID());
			pstmt.setString(2, activityDetailVO.getrtID());
			pstmt.setFloat(3, activityDetailVO.getdiscount());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
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
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_SQL);

			pstmt.setFloat(1, activityDetailVO.getdiscount());
			pstmt.setString(2, activityDetailVO.getactID());
			pstmt.setString(3, activityDetailVO.getrtID());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
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
		PreparedStatement pstmt = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(DELETE_SQL);

			pstmt.setString(1, actID);
			pstmt.setString(2, rtID);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

	public ActivityDetailVO findByPK(String actID) {
		ActivityDetailVO adVO = null;
		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		
		try {
			con =ds.getConnection();
			pstmt =con.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, actID);
			rs =pstmt.executeQuery();
			
			while(rs.next()) {
				adVO =new ActivityDetailVO();
				
				adVO.setactID(rs.getString("actID"));
				adVO.setrtID(rs.getString("rtID"));
				adVO.setdiscount(rs.getFloat("discount"));
				
			}
			
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
			
			if(rs!=null) {
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
			
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		
		}
		
		
		return adVO;
	}
	
	
	@Override
	public List<ActivityDetailVO> getAll() {
		List<ActivityDetailVO> list = new ArrayList<>();
		ActivityDetailVO adVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_SQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				adVO = new ActivityDetailVO();

				adVO.setactID(rs.getString("actID"));
				adVO.setrtID(rs.getString("rtID"));
				adVO.setdiscount(rs.getFloat("discount"));

				list.add(adVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
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
	public void insert2(ActivityDetailVO adVO, Connection con) {
		
		PreparedStatement pstmt =null;
		
		try {
			pstmt =con.prepareStatement(INSERT_SQL);
			pstmt.setString(1, adVO.getactID());
			pstmt.setString(2, adVO.getrtID());
			pstmt.setFloat(3, adVO.getdiscount());
		
			pstmt.executeUpdate();
		
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-ActivityDetailDAO");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
