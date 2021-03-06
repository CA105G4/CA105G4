package com.android.activity.model;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ActivityDAO implements ActivityDAO_interface {
	
private static DataSource ds =null;	
	
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CA105G4DB");
		} catch (NamingException e) {
			e.printStackTrace();
		} 
	}


	private static final String INSERT_SQL = "INSERT INTO Activity (actID,actName,actStart,actEnd)"
																			+ "VALUES('A'||LPAD(to_char(act_seq.nextval),4,'0'),?,?,?)";
	private static final String UPDATE = "UPDATE Activity set actName=?,actStart=?,actEnd=? where actID=?";
	private static final String DELETE="DELETE FROM Activity WHERE actID=?";
	private static final String FIND_BY_PK = "SELECT * FROM Activity where actID=?";
	private static final String FIND_BY_NAME = "SELECT actID,actStart,actEnd FROM Activity where actName=?";
	private static final String FIND_ALL_STMT = "SELECT * FROM Activity ORDER by actID";
	
	/**[Gina]訂單計算總金額-查找促銷日期折扣**/
	private static final String GET_DISCOUNT_SQL = "SELECT activity.actId, activitydetail.rtid, actName, actstart, actend, activitydetail.discount FROM activity " + 
			   "inner join activityDetail on activity.actId = activityDetail.actid " + 
			   "where activityDetail.rtId = ? AND (actstart <= ? AND ? <= actend)";
	/**[Gina]訂單計算總金額-查找促銷日期折扣**/	
	
	@Override
	public void insert(ActivityVO activityVO) {
		Connection con = null;
		PreparedStatement pstmt =null;
		
		try {
			con=ds.getConnection();
			pstmt =con.prepareStatement(INSERT_SQL);
			
			pstmt.setString(1, activityVO.getActName());
			pstmt.setDate(2,activityVO.getActStart());
			pstmt.setDate(3, activityVO.getActEnd());
			
			pstmt.executeUpdate();
			
		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
			
			if(pstmt != null) {
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
		
		
	}

	@Override
	public void update(ActivityVO activityVO) {
			Connection con =null;
			PreparedStatement pstmt =null;
		
			try {
				con=ds.getConnection();
				pstmt=con.prepareStatement(UPDATE);
				
				pstmt.setString(1, activityVO.getActName());
				pstmt.setDate(2,activityVO.getActStart());
				pstmt.setDate(3, activityVO.getActEnd());
				pstmt.setString(4, activityVO.getActID());
				pstmt.executeUpdate();

			} catch (SQLException se) {
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
	public void delete(String actID) {
			Connection con =null;
			PreparedStatement pstmt =null;
			
			try {
				con=ds.getConnection();
				pstmt=con.prepareStatement(DELETE);
				
				pstmt.setString(1, actID);
				
				pstmt.executeUpdate();
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "+se.getMessage());
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
	public ActivityVO findByPK(String actID) {
		ActivityVO actVO =null;
		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		
		
		try {
			con=ds.getConnection();
			pstmt =con.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, actID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				actVO =new ActivityVO();
				actVO.setActID(rs.getString("ACTID"));
				actVO.setActName(rs.getString("ACTName"));
				actVO.setActStart(rs.getDate("actStart"));
				actVO.setActEnd(rs.getDate("actEnd"));
			}
			System.out.println();
			
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
			
		return actVO;
	}

	@Override
	public ActivityVO findByName(String actName) {
		ActivityVO actVO =null;
		Connection con =null;
		PreparedStatement pstmt =null;
		
		ResultSet rs =null;
		
		try {
			con=ds.getConnection();
			pstmt =con.prepareStatement(FIND_BY_NAME);
			pstmt.setString(1,actName);
			rs= pstmt.executeQuery();
			
			while(rs !=null) {
				actVO = new ActivityVO();
				actVO.setActID(rs.getString("actID"));
				actVO.setActName(rs.getString("actName"));
				actVO.setActStart(rs.getDate("actStart"));
				actVO.setActEnd(rs.getDate("actEnd"));
			}
			
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return actVO;
	}

	@Override
	public List<ActivityVO> getAll() {
		List<ActivityVO> list = new ArrayList<ActivityVO>();
		ActivityVO actVO =null;
		
		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		
		try {
			con=ds.getConnection();
			pstmt =con.prepareStatement(FIND_ALL_STMT);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				actVO =new ActivityVO();
				actVO.setActID(rs.getString("actID"));
				actVO.setActName(rs.getString("actName"));
				actVO.setActStart(rs.getDate("actStart"));
				actVO.setActEnd(rs.getDate("actEnd"));
				list.add(actVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}	finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return list;
	}
	
	/**[Gina]訂單計算總金額-查找促銷日期折扣**/
	@Override
	public float getActivityDiscount(String rtId, String date) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_DISCOUNT_SQL);

			pstmt.setString(1, rtId);
			pstmt.setDate(2, Date.valueOf(date));
			pstmt.setDate(3, Date.valueOf(date));

			System.out.println(Date.valueOf(date));

			rs = pstmt.executeQuery();

			if (rs.next()) {
				return rs.getFloat("discount");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return 1;
	}
	
}