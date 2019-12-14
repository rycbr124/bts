package bts.common;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("pagingVO")
@Scope("prototype")
public class PagingVO {
	private int totalCount;//전체 열
	private int totalPage;//전체 페이지
	private int rangeRow;//페이지당 열수
	private int rangePage;//보여줄 페이지범위
	private int curPage;//현재 페이지
	private int startPage;//현재 시작 페이지
	private int endPage;//현재 끝 페이지
	private int startRow;//현재 시작 열
	private int endRow;//현재 끝 열
	
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getEndRow() {
		return endRow;
	}
	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
	public int getRangeRow() {
		return rangeRow;
	}
	public void setRangeRow(int rangeRow) {
		this.rangeRow = rangeRow;
	}
	
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
	
	public void setPaging(int curPage,int totalCount,int rPage,int rRow) {
		calTotalPage(totalCount, rRow);
		calCurPage(curPage);
		calStartEndPage(rPage);
		calStartEndRow();
	}

	public void setPagingDesc(int curPage,int totalCount,int rPage,int rRow) {
		calTotalPage(totalCount, rRow);
		calCurPage(curPage);
		calStartEndPage(rPage);
		calStartEndRowDesc();
	}
	
	private void calTotalPage(int totalCount,int rangeRow) {
		int totalPage = totalCount/rangeRow;
		if(totalCount%rangeRow>0) {
			totalPage=totalPage+1;
		}
		this.totalCount=totalCount;
		this.rangeRow=rangeRow;
		this.totalPage=totalPage;
	}
	
	private void calStartEndPage(int rangePage) {
		int startPage=(((this.curPage-1)/rangePage)*rangePage)+1;
		int endPage=(startPage+rangePage)-1;
		if(endPage>this.totalPage) {
			endPage=this.totalPage;
		}
		
		this.startPage=startPage;
		this.endPage = endPage;
		this.rangePage=rangePage;
	}

	private void calStartEndRowDesc() {
		int endRow = this.totalCount-((this.curPage-1)*this.rangeRow);
		int startRow = (endRow-this.rangeRow)+1;
		if(startRow<0) {
			startRow=0;
		}	
		if(endRow<0) {
			endRow=0;
		}
		this.startRow=startRow;
		this.endRow=endRow;
	}	
	
	private void calStartEndRow() {
		int endRow = this.curPage*this.rangeRow;
		int startRow = (endRow-this.rangeRow)+1;
		if(startRow<0) {
			startRow=0;
		}
		this.startRow=startRow;
		this.endRow=endRow;
	}
	
	private void calCurPage(int curPage){
		if(curPage<=0) {
			this.curPage=1;
		}else if(curPage>this.totalPage) {
			this.curPage=this.totalPage;			
		}else {
			this.curPage=curPage;
		}
	}
	
}
