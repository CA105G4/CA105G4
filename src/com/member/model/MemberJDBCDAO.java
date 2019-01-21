package com.member.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tool.BLOB;

public class MemberJDBCDAO implements MemberDAO_interface {
	
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "CA105G4"; 
	private static final String PASSWORD = "123456";
	
	
	private static final String INSERT_SQL = "INSERT INTO Member (memID,memName,memAcc,memPsw,memBirth,memEmail,memTel,memAddr,memSex,memSkill,memPic,memIDcard)"
			+ "values('M'||LPAD(to_char(mem_seq.NEXTVAL),4, '0'),?,?,?,?,?,?,?,?,?,?,?)";

	private static final String UPDATE = "UPDATE Member set memName =?,memAcc =?,memPsw =?,memBirth =?,memEmail =?,memTel =?,memAddr =?,memSex =?,memSkill =?,memState=?,memPic =?,memIDcard =?,memReg=? where memID=?";
	private static final String UPDATE_state = "UPDATE Member set memState=? where memID=?";
	private static final String FIND_ALL_STMT = "SELECT * from  Member";
 
	private static final String FIND_BY_PK = "SELECT memID, memName, memAcc, memPsw, memBirth, memEmail, memTel, memAddr, memSex, memReg, memSkill, memState,memPic,memIDcard from Member where memID = ?";
	private static final String FIND_BY_MEMACC = "SELECT * from Member where memAcc = ?";
	private static final String FIND_BY_MEMIDCARD = "SELECT * from Member where memIDcard  = ?";

	//Ivan 從需求名稱找出符合技能的會員
	private static final String GET_BY_MEMSKILL = "select * from member where memskill like ";
	
	static {
    	try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}    	
    }
	
	
	
	
	@Override
	public void insert(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_SQL);
		
			pstmt.setString(1,memberVO.getMemName());
			pstmt.setString(2,memberVO.getMemAcc());
			pstmt.setString(3,memberVO.getMemPsw());
			pstmt.setDate(4,memberVO.getMemBirth());
			pstmt.setString(5,memberVO.getMemEmail());
			pstmt.setString(6,memberVO.getMemTel());
			pstmt.setString(7,memberVO.getMemAddr());
			pstmt.setString(8,memberVO.getMemSex());
			pstmt.setString(9,memberVO.getMemSkill());
			pstmt.setBytes(10,memberVO.getMemPic());
			pstmt.setString(11,memberVO.getMemIDcard());
			
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
	public void update(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1,memberVO.getMemName());
			pstmt.setString(2,memberVO.getMemAcc());
			pstmt.setString(3,memberVO.getMemPsw());
			pstmt.setDate(4,memberVO.getMemBirth());
			pstmt.setString(5,memberVO.getMemEmail());
			pstmt.setString(6,memberVO.getMemTel());
			pstmt.setString(7,memberVO.getMemAddr());
			pstmt.setString(8,memberVO.getMemSex());
			pstmt.setString(9,memberVO.getMemSkill());
			pstmt.setInt(10,memberVO.getMemState());
			pstmt.setBytes(11,memberVO.getMemPic());
			pstmt.setString(12,memberVO.getMemIDcard());
			pstmt.setDate(13,memberVO.getMemReg());
			pstmt.setString(14,memberVO.getMemID());
		
			
			
			
			
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
	
	public void update_state(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_state);
			
			pstmt.setInt(1,memberVO.getMemState());
			pstmt.setString(2,memberVO.getMemID());
			
			
			
			
			
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
	public String findIDcard(String memIDcard) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String memID =null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_MEMIDCARD);
			
			pstmt.setString(1,memIDcard );
			rs = pstmt.executeQuery();
			while(rs.next()) {
				memID=rs.getString("memID");
			}
			
			
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
		return memID ;
	}	
	public String findAcc(String memAcc) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String memID =null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_MEMACC);
			
			pstmt.setString(1,memAcc);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				memID=rs.getString("memID");
			}
			
			
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
		return memID;
	}
	

	@Override
	public MemberVO findByPK(String memID) {
		MemberVO memberVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			
			pstmt.setString(1, memID);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				memberVO = new MemberVO();
				
				memberVO.setMemID(rs.getString("MemID"));
				memberVO.setMemName(rs.getString("MemName"));
				memberVO.setMemAcc(rs.getString("MemAcc"));
				memberVO.setMemPsw(rs.getString("MemPsw"));
				memberVO.setMemBirth(rs.getDate("MemBirth"));
				memberVO.setMemEmail(rs.getString("MemEmail"));
				memberVO.setMemTel(rs.getString("MemTel"));
				memberVO.setMemAddr(rs.getString("MemAddr"));
				memberVO.setMemSex(rs.getString("MemSex"));
				memberVO.setMemReg(rs.getDate ("MemReg"));
				memberVO.setMemSkill(rs.getString("MemSkill"));
				memberVO.setMemState(rs.getInt("MemState"));
				memberVO.setMemPic(rs.getBytes("MemPic"));
				memberVO.setMemIDcard(rs.getString("MemIDcard"));
				
				
				
				
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
		return memberVO;
	}

	@Override
		public List<MemberVO> getAll() {
			List<MemberVO> list = new ArrayList<MemberVO>();
			MemberVO memberVO = null;
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				con = DriverManager.getConnection(URL, USER, PASSWORD);
				pstmt = con.prepareStatement(FIND_ALL_STMT);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					memberVO = new MemberVO();
					
					memberVO.setMemID(rs.getString("MemID"));
					memberVO.setMemName(rs.getString("MemName"));
					memberVO.setMemAcc(rs.getString("MemAcc"));
					memberVO.setMemPsw(rs.getString("MemPsw"));
					memberVO.setMemBirth(rs.getDate("MemBirth"));
					memberVO.setMemEmail(rs.getString("MemEmail"));
					memberVO.setMemTel(rs.getString("MemTel"));
					memberVO.setMemAddr(rs.getString("MemAddr"));
					memberVO.setMemSex(rs.getString("MemSex"));
					memberVO.setMemReg(rs.getDate ("MemReg"));
					memberVO.setMemSkill(rs.getString("MemSkill"));
					memberVO.setMemState(rs.getInt("MemState"));
					memberVO.setMemIDcard(rs.getString("MemIDcard"));
					
					list.add(memberVO);
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
	
	@Override
	public List<MemberVO> getMemBySkill(String weName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	public static void main(String[] args) {
		MemberJDBCDAO dao = new MemberJDBCDAO(); 
		
		//新增
//		MemberVO memberVO= new MemberVO();
//		memberVO.setMemName("DAVID47");
//		memberVO.setMemAcc("DAVID123");
//		memberVO.setMemPsw("123456");
//		memberVO.setMemBirth(Date.valueOf("1990-11-17"));
//		memberVO.setMemEmail("ixlogic.wu2@gmail.com");
//		memberVO.setMemTel("0988888888");
//		memberVO.setMemAddr("台北市-信義區-市府路1號");
//		memberVO.setMemSex("M");
//		memberVO.setMemSkill("煮海鮮");
//		
//		memberVO.setMemPic(new BLOB().writeBlob("images/black.jpeg"));
//		memberVO.setMemIDcard("A123456789");
//		dao.insert(memberVO);
//		System.out.println("成功成功!!");
//		
	
		 //修改
		MemberVO memberVO02= new MemberVO();
		
			memberVO02.setMemPsw("9527");
			memberVO02.setMemTel("0925688888");
			memberVO02.setMemAddr("台北市-內湖區-中正路135號");
			memberVO02.setMemSkill("寫程式,健身");
//			memberVO02.setMemPic(new BLOB().writeBlob("images/panta.jpg"));
			memberVO02.setMemID("M0002");
			dao.update(memberVO02);
			System.out.println("修改成功!!");
		
//			//查詢一筆(只能查 有照片的 不然會出錯 把照片那行刪掉 就可以查沒照片的)
//		
//			MemberVO memberVO03= dao.findByPK("M0004");
//			
//				System.out.println(memberVO03.getMemID());
//				System.out.println(memberVO03.getMemName());
//				System.out.println(memberVO03.getMemAcc());
//				System.out.println(memberVO03.getMemPsw());
//				System.out.println(memberVO03.getMemBirth());
//				System.out.println(memberVO03.getMemEmail());
//				System.out.println(memberVO03.getMemTel());
//				System.out.println(memberVO03.getMemAddr());
//				System.out.println(memberVO03.getMemSex());
//				System.out.println(memberVO03.getMemReg());
//				System.out.println(memberVO03.getMemSkill());
//				System.out.println(memberVO03.getMemState());
//				new BLOB().readBlob(memberVO03.getMemPic(),"input/black.jpeg");
//				System.out.println(memberVO03.getMemIDcard());
//				System.out.println("成功");
//			
//			//查詢多筆	
//			List<MemberVO> list = dao.getAll();
//			for(MemberVO rt : list) {
//				
//				System.out.println(rt.getMemID());
//				System.out.println(rt.getMemName());
//				System.out.println(rt.getMemAcc());
//				System.out.println(rt.getMemPsw());
//				System.out.println(rt.getMemBirth());
//				System.out.println(rt.getMemEmail());
//				System.out.println(rt.getMemTel());
//				System.out.println(rt.getMemAddr());
//				System.out.println(rt.getMemSex());
//				System.out.println(rt.getMemReg());
//				System.out.println(rt.getMemSkill());
//				System.out.println(rt.getMemState());
//				System.out.println(rt.getMemIDcard());				
//				
//				System.out.println("=========================");
//	
//			}
	
	
	
	}


	
	
	
	

	

}
