package cn.bcsp.mall.util;

public class PageUtil {
	private Integer index; //当前页  第一页是从0开始的
	private Integer count; //每页显示的记录数
	private Integer total; //总记录数
	private Integer pageStart; //每页开始条数

	public PageUtil() {
	}

	public PageUtil(Integer index, Integer count) {
		this.index = index;
		this.count = count;
	}
    //是否有上一页
	public Boolean isHasPrev(){
		return index>=1;
	}
	//是否有下一页
	public Boolean isHashNext(){
		return index+1 < getTotalPage();
	}

	//总页数
	public Integer getTotalPage(){
		if(total%count==0){
			return total/count;
		}else{
			return total/count+1;
		}
	}

	//获得每页开始条数
	public Integer getPageStart(){
		if(index!=null){
			return index*count;
		}else{
			return pageStart;
		}
	}

	//设置每页开始条数
	public PageUtil setPageStart(Integer pageStart){
		this.pageStart=pageStart;
		return this;
	}

	//当前页的sett和gett方法
	public Integer getIndex() {
		return index;
	}

	public PageUtil setIndex(Integer index) {
		this.index = index;
		return this;
	}

	public Integer getCount(){
		return count;
	}

	public PageUtil setCount(Integer count){
		this.count=count;
		return this;
	}

	public Integer getTotal(){
		return total;
	}

	public PageUtil setTotal(Integer total){
		this.total=total;
		return this;
	}


}
