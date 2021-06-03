import org.json.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Refunding {
private String start,trainNumber,code,Date;
private String []seats;
private String time;
private int hour,min;



	JSONArray arrayofbooking = JSONUtils.getJSONArrayFromFile("/booking.json");
	JSONObject obj = JSONUtils.getJSONObjectFromFile("/timeTable.json");
	JSONArray jsonArray = obj.getJSONArray("Array");

	public String Refund (String UID, String ticketCode , int people) {
		for (int i = 0; i < arrayofbooking.length(); i++) {
			JSONObject date = arrayofbooking.getJSONObject(i);
			String temporcode = date.getString("code");
			if (temporcode.contentEquals(ticketCode)) {
				code = temporcode;
				JSONArray ticketInfo = date.getJSONArray("ticketInfo");
				start = ticketInfo.getJSONObject(0).getString("start");
				trainNumber = ticketInfo.getJSONObject(0).getString("trainNumber");
				Date = ticketInfo.getJSONObject(0).getString("date");
				for (int m = 0; m <ticketInfo.getJSONObject(0).getJSONArray("seats").length(); m++) {
					seats = new String[ticketInfo.getJSONObject(0).getJSONArray("seats").length()];
					seats[m] = ticketInfo.getJSONObject(0).getJSONArray("seats").getString(m);
					}
				//get seats的array方便之後刪掉位子
				}
		}
		//booking variables : code , start , train number , Date , seats array
		
		for(int j = 0; j < jsonArray.length(); j++) {	
		    JSONObject train = jsonArray.getJSONObject(j);
		    JSONObject timetable = train.getJSONObject("GeneralTimetable");
		    String trainNo = timetable.getJSONObject("GeneralTrainInfo").getString("TrainNo");
		    if (trainNo.contentEquals(trainNumber)) {
		    	JSONArray stopTimes = timetable.getJSONArray("StopTimes");
		    	for(int n = 0; n <stopTimes.length(); n++) {
		    		String stationID = stopTimes.getJSONObject(n).getString("StationID");
		    		if (stationID.contentEquals(start)) {
		    			time = stopTimes.getJSONObject(n).getString("DepartureTime");
		    		}
		    	}
		    }
	    }
		//這個大迴圈的目的是，先在timeTable裡面找到我要的trainNo後，再去找起站對應的stationID
		//都找到後，把departuretime存給time，有了time之後，後面就可以處理半小時退票的問題
		//variables : time
		
		Date currentDate = new Date();
		SimpleDateFormat df = new SimpleDateFormat("MM:dd:hh:mm"); 
		String [] today = df.format(currentDate).split(":");//[0] = month ,[1] = day ,[2] = hour ,[3] = minute
		
		String []DateArray = Date.split("-");//DateArray[1],[2]可以拿來比較月份
		
		String[]DepartureTime = time.split(":");//hour:min
		if (Integer.parseInt(DepartureTime[1]) > 30) {
			hour = Integer.parseInt(DepartureTime[0]);
			min = Integer.parseInt(DepartureTime[1]) - 30;
		}
		else {
			hour = Integer.parseInt(DepartureTime[0]) - 1 ;
			min = Integer.parseInt(DepartureTime[1]) + 30;
		}
		
		if (code.contentEquals(ticketCode)) {
			if(Integer.parseInt(DateArray[1]) >= Integer.parseInt(today[0]) && Integer.parseInt(DateArray[2]) >= Integer.parseInt(today[1])) {
				if(hour >= Integer.parseInt(today[2]) && min >= Integer.parseInt(today[3])) {
					if (seats.length == people) {
						deleteticket();
					}
					else {
						conditionrefund();
					}
				}
				else
					return "退票/修改失敗，需於指定車次開車前半小時";
			}
			else
				return "退票/修改失敗，需於指定車次開車前半小時";
				
			}
		else
			return "退票/修改失敗，此訂位代號不存在\r\n";
					
		
	}
	

}
