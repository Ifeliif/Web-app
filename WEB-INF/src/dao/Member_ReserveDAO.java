package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Member_Reserve;

public class Member_ReserveDAO {
	private Connection connection;
	public Member_ReserveDAO(Connection connection){
		super();
		this.connection=connection;
	}

	public List<Member_Reserve> selectCertain(String member_id){
		try{
			PreparedStatement ps=connection.prepareStatement("select distinct arrival_stop_id,departure_stop_id,bus_id,reserve_date,start_time,end_time,fee,lines.line_id from reserve inner join lines on lines.line_id=reserve.line_id where member_id =?" );
			ps.setString(1, member_id);

			ResultSet rs=ps.executeQuery();
			List<Member_Reserve> result=new ArrayList<Member_Reserve>();
			while(rs.next()){
				Member_Reserve mr=new Member_Reserve();
				mr.setArrival_stop_id(rs.getString("arrival_stop_id"));
				mr.setDeparture_stop_id(rs.getString("departure_stop_id"));
				mr.setBus_id(rs.getString("bus_id"));
				mr.setReserve_date(rs.getDate("reserve_date"));
				mr.setLine_id(rs.getString("line_id"));
				mr.setStart_time(rs.getString("start_time"));
				mr.setEnd_time(rs.getString("end_time"));
				mr.setFee(rs.getInt("fee"));
				result.add(mr);
			}
			return result;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}


}
