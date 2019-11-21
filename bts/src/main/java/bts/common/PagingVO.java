package bts.common;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("pagingVO")
@Scope("prototype")
public class PagingVO {
	private int totalCount;//전체 열
	private int totalPage;//전체 페이지
	private int rangePage;//페이지당 열수
	private int curPage;//현재 페이지
	private int startPage;//현재 시작 페이지
	private int endPage;//현재 끝 페이지
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getRangePage() {
		return rangePage;
	}
	public void setRangePage(int rangePage) {
		this.rangePage = rangePage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public void calTotalPage(int totalCount,int rangePage) {
		int totalPage = totalCount/rangePage;
		
		if(totalPage%rangePage>0) {
			totalPage=totalPage+1;
		}
		this.totalCount=totalCount;
		this.rangePage=rangePage;
		this.totalPage=totalPage;
	}
	
	public void calStartEndPage() {
		int startPage=(((this.curPage-1)/this.rangePage)*this.rangePage)+1;
		int endPage=(startPage+this.rangePage)-1;
		if(endPage>this.totalPage) {
			endPage=this.totalPage;
		}
		
		this.startPage=startPage;
		this.endPage = endPage;
	}
	
	
}
