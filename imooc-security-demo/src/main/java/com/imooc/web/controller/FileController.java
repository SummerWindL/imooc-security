package com.imooc.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.imooc.dto.FileInfo;

/**
 *@date 2018年6月9日-下午3:27:02
 *@author fu yanliang
 *@action(作用)
 *@instruction
 */
@RestController
@RequestMapping("/file")
public class FileController {

	private String folder = "C:\\D\\workspace_yanl.eclipse\\workspace_spring\\imooc-security-demo\\src\\main\\java\\com\\imooc\\web\\controller";
	
	@PostMapping
	public FileInfo upload(MultipartFile file) throws IllegalStateException, IOException {
		System.out.println(file.getName());
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
		
		File localFile = new File(folder,new Date().getTime()+".txt");
		file.transferTo(localFile);
		
		return new FileInfo(localFile.getAbsolutePath());
	}
	
	@GetMapping("/{id}")
	public void download(@PathVariable String id,HttpServletRequest request,HttpServletResponse response) throws FileNotFoundException, IOException {
		try(InputStream in = new FileInputStream(new File(folder,id+".txt"));
				OutputStream out = response.getOutputStream();){//jdk7 新语法，不用自己在finally块中关闭流，会自己关闭
			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition", "attachment;filename=test.txt");
			
			IOUtils.copy(in, out);
			out.flush();
		} 
	}

}












