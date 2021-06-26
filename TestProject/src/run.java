import java.io.IOException;

public class run {

	public static void main(String[] args) {
		Booking runner = new Booking();
		
		try {
			System.out.println(runner.Search("2021-07-20 1400", "2021-07-23 1200", "1047", "1060", 2, 1, 1, 0, false));
			runner.SearchSelect(2, 2);
			System.out.println(runner.Book("A123456789"));
			
			runner.TrainnoSearchSelect("0829","0846","2021-07-20 1400", "2021-07-23 1200", "1047", "1060", 2, 1, 1, 0, false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BookingExceptions e) {
			System.out.println("error message");
		}
	}

}
