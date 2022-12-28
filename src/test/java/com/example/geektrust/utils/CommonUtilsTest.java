package com.example.geektrust.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.geektrust.model.Program;
import com.example.geektrust.model.RequestModel;

/**
 * @author Prantik Sarkar
 * @date 11/12/22
 */

@SuppressWarnings("static-access")
public class CommonUtilsTest {
	
	private CommonUtils utils;
	
	@BeforeEach
	void setUp() {
		utils = new CommonUtils();
	}
	
	@Test
	public void testReadAndParseInput() {
		RequestModel request = new RequestModel();
		List<Program> programs = new ArrayList<>();
		List<String> coupons = new ArrayList<>();
		programs.add(new Program("DIPLOMA",2));
		programs.add(new Program("CERTIFICATION",1));
		programs.add(new Program("DEGREE",1));
		
		coupons.add("DEAL_G5");
		coupons.add("DEAL_G20");
		
		request.setPrograms(programs);
		request.setCoupons(coupons);
		request.setProMember(false);
		request.setPrintResult(true);
		
		Map<String,String> mappedInput = utils.readInputFromFile("sample_input/input1.txt");
		assertEquals(request,utils.convertMappedInputToRequestModel(mappedInput));
	}
	
	@Test
	public void testFormatDoubleValue() {
		double input = 245.0;
		assertEquals("245.00",utils.formatDoubleValue(input));
	}

}
