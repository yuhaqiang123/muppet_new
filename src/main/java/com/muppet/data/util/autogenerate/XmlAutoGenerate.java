package com.muppet.data.util.autogenerate;

import com.muppet.core.ioc.ApplicationContext;
import com.muppet.data.core.DataBaseCheck;
import com.muppet.data.util.log.Logger;

public class XmlAutoGenerate extends DefaultAutoGenerate{

	private AutoInfo info;
	
	public XmlAutoGenerate(AutoInfo info, ApplicationContext applicationContext){
		super(info, applicationContext);
		this.info = info;
	}
	
	@Override
	protected String getClassName(String tableName){
		return info.getDomainObjectName(tableName);
	}
	
	
	@Override
	public void generate() {
		for(String tableName:info.KeySets()){
			
				DataBaseCheck.TableCheck tableCheck = check.createTableCheck(tableName);
				if(!tableCheck.isExist()){
					new GenerateException(
							String.format("Unable to generate entity class :%s, related table with name %s does not exist"
									,getClassName(tableName),tableName)).printStackTrace();
					continue;
				}
				createClass(tableCheck);
				Logger.println(tableCheck.getTableName()+"表对应实体类"+info.getDomainObjectName(tableName)+"已经生成。");
			
			
		}
	}

	
	
}
