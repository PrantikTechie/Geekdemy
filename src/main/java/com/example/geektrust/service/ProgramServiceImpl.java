package com.example.geektrust.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.geektrust.constants.AppConstants;
import com.example.geektrust.handler.ProgramHandler;
import com.example.geektrust.handler.factory.ProgramHandlerFactory;
import com.example.geektrust.model.Program;
import com.example.geektrust.model.RequestModel;
import com.example.geektrust.utils.CommonUtils;

/**
 * @author Prantik Sarkar
 * @date 11/12/22
 */

public class ProgramServiceImpl implements ProgramService {
	
	public static final String NO_DISCOUNT_MESSAGE = "NONE 0.00";
	public static final String NEW_LINE = "\n";

	@Override
	public String execute(RequestModel request) {
		boolean isProMember = request.isProMember();
		double subTotal = calculateSubTotal(request.getPrograms(), isProMember);
		double enrollmentFees = subTotal < 6666 ? 500 : 0;
		double proMembershipFees = isProMember ? 200 : 0;
		String discountMessageString = getCouponDiscount(request);
		double couponDiscount = discountMessageString.equalsIgnoreCase(NO_DISCOUNT_MESSAGE) ? 0.0 : Double.parseDouble(getCouponDiscount(request).split(" ")[1]);
		
		StringBuilder builder = new StringBuilder();
		builder.append(AppConstants.SUB_TOTAL +" " +CommonUtils.formatDoubleValue(subTotal)).append(NEW_LINE)
				.append(AppConstants.COUPON_DISCOUNT+" "+ discountMessageString).append(NEW_LINE)
				.append(AppConstants.TOTAL_PRO_DISCOUNT+" "+ CommonUtils.formatDoubleValue((calculateSubTotal(request.getPrograms(), false) - subTotal + proMembershipFees))).append(NEW_LINE)
				.append(AppConstants.PRO_MEMBERSHIP_FEE+" "+CommonUtils.formatDoubleValue(proMembershipFees)).append(NEW_LINE)
				.append(AppConstants.ENROLLMENT_FEE+" "+CommonUtils.formatDoubleValue(enrollmentFees)).append(NEW_LINE)
				.append(AppConstants.TOTAL+" "+CommonUtils.formatDoubleValue(subTotal+enrollmentFees-couponDiscount));
		
		return builder.toString();
	}

	@Override
	public double calculateSubTotal(List<Program> programs, boolean isProMember) {
		double subTotal = 0;
		for(Program program : programs) {
			ProgramHandler programHandler = ProgramHandlerFactory.getProgramInstance(program.getProgramName());
			subTotal += programHandler.calculateTotalCost(program, isProMember);
		}
		return isProMember ? subTotal + 200 : subTotal;
	}

	@Override
	public String getCouponDiscount(RequestModel request) {
		if(getTotalProgrammesCount(request) >=4) {
			return AppConstants.B4G1 + " " + CommonUtils.formatDoubleValue(getLeastPricedProgramme(request));
		}
		
		boolean isProMember = request.isProMember();
		double subTotal = calculateSubTotal(request.getPrograms(), isProMember);
		List<String> couponList = request.getCoupons();
		
		if(couponList.contains(AppConstants.DEAL_G20) && calculateSubTotal(request.getPrograms(), false) >=10000) {
			return AppConstants.DEAL_G20+" "+CommonUtils.formatDoubleValue((subTotal*0.2));
		} else if (couponList.contains(AppConstants.DEAL_G5) && getTotalProgrammesCount(request) >=2) {
			return AppConstants.DEAL_G5+" "+CommonUtils.formatDoubleValue((subTotal*0.05));
		} else {
			return NO_DISCOUNT_MESSAGE;
		}
	}
	
	@Override
	public int getTotalProgrammesCount(RequestModel request) {
		int totalCount = 0;
		for(Program program : request.getPrograms()) {
			totalCount+=program.getCount();
		}
		return totalCount;
	}
	
	@Override
	public double getLeastPricedProgramme(RequestModel request) {
		Set<String> programNames =request.getPrograms().stream().map(program -> program.getProgramName()).collect(Collectors.toSet());
		if(programNames.contains(AppConstants.DIPLOMA)) {
			return ProgramHandlerFactory.getProgramInstance(AppConstants.DIPLOMA).singleProgramCost(request.isProMember());
		} else if (programNames.contains(AppConstants.CERTIFICATION)) {
			return ProgramHandlerFactory.getProgramInstance(AppConstants.CERTIFICATION).singleProgramCost(request.isProMember());
		} else {
			return ProgramHandlerFactory.getProgramInstance(AppConstants.DEGREE).singleProgramCost(request.isProMember());
		}
	}

}
