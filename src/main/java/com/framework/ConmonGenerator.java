package com.framework;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.framework.utils.GenUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * <p>Description desc   </p>
 *
 * @author jianggk3
 * @date 2018/10/27 10:40
 */
public class ConmonGenerator {
    /**
     * 输出的目录
     */
    private static final String PROJECT_PATH = "F:/IdeaProjects/generator";
    /**
     * 下面的设置， 包就放在 com.framework.modules.account 的下面各目录中。
     * com.framework.modules.account.mapper com.framework.modules.account.entity
     * com.framework.modules.account.service
     * com.framework.modules.account.service.impl
     * xml 放入 application.yml 中配置能加载的路径
     */
    private static final String PACKAGE_NAME = "com.framework.common";
    private static final String MODULE_NAME = "account";
    /**
     * 作者
     */
    private static final String AUTHOR = "chendm";

    /**
     * 数据库链接
     */
    private static final DbType DB_TYPE = DbType.MYSQL;
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/framework?characterEncoding=UTF-8&useSSL=false";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "123456";

    /**
     * 表。
     */
    private static final String[] TABLE_NAME = { "jas_terminal_details", "sys_jasper_user", "demo" };
    /**
     * 表前缀 生成类的时候忽略前缀生成实体类
     */
    private static final String[] TABLE_PREFIX = { "" };

    @SuppressWarnings("Duplicates")
    public static void main(String[] args) {
        GenUtil genUtil = new GenUtil();
        // 数据库链接
        genUtil.setDriver(DRIVER);
        genUtil.setUrl(URL);
        genUtil.setUserName(USER_NAME);
        genUtil.setPassword(PASSWORD);
        genUtil.setDbType(DB_TYPE);
        // 包路径
        if (StringUtils.isNotEmpty(MODULE_NAME)) {
            genUtil.setModuleName(MODULE_NAME);
        }
        genUtil.setPackageName(PACKAGE_NAME);
        // 输出文件位置
        genUtil.setProjectPath(PROJECT_PATH);

        // 生成的表及其前缀
        genUtil.setTablePrefix(TABLE_PREFIX);
        genUtil.setTableName(TABLE_NAME);
        genUtil.setAuthor(AUTHOR);

        AutoGenerator mpg = genUtil.getGenerator();
        // 执行生成
        mpg.execute();
        // 打印注入设置
        System.err.println(mpg.getCfg().getMap().get("abc"));
    }

}
