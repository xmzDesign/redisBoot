/**
 * 
 */
package com.example.demo.domian;

import java.io.Serializable;

/**
 * @author xuminzhe 2017年8月9日 下午5:54:48
 *
 */
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8289770787953160443L;

	private String username;
	private Integer age;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String username, Integer age) {
		this.username = username;
		this.age = age;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}
