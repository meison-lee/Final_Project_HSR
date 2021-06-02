import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.*;

public class Booking {
	JSONObject obj = JSONUtils.getJSONObjectFromFile("/timeTable.json");
	JSONArray jsonArray = obj.getJSONArray("Array");
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HHmm");
	
	public String NormalBooking(String UID, String Ddate, String Rdate, // Ddate出發時間, Rdate返程時間
			String SStation, String DStation, //S始站, D終站
			int normalT, int concessionT, int studentT, //一般票, 優待票, 大學生票
			int AorW, boolean BorS) // 走道or靠窗, 商務或標準車廂
	{
		//處理方向
		
		//去程方向(記去程就好)
		int Direction;
		
		if (Integer.valueOf(SStation) < Integer.valueOf(DStation)) {
			Direction = 0; //南向
		}
		else{
			Direction = 1; //北向
		}
		
		//處理時間
		
		//Calendar用來操作

		//今天時間
		long current = System.currentTimeMillis();
		Date ttoday = new Date(current);
		Calendar today = Calendar.getInstance();
		today.setTime(ttoday);
		
		//去程
		Date Dedate  = null; //Date object
		String WoDD  = null; //week of day
		String Dtime = null; //time
		
		if(Ddate != null) {
			try {
				Dedate = sdf.parse(Ddate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			WoDD = getWeekofDay(Dedate);
			
			Dtime = Ddate.substring(11);
		}
		
		//回程
		Date Redate  = null; //Date object
		String WoDR  = null; //week of day
		String Rtime = null; //time
		
		if(Rdate != null) {
			try {
				Redate = sdf.parse(Rdate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			WoDR = getWeekofDay(Redate);
			
			Rtime = Rdate.substring(11);
		}
		
		//日期期限 (今日的30天之後)
		Calendar Limitdate = today;
		Limitdate.add(Calendar.DAY_OF_MONTH, 28);
		
		//確認當日時間是否可供訂票
		if(Limitdate.before(Dedate)) {
			if(Limitdate.before(Redate)) {
				return "去回程列車皆尚未開放訂票";
			}
			else {
				return "去程列車皆尚未開放訂票";
			}
		}
		else {
			//無問題
		}

		//將符合條件的列車編號做成JSONArray
		JSONArray Davailable = null;
		JSONArray Ravailable = null;
		
		/*    確認順序為：
		 * 1. 確認方向 Direction
		 * 2. 確認星期 DayofWeek
		 * 3. 確認是否符合路線(有無抵達始站與終站) Stations
		 * 4. 確認始站出發時間 Date
		 */
		
		for(int i = 0; i < jsonArray.length(); i++) {
			
			JSONObject train = jsonArray.getJSONObject(i);
			JSONObject timetable = train.getJSONObject("GeneralTimetable");
			
			//去程
			if ((timetable.getJSONObject("GeneralTrainInfo").getInt("Direction") == Direction)
				//確認方向
				&& (timetable.getJSONObject("ServiceDay").getInt(WoDD) != 1) 
				//確認星期
				&& (trainroutehas(train, SStation, DStation)) 
				//確認路線
				&& (dparturetime(Dtime, SStation, timetable.getJSONArray("StopTimes")))) 
				//確認出發時間
			{
				Davailable.put(train);
			}
			
			//回程
			if ((timetable.getJSONObject("GeneralTrainInfo").getInt("Direction") != Direction)
				//確認方向
				&& (timetable.getJSONObject("ServiceDay").getInt(WoDR) != 1) 
				//確認星期
				&& (trainroutehas(train, SStation, DStation)) 
				//確認路線
				&& (dparturetime(Rtime, DStation, timetable.getJSONArray("StopTimes")))) 
				//確認出發時間
			{
				Ravailable.put(train);
			}
		}
		
		//處理票量問題
		
		//商務則沒有各種優待票
		if (BorS == false) {
			
		//早鳥票 (有折扣跟票數) (票數要處理)
			JSONObject earlyDiscount = JSONUtils.getJSONObjectFromFile("/earlyDiscount.json");
			JSONArray EDTrains = earlyDiscount.getJSONArray("DiscountTrains");
			
			//把limitdate改為今日後五天(28-23)
			Limitdate.add(Calendar.DAY_OF_MONTH, -23);
			
			//去程
			if (Limitdate.after(Dedate)) {
				System.out.println("去程日期列車已不提供早鳥票訂購");
			}
			else {
				//搜尋班次的折價與以及是否還有位子
			}
			
			//回程
			if (Limitdate.after(Redate)) {
				System.out.println("回程日期列車已不提供早鳥票訂購");
			}
			else {
				//搜尋班次的折價與以及是否還有位子
			}
		
		//大學生票 (只有折扣)
			JSONObject universityDiscount = JSONUtils.getJSONObjectFromFile("/universityDiscount.json");
			JSONArray UDTrains = universityDiscount.getJSONArray("DiscountTrains");
			
			int Nofstudent = studentT;
			
		//優待票 (各類價格)
		
		//整車正常票(分一般與商務)
			
			
		}
		
		
		
		//excel檔?
		
		for(int i = 0; i < Davailable.length(); i++) {
			System.out.println(Davailable.get(i));
		}
		
		for(int i = 0; i < Ravailable.length(); i++) {
			System.out.println(Ravailable.get(i));
		}
		
		//輸出錯誤結果
		
		if ((normalT+concessionT+studentT > 10) || ((Rdate != null)&&(normalT+concessionT+studentT > 5))) {
			return "失敗，因訂單預定過多車票(每筆最多10張，來回車票獨立計算)";
		}
		
		if (Dedate.after(null) || Redate.after(null)) {
			return "失敗，因尚未能預約";
		}
		
		//輸出搜尋結果
		
		System.out.println("去程列車如下：\n");
			
		for(int i = 0; i < Davailable.length(); i++) {
			System.out.println(Davailable.get(i));
		}
		
		System.out.println("回程列車如下：\n");
		
		for(int i = 0; i < Ravailable.length(); i++) {
			System.out.println(Ravailable.get(i));
		}
		
		/*下面兩個要參考我們怎麼處理資料
		 * 大致上如下
		 * 1. 建list儲存班次編號
		 * 2. 檢查各種條件 (各個票種, AorW, 時間)，將符合條件的班次存入list
		 * 3. 透過list輸出各班次資訊
		 */
		
		/* 列出該時段符合條件的車次*/
		/* 失敗，因為該時段車次座位已售罄*/
		
		return null;
	}
	
	/**
	 * @param time 輸入時間
	 * @param SStation 起站
	 * @param StopTimes 該列次停站表
	 * @return 若該列次該站的出站時間 在 輸入時間 後 則回傳true 反之回傳false
	 */
	
	private boolean dparturetime(String time, String SStation, JSONArray StopTimes) {
		for (int i=0 ; i < StopTimes.length(); i++) {
			if (StopTimes.getJSONObject(i).getString("StationID") == SStation){
				String DepartureTime = StopTimes.getJSONObject(i).getString("DepartureTime").replace(":", "");
				if (Integer.valueOf(DepartureTime) >= Integer.valueOf(time)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * @param train 該列次的JSONobject
	 * @param SStation 始站
	 * @param DStation 終站
	 * @return true 若路線正確 false 反之
	 */
	
	private boolean trainroutehas(JSONObject train, String SStation, String DStation) {
		boolean S = false;
		boolean D = false;
		
		for (int j = 0; j < train.getJSONArray("StopTimes").length(); j++) {
			String station = train.getJSONArray("StopTimes").getJSONObject(j).getString("StationID");
			if (station	== SStation) {
				S = true;
			}
			if (station	== DStation) {
				D = true;
			}
		}

		if (S && D) {
			return true;
		}
		else return false;
	}
	
	/**
	 * @param date
	 * @return 該日期的星期
	 */
	
	private String getWeekofDay(Date date) {
		
		String[] weekDays = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        
		return weekDays[w];
	}
}
