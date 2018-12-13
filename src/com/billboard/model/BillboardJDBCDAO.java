package com.billboard.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tool.BLOB;

public class BillboardJDBCDAO implements BillboardDAO_interface{
	private static final String DRIVER="oracle.jdbc.driver.OracleDriver";
	
	private static final String URL ="jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER="system";
	private static final String PWD="oracle";
	
	private static final String INSERT_SQL="INSERT INTO Billboard(bbID,pic,bbStart,bbEnd) "
																				+ "VALUES(bb_seq.nextval,?,?,?)";
	private static final String UPDATE="UPDATE Billboard set bbStart=?, bbEnd=? where bbID=? ";
	private static final String DELETE="DELETE FROM Billboard WHERE bbID=?";
	private static final String GET_ONE_SQL="SELECT bbID,bbStart,bbEnd FROM Billboard WHERE bbID=?";
	private static final String GET_ALL_SQL="SELECT * FROM Billboard";
	
	
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void insert(BillboardVO billboardVO) {
		Connection con =null;
		PreparedStatement pstmt =null;
		
		try {
			con =DriverManager.getConnection(URL, USER, PWD);
			pstmt=con.prepareStatement(INSERT_SQL);
			pstmt.setBytes(1,billboardVO.getPic());
			pstmt.setDate(2, billboardVO.getBbStart());
			pstmt.setDate(3, billboardVO.getBbEnd());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
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
	}

	

	@Override
	public void update(BillboardVO billboardVO) {
		Connection con = null;
		PreparedStatement pstmt =null;
		
		try {
			con =DriverManager.getConnection(URL, USER, PWD);
			pstmt=con.prepareStatement(UPDATE);
			
			pstmt.setDate(1,billboardVO.getBbStart());
			pstmt.setDate(2, billboardVO.getBbEnd());
			pstmt.setInt(3,billboardVO.getBbID());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	public void delete(BillboardVO billboardVO) {
		Connection con =null;
		PreparedStatement pstmt =null;
		
		try {
			con =DriverManager.getConnection(URL, USER, PWD);
			pstmt =con.prepareStatement(DELETE);
			
			pstmt.setInt(1,billboardVO.getBbID());
			
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
	public BillboardVO findByPK(Integer bbID) {
		BillboardVO billboardVO =null;
		
		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		
		try {
			con =DriverManager.getConnection(URL, USER, PWD);
			pstmt =con.prepareStatement(GET_ONE_SQL);
			
			pstmt.setInt(1,bbID);
			rs =pstmt.executeQuery();
			
			while(rs.next()) {
				billboardVO =new BillboardVO();
				
				billboardVO.setBbID(rs.getInt("bbID"));
				billboardVO.setBbStart(rs.getDate("bbStart"));
				billboardVO.setBbEnd(rs.getDate("bbEnd"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
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
				
		return billboardVO;
	}







	@Override
	public List<BillboardVO> getAll() {
		List<BillboardVO> list =new ArrayList<BillboardVO>();
		BillboardVO billboardVO =null;
		
		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		
		try {
			con =DriverManager.getConnection(URL, USER, PWD);
			pstmt =con.prepareStatement(GET_ALL_SQL);
			rs = pstmt.executeQuery(); 
			
			while(rs.next()) {
				billboardVO = new BillboardVO();
				
				billboardVO.setBbID(rs.getInt("bbID"));
				billboardVO.setUrl(rs.getString("URL"));
				billboardVO.setPic(rs.getBytes("pic"));
				billboardVO.setBbStart(rs.getDate("bbStart"));
				billboardVO.setBbEnd(rs.getDate("bbEnd"));
				
				list.add(billboardVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
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
		
BillboardJDBCDAO dao =new BillboardJDBCDAO();
		
		//新增
		BillboardVO vo = new BillboardVO(); 
		Date d1 =Date.valueOf("2018-12-01");
		Date d2 =Date.valueOf("2018-12-25");
		
		vo.setPic(new BLOB().writeBlob("room_sales.png"));
		vo.setBbStart(d1);
		vo.setBbEnd(d2);
		
		dao.insert(vo);
		System.out.println("新增成功");
		System.out.println("--------------------");
		
		//修改
		BillboardVO vo2 =new BillboardVO();
		
		Date d3 =Date.valueOf("2018-12-10");
		Date d4 =Date.valueOf("2018-12-28");
		vo2.setBbStart(d3);
		vo2.setBbEnd(d4);
		vo2.setBbID(1003);
		
		dao.update(vo2);
		System.out.println("修改成功");
		System.out.println("--------------------");
		
		//刪除
		
		BillboardVO vo3 =new BillboardVO();
		
		vo3.setBbID(1003);
		dao.delete(vo3);
		
		System.out.println("刪除成功");
		System.out.println("--------------------");
		
		//搜尋
		
		BillboardVO vo4 =dao.findByPK(1001);
		
		System.out.print(vo4.getBbID()+" ");
		System.out.print(vo4.getBbStart()+" ");
		System.out.println(vo4.getBbEnd());
		
		System.out.println("單筆查詢成功");
		System.out.println("--------------------");
		
		//搜尋全部
		
		List<BillboardVO>list =dao.getAll();
		
		for(BillboardVO vo5:list) {
			System.out.print(vo5.getBbID()+" ");
			System.out.print(vo5.getUrl()+" ");
			System.out.print(vo5.getPic()+" ");
			System.out.print(vo5.getBbStart()+" ");
			System.out.println(vo5.getBbEnd()+" ");
		}
		
		System.out.println("查詢全部成功");
		System.out.println("-------------------");
		
		

	}
	
}
