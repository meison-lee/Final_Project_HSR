import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
		//票種檢查
		/*
		 * 每筆交易至多可預訂10張票，
		 * 不限身份，去回程至多可預訂各5張。
		 */
		
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
		
		/*
		 * 提供預訂當日及未來28日以內之車票。
		 * 訂位開放時間為乘車日(含)前28日凌晨0點開始，
		 * 當日車次之預訂僅受理至列車出發時間前1小時為止
		 */
		
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
		/*
		 * 早鳥優惠僅適用於標準車廂對號座全票。
		 * 早鳥優惠車票，自乘車日（含）前 28 日起開始限量發售，最晚發售至乘車日（含）前 5 日截止。
		 * 愈早訂位者，愈有機會訂得 65 折優惠車票。
		 * 早鳥 65 折銷售完畢即改發售早鳥 8 折，早鳥 8 折銷售完畢即改發售早鳥 9 折，早鳥 9 折銷售完畢即提前截止並改發售原價車票。
		 */
			JSONObject earlyDiscount = JSONUtils.getJSONObjectFromFile("/earlyDiscount.json");
			JSONArray EDTrains = earlyDiscount.getJSONArray("DiscountTrains");
			
			//把limitdate改為今日後五天(28-23)
			Limitdate.add(Calendar.DAY_OF_MONTH, -23);
			
			//去程
			ArrayList<Double> DEDdiscount = null;
			
			//確認是否於五日前
			if (Limitdate.after(Dedate)) {
				System.out.println("去程日期列車已不提供早鳥票訂購");
			}
			else {
				//外圈為去程的JSONArray
				for(int j = 0; j < Davailable.length(); j++) {
					//內圈1為所有ED的JSONArray
					ED: for(int i = 0; i < EDTrains.length(); i++) {
						//若找到對應的列車
						if (TrainNoofED(EDTrains, i) == TrainNoofAv(Davailable, j)) {
							//將該列車的ED資訊提出
							JSONArray thatdayED = EDTrains.getJSONObject(i).getJSONObject("ServiceDayDiscount").getJSONArray(WoDD);
							
							//提出該星期的折價資料並查詢是否還有剩票，並將折價存進REDdiscount中，並跳出ED迴圈，若都沒票則存入1.0(無折價)，同樣跳出ED迴圈
							for (int k = 0; k < thatdayED.length(); k++) {
								if (thatdayED.getJSONObject(k).getInt("tickets") > 0) {
									DEDdiscount.add(thatdayED.getJSONObject(k).getDouble("0.65"));
									break ED;
								}
								else if (k == thatdayED.length()) {
									DEDdiscount.add(1.0);
									break ED;
								}
								else;
							}
						}
						else;
					}
				}
				//搜尋班次的折價與以及是否還有位子
			}
			
			
			
			//回程 先確認是否有回程
			if (Rdate != null) {
				ArrayList<Double> REDdiscount = null;
				
				//確認是否於五日前
				if (Limitdate.after(Redate)) {
					System.out.println("回程日期列車已不提供早鳥票訂購");
				}
				else {
					//外圈為回程的JSONArray
					for(int j = 0; j < Ravailable.length(); j++) {
						//內圈1為所有ED的JSONArray
						ED: for(int i = 0; i < EDTrains.length(); i++) {
							//若找到對應的列車
							if (TrainNoofED(EDTrains, i) == TrainNoofAv(Ravailable, j)) {
								//將該列車的ED資訊提出
								JSONArray thatdayED = EDTrains.getJSONObject(i).getJSONObject("ServiceDayDiscount").getJSONArray(WoDR);
								
								//提出該星期的折價資料並查詢是否還有剩票，並將折價存進REDdiscount中，並跳出ED迴圈，若都沒票則存入1.0(無折價)，同樣跳出ED迴圈
								for (int k = 0; k < thatdayED.length(); k++) {
									if (thatdayED.getJSONObject(k).getInt("tickets") > 0) {
										REDdiscount.add(thatdayED.getJSONObject(k).getDouble("0.65"));
										break ED;
									}
									else if (k == thatdayED.length()) {
										REDdiscount.add(1.0);
										break ED;
									}
									else;
								}
							}
							else;
						}
					}
					//搜尋班次的折價與以及是否還有位子
				}
			}
			else;
		
		//大學生票 (只有折扣)
		/*
		 * 大學生優惠（5折/75折/88折）票恕無法與其他優惠合併使用。
		 */
		
			JSONObject universityDiscount = JSONUtils.getJSONObjectFromFile("/universityDiscount.json");
			JSONArray UDTrains = universityDiscount.getJSONArray("DiscountTrains");
			
			int Nofstudent = studentT;
			
		//優待票 (各類價格)
		
		//整車正常票(分一般與商務)
			
			
		}
		
		/*
		 * 座位喜好功能係依列車訂位概況媒合您期望的座位，如該車次已無合適座位時，為讓您仍可順利完成訂票作業，將由系統自動配置座位。座位喜好功能僅適用於單人乘客。
		 */
		
		
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
		 * 1. 建一個JSONArray用來儲存符合條件的列次資料
		 * 2. 檢查各種條件 (各個票種, AorW, 時間)，將符合條件的班次存入list
		 * 3. 透過此JSONArray輸出各班次資訊
		 */
		
		/* 列出該時段符合條件的車次*/
		/* 失敗，因為該時段車次座位已售罄*/
		
		return null;
	}
	
	
	/**
	 * 此method方便找查Ravailable中的TrainNo
	 * 
	 * @param Ravailable
	 * @param which 第幾個
	 * @return 該位置的TrainNo
	 */
	
	public static String TrainNoofAv(JSONArray Ravailable, int which) {
		return Ravailable.getJSONObject(which).getJSONObject("GeneralTimetable").getJSONObject("GeneralTimeInfo").getString("TrainNo");
	}
	
	/**
	 * 此method方便找查earlyDiscount中的TrainNo
	 * 
	 * @param EDTrains
	 * @param which 第幾個
	 * @return 該位置的TrainNo
	 */
	
	public static String TrainNoofED(JSONArray EDTrains, int which) {
		return EDTrains.getJSONObject(which).getString("TrainNo");
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
