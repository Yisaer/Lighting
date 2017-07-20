package com.yisaer.framework; /**
 * Created by Yisa on 2017/7/1.
 */

/**
 * 助手类,用来读取properties配置文件
 * 提供相关配置项的常量
 *
 * @since 1.0.0
 */
public interface ConfigConstant {

    String CONFIG_FILE = "lighting.properties";

    String JDBC_DRIVER = "lighting.framework.jdbc.driver";
    String JDBC_URL = "lighting.framework.jdbc.url";
    String JDBC_USERNAME = "lighting.framework.jdbc.username";
    String JDBC_PASSWORD = "lighting.framework.jdbc.password";
    String APP_BASE_PACKAGE = "lighting.framework.app.base_package";
    String APP_JSP_PATH = "lighting.framework.app.jsp_path";
    String APP_ASSET_PATH = "lighting.framework.app.asset_path";
    String APP_UPLOAD_LIMIT = "lighting.framework.app.upload_limit";

}
