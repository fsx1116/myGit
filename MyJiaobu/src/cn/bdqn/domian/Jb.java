package cn.bdqn.domian;

import java.util.Date;

public class Jb {

	private long id;      //主键id
	private String area;     //区域名称
	private Date time;       //时间
    private String felling;  //感慨
    
   
    
    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getFelling() {
		return felling;
	}

	public void setFelling(String felling) {
		this.felling = felling;
	}

	public String getarea(){
    	return area;
    }
    
    public void setarea(String area) {
        this.area = area;
    }
    
    public String getfelling(){
    	return felling;
    }
    
    public void setfelling(String felling) {
        this.felling = felling;
    }
    
    public Date gettime() {
        return time;
    }

    public void settime(Date time) {
        this.time = time;
    }
}
