/*
 * Copyright (c) 2018-2999 广州市蓝海创新科技有限公司 All rights reserved.
 *
 * https://www.mall4j.com/
 *
 * 未经允许，不可做商业用途！
 *
 * 版权所有，侵权必究！
 */

package com.yami.shop.admin.controller;

import com.yami.shop.common.bean.Qiniu;
import com.yami.shop.common.response.ServerResponseEntity;
import com.yami.shop.service.AttachFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 文件上传 controller
 * @author lgh
 *
 */
@RestController
@RequestMapping("/admin/file")
public class FileController {
	
	@Autowired
	private AttachFileService attachFileService;
	@Autowired
	private Qiniu qiniu;
	
	@PostMapping("/upload/element")
	public ServerResponseEntity<String> uploadElementFile(@RequestParam("file") MultipartFile file) throws IOException{
		if(file.isEmpty()){
            return ServerResponseEntity.success();
        }
		String fileName = attachFileService.uploadFile(file.getBytes(),file.getOriginalFilename());
        return ServerResponseEntity.success(fileName);
	}
	
	@PostMapping("/upload/tinymceEditor")
	public ServerResponseEntity<String> uploadTinymceEditorImages(@RequestParam("editorFile") MultipartFile editorFile) throws IOException{
		String fileName =  attachFileService.uploadFile(editorFile.getBytes(),editorFile.getOriginalFilename());
        return ServerResponseEntity.success(qiniu.getResourcesUrl() + fileName);
	}
	
}
