package com.member.model;

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

import tool.BLOB;

public class MemberDAO implements MemberDAO_interface {
	

	
	
	private static final String INSERT_SQL = "INSERT INTO Member (memID,memName,memAcc,memPsw,memBirth,memEmail,memTel,memAddr,memSex,memSkill,memPic,memIDcard)"
			+ "values('M'||LPAD(to_char(mem_seq.NEXTVAL),4, '0'),?,?,?,?,?,?,?,?,?,?,?)";

	private static final String UPDATE = "UPDATE Member set memName =?,memAcc =?,memPsw =?,memBirth =?,memEmail =?,memTel =?,memAddr =?,memSex =?,memSkill =?,memState=?,memPic =?,memIDcard =?,memReg=? where memID=?";

	private static final String FIND_ALL_STMT = "SELECT * from  Member";

	private static final String FIND_BY_PK = "SELECT memID, memName, memAcc, memPsw, memBirth, memEmail, memTel, memAddr, memSex, memReg, memSkill, memState,memPic,memIDcard from Member where memID = ?";
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
	public void insert(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con=ds.getConnection();
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
			con=ds.getConnection();
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

	@Override
	public MemberVO findByPK(String memID) {
		MemberVO memberVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con=ds.getConnection();
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
				con=ds.getConnection();
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
}

