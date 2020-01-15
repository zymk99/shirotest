var userListPage=new Vue({
    el:".userlist_div",
    data:{
        tableData:null,
        pagesize:9,
        pagenum:1,
        nummax:50
    },
    methods:{
        handleClick:function(arg)     //查看
        {
            layer.open({
                type: 2,
                content: ['/admin/my-html/userinfo.html', 'no'], //url,no防止出现滚动条
                area:["500px","500px"],
                anim:"6"
            });
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
        }
    },
    created:function(){
        this.pageSearch();
    }
})