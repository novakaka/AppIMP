package cn.AppIMP.controller.develop;

import cn.AppIMP.pojo.AppCategory;
import cn.AppIMP.pojo.AppInfo;
import cn.AppIMP.pojo.DataDictionary;
import cn.AppIMP.pojo.DevUser;
import cn.AppIMP.service.AppCategoryService;
import cn.AppIMP.service.AppVersionService;
import cn.AppIMP.service.DataDictionaryService;
import cn.AppIMP.service.appinfo.AppInfoService;
import cn.AppIMP.tool.PageSupport;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/dev/flatform/app")
@SessionAttributes("user")
public class AppInFoController {

    @Resource
    private AppInfoService appInfoService;
    @Resource
    private DataDictionaryService dataDictionaryService;
    @Resource
    private AppCategoryService appCategoryService;
    @Resource
    private AppVersionService appVersionService;
    @RequestMapping("/list")
    public String  getAppInFoList( Model model, HttpSession session,
                                   @RequestParam(value="querySoftwareName",required=false) String querySoftwareName,
                                   @RequestParam(value="queryStatus",required=false) String _queryStatus,
                                   @RequestParam(value="queryCategoryLevel1",required=false) String _queryCategoryLevel1,
                                   @RequestParam(value="queryCategoryLevel2",required=false) String _queryCategoryLevel2,
                                   @RequestParam(value="queryCategoryLevel3",required=false) String _queryCategoryLevel3,
                                   @RequestParam(value="queryFlatformId",required=false) String _queryFlatformId,
                                   @RequestParam(value="pageIndex",required=false) String pageIndex) throws Exception {

        List<AppInfo> appInfoList = null;
        List<DataDictionary> statusList = null;
        List<DataDictionary> flatFormList = null;
        List<AppCategory> categoryLevel1List = null;//列出一级分类列表，注：二级和三级分类列表通过异步ajax获取
        List<AppCategory> categoryLevel2List = null;
        List<AppCategory> categoryLevel3List = null;
        //session=request.getSession();
        //获取当前用户id
       Integer devUserId= ((DevUser) session.getAttribute("user")).getId();
        //页面容量
        int pageSize =5;
        //当前页码
        Integer currentPageNo = 1;
        if(pageIndex != null){
            try{
                currentPageNo = Integer.valueOf(pageIndex);
            }catch (NumberFormatException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
        Integer queryStatus = null;
        if(_queryStatus != null && !_queryStatus.equals("")){
            queryStatus = Integer.parseInt(_queryStatus);
        }
        Integer queryCategoryLevel1 = null;
        if(_queryCategoryLevel1 != null && !_queryCategoryLevel1.equals("")){
            queryCategoryLevel1 = Integer.parseInt(_queryCategoryLevel1);
        }
        Integer queryCategoryLevel2 = null;
        if(_queryCategoryLevel2 != null && !_queryCategoryLevel2.equals("")){
            queryCategoryLevel2 = Integer.parseInt(_queryCategoryLevel2);
        }
        Integer queryCategoryLevel3 = null;
        if(_queryCategoryLevel3 != null && !_queryCategoryLevel3.equals("")){
            queryCategoryLevel3 = Integer.parseInt(_queryCategoryLevel3);
        }
        Integer queryFlatformId = null;
        if(_queryFlatformId != null && !_queryFlatformId.equals("")){
            queryFlatformId = Integer.parseInt(_queryFlatformId);
        }
        //总数量（表）
        int totalCount = 0;
        try {
            totalCount = appInfoService.getAppInfoCount(querySoftwareName,
                    queryStatus, queryCategoryLevel1, queryCategoryLevel2,
                    queryCategoryLevel3, queryFlatformId,devUserId);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //总页数
        PageSupport pages = new PageSupport();
        pages.setCurrentPageNo(currentPageNo);
        pages.setPageSize(pageSize);
        pages.setTotalCount(totalCount);
        int totalPageCount = pages.getTotalPageCount();
        //控制首页和尾页
        if(currentPageNo < 1){
            currentPageNo = 1;
        }else if(currentPageNo > totalPageCount){
            currentPageNo = totalPageCount;
        }
        try {
            appInfoList = appInfoService.getAppInfoList(querySoftwareName, queryStatus,
                    queryCategoryLevel1,queryCategoryLevel2,queryCategoryLevel3,
                    queryFlatformId,devUserId,currentPageNo,pageSize);
            statusList = this.getDataDictionaryList("APP_STATUS");
            flatFormList =this.getDataDictionaryList("APP_FLATFORM");
            categoryLevel1List = appCategoryService.getAppCategoryListByParentId(null);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        model.addAttribute("appInfoList", appInfoList);
        model.addAttribute("statusList", statusList);
        model.addAttribute("flatFormList", flatFormList);
        model.addAttribute("categoryLevel1List", categoryLevel1List);
        model.addAttribute("pages", pages);
        model.addAttribute("queryStatus", queryStatus);
        model.addAttribute("querySoftwareName", querySoftwareName);
        model.addAttribute("queryCategoryLevel1", queryCategoryLevel1);
        model.addAttribute("queryCategoryLevel2", queryCategoryLevel2);
        model.addAttribute("queryCategoryLevel3", queryCategoryLevel3);
        model.addAttribute("queryFlatformId", queryFlatformId);
        //二级分类列表和三级分类列表---回显
        if(queryCategoryLevel2 != null && !queryCategoryLevel2.equals("")){
            categoryLevel2List = getCategoryList(queryCategoryLevel1.toString());
            model.addAttribute("categoryLevel2List", categoryLevel2List);
        }
        if(queryCategoryLevel3 != null && !queryCategoryLevel3.equals("")){
            categoryLevel3List = getCategoryList(queryCategoryLevel2.toString());
            model.addAttribute("categoryLevel3List", categoryLevel3List);
        }
        return "developer/appinfolist";
    }
    public List<DataDictionary> getDataDictionaryList(String typeCode){
        List<DataDictionary> dataDictionaryList = null;
        try {
            dataDictionaryList = dataDictionaryService.getDataDictionaryList(typeCode);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dataDictionaryList;
    }

    /**
     * 根据typeCode查询出相应的数据字典列表
     *
     */
    @RequestMapping(value="/datadictionarylist.json",method=RequestMethod.GET)
    @ResponseBody//返回ajax常用的注解
    public List<DataDictionary> getDataDicList (@RequestParam String tcode){
        //logger.debug("getDataDicList tcode ============ " + tcode);
        return this.getDataDictionaryList(tcode);
    }

    /**
     * 根据parentId查询出相应的分类级别列表
     * @param pid
     * @return
     */
    @RequestMapping(value = "/categorylevellist.json",method=RequestMethod.GET)
    @ResponseBody
    public List<AppCategory> getAppCategoryList (@RequestParam String pid){
        //logger.debug("getAppCategoryList pid ============ " + pid);
        if(pid.equals("")) pid = null;
        return getCategoryList(pid);
    }

    public List<AppCategory> getCategoryList (String pid){
        List<AppCategory> categoryLevelList = null;
        try {
            categoryLevelList = appCategoryService.
                    getAppCategoryListByParentId(pid==null?null:Integer.parseInt(pid));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return categoryLevelList;
    }

}
