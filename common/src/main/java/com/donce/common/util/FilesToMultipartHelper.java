package com.donce.common.util;

import java.io.File;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 文件转换成MultipartBody.Part 主要用于retrofit2框架中的文件上传
 * Created by Administrator on 2016/8/10 0010.
 */
public class FilesToMultipartHelper {

    public static final String MULTIPART_FORM_DATA = "multipart/form-data";

    /***
     * @param key       对应请求正文中name的值
     * @param filePaths 文件路径数组
     * @return
     */
    public static List<MultipartBody.Part> filesToMultipart(String key, String[] filePaths) {

        List<MultipartBody.Part> parts = new ArrayList<>(filePaths.length);
        for (String filePath : filePaths) {
            MultipartBody.Part part = prepareFilePart(key, filePath);
            parts.add(part);
        }
        return parts;
    }

    /**
     * @param key       对应请求正文中name的值
     * @param filePaths 文件路径数组
     * @return
     */
    public static MultipartBody filesToMultipartBody(String key, String[] filePaths) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        for (String filePath : filePaths) {
            File file = new File(filePath);
            RequestBody requestBody = RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), file);
            builder.addFormDataPart(key, file.getName(), requestBody);
        }
        builder.setType(MultipartBody.FORM);
        return builder.build();
    }


    public static RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                MediaType.parse(MULTIPART_FORM_DATA), descriptionString);
    }

    public static MultipartBody.Part prepareFilePart(String partName, String filePath) {
        File file = new File(filePath);

        // 为file建立RequestBody实例
        RequestBody requestFile =
                RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), file);

        // MultipartBody.Part借助文件名完成最终的上传
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }

    private static String guessMimeType(String path) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(path);
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }

}
