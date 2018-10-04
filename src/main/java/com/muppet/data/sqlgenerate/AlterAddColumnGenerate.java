package com.muppet.data.sqlgenerate;

import com.muppet.data.resource.ColumnInfo;
import com.muppet.data.resource.ResourceInfo;
import com.muppet.data.sql.SqlType;
import com.muppet.data.sql.TypeConvertMapper;

public class AlterAddColumnGenerate implements Generate{

	@Override
	public String generate(ResourceInfo resourceInfo) throws SqlGenerateException {
		assert resourceInfo!=null;
		assert resourceInfo instanceof ColumnInfo;
		
			ColumnInfo columnInfo = (ColumnInfo) resourceInfo;
			String check = columnInfo.getCheck();
			
			String defaultValue = columnInfo.getDefaultValue();
			int length = columnInfo.getLength();
			SqlType type = columnInfo.getType();
			String name =  columnInfo.getName();
			String[] values= columnInfo.getValues();
			boolean isPrimaryKey = columnInfo.isIsprivarykey();
			boolean isCanNull = columnInfo.isCanNull();
			boolean isUnique = columnInfo.isUnique();
			
			

			StringBuffer buffer = new StringBuffer();
			buffer.append("alter table "+columnInfo.getTableName()+" add column ");
			buffer.append(name);
			buffer.append(" ");
			buffer.append(TypeConvertMapper.sqlTypeToString(type));
			if(length!=0){
				buffer.append(" ("+length+")");
			}
			
			if(isUnique){
				buffer.append(" unique ");
			}else{
				
			}
			
			if(isCanNull){
				buffer.append(" null ");
			}else{
				buffer.append(" not null");
			}
			
			if(isPrimaryKey){
				buffer.append(" primary key ");
			}
			
			if(!defaultValue.equals("")){
				buffer.append(" default " + defaultValue);
			}
			return buffer.toString();
		
	}

	
	
}
