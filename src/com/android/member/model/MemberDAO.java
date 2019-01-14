package com.android.member.model;

import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.android.tool.ConData;

public class MemberDAO implements MemberDAO_interface{
	
	private static final String FIND_BY_ACC_PSW = "SELECT * FROM member where memAcc = ? AND memPsw = ?";
	private static final String UPDATE = "UPDATE Member set memPsw = ? , memTel = ?, memAddr = ?, memSkill = ?, memPic = ? where memID = ?";
	private static final String FIND_BY_PK = "SELECT memID, memName, memBirth, memEmail, memTel, memAddr, memSex, memSkill, memIDcard from Member where memID = ?";
	private static final String FIND_IMAGE_BY_MEMID = "SELECT memPic FROM member where memId = ?";

	//連線池
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CA105G4DB");
		} catch (NamingException e) {
			e.printStackTrace();
		} 
	}
	
	public MemberDAO() {
	}
	
	@Override
	public String isMember(String account, String password) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String memberId = "nothing";
		
		try {
			con = ds.getConnection();
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
		
		return memberId;                 //發生Exception, 回傳 nothing
	}

	@Override
	public boolean update(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
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
	public MemberVO findByMemId(String memID) {
		MemberVO memberVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			
			pstmt.setString(1, memID);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				memberVO = new MemberVO();
				
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_IMAGE_BY_MEMID);
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				image = rs.getBytes("memPic");
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
	
}


