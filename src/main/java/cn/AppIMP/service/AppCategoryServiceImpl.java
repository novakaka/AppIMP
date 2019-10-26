package cn.AppIMP.service;

import javax.annotation.Resource;

import cn.AppIMP.mapper.appcategory.AppCategoryMapper;
import cn.AppIMP.pojo.AppCategory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppCategoryServiceImpl implements AppCategoryService {

	@Resource
	private AppCategoryMapper mapper;
	
	@Override
	public List<AppCategory> getAppCategoryListByParentId(Integer parentId)
			throws Exception {
		// TODO Auto-generated method stub
		return mapper.getAppCategoryListByParentId(parentId);
	}

}
