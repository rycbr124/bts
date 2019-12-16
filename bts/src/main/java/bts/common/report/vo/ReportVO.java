package bts.common.report.vo;

import java.sql.Timestamp;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("reportVO")
@Scope("prototype")
public class ReportVO {
	private int report_no;
	private Timestamp report_date;
	private String title;
	private String report_se;
	private String contents;
	private String pnish_cd;
	private String reporter_id;
	private String target_id;
	private String report_at;
	private String contents_cd;
	private String target_contents;
	
	private String showContents;
	private String pnish_name;
	private int day_cnt;
	
	public String getPnish_name() {
		return pnish_name;
	}
	public void setPnish_name(String pnish_name) {
		this.pnish_name = pnish_name;
	}
	public int getDay_cnt() {
		return day_cnt;
	}
	public void setDay_cnt(int day_cnt) {
		this.day_cnt = day_cnt;
	}
	public String getShowContents() {
		return showContents;
	}
	public void setShowContents(String showContents) {
		this.showContents = showContents;
	}
	public String getTarget_contents() {
		return target_contents;
	}
	public void setTarget_contents(String target_contents) {
		this.target_contents = target_contents;
	}
	public int getReport_no() {
		return report_no;
	}
	public void setReport_no(int report_no) {
		this.report_no = report_no;
	}
	public Timestamp getReport_date() {
		return report_date;
	}
	public void setReport_date(Timestamp report_date) {
		this.report_date = report_date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getReport_se() {
		return report_se;
	}
	public void setReport_se(String report_se) {
		this.report_se = report_se;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getPnish_cd() {
		return pnish_cd;
	}
	public void setPnish_cd(String pnish_cd) {
		this.pnish_cd = pnish_cd;
	}
	public String getReporter_id() {
		return reporter_id;
	}
	public void setReporter_id(String reporter_id) {
		this.reporter_id = reporter_id;
	}
	public String getTarget_id() {
		return target_id;
	}
	public void setTarget_id(String target_id) {
		this.target_id = target_id;
	}
	public String getReport_at() {
		return report_at;
	}
	public void setReport_at(String report_at) {
		this.report_at = report_at;
	}
	public String getContents_cd() {
		return contents_cd;
	}
	public void setContents_cd(String contents_cd) {
		this.contents_cd = contents_cd;
	}

	
}
