package com.example.geektrust;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Prantik Sarkar
 * @date 11/12/22
 */

@SuppressWarnings("static-access")
public class MainTest {
	
	private Main service;
	
	@BeforeEach
	void setUp() {
		service = new Main();
	}
	
	@Test
	public void testInput_1() {
		String filePath = "sample_input/input1.txt";
		String expectedResult = "SUB_TOTAL 13000.00\n" + 
				"COUPON_DISCOUNT B4G1 2500.00\n" + 
				"TOTAL_PRO_DISCOUNT 0.00\n" + 
				"PRO_MEMBERSHIP_FEE 0.00\n" + 
				"ENROLLMENT_FEE 0.00\n" + 
				"TOTAL 10500.00";
		assertEquals(expectedResult, service.calculateBill(filePath));
	}
	
	@Test
	public void testInput_2() {
		String filePath = "sample_input/input2.txt";
		String expectedResult = "SUB_TOTAL 10465.00\n" + 
				"COUPON_DISCOUNT DEAL_G20 2093.00\n" + 
				"TOTAL_PRO_DISCOUNT 235.00\n" + 
				"PRO_MEMBERSHIP_FEE 200.00\n" + 
				"ENROLLMENT_FEE 0.00\n" + 
				"TOTAL 8372.00";
		assertEquals(expectedResult, service.calculateBill(filePath));
	}
	
	@Test
	public void testInput_3() {
		String filePath = "sample_input/input3.txt";
		String expectedResult = "SUB_TOTAL 3140.00\n" + 
				"COUPON_DISCOUNT NONE 0.00\n" + 
				"TOTAL_PRO_DISCOUNT 60.00\n" + 
				"PRO_MEMBERSHIP_FEE 200.00\n" + 
				"ENROLLMENT_FEE 500.00\n" + 
				"TOTAL 3640.00";
		assertEquals(expectedResult, service.calculateBill(filePath));
	}
	
	@Test
	public void testInput_4() {
		String filePath = "sample_input/input4.txt";
		String expectedResult = "SUB_TOTAL 8555.00\n" + 
				"COUPON_DISCOUNT DEAL_G5 427.75\n" + 
				"TOTAL_PRO_DISCOUNT 145.00\n" + 
				"PRO_MEMBERSHIP_FEE 200.00\n" + 
				"ENROLLMENT_FEE 0.00\n" + 
				"TOTAL 8127.25";
		assertEquals(expectedResult, service.calculateBill(filePath));
	}
}