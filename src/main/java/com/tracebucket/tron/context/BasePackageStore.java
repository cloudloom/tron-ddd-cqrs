package com.tracebucket.tron.context;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ffl on 14-04-2015.
 */
public class BasePackageStore {
	private Set<String> basePackages = new HashSet<>(0);

	public BasePackageStore(){

	}

	public void add(String basePackage){
		this.basePackages.add(basePackage);
	}

	public void addAll(Set<String> basePackages){
		this.basePackages.addAll(basePackages);
	}

	public Set<String> getAll(){
		return this.basePackages;
	}
}
