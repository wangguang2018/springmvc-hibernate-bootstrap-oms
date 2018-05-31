package com.wangguang.services.oss;

import com.aliyun.oss.OSSClient;
import com.wangguang.model.entity.Agent;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Arrays;

/**
 * @Author: created by zzg
 * @CreateDate: 2018/2/8 14:02
 * @Remark:
 * @Version: 1.0
 */
@Service
public class OssService {

    /**
     * 文件上传
     *
     * @param filename 文件名
     * @param file 文件流
     * @return 文件地址
     */
    public String upload(Agent agent, String filename, InputStream file) {
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(agent.getEndpoint(), agent.getAccessKeyId(), agent.getAccessKeySecret());

        // 上传文件流
        String key = generateUniqueID(filename);
        ossClient.putObject(agent.getBucketName(), key, file);

        // 关闭Client
        ossClient.shutdown();

        return agent.getBucketUrl() + "/" + key;
    }



    /**
     * 文件上传
     *
     * @param filename 文件名
     * @param file 文件流
     * @return 文件地址
     */
    public String uploadVideo(Agent agent,String filename, InputStream file) {
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(agent.getEndpoint(), agent.getAccessKeyId(), agent.getAccessKeySecret());

        // 上传文件流
        String key = generateUniqueVideoID(filename);
        ossClient.putObject(agent.getBucketName(), key, file);

        // 关闭Client
        ossClient.shutdown();

        return agent.getBucketUrl() + "/" + key;
    }

    /**
     * 生成唯一id
     *
     * @param filename 文件名
     * @return 唯一id
     */
    public String generateUniqueID(String filename) {
        return DigestUtils.md5Hex(RandomStringUtils.random(6) +
                System.currentTimeMillis()) +
                ".png" ;
    }


    /**
     * 生成唯一id
     *
     * @param filename 文件名
     * @return 唯一id
     */
    public String generateUniqueVideoID(String filename) {
        return DigestUtils.md5Hex(RandomStringUtils.random(6) +
                System.currentTimeMillis()) +
                ".mp4" ;
    }





    // Client Security Code
    // ydb%2016

    /**
     * 文件上传
     *
     * @param extension 文件拓展名
     * @param file 文件流
     * @return 文件地址
     */
    public String uploadFile(Agent agent,String extension, InputStream file) {
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(agent.getEndpoint(), agent.getAccessKeyId(), agent.getAccessKeySecret());

        // 上传文件流
        String key = geneUniqueID(extension);
        ossClient.putObject(agent.getBucketName(), key, file);

        // 关闭Client
        ossClient.shutdown();

        return agent.getBucketUrl() + "/" + key;
    }

    /**
     * 生成唯一id
     *
     * @param extension 文件拓展名
     * @return 唯一id
     */
    public String geneUniqueID(String extension) {
        if(StringUtils.isEmpty(extension)){
            extension = "png";
        }
        return DigestUtils.md5Hex(RandomStringUtils.random(6) +
                System.currentTimeMillis()) +
                "." + extension ;
    }


    /**
     * 缩放图片
     *
     * @param imageURI 图片地址
     * @param mode 模式（默认lfit）
     * @param width 宽度
     * @param height 高度
     * @return 缩放后的地址
     */
    public String resize(String imageURI, String mode, Integer width, Integer height) {
        imageURI += "?x-oss-process=image/resize";

        if (Arrays.asList("lfit", "mfit", "fill", "pad", "fixed").contains(mode)) {
            imageURI += (",m_" + mode);
        }

        if (width != null && width > 0) {
            imageURI += (",w_" + width);
        }

        if (height != null && height > 0) {
            imageURI += (",h_" + height);
        }

        return imageURI;
    }
}
