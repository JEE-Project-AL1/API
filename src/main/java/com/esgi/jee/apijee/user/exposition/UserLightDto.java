package com.esgi.jee.apijee.user.exposition;


import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.util.Date;


public class UserLightDto {
	private Long id;

	private Date connectedAt;

	private String firstname;

	private String lastname;

	private String email;

	private Boolean isDeleted;

	public UserLightDto(Long id, Date connectedAt, String firstname, String lastname, String email, Boolean isDeleted) {
		this.id = id;
		this.connectedAt = connectedAt;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.isDeleted = isDeleted;
	}

	public UserLightDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getConnectedAt() {
		return connectedAt;
	}

	public void setConnectedAt(Date connectedAt) {
		this.connectedAt = connectedAt;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getDeleted() {
		return isDeleted;
	}

	public void setDeleted(Boolean deleted) {
		isDeleted = deleted;
	}

	@InitBinder
	public void dataBinding(WebDataBinder dataBinder) {
		dataBinder.setAllowedFields((String[]) null);
	}
}
