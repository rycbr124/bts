package bts.e.p001.VO;

public class PagingVO3 {
	
	 // 현재페이지, 시작페이지, 끝페이지, 게시글 총 갯수, 페이지당 글 갯수, 마지막페이지, SQL쿼리에 쓸 start, end
	   private int nowPage3, startPage3, endPage3, total3, cntPerPage3, lastPage3, start3, end3;
	   private int cntPage3 = 5;

	   private String member_id;
	   
	   public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public PagingVO3() {
	   }

	public PagingVO3(int total3, int nowPage3, int cntPerPage3) {
	      setNowPage3(nowPage3);
	      setCntPerPage3(cntPerPage3);
	      setTotal3(total3);
	      calcLastPage3(getTotal3(), getCntPerPage3());
	      calcStartEndPage3(getNowPage3(), cntPage3);
	      calcStartEnd3(getNowPage3(), getCntPerPage3());
	   }

	   public int getNowPage3() {
		return nowPage3;
	}
	public void setNowPage3(int nowPage3) {
		this.nowPage3 = nowPage3;
	}
	public int getStartPage3() {
		return startPage3;
	}
	public void setStartPage3(int startPage3) {
		this.startPage3 = startPage3;
	}
	public int getEndPage3() {
		return endPage3;
	}
	public void setEndPage3(int endPage3) {
		this.endPage3 = endPage3;
	}
	public int getTotal3() {
		return total3;
	}
	public void setTotal3(int total3) {
		this.total3 = total3;
	}
	public int getCntPerPage3() {
		return cntPerPage3;
	}
	public void setCntPerPage3(int cntPerPage3) {
		this.cntPerPage3 = cntPerPage3;
	}
	public int getLastPage3() {
		return lastPage3;
	}
	public void setLastPage3(int lastPage3) {
		this.lastPage3 = lastPage3;
	}
	public int getStart3() {
		return start3;
	}
	public void setStart3(int start3) {
		this.start3 = start3;
	}
	public int getEnd3() {
		return end3;
	}
	public void setEnd3(int end3) {
		this.end3 = end3;
	}
	public int getCntPage3() {
		return cntPage3;
	}
	public void setCntPage3(int cntPage3) {
		this.cntPage3 = cntPage3;
	}
	// 제일 마지막 페이지 계산
	   public void calcLastPage3(int total3, int cntPerPage3) {
	      setLastPage3((int) Math.ceil((double)total3 / (double)cntPerPage3));
	   }
	   // 시작, 끝 페이지 계산
	   public void calcStartEndPage3(int nowPage3, int cntPage3) {
	      setEndPage3(((int)Math.ceil((double)nowPage3 / (double)cntPage3)) * cntPage3);
	      if (getLastPage3() < getEndPage3()) {
	         setEndPage3(getLastPage3());
	      }
	      setStartPage3(getEndPage3() - cntPage3 + 1);
	      if (getStartPage3() < 1) {
	         setStartPage3(1);
	      }
	   }
	   // DB 쿼리에서 사용할 start, end값 계산
	   public void calcStartEnd3(int nowPage3, int cntPerPage3) {
	      setEnd3(nowPage3 * cntPerPage3);
	      setStart3(getEnd3() - cntPerPage3 + 1);
	   }

}
