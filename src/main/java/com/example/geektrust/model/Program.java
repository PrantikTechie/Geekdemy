package com.example.geektrust.model;

/**
 * @author Prantik Sarkar
 * @date 11/12/22
 */

public class Program {

	private String programName;
	private int count;
	
	public String getProgramName() {
		return programName;
	}
	
	public int getCount() {
		return count;
	}
	
	public Program() {
		super();
	}

	public Program(String programName, int count) {
		super();
		this.programName = programName;
		this.count = count;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + count;
		result = prime * result + ((programName == null) ? 0 : programName.hashCode());
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
		Program other = (Program) obj;
		if (count != other.count)
			return false;
		if (programName == null) {
			if (other.programName != null)
				return false;
		} else if (!programName.equals(other.programName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Program [programName=" + programName + ", count=" + count + "]";
	}
}
