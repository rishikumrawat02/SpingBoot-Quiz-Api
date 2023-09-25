package com.rishabh.quizapp.model;

public class Response {
	private Integer id; 
	private String resp;
	public Response(Integer id, String resp) {
		super();
		this.id = id;
		this.resp = resp;
	}
	@Override
	public String toString() {
		return "Response [id=" + id + ", resp=" + resp + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getResp() {
		return resp;
	}
	public void setResp(String resp) {
		this.resp = resp;
	}
}
