package com.day.parent.util;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEByteEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

/**
 * <p>
 * explain: 密码加密
 * 参考：https://www.cnblogs.com/softidea/p/11044896.html
 *      http://www.safebase.cn/article-257173-1.html
 *      https://www.jianshu.com/p/7eed4e4fb3ba
 * </p>
 *
 * @author wangjunming
 * @since 2020/10/29 10:26
 */
public class JasyptUtil {

    /**
     * Jasypt生成加密结果
     *
     * @param password 配置文件中设定的加密密码 jasypt.encryptor.password
     * @param value    待加密值
     */
    public static String encryptPwd(String password, String value) {
        PooledPBEStringEncryptor encryptOr = new PooledPBEStringEncryptor();
        encryptOr.setConfig(cryptOr(password));
        return encryptOr.encrypt(value);
    }

    /**
     * 解密
     *
     * @param password 配置文件中设定的加密密码 jasypt.encryptor.password
     * @param value    待解密密文
     */
    public static String decyptPwd(String password, String value) {
        PooledPBEStringEncryptor encryptOr = new PooledPBEStringEncryptor();
        encryptOr.setConfig(cryptOr(password));
        return encryptOr.decrypt(value);
    }

    /**
     * 配置类--此处需要与配置文件中保持一致
     *
     * jasypt.encryptor.algorithm=PBEWithMD5AndDES
     * jasypt.encryptor.provider-name=SunJCE
     *
     * @author wangjunming
     * @since 2020/10/29 10:39
     */
    public static SimpleStringPBEConfig cryptOr(String password) {
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(password);
        config.setAlgorithm(StandardPBEByteEncryptor.DEFAULT_ALGORITHM);
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");
        return config;
    }

    public static void main(String[] args) {
        //jasypt.encryptor.password  首先生成所配置的加密密码字符串
        final String encryptPassword = "qjyn1314@163.com";
        final String encryptValue = "20201029104020";
        String encryptPwd = encryptPwd(encryptPassword, encryptValue);
        encryptPwd = "MdQRVDiKXf4sS0lXp61SH5cvVdmn8FJw++ODKj0ojhY=";
        System.out.println("配置文件中jasypt.encryptor.password所配置的加密字符串是：" + encryptPwd);
        //真实密码
        final String password = "1234567";
        // 加密,打印出来的就是ENC(密码)的密码
        final String pwd = encryptPwd(encryptPwd, password);
        System.out.println("配置文件中需要ENC(密码)配置的密码是：" + pwd);
        final String decyptPwd = decyptPwd(encryptPwd, pwd);
        System.out.println("解密之后的真实密码：" + decyptPwd);

    }


}
