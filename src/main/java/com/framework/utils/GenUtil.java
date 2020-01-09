package com.framework.utils;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @program: skeleton
 * @Author: jianggk3
 * @Date: 2018/10/27 18:03
 */
@Data
public class GenUtil {

	/**
	 * 输出文件位置
	 */
	private String projectPath;
	/**
	 * 数据库链接
	 */
	private String driver;
	private String url;
	private String userName;
	private String password;
	private DbType dbType;
	/**
	 * 包路径
	 */
	private String moduleName;
	private String packageName;
	/**
	 * 生成的表及其前缀
	 */
	private String[] tablePrefix;
	private String[] tableName;
	private String author;

	public AutoGenerator getGenerator() {
		AutoGenerator mpg = new AutoGenerator();
		// 本地化配置
		GlobalConfig gc = getGlobalConfig();
		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = getDataSourceConfig();
		mpg.setDataSource(dsc);

		// 策略配置
		StrategyConfig strategy = getStrategyConfig();
		mpg.setStrategy(strategy);

		// 包配置
		PackageConfig pc = getPackageConfig();
		mpg.setPackageInfo(pc);

		// 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
		InjectionConfig cfg = getInjectionConfig();
		mpg.setCfg(cfg);

		// 关闭默认 xml 生成，调整生成 至 根目录
		// 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/template 下面内容修改，
		// 放置自己项目的 src/main/resources/template 目录下, 默认名称一下可以不配置，也可以自定义模板名称
		TemplateConfig tc = getTemplateConfig();
		mpg.setTemplate(tc);

		// 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
		// mpg.setTemplateEngine(new FreemarkerTemplateEngine());
		return mpg;
	}

	private GlobalConfig getGlobalConfig() {
		GlobalConfig gc = new GlobalConfig();
		gc.setOutputDir(projectPath);
		gc.setFileOverride(true);
		gc.setActiveRecord(true);
		gc.setEnableCache(false);
		// XML ResultMap
		gc.setBaseResultMap(true);
		// XML columnList
		gc.setBaseColumnList(false);
		gc.setAuthor(author);
		gc.setOpen(true);

		// 自定义文件命名，注意 %s 会自动填充表实体属性！
		gc.setMapperName("%sMapper");
		gc.setXmlName("%sMapper");
		gc.setServiceName("%sService");
		gc.setServiceImplName("%sServiceImpl");
		gc.setControllerName("%sController");
		return gc;
	}

	private PackageConfig getPackageConfig() {
		PackageConfig pc = new PackageConfig();
		pc.setModuleName(moduleName);
		// 自定义包路径
		pc.setParent(packageName);
		// 这里是控制器包名，默认 web
		pc.setController("controller");
		pc.setMapper("mapper");
		pc.setEntity("entity");
		return pc;
	}

	private DataSourceConfig getDataSourceConfig() {
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setDbType(dbType);
		dsc.setTypeConvert(new MySqlTypeConvert() {
			@Override
			public DbColumnType processTypeConvert(String fieldType) {
				return super.processTypeConvert(fieldType);
			}
		});
		dsc.setDriverName(driver);
		dsc.setUrl(url);
		dsc.setUsername(userName);
		dsc.setPassword(password);
		return dsc;
	}

	private TemplateConfig getTemplateConfig() {
		TemplateConfig tc = new TemplateConfig();
		tc.setXml(null);
		tc.setMapper(null);
		tc.setEntity(null);
		tc.setController(null);
		tc.setService(null);
		tc.setServiceImpl(null);
		return tc;
	}

	private InjectionConfig getInjectionConfig() {
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
				Map<String, Object> map = new HashMap<>(10);
				map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
				this.setMap(map);
			}
		};
		List<FileOutConfig> focList = new ArrayList<>();
		focList.add(new FileOutConfig("/templates/entity.java.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return projectPath + "/entity/" + tableInfo.getEntityName() + ".java";
			}
		});
		focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return projectPath + "/xml/" + tableInfo.getEntityName() + "Mapper.xml";
			}
		});
		focList.add(new FileOutConfig("/templates/mapper.java.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return projectPath + "/mapper/" + tableInfo.getEntityName() + "Mapper.java";
			}
		});
		focList.add(new FileOutConfig("/templates/service.java.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return projectPath + "/service/" + tableInfo.getEntityName() + "Service.java";
			}
		});
		focList.add(new FileOutConfig("/templates/serviceImpl.java.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return projectPath + "/impl/" + tableInfo.getEntityName() + "ServiceImpl.java";
			}
		});
		cfg.setFileOutConfigList(focList);
		return cfg;
	}

	private StrategyConfig getStrategyConfig() {
		StrategyConfig strategy = new StrategyConfig();
		// strategy.setCapitalMode(true);// 全局大写命名
		// strategy.setDbColumnUnderline(true);//全局下划线命名
		strategy.setTablePrefix(tablePrefix);
		// 表名生成策略
		strategy.setNaming(NamingStrategy.underline_to_camel);
		// strategy.setInclude(new String[] { "sys_demo" });
		// 需要生成的表
		strategy.setInclude(tableName);
		// strategy.setExclude(new String[]{"t_user","t_role","t_permission"});
		// // 排除生成的表
		// 字段名生成策略
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
		strategy.setEntityLombokModel(true);
		// 驼峰转连字符
		strategy.setControllerMappingHyphenStyle(true);
		// 自定义实体父类
		// strategy.setSuperEntityClass("com.fcs.demo.TestEntity");
		// 自定义实体，公共字段
		// strategy.setSuperEntityColumns(new String[] { "test_id", "age" });
		// 自定义 mapper 父类
		// strategy.setSuperMapperClass("cn.chinaunicom.skeleton.common.gen.config.SkeletonMapper");
		// 自定义 service 父类
		// strategy.setSuperServiceClass("cn.chinaunicom.skeleton.common.gen.config.SkeletonService");
		// 自定义 service 实现类父类
		// strategy.setSuperServiceImplClass("cn.chinaunicom.skeleton.common.gen.config.SkeletonServiceImpl");
		// 自定义 controller 父类
		// strategy.setSuperControllerClass("com.fcs.demo.TestController");
		// 【实体】是否生成字段常量（默认 false）
		// public static final String ID = "test_id";
		// strategy.setEntityColumnConstant(true);
		// 【实体】是否为构建者模型（默认 false）
		// public User setName(String name) {this.name = name; return this;}
		strategy.setEntityBuilderModel(false);
		return strategy;
	}

}
