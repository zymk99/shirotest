package com.example.shirotest.controller;

import com.example.shirotest.Utils.CurrUtils;
import com.example.shirotest.Utils.MinioUtils;
import com.example.shirotest.Utils.RedisUtils;
import com.example.shirotest.Utils.TencentAuth;
import com.example.shirotest.dao.IndexMenu;
import com.example.shirotest.dao.TUser;
import com.example.shirotest.mapper.*;
import com.example.shirotest.server.word.ExportWord;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

@RestController
@RequestMapping("/data")
public class DataController {
    String server_url="http://203.195.251.136:9000";
    String ico_bucket="icosource";//用户头像桶
    @Autowired
    IndexMenuMapper menu;
    @Autowired
    UserMapper um;
    @Autowired
    MinioUtils minio;
    @Autowired
    TencentAuth tencentAuth;
    @Autowired
    RolePermMapper roleper;

    @Autowired
    private RedisUtils redis;
    @Autowired
    PictureModuleMapper pmm;
    //测试用
    @RequestMapping("/test")
    public void test(){
        LinkedList<Map> ttt= pmm.getAllData();
        int aa=10;
    }
    //获取瀑布流图片数据
    @PostMapping("/getWaterfallData")
    public LinkedList<Map<String,String>> getWaterfallData(){
        return null;
    }
    //角色-目录关联变动   事务
    @PostMapping("/setMenuRoleRela")
    @Transactional
    public boolean setMenuRoleRela(@RequestBody LinkedList<Map<String,String>> par){
        if(par==null && par.size()<=0)
        {
            return false;
        }
        menu.deleMenuRoleRelaByMenuid(par.get(0).get("menuid").toString());
        for(Map<String,String> map:par) {
                map.put("id",UUID.randomUUID().toString().replaceAll("-",""));
                menu.setMenuRoleRela(map);
        }
        return true;
    }
    //获取目录绑定的角色
    @RequestMapping("/getRoleByMenu")
    public LinkedList<Map> getRoleByMenu(@RequestParam("menuid") String menuid){
        return menu.getRoleByMenuid(menuid);
    }
    @RequestMapping(value="/null")
    public String ReruNull()
    {
        return null;
    }
    //获取用户信息
    Map userInfoMap =null;
    @RequestMapping(value="/getUserInfo",produces = "text/plain;charset=utf-8")
    public String getUserInfo(){
        if(userInfoMap==null)
        {
            Subject sub=SecurityUtils.getSubject();
            TUser tu=(TUser)sub.getPrincipal();
            Map tmpMap=roleper.getRoleByUserid(tu.getId());
            if(tmpMap.size()>0)
            {
                userInfoMap=(Map)tmpMap.get(tmpMap.keySet().toArray()[0]);
            }
        }
        JSONObject jsob=JSONObject.fromObject(userInfoMap);
        return jsob.toString();
        //return CurrUtils.ClassToJsonstring(userInfoMap);
    }
    //获取首页
    @RequestMapping("/getHomePage")
    public String getHomePage()
    {
        Subject sub=SecurityUtils.getSubject();
        if(sub.hasRole("admin"))
        {
            return "/admin/my-html/main.html";
        }
        return "/admin/my-html/main2.html";
    }
    //获取菜单
    @RequestMapping(value="/getMenu",produces = "text/plain;charset=utf-8")
    public String getMenu(@RequestParam("roleid") String roleid)
    {
//        Subject sj=SecurityUtils.getSubject();
//        sj.isPermitted("print");
        IndexMenu[] me=null;
        if(roleid.equals("19a40a2702b04fc1816dab9db9488400"))
        {
            me=menu.getMenuByAdmin();
        }else{
            me=menu.getMenuByRole(roleid);
        }
        Map menuMap=new HashMap();
        for(IndexMenu m:me)
        {
            IndexMenu tmp_im=m;
            if(menuMap.get(m.getId())!=null)
            {
                tmp_im=(IndexMenu)menuMap.get(m.getId());
                tmp_im.setChildlist( tmp_im.getChildlist()+","+m.getChildlist() );
            }
            menuMap.put(m.getId(),tmp_im);
        }


        List list=new LinkedList<Map>();
        for(Object tmp_s:menuMap.keySet())
        {
            IndexMenu indexm=(IndexMenu)menuMap.get(tmp_s.toString());
            indexm.setChildlist("["+indexm.getChildlist()+"]");
            list.add(menuMap.get(tmp_s.toString()));
        }
        JSONArray json=JSONArray.fromObject(list);
        return json.toString();
    }
    //删除菜单节点
    @PostMapping(value="/deleItem")
    @RequiresPermissions("menuindex:delete")
    public String deleItemById(@RequestBody Map<String,String> par)
    {
        String val="";
        if(menu.deleItemById(par.get("id")))
        {
            val="{'flag':true}";
        }
        else
        {
            val="{'flag':false}";
        }
        return val;
    }
    //新增菜单节点
    @PostMapping("/insertMenu")
    @RequiresPermissions("menuindex:add")
    public String insertMenu(IndexMenu indexMenu)
    {
        indexMenu.setId(UUID.randomUUID().toString().replaceAll("-",""));
        if(menu.insertMenu(indexMenu))
        {
            return JSONObject.fromObject("{'flag':true}").toString();
        }
        return JSONObject.fromObject("{'flag':false}").toString();
    }

    //修改菜单节点
    @PostMapping("/updateMenu")
    @RequiresPermissions("menuindex:update")
    public String updateMenu(IndexMenu indexMenu)
    {
        if(indexMenu!=null && indexMenu.getId()!=null && menu.updateItemById(indexMenu))
        {
            return JSONObject.fromObject("{'flag':true}").toString();
        }
        return JSONObject.fromObject("{'flag':false}").toString();
    }
    @RequestMapping("/power")
    @RequiresPermissions("user:add")
    public String power()
    {
        return "123456";
    }

    @RequestMapping("/cancellation")    //注销
    public String cancellation(){
        Subject currentUser = SecurityUtils.getSubject();
        if(currentUser!=null)
        {
            currentUser.logout();
        }
        return "{}";
    }
    @PostMapping("/login" )
    public String postLog(String name,String passwd)
    {
        Subject currentUser = SecurityUtils.getSubject();
        //注销
        currentUser.logout();
        userInfoMap=null;
        if (!currentUser.isAuthenticated()) {
            // 把用户名和密码封装为 UsernamePasswordToken 对象
            UsernamePasswordToken token = new UsernamePasswordToken(name, passwd);
            // 设置为rememberme
            token.setRememberMe(true);
            try {
                // 执行登录.
                currentUser.login(token);
            }
            // 所有认证时异常的父类
            catch (AuthenticationException ae) {
                return "{}";
            }
            currentUser.hasRole("add");
        }
        return "{}";
    }

    @PostMapping(value="/getAllUser" , produces = "text/plain;charset=utf-8")
    public String getAllUser(@RequestBody Map<String,String> map)
    {
        if(StringUtils.isBlank(map.get("pagenum")) ||StringUtils.isBlank(map.get("pagesize")) )
        {
            return null;
        }
        int beginindex=(Integer.parseInt(map.get("pagenum"))-1)*Integer.parseInt(map.get("pagesize"));
        map.put("beginindex", String.valueOf( beginindex )  );
        List<Map> userList= um.selectAllUser(map);
        if(userList.size()>0)
        {
            for(Map tmp:userList)
            {
                tmp.put("index",++beginindex);
            }
        }
        return (JSONArray.fromObject(userList) ).toString();
    }

    //创建用户
    @PostMapping("/addUser")
    public String addUser(@RequestBody Map map, HttpServletRequest request)
    {
        HttpSession session=request.getSession();
        String id=UUID.randomUUID().toString().replaceAll("-","");
        map.put("id",id);
        if(session.getAttribute("userTmpHeadPortrait")!=null)
        {
            Map filemap=(Map) session.getAttribute("userTmpHeadPortrait");
            if( minio.upload(ico_bucket,filemap,null,null)  ){
                String fileName=filemap.get("fileName").toString();
                //获取刚才上传图片的路径
                //String url=minio.getUrl("icosource",fileName);
                map.put("icon",server_url+"/"+ico_bucket+"/"+fileName);
            }
        }
        if(um.addUser(map)){
            session.removeAttribute("userTmpHeadPortrait");
            postLog(map.get("name").toString(),map.get("passwd").toString());
            return "{\"value\":\"yes\"}";
        }else{
            return "{\"value\":\"no\"}";
        }
    }
    //修改用户
    @PostMapping("/updateUser")
    public String updateUser(@RequestBody Map map, HttpServletRequest request)
    {
        HttpSession session=request.getSession();
        if(session.getAttribute("userTmpHeadPortrait")!=null)
        {
            Map filemap=(Map) session.getAttribute("userTmpHeadPortrait");
            if( minio.upload(ico_bucket,filemap,null,null)  ){
                String fileName=filemap.get("fileName").toString();
                map.put("icon",server_url+"/"+ico_bucket+"/"+fileName);
            }
        }
        if(um.updateItemById(map))
        {
            session.removeAttribute("userTmpHeadPortrait");
            return "{\"value\":\"yes\"}";
        }
        return null;
    }
    //删除用户
    @PostMapping("/deleUser")
    public String deleteUser(@RequestBody Map user)
    {
        String id=user.get("id").toString();
        if(!StringUtils.isEmpty(id) && um.deleteUser(id))
        {
            return "{\"value\":\"yes\"}";
        }
        return "{\"value\":\"error\"}";
    }

    @RequestMapping("/createWord")
    public String createWord()  //生成字段Word
    {
        try{
            List<Map>  count=um.selectLeapCount();
            List<Map> data=um.selectLeap();
            ExportWord ew = new ExportWord();
            XWPFDocument document = ew.createXWPFDocument(count);
            List<List<Object>> list =null;
            List< List<List<Object>> > lastList = new ArrayList< List<List<Object>> >();
            int data_i=0;
            for(Map tmp:count)
            {
                list = new ArrayList<List<Object>>();
                List<Object> tempList = new ArrayList<Object>();
                tempList.add("字段名");
                tempList.add("中文名");
                tempList.add("字段类型");
                tempList.add("代码表");
                list.add(tempList);
                for(int i=0;i<Integer.parseInt(tmp.get("count").toString());i++  )
                {
                    Map row=data.get(data_i++);
                    tempList = new ArrayList<Object>();
                    tempList.add(row.get("name").toString());
                    tempList.add(row.get("cnname").toString());
                    tempList.add(row.get("datatype").toString());
                    tempList.add(row.get("codetype").toString());
                    list.add(tempList);
                }
                lastList.add(list);
            }

            Map<String, Object> dataList = new HashMap<String, Object>();
            dataList.put("TITLE", count);
            dataList.put("TABLEDATA", lastList);
            ew.exportCheckWord(dataList, document, "E:/expWordTest.docx");
            System.out.println("文档生成成功");
        }catch (Exception e)
        {
            return null;
        }
        return null;
    }

    //图片转文字
    @PostMapping(value="/picToText",produces = "text/plain;charset=utf-8")
    public String picToText( HttpServletRequest request){
        HttpSession session=request.getSession();
        try {
            if(session.getAttribute("GeneralPortrait")!=null)
            {
                Map map=(Map)session.getAttribute("GeneralPortrait");
                InputStream is=(InputStream)map.get("is");
                byte[] bytes = IOUtils.toByteArray(is);
                String encoded = Base64.getEncoder().encodeToString(bytes);
                Map<String,String> valMap=tencentAuth.getAuthentication(encoded);
//                String par="";
//                for(String tmps: valMap.keySet())
//                {
//                    par+= (tmps+"="+valMap.get(tmps).toString() +"&" );
//                }
//                par=par.substring(0,par.length()-1);
                String json =CurrUtils.ClassToJsonstring(valMap);
                String value=CurrUtils.sendPost("https://api.ai.qq.com/fcgi-bin/nlp/nlp_imagetranslate",valMap);
                return value;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}



