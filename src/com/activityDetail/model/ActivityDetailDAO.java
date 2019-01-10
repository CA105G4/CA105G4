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

	private static final String INSERT_SQL = "INSERT INTO ActivityDetail(actID,rtID,Discount) VALUES(?,?,?)";
	private static final String UPDATE_SQL = "UPDATE ActivityDetail set Discount=? where actID=? and rtID=?";
	private static final String DELETE_SQL = "DELETE FROM ActivityDetail where actID=?";
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

			pstmt.setString(1, activityDetailVO.getActID());
			pstmt.setString(2, activityDetailVO.getRtID());
			pstmt.setFloat(3, activityDetailVO.getDiscount());

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

			pstmt.setFloat(1, activityDetailVO.getDiscount());
			pstmt.setString(2, activityDetailVO.getActID());
			pstmt.setString(3, activityDetailVO.getRtID());

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
	public void delete(String actID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(DELETE_SQL);

			pstmt.setString(1, actID);
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
				
				adVO.setActID(rs.getString("actID"));
				adVO.setRtID(rs.getString("rsID"));
				adVO.setDiscount(rs.getFloat("discount"));
				
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

				adVO.setActID(rs.getString("actID"));
				adVO.setRtID(rs.getString("rtID"));
				adVO.setDiscount(rs.getFloat("Discount"));

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
			pstmt.setString(1, adVO.getActID());
			pstmt.setString(2, adVO.getRtID());
			pstmt.setFloat(3, adVO.getDiscount());
		
			pstmt.executeUpdate();
		
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-ActivityDetailDAO");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
