//sweetalert.min.js  jquery.min.js   layer.js

var Zafkiel={}
//弹窗打开页面
Zafkiel.loadPage=function(url,id,title,success){
     return layer.open({
        id:id,
        type: 2,
        title:title,
        content: [url, 'no'], //url,no防止出现滚动条
        area:["500px","500px"],
        anim:"6",
        closeBtn:"1",
        shadeClose:true,
        success:success
    });
}
Zafkiel.request=function(_url,par,type,success_func,error_func,isJsonString,async){  //发送请求 默认get  后台接受参数为map时要传json字符串
   if(!type)
   {
      type="get";
   }
   var bean={
      url:_url,
      type:type,
      data:par,
      success:success_func
   }
   if(isJsonString){           //后台用map接收时需要
      bean.contentType="application/json;charset=UTF-8";
      bean.dataType="json";
      bean.data=JSON.stringify(par);
   }
   var data;
   bean.error=error_func;
   if(async==false || async==null)//非异步
   {
      bean.async=false;
      if(success_func==null)
      {
         bean.success=function(arg)
         {
            data=arg;
         }
      }
   }
   $.ajax(bean);
   return data;
}
Zafkiel.getUserInfo=function(){
   if(this.userInfo)
   {
      return this.userInfo;
   }
   var userInfoStr=Zafkiel.request("/data/getUserInfo");
   this.userInfo=JSON.parse(userInfoStr)
   return this.userInfo;
}