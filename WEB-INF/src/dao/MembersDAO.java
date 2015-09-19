package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import dto.MembersDTO;

public class MembersDAO {

	private Connection connection;
	public MembersDAO(Connection connection){
		super();
		this.connection=connection;
	}
	public List<MembersDTO> selectAll(){
		try{
			PreparedStatement ps=connection.prepareStatement("select  * from members order by member_id" );
			ResultSet rs=ps.executeQuery();
			List<MembersDTO> result=new ArrayList<MembersDTO>();
			while(rs.next()){
				MembersDTO memberDTO=new MembersDTO();
				memberDTO.setMember_id(rs.getString("member_id"));
				memberDTO.setMember_name(rs.getString("member_name"));
				memberDTO.setTelephone(rs.getString("telephone"));
				memberDTO.setMail(rs.getString("mail"));
				result.add(memberDTO);
			}
		return result;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	public List<MembersDTO> selectReserch_id(String member_id){
		try{
			PreparedStatement ps=connection.prepareStatement("select * from members where member_id like ?");
			ps.setString(1,member_id+"%");
			ResultSet rs=ps.executeQuery();
			List<MembersDTO> result=new ArrayList<MembersDTO>();
			while(rs.next()){
					MembersDTO memberDTO=new MembersDTO();
					memberDTO.setMember_id(rs.getString("member_id"));
					memberDTO.setMember_name(rs.getString("member_name"));
					memberDTO.setTelephone(rs.getString("telephone"));
					memberDTO.setMail(rs.getString("mail"));
					result.add(memberDTO);
				}
			return result;
			}catch(SQLException e){
				throw new RuntimeException(e);
			}
		}

	public List<MembersDTO> selectReserch_name(String member_name){
		try{
			PreparedStatement ps=connection.prepareStatement("select * from members where member_name like ?");
			ps.setString(1,"%" + member_name +"%");
			ResultSet rs=ps.executeQuery();
			List<MembersDTO> result=new ArrayList<MembersDTO>();
			while(rs.next()){
					MembersDTO memberDTO=new MembersDTO();
					memberDTO.setMember_id(rs.getString("member_id"));
					memberDTO.setMember_name(rs.getString("member_name"));
					memberDTO.setTelephone(rs.getString("telephone"));
					memberDTO.setMail(rs.getString("mail"));
					result.add(memberDTO);
				}
			return result;
			}catch(SQLException e){
				throw new RuntimeException(e);
			}
		}

	public List<MembersDTO> selectReserch(String member_id,String member_name){
		try{
//			PreparedStatement ps=connection.prepareStatement("select * from members where member_id like '?%' and member_name like '%?%'");
			PreparedStatement ps=connection.prepareStatement("select * from members where member_id like ? and member_name like ?");
//			ps.setString(1,member_id);
//			ps.setString(2,member_name);
			ps.setString(1,member_id+"%");
			ps.setString(2, "%" + member_name +"%"  );
			ResultSet rs=ps.executeQuery();
			List<MembersDTO> result=new ArrayList<MembersDTO>();
			while(rs.next()){
					MembersDTO memberDTO=new MembersDTO();
					memberDTO.setMember_id(rs.getString("member_id"));
					memberDTO.setMember_name(rs.getString("member_name"));
					memberDTO.setTelephone(rs.getString("telephone"));
					memberDTO.setMail(rs.getString("mail"));
					result.add(memberDTO);
				}
			return result;
			}catch(SQLException e){
				throw new RuntimeException(e);
			}
		}

		public List<MembersDTO> selectCertain(String member_id){
			try{
				PreparedStatement ps=connection.prepareStatement("select * from members where member_id=?");
				ps.setString(1,member_id);
				ResultSet rs=ps.executeQuery();
				List<MembersDTO> result=new ArrayList<MembersDTO>();
				while(rs.next()){
				MembersDTO memberDTO=new MembersDTO();
				memberDTO.setMember_id(rs.getString("member_id"));
				memberDTO.setMember_name(rs.getString("member_name"));
				memberDTO.setTelephone(rs.getString("telephone"));
				memberDTO.setMail(rs.getString("mail"));
				result.add(memberDTO);

				}
				return result;
			}catch(SQLException e){
			throw new RuntimeException(e);
	}
	}
}

