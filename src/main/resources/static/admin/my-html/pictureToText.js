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
            if(value)
            {
                debugger
                var text="";
                var text2="";
                var data=JSON.parse(value).data.image_records;
                for(var i=0;i<data.length;i++)
                {
                    text+=(data[i].source_text+"\n");
                    text2 +=(data[i].target_text+"\n");
                }
                picToText_text._data.textarea1=text;
            }
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