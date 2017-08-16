package han.gyoul.shop.controller;

import java.io.FileInputStream;
import java.io.InputStream;

//import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import han.gyoul.shop.util.MediaUtils;
import han.gyoul.shop.util.UploadFileUtils;



@Controller
@SessionAttributes({"memberDTO","itemList"})
public class UploadController {
	
  private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

//  �뙆�씪 �뾽濡쒕뱶 ���옣 - 沅낆씠 �뼱�끂�뀒�씠�뀡 �벝 �븘�슂 �뾾�쓬
//  @Resource(name = "uploadPath")
  private String uploadPath = "C:\\zzz\\upload";

  @RequestMapping(value = "/uploadAjax", method = RequestMethod.GET)
  public void uploadAjax() {
  }

  @ResponseBody
  @RequestMapping(value ="/uploadAjax", method=RequestMethod.POST, 
                  produces = "text/plain;charset=UTF-8")
  public ResponseEntity<String> uploadAjax(MultipartFile file)throws Exception{
    System.out.println("1");
    logger.info("originalName: " + file.getOriginalFilename());
    return 
    	      new ResponseEntity<>(
    	          UploadFileUtils.uploadFile(uploadPath, 
    	                file.getOriginalFilename(), 
    	                file.getBytes()), 
    	          HttpStatus.CREATED);
    	  }
   
  
  @ResponseBody
  @RequestMapping("/displayFile")
  public ResponseEntity<byte[]>  displayFile(String fileName)throws Exception{
    InputStream in = null; 
    ResponseEntity<byte[]> entity = null;
    System.out.println("2");
    logger.info("FILE NAME: " + fileName);
    
    try{
      System.out.println("3");
      String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
      
      MediaType mType = MediaUtils.getMediaType(formatName);
      
      HttpHeaders headers = new HttpHeaders();
      
      // 占쌔댐옙占싸울옙 占쏙옙占쏙옙 占쌈쏙옙占쏙옙占쏙옙
      in = new FileInputStream(uploadPath+fileName);
      
      if(mType != null){
        headers.setContentType(mType);
      }else{
        
        fileName = fileName.substring(fileName.indexOf("_")+1);       
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.add("Content-Disposition", "attachment; filename=\""+ 
          new String(fileName.getBytes("UTF-8"), "ISO-8859-1")+"\"");
      }

        entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), 
          headers, 
          HttpStatus.CREATED);
    }catch(Exception e){
      e.printStackTrace();
      entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
    }finally{
      in.close();
    }
      return entity;    
  }
}