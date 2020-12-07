<!--       图片查看     -->
<template>
    <div>
        <ul class="picsee_ul">
            <li v-for="(item,index) in mypiclist" :ind="index">
                <img :src="item" />
            </li>
        </ul>
    </div>
</template>
<script>
    module.exports={
        props:{
            piclist:Array
        },
        data(){
            return {
                mypiclist:this.piclist,
                flag:1
            }
        },
        created(){

        }
    }
</script>

<style>
    .picsee_ul{
        width: 100%;
    }
    .picsee_ul li{
        width: 95%;
        text-align: center;
        list-style: none;
        padding-bottom: 8px;
    }
    .picsee_ul img{
        width: 40%;
    }
</style>