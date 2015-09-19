package test;


import java.sql.*;
import java.util.*;

import dto.BusesDTO;

import dao.BusesDAO;

import util.DBConnectUtil;

public class BusesDAOTest {

	public static void main(String[] args) {
		//以下select文のテスト
		Connection con=DBConnectUtil.getConnection();
		try{
		BusesDAO busDAO=new BusesDAO(con);
		showAllBus(busDAO);
		con.commit();
		} catch (Throwable t) {
			t.printStackTrace();
			if(con!=null){
				try{
					con.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		System.out.println("+++++++++");
		System.out.println("+++++++++");


		//以下insert文のテスト
		try{
		BusesDAO busDAO=new BusesDAO(con);
		BusesDTO busDTO=new BusesDTO();
		busDTO.setSeat_colum(2);
		busDTO.setSeat_row(7);
		busDTO.setNp_name("広島");
		busDTO.setNp_class_no(9);
		busDTO.setNp_hiragana("ち");
		busDTO.setNp_no1(6);
		busDTO.setNp_no2(5);

		int insertCount=busDAO.insert(busDTO);
		System.out.println(insertCount+"件登録しました");
		showAllBus(busDAO);
		con.commit();
		} catch (Throwable t) {
			t.printStackTrace();
			if(con!=null){
				try{
					con.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}

		System.out.println("+++++++++");
		System.out.println("+++++++++");

		//以下delete文のテスト

		try{
		BusesDAO busDAO=new BusesDAO(con);
		String test_id="BU0002";

		int deleteCount=busDAO.deleteCertain(test_id);
		System.out.println(deleteCount+"件削除しました");
		showAllBus(busDAO);
		con.commit();
		} catch (Throwable t) {
			t.printStackTrace();
			if(con!=null){
				try{
					con.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}




	}

	private static void showAllBus(BusesDAO busesDAO) {
		// TODO Auto-generated method stub
		List<BusesDTO> dtos=busesDAO.selectAll();
		for (BusesDTO busDTO : dtos){
			String result ="";
			result+=busDTO.getBus_id();
			result+=":";
			result+=busDTO.getSeat_colum();
			result+=":";
			result+=busDTO.getSeat_row();
			result+=":";
			result+=busDTO.getNp_name();
			result+=":";
			result+=busDTO.getNp_class_no();
			result+=":";
			result+=busDTO.getNp_hiragana();
			result+=":";
			result+=busDTO.getNp_no1();
			result+=":";
			result+=busDTO.getNp_no2();
			System.out.println(result);
		}


	}

}
