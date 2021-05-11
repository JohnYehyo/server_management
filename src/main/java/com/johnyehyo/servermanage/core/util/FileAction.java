package com.johnyehyo.servermanage.core.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.johnyehyo.servermanage.core.bean.BucketPolicyConfigDto;
import io.minio.*;
import io.minio.http.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


/**
 * @Description 对象存储操作
 * @Author JohnYehyo
 * @Date 2020-09-03 14:44
 * @Version 1.0
 */
@Component
@PropertySource("classpath:application.yml")
public class FileAction {

    @Value("${minio.url}")
    private String URL;

    @Value("${minio.username}")
    private String USERNAME;

    @Value("${minio.password}")
    private String PASSWORD;

    private static final Logger LOGGER = LoggerFactory.getLogger(FileAction.class);

    private static MinioClient instance = null;

    @PostConstruct
    public MinioClient getMinioClient(){
        if(null == instance){
            instance = MinioClient.builder()
                    .endpoint(URL)
                    .credentials(USERNAME,PASSWORD)
                    .build();
        }
        return instance;
    }


    /**
     * 上传
     *
     * @param file        文件
     * @param bucketName  存储单元名称
     * @param childFolder 跟在存储单元名称后的子路径 /bucketName/childFolder/123.jpg
     * @return 结果
     */
    public String upload(MultipartFile file, String bucketName, String childFolder) {
        try {
            MinioClient minioClient = getMinioClient();
            boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!isExist) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
                BucketPolicyConfigDto bucketPolicyConfigDto = createBucketPolicyConfigDto(bucketName);
                SetBucketPolicyArgs setBucketPolicyArgs = SetBucketPolicyArgs.builder()
                        .bucket(bucketName)
                        .config(JSONUtil.toJsonStr(bucketPolicyConfigDto))
                        .build();
                minioClient.setBucketPolicy(setBucketPolicyArgs);
            }
            String filename = file.getOriginalFilename();

            SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
            SimpleDateFormat sdfMonth = new SimpleDateFormat("MM");
            SimpleDateFormat sdfDay = new SimpleDateFormat("dd");
            // 设置存储对象名称
            String objectName = childFolder + "/" + sdfYear.format(new Date()) + "/" + sdfMonth.format(new Date()) +
                    "/" + sdfDay.format(new Date()) + "/" + System.currentTimeMillis() + "_" + filename;
            String contentType = file.getContentType();
            InputStream inputStream = file.getInputStream();
            long size = file.getSize();

            // 使用putObject上传一个文件到存储桶中
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .contentType(contentType)
                    .stream(inputStream, size, ObjectWriteArgs.MIN_MULTIPART_SIZE).build();
            minioClient.putObject(putObjectArgs);
            return minioClient.getObjectUrl(bucketName, objectName);
        } catch (Exception e) {
            LOGGER.error("上传发生错误: {}！具体信息:", e.getMessage(), e);
        }
        return null;
    }

    private BucketPolicyConfigDto createBucketPolicyConfigDto(String bucketName) {
        BucketPolicyConfigDto.Statement statement = BucketPolicyConfigDto.Statement.builder()
                .Effect("Allow")
                .Principal("*")
                .Action("s3:GetObject")
                .Resource("arn:aws:s3:::"+bucketName+"/*.**").build();
        return BucketPolicyConfigDto.builder()
                .Version("2012-10-17")
                .Statement(CollUtil.toList(statement))
                .build();
    }

    /**
     * 删除
     *
     * @param objectName 存储对象名称
     * @param bucketName 存储单元名称
     * @return 结果
     */
    public boolean delete(String objectName, String bucketName) {
        try {
            MinioClient minioClient = getMinioClient();
            minioClient.removeObject(RemoveObjectArgs.
                    builder().
                    bucket(bucketName).
                    object(objectName).
                    build());
            return true;
        } catch (Exception e) {
            LOGGER.error("删除发生错误: {}！具体信息:", e.getMessage(), e);
        }
        return false;
    }

    /**
     * 获取给HTTP GET可用的url资源
     *
     * @param objectName 存储对象名称
     * @param bucketName 存储单元名称
     * @return 结果
     */
    public String url(String objectName, String bucketName) {
        try {
            MinioClient minioClient = getMinioClient();
            //默认7天 这里改为1天(单位秒)
            return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.
                    builder().
                    method(Method.GET).
                    bucket(bucketName).
                    object(objectName).
                    expiry(60 * 60 * 24, TimeUnit.SECONDS).
                    build());
        } catch (Exception e) {
            LOGGER.error("获取url发生错误: {}！具体信息:", e.getMessage(), e);
        }
        return null;
    }

    /**
     * 下载对象
     * @param bucketName 存储单元名称
     * @param objectName 存储对象名称
     * @return 结果
     */
    public InputStream getObject(String bucketName, String objectName) {
        try {
            MinioClient minioClient = getMinioClient();
            return minioClient.getObject(GetObjectArgs.
                    builder().
                    bucket(bucketName).
                    object(objectName).
                    build());
        } catch (Exception e) {
            LOGGER.error("下载对象发生错误: {}！具体信息:", e.getMessage(), e);
        }
        return null;
    }
}
