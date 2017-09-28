package com.crfeb.tbmpt.sgcj.model;

import java.util.List;

import com.crfeb.tbmpt.project.model.ProRProjectSection;
import com.crfeb.tbmpt.sgkshgl.jcdtgl.model.SgkshglJcdtgl;

public class SgcjSec {

	private ProRProjectSection sec;
	private SgkshglJcdtgl     jcdt; 
	private List<SgcjLine> lines;
	
	public ProRProjectSection getSec() {
		return sec;
	}
	public void setSec(ProRProjectSection sec) {
		this.sec = sec;
	}
	public SgkshglJcdtgl getJcdt() {
		return jcdt;
	}
	public void setJcdt(SgkshglJcdtgl jcdt) {
		this.jcdt = jcdt;
	}
	public List<SgcjLine> getLines() {
		return lines;
	}
	public void setLines(List<SgcjLine> lines) {
		this.lines = lines;
	} 
}
