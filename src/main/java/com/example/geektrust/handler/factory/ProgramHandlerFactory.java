package com.example.geektrust.handler.factory;

/**
 * @author Prantik Sarkar
 * @date 11/12/22
 */

import com.example.geektrust.constants.AppConstants;
import com.example.geektrust.exception.ProgramException;
import com.example.geektrust.handler.ProgramHandler;
import com.example.geektrust.handler.impl.CertifcationHandler;
import com.example.geektrust.handler.impl.DegreeHandler;
import com.example.geektrust.handler.impl.DiplomaHandler;

public class ProgramHandlerFactory {

	public static ProgramHandler getProgramInstance(String programName) {
		ProgramHandler programHandler;
		switch(programName) {
			case AppConstants.CERTIFICATION:
				programHandler = new CertifcationHandler();
				break;
				
			case AppConstants.DEGREE:
				programHandler = new DegreeHandler();
				break;
				
			case AppConstants.DIPLOMA:
				programHandler = new DiplomaHandler();
				break;
			
			default : throw new ProgramException("No such Program !!");
		}
		return programHandler;
	}
}
