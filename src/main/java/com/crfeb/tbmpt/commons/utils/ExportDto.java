package com.crfeb.tbmpt.commons.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据导出参数Dto封装
 * @author：smxg
 * @date：2016-11-06 11:12
 */
public class ExportDto {
	
	private String fileName;
	private String fileType;
	private int all;
	private boolean isExpAll;
	private String fields;
	private List<String> fieldList = new ArrayList<String>();
	private String titles;
	private List<String> titleList = new ArrayList<String>();
	private String ids;
	private int pageNumber;
	private int pageSize;
	private int expPageNo;
	private int expPageSize;
	private int total;
	private Map<String, String> expColsMap = new HashMap<String, String>();
	private int expXlsMaxRows;
	private int expXlsMaxColumns;
	public static final String SPLIT_STR = ",";

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return this.fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public int getAll() {
		return this.all;
	}

	public void setAll(int all) {
		this.all = all;
	}

	public boolean isExpAll() {
		if (this.all == 1)
			this.isExpAll = true;
		else {
			this.isExpAll = false;
		}

		return this.isExpAll;
	}

	public void setExpAll(boolean isExpAll) {
		this.isExpAll = isExpAll;
	}

	public String getFields() {
		return this.fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	public String getTitles() {
		return this.titles;
	}

	public void setTitles(String titles) {
		this.titles = titles;
	}

	public int getPageNumber() {
		return this.pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getExpPageNo() {
		if (isExpAll())
			this.expPageNo = 1;
		else {
			this.expPageNo = getPageNumber();
		}

		return this.expPageNo;
	}

	public void setExpPageNo(int expPageNo) {
		this.expPageNo = expPageNo;
	}

	public int getExpPageSize() {
		if (isExpAll())
			this.expPageSize = getTotal();
		else {
			this.expPageSize = getPageSize();
		}

		if (this.expPageSize > getExpXlsMaxRows()) {
			this.expPageSize = getExpXlsMaxRows();
		}

		return this.expPageSize;
	}

	public void setExpPageSize(int expPageSize) {
		this.expPageSize = expPageSize;
	}

	public int getTotal() {
		return this.total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<String> getFieldList() {
		if ((this.fields != null) && (this.fields.length() > 0)) {
			this.fieldList = Arrays.asList(this.fields.split(SPLIT_STR));
		}

		if (this.fieldList.size() > getExpXlsMaxColumns()) {
			this.fieldList = this.fieldList.subList(0, getExpXlsMaxColumns());
		}

		return this.fieldList;
	}

	public void setFieldList(List<String> fieldList) {
		this.fieldList = fieldList;
	}

	public List<String> getTitleList() {
		if ((this.titles != null) && (this.titles.length() > 0)) {
			this.titleList = Arrays.asList(this.titles.split(SPLIT_STR));
		}

		if (this.titleList.size() > getExpXlsMaxColumns()) {
			this.titleList = this.titleList.subList(0, getExpXlsMaxColumns());
		}
		return this.titleList;
	}

	public void setTitleList(List<String> titleList) {
		this.titleList = titleList;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Map<String, String> getExpColsMap() {
		if ((this.fields != null) && (this.fields.length() > 0) && (this.titles != null)
				&& (this.titles.length() > 0)) {
			String[] farr = this.fields.split(SPLIT_STR);
			String[] tarr = this.titles.split(SPLIT_STR);

			if (farr.length == tarr.length) {
				for (int i = 0; i < farr.length; i++) {
					this.expColsMap.put(farr[i], tarr[i]);
				}
			}

		}

		return this.expColsMap;
	}

	public void setExpColsMap(Map<String, String> expColsMap) {
		this.expColsMap = expColsMap;
	}

	public int getExpXlsMaxRows() {
		return this.expXlsMaxRows;
	}

	public void setExpXlsMaxRows(int expXlsMaxRows) {
		this.expXlsMaxRows = expXlsMaxRows;
	}

	public int getExpXlsMaxColumns() {
		return this.expXlsMaxColumns;
	}

	public void setExpXlsMaxColumns(int expXlsMaxColumns) {
		this.expXlsMaxColumns = expXlsMaxColumns;
	}
}