package com.example.geektrust.handler.impl;

import com.example.geektrust.model.Program;

/**
 * @author Prantik Sarkar
 * @date 11/12/22
 */

public class DiplomaHandler extends AbstractProgramHandler {
	
	public static final int price = 2500;
	
	@Override
	public double singleProgramCost(boolean isProMember) {
		return isProMember ? price * 0.99 : price;
	}

	@Override
	public double calculateTotalCost(Program program, boolean isProMember) {
		return isProMember ? price * program.getCount() * 0.99 : price * program.getCount();
	}

}
