package com.projetsoap.srcfiles;
// Generated 6 janv. 2017 13:37:08 by Hibernate Tools 5.2.0.CR1

/**
 * Company generated by hbm2java
 */
public class Company implements java.io.Serializable {

	private Integer id;
	private String name;
	private String address;
	private int idSector;

	public Company() {
	}

	public Company(String name, String address, int idSector) {
		this.name = name;
		this.address = address;
		this.idSector = idSector;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getIdSector() {
		return this.idSector;
	}

	public void setIdSector(int idSector) {
		this.idSector = idSector;
	}

}
