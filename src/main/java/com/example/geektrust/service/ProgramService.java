package com.example.geektrust.service;

import java.util.List;

import com.example.geektrust.model.Program;
import com.example.geektrust.model.RequestModel;

/**
 * @author Prantik Sarkar
 * @date 11/12/22
 */

public interface ProgramService {

	/**
	 * Method to perform the execution of billing process
	 * @param request
	 * @return
	 */
	public String execute(RequestModel request);
	
	/**
	 * Method to calculate the total number of programs selected
	 * @param request
	 * @return
	 */
	public int getTotalProgrammesCount(RequestModel request);
	
	/**
	 * Method to calculate the sub total of all orders
	 * @param programs
	 * @param isProMember
	 * @return
	 */
	public double calculateSubTotal(List<Program> programs, boolean isProMember);
	
	/**
	 * Method to find applicable coupon and discount amount
	 * @param request
	 * @return
	 */
	public String getCouponDiscount(RequestModel request);

	/**
	 * Method to find least priced program
	 * @param request
	 * @return
	 */
	public double getLeastPricedProgramme(RequestModel request);
}
