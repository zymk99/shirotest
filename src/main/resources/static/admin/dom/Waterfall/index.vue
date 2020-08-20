<template>
    <div >
        <div v-for="item in myrownum">{{item}}</div>
    </div>
</template>

<script>
    module.exports={
        props:{
            rownum:Number
        },
        data(){             //自定义组件的data是个方法
            return{
                myrownum:this.rownum
            }
        },
        created(){

        },
        mounted(){                    //组件装配后
            this.AdditionalData();
        },
        methods:{
            //是否触底
            TouchBottom(){
                let scrollTop = document.documentElement.scrollTop||document.body.scrollTop;
                //变量windowHeight是可视区的高度
                let windowHeight = document.documentElement.clientHeight || document.body.clientHeight;
                //变量scrollHeight是滚动条的总高度
                let scrollHeight = document.documentElement.scrollHeight||document.body.scrollHeight;
                //滚动条到底部的条件
                if(scrollTop+windowHeight==scrollHeight){
                    return true;
                }
                return false;
            },
            //追加数据
            AdditionalData(){
                //无法动态刷新循环
                // while(this.TouchBottom()){
                //     debugger
                //     this._data.myrownum++;
                // }
            }
        }
    }
</script>