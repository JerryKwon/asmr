package asmr;

public class NotiData{
	private int no;
	private String tit, wrtPsn, wrtDttm;
	
	public NotiData() {
    }
    //�ʱ�ȭ�ϴ� �����ڵ� alt + shift +s ����Ű�� ����� �ִ�
    //generate constructor using fields
    public NotiData(int no, String tit, String wrtPsn, String wrtDttm) {
        super();
        this.no = no;
        this.tit = tit;
        this.wrtPsn = wrtPsn;
        this.wrtDttm = wrtDttm;
    }
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTit() {
		return tit;
	}
	public void setTit(String tit) {
		this.tit = tit;
	}
	public String getWrtPsn() {
		return wrtPsn;
	}
	public void setWrtPsn(String wrtPsn) {
		this.wrtPsn = wrtPsn;
	}
	public String getWrtDttm() {
		return wrtDttm;
	}
	public void setWrtDttm(String wrtDttm) {
		this.wrtDttm = wrtDttm;
	}
	
	
	@Override
    public String toString() {
        return "NotiData [no=" + no + ", tit=" + tit + ", wrtPsn=" + wrtPsn + ", wrtDttm=" + wrtDttm + "]";
    }
	
}