package com.crfeb.tbmpt.commons.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.crfeb.tbmpt.gpztcl.base.model.dto.DaoXiangDto;

public class FileUtil {
	private static String path="D:\\项目\\系统设计\\管片姿态测量系统\\导向系统解析文件\\slst_329.DB";
	private static String writeFilepath="F:\\bak\\slst_329.txt";
	
	private static String targetFile = "D:/项目/test329F.txt";
	
	public static void main(String[] args) {
		int d = 16,aa = 0;
		char DLE = (char)d;
		char NUL = (char)aa;
		String content = readFile(path);
		String[] items = content.split(String.valueOf(NUL)+String.valueOf(NUL)+String.valueOf(DLE));
		int i = 0;
		List<DaoXiangDto> dxs = new ArrayList<DaoXiangDto>();
		for (String item : items) {
			DaoXiangDto dx = new DaoXiangDto();
			int start = -10;
			int row = 1000;
			if(start+7<i&&i<start+8+row){
//				System.out.println("第"+(i-8)+"条："+item);
				writeFile(writeFilepath, item+"\r\n", true);		
				String[] strs = item.split(String.valueOf(NUL)+String.valueOf(NUL));
				String[] vle = new String[126];
				int j = 0;
				for (String str : strs) {
					String sss = str.replace(String.valueOf(NUL), "");
					sss = sss.replaceAll("(?:߀|ڀ|טݸ|�|ߣ|ـ|܀|ޖ|׀|ٷ|ގ|֥|&|ߴ|ۀ|ހ|܈|\n|\r|	)", "");  
					sss = sss.replace("̸", "");
					if(!sss.equals("")){
//						System.out.println("第"+j+"个字段："+sss);
						vle[j] = sss;
//						System.out.println(sss);
						j++;
					}
					
				}
//				if(vle[1]==null){
//					break;
//				}
				
				dx.copyValue(vle);
				dxs.add(dx);
			}
			i++;
		}
		for (DaoXiangDto dx : dxs) {
			System.out.println(dx.getAdvanceno()+"::::"+dx.getRbhos()+"------"+dx.getRbvos());
//			System.out.println(dx.getAdvanceno());
		}
	}
	
	public static String asciiToString(String value)
	{
		StringBuffer sbu = new StringBuffer();
		String[] chars = value.split(",");
		for (int i = 0; i < chars.length; i++) {
			sbu.append((char) Integer.parseInt(chars[i]));
		}
		return sbu.toString();
	}
	
	/**
	 * 创建文件
	 * @param path
	 * @return
	 */
	public static boolean createFile(String path){
		File file = new File(path);
		try {
			return file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	} 
	
	/**
	 * 删除一个文件
	 * @param path:文件路径
	 * @return
	 */
	public static boolean deleteFile(String path){
		File file = new File(path);
		if(file.exists()){
			System.out.println("文件："+file.getName()+" 删除成功！");
			return file.delete();
		}else{
			System.out.println("文件："+file.getName()+" 不存在！");
			return false;
		}
		 
	}
	
	public static boolean renameFile(String oldFile,String newFile){
		return false;
	}
	
	/**
	 * 写文件
	 * @param path：文件路径
	 * @param conten：要写入的内容
	 * @param append：是否追加
	 */
	public static void writeFile(String path,String conten,boolean append){
		File file = new File(path);
		OutputStream os = null;
		BufferedOutputStream bs = null;
		try {
			os = new FileOutputStream(file,append);
			bs = new BufferedOutputStream(os);
			bs.write(conten.getBytes());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(bs!=null)bs.close();
				if(os!=null)os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 读文件
	 * @param file
	 * @return
	 */
	public static String readFile(File file){
		InputStream inStream = null;
		BufferedInputStream bufferedfileInStream = null;
		try {
			inStream = new FileInputStream(file);
			bufferedfileInStream = new BufferedInputStream(inStream);
			byte[] b = new byte[(int) file.length()];
			bufferedfileInStream.read(b);
			String str = new String(b);
			return str;
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
				try {
					if(bufferedfileInStream!=null) bufferedfileInStream.close();
					if(inStream!=null)	inStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			
		}
		return null;
	}
	
	public static String readMultipartFile(MultipartFile file){
		InputStream inStream = null;
		BufferedInputStream bufferedfileInStream = null;
		try {
			inStream = file.getInputStream();
			bufferedfileInStream = new BufferedInputStream(inStream);
			byte[] b = new byte[(int) file.getSize()];
			bufferedfileInStream.read(b);
			String str = new String(b);
			return str;
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
				try {
					if(bufferedfileInStream!=null) bufferedfileInStream.close();
					if(inStream!=null)	inStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			
		}
		return null;
	}
	
	/**
	 * 读文件
	 * @param path:文件路径
	 * @return
	 */
	public static String readFile(String path){
		File file = new File(path);
		return readFile(file);
	}
	
	/**
	 * 向文件中写入字符串
	 * @param path：文件路径
	 * @param conten：要写放的内容
	 */
	public static void writeFile(String path,String conten){
		 writeFile(path,conten,false);
	}
	
	/**
	 * 读出字节流
	 * @param path：文件路径
	 * @return
	 */
	public static byte[] readFileByStream(String path){
		File file = new File(path);
		InputStream instream = null;
		BufferedInputStream bufferedInStream = null;
		int length = (int) file.length();
		byte[] b = new byte[length];
		try {
			instream = new FileInputStream(file);
			bufferedInStream = new BufferedInputStream(instream);
			bufferedInStream.read(b);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
				try {
					if(bufferedInStream!=null)bufferedInStream.close();
					if(instream!=null) instream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return b;
	}

	/**
	 * 写入字节流
	 * @param b:字节流
	 * @param path：文件路径
	 */
	public static void writeStream(byte[] b,String path){
		File file = new File(path);
		OutputStream outStream = null;
		BufferedOutputStream bufferedoutStream = null;
		try {
			outStream = new FileOutputStream(file);
			bufferedoutStream = new BufferedOutputStream(outStream);
			bufferedoutStream.write(b);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
				try {
					if(bufferedoutStream!=null)	bufferedoutStream.close();
					if(outStream!=null) outStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
	}
}
