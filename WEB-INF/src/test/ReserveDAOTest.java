package test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import util.DBConnectUtil;
import dao.LinesDAO;
import dao.ReserveDAO;
import dao.StopsDAO;
import dto.LinesDTO;
import dto.ReserveDTO;
import dto.StopsDTO;

public class ReserveDAOTest {
	static SimpleDateFormat dformat=new SimpleDateFormat("yyyy-MM-dd");

	public static void main(String[] args) {
		//以下selectStop文のテスト

		Connection con=DBConnectUtil.getConnection();
		try{
			StopsDAO stopsDAO=new StopsDAO(con);
			showAllStop(stopsDAO);
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


	//以下selectLine文のテスト
	try{
		LinesDAO lineDAO=new LinesDAO(con);
		showAllLine(lineDAO);
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


	//以下selectReserve文のテスト
	try{
		ReserveDAO reserveDAO=new ReserveDAO(con);
		showAllCertainDateLine(reserveDAO, Date.valueOf("2010-05-25"), "R0040");
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



	try{
		ReserveDAO reserveDAO=new ReserveDAO(con);
		showAllCertainDate(reserveDAO, Date.valueOf("2014-12-08"));
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

	}



	private static void showAllStop(StopsDAO stopsDAO){

		List<StopsDTO> dtos=stopsDAO.selectAll();
		for (StopsDTO stopsDTO : dtos){
			String result ="";
			result+=stopsDTO.getStop_id();
			result+=":";
			result+=stopsDTO.getPrefecture();
			result+=":";
			result+=stopsDTO.getStop_name();
			System.out.println(result);
		}
	}

	private static void showAllLine(LinesDAO lineDAO) {
		SimpleDateFormat dformat=new SimpleDateFormat("yyyy-MM-dd");
		List<LinesDTO> dtos=lineDAO.selectAll();
		for (LinesDTO lineDTO : dtos){
			String result ="";
			result+=lineDTO.getLine_id();
			result+=":";
			result+=lineDTO.getArrival_stop_id();
			result+=":";
			result+=lineDTO.getDeparture_stop_id();
			result+=":";
			result+=lineDTO.getFee();
			result+=":";
			result+=lineDTO.getBus_id();
			result+=":";
			result+=dformat.format(lineDTO.getStart_date());
			result+=":";
			result+=dformat.format(lineDTO.getEnd_date());
			result+=":";
			result+=lineDTO.getStart_time();
			result+=":";
			result+=lineDTO.getEnd_time();
			System.out.println(result);
		}
	}

	private static void showAllCertainDateLine(ReserveDAO reserveDAO, Date reserve_date,String line_id) {
		SimpleDateFormat dformat=new SimpleDateFormat("yyyy-MM-dd");
		List<ReserveDTO> dtos= reserveDAO.selectCertainDateLine(reserve_date , line_id);
		for (ReserveDTO reserveDTO : dtos){
			String result ="";
			result+=reserveDTO.getReserve_id();
			result+=":";
			result+=reserveDTO.getMember_id();
			result+=":";
			result+=reserveDTO.getLine_id();
			result+=":";
			result+=reserveDTO.getSeat_number();
			result+=":";
			result+=dformat.format(reserveDTO.getReserve_date());
			result+=":";
			System.out.println(result);
		}
	}

	private static void showAllCertainDate(ReserveDAO reserveDAO, Date reserve_date) {
		SimpleDateFormat dformat=new SimpleDateFormat("yyyy-MM-dd");
		List<ReserveDTO> dtos= reserveDAO.selectCertainDate(reserve_date);
		for (ReserveDTO reserveDTO : dtos){
			String result ="";
			result+=reserveDTO.getReserve_id();
			result+=":";
			result+=reserveDTO.getMember_id();
			result+=":";
			result+=reserveDTO.getLine_id();
			result+=":";
			result+=reserveDTO.getSeat_number();
			result+=":";
			result+=dformat.format(reserveDTO.getReserve_date());
			result+=":";
			System.out.println(result);
		}
	}

}

