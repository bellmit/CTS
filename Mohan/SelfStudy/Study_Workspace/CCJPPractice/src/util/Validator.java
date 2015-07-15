package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class Validator {

	public static boolean isThisDateValid(String dateToValidate,
			String dateFromat) {

		if (dateToValidate == null) {
			return false;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
		sdf.setLenient(false);

		try {

			// if not valid, it will throw ParseException
			Date date = sdf.parse(dateToValidate);
			// System.out.println(date);

		} catch (ParseException e) {

			e.printStackTrace();
			return false;
		}

		return true;
	}

	public static boolean isNumber(String token) {
		// TODO Auto-generated method stub
		try {
			if (!(token.startsWith("9") || token.startsWith("8") || token
					.startsWith("7")))
				return false;
			long i = Long.parseLong(token);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public static boolean isMailValid(String token) {
		// TODO Auto-generated method stub
		Pattern p = Pattern
				.compile("[a-z]+[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*@+[a-zA-Z]{5,}+(\\.(com|in))$");
		// System.out.println("SS"+p.matcher("aBa.ba11@aaaaaa.com").matches());
		if (p.matcher(token).matches())
			return true;
		return false;
	}

	public static boolean isPaySlipNumberValid(String paySlipNo) {
		// TODO Auto-generated method stub
		StringTokenizer st = new StringTokenizer(paySlipNo, "/");
		if (st.countTokens() == 3)
			return true;
		return false;
	}

	public static boolean isEmpHasSalaryDuplicate(Map<String, Integer> sal,
			String paySlipNo) {
		// TODO Auto-generated method stub
		Set<String> keySet = sal.keySet();
		if (keySet.contains(paySlipNo))
			return true;
		StringTokenizer st;
		for (String string : keySet) {
			st = new StringTokenizer(paySlipNo, "/");
			Integer empid = Integer.valueOf(st.nextToken());
			int month;
			try {
				month = new SimpleDateFormat("dd-MM-yyyy")
						.parse(st.nextToken()).getMonth();
				st = new StringTokenizer(string, "/");
				st.nextToken();

				if (month == new SimpleDateFormat("dd-MM-yyyy").parse(
						st.nextToken()).getMonth()) {
					System.out.println("paylsip");
					return true;
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return false;
	}

}
