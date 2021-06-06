import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.json.*;

public class runner {
	
	public void JSONArraydeleter(String writer, String filelocation, int i) throws IOException {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(writer));			//filewriter
			
			JSONArray dataJSON = JSONUtils.getJSONArrayFromFile(filelocation);      //將該位置的檔案複製近來變成JSONArray(or JSONObject)
			//dataJSON.remove(i);														//移除該複製物件的第0項
			JSONObject ticket = dataJSON.getJSONObject(i).getJSONArray("ticketInfo").getJSONObject(0); //到ticketInfo
			ticket.getJSONArray("seats").clear();                                          //清除array資料(seats還是會在，跟remove不同)
			for (int j = 0;j<5;j++) {
			    ticket.getJSONArray("seats").put(j,"0000");                                //for迴圈 加入指定數量的資料
			}
			String ws = dataJSON.toString();		//將dataJSON轉為String type
			System.out.println(ws);					//將ws印出來看看
			bw.write(ws);
			bw.flush();
			bw.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		
		JSONArray timetable = JSONUtils.getJSONArrayFromFile("/timetable.json");
		JSONObject earlyDiscount = JSONUtils.getJSONObjectFromFile("/earlyDiscount.json");
		
		System.out.println(timetable.length());
		System.out.println(earlyDiscount.getJSONArray("DiscountTrains").length());
		
		JSONArray arrayofbooking = JSONUtils.getJSONArrayFromFile("/booking.json");
		System.out.println(arrayofbooking.get(0));
		System.out.println(arrayofbooking.get(1));
		
		JSONArray train = JSONUtils.getJSONArrayFromFile("/timeTable.json");
		
		
		runner tester = new runner();
		
		String writer = "Data/booking.json";
		String filelocation = "booking.json";
		int which = 0;
		
		tester.JSONArraydeleter(writer, filelocation, which);
		
	}
}
