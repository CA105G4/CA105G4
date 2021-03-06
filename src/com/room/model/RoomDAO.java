package com.room.model;

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

import com.roomType.model.RoomTypeService;
import com.roomType.model.RoomTypeVO;

public class RoomDAO implements RoomDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CA105G4DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_SQL = 
			 "Insert into Room (roomID, roomTypeID, braID, roomNo, roomState, memName)" 
			+ "values ('R'||LPAD(to_char(room_seq.nextVal),5,'0'), ?, ?, ?, ? ,?)";
	private static final String UPDATE_SQL = "Update Room set roomTypeID = ?, braID = ?, roomNo = ?, roomState = ?, memName =? where roomID = ?";
	private static final String GET_ONE_SQL = "Select roomID, roomTypeID, braID, roomNo, roomState, memName from Room where roomID = ?";
	private static final String GET_ALL_SQL = "Select * from Room";
	private static final String Find_By_Branch_SQL = "select * from Room where braID = ? ";
	private static final String Find_Room_ForAssign_SQL = "select * from Room where braID = ? and roomTypeID = ? and roomState = ?";
	
	/**[CHECKIN]Gina更改房間狀態)**/
	private static final String UPDATE_roomState_By_roomID = "Update Room set ROOMSTATE = ?, MEMNAME=? where roomID = ?";
	/**[CHECKIN]Gina更改房間狀態)**/
	
	//依房號去修改狀態
	private static final String UPDATE_roomState_By_roomNo = "Update Room set ROOMSTATE = ? where roomNo = ?";
	
	//依分店及狀態查詢各個房況總數
	private static final String GET_RoomState = "select COUNT(*) from Room where roomState = ? and braID= ? ";
	
	//依房號去修改狀態
	private static final String UPDATE_RoomState_By_RoomID = "Update Room set ROOMSTATE = ? where roomID = ?";
	
	//一次新增單個房型多個房間
//	private static final String Add_ROOMS_ONETIME = "insert into room (ROOMID,roomtypeid,braid,roomno) "
//	+ " select 'R'||LPAD(to_char(room_seq.nextVal),5,'0'),'?','?',roomNo)" 
//	+" from room where braid='B01'";
	
	
	@Override
	public void insert(RoomVO roomVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_SQL);
			
			pstmt.setString(1, roomVO.getRoomTypeID());
			pstmt.setString(2, roomVO.getBraID());
			pstmt.setInt(3, roomVO.getRoomNo());
			pstmt.setInt(4, roomVO.getRoomState());
			pstmt.setString(5, roomVO.getMemName());
			
			pstmt.executeUpdate();
			
		}catch(SQLException se) {
			se.printStackTrace();
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
	public void update(RoomVO roomVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_SQL);
			
			pstmt.setString(1, roomVO.getRoomTypeID());
			pstmt.setString(2, roomVO.getBraID());
			pstmt.setInt(3, roomVO.getRoomNo());
			pstmt.setInt(4, roomVO.getRoomState());
			pstmt.setString(5, roomVO.getMemName());
			pstmt.setString(6, roomVO.getRoomID());
			
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
	public RoomVO findByPrimaryKey(String roomID) {
		RoomVO roomVO= null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_SQL);
			
			pstmt.setString(1, roomID);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				roomVO = new RoomVO();
				roomVO.setRoomID(rs.getString("roomID"));
				roomVO.setRoomTypeID(rs.getString("roomTypeID"));
				roomVO.setBraID(rs.getString("braID"));
				roomVO.setRoomNo(rs.getInt("roomNo"));
				roomVO.setRoomState(rs.getInt("roomState"));
				roomVO.setMemName(rs.getString("memName"));
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
		return roomVO;
	}

	@Override
	public List<RoomVO> getAll() {
		List<RoomVO> list = new ArrayList<RoomVO>();
		RoomVO roomVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_SQL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				roomVO = new RoomVO();
				roomVO.setRoomID(rs.getString("roomID"));
				roomVO.setRoomTypeID(rs.getString("roomTypeID"));
				roomVO.setBraID(rs.getString("braID"));
				roomVO.setRoomNo(rs.getInt("roomNo"));
				roomVO.setRoomState(rs.getInt("roomState"));
				roomVO.setMemName(rs.getString("memName"));
				
				list.add(roomVO);
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
	
	@Override
	public List<RoomVO> findRoomByBranch(String braID){
		List<RoomVO> list = new ArrayList<RoomVO>();
		RoomVO roomVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(Find_By_Branch_SQL);
			
			pstmt.setString(1, braID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				roomVO = new RoomVO();
				roomVO.setRoomID(rs.getString("roomID"));
				roomVO.setRoomTypeID(rs.getString("roomTypeID"));
				roomVO.setBraID(rs.getString("braID"));
				roomVO.setRoomNo(rs.getInt("roomNo"));
				roomVO.setRoomState(rs.getInt("roomState"));
				roomVO.setMemName(rs.getString("memName"));
				
				list.add(roomVO);
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
	
	@Override
	public List<RoomVO> findRoomForAssign(String braID, String roomTypeID, Integer roomState){
		List<RoomVO> list = new ArrayList<RoomVO>();
		RoomVO roomVO = new RoomVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(Find_Room_ForAssign_SQL);
			
			pstmt.setString(1, braID);
			pstmt.setString(2, roomTypeID);
			pstmt.setInt(3, roomState);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				roomVO = new RoomVO();
				roomVO.setRoomID(rs.getString("roomID"));
				roomVO.setRoomTypeID(rs.getString("roomTypeID"));
				roomVO.setBraID(rs.getString("braID"));
				roomVO.setRoomNo(rs.getInt("roomNo"));
				roomVO.setRoomState(rs.getInt("roomState"));
				roomVO.setMemName(rs.getString("memName"));
				
				list.add(roomVO);
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
	
	/**[CHECKIN]Gina更改房間 狀態 及 入住會員名稱)**/
	@Override
	public void updateRoomState(Integer roomState, String memname, String roomID) {
System.out.println("房間收到roomState:"+roomState);
System.out.println("房間收到memname:"+memname);
System.out.println("房間收到roomID:"+roomID);
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_roomState_By_roomID);
			
			pstmt.setInt(1, roomState);
			pstmt.setString(2, memname);
			pstmt.setString(3, roomID);
			
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
	public void updateRSByRoomNo(Integer roomState, Integer roomNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_roomState_By_roomNo);
			
			pstmt.setInt(1, roomState);
			pstmt.setInt(2, roomNo);
			
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
	public int getEachRoomState(Integer roomState, String braID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer total = 0;
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(GET_RoomState);
			
			pstmt.setInt(1, roomState);
			pstmt.setString(2, braID);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
			total = rs.getInt(1);
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
		return total;
	}

	@Override
	public void updateRSByRoomID(Integer roomState, String roomID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_RoomState_By_RoomID);
			
			pstmt.setInt(1, roomState);
			pstmt.setString(2, roomID);
			
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
	public void addRoomsOneTime(String braID, String roomTypeID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			System.out.println("roomTypeID:"+roomTypeID);
			System.out.println("braID:"+braID);
			
			String Add_ROOMS_ONETIME = "INSERT INTO room (ROOMID,roomtypeid,braid,roomno) SELECT 'R'||LPAD(to_char(room_seq.nextVal),5,'0'),'"+roomTypeID+"','"+braID + "',? FROM room where roomtypeid='RT01' and rownum =1";
			pstmt = con.prepareStatement(Add_ROOMS_ONETIME);
			
			RoomTypeService rtSvc = new RoomTypeService();
			RoomTypeVO rtVO = rtSvc.getOneRoomType(roomTypeID);
			int count = rtVO.getTotal();
			System.out.println("此房型有"+count+"間");
			
			
			for(int i = 0 ; i<count ; i++) {
			pstmt.setInt(1, 101+i);
			pstmt.addBatch();
			}
			pstmt.executeBatch();
			
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
}
