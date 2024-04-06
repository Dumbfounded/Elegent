package com.jkoss.common.vo;

/**
 * Token
 * 
 * @Author chair
 * @Version 1.0, 2017年8月20日
 * @See
 * @Since com.jk.bestbaby.vo
 * @Description: TODO
 */
public class Token {
	
	private String token;
	private Long exp;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getExp() {
		return exp;
	}

	public void setExp(Long exp) {
		this.exp = exp;
	}

	@Override
	public String toString() {
		return "Token [token=" + token + ", exp=" + exp + "]";
	}

}