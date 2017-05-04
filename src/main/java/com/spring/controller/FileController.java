package com.spring.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.spring.security.ResultDto;
import com.spring.servlet.uploadServlet;
import com.spring.util.ImageUtil;

@Controller
@RequestMapping("file")
public class FileController {
	private Logger log=LoggerFactory.getLogger(this.getClass());
	/**
	 * file/upload
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="upload",method=RequestMethod.POST)
	public ResultDto getfile(MultipartFile imgFile, HttpSession session){
		try {
			File fileis = new File(uploadServlet.doMyUpload(imgFile,session,"upload"));
			FileInputStream inputFile = new FileInputStream(fileis);
			byte[] buffer = new byte[(int)fileis.length()];
			inputFile.read(buffer);
			inputFile.close();
			String files = new Base64().encodeToString(buffer);
			String fileid="itemdoc";
			String imageUrl=ImageUtil.uploadImages(files,fileid);
			System.out.println(imageUrl);
			return new ResultDto(200,"成功",imageUrl);
		} catch (IOException e) {
			e.printStackTrace();
			log.error("参数异常！");
			System.out.println("参数异常！");
		}
		return new ResultDto(2001,"参数异常！");
	}
	/**
	 * file/getfile
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="getfile",method=RequestMethod.POST)
	public ResultDto getfile(){
		try {
			File fileisE = new File("D:\\Android\\8.jpg");
			FileInputStream inputFile = new FileInputStream(fileisE);
			byte[] buffer = new byte[(int)fileisE.length()];
			inputFile.read(buffer);
			inputFile.close();
			String file = new Base64().encodeToString(buffer);
			String fileid="itemdoc";
			String headimg = ImageUtil.uploadImages(file,fileid);
			System.out.println(headimg);
			return new ResultDto(200,"成功",headimg);
		} catch (IOException e) {
			log.error("参数异常！");
			System.out.println("参数异常！");
		}
		return new ResultDto(2001,"参数异常！");
	}
}
