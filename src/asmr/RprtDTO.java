package asmr;

public class RprtDTO {
	
	private String rprtDttm;
	public String getRprtDttm() {
		return rprtDttm;
	}

	public void setRprtDttm(String rprtDttm) {
		this.rprtDttm = rprtDttm;
	}


	private String rprtPrsnName;
	private String telNo;
	private String rprtTp;
	private String anmlKinds;
	private String anmlSize;
	private String expln;
	private String dscvDttm;
	private String dscvLoc;
	private String pic;
	
	public String getRprtPrsnName() {
		return rprtPrsnName;
	}

	public void setRprtPrsnName(String rprtPrsnName) {
		this.rprtPrsnName = rprtPrsnName;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String getRprtTp() {
		return rprtTp;
	}

	public void setRprtTp(String rprtTp) {
		this.rprtTp = rprtTp;
	}

	public String getAnmlKinds() {
		return anmlKinds;
	}

	public void setAnmlKinds(String anmlKinds) {
		this.anmlKinds = anmlKinds;
	}

	public String getAnmlSize() {
		return anmlSize;
	}

	public void setAnmlSize(String anmlSize) {
		this.anmlSize = anmlSize;
	}

	public String getExpln() {
		return expln;
	}

	public void setExpln(String expln) {
		this.expln = expln;
	}

	public String getDscvDttm() {
		return dscvDttm;
	}

	public void setDscvDttm(String dscvDttm) {
		this.dscvDttm = dscvDttm;
	}

	public String getDscvLoc() {
		return dscvLoc;
	}

	public void setDscvLoc(String dscvLoc) {
		this.dscvLoc = dscvLoc;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	@Override
	public String toString() {
		return "RprtDTO [rprtDttm=" + rprtDttm + ", rprtPrsnName=" + rprtPrsnName + ", telNo=" + telNo + ", rprtTp="
				+ rprtTp + ", anmlKinds=" + anmlKinds + ", anmlSize=" + anmlSize + ", expln=" + expln + ", dscvDttm="
				+ dscvDttm + ", dscvLoc=" + dscvLoc + ", pic=" + pic + "]";
	}
	

}
