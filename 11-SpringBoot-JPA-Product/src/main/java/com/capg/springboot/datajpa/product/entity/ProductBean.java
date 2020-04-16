package com.capg.springboot.datajpa.product.entity;
import javax.persistence.Entity;
  
//insert into  product_masters values(20,'i7','HP',65000);

import javax.persistence.Table;
import javax.persistence.Id;
@Entity
@Table(name="product_maters")
public class ProductBean {
	
	@Id
	private int pid;
	
	private String pname;
	private double price;
	private String mno;
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getMno() {
		return mno;
	}
	public void setMno(String mno) {
		this.mno = mno;
	}
	
	

}
