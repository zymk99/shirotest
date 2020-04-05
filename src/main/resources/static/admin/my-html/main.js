var menuset=new Vue({
    el:".menuset",
    data: {
        menudata: new function () {
            $.ajax({
                url:"/data/getMenu",
                type:"get",
                dataType:"json",
                success:function(arg)
                {
                    for(var i=0;i<arg.length;i++)
                    {
                        arg[i].childList=JSON.parse(arg[i].childlist);
                    }
                    menuset.menudata=arg;
                }
            })
        },
        currentItem:null
    },
    methods:{
        deleteItem:function (arg) {
            //JSON.parse(JSON.stringify(arg))
           if(arg && arg.id)
           {
               this.currentItem=arg;
               swal({
                   title: "确定删除该节点?",
                   text: "确定删除该节点?",
                   icon: "warning",
                   buttons: ["否!", "是!"],
               }).then(function(willDelete){
                   if(willDelete) {
                       //确认删除
                       var bean={};
                       bean.id=arg.id;
                       $.ajax({
                           url:"/data/deleItem",
                           type:"post",
                           contentType: "application/json;charset=UTF-8",
                           dataType:"json",
                           data:JSON.stringify(bean),
                           success:function(arg)
                           {
                               if(arg && arg.flag)
                               {
                                   swal({'text':'删除成功！'});
                               }
                               else
                               {
                                   swal({'text':'删除失败，请检测权限！'});
                               }
                           }
                       });
                   }
               });
           }
        },
        addItem:function(arg)
        {
            if(arg && arg.id)
            {
                this.currentItem=arg;
                var d={
                    type:"insert"
                };
                menuFrom._data.m=d;
                menuFrom.$el.style.display="block";
            }
        },
        updateItem:function(arg)
        {
            if(arg)
            {
                this.currentItem=arg;
                var d={};
                d.url=arg.url;
                d.name=arg.name;
                d.isvalid=arg.isvalid;
                d.icon=arg.icon;
                d.type="update";
                menuFrom._data.m=d;
                menuFrom.$el.style.display="block";
            }
        }
    }

});
var menuFrom=new Vue({
    el:".menu_from",
    data:{
        m:{
            url:"",
            name:'',
            isvalid:0,
            icon:'',
            type:''
        }
    },
    methods:{
        submit:function(arg){

            if(menuset._data.currentItem)
            {
                var bean={};
                var path="";
                if(this.m.type=="insert")
                {
                    bean.pmenuid=menuset._data.currentItem.id;
                    path="/data/insertMenu"
                }
                if(this.m.type=="update")
                {
                    bean.id=menuset._data.currentItem.id;
                    path="/data/updateMenu"
                }
                bean.name=this.m.name;
                bean.icon=this.m.icon;
                bean.url=this.m.url;

                $.ajax({
                    url:path,
                    type:"post",
                    dataType:"json",
                    data:bean,
                    success:function(arg){
                        if(arg && arg.flag)
                        {
                            swal({text: "提交成功！",});
                            menuFrom.$el.style.display="none";
                        }
                    }
                })
            }
        },
        _close:function(arg){

            this.$el.style.display="none";
        },
        relatrole:function()
        {
            Zafkiel.loadPage("/admin/my-html/role_menu_set.html","m_r","关联角色");
        }
    },
    created:function(){
    }
})








