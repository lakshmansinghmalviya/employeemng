package com.ls.project.model;

public class Employee {
	private Long id;
	private String firstName;
	private String lastName;
	private Integer age;
	private String email;
	private String password;
	private String doj;
	private String mobile;
	private String country;
	private String city;
	private String street;
	private String dept;
	private String roles;
	private String services;
	private Boolean active;

	public Employee() {
		super();
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
				+ ", email=" + email + ", password=" + password + ", doj=" + doj + ", mobile=" + mobile + ", country="
				+ country + ", city=" + city + ", street=" + street + ", dept=" + dept + ", roles=" + roles
				+ ", services=" + services + ", active=" + active + "]";
	}

	public Employee(Long id, String firstName, String lastName, Integer age, String email, String password, String doj,
			String mobile, String country, String city, String street, String dept, String roles, String services,
			Boolean active) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.email = email;
		this.password = password;
		this.doj = doj;
		this.mobile = mobile;
		this.country = country;
		this.city = city;
		this.street = street;
		this.dept = dept;
		this.roles = roles;
		this.services = services;
		this.active = active;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getServices() {
		return services;
	}

	public void setServices(String services) {
		this.services = services;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getDoj() {
		return doj;
	}

	public void setDoj(String doj) {
		this.doj = doj;
	}
}
