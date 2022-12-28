package com.example.geektrust.handler;

/**
 * @author Prantik Sarkar
 * @date 11/12/22
 */

import com.example.geektrust.model.Program;

public interface ProgramHandler {
	
	public double singleProgramCost(boolean isProMember);

	public double calculateTotalCost(Program program, boolean isProMember);
	
}
