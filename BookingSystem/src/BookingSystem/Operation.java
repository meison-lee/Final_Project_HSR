package BookingSystem;

import java.util.Date; //使用Date處理訂票時間

public class Operation {
	
	public String Booking(String UID, Date Ddate, Date Rdate, // Ddate出發時間, Rdate返程時間
			String SStation, String DStation, //S始站, D終戰
			int normalT, int concessionT, int studentT, //一般票, 優待票, 大學生票
			int AorW, boolean BorS) // 走道or靠窗, 商務或標準車廂
	{
		if ((normalT+concessionT+studentT > 10) || ((Rdate != new Date(0))&&(normalT+concessionT+studentT > 5))) {
			return "失敗，因訂單預定過多車票(每筆最多10張，來回車票獨立計算)";
		}
		
		if (Ddate.after(null) || Rdate.after(null)) {
			return "失敗，因尚未能預約";
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
}
