var userListPage=new Vue({
    el:".userlist_div",
    data:{
        tableData:null,
        pasize:9,
        pagenum:1,
        pagemax:5
    },
    methods:{
        pagechange:function(arg)
        {
            debugger
        }
    },
    created:function(){
        var bean={"pagenum":this.pagenum,"pagesize":this.pasize};
        $.ajax({
            url:"/data/getAllUser",
            type:"post",
            contentType:"application/json;charset=UTF-8",
            dataType:"json",
            data:JSON.stringify(bean),
            success:function(arg)
            {

                userListPage._data.tableData=arg;
            }
        })
    }
})