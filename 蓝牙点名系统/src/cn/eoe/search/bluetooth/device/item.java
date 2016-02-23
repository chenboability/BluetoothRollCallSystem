package cn.eoe.search.bluetooth.device;

public class item {

	int mark;
	int state;
	int isComeOrNot;
	String name;
	String number;
	String address;
	
	public item(){}
	
	
	public item(int mark,int state, int isComeOrNot, String name, String number,
			String address) {
		super();
		this.mark = mark;
		this.state = state;
		this.isComeOrNot = isComeOrNot;
		this.name = name;
		this.number = number;
		this.address = address;
	}


	public int getMark() {
		return mark;
	}


	public void setMark(int mark) {
		this.mark = mark;
	}


	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getIsComeOrNot() {
		return isComeOrNot;
	}
	public void setIsComeOrNot(int isComeOrNot) {
		this.isComeOrNot = isComeOrNot;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}

}
