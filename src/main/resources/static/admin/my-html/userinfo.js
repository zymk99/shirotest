var userAddForm=new Vue({
    el:".user_addform",
    data:{
        name:"",
        passwd:"",
        emails:"",
        imageUrl:""
    },
    methods:{
        submit:function(arg){
            var bean={};
            bean.name=this.name;
            bean.passwd=this.passwd;
            bean.emails=this.emails;
            bean.imageUrl=this.imageUrl;
            $.ajax({
                url:"/data/addUser",
                type:"post",
                contentType:"application/;charset=UTF-8",
                dataType:"json",
                data:JSON.stringify(bean),
                success:function(arg){
                    debugger
                }
            })
        },
        handleAvatarSuccess:function(res, file) {
            this.imageUrl = URL.createObjectURL(file.raw);
            debugger
            this.file=file;
        }
    }
})