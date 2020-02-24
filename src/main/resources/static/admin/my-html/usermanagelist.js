var userListPage=new Vue({
    el:".userlist_div",
    data:{
        tableData:null,
        pagesize:9,
        pagenum:1,
        nummax:50,
        openFrom:null
    },
    methods:{
        handleClick:function(arg,modeType)     //查看/修改
        {
            // this.openFrom=layer.open({
            //     id:"layer_userinfo",
            //     type: 2,
            //     title:"用户信息",
            //     content: ['/admin/my-html/userinfo.html', 'no'], //url,no防止出现滚动条
            //     area:["500px","500px"],
            //     anim:"6",
            //     closeBtn:"1",
            //     shadeClose:true,
            //     success:function(){
            //         if(window.userAddForm)
            //         {
            //             window.userAddForm.setPageData(arg);
            //             if(modeType=="view")
            //             {
            //                 window.userAddForm.setReadOnly(true);
            //             }
            //         }
            //     }
            // });
            this.openFrom=Zafkiel.loadPage('/admin/my-html/userinfo.html',"layer_userinfo","用户信息", function(){
            if(window.userAddForm)
            {
                window.userAddForm.setPageData(arg);
                if(modeType=="view")
                {
                    window.userAddForm.setReadOnly(true);
                }
            }
        })
        },
        deleteClick:function(arg)
        {
            debugger
        },
        lastPage:function(arg)
        {
            //上一页
            this.pagenum-=1;
            this.pageSearch();
        },
        nextPage:function(arg)
        {
            //下一页
            this.pagenum+=1;
            this.pageSearch();
        },
        pageSearch:function()
        {
            var bean={"pagenum":this.pagenum,"pagesize":this.pagesize};
            $.ajax({
                url:"/data/getAllUser",
                type:"post",
                contentType:"application/json;charset=UTF-8",
                dataType:"json",
                data:JSON.stringify(bean),
                success:function(arg)
                {
                    if(arg)
                    {
                        userListPage._data.tableData=arg;
                        userListPage._data.nummax=arg[0].count;
                    }
                }
            })
        },
        openAddUser:function(){

        }
    },
    created:function(){
        this.pageSearch();
        window.userListPage=userListPage;
    }
})