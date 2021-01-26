package com.springboot.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest.entity.Guild;
import com.springboot.rest.repository.GuildRepository;

@RestController
public class GuildController {
	@Autowired
	GuildRepository guildRepository;
	
	@RequestMapping(value = "/guild", method = RequestMethod.GET)
	public List<Guild> queryAllAdventurer() {
		return guildRepository.findAll();
	}
}
