package com.springboot.rest.service;

import java.util.Map;

import com.springboot.rest.model.Magician;

public interface MagicianService {
	public Map<String, Magician> getMagician();
	
	public void registerMagician();
	
	public void updateMagician();
	
	public void unregisterMagician();
}
