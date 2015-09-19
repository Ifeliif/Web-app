package dao;

import java.sql.*;
import java.util.*;

import dto.BusesDTO;


public class BusesDAO {
	private Connection connection;
	public BusesDAO(Connection connection){
		super();
		this.connection=connection;
	}

	public List<BusesDTO> selectAll(){
		try{
			PreparedStatement ps=connection.prepareStatement("select  * from buses order by bus_id" );
			ResultSet rs=ps.executeQuery();
			List<BusesDTO> result=new ArrayList<BusesDTO>();
			while(rs.next()){
				BusesDTO busDTO=new BusesDTO();
				busDTO.setBus_id(rs.getString("bus_id"));
				busDTO.setSeat_colum(rs.getInt("seat_colum"));
				busDTO.setSeat_row(rs.getInt("seat_row"));
				busDTO.setNp_name(rs.getString("np_name"));
				busDTO.setNp_class_no(rs.getInt("np_class_no"));
				busDTO.setNp_hiragana(rs.getString("np_hiragana"));
				busDTO.setNp_no1(rs.getInt("np_no1"));
				busDTO.setNp_no2(rs.getInt("np_no2"));
				result.add(busDTO);
			}
			return result;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	public List<BusesDTO> selectCertain(String bus_id){
		try{
			PreparedStatement ps=connection.prepareStatement("select  * from buses where bus_id = ?" );
			ps.setString(1, bus_id);
			ResultSet rs=ps.executeQuery();
			List<BusesDTO> result=new ArrayList<BusesDTO>();
			while(rs.next()){
				BusesDTO busDTO=new BusesDTO();
				busDTO.setBus_id(rs.getString("bus_id"));
				busDTO.setSeat_colum(rs.getInt("seat_colum"));
				busDTO.setSeat_row(rs.getInt("seat_row"));
				busDTO.setNp_name(rs.getString("np_name"));
				busDTO.setNp_class_no(rs.getInt("np_class_no"));
				busDTO.setNp_hiragana(rs.getString("np_hiragana"));
				busDTO.setNp_no1(rs.getInt("np_no1"));
				busDTO.setNp_no2(rs.getInt("np_no2"));
				result.add(busDTO);
			}
			return result;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}



	public int insert(BusesDTO dto){
		try{
			PreparedStatement ps=connection.prepareStatement("insert into buses " +
					"(bus_id,seat_colum,seat_row,np_name,np_class_no,np_hiragana,np_no1,np_no2) " +
					"values('BU'||to_char(nextval('bus_id'),'FM0000'),?,?,?,?,?,?,?)");
			ps.setInt(1, dto.getSeat_colum());
			ps.setInt(2, dto.getSeat_row());
			ps.setString(3, dto.getNp_name());
			ps.setInt(4, dto.getNp_class_no());
			ps.setString(5,dto.getNp_hiragana());//以降、立野作成
			ps.setInt(6,dto.getNp_no1());
			ps.setInt(7,dto.getNp_no2());
			return ps.executeUpdate();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	public int deleteCertain(String bus_id){
		try{
			PreparedStatement ps=connection.prepareStatement("delete from buses where bus_id=?");
			ps.setString(1, bus_id);
			return ps.executeUpdate();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
}





