package com.mark.o2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mark.o2o.dao.AreaDao;
import com.mark.o2o.entity.Area;
import com.mark.o2o.service.AreaService;

//托管
@Service
public class AreaServiceImpl implements AreaService{

	//Spring 自动注入
	@Autowired
	private AreaDao areaDao;

	//Service层调用Dao层
	public List<Area> getAreaList() {
		return areaDao.query();
	}

}
