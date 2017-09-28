package com.crfeb.tbmpt.commons.wsdl;

import java.rmi.RemoteException;

public class test {

	public static void main(String[] args) {
		String xyys = "[{qdzh:-26105.19, zdzh:-25974.267,qdzbX:46039.5323, qdzbY:80807.7487, qdfwj_dms:-5.520035, qdfwj:0, qdbj:0, zdbj:0, zx:0,qdpyl:0,zdpyl:0},{qdzh:0,         zdzh:-25914.267,qdzbX:0, qdzbY:0, qdfwj_dms:0, qdfwj:0, qdbj:0,   zdbj:370, zx:-1,qdpyl:0,     zdpyl:0.138}, {qdzh:0,         zdzh:-25456.61, qdzbX:0, qdzbY:0, qdfwj_dms:0, qdfwj:0, qdbj:370, zdbj:370, zx:-1,qdpyl:0.138, zdpyl:0.138},{qdzh:0,         zdzh:-25396.61, qdzbX:0, qdzbY:0, qdfwj_dms:0, qdfwj:0, qdbj:370, zdbj:0,   zx:-1,qdpyl:0.138, zdpyl:0},{qdzh:0,         zdzh:-24952.16, qdzbX:0, qdzbY:0, qdfwj_dms:0, qdfwj:0, qdbj:0,   zdbj:0,   zx:0, qdpyl:0,     zdpyl:0}]";
		String sqys = "[{bpd_zh:-26310, bpd_gc:-16.4197,sjqxbj: 0},{bpd_zh:-25940, bpd_gc:-15.6797,sjqxbj: 3000},{bpd_zh:-25400, bpd_gc:-17.9497,sjqxbj: 5000},{bpd_zh:-25180, bpd_gc:-13.8697,sjqxbj: 5000},{bpd_zh:-24980, bpd_gc:-8.8697,sjqxbj: 3000},{bpd_zh: -24450, bpd_gc:-9.9297,sjqxbj: 0}]";
		String sczb = "[{gphs: 1 ,  zbX:  46039.5323 , zbY:  80807.7487 },{gphs: 2 ,  zbX:  46040.52706 , zbY:  80807.64648 },{gphs: 3 ,  zbX:  46041.52182 , zbY:  80807.54427 },{gphs: 4 ,  zbX:  46042.51659 , zbY:  80807.44205 },{gphs: 5 ,  zbX:  46043.51135 , zbY:  80807.33984 },{gphs: 6 ,  zbX:  46044.50611 , zbY:  80807.23762 },{gphs: 7 ,  zbX:  46045.50087 , zbY:  80807.13541 },{gphs: 8 ,  zbX:  46046.49564 , zbY:  80807.03319 },{gphs: 9 ,  zbX:  46047.4904 , zbY:  80806.93098 },{gphs: 10 ,  zbX:  46048.48516 , zbY:  80806.82876}]";
		
		SzzxSoapProxy proxy = new SzzxSoapProxy();
		String result = null;
		try {
			result = proxy.hqxlzxzb(xyys, sqys, sczb);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(result);
	}
}
