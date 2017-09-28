package com.crfeb.tbmpt.commons.utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtil {

	public static void response(HttpServletResponse response, Object obj)
			throws Exception {
		if (obj instanceof InputStream) {
			InputStream is = (InputStream) obj;
			try {
				isToOs(is, response.getOutputStream());
			} finally {
				is.close();
			}
		} else {
			byte[] bytes;
			if (obj instanceof byte[])
				bytes = (byte[]) obj;
			else
				bytes = obj.toString().getBytes("utf-8");
			int len = bytes.length;
			if (len >= 10*1024*1024) {
				response.setHeader("Content-Encoding", "gzip");
				GZIPOutputStream gos = new GZIPOutputStream(response
						.getOutputStream());
				try {
					gos.write(bytes);
				} finally {
					gos.close();
				}
			} else {
				response.setContentLength(len);
				response.getOutputStream().write(bytes);
			}
		}
		response.flushBuffer();
	}

	public static void response(HttpServletResponse response, String obj,
			boolean successful) throws Exception {
		WebUtil.response(response, concat("{success:", Boolean
				.toString(successful), ",value:", quote(convertHTML(obj)), "}"));
	}
	
	public static int isToOs(InputStream is, OutputStream os) throws Exception {
		byte buf[] = new byte[8192];
		int len, size = 0;

		while ((len = is.read(buf)) > 0) {
			os.write(buf, 0, len);
			size += len;
		}
		return size;
	}
	
	public static String convertHTML(String string) {
		if (isEmpty(string))
			return "";
		int i, j = string.length();
		StringBuilder out = new StringBuilder();
		char c;

		for (i = 0; i < j; i++) {
			c = string.charAt(i);
			switch (c) {
			case '&':
				out.append("&amp;");
				break;
			case '>':
				out.append("&gt;");
				break;
			case '<':
				out.append("&lt;");
				break;
			default:
				out.append(c);
			}
		}
		return out.toString();
	}
	
	public static boolean isEmpty(String string) {
		return string == null || string.length() == 0;
	}
	
	public static String quote(String string) {
		if (string == null)
			return "\"\"";
		int len = string.length();
		if (len == 0)
			return "\"\"";
		char b, c = 0;
		int i;
		String t;
		StringBuilder sb = new StringBuilder(len + 4);

		sb.append('"');
		for (i = 0; i < len; i++) {
			b = c;
			c = string.charAt(i);
			switch (c) {
			case '\\':
			case '"':
				sb.append('\\');
				sb.append(c);
				break;
			case '/':
				if (b == '<')
					sb.append('\\');
				sb.append(c);
				break;
			case '\b':
				sb.append("\\b");
				break;
			case '\t':
				sb.append("\\t");
				break;
			case '\n':
				sb.append("\\n");
				break;
			case '\f':
				sb.append("\\f");
				break;
			case '\r':
				sb.append("\\r");
				break;
			default:
				if (c < ' ' || (c >= '\u0080' && c < '\u00a0')
						|| (c >= '\u2000' && c < '\u2100')) {
					t = "000" + Integer.toHexString(c);
					sb.append("\\u");
					sb.append(t.substring(t.length() - 4));
				} else
					sb.append(c);
			}
		}
		sb.append('"');
		return sb.toString();
	}
	
	public static String concat(String s, String... more) {
		StringBuilder buf = new StringBuilder(s);
		for (String t : more)
			buf.append(t);
		return buf.toString();
	}
	
	public static boolean isAjax(HttpServletRequest request){
	    return  (request.getHeader("X-Requested-With") != null  
	    && "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString())) ;
	}
	
	public static boolean JudgeIsMoblie(HttpServletRequest request) {
		boolean isMoblie = false;
		String[] mobileAgents = { "iphone", "android", "phone", "mobile", "wap", "netfront", "java", "opera mobi",
				"opera mini", "ucweb", "windows ce", "symbian", "series", "webos", "sony", "blackberry", "dopod",
				"nokia", "samsung", "palmsource", "xda", "pieplus", "meizu", "midp", "cldc", "motorola", "foma",
				"docomo", "up.browser", "up.link", "blazer", "helio", "hosin", "huawei", "novarra", "coolpad", "webos",
				"techfaith", "palmsource", "alcatel", "amoi", "ktouch", "nexian", "ericsson", "philips", "sagem",
				"wellcom", "bunjalloo", "maui", "smartphone", "iemobile", "spice", "bird", "zte-", "longcos",
				"pantech", "gionee", "portalmmm", "jig browser", "hiptop", "benq", "haier", "^lct", "320x320",
				"240x320", "176x220", "w3c ", "acs-", "alav", "alca", "amoi", "audi", "avan", "benq", "bird", "blac",
				"blaz", "brew", "cell", "cldc", "cmd-", "dang", "doco", "eric", "hipt", "inno", "ipaq", "java", "jigs",
				"kddi", "keji", "leno", "lg-c", "lg-d", "lg-g", "lge-", "maui", "maxo", "midp", "mits", "mmef", "mobi",
				"mot-", "moto", "mwbp", "nec-", "newt", "noki", "oper", "palm", "pana", "pant", "phil", "play", "port",
				"prox", "qwap", "sage", "sams", "sany", "sch-", "sec-", "send", "seri", "sgh-", "shar", "sie-", "siem",
				"smal", "smar", "sony", "sph-", "symb", "t-mo", "teli", "tim-", "tosh", "tsm-", "upg1", "upsi", "vk-v",
				"voda", "wap-", "wapa", "wapi", "wapp", "wapr", "webc", "winw", "winw", "xda", "xda-",
				"Googlebot-Mobile" };
		if (request.getHeader("User-Agent") != null) {
			for (String mobileAgent : mobileAgents) {
				if (request.getHeader("User-Agent").toLowerCase().indexOf(mobileAgent) >= 0) {
					isMoblie = true;
					break;
				}
			}
		}
		return isMoblie;
	}
	
	public static boolean IsIphone(HttpServletRequest request) {
		boolean isIphone = false;
		String[] mobileAgents = { "iphone" };
		if (request.getHeader("User-Agent") != null) {
			for (String mobileAgent : mobileAgents) {
				if (request.getHeader("User-Agent").toLowerCase().indexOf(mobileAgent) >= 0) {
					isIphone = true;
					break;
				}
			}
		}
		return isIphone;
	}
	
	public static boolean IsAndroid(HttpServletRequest request) {
		boolean isAndroid = false;
		String[] mobileAgents = { "android" };
		if (request.getHeader("User-Agent") != null) {
			for (String mobileAgent : mobileAgents) {
				if (request.getHeader("User-Agent").toLowerCase().indexOf(mobileAgent) >= 0) {
					isAndroid = true;
					break;
				}
			}
		}
		return isAndroid;
	}
	
	public static String checkDeviceType(HttpServletRequest request) {
		String type = "";
		if (request.getHeader("User-Agent") != null) {
			if (request.getHeader("User-Agent").toLowerCase().indexOf("android") >= 0) {
				type = "Android";
			}else if(request.getHeader("User-Agent").toLowerCase().indexOf("iphone") >= 0){
				type = "Iphone";
			}else{
				type = "Web";
			}
		}
		return type;
	}
}
