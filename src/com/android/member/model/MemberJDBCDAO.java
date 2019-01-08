package com.android.member.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.android.tool.ConData;

public class MemberJDBCDAO implements MemberDAO_interface{
	
	private static final String FIND_BY_ACC_PSW = "SELECT * FROM member where memAcc = ? AND memPsw = ?";
	private static final String UPDATE = "UPDATE Member set memPsw = ? , memTel = ?, memAddr = ?, memSkill = ?, memPic = ? where memID = ?";
	private static final String FIND_BY_MEMID = "SELECT memID, memName, memBirth, memEmail, memTel, memAddr, memSex, memSkill, memIDcard from Member where memID = ?";
	private static final String FIND_IMAGE_BY_MEMID = "SELECT memPic FROM member where memId = ?";
	
	
	static {
		try {
			Class.forName(ConData.DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}    	
	}
	
	public MemberJDBCDAO() {
	}
	
	@Override
	public String isMember(String account, String password) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String memberId = "nothing";
		
		try {
			con = DriverManager.getConnection(ConData.URL, ConData.USER, ConData.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_ACC_PSW);
			
			pstmt.setString(1, account);
			pstmt.setString(2, password);
			
			rs = pstmt.executeQuery();    //執行, 游標下移一行
			rs.next();
			memberId = rs.getString(1);
			
			return memberId;                //成功, 回傳 memId 
			
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
		
		return memberId;                    //發生Exception, 回傳 nothing
	}
	
	
	@Override
	public boolean update(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(ConData.URL, ConData.USER, ConData.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1,memberVO.getMemPsw());
			pstmt.setString(2,memberVO.getMemTel());
			pstmt.setString(3,memberVO.getMemAddr());
			pstmt.setString(4,memberVO.getMemSkill());
			pstmt.setString(6,memberVO.getMemID());
			
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
		return false;
	}

	@Override
	public MemberVO findByMemId(String memId) {
		MemberVO memberVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(ConData.URL, ConData.USER, ConData.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_MEMID);
			
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				memberVO = new MemberVO();
				
				memberVO.setMemID(rs.getString("MemID"));
				memberVO.setMemName(rs.getString("MemName"));
				memberVO.setMemBirth(rs.getDate("MemBirth"));
				memberVO.setMemEmail(rs.getString("MemEmail"));
				memberVO.setMemTel(rs.getString("MemTel"));
				memberVO.setMemAddr(rs.getString("MemAddr"));
				memberVO.setMemSex(rs.getString("MemSex"));
				memberVO.setMemSkill(rs.getString("MemSkill"));
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
	
	public byte[] getImage(String memId) {
		byte[] image = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(ConData.URL, ConData.USER, ConData.PASSWORD);
			pstmt = con.prepareStatement(FIND_IMAGE_BY_MEMID);
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				image = rs.getBytes(1);
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
		return image;      // 沒有圖片為null
	}

	public static void main(String[] args) {
		MemberJDBCDAO dao = new MemberJDBCDAO(); 
		
		//判斷有無會員資料
		System.out.println("有這會員：" + dao.isMember("peter1", "abc123"));
		System.out.println("無這會員：" + dao.isMember("peter1", "abc"));
		System.out.println("------------");
		
		//修改
//		MemberVO memberVO02= new MemberVO();
//		
//		memberVO02.setMemPsw("9527");
//		memberVO02.setMemTel("0925688888");
//		memberVO02.setMemAddr("台北市-內湖區-中正路135號");
//		memberVO02.setMemSkill("寫程式,健身");
//		memberVO02.setMemID("M0002");
//		dao.update(memberVO02);
//		System.out.println("修改成功!!");
		
		//查詢一筆(只能查 有照片的 不然會出錯 把照片那行刪掉 就可以查沒照片的)
//		MemberVO memberVO03= dao.findByMemId("M0003");
//			
//		System.out.println(memberVO03.getMemID());
//		System.out.println(memberVO03.getMemName());
//		System.out.println(memberVO03.getMemBirth());
//		System.out.println(memberVO03.getMemEmail());
//		System.out.println(memberVO03.getMemTel());
//		System.out.println(memberVO03.getMemAddr());
//		System.out.println(memberVO03.getMemSex());
//		System.out.println(memberVO03.getMemSkill());
//		System.out.println(memberVO03.getMemIDcard());
//		System.out.println("成功");
			
	}
}


