package com.activity.model;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.activityDetail.model.ActivityDetailDAO;
import com.activityDetail.model.ActivityDetailVO;

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
	
	private static final String GET_ActivityDetails_BY_actID="SELECT activitydetail.actID,activitydetail.rtID, roomtype.rtname,activitydetail.discount \r\n" + 
			"FROM (activitydetail )LEFT JOIN roomtype \r\n" + 
			"ON (activitydetail.rtID =roomtype.rtid) \r\n" + 
			"WHERE activitydetail.actID=? order by actID";
	
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
	
	@Override
	public void insertWithDetail(ActivityVO actVO, List<ActivityDetailVO> list) {
			Connection con =null;
			PreparedStatement pstmt =null;
			
			try {
				con =ds.getConnection();
				// 1●設定於 pstm.executeUpdate()之前
				con.setAutoCommit(false);
				
				//先新增促銷活動編號
				String cols[] = {"actID"};
				pstmt=con.prepareStatement(INSERT_SQL, cols);
				pstmt.setString(1,actVO.getActName());
				pstmt.setDate(2, actVO.getActStart());
				pstmt.setDate(3, actVO.getActEnd());
				pstmt.executeUpdate();
				//掘取對應的自增主鍵值
				String next_actID =null;
				ResultSet rs =pstmt.getGeneratedKeys();
				if(rs.next()) {
					next_actID =rs.getString(1);
					System.out.println("自增主鍵值= " + next_actID +"(剛新增成功的活動編號)");
				}else {
					System.out.println("未取得自增主鍵值");
				}
				rs.close();
				ActivityDetailDAO adDAO =new ActivityDetailDAO(); 
				System.out.println(list.size());
				for(ActivityDetailVO adVO:list) {
					adVO.setActID(next_actID);
					adDAO.insert2(adVO, con);
				}
				// 2●設定於 pstm.executeUpdate()之後
				con.commit();
				con.setAutoCommit(true);
				
				
			}catch (SQLException se) {
				if (con != null) {
					try {
						// 3●設定於當有exception發生時之catch區塊內
						System.err.print("Transaction is being ");
						System.err.println("rolled back-由-activity");
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
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
			
			
		
		
	}

	public Set<ActivityDetailVO>getDetailByactID(String actID){
		Set<ActivityDetailVO>set =new LinkedHashSet<ActivityDetailVO>();
		ActivityDetailVO adVO =null;
		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		
		try {
			con =ds.getConnection();
			pstmt =con.prepareStatement(GET_ActivityDetails_BY_actID);
			pstmt.setString(1, actID);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				adVO = new ActivityDetailVO();
				
				adVO.setActID(rs.getString("actID"));
				adVO.setRtID(rs.getString("rtID"));
				adVO.setDiscount(rs.getFloat("discount"));
				
				set.add(adVO);
				
				}
			}catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
		return set;
	}
}