package com.muppet.data.filters;

import com.muppet.data.core.AbstractConfig;
import com.muppet.data.core.XMLConfigResource;

public class StandardFilterXMLConfig extends AbstractConfig implements FilterXMLConfig{

	public StandardFilterXMLConfig(XMLConfigResource resource) {
		super(resource);
		
	}

	@Override
	public FilterWrapper[] getFilterWrapper() {
		
		return null;
	}
	
	
	
	
	

}
