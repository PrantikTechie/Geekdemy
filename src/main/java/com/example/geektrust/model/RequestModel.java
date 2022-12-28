package com.example.geektrust.model;

import java.util.List;

/**
 * @author Prantik Sarkar
 * @date 11/12/22
 */

public class RequestModel {

	private List<Program> programs;
	private List<String> coupons;
	private boolean isProMember;
	private boolean printResult;
	
	public List<Program> getPrograms() {
		return programs;
	}
	
	public void setPrograms(List<Program> programs) {
		this.programs = programs;
	}
	
	public List<String> getCoupons() {
		return coupons;
	}
	
	public void setCoupons(List<String> coupons) {
		this.coupons = coupons;
	}
	
	public boolean isProMember() {
		return isProMember;
	}
	
	public void setProMember(boolean isProMember) {
		this.isProMember = isProMember;
	}
	
	public boolean isPrintResult() {
		return printResult;
	}
	
	public void setPrintResult(boolean printResult) {
		this.printResult = printResult;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coupons == null) ? 0 : coupons.hashCode());
		result = prime * result + (isProMember ? 1231 : 1237);
		result = prime * result + (printResult ? 1231 : 1237);
		result = prime * result + ((programs == null) ? 0 : programs.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RequestModel other = (RequestModel) obj;
		if (coupons == null) {
			if (other.coupons != null)
				return false;
		} else if (!coupons.equals(other.coupons))
			return false;
		if (isProMember != other.isProMember)
			return false;
		if (printResult != other.printResult)
			return false;
		if (programs == null) {
			if (other.programs != null)
				return false;
		} else if (!programs.equals(other.programs))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RequestModel [programs=" + programs + ", coupons=" + coupons + ", isProMember=" + isProMember
				+ ", printResult=" + printResult + "]";
	}
}
