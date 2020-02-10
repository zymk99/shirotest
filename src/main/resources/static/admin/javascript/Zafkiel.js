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