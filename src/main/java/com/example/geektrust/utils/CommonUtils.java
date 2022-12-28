package com.example.geektrust.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.example.geektrust.constants.AppConstants;
import com.example.geektrust.exception.ProgramException;
import com.example.geektrust.model.Program;
import com.example.geektrust.model.RequestModel;

/**
 * @author Prantik Sarkar
 * @date 11/12/22
 */

public class CommonUtils {
	
	public static final Set<String> validInputKeys = new HashSet<>(Arrays.asList(AppConstants.ADD_PROGRAMME,AppConstants.APPLY_COUPON,AppConstants.ADD_PRO_MEMBERSHIP,AppConstants.PRINT_BILL));
	public static final Set<String> validPrograms = new HashSet<>(Arrays.asList(AppConstants.CERTIFICATION,AppConstants.DIPLOMA,AppConstants.DEGREE));
	public static final Set<String> validCoupons = new HashSet<>(Arrays.asList(AppConstants.B4G1,AppConstants.DEAL_G20,AppConstants.DEAL_G5));
	
	/**
	 * This method reads input from file and add them to map
	 * @param filePath
	 * @return
	 */
	public static Map<String, String> readInputFromFile(String filePath) {
		Map<String, String> mappedInput = new HashMap<>();
		try {
			File inputFile = new File(filePath);
			Scanner reader = new Scanner(inputFile);
			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				validateInputs(line);
				prepareInputToMap(mappedInput, line);
			}
			reader.close();
		} catch (FileNotFoundException exception) {
			System.err.println("#### Input file not found at given location");
			exception.printStackTrace();
		}
		return mappedInput;
	}
	
	public static void prepareInputToMap(Map<String, String> mappedInput, String line) {
		int count = 1;
		String[] inputArray = line.split(" ");
		switch (inputArray[0]) {
			case AppConstants.ADD_PROGRAMME:
				mappedInput.put(inputArray[1], inputArray[2]);
				break;
			case AppConstants.APPLY_COUPON:
				if(!mappedInput.containsKey(AppConstants.APPLY_COUPON)) {
					mappedInput.put(AppConstants.APPLY_COUPON, inputArray[1]);
				} else {
					mappedInput.put(AppConstants.APPLY_COUPON+count+"", inputArray[1]);
				}
				count++;
				break;
			case AppConstants.PRINT_BILL:
				mappedInput.put(AppConstants.PRINT_BILL, "true");
				break;
			case AppConstants.ADD_PRO_MEMBERSHIP:
				mappedInput.put(AppConstants.ADD_PRO_MEMBERSHIP, "true");
				break;
			default:
				System.err.println("#### Invalid input format");
		}
	}
	
	/**
	 * This method convert mapped input to request model
	 * @param mappedInput
	 * @return
	 */
	public static RequestModel convertMappedInputToRequestModel(Map<String, String> mappedInput) {
		RequestModel request = new RequestModel();
		List<Program> programs = new ArrayList<>();
		List<String> coupons = new ArrayList<>();
		
		mappedInput.forEach((key,value) -> {
			if(key.equalsIgnoreCase(AppConstants.CERTIFICATION) || key.equalsIgnoreCase(AppConstants.DIPLOMA) || key.equalsIgnoreCase(AppConstants.DEGREE)) {
				Program program = new Program(key,Integer.parseInt(value));
				programs.add(program);
			} else if(key.contains(AppConstants.APPLY_COUPON)) {
				coupons.add(value);
			}
		});
		request.setPrograms(programs);
		request.setCoupons(coupons);
		request.setProMember(mappedInput.containsKey(AppConstants.ADD_PRO_MEMBERSHIP));
		request.setPrintResult(mappedInput.containsKey(AppConstants.PRINT_BILL));
		
		return request;
	}
	
	/**
	 * This method validates the user entered input.
	 * @param line
	 */
	public static void validateInputs(String line) {
		checkForIncorrectKeys(line);
		checkForIncorrectArgumentNumber(line);
		checkForValidPrograms(line);
		checkForValidCoupons(line);
	}
	
	public static void checkForIncorrectKeys(String line) {
		String[] inputArray = line.split(" ");
		if(!validInputKeys.contains(inputArray[0])) {
			throw new ProgramException("Incorrect key present in input.");
		}
	}
	
	public static void checkForIncorrectArgumentNumber(String line) {
		String[] inputArray = line.split(" ");
		if((inputArray[0].equalsIgnoreCase(AppConstants.ADD_PROGRAMME)) && ((inputArray.length)-1 != 2)) {
			throw new ProgramException("Mismatch in number of input arguments. Please check and enter input again");
		} else if((inputArray[0].equalsIgnoreCase(AppConstants.APPLY_COUPON)) && ((inputArray.length)-1 != 1)) {
			throw new ProgramException("Mismatch in number of input arguments. Please check and enter input again");
		} else if((inputArray[0].equalsIgnoreCase(AppConstants.ADD_PRO_MEMBERSHIP) || inputArray[0].equalsIgnoreCase(AppConstants.PRINT_BILL))  && ((inputArray.length)-1 != 0)) {
			throw new ProgramException("Mismatch in number of input arguments. Please check and enter input again");
		}
	}
	
	public static void checkForValidPrograms(String line) {
		String[] inputArray = line.split(" ");
		if(inputArray[0].equalsIgnoreCase(AppConstants.ADD_PROGRAMME) && !validPrograms.contains(inputArray[1])) {
			throw new ProgramException("Invalid Program selected");
		}
	}
	
	public static void checkForValidCoupons(String line) {
		String[] inputArray = line.split(" ");
		if(inputArray[0].equalsIgnoreCase(AppConstants.APPLY_COUPON) && !validCoupons.contains(inputArray[1])) {
			throw new ProgramException("Invalid Coupon selected");
		}
	}
	
	public static String formatDoubleValue(double input) {
		NumberFormat formatter = new DecimalFormat("#0.00"); 
		return formatter.format(input);
	}
}
