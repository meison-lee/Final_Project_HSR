import org.json.*;

public class runner {

	public static void main(String[] args) {
		JSONArray arrayofbooking = JSONUtils.getJSONArrayFromFile("/booking.json");
		System.out.println(arrayofbooking.get(0));
		System.out.println(arrayofbooking.get(1));
	}

}
