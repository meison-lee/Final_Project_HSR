import org.json.*;

public class runner {

	public static void main(String[] args) {
		JSONArray arrayofbooking = JSONUtils.getJSONArrayFromFile("/booking.json");
		System.out.println(arrayofbooking.get(0));
		System.out.println(arrayofbooking.get(1));
		
		JSONArray train = JSONUtils.getJSONArrayFromFile("/timeTable.json");
		
		for (int i = 0; i < train.length(); i++) {
			System.out.println(train.getJSONObject(i));
		}
		if(train.getJSONObject(0).getJSONObject("GeneralTimetable").getJSONObject("ServiceDay").getInt("Monday") == 1) {
			System.out.println(train.getJSONObject(0).getJSONObject("GeneralTimetable").getJSONObject("GeneralTrainInfo").get("TrainNo"));
		}
	}

}
