package com.crfeb.tbmpt.gpztcl.base.model;

import java.util.HashMap;
import java.util.Map;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
@TableName("GPZTCL_DAOXIANG")
public class DaoXiang {
	
	@TableId(type = IdType.UUID)
    private String id;
	
	@TableField(value="recordstatus")
	private String recordstatus;
	
	@TableField(value="odate")
	private String odate;
	
	@TableField(value="stime")
	private String stime;
	
	@TableField(value="sdate")
	private String sdate;
	
	@TableField(value="advanceno")
	private String advanceno;
	
	@TableField(value="bt1nr")
	private String bt1nr;
	
	@TableField(value="bt1y")
	private String bt1y;
	
	@TableField(value="bt1x")
	private String bt1x;
	
	@TableField(value="bt1z")
	private String bt1z;
	
	@TableField(value="bt1st")
	private String bt1st;
	
	@TableField(value="bt2nr")
	private String bt2nr;
	
	@TableField(value="bt2y")
	private String bt2y;
	
	@TableField(value="bt2x")
	private String bt2x;
	
	@TableField(value="bt2z")
	private String bt2z;
	
	@TableField(value="bt2st")
	private String bt2st;
	
	@TableField(value="laserstnr")
	private String laserstnr;
	
	@TableField(value="lasersty")
	private String lasersty;
	
	@TableField(value="laserstx")
	private String laserstx;
	
	@TableField(value="laserstz")
	private String laserstz;
	
	@TableField(value="laserstst")
	private String laserstst;
	
	@TableField(value="laserst2nr")
	private String laserst2nr;
	
	@TableField(value="laserst2y")
	private String laserst2y;
	
	@TableField(value="laserst2x")
	private String laserst2x;
	
	@TableField(value="laserst2z")
	private String laserst2z;
	
	@TableField(value="laserst2st")
	private String laserst2st;
	
	@TableField(value="dyels")
	private String dyels;
	
	@TableField(value="dzels")
	private String dzels;
	
	@TableField(value="rollels")
	private String rollels;
	
	@TableField(value="nickels")
	private String nickels;
	
	@TableField(value="gierels")
	private String gierels;
	
	@TableField(value="ych")
	private String ych;
	
	@TableField(value="xch")
	private String xch;
	
	@TableField(value="zch")
	private String zch;
	
	@TableField(value="hosch")
	private String hosch;
	
	@TableField(value="vosch")
	private String vosch;
	
	@TableField(value="stch")
	private String stch;
	
	@TableField(value="riwilaserels")
	private String riwilaserels;
	
	@TableField(value="riwilaserbt")
	private String riwilaserbt;
	
	@TableField(value="hdlaserels")
	private String hdlaserels;
	
	@TableField(value="hdlaserbt")
	private String hdlaserbt;
	
	@TableField(value="vzlaserels")
	private String vzlaserels;
	
	@TableField(value="vzlaserbt")
	private String vzlaserbt;
	
	@TableField(value="ymav")
	private String ymav;
	
	@TableField(value="xmav")
	private String xmav;
	
	@TableField(value="zmav")
	private String zmav;
	
	@TableField(value="hosmav")
	private String hosmav;
	
	@TableField(value="vosmav")
	private String vosmav;
	
	@TableField(value="stmav")
	private String stmav;
	
	@TableField(value="deltattbm")
	private String deltattbm;
	
	@TableField(value="deltavtbm")
	private String deltavtbm;
	
	@TableField(value="deltatch")
	private String deltatch;
	
	@TableField(value="deltavch")
	private String deltavch;
	
	@TableField(value="gierch")
	private String gierch;
	
	@TableField(value="nickch")
	private String nickch;
	
	@TableField(value="amplitude")
	private String amplitude;
	
	@TableField(value="hosmah")
	private String hosmah;
	
	@TableField(value="vosmah")
	private String vosmah;
	
	@TableField(value="stmah")
	private String stmah;
	
	@TableField(value="ymah")
	private String ymah;
	
	@TableField(value="xmah")
	private String xmah;
	
	@TableField(value="zmah")
	private String zmah;
	
	@TableField(value="rbtyp")
	private String rbtyp;
	
	@TableField(value="rbssp")
	private String rbssp;
	
	@TableField(value="rbsta")
	private String rbsta;
	
	@TableField(value="rbhos")
	private String rbhos;
	
	@TableField(value="rbvos")
	private String rbvos;
	
	@TableField(value="rby")
	private String rby;
	
	@TableField(value="rbx")
	private String rbx;
	
	@TableField(value="rbz")
	private String rbz;
	
	@TableField(value="rbsslo")
	private String rbsslo;
	
	@TableField(value="rbsslr")
	private String rbsslr;
	
	@TableField(value="rbsslu")
	private String rbsslu;
	
	@TableField(value="rbssll")
	private String rbssll;
	
	@TableField(value="rbdeltat")
	private String rbdeltat;
	
	@TableField(value="rbdeltav")
	private String rbdeltav;
	
	@TableField(value="rbstat")
	private String rbstat;
	
	@TableField(value="sslo")
	private String sslo;
	
	@TableField(value="sslr")
	private String sslr;
	
	@TableField(value="sslu")
	private String sslu;
	
	@TableField(value="ssll")
	private String ssll;
	
	@TableField(value="rbdir")
	private String rbdir;
	
	@TableField(value="vtpo")
	private String vtpo;
	
	@TableField(value="vtpr")
	private String vtpr;
	
	@TableField(value="vtpl")
	private String vtpl;
	
	@TableField(value="vtpu")
	private String vtpu;
	
	@TableField(value="stpo")
	private String stpo;
	
	@TableField(value="stpr")
	private String stpr;
	
	@TableField(value="stpl")
	private String stpl;
	
	@TableField(value="stpu")
	private String stpu;
	
	@TableField(value="sgelo")
	private String sgelo;
	
	@TableField(value="sgelr")
	private String sgelr;
	
	@TableField(value="sgell")
	private String sgell;
	
	@TableField(value="sgelu")
	private String sgelu;
	
	@TableField(value="ypp")
	private String ypp;
	
	@TableField(value="xpp")
	private String xpp;
	
	@TableField(value="zpp")
	private String zpp;
	
	@TableField(value="hospp")
	private String hospp;
	
	@TableField(value="vospp")
	private String vospp;
	
	@TableField(value="stpp")
	private String stpp;
	
	@TableField(value="ycw")
	private String ycw;
	
	@TableField(value="xcw")
	private String xcw;
	
	@TableField(value="zcw")
	private String zcw;
	
	@TableField(value="hoscw")
	private String hoscw;
	
	@TableField(value="voscw")
	private String voscw;
	
	@TableField(value="stcw")
	private String stcw;
	
	@TableField(value="projectid")
	private String projectid;
	
	@TableField(value="rbhosring")
	private String rbhosring;
	
	@TableField(value="rbvosring")
	private String rbvosring;
	
	@TableField(value="vtp2o")
	private String vtp2o;
	
	@TableField(value="vtp2r")
	private String vtp2r;
	
	@TableField(value="vtp2u")
	private String vtp2u;
	
	@TableField(value="vtp2l")
	private String vtp2l;
	
	@TableField(value="postrb_sta")
	private String postrb_sta;
	
	@TableField(value="postrb_hos")
	private String postrb_hos;
	
	@TableField(value="postrb_vos")
	private String postrb_vos;
	
	@TableField(value="postrb_x")
	private String postrb_x;
	
	@TableField(value="postrb_y")
	private String postrb_y;
	
	@TableField(value="postrb_z")
	private String postrb_z;
	
	@TableField(value="postrb_sslo")
	private String postrb_sslo;
	
	@TableField(value="postrb_sslr")
	private String postrb_sslr;
	
	@TableField(value="postrb_sslu")
	private String postrb_sslu;
	
	@TableField(value="postrb_ssll")
	private String postrb_ssll;
	
	@TableField(value="postrb_vtpo")
	private String postrb_vtpo;
	
	@TableField(value="postrb_vtpr")
	private String postrb_vtpr;
	
	@TableField(value="postrb_vtpu")
	private String postrb_vtpu;
	
	@TableField(value="postrb_vtpl")
	private String postrb_vtpl;
	
	@TableField(value="createUserId")
	private String createUserId;
	
	@TableField(value="createTime")
	private String createTime;
	
	public void copyValue(String[] clums){
		this.setStime(clums[0]);
		this.setSdate(clums[1]);
		this.setAdvanceno(clums[2]);
		this.setBt1nr(clums[3]);
		this.setBt1y(clums[4]);
		this.setBt1x(clums[5]);
		this.setBt1z(clums[6]);
		this.setBt1st(clums[7]);
		this.setBt2nr(clums[8]);
		this.setBt2y(clums[9]);
		this.setBt2x(clums[10]);
		this.setBt2z(clums[11]);
		this.setBt2st(clums[12]);
		this.setLaserstnr(clums[13]);
		this.setLasersty(clums[14]);
		this.setLaserstx(clums[15]);
		this.setLaserstz(clums[16]);
		this.setLaserstst(clums[17]);
		this.setLaserst2nr(clums[18]);
		this.setLaserst2y(clums[19]);
		this.setLaserst2x(clums[20]);
		this.setLaserst2z(clums[21]);
		this.setLaserst2st(clums[22]);
		this.setDyels(clums[23]);
		this.setDzels(clums[24]);
		this.setRollels(clums[25]);
		this.setNickels(clums[26]);
		this.setGierels(clums[27]);
		this.setYch(clums[28]);
		this.setXch(clums[29]);
		this.setZch(clums[30]);
		this.setHosch(clums[31]);
		this.setVosch(clums[32]);
		this.setStch(clums[33]);
		this.setRiwilaserels(clums[34]);
		this.setRiwilaserbt(clums[35]);
		this.setHdlaserels(clums[36]);
		this.setHdlaserbt(clums[37]);
		this.setVzlaserels(clums[38]);
		this.setVzlaserbt(clums[39]);
		this.setYmav(clums[40]);
		this.setXmav(clums[41]);
		this.setZmav(clums[42]);
		this.setHosmav(clums[43]);
		this.setVosmav(clums[44]);
		this.setStmav(clums[45]);
		this.setDeltattbm(clums[46]);
		this.setDeltavtbm(clums[47]);
		this.setDeltatch(clums[48]);
		this.setDeltavch(clums[49]);
		this.setGierch(clums[50]);
		this.setNickch(clums[51]);
		this.setAmplitude(clums[52]);
		this.setHosmah(clums[53]);
		this.setVosmah(clums[54]);
		this.setStmah(clums[55]);
		this.setYmah(clums[56]);
		this.setXmah(clums[57]);
		this.setZmah(clums[58]);
		this.setRbtyp(clums[59]);
		this.setRbssp(clums[60]);
		this.setRbsta(clums[61]);
		this.setRbhos(clums[62]);
		this.setRbvos(clums[63]);
		this.setRby(clums[64]);
		this.setRbx(clums[65]);
		this.setRbz(clums[66]);
		this.setRbsslo(clums[67]);
		this.setRbsslr(clums[68]);
		this.setRbsslu(clums[69]);
		this.setRbssll(clums[70]);
		this.setRbdeltat(clums[71]);
		this.setRbdeltav(clums[72]);
		this.setRbstat(clums[73]);
		this.setSslo(clums[74]);
		this.setSslr(clums[75]);
		this.setSslu(clums[76]);
		this.setSsll(clums[77]);
		this.setRbdir(clums[78]);
		this.setVtpo(clums[79]);
		this.setVtpr(clums[80]);
		this.setVtpl(clums[81]);
		this.setVtpu(clums[82]);
		this.setStpo(clums[83]);
		this.setStpr(clums[84]);
		this.setStpl(clums[85]);
		this.setStpu(clums[86]);
		this.setSgelo(clums[87]);
		this.setSgelr(clums[88]);
		this.setSgell(clums[89]);
		this.setSgelu(clums[90]);
		this.setYpp(clums[91]);
		this.setXpp(clums[92]);
		this.setZpp(clums[93]);
		this.setHospp(clums[94]);
		this.setVospp(clums[95]);
		this.setStpp(clums[96]);
		this.setYcw(clums[97]);
		this.setXcw(clums[98]);
		this.setZcw(clums[99]);
		this.setHoscw(clums[100]);
		this.setVoscw(clums[101]);
		this.setStcw(clums[102]);
		this.setRbhosring(clums[103]);
		this.setRbvosring(clums[104]);
		this.setVtp2o(clums[105]);
		this.setVtp2r(clums[106]);
		this.setVtp2u(clums[107]);
		this.setVtp2l(clums[108]);
		this.setPostrb_sta(clums[109]);
		this.setPostrb_hos(clums[110]);
		this.setPostrb_vos(clums[111]);
		this.setPostrb_x(clums[112]);
		this.setPostrb_y(clums[113]);
		this.setPostrb_z(clums[114]);
		this.setPostrb_sslo(clums[115]);
		this.setPostrb_sslr(clums[116]);
		this.setPostrb_sslu(clums[117]);
		this.setPostrb_ssll(clums[118]);
		this.setPostrb_vtpo(clums[119]);
		this.setPostrb_vtpr(clums[120]);
		this.setPostrb_vtpu(clums[121]);
		this.setPostrb_vtpl(clums[122]);
	}
	
	
	
	public String findFile(int num){
		Map<Integer, String> mapFile = new HashMap<>();
		return mapFile.get(num);
	}
	
	public String getRecordstatus() {
		return recordstatus;
	}
	public void setRecordstatus(String recordstatus) {
		this.recordstatus = recordstatus;
	}
	public String getOdate() {
		return odate;
	}
	public void setOdate(String odate) {
		this.odate = odate;
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public String getAdvanceno() {
		return advanceno;
	}
	public void setAdvanceno(String advanceno) {
		this.advanceno = advanceno;
	}
	public String getBt1nr() {
		return bt1nr;
	}
	public void setBt1nr(String bt1nr) {
		this.bt1nr = bt1nr;
	}
	public String getBt1y() {
		return bt1y;
	}
	public void setBt1y(String bt1y) {
		this.bt1y = bt1y;
	}
	public String getBt1x() {
		return bt1x;
	}
	public void setBt1x(String bt1x) {
		this.bt1x = bt1x;
	}
	public String getBt1z() {
		return bt1z;
	}
	public void setBt1z(String bt1z) {
		this.bt1z = bt1z;
	}
	public String getBt1st() {
		return bt1st;
	}
	public void setBt1st(String bt1st) {
		this.bt1st = bt1st;
	}
	public String getBt2nr() {
		return bt2nr;
	}
	public void setBt2nr(String bt2nr) {
		this.bt2nr = bt2nr;
	}
	public String getBt2y() {
		return bt2y;
	}
	public void setBt2y(String bt2y) {
		this.bt2y = bt2y;
	}
	public String getBt2x() {
		return bt2x;
	}
	public void setBt2x(String bt2x) {
		this.bt2x = bt2x;
	}
	public String getBt2z() {
		return bt2z;
	}
	public void setBt2z(String bt2z) {
		this.bt2z = bt2z;
	}
	public String getBt2st() {
		return bt2st;
	}
	public void setBt2st(String bt2st) {
		this.bt2st = bt2st;
	}
	public String getLaserstnr() {
		return laserstnr;
	}
	public void setLaserstnr(String laserstnr) {
		this.laserstnr = laserstnr;
	}
	public String getLasersty() {
		return lasersty;
	}
	public void setLasersty(String lasersty) {
		this.lasersty = lasersty;
	}
	public String getLaserstx() {
		return laserstx;
	}
	public void setLaserstx(String laserstx) {
		this.laserstx = laserstx;
	}
	public String getLaserstz() {
		return laserstz;
	}
	public void setLaserstz(String laserstz) {
		this.laserstz = laserstz;
	}
	public String getLaserstst() {
		return laserstst;
	}
	public void setLaserstst(String laserstst) {
		this.laserstst = laserstst;
	}
	public String getLaserst2nr() {
		return laserst2nr;
	}
	public void setLaserst2nr(String laserst2nr) {
		this.laserst2nr = laserst2nr;
	}
	public String getLaserst2y() {
		return laserst2y;
	}
	public void setLaserst2y(String laserst2y) {
		this.laserst2y = laserst2y;
	}
	public String getLaserst2x() {
		return laserst2x;
	}
	public void setLaserst2x(String laserst2x) {
		this.laserst2x = laserst2x;
	}
	public String getLaserst2z() {
		return laserst2z;
	}
	public void setLaserst2z(String laserst2z) {
		this.laserst2z = laserst2z;
	}
	public String getLaserst2st() {
		return laserst2st;
	}
	public void setLaserst2st(String laserst2st) {
		this.laserst2st = laserst2st;
	}
	public String getDyels() {
		return dyels;
	}
	public void setDyels(String dyels) {
		this.dyels = dyels;
	}
	public String getDzels() {
		return dzels;
	}
	public void setDzels(String dzels) {
		this.dzels = dzels;
	}
	public String getRollels() {
		return rollels;
	}
	public void setRollels(String rollels) {
		this.rollels = rollels;
	}
	public String getNickels() {
		return nickels;
	}
	public void setNickels(String nickels) {
		this.nickels = nickels;
	}
	public String getGierels() {
		return gierels;
	}
	public void setGierels(String gierels) {
		this.gierels = gierels;
	}
	public String getYch() {
		return ych;
	}
	public void setYch(String ych) {
		this.ych = ych;
	}
	public String getXch() {
		return xch;
	}
	public void setXch(String xch) {
		this.xch = xch;
	}
	public String getZch() {
		return zch;
	}
	public void setZch(String zch) {
		this.zch = zch;
	}
	public String getHosch() {
		return hosch;
	}
	public void setHosch(String hosch) {
		this.hosch = hosch;
	}
	public String getVosch() {
		return vosch;
	}
	public void setVosch(String vosch) {
		this.vosch = vosch;
	}
	public String getStch() {
		return stch;
	}
	public void setStch(String stch) {
		this.stch = stch;
	}
	public String getRiwilaserels() {
		return riwilaserels;
	}
	public void setRiwilaserels(String riwilaserels) {
		this.riwilaserels = riwilaserels;
	}
	public String getRiwilaserbt() {
		return riwilaserbt;
	}
	public void setRiwilaserbt(String riwilaserbt) {
		this.riwilaserbt = riwilaserbt;
	}
	public String getHdlaserels() {
		return hdlaserels;
	}
	public void setHdlaserels(String hdlaserels) {
		this.hdlaserels = hdlaserels;
	}
	public String getHdlaserbt() {
		return hdlaserbt;
	}
	public void setHdlaserbt(String hdlaserbt) {
		this.hdlaserbt = hdlaserbt;
	}
	public String getVzlaserels() {
		return vzlaserels;
	}
	public void setVzlaserels(String vzlaserels) {
		this.vzlaserels = vzlaserels;
	}
	public String getVzlaserbt() {
		return vzlaserbt;
	}
	public void setVzlaserbt(String vzlaserbt) {
		this.vzlaserbt = vzlaserbt;
	}
	public String getYmav() {
		return ymav;
	}
	public void setYmav(String ymav) {
		this.ymav = ymav;
	}
	public String getXmav() {
		return xmav;
	}
	public void setXmav(String xmav) {
		this.xmav = xmav;
	}
	public String getZmav() {
		return zmav;
	}
	public void setZmav(String zmav) {
		this.zmav = zmav;
	}
	public String getHosmav() {
		return hosmav;
	}
	public void setHosmav(String hosmav) {
		this.hosmav = hosmav;
	}
	public String getVosmav() {
		return vosmav;
	}
	public void setVosmav(String vosmav) {
		this.vosmav = vosmav;
	}
	public String getStmav() {
		return stmav;
	}
	public void setStmav(String stmav) {
		this.stmav = stmav;
	}
	public String getDeltattbm() {
		return deltattbm;
	}
	public void setDeltattbm(String deltattbm) {
		this.deltattbm = deltattbm;
	}
	public String getDeltavtbm() {
		return deltavtbm;
	}
	public void setDeltavtbm(String deltavtbm) {
		this.deltavtbm = deltavtbm;
	}
	public String getDeltatch() {
		return deltatch;
	}
	public void setDeltatch(String deltatch) {
		this.deltatch = deltatch;
	}
	public String getDeltavch() {
		return deltavch;
	}
	public void setDeltavch(String deltavch) {
		this.deltavch = deltavch;
	}
	public String getGierch() {
		return gierch;
	}
	public void setGierch(String gierch) {
		this.gierch = gierch;
	}
	public String getNickch() {
		return nickch;
	}
	public void setNickch(String nickch) {
		this.nickch = nickch;
	}
	public String getAmplitude() {
		return amplitude;
	}
	public void setAmplitude(String amplitude) {
		this.amplitude = amplitude;
	}
	public String getHosmah() {
		return hosmah;
	}
	public void setHosmah(String hosmah) {
		this.hosmah = hosmah;
	}
	public String getVosmah() {
		return vosmah;
	}
	public void setVosmah(String vosmah) {
		this.vosmah = vosmah;
	}
	public String getStmah() {
		return stmah;
	}
	public void setStmah(String stmah) {
		this.stmah = stmah;
	}
	public String getYmah() {
		return ymah;
	}
	public void setYmah(String ymah) {
		this.ymah = ymah;
	}
	public String getXmah() {
		return xmah;
	}
	public void setXmah(String xmah) {
		this.xmah = xmah;
	}
	public String getZmah() {
		return zmah;
	}
	public void setZmah(String zmah) {
		this.zmah = zmah;
	}
	public String getRbtyp() {
		return rbtyp;
	}
	public void setRbtyp(String rbtyp) {
		this.rbtyp = rbtyp;
	}
	public String getRbssp() {
		return rbssp;
	}
	public void setRbssp(String rbssp) {
		this.rbssp = rbssp;
	}
	public String getRbsta() {
		return rbsta;
	}
	public void setRbsta(String rbsta) {
		this.rbsta = rbsta;
	}
	public String getRbhos() {
		return rbhos;
	}
	public void setRbhos(String rbhos) {
		this.rbhos = rbhos;
	}
	public String getRbvos() {
		return rbvos;
	}
	public void setRbvos(String rbvos) {
		this.rbvos = rbvos;
	}
	public String getRby() {
		return rby;
	}
	public void setRby(String rby) {
		this.rby = rby;
	}
	public String getRbx() {
		return rbx;
	}
	public void setRbx(String rbx) {
		this.rbx = rbx;
	}
	public String getRbz() {
		return rbz;
	}
	public void setRbz(String rbz) {
		this.rbz = rbz;
	}
	public String getRbsslo() {
		return rbsslo;
	}
	public void setRbsslo(String rbsslo) {
		this.rbsslo = rbsslo;
	}
	public String getRbsslr() {
		return rbsslr;
	}
	public void setRbsslr(String rbsslr) {
		this.rbsslr = rbsslr;
	}
	public String getRbsslu() {
		return rbsslu;
	}
	public void setRbsslu(String rbsslu) {
		this.rbsslu = rbsslu;
	}
	public String getRbssll() {
		return rbssll;
	}
	public void setRbssll(String rbssll) {
		this.rbssll = rbssll;
	}
	public String getRbdeltat() {
		return rbdeltat;
	}
	public void setRbdeltat(String rbdeltat) {
		this.rbdeltat = rbdeltat;
	}
	public String getRbdeltav() {
		return rbdeltav;
	}
	public void setRbdeltav(String rbdeltav) {
		this.rbdeltav = rbdeltav;
	}
	public String getRbstat() {
		return rbstat;
	}
	public void setRbstat(String rbstat) {
		this.rbstat = rbstat;
	}
	public String getSslo() {
		return sslo;
	}
	public void setSslo(String sslo) {
		this.sslo = sslo;
	}
	public String getSslr() {
		return sslr;
	}
	public void setSslr(String sslr) {
		this.sslr = sslr;
	}
	public String getSslu() {
		return sslu;
	}
	public void setSslu(String sslu) {
		this.sslu = sslu;
	}
	public String getSsll() {
		return ssll;
	}
	public void setSsll(String ssll) {
		this.ssll = ssll;
	}
	public String getRbdir() {
		return rbdir;
	}
	public void setRbdir(String rbdir) {
		this.rbdir = rbdir;
	}
	public String getVtpo() {
		return vtpo;
	}
	public void setVtpo(String vtpo) {
		this.vtpo = vtpo;
	}
	public String getVtpr() {
		return vtpr;
	}
	public void setVtpr(String vtpr) {
		this.vtpr = vtpr;
	}
	public String getVtpl() {
		return vtpl;
	}
	public void setVtpl(String vtpl) {
		this.vtpl = vtpl;
	}
	public String getVtpu() {
		return vtpu;
	}
	public void setVtpu(String vtpu) {
		this.vtpu = vtpu;
	}
	public String getStpo() {
		return stpo;
	}
	public void setStpo(String stpo) {
		this.stpo = stpo;
	}
	public String getStpr() {
		return stpr;
	}
	public void setStpr(String stpr) {
		this.stpr = stpr;
	}
	public String getStpl() {
		return stpl;
	}
	public void setStpl(String stpl) {
		this.stpl = stpl;
	}
	public String getStpu() {
		return stpu;
	}
	public void setStpu(String stpu) {
		this.stpu = stpu;
	}
	public String getSgelo() {
		return sgelo;
	}
	public void setSgelo(String sgelo) {
		this.sgelo = sgelo;
	}
	public String getSgelr() {
		return sgelr;
	}
	public void setSgelr(String sgelr) {
		this.sgelr = sgelr;
	}
	public String getSgell() {
		return sgell;
	}
	public void setSgell(String sgell) {
		this.sgell = sgell;
	}
	public String getSgelu() {
		return sgelu;
	}
	public void setSgelu(String sgelu) {
		this.sgelu = sgelu;
	}
	public String getYpp() {
		return ypp;
	}
	public void setYpp(String ypp) {
		this.ypp = ypp;
	}
	public String getXpp() {
		return xpp;
	}
	public void setXpp(String xpp) {
		this.xpp = xpp;
	}
	public String getZpp() {
		return zpp;
	}
	public void setZpp(String zpp) {
		this.zpp = zpp;
	}
	public String getHospp() {
		return hospp;
	}
	public void setHospp(String hospp) {
		this.hospp = hospp;
	}
	public String getVospp() {
		return vospp;
	}
	public void setVospp(String vospp) {
		this.vospp = vospp;
	}
	public String getStpp() {
		return stpp;
	}
	public void setStpp(String stpp) {
		this.stpp = stpp;
	}
	public String getYcw() {
		return ycw;
	}
	public void setYcw(String ycw) {
		this.ycw = ycw;
	}
	public String getXcw() {
		return xcw;
	}
	public void setXcw(String xcw) {
		this.xcw = xcw;
	}
	public String getZcw() {
		return zcw;
	}
	public void setZcw(String zcw) {
		this.zcw = zcw;
	}
	public String getHoscw() {
		return hoscw;
	}
	public void setHoscw(String hoscw) {
		this.hoscw = hoscw;
	}
	public String getVoscw() {
		return voscw;
	}
	public void setVoscw(String voscw) {
		this.voscw = voscw;
	}
	public String getStcw() {
		return stcw;
	}
	public void setStcw(String stcw) {
		this.stcw = stcw;
	}
	public String getProjectid() {
		return projectid;
	}
	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}
	public String getRbhosring() {
		return rbhosring;
	}
	public void setRbhosring(String rbhosring) {
		this.rbhosring = rbhosring;
	}
	public String getRbvosring() {
		return rbvosring;
	}
	public void setRbvosring(String rbvosring) {
		this.rbvosring = rbvosring;
	}
	public String getVtp2o() {
		return vtp2o;
	}
	public void setVtp2o(String vtp2o) {
		this.vtp2o = vtp2o;
	}
	public String getVtp2r() {
		return vtp2r;
	}
	public void setVtp2r(String vtp2r) {
		this.vtp2r = vtp2r;
	}
	public String getVtp2u() {
		return vtp2u;
	}
	public void setVtp2u(String vtp2u) {
		this.vtp2u = vtp2u;
	}
	public String getVtp2l() {
		return vtp2l;
	}
	public void setVtp2l(String vtp2l) {
		this.vtp2l = vtp2l;
	}
	public String getPostrb_sta() {
		return postrb_sta;
	}
	public void setPostrb_sta(String postrb_sta) {
		this.postrb_sta = postrb_sta;
	}
	public String getPostrb_hos() {
		return postrb_hos;
	}
	public void setPostrb_hos(String postrb_hos) {
		this.postrb_hos = postrb_hos;
	}
	public String getPostrb_vos() {
		return postrb_vos;
	}
	public void setPostrb_vos(String postrb_vos) {
		this.postrb_vos = postrb_vos;
	}
	public String getPostrb_x() {
		return postrb_x;
	}
	public void setPostrb_x(String postrb_x) {
		this.postrb_x = postrb_x;
	}
	public String getPostrb_y() {
		return postrb_y;
	}
	public void setPostrb_y(String postrb_y) {
		this.postrb_y = postrb_y;
	}
	public String getPostrb_z() {
		return postrb_z;
	}
	public void setPostrb_z(String postrb_z) {
		this.postrb_z = postrb_z;
	}
	public String getPostrb_sslo() {
		return postrb_sslo;
	}
	public void setPostrb_sslo(String postrb_sslo) {
		this.postrb_sslo = postrb_sslo;
	}
	public String getPostrb_sslr() {
		return postrb_sslr;
	}
	public void setPostrb_sslr(String postrb_sslr) {
		this.postrb_sslr = postrb_sslr;
	}
	public String getPostrb_sslu() {
		return postrb_sslu;
	}
	public void setPostrb_sslu(String postrb_sslu) {
		this.postrb_sslu = postrb_sslu;
	}
	public String getPostrb_ssll() {
		return postrb_ssll;
	}
	public void setPostrb_ssll(String postrb_ssll) {
		this.postrb_ssll = postrb_ssll;
	}
	public String getPostrb_vtpo() {
		return postrb_vtpo;
	}
	public void setPostrb_vtpo(String postrb_vtpo) {
		this.postrb_vtpo = postrb_vtpo;
	}
	public String getPostrb_vtpr() {
		return postrb_vtpr;
	}
	public void setPostrb_vtpr(String postrb_vtpr) {
		this.postrb_vtpr = postrb_vtpr;
	}
	public String getPostrb_vtpu() {
		return postrb_vtpu;
	}
	public void setPostrb_vtpu(String postrb_vtpu) {
		this.postrb_vtpu = postrb_vtpu;
	}
	public String getPostrb_vtpl() {
		return postrb_vtpl;
	}
	public void setPostrb_vtpl(String postrb_vtpl) {
		this.postrb_vtpl = postrb_vtpl;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getCreateUserId() {
		return createUserId;
	}



	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}



	public String getCreateTime() {
		return createTime;
	}



	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
	
	

}
