package bts.e.p001.VO;

public class PagingVO2 {
	
	 // 현재페이지, 시작페이지, 끝페이지, 게시글 총 갯수, 페이지당 글 갯수, 마지막페이지, SQL쿼리에 쓸 start, end
	   private int nowPage2, startPage2, endPage2, total2, cntPerPage2, lastPage2, start2, end2;
	   private int cntPage2 = 5;

	   private String member_id;
	   
	   public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public PagingVO2() {
	   }

	public int getNowPage2() {
		return nowPage2;
	}
	public void setNowPage2(int nowPage2) {
		this.nowPage2 = nowPage2;
	}
	public int getStartPage2() {
		return startPage2;
	}
	public void setStartPage2(int startPage2) {
		this.startPage2 = startPage2;
	}
	public int getEndPage2() {
		return endPage2;
	}
	public void setEndPage2(int endPage2) {
		this.endPage2 = endPage2;
	}
	public int getTotal2() {
		return total2;
	}
	public void setTotal2(int total2) {
		this.total2 = total2;
	}
	public int getCntPerPage2() {
		return cntPerPage2;
	}
	public void setCntPerPage2(int cntPerPage2) {
		this.cntPerPage2 = cntPerPage2;
	}
	public int getLastPage2() {
		return lastPage2;
	}
	public void setLastPage2(int lastPage2) {
		this.lastPage2 = lastPage2;
	}
	public int getStart2() {
		return start2;
	}
	public void setStart2(int start2) {
		this.start2 = start2;
	}
	public int getEnd2() {
		return end2;
	}
	public void setEnd2(int end2) {
		this.end2 = end2;
	}
	public int getCntPage2() {
		return cntPage2;
	}
	public void setCntPage2(int cntPage2) {
		this.cntPage2 = cntPage2;
	}
	public PagingVO2(int total2, int nowPage2, int cntPerPage2) {
	      setNowPage2(nowPage2);
	      setCntPerPage2(cntPerPage2);
	      setTotal2(total2);
	      calcLastPage(getTotal2(), getCntPerPage2());
	      calcStartEndPage(getNowPage2(), cntPage2);
	      calcStartEnd(getNowPage2(), getCntPerPage2());
	   }

	   // 제일 마지막 페이지 계산
	   public void calcLastPage(int total2, int cntPerPage2) {
	      setLastPage2((int) Math.ceil((double)total2 / (double)cntPerPage2));
	   }
	   // 시작, 끝 페이지 계산
	   public void calcStartEndPage(int nowPage2, int cntPage2) {
	      setEndPage2(((int)Math.ceil((double)nowPage2 / (double)cntPage2)) * cntPage2);
	      if (getLastPage2() < getEndPage2()) {
	         setEndPage2(getLastPage2());
	      }
	      setStartPage2(getEndPage2() - cntPage2 + 1);
	      if (getStartPage2() < 1) {
	         setStartPage2(1);
	      }
	   }
	   // DB 쿼리에서 사용할 start, end값 계산
	   public void calcStartEnd(int nowPage2, int cntPerPage2) {
	      setEnd2(nowPage2 * cntPerPage2);
	      setStart2(getEnd2() - cntPerPage2 + 1);
	   }
	   
	   @Override
	   public String toString() {
	      return "PagingVO [nowPage=" + nowPage2 + ", startPage=" + startPage2 + ", endPage=" + endPage2 + ", total=" + total2
	            + ", cntPerPage=" + cntPerPage2 + ", lastPage=" + lastPage2 + ", start=" + start2 + ", end=" + end2
	            + ", cntPage=" + cntPage2 + "]";
	   }
}
