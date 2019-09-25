package db;

import java.util.Date;

public class MemberVO {
	int num;
	String title;

	public MemberVO() {
	}
	
	public MemberVO(int num, String title) {
		this.num = num;
		this.title = title;
	}

	public int getNum() {
		return this.num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
