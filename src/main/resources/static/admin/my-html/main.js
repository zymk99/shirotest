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
               }).then((willDelete)=>{
                   if(willDelete) {
                       swal("删除成功!", {
                           icon: "success",
                       });
                   } else {
                       swal("no!");
                    }
               });
           }
        },
        addItem:function(arg)
        {
            if(arg && arg.id)
            {
            }
        }
    }

});


var menuFrom=new Vue({
    el:".c-panel",
    data:{
        m:{
            url:"",
            name:'',
            isvalid:0,
            icon:''
        }
    },
    methods:{
        submit:function(arg){
            debugger
            if(menuset._data.currentItem)
            {

            }
        },
    },
    created:function(){
    }
})








