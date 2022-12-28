package com.example.geektrust;

import java.util.Map;

import com.example.geektrust.exception.ProgramException;
import com.example.geektrust.model.RequestModel;
import com.example.geektrust.service.ProgramService;
import com.example.geektrust.service.ProgramServiceImpl;
import com.example.geektrust.utils.CommonUtils;

/**
 * Main class and entrypoint of the application
 * 
 * @author Prantik Sarkar
 * @date 11/12/22
 */

public class Main {
	
    public static void main(String[] args) {
    	try {
			if (args.length != 1) {
				throw new ProgramException("Input file path is not provided in arguments while execution. Please provide the valid input file path.");
			}
			String filePath = args[0];
			System.out.println(calculateBill(filePath));
		} catch (Exception e) {
			System.err.println("Exception while billing execution, details - "+e.getMessage());
		}
	}
    
    public static String calculateBill(String filePath) {
		Map<String,String> mappedRequest = CommonUtils.readInputFromFile(filePath);
		RequestModel request = CommonUtils.convertMappedInputToRequestModel(mappedRequest);
		ProgramService programService = new ProgramServiceImpl();
		return request.isPrintResult() ? programService.execute(request) : "Not Printing result as PRINT_BILL key is not present in input";
	}
}
