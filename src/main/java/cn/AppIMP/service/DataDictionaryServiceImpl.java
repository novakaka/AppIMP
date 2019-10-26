package cn.AppIMP.service;

import java.util.List;

import javax.annotation.Resource;

import cn.AppIMP.mapper.datadictionary.DataDictionaryMapper;
import cn.AppIMP.pojo.DataDictionary;
import org.springframework.stereotype.Service;


@Service
public class DataDictionaryServiceImpl implements DataDictionaryService {
	
	@Resource
	private DataDictionaryMapper mapper;
	
	@Override
	public List<DataDictionary> getDataDictionaryList(String typeCode)
			throws Exception {
		// TODO Auto-generated method stub
		return mapper.getDataDictionaryList(typeCode);
	}

}
