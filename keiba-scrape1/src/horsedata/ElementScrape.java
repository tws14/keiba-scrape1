package horsedata;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import horsedata.*;

public class ElementScrape {
		
	
	public void scrape(String url) {
		
				//時間計測
				long start = System.currentTimeMillis();
		
				BufferedWriter bw = null;
	
		try {
				//書き込み用ファイル作成（任意のパス)
				String fp = "/home/user/ドキュメント/horsedata.csv";
				File file = new File(fp);
						bw = new BufferedWriter (new FileWriter(file, true));
						bw.write("日付,開催,天候,R,レース名,頭数,枠番,馬番,着順,騎手,斤量,距離,馬場,タイム,通過,ペース,上がり");
						bw.newLine();
				
		
				
				//URLからテーブル取得
				Document document
						= Jsoup.connect(url).timeout(5000).get();
				Elements rows = document.select("table.db_h_race_results tbody tr");
	
	
				String[] cells = null;
				List<Horse> list = new ArrayList<Horse>();
				
				//セル取得
				for(Element row : rows) {
							
							cells = row.select("td").stream()
								  	.map(Element::text)
								  	.toArray(String[]::new);
					
							
									Horse h = new Horse();
										   h.setDate(cells[0]);
										   h.setWeather(cells[1]);
										   h.setPlace(cells[2]);
										   h.setRace(NumberUtils.toInt(cells[3], 0));
										   h.setRacename(cells[4]);
										   h.setTousuu(NumberUtils.toInt(cells[6], 0));
										   h.setWakuban(NumberUtils.toInt(cells[7], 0));
										   h.setUmaban(NumberUtils.toInt(cells[8], 0));
										   h.setTyakujun(NumberUtils.toInt(cells[11], 0));
										   h.setJockey(cells[12]);
										   h.setHande(NumberUtils.toDouble(cells[13],0));
										   h.setCourse(cells[14]);
										   h.setBaba(cells[15]);
										   h.setTime(cells[17]);
										   h.setTuuka(cells[20]);
										   h.setPace(cells[21]);
										   h.setSanharon(NumberUtils.toDouble(cells[22], 0));
										   
										   list.add(h);
					
				}
				
				//書き込み
				for(Horse h : list) {
					bw.write(h.getDate() + "," + h.getWeather() + "," + h.getPlace() + "," + h.getRace() + "," + h.getRacename()
					          + "," + h.getTousuu() + "," + h.getWakuban() + "," + h.getUmaban() + "," + h.getTyakujun()
					          + "," + h.getJockey() + "," + h.getHande() + "," + h.getCourse() + "," + h.getBaba()
					            + "," + h.getTime() + "," + h.getTuuka() + "," + h.getPace() + "," + h.getSanharon());
					bw.newLine();
				}
					
		} catch(IOException | NumberFormatException e) {
			e.printStackTrace();
			
		} finally {
			
				try {
						if(bw != null) {
							bw.close();
						}
				} catch (IOException e) {
					e.printStackTrace();
					
				}
		     	}
				
		long end = System.currentTimeMillis();
		System.out.println((end - start) + "ms");
		System.out.println(((end - start)/1000) + "秒");
		
		System.out.println("完了");
	}
}
