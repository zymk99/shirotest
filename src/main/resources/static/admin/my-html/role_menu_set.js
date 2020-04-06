var _transfer=new Vue({
    el:"._transfer",
    data:{
        dataall:[],
        value:[],
        menuid:''
    },
    methods:{
            filterMethod:function(query, item) {
                return item.label;
            },
            initData:function(menuid)
            {
                this._data.menuid=menuid;
                var tmp=[];
                var value=Zafkiel.request("/data/getRoleByMenu",{menuid:menuid});
                if(value)
                {
                    this._data.dataall=value;
                    for(var i=0;i<value.length;i++)
                    {
                        if(value[i].flag)
                        {
                            tmp.push(value[i].key);
                        }
                    }
                }
                this._data.value=tmp;
            },
            submit:function()
            {
                var v=this._data.value;
                var menuid=this._data.menuid;
                var list=[];
                for(var i=0;i<v.length;i++){
                    var tmp={};
                    tmp.menuid=menuid;
                    tmp.roleid=v[i];
                    list.push(tmp);
                }
                var result=Zafkiel.request("/data/setMenuRoleRela",list,"post",null,null,true);
                if(result==true){
                    swal({text: "修改成功！",});
                }
            }
    }
})







