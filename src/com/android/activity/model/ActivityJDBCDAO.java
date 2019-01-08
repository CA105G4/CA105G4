package com.android.activity.model;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ActivityJDBCDAO implements ActivityDAO_interface {

	private static final String DRIVER= "oracle.jdbc.driver.OracleDriver";
	 
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "CA105G4";
	private static final String PWD = "123456";

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

	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		}

	}

	@Override
	public void insert(ActivityVO activityVO) {
		Connection con = null;
		PreparedStatement pstmt =null;
		
		try {
			
			con = DriverManager.getConnection(URL, USER, PWD);
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
				con=DriverManager.getConnection(URL,USER, PWD);
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
				con=DriverManager.getConnection(URL, USER, PWD);
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
			con=DriverManager.getConnection(URL,USER,PWD);
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
			con=DriverManager.getConnection(URL, USER, PWD);
			pstmt =con.prepareStatement(FIND_BY_NAME);
			pstmt.setString(1,actName);
			rs= pstmt.executeQuery();
			
			while(rs !=null) {
				actVO = new ActivityVO();
				actVO.setActID(rs.getString("actID"));
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
			
			con=DriverManager.getConnection(URL, USER, PWD);
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
			con = DriverManager.getConnection(URL, USER, PWD);
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

	
	public static void main(String[] args) {
		ActivityJDBCDAO dao= new ActivityJDBCDAO();
		
//		//新增
//		ActivityVO actVO =new ActivityVO();
//		Date d1 =  Date.valueOf("2018-12-25");
//		Date d2 =  Date.valueOf("2018-12-31");
//		actVO.setActName("聖誕老公公");
//		actVO.setActStart(d1);
//		actVO.setActEnd(d2);
//		
//		
//		dao.insert(actVO);
//		System.out.println("新增成功");
//		
//		//修改
//		ActivityVO actVO2 =new ActivityVO();
//		Date d3 =  Date.valueOf("2019-02-25");
//		Date d4 =  Date.valueOf("2019-02-26");
//		actVO2.setActID("A0003"); 
//		actVO2.setActName("測試用節慶");
//		actVO2.setActStart(d3);
//		actVO2.setActEnd(d4);
//		dao.update(actVO2);
//		System.out.println("修改成功");
//		
//		//透過主鍵尋找全部
//		ActivityVO actVO3 =dao.findByPK("A0003");
//		System.out.print(actVO3.getActID()+" ");
//		System.out.print(actVO3.getActName()+" ");
//		System.out.print(actVO3.getActStart()+" ");
//		System.out.println(actVO3.getActEnd());
		
		
		
		
		//找全部
		
		List<ActivityVO>list =dao.getAll();
		
		for(ActivityVO actVO4:list) {
			System.out.print(actVO4.getActID()+" ");
			System.out.print(actVO4.getActName()+" ");
			System.out.print(actVO4.getActStart()+" ");
			System.out.print(actVO4.getActEnd()+" ");
			System.out.println();
		}
		
		//刪除
//		dao.delete("A0021");
		
		
		
	}



}