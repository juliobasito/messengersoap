package com.projetsoap.srcfiles;
// Generated 6 janv. 2017 13:37:08 by Hibernate Tools 5.2.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User generated by hbm2java
 */
@Entity()
@Table(name="user")
public class User implements java.io.Serializable {

	@Id
	@Column(name="Id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name="FirstName")
	private String firstName;
	@Column(name="LastName")
	private String lastName;
	@Column(name="Mail")
	private String mail;
	@Column(name="Phone")
	private String phone;
	@Column(name="Password")
	private String password;
	@Column(name="Id_Zone")
	private int idZone;
	@Column(name="Id_Role")
	private int idRole;
	@Column(name="Formation")
	private String formation;

	public User() {
	}

	public User(String firstName, String lastName, String mail, String phone, String password, int idZone, int idRole,
			String formation) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.mail = mail;
		this.phone = phone;
		this.password = password;
		this.idZone = idZone;
		this.idRole = idRole;
		this.formation = formation;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getIdZone() {
		return this.idZone;
	}

	public void setIdZone(int idZone) {
		this.idZone = idZone;
	}

	public int getIdRole() {
		return this.idRole;
	}

	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}

	public String getFormation() {
		return this.formation;
	}

	public void setFormation(String formation) {
		this.formation = formation;
	}

}
