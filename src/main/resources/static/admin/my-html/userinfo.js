var userAddForm=new Vue({
    el:".user_addform",
    data:{
        id:"",
        name:"",
        passwd:"",
        emails:"",
        imageUrl:"",
        type:"",
        isReadOnly:false
    },
    methods:{
        submit:function(arg){
            var bean={};
            bean.name=this.name;
            bean.passwd=this.passwd;
            bean.emails=this.emails;
            bean.imageUrl=this.imageUrl;
            this.$refs.upload.submit();
            var url="/data/addUser";
            if(this.type="update"){
                url="/data/updateUser";
                bean.id=this.id;
            }
            $.ajax({
                url:url,
                type:"post",
                contentType:"application/json;charset=UTF-8",
                dataType:"json",
                data:JSON.stringify(bean),
                success:function(arg){
                    debugger
                    layer.msg('新增完成！');
                },
                error:function(arg)
                {
                    debugger
                }
            })

            // layer.close(userListPage._data.openFrom);
        },
        handleAvatarSuccess:function(res, file) {
            this.imageUrl = URL.createObjectURL(file.raw);
            this.file=file;
        },
        setPageData:function(arg) {
            this.name=arg.name;
            this.passwd=arg.passwd;
            this.emails=arg.emails;
            this.imageUrl=arg.imageUrl;
            this.id=arg.id;
        },
        setReadOnly:function(flag) {
            if(flag) {
                this.type="see";
                this.isReadOnly=true;
            }
        }
    },
    created:function(arg)
    {
        this.type="update";
        if(window.parent)
        {
            window.parent.userAddForm=this;
        }
    }
})