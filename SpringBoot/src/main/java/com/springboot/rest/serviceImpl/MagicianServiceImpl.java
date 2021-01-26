package com.springboot.rest.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.springboot.rest.model.Magician;
import com.springboot.rest.service.MagicianService;

@Service
@Scope("prototype")
public class MagicianServiceImpl implements MagicianService{
	
	private static Map<String, Magician> magicianRepo = new HashMap<>();
	
	static {
		Magician morgana = new Magician();
		morgana.setName("Morgana");
		morgana.setLevel(99);
		magicianRepo.put("Morgana", morgana);
		
		Magician rookie = new Magician();
		rookie.setName("Rookie");
		rookie.setLevel(1);
		magicianRepo.put("Rookie", rookie);
	}

	@Override
	public Map<String, Magician> getMagician() {
		return magicianRepo;
	}

	@Override
	public void registerMagician() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateMagician() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unregisterMagician() {
		// TODO Auto-generated method stub
		
	}

}
