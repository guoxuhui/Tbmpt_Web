package com.crfeb.tbmpt.sgcj.model;

import com.crfeb.tbmpt.project.model.ProRSectionLine;
import com.crfeb.tbmpt.sgkshgl.jcdtgl.model.SgkshglJcdtgl;

public class SgcjLine {

	private ProRSectionLine line;
	private SgkshglJcdtgl   jcdt;
	
	public ProRSectionLine getLine() {
		return line;
	}
	public void setLine(ProRSectionLine line) {
		this.line = line;
	}
	public SgkshglJcdtgl getJcdt() {
		return jcdt;
	}
	public void setJcdt(SgkshglJcdtgl jcdt) {
		this.jcdt = jcdt;
	}
}
