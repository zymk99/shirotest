var picToText_up=new Vue({
    el:".picToText_up",
    data:{
        imageUrl:""
    },
    methods:{
        beforeAvatarUpload:function(file){
            if(file.size/1024/1024>20)
            {
                this.$message.error('识别图片不能超过20M!');
                return false;
            }
            return true;
        },
        handleAvatarSuccess:function(res, file) {
            this.imageUrl = URL.createObjectURL(file.raw);
            var value=Zafkiel.request("/data/picToText",null,"post");
            debugger
        }
    }
})

var picToText_text=new Vue({
    el:".picToText_text",
    data:{
        textarea1:""
    },
    methods:{

    }
})