package com.example.geektrust.handler.impl;

import com.example.geektrust.model.Program;

/**
 * @author Prantik Sarkar
 * @date 11/12/22
 */

public class DegreeHandler extends AbstractProgramHandler {
	
	public static final int price = 5000;
	
	@Override
	public double singleProgramCost(boolean isProMember) {
		return isProMember ? price * 0.97 : price;
	}

	@Override
	public double calculateTotalCost(Program program, boolean isProMember) {
		return isProMember ? price * program.getCount() * 0.97 : price * program.getCount();
	}

}
