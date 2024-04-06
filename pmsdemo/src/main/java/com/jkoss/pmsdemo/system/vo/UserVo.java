package com.jkoss.pmsdemo.system.vo;

import com.jkoss.pmsdemo.system.entity.User;

public class UserVo extends User{
       
	private String rname;
    private String ogname;
    
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getOgname() {
		return ogname;
	}
	public void setOgname(String ogname) {
		this.ogname = ogname;
	}
}
