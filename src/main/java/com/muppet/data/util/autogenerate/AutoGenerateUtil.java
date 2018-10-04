package com.muppet.data.util.autogenerate;

import com.muppet.core.ioc.ApplicationContext;
import com.muppet.core.ioc.AutowiredApplicationContext;
import com.muppet.data.core.EntityMappingDBXMLConfig;
import com.muppet.data.core.StandardEntityMappingDBXMLConfig;
import com.muppet.data.core.StandardXMLConfig;
import com.muppet.data.core.XMLConfig;


/**
 * 用户使用工具类，只需要向这个类传一个配置文件，即可自动生成实体类
 * @author 于海强
 *
 */
public class AutoGenerateUtil {

	public static void generate(String xmlPath)
	{
		ApplicationContext applicationContext = new AutowiredApplicationContext();
		XMLConfig config = new StandardXMLConfig(xmlPath, applicationContext);
		EntityMappingDBXMLConfig resourceConfig = new StandardEntityMappingDBXMLConfig(config.getXMLConfigResource());
		AutoGenerateConfig autoGenerateConfig = new StandardDB2EntityAutoGenereateXMLConfig(resourceConfig.getXMLConfigResource());
		AutoGenerate autoGenerate = new XmlAutoGenerate(autoGenerateConfig.getAutoInfo(), applicationContext);
		autoGenerate.generate();
	}
	
	public static void main(String[] args){
		AutoGenerateUtil.generate("muppet.xml");
	}
	
}
