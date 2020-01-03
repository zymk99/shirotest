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
        }
    }

});