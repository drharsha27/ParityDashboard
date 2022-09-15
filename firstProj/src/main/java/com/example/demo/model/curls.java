package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class curls {

	@Id
	@GeneratedValue
	private int id;
	@Override
	public String toString() {
		return "curls [id=" + id + ", preProdCurl=" + preProdCurl + ", prodCurl=" + prodCurl + "]";
	}
	public String preProdCurl;
	public String prodCurl;
	public String getPreProdCurl() {
		return preProdCurl;
	}
	public void setPreProdCurl(String preProdCurl) {
		this.preProdCurl = preProdCurl;
	}
	
	public String getProdCurl() {
		return prodCurl;
	}
	public void setProdCurl(String prodCurl) {
		this.prodCurl = prodCurl;
	}
	
	
}
