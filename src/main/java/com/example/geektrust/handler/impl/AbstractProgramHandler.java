package com.example.geektrust.handler.impl;

import com.example.geektrust.handler.ProgramHandler;
import com.example.geektrust.model.Program;

/**
 * @author Prantik Sarkar
 * @date 11/12/22
 */

public abstract class AbstractProgramHandler implements ProgramHandler {

	@Override
	public double singleProgramCost(boolean isProMember) {
		return 0;
	}
	
	@Override
	public double calculateTotalCost(Program program, boolean isProMember) {
		return 0;
	}

}
