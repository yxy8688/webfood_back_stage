package com.yc.bean;

import java.io.Serializable;

public class Resorderitem implements Serializable {

	
	private static final long serialVersionUID = 2653390531118677876L;
	
	private Integer roiid ;
	private Integer roid ;
	private Integer fid ;
	private Double dealprice ;
	private Integer num ;
	
	//////向其中加入userid ，fname
	private Integer userid;
	private String fname;
	
	
	
	

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}
	
	@Override
	public String toString() {
		return "Resorderitem [roiid=" + roiid + ", roid=" + roid + ", fid=" + fid + ", dealprice=" + dealprice
				+ ", num=" + num +  ", userid" + userid +  ", fname=" + fname + "]";
	}
	public Integer getRoiid() {
		return roiid;
	}
	public void setRoiid(Integer roiid) {
		this.roiid = roiid;
	}
	public Integer getRoid() {
		return roid;
	}
	public void setRoid(Integer roid) {
		this.roid = roid;
	}
	public Integer getFid() {
		return fid;
	}
	public void setFid(Integer fid) {
		this.fid = fid;
	}
	public Double getDealprice() {
		return dealprice;
	}
	public void setDealprice(Double dealprice) {
		this.dealprice = dealprice;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dealprice == null) ? 0 : dealprice.hashCode());
		result = prime * result + ((fid == null) ? 0 : fid.hashCode());
		result = prime * result + ((num == null) ? 0 : num.hashCode());
		result = prime * result + ((roid == null) ? 0 : roid.hashCode());
		result = prime * result + ((roiid == null) ? 0 : roiid.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Resorderitem other = (Resorderitem) obj;
		if (dealprice == null) {
			if (other.dealprice != null)
				return false;
		} else if (!dealprice.equals(other.dealprice))
			return false;
		if (fid == null) {
			if (other.fid != null)
				return false;
		} else if (!fid.equals(other.fid))
			return false;
		if (num == null) {
			if (other.num != null)
				return false;
		} else if (!num.equals(other.num))
			return false;
		if (roid == null) {
			if (other.roid != null)
				return false;
		} else if (!roid.equals(other.roid))
			return false;
		if (roiid == null) {
			if (other.roiid != null)
				return false;
		} else if (!roiid.equals(other.roiid))
			return false;
		return true;
	}
	public Resorderitem(Integer roiid, Integer roid, Integer fid, Double dealprice, Integer num) {
		super();
		this.roiid = roiid;
		this.roid = roid;
		this.fid = fid;
		this.dealprice = dealprice;
		this.num = num;
	}
	public Resorderitem() {
		super();
	}
	
	

}
