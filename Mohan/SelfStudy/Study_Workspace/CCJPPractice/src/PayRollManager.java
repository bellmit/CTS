import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;

import util.Validator;
import bean.EmployeeVO;
import exception.PayRollProcessingException;

public class PayRollManager {

	private Map<Integer, Map> emplMap = new HashMap<Integer, Map>();

	public Map<Integer, Map> getEmployeeSalaryDetails(String file1, String file2) {
		Map<Integer, EmployeeVO> empMap;
		try {
			Scanner sc = new Scanner(new File(file1));
			empMap = readEmployeeFile(sc);
			System.out.println("EMP SIZE>>>>" + empMap.size());

			Map<Integer, Object> output = readpayRollFile(file2, empMap);
			printOutputData(output);
		} catch (Exception e) {
			System.err.println("Error" + e.toString());
		}

		return emplMap;
	}

	private Map<Integer, Object> readpayRollFile(String file2,
			Map<Integer, EmployeeVO> empMap) {
		// TODO Auto-generated method stub
		Map<Integer, Object> mainMap = new HashMap<Integer, Object>();
		Map<Character, Object> secndMap = new HashMap<Character, Object>();

		Map<Integer, ArrayList<EmployeeVO>> dupSalMap = new HashMap<Integer, ArrayList<EmployeeVO>>();
		ArrayList<EmployeeVO> empList = new ArrayList<EmployeeVO>();
		Map<String, Integer> sal;
		List<String> resignSal = new ArrayList<String>();

		try {
			Scanner sc = new Scanner(new File(file2));

			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				// System.out.println(line);
				StringTokenizer st = new StringTokenizer(line, ",");
				if (st.countTokens() == 3 || st.countTokens() == 4) {
					String paySlipNo = st.nextToken();
					Double basic = Double.valueOf(st.nextToken());
					int noOfDaysLeave = Integer.valueOf(st.nextToken());
					String resignation = null;
					if (st.hasMoreTokens())
						resignation = st.nextToken();

					if (Validator.isPaySlipNumberValid(paySlipNo)) {
						st = new StringTokenizer(paySlipNo, "/");
						Integer empid = Integer.valueOf(st.nextToken());
						String month = st.nextToken();
						int uniqueNo = Integer.valueOf(st.nextToken());
						if (empMap.containsKey(empid)) {
							EmployeeVO empVo = empMap.get(empid);
							// System.out.println(empid);
							empVo.setNumberOfLeaveDays(empVo
									.getNumberOfLeaveDays() + noOfDaysLeave);
							Double salary = caculateSalary(basic, noOfDaysLeave);
							// System.out.println("Salary for"+paySlipNo+"is >>"+salary);

							if (empVo.getSalaryDetails() != null)
								sal = empVo.getSalaryDetails();
							else
								sal = new HashMap<String, Integer>();
							// sal.put(paySlipNo, salary.intValue());
							// empVo.setSalaryDetails(sal);
							if (!Validator.isEmpHasSalaryDuplicate(sal,
									paySlipNo)) {

								if (empVo.getResignationDate() != null) {
									resignSal.add(String.valueOf(empid));
									// System.out.println("Resign");
									// throw new
									// PayRollProcessingException("Salary Date appearing after the resignation date");
								}
								if (resignation != null
										&& !"".equals(resignation)) {
									empVo.setResignationDate(new SimpleDateFormat(
											"dd-MM-yyyy").parse(resignation));
								}

								sal.put(paySlipNo, salary.intValue());
								empVo.setSalaryDetails(sal);
							} else {
								System.out.println("Duo");
								// EmployeeVO dupEmpVo=new EmployeeVO();
								// copyEmployee(dupEmpVo, empVo);
								sal.put(paySlipNo, salary.intValue());
								empVo.setSalaryDetails(sal);
								if (dupSalMap.containsKey(empid)) {
									empList = dupSalMap.get(empid);
									empList.add(empVo);
								} else {
									empList = new ArrayList<EmployeeVO>();
									empList.add(empVo);

								}

								dupSalMap.put(empid, empList);
								// throw new
								// PayRollProcessingException("Duplicate Salary Entry");
							}

							empMap.put(empid, empVo);
						} else
							throw new PayRollProcessingException(
									"Employee not found in File");
					} else
						throw new PayRollProcessingException(
								"Invalid Payslip format");
				} else {
					throw new PayRollProcessingException(
							"Payroll file>>>Invalid File format");
				}
			}

			// Updating Main Map;
			secndMap.put('D', dupSalMap);
			System.out.println(dupSalMap.keySet());
			secndMap.put('S', resignSal.toArray());
			mainMap.put(1, empMap);
			mainMap.put(2, secndMap);

		} catch (PayRollProcessingException e) {
			// TODO: handle exception
			if ("Duplicate Salary Entry".equalsIgnoreCase(e.getMessage())) {
				System.out.println("Duplicate size" + dupSalMap.size());
			}
			e.printStackTrace();
		}

		catch (Exception e) {
			System.out.println("ee" + e.toString());
			e.printStackTrace();
		}
		return mainMap;
	}

	/*
	 * private void copyEmployee(EmployeeVO dupEmpVo, EmployeeVO empVo) { //
	 * TODO Auto-generated method stub
	 * dupEmpVo.setDepartmentCode(empVo.getDepartmentCode());
	 * dupEmpVo.setEmailID(emailID) dupEmpVo.setEmployeeID(employeeID);
	 * dupEmpVo.setEmployeeName(employeeName);
	 * dupEmpVo.setJoiningDate(joiningDate);
	 * dupEmpVo.setMobileNumber(mobileNumber);
	 * 
	 * }
	 */

	private static Map<Integer, EmployeeVO> readEmployeeFile(Scanner sc)
			throws PayRollProcessingException, Exception {

		EmployeeVO empvo;
		Map<Integer, EmployeeVO> empMap = new TreeMap<Integer, EmployeeVO>();
		while (sc.hasNextLine()) {
			String string = sc.nextLine();
			// System.out.println(string);
			StringTokenizer st = new StringTokenizer(string, ",");

			if (st.countTokens() != 6)
				throw new PayRollProcessingException("Invalid File");
			else {
				empvo = new EmployeeVO();
				String token;
				for (int i = 0; i < 6; i++) {
					token = st.nextToken();
					if (token == null || token.equals("")) {
						throw new PayRollProcessingException(
								"Mandatory field Empty");
					}
					switch (i) {
					case 0: {
						if (empMap.containsKey(Integer.valueOf(token)))
							throw new PayRollProcessingException(
									"Duplicate Employee Id");
						else
							empvo.setEmployeeID(Integer.valueOf(token));

					}
						break;
					case 1:
						empvo.setEmployeeName(token);
						break;
					case 2:
						empvo.setDepartmentCode(token.charAt(0));
						break;
					case 3: {

						if (!Validator.isThisDateValid(token, "MM-dd-yyyy"))
							throw new PayRollProcessingException(
									"Invalid Date Format");
						empvo.setJoiningDate(new SimpleDateFormat("MM-dd-yyyy")
								.parse(token));
					}
						break;
					case 4: {
						// System.out.println("Number"+token);
						if (Validator.isNumber(token) && token.length() == 10) {
							empvo.setMobileNumber(Long.parseLong(token));
						} else
							throw new PayRollProcessingException(
									"Invalid mobile Number format");
					}
						break;
					case 5: {
						if (Validator.isMailValid(token))
							empvo.setEmailID(token);
						else
							throw new PayRollProcessingException(
									"Invalid Email");
					}
					}
				}
				empMap.put(empvo.getEmployeeID(), empvo);

			}
		}

		return empMap;
	}

	private Double caculateSalary(Double basic, int noOfDaysLeave) {
		// TODO Auto-generated method stub
		double hra = 0;
		if (basic < 20000)
			hra = 75 * basic / 100;
		else
			hra = 50 * basic / 100;

		return (basic + hra - (basic / 30) * noOfDaysLeave);
	}

	private void printOutputData(Map<Integer, Object> output) {
		// TODO Auto-generated method stub
		System.out.println("Printing Employee Details");
		Map<Integer, EmployeeVO> firstMap = (Map<Integer, EmployeeVO>) output
				.get(1);
		System.out.println("Employee List >>" + firstMap.keySet());
		System.out
				.println("Emp ID \t Name \t Dept code \t Joining Date \t Resignation date");
		for (Entry<Integer, EmployeeVO> entry : firstMap.entrySet()) {
			// System.out.println("Details of employee :"+empid);
			EmployeeVO empvo = entry.getValue();
			System.out.println(empvo.getEmployeeID() + "\t"
					+ empvo.getEmployeeName() + "\t"
					+ empvo.getDepartmentCode() + "\t" + empvo.getJoiningDate()
					+ "\t" + empvo.getResignationDate());
			//System.out.println("\t" + empvo.getSalaryDetails().keySet());
			System.out.println("\t PaySlipno\t\t Salary");
			for (Entry<String, Integer>  payslip : empvo.getSalaryDetails().entrySet()) {
				Integer i = payslip.getValue();
				System.out.println("\t" + payslip + "\t" + i);
			}

		}
		System.out.println("Duplicate Employee List >>");
		Map<Character, Object> secondMap = (Map<Character, Object>) output
				.get(2);
		Map<Integer, Object> mp = (Map<Integer, Object>) secondMap.get('D');
		System.out.println(mp.keySet());
		System.out.println("Employee After Resignation List >>");
		Object[] s = (Object[]) secondMap.get('S');
		System.out.println((Arrays.toString(s)));

	}
}
