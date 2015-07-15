import java.util.Date;


public class TestSQLDate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(System.getProperty("java.version"));
		
		Date date=new Date(System.currentTimeMillis());
		System.out.println(date.toString());
		
	}

}
