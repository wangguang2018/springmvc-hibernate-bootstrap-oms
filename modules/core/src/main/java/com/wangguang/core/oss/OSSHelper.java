package com.wangguang.core.oss;

import com.aliyun.oss.OSSClient;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.util.Arrays;

/**
 * Helper - 阿里云OSS
 *
 * @author xingkong1221
 * @since 2016-11-26
 */
public class OSSHelper {

    private static String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
    private static String accessKeyId = "LTAIALhDnclBYATv";
    private static String accessKeySecret = "4i9zJ5aDljBkFVRbp8pazK1l21XS79";
    private static String bucketName = "yingdd";
    private static String bucketURI = "https://yingdd.oss-cn-hangzhou.aliyuncs.com";



    /**
     * 文件上传
     *
     * @param filename 文件名
     * @param file 文件流
     * @return 文件地址
     */
    public static String upload(String filename, InputStream file) {
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        // 上传文件流
        String key = generateUniqueID(filename);
        ossClient.putObject(bucketName, key, file);

        // 关闭Client
        ossClient.shutdown();

        return bucketURI + "/" + key;
    }



    /**
     * 文件上传
     *
     * @param filename 文件名
     * @param file 文件流
     * @return 文件地址
     */
    public static String uploadVideo(String filename, InputStream file) {
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        // 上传文件流
        String key = generateUniqueVideoID(filename);
        ossClient.putObject(bucketName, key, file);

        // 关闭Client
        ossClient.shutdown();

        return bucketURI + "/" + key;
    }

    /**
     * 生成唯一id
     *
     * @param filename 文件名
     * @return 唯一id
     */
    public static String generateUniqueID(String filename) {
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
    public static String generateUniqueVideoID(String filename) {
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
    public static String uploadFile(String extension, InputStream file) {
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        // 上传文件流
        String key = geneUniqueID(extension);
        ossClient.putObject(bucketName, key, file);

        // 关闭Client
        ossClient.shutdown();

        return bucketURI + "/" + key;
    }

    /**
     * 生成唯一id
     *
     * @param extension 文件拓展名
     * @return 唯一id
     */
    public static String geneUniqueID(String extension) {
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
    public static String resize(String imageURI, String mode, Integer width, Integer height) {
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
