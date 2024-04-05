package com.mkyong;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
public class HelloController {
	
	private static final Logger LOG = LogManager.getLogger(OpenTextServiceImpl.class);
	@Autowired
	private OpenTextService openTextService;
	
	private String ipfguiFolderPath = "/home/wes/Documents/testfiles";

    @RequestMapping("/")
    String hello() {
        return "Hello World, Spring Boot!";
    }
    
    @PostMapping(value = "/openText/getToken", produces = "application/json")
	@ResponseBody
	public String getToken() throws Exception {
		return openTextService.getToken();
	}
    
    @PostMapping(value = "/opentext/getnodeId/{parentID}/{type}/{fileName}", produces = "application/json")
	@ResponseBody
	public long uploadFile(
		@PathVariable String parentID, @PathVariable String type, @PathVariable String fileName) throws Exception {
		String filePath = ipfguiFolderPath +"/"+fileName;
		File file=new File(filePath);
		LOG.info(file.getName() + "---" + file.getAbsolutePath());
		//return 0;
		return openTextService.uploadFile(file,fileName);

	}
    
    
    @PostMapping(value = "/opentext/endtoend", produces = "application/json")
	@ResponseBody
	public boolean endtoend() throws Exception {
		
		return openTextService.sendReportsToOpenText();

	}
    
    
    

}