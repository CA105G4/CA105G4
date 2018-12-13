package com.authorityRecord.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


	public class AuthorityRecordJDBCDAO implements AuthorityRecordDAO_interfacr{

		private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
		private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
		private static final String USER = "CA105G4"; 
		private static final String PASSWORD = "123456"; 
		
		private static final String INSERT_SQL = 
	    		"INSERT INTO AuthorityRecord (authid, empid)VALUES(?,?)";
	    private static final String UPDATE_SQL = "UPDATE AuthorityRecord set authid = ? where empID = ?";
	   //以員工編號 還是權限編號 修改????
	    private static final String GET_ALL_SQL = "SELECT * from AuthorityRecord";
	    
		
		
	    static {
	    	try {
				Class.forName(DRIVER);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}    	
	    }
		
	    @Override
		public void insert(AuthorityRecordVO authorityRecordVO) {
			
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = DriverManager.getConnection(URL, USER, PASSWORD);
				pstmt = con.prepareStatement(INSERT_SQL);
				
				pstmt.setInt(1, authorityRecordVO.getAuthID());
				pstmt.setString(2, authorityRecordVO.getEmpID());
				
				
				
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
		public void update(AuthorityRecordVO authorityRecordVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = DriverManager.getConnection(URL, USER, PASSWORD);
				pstmt = con.prepareStatement(UPDATE_SQL);
				
				pstmt.setInt(1, authorityRecordVO.getAuthID());
				pstmt.setString(1, authorityRecordVO.getEmpID());
				
				
				
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
		public List<AuthorityRecordVO> getAll() {
			List<AuthorityRecordVO> list = new ArrayList<AuthorityRecordVO>();
			AuthorityRecordVO authorityRecordVO = null;
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				con = DriverManager.getConnection(URL, USER, PASSWORD);
				pstmt = con.prepareStatement(GET_ALL_SQL);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					authorityRecordVO = new AuthorityRecordVO();
					
					authorityRecordVO.setAuthID(rs.getInt("authID"));
					authorityRecordVO.setEmpID(rs.getString("empID"));
					
					
					list.add(authorityRecordVO);
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
			AuthorityRecordJDBCDAO dao = new AuthorityRecordJDBCDAO();
		//new
//			AuthorityRecordVO authorityRecordVO= new AuthorityRecordVO();
//				authorityRecordVO.setAuthID(1004);
//				authorityRecordVO.setEmpID("E0005");
//				dao.insert(authorityRecordVO);
//				System.out.println("新增成功!!");
		
			//修改	
//				AuthorityRecordVO authorityRecordVO02= new AuthorityRecordVO();	
//				authorityRecordVO02.setAuthID(1001);
//				authorityRecordVO02.setEmpID("E0005");
//				dao.update(authorityRecordVO02);
//				System.out.println("修改成功!!");
//				

				
				
////				
//				//查詢多筆
				List<AuthorityRecordVO> list = dao.getAll();
				for(AuthorityRecordVO rt : list) {
					System.out.println(rt.getAuthID());
					System.out.println(rt.getEmpID());
					System.out.println("=========================");
					
				
				
		}
		
		}
	}
		
		
	

