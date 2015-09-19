package test;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;

import util.DBConnectUtil;
import dao.LinesDAO;
import dto.LinesDTO;

public class LinesDAOTest {
	static SimpleDateFormat dformat=new SimpleDateFormat("yyyy-MM-dd");

	public static void main(String[] args) {
		//以下select文のテスト
		Connection con=DBConnectUtil.getConnection();
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

		//以下insert文のテスト

	try{
		LinesDAO lineDAO=new LinesDAO(con);
		LinesDTO lineDTO=new LinesDTO();
		lineDTO.setArrival_stop_id("S27002");
		lineDTO.setDeparture_stop_id("S13001");
		lineDTO.setFee(5000);
		lineDTO.setBus_id("BU0095");
		lineDTO.setStart_date(Date.valueOf("2002-06-19"));
		lineDTO.setEnd_date(Date.valueOf("2009-07-02"));
		lineDTO.setStart_time("0900");
		lineDTO.setEnd_time("1600");

		int insertCount=lineDAO.insert(lineDTO);
		System.out.println(insertCount+"件登録しました");
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
//以下delete文のテスト
		try{
			LinesDAO lineDAO=new LinesDAO(con);
			String line_id="R0001";
			int deleteCount=lineDAO.deleteCertain(line_id);
			System.out.println(deleteCount+"件削除しました");
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
//以下selectCertainのテスト
		try{
		LinesDAO lineDAO=new LinesDAO(con);
		Date day=Date.valueOf("2005-01-01");
		showAllCertainLine(lineDAO,day);

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
//以下update文のテスト
		try{
		LinesDAO lineDAO=new LinesDAO(con);
		LinesDTO lineDTO=new LinesDTO();
		lineDTO.setArrival_stop_id("S01001");
		lineDTO.setDeparture_stop_id("S12001");
		lineDTO.setFee(9000);
		lineDTO.setBus_id("BU0090");
		lineDTO.setStart_date(Date.valueOf("2008-07-26"));
		lineDTO.setEnd_date(Date.valueOf("2010-09-09"));
		lineDTO.setStart_time("1000");
		lineDTO.setEnd_time("1700");
		lineDTO.setLine_id("R0024");

		int updateCount=lineDAO.update(lineDTO);
		System.out.println(updateCount+"件更新しました。");

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

		private static void showAllCertainLine(LinesDAO lineDAO,Date day) {
			SimpleDateFormat dformat=new SimpleDateFormat("yyyy-MM-dd");
			List<LinesDTO> dtos=lineDAO.selectCertain_day(day);
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
	}


