package com.muppet.data.util.autogenerate;

import com.muppet.data.core.XMLConfigResource;

public class EntityMappingDBResource extends XMLConfigResource {

	private String [] packages;
	private boolean isBuilded;
	public String[] getPackages() {
		return packages;
	}
	public void setPackages(String[] packages) {
		this.packages = packages;
	}
	public boolean isBuilded() {
		return isBuilded;
	}
	public void setBuilded(boolean isBuilded) {
		this.isBuilded = isBuilded;
	}
}
