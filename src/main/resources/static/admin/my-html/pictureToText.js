var picToText_up=new Vue({
    el:".picToText_up",
    data:{
        imageUrl:""
    },
    methods:{
        beforeAvatarUpload(file){
            if(file.size/1024/1024>20)
            {
                this.$message.error('识别图片不能超过20M!');
                return false;
            }
            return true;
        },
        handleAvatarSuccess(res, file) {
            this.imageUrl = URL.createObjectURL(file.raw);
        }
    }
})