package com.best.peng.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.best.peng.service.BestFileService;

@Controller
@RequestMapping("file")
public class BestFileController {
	
	@Resource
	private BestFileService bestFileService;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String get(){
		
		return "";
	}
	
	
	
	@RequestMapping(value="upload",method=RequestMethod.POST)
	@ResponseBody
	public String upload(MultipartFile file, HttpServletRequest req) throws IllegalStateException, IOException{
		
		if (!file.isEmpty()) {
			String path = req.getServletContext().getRealPath("/upload");//获取项目绝对路劲
			String name = file.getOriginalFilename();//文件原名
			long size = file.getSize();// 文件大小
			file.getInputStream();// 文件流
			File pathFile = new File(path);
			if (!pathFile.exists()) {
				// 文件夹不存 创建文件
				pathFile.mkdirs();
			}
			// 保存文件
			file.transferTo(new File(path+"/"+name));
			return "上传成功!";
		} else {
			return "上传失败，文件是空的";
		}
	}

	// 多文件上传
	@ResponseBody
	@RequestMapping(value="uploads",method=RequestMethod.POST)
	public String fileUploads(@RequestParam("file") MultipartFile[] files, HttpServletRequest req)
			throws IllegalStateException, IOException {
		// 获取文件 存储位置
		String realPath = req.getSession().getServletContext().getRealPath("/upload");

		File pathFile = new File(realPath);

		if (!pathFile.exists()) {
			// 文件夹不存 创建文件
			pathFile.mkdirs();
		}
		for (MultipartFile f : files) {

			System.out.println("文件类型：" + f.getContentType());
			System.out.println("文件名称：" + f.getOriginalFilename());
			System.out.println("文件大小:" + f.getSize());
			// 将文件copy上传到服务器
			f.transferTo(new File(realPath + "/" + f.getOriginalFilename()));
		}
		return "<script>alert('上传成功!')</script>";
	}
	
	
	/**
	 * 文件下载
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value = "download/{id}",method=RequestMethod.GET)  
    public String download(@PathVariable("id") Long id,HttpServletRequest request,HttpServletResponse response) throws Exception {   
    	String storeName="090909.txt";
        String contentType = "application/octet-stream";  
        download(request, response, storeName, contentType);  
        return "index";
    }
    //文件下载 主要方法
    public static void download(HttpServletRequest request,HttpServletResponse response, String storeName, String contentType
           ) throws Exception {
    	
    	BufferedInputStream bis = null;  
        BufferedOutputStream bos = null;  
        
    	try {
	        request.setCharacterEncoding("UTF-8");  
	        
	        //获取项目根目录
	        String ctxPath = request.getSession().getServletContext()  
	                .getRealPath("");  
	        
	        //获取下载文件露肩
	        String downLoadPath = ctxPath+"/"+ storeName;  
	  
	        //获取文件的长度
	        long fileLength = new File(downLoadPath).length();  
	
	        //设置文件输出类型
	        response.setContentType("application/octet-stream");  
	        response.setHeader("Content-disposition", "attachment; filename="  
	                + new String(storeName.getBytes("utf-8"), "ISO8859-1")); 
	        //设置输出长度
	        response.setHeader("Content-Length", String.valueOf(fileLength));  
	        //获取输入流
	        bis = new BufferedInputStream(new FileInputStream(downLoadPath));  
	        //输出流
	        bos = new BufferedOutputStream(response.getOutputStream());  
	        byte[] buff = new byte[2048];  
	        int bytesRead;  
	        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {  
	            bos.write(buff, 0, bytesRead);  
	        }
		} finally {
			//关闭流
		    bis.close();
		    bos.close();
		}
    }
    
    
    
}
