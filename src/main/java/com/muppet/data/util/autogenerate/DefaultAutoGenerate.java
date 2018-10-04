package com.muppet.data.util.autogenerate;

import com.muppet.core.ioc.ApplicationContext;
import com.muppet.data.core.DataBaseCheck;
import com.muppet.data.sql.SqlType;
import com.muppet.data.sql.TypeConvertMapper;
import com.muppet.data.util.StringUtil;
import com.muppet.data.util.log.Logger;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;

/**
 * 默认代码生成器，默认读取数据库所有的表，自动映射相应按表名大写命名的实体类
 * 需要说明的是：数据表映射需要两方面的考虑，第一哪些数据表需要映射，第二实体类命名 在本类中，将两种分开，可以通过继承分别重写getClassName()
 * createClass();重写createClass可以指定如何
 * 
 * @author 于海强
 * 
 *
 */
class DefaultAutoGenerate implements AutoGenerate {
	protected DataBaseCheck check;
	protected ApplicationContext applicationContext;
	private AutoInfo info;

	public DefaultAutoGenerate(AutoInfo info, ApplicationContext applicationContext) {
		this.info = info;
		check = new DataBaseCheck(applicationContext);
	}

	/**
	 * 根据tableName获取对应实体类，相应类可以覆盖， 如果不覆盖，使用createClass将会默认按照表名首字母大写命名实体类
	 * 
	 * @param tableName
	 * @return
	 */
	protected String getClassName(String tableName) {
		return StringUtil.upperCaseFirst(tableName);
	}

	/**
	 * 创建Class，创建class有两处需要注意,第一哪些表映射，这个方法不需要关注，
	 * 输入时tableCheck，只对应一个Table,第二：实体类命名规则，如果不覆盖本类 getClassName那么默认按照表名首字母大写命名
	 * 
	 * @param tableCheck
	 */
	protected final void createClass(DataBaseCheck.TableCheck tableCheck) {
		try {
			List<DataBaseCheck.ColumnCheck> columnList = tableCheck.getColumns();
			String clazzName = getClassName(tableCheck.getTableName());
			ICreateClass createClass = new CreateClass(clazzName, info.getGeneratePath());
			if (columnList == null) {
				return;
			} else {

				for (DataBaseCheck.ColumnCheck columnCheck : columnList) {
					SqlType type = columnCheck.getSqlType();
					Type types = TypeConvertMapper.sqlToJava(type)[0];
					Class clazz = (Class) (types);
					String javaTypeName = clazz.getCanonicalName();
					Field field = new Field(javaTypeName, columnCheck.getColumnName());
					field.setNeedGetAndSet(true);
					createClass.addField(field);

				}
			}
			createClass.create();
		} catch (SQLException e) {
			throw new GenerateException(e);
		}

	}

	/**
	 * 生成对应实体类
	 */
	public void generate() {
		try {
			List<DataBaseCheck.TableCheck> list = check.getTableChecks();
			if (list == null) {
				return;
			} else {

				for (DataBaseCheck.TableCheck tableCheck : list) {

					List<DataBaseCheck.ColumnCheck> columnList = tableCheck.getColumns();
					String clazzName = StringUtil.upperCaseFirst(tableCheck.getTableName());
					ICreateClass createClass = new CreateClass(clazzName, info.getGeneratePath());
					if (columnList == null) {
						continue;
					} else {

						for (DataBaseCheck.ColumnCheck columnCheck : columnList) {
							SqlType type = columnCheck.getSqlType();
							// Logger.println(""+type);
							Type types = TypeConvertMapper.sqlToJava(type)[0];
							Class clazz = (Class) (types);
							String javaTypeName = clazz.getCanonicalName();
							Field field = new Field(javaTypeName, columnCheck.getColumnName());
							field.setNeedGetAndSet(true);
							createClass.addField(field);

						}
					}
					createClass.create();
					Logger.println(tableCheck.getTableName() + "表对应实体类" + clazzName + "已经生成。");
				}
			}
		} catch (SQLException e) {
			throw new GenerateException(e);
		}

	}

	public static void main(String[] args) {
		/*
		 * Class clazz = Byte[].class; if(clazz.isArray()){
		 * System.out.println(clazz.getCanonicalName()); }
		 */
		/*
		 * AutoGenerate autoGenerate = new
		 * StandardAutoGenereateTableXMLConfig("data.xml"); AutoInfo info =
		 * autoGenerate.getAutoInfo();
		 */

		// AutoGenerateCode autoGenerateCode = new AutoGenerateCode(AutoInfo);
		// autoGenerateCode.generate("cn.bronzeware.data.auto.test");
	}

}
