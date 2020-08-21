<template>
    <div >
        <ul class="water_ul">
            <li  v-for="item in mylistdata" :style="myrownum">{{item}}</li>
        </ul>
    </div>
</template>

<script>
    module.exports={
        props:{
            rownum:Number,
            listdata:Number
        },
        data(){             //自定义组件的data是个方法
            return{
                myrownum:"width:"+(100/this.rownum)+"%;",
                mylistdata:this.listdata
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

<style >
    .water_ul li{
        border:1px #000 solid;
        list-style:none;
        float:left;
        margin: .1rem;
    }
</style>