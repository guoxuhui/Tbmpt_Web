package com.crfeb.tbmpt.commons.utils;

import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * 工具类
 * 上传附件（图片,文件）
 * 
 * @author Administrator
 * 
 */
public class UploadUtil {
	// private String imagePath = "/imageFile/" + new
	// SimpleDateFormat("yyyyMMddHH").format(new Date()) + "";// 配置图片路径
	// private String imagePath = "/upload/gczl/pic/"+DateUtils.format(new
	// Date(), "yyyyMMdd");
	private int longSide = 300; // 压缩图片长边宽度

	/**
	 * 上传图片并处理成缩略图存储
	 * @param request
	 * @param file
	 *            原始文件
	 * @param originalFileName
	 *            原始文件名
	 * @param compressFileNewName
	 *            压缩文件名
	 * @param imagePath
	 *            文件上传路径
	 * @return
	 */
	public String uploadImage(HttpServletRequest request, MultipartFile file, String originalFileName,
			String compressFileNewName, String imagePath) {
		SysPropertieUtil configEntity = SysPropertieUtil.getInstince();
		String upLoadPath = configEntity.getUploadPath();
		String errorMesage = "";// 错误信息
		String fileName = file.getOriginalFilename();
		String type = fileName.substring(fileName.lastIndexOf(".") + 1);
		imagePath = upLoadPath + imagePath;
		originalFileName += "." + type;
		compressFileNewName += "." + type;
		// 允许的文件格式
		String[] canType = { "BMP", "bmp", "jpg", "JPG", "wbmp", "jpeg", "png", "PNG", "JPEG", "WBMP", "GIF", "gif" };
		// ==================校验文件格式是否正确=================================//
		if (!Arrays.asList(canType).contains(type)) {
			return "文件格式错误，不可上传，请确认。";
		}
		// String path =
		// request.getSession().getServletContext().getRealPath(imagePath);
		File uploadDir = new File(imagePath);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		try {
			// ===========================保存压缩图片到服务器 =====================//
			 compressImage(request, file, originalFileName, compressFileNewName, imagePath);
			// ===========================保存原图片到服务器 =====================//
			File originalFile = new File(imagePath, originalFileName);
			file.transferTo(originalFile);
		} catch (IOException e) {
			e.printStackTrace();
			return "上传图片失败!";
		}
		return errorMesage;
	}


	/**
	 * 上传图片并处理成缩略图存储
	 * @param request
	 * @param file
	 *            原始文件
	 * @param originalFileName
	 *            原始文件名
	 * @param compressFileNewName
	 *            压缩文件名
	 * @param imagePath
	 *            文件上传路径
	 * @return
	 */
	public String uploadImage2(HttpServletRequest request, MultipartFile file, String originalFileName,
							  String compressFileNewName, String imagePath) {
		SysPropertieUtil configEntity = SysPropertieUtil.getInstince();
		String upLoadPath = configEntity.getUploadPath();
		String errorMesage = "";// 错误信息
		String fileName = file.getOriginalFilename();
		String type = fileName.substring(fileName.lastIndexOf(".") + 1);
		imagePath = upLoadPath + imagePath;
		originalFileName += "." + type;
		compressFileNewName += "." + type;
		// 允许的文件格式
		String[] canType = { "BMP", "bmp", "jpg", "JPG", "wbmp", "jpeg", "png", "PNG", "JPEG", "WBMP", "GIF", "gif" };
		// ==================校验文件格式是否正确=================================//
		if (!Arrays.asList(canType).contains(type)) {
			return "文件格式错误，不可上传，请确认。";
		}
		// String path =
		// request.getSession().getServletContext().getRealPath(imagePath);
		File uploadDir = new File(imagePath);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		try {
			// ===========================保存压缩图片到服务器 =====================//
			compressImage1(request, file, originalFileName, compressFileNewName, imagePath);
			// ===========================保存原图片到服务器 =====================//
			File originalFile = new File(imagePath, originalFileName);
			file.transferTo(originalFile);
		} catch (IOException e) {
			e.printStackTrace();
			return "上传图片失败!";
		}
		return errorMesage;
	}
	/**
	 * 将原始图片生成缩略图
	 * @param request
	 * @param file
	 *            原始图片文件
	 * @param getUploadContentType
	 *            文件类型
	 * @param getUploadFileName
	 *            原文件名
	 * @param fileNewName
	 *            新文件名
	 * @param imagePath
	 *            图片上传路径
	 * @throws IOException
	 */
	public void compressImage(HttpServletRequest request, MultipartFile file, String getUploadFileName,
			String fileNewName, String imagePath) throws IOException {

		// String getImagePath =
		// request.getSession().getServletContext().getRealPath(imagePath);

		File image = new File(imagePath);
		if (!image.exists()) {
			image.mkdirs();
		}

		// 最后返回图片路径
		imagePath = imagePath + "/" + fileNewName;
		BufferedImage srcBufferImage = ImageIO.read(file.getInputStream());
		BufferedImage scaledImage;
		ScaleImage scaleImage = ScaleImage.getInstance();
		int yw = srcBufferImage.getWidth();
		int yh = srcBufferImage.getHeight();
		// int w = 800, h = 450;
		// 如果上传图片 宽高 比 压缩的要小 则不压缩
		// if (w > yw && h > yh)
		// 如果上传图片最长的一边比要压缩的最长边还短；则不压缩
		if ((yw > yh && yw < longSide) || (yw < yh && yh < longSide)) {
			FileOutputStream fos = new FileOutputStream(imagePath);
//			FileInputStream fis = (FileInputStream) file.getInputStream();
			DataInputStream dis_2=new DataInputStream(file.getInputStream());
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = dis_2.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			fos.close();
			dis_2.close();
		} else {
			int[] newSize = this.findNewSize(yw, yh);// 获取压缩尺寸
			scaledImage = scaleImage.imageZoomOut(srcBufferImage, newSize[0], newSize[1]);
			FileOutputStream out = new FileOutputStream(imagePath);
			ImageIO.write(scaledImage, "jpeg", out);
			out.close();

		}
	}

	/**
	 * 将原始图片生成缩略图
	 * @param request
	 * @param file
	 *            原始图片文件
	 * @param getUploadContentType
	 *            文件类型
	 * @param getUploadFileName
	 *            原文件名
	 * @param fileNewName
	 *            新文件名
	 * @param imagePath
	 *            图片上传路径
	 * @throws IOException
	 */
	public void compressImage1(HttpServletRequest request, MultipartFile file, String getUploadFileName,
							  String fileNewName, String imagePath) throws IOException {

		// String getImagePath =
		// request.getSession().getServletContext().getRealPath(imagePath);

		File image = new File(imagePath);
		if (!image.exists()) {
			image.mkdirs();
		}

		// 最后返回图片路径
		imagePath = imagePath + "/" + fileNewName;
		BufferedImage srcBufferImage = ImageIO.read(file.getInputStream());
		BufferedImage scaledImage;
		ScaleImage scaleImage = ScaleImage.getInstance();
		int yw = srcBufferImage.getWidth();
		int yh = srcBufferImage.getHeight();
		// int w = 800, h = 450;
		// 如果上传图片 宽高 比 压缩的要小 则不压缩
		// if (w > yw && h > yh)
		// 如果上传图片最长的一边比要压缩的最长边还短；则不压缩
		if ((yw > yh && yw < longSide) || (yw < yh && yh < longSide)) {
			FileOutputStream fos = new FileOutputStream(imagePath);

			InputStream fis = file.getInputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			fos.close();
			fis.close();
		} else {
			int[] newSize = this.findNewSize(yw, yh);// 获取压缩尺寸
			scaledImage = scaleImage.imageZoomOut(srcBufferImage, newSize[0], newSize[1]);
			FileOutputStream out = new FileOutputStream(imagePath);
			ImageIO.write(scaledImage, "jpeg", out);
			out.close();

		}
	}

	/**
	 * 根据原始图片的尺寸大小获取压缩后的图片的新尺寸
	 * 
	 * @param yw
	 *            原始图片宽度
	 * @param yh
	 *            原始图片高度
	 * @return 返回新尺寸 数组形式{宽度，高度}
	 */
	public int[] findNewSize(int yw, int yh) {
		int[] newSize = new int[2];
		int nw, nh;
		if (yw > yh) {
			// 宽度大于高度
			nw = longSide;
			nh = (int) UtilMath.multiply(yh, UtilMath.divide(nw * 1.0, yw * 1.0, 2), 2);
		} else {
			// 宽度小于等于高度
			nh = longSide;
			nw = (int) UtilMath.multiply(yw, UtilMath.divide(nh * 1.0, yh * 1.0, 2), 2);
		}
		newSize[0] = nw;
		newSize[1] = nh;
		return newSize;
	}

	/**
	 * @param request
	 * @param filePath
	 *            文件相对工程根目录路径
	 */
	public void deleteFile(String filePath) {
		File f = new File(filePath); // 输入要删除的文件位置
		if (f.exists()) {
			f.delete();
			f=null;
		}
	}

	/**
	 * 上传文件
	 * 
	 * @param request
	 * @param file
	 *            原始文件
	 * @param originalFileName
	 *            原始文件名
	 * @param imagePath
	 *            文件上传路径
	 * @return 返回空则上传文件成功，否则返回错误信息
	 */
	public String uploadFile(HttpServletRequest request, MultipartFile file, String newFileName, String filePath) {
		SysPropertieUtil configEntity = SysPropertieUtil.getInstince();
		String upLoadPath = configEntity.getUploadPath();
		String errorMesage = "";// 错误信息
		filePath = upLoadPath + filePath;
		File uploadDir = new File(filePath);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		try {
			// ===========================保存原图片到服务器 =====================//
			File originalFile = new File(filePath, newFileName);
			file.transferTo(originalFile);
		} catch (IOException e) {
			e.printStackTrace();
			return "上传附件失败!";
		}
		return errorMesage;
	}
	
	/**
	 * 方法说明：文件下载
	 * @param filepath 下载文件全路径
	 * @param request 
	 * @param response
	 * @return  若成功则返回空，否则返回错误信息
	 * @author:YangYj
	 * @Time: 2016年12月15日 下午2:49:06
	 */
	public static String downloadFile2(String filepath,HttpServletRequest request,HttpServletResponse response) {
		String fileName = filepath.substring(filepath.lastIndexOf("/")+1, filepath.length());
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition", "attachment;fileName="+fileName);
		try {
			filepath =request.getRealPath(filepath);
			InputStream inputStream = new FileInputStream(new File(filepath));
			OutputStream os = response.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
			os.close();
			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "未找到文件";
		} catch (IOException e) {
			e.printStackTrace();
			return "下载文件失败";
		}
		return null;
	}
    /**
	 * 方法说明：文件下载
	 * @param filepath 下载文件全路径
	 * @param request 
	 * @param response
	 * @return  若成功则返回空，否则返回错误信息
	 * @author:YangYj
	 * @Time: 2016年12月15日 下午2:49:06
	 */
	public static String downloadFile(String filepath,HttpServletRequest request,HttpServletResponse response) {
		    String fileName = filepath.substring(filepath.lastIndexOf("/")+1, filepath.length());
			response.setCharacterEncoding("utf-8");
			response.setContentType("multipart/form-data");
			response.setHeader("Content-Disposition", "attachment;fileName="+fileName);
			SysPropertieUtil configEntity = SysPropertieUtil.getInstince();
			String upLoadPath = configEntity.getUploadPath();//配置的存储文件路径
			filepath =upLoadPath+filepath;
			try {
//				String rootPath =request.getSession().getServletContext().getRealPath("upload");
				InputStream inputStream = new FileInputStream(new File(filepath));
				OutputStream os = response.getOutputStream();
				byte[] b = new byte[2048];
				int length;
				while ((length = inputStream.read(b)) > 0) {
					os.write(b, 0, length);
				}
				os.close();
				inputStream.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return "未找到文件";
			} catch (IOException e) {
				e.printStackTrace();
				return "下载文件失败";
			}
			return null;
		}
}