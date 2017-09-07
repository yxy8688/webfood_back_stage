package com.yc.bean;

public class Administrator {
	private Integer adminid;
	private String adminname;
	private String adminpwd;
	

	
	

	public Integer getAdminid() {
		return adminid;
	}
	public void setAdminid(Integer adminid) {
		this.adminid = adminid;
	}
	public String getAdminname() {
		return adminname;
	}
	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}
	public String getAdminpwd() {
		return adminpwd;
	}
	public void setAdminpwd(String adminpwd) {
		this.adminpwd = adminpwd;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adminid == null) ? 0 : adminid.hashCode());
		result = prime * result + ((adminname == null) ? 0 : adminname.hashCode());
		result = prime * result + ((adminpwd == null) ? 0 : adminpwd.hashCode());
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
		Administrator other = (Administrator) obj;
		if (adminid == null) {
			if (other.adminid != null)
				return false;
		} else if (!adminid.equals(other.adminid))
			return false;
		if (adminname == null) {
			if (other.adminname != null)
				return false;
		} else if (!adminname.equals(other.adminname))
			return false;
		if (adminpwd == null) {
			if (other.adminpwd != null)
				return false;
		} else if (!adminpwd.equals(other.adminpwd))
			return false;
		return true;
	}
	public Administrator(Integer adminid, String adminname, String adminpwd) {
		super();
		this.adminid = adminid;
		this.adminname = adminname;
		this.adminpwd = adminpwd;
	}
	public Administrator() {
		super();
	}
	@Override
	public String toString() {
		return "Administrator [adminid=" + adminid + ", adminname=" + adminname + ", adminpwd=" + adminpwd + "]";
	}
	
	
	
	

}
