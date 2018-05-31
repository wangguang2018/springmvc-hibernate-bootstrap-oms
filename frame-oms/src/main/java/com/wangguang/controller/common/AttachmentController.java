package com.wangguang.controller.common;


import com.google.common.collect.Maps;
import com.wangguang.core.oss.OSSHelper;
import com.wangguang.model.entity.Agent;
import com.wangguang.services.oss.OssService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 * Controller － 附件
 *
 * @author xingkong1221
 * @date 2015-10-21
 */
@Controller
@RequestMapping("/attachment")
public class AttachmentController extends WebController {

    private final Logger logger = LoggerFactory.getLogger(AttachmentController.class);

    @Resource
    private OssService ossService;


    /** 百度富文本上传图片 */
    @ResponseBody
    @RequestMapping(value = "/uploadUmImg", method = RequestMethod.POST)
    public Map uploadUmImg(@RequestParam("upfile") MultipartFile image) throws IOException {

//        String suffix = "jpg";
        Map<String,String> map = Maps.newHashMap();
        if(!image.getContentType().contains("image")){
            map.put("state","不允许的文件格式");
            return map;
        }
        if(!image.isEmpty()) {
            String fileName = image.getOriginalFilename();
            Agent agent = getLoginAgent();
            String url;
            if (agent == null || agent.getEndpoint() == null) {
                url = OSSHelper.upload(fileName, image.getInputStream());
            }else {
                url = ossService.upload(agent,fileName, image.getInputStream());
            }
            map.put("state","SUCCESS");
            map.put("original",image.getOriginalFilename());
            map.put("size",image.getSize()+"");
            map.put("type",".png");
            map.put("url",url);
        }
        return map;
    }
    
    /** 编辑器上传图片
     * {
     "state": "SUCCESS",
     "original": "80px - \u526f\u672c (2).jpg",
     "size": "13252",
     "title": "1465731377326075274.jpg",
     "type": ".jpg",
     "url": "/ueditor/jsp/upload/image/20160612/1465731377326075274.jpg"
     }
     * */
    @ResponseBody
    @RequestMapping(value = "/editorImage", method = {RequestMethod.POST,RequestMethod.GET})
    public Object editorUploadImage(@RequestParam("image") MultipartFile image) {
        Map<String,String> map = Maps.newHashMap();
        //ModelMap ret = new ModelMap("success", false);
        String suffix = "jpg";
        try {
            if(!image.isEmpty()) {
                String fileName = image.getOriginalFilename();
                if (StringUtils.isNotBlank(fileName)){
                    int index = fileName.lastIndexOf(".");
                    if (index > -1){
                        suffix = fileName.substring(index + 1);
                    }
                }
                Agent agent = getLoginAgent();
                String result;
                if (agent == null || agent.getEndpoint() == null) {
                    result = OSSHelper.upload(fileName,image.getInputStream());
                }else {
                    result = ossService.upload(agent,fileName,image.getInputStream());
                }
                map.put("state","SUCCESS");
                map.put("original",image.getOriginalFilename());
                map.put("size",image.getSize()+"");
//                map.put("title",result);
                map.put("type",suffix);
                map.put("url",result);
            } else {
                map.put("state","FAIL");
                map.put("original",image.getOriginalFilename());
                map.put("size",image.getSize()+"");
//                map.put("title","空图片");
                map.put("type","");
                map.put("url","");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 上传文件(多张图片)
     * @param fileData
     * @return
     */
	@RequestMapping(value = "/uploadImgAndCheckSize", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> uploadImgAndCheckSize(@RequestParam MultipartFile[] fileData) {
        Map<String, Object> result = Maps.newHashMap();
        result.put("status", "ok");
        try {
            if (fileData != null && fileData.length > 0) {
                // 循环获取file数组中得文件
                for (int i = 0; i < fileData.length; i++) {
                    MultipartFile file = fileData[i];
                    long size = file.getSize();
                    if(size > 1024*1024L){//上传图片不能大于1M
                        result.put("status", "-1");
                        return result;
                    }

                    String fileName = file.getOriginalFilename();
                    String suffix = "jpg";
                    if (StringUtils.isNotBlank(fileName)){
                        int index = fileName.lastIndexOf(".");
                        if (index > -1){
                            suffix = fileName.substring(index + 1);
                        }
                    }
//					String ret = ImageService.postImage(file,"c_1",suffix);
                    Agent agent = getLoginAgent();
                    String ret;
                    if (agent == null || agent.getEndpoint() == null) {
                        ret = OSSHelper.upload(fileName,file.getInputStream());
                    }else {
                        ret = ossService.upload(agent,fileName,file.getInputStream());
                    }

//					List list = JsonMapper.nonEmptyMapper().fromJson(ret, List.class);
//					String url = ((Map)(list.get(0))).get("url").toString();
//					int index = url.indexOf("/img");
//					String picUrl = url.substring(index);
                    result.put("fileName", file.getOriginalFilename());
                    result.put("url", ret);
                }
            }
        } catch (Exception e) {
            result.put("status", "error");
        }
        return result;
	}



    /**
     * 上传文件(多张图片)
     * @param fileData
     * @return
     */
    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadFile(@RequestParam MultipartFile[] fileData) {
        Map<String, Object> result = Maps.newHashMap();
        result.put("status", "ok");
        try {
            if (fileData != null && fileData.length > 0) {
                // 循环获取file数组中得文件
                for (int i = 0; i < fileData.length; i++) {
                    MultipartFile file = fileData[i];
                    String fileName = file.getOriginalFilename();
                    String suffix = "jpg";
                    if (StringUtils.isNotBlank(fileName)){
                        int index = fileName.lastIndexOf(".");
                        if (index > -1){
                            suffix = fileName.substring(index + 1);
                        }
                    }
//					String ret = ImageService.postImage(file,"c_1",suffix);
                    Agent agent = getLoginAgent();
                    String ret;
                    if (agent == null || agent.getEndpoint() == null) {
                        ret = OSSHelper.upload(fileName,file.getInputStream());
                    }else {
                        ret = ossService.upload(agent,fileName,file.getInputStream());
                    }

//					List list = JsonMapper.nonEmptyMapper().fromJson(ret, List.class);
//					String url = ((Map)(list.get(0))).get("url").toString();
//					int index = url.indexOf("/img");
//					String picUrl = url.substring(index);
                    result.put("fileName", file.getOriginalFilename());
                    result.put("url", ret);
                }
            }
        } catch (Exception e) {
            result.put("status", "error");
        }
        return result;
    }




    /** 上传apk文件*/
    @ResponseBody
    @RequestMapping(value = "/uploadApk", method = RequestMethod.POST)
    public Map uploadApk(@RequestParam("file") MultipartFile file) throws IOException {
        Map<String,String> map = Maps.newHashMap();
        if(!file.getOriginalFilename().contains(".apk")){
            map.put("state","不允许的文件格式");
            return map;
        }
        if(!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            Agent agent = getLoginAgent();
            String url;
            if (agent == null || agent.getEndpoint() == null) {
                url = OSSHelper.uploadFile("apk", file.getInputStream());
            }else {
                url = ossService.uploadFile(agent,"apk", file.getInputStream());
            }
            map.put("state","SUCCESS");
            map.put("original",file.getOriginalFilename());
            map.put("size",file.getSize()+"");
            map.put("type",".apk");
            map.put("url",url);
        }
        return map;
    }

}
