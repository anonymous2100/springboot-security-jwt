package com.ctgu.common.generator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.internal.util.StringUtility;

/**
 * @ClassName: CommentGenerator
 * @Description: 自定义注释生成器
 * @author lh2
 * @date 2020年7月2日 下午4:45:35
 */
public class CommentGenerator extends DefaultCommentGenerator
{
	private final Properties properties;

	private boolean addRemarkComments = false;
	private static final String EXAMPLE_SUFFIX = "Example";
	private static final String API_MODEL_PROPERTY_FULL_CLASS_NAME = "io.swagger.annotations.ApiModelProperty";
	private static final String API_JSON_SERIALIZE = "com.fasterxml.jackson.databind.annotation.JsonSerialize";
	private static final String API_JSON_DATE_SERIALIZER = "com.chuanmian.common.util.JsonDateSerializer";
	private static final String API_DATE_TIME_FORMAT = "org.springframework.format.annotation.DateTimeFormat";

	public CommentGenerator()
	{
		properties = new Properties();
	}

	/**
	 * 设置用户配置的参数
	 */
	@Override
	public void addConfigurationProperties(Properties properties)
	{
		// 获取自定义的 properties
		super.addConfigurationProperties(properties);
		this.properties.putAll(properties);
		this.addRemarkComments = StringUtility.isTrue(properties.getProperty("addRemarkComments"));
	}

	@Override
	public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable)
	{
		String author = properties.getProperty("author");
		String dateFormat = properties.getProperty("dateFormat", "yyyy-MM-dd");
		SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormat);

		// 获取表注释
		String remarks = introspectedTable.getRemarks();

		topLevelClass.addJavaDocLine("/**");
		topLevelClass.addJavaDocLine(" * " + remarks);
		topLevelClass.addJavaDocLine(" *");
		topLevelClass.addJavaDocLine(" * @author " + author);
		topLevelClass.addJavaDocLine(" * @date   " + dateFormatter.format(new Date()));
		topLevelClass.addJavaDocLine(" */");
	}

	/**
	 * 给字段添加注释
	 */
	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn)
	{
		// 获取列注释
		String remarks = introspectedColumn.getRemarks();
		field.addJavaDocLine("/**");
		field.addJavaDocLine(" * " + remarks);
		field.addJavaDocLine(" */");
	}

	// @Override
	// public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn
	// introspectedColumn)
	// {
	// String remarks = introspectedColumn.getRemarks();
	// // 根据参数和备注信息判断是否添加备注信息
	// if (addRemarkComments && StringUtility.stringHasValue(remarks))
	// {
	// // addFieldJavaDoc(field, remarks);
	// // 数据库中特殊字符需要转义
	// if (remarks.contains("\""))
	// {
	// remarks = remarks.replace("\"", "'");
	// }
	// // 给model的字段添加swagger注解
	// field.addJavaDocLine("@ApiModelProperty(value = \"" + remarks + "\")");
	//
	// // createdTime或者updatedTime时间格式化
	// if ("createdTime".equals(field.getName()) || "updatedTime".equals(field.getName()))
	// {
	// field.addJavaDocLine("@DateTimeFormat(pattern=\"yyyy-MM-dd HH:mm:ss\")");
	// field.addJavaDocLine("@JsonSerialize(using=JsonDateSerializer.class)");
	// }
	// }
	// }
	//
	// /**
	// * 给model的字段添加注释
	// */
	// private void addFieldJavaDoc(Field field, String remarks)
	// {
	// // 文档注释开始
	// field.addJavaDocLine("/**");
	// // 获取数据库字段的备注信息
	// String[] remarkLines = remarks.split(System.getProperty("line.separator"));
	// for (String remarkLine : remarkLines)
	// {
	// field.addJavaDocLine(" * " + remarkLine);
	// }
	// addJavadocTag(field, false);
	// field.addJavaDocLine(" */");
	// }
	//
	// @Override
	// public void addJavaFileComment(CompilationUnit compilationUnit)
	// {
	// super.addJavaFileComment(compilationUnit);
	// //只在model中添加swagger注解类的导入
	// if(!compilationUnit.isJavaInterface()&&!compilationUnit.getType().getFullyQualifiedName().contains(EXAMPLE_SUFFIX))
	// {
	// compilationUnit.addImportedType(new FullyQualifiedJavaType(API_MODEL_PROPERTY_FULL_CLASS_NAME));
	// compilationUnit.addImportedType(new FullyQualifiedJavaType(API_JSON_SERIALIZE));
	// compilationUnit.addImportedType(new FullyQualifiedJavaType(API_JSON_DATE_SERIALIZER));
	// compilationUnit.addImportedType(new FullyQualifiedJavaType(API_DATE_TIME_FORMAT));
	// }
	// }
}
