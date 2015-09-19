package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.LinesDTO;
import dto.ReserveDTO;
import dto.StopsDTO;

public class ReserveDAO {

	private Connection connection;
	public ReserveDAO(Connection connection){
		super();
		this.connection=connection;
	}
	//停留所全表示
	public List<StopsDTO> selectAllStops(){
		try{
			PreparedStatement ps=connection.prepareStatement("select * from stops" );
			ResultSet rs=ps.executeQuery();
			List<StopsDTO> result=new ArrayList<StopsDTO>();
			while(rs.next()){
				StopsDTO stopsDTO=new StopsDTO();
				stopsDTO.setStop_id(rs.getString("stop_id"));
				stopsDTO.setPrefecture(rs.getString("prefecture"));
				stopsDTO.setStop_name(rs.getString("stop_name"));
				result.add(stopsDTO);
			}
			return result;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	//路線全表示
	public List<LinesDTO> selectAllLines(){
		try{
			PreparedStatement ps=connection.prepareStatement("select  * from lines order by line_id" );
			ResultSet rs=ps.executeQuery();
			List<LinesDTO> result=new ArrayList<LinesDTO>();
			while(rs.next()){
				LinesDTO lineDTO=new LinesDTO();
				lineDTO.setLine_id(rs.getString("line_id"));
				lineDTO.setArrival_stop_id(rs.getString("arrival_stop_id"));
				lineDTO.setDeparture_stop_id(rs.getString("departure_stop_id"));
				lineDTO.setFee(rs.getInt("fee"));
				lineDTO.setBus_id(rs.getString("bus_id"));
				lineDTO.setStart_date(rs.getDate("start_date"));
				lineDTO.setEnd_date(rs.getDate("end_date"));
				lineDTO.setStart_time(rs.getString("start_time"));
				lineDTO.setEnd_time(rs.getString("end_time"));
				result.add(lineDTO);

			}
			return result;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	//初回検索のDAO
	public List<ReserveDTO> selectCertainDateLine(Date reserve_date , String line_id){
		try{
			PreparedStatement ps=connection.prepareStatement("select  * from reserve where reserve_date = ? and line_id = ? order by seat_number" );
			ps.setDate(1, reserve_date);
			ps.setString(2, line_id);
			ResultSet rs=ps.executeQuery();
			List<ReserveDTO> result=new ArrayList<ReserveDTO>();
			while(rs.next()){
				ReserveDTO reserveDTO=new ReserveDTO();
				reserveDTO.setReserve_id(rs.getString("reserve_id"));
				reserveDTO.setMember_id(rs.getString("member_id"));
				reserveDTO.setLine_id(rs.getString("line_id"));
				reserveDTO.setSeat_number(rs.getString("seat_number"));
				reserveDTO.setReserve_date(rs.getDate("reserve_date"));
				result.add(reserveDTO);
			}
			return result;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	//再表示のDAO
	public List<ReserveDTO> selectCertainDate(Date reserve_date){
		try{
			PreparedStatement ps=connection.prepareStatement("select  * from reserve where reserve_date = ?" );
			ps.setDate(1, reserve_date);
			ResultSet rs=ps.executeQuery();
			List<ReserveDTO> result=new ArrayList<ReserveDTO>();
			while(rs.next()){
				ReserveDTO reserveDTO=new ReserveDTO();
				reserveDTO.setReserve_id(rs.getString("reserve_id"));
				reserveDTO.setMember_id(rs.getString("member_id"));
				reserveDTO.setLine_id(rs.getString("line_id"));
				reserveDTO.setSeat_number(rs.getString("seat_number"));
				reserveDTO.setReserve_date(rs.getDate("reserve_date"));
				result.add(reserveDTO);
			}
			return result;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}



}

