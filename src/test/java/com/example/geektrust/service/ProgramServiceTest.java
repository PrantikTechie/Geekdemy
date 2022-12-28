package com.example.geektrust.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.geektrust.model.Program;
import com.example.geektrust.model.RequestModel;

/**
 * @author Prantik Sarkar
 * @date 11/12/22
 */

public class ProgramServiceTest {
	
	ProgramServiceImpl service;
	
	@BeforeEach
	void setUp() {
		service = new ProgramServiceImpl();
	}
	
	@Test
	public void testGetTotalProgrammesCount() {
		assertEquals(3,service.getTotalProgrammesCount(getSampleRequestModel(true,3,"CERTIFICATION")));
	}
	
	@Test
	public void testCalculateSubTotal() {
		assertEquals(15000.0,service.calculateSubTotal(getSampleRequestModel(false, 3, "DEGREE").getPrograms(), false));
		assertEquals(10100.0,service.calculateSubTotal(getSampleRequestModel(true, 4, "DIPLOMA").getPrograms(), true));
		assertEquals(9000.0,service.calculateSubTotal(getSampleRequestModel(false, 3, "CERTIFICATION").getPrograms(), false));
	}
	
	@Test
	public void testGetCouponDiscount() {
		String expectedResult1 = "B4G1 3000.00";
		String expectedResult2 = "B4G1 2940.00";
		String expectedResult3 = "DEAL_G20 3000.00";
		String expectedResult4 = "DEAL_G20 2950.00";
		String expectedResult5 = "DEAL_G5 300.00";
		String expectedResult6 = "DEAL_G5 451.00";
		String expectedResult7 = "NONE 0.00";
		assertEquals(expectedResult2, service.getCouponDiscount(getSampleRequestModel(true, 4,"CERTIFICATION")));
		assertEquals(expectedResult1, service.getCouponDiscount(getSampleRequestModel(false, 4,"CERTIFICATION")));
		assertEquals(expectedResult3, service.getCouponDiscount(getSampleRequestModel(false, 3, "DEGREE")));
		assertEquals(expectedResult4, service.getCouponDiscount(getSampleRequestModel(true, 3, "DEGREE")));
		assertEquals(expectedResult5, service.getCouponDiscount(getSampleRequestModel(false, 2, "CERTIFICATION")));
		assertEquals(expectedResult6, service.getCouponDiscount(getSampleRequestModel(true, 3, "CERTIFICATION")));
		assertEquals(expectedResult7, service.getCouponDiscount(getSampleRequestModel(false, 1, "DIPLOMA")));
	}
	
	@Test
	public void testGetLeastPricedProgramme() {
		assertEquals(3000.0,service.getLeastPricedProgramme(getSampleRequestModel(false,3,"CERTIFICATION")));
		assertEquals(2940.0,service.getLeastPricedProgramme(getSampleRequestModel(true,3,"CERTIFICATION")));
	}
	
	public RequestModel getSampleRequestModel(boolean isProMember,int productCount, String productName) {
		RequestModel request = new RequestModel();
		List<Program> programs = new ArrayList<>();
		List<String> coupons = new ArrayList<>();
		programs.add(new Program(productName,productCount));
		coupons.add("DEAL_G20");
		coupons.add("DEAL_G5");
		
		request.setPrograms(programs);
		request.setCoupons(coupons);
		request.setProMember(isProMember);
		request.setPrintResult(true);
		return request;
	}
}
