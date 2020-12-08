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
            inde:String
        },
        data(){
            return {
                num:parseInt(this.inde),
                mypiclist:[],
                objpiclist:[],
                index:0
            }
        },
        created(){
            let v={};
            v.index=this._data.num;
            let that =this;
            Zafkiel.request("/filepic/getPicByIndex",v,"post",function(val){
                for(i in val){
                    let bytes = window.atob(val[i]);
                    let ab = new ArrayBuffer(bytes.length);
                    let ia = new Uint8Array(ab);
                    for (let i = 0; i < bytes.length; i++) {
                        ia[i] = bytes.charCodeAt(i);
                    }
                    val[i]=URL.createObjectURL(new Blob([ab]));
                    that._data.objpiclist.push(val[i]);
                }
                window.setTimeout(that.pushData,500);
            },null,true,true);
        },
        methods:{
            pushData(){
                let list=this._data.objpiclist
                let max=0,i=this._data.index;
                while(i<list.length && max<10){
                    this._data.mypiclist.push(list[i++])
                    max++;
                }
                if(i<list.length){
                    this._data.index=i;
                    window.setTimeout(this.pushData,500);
                }
            }
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
        filter: brightness(1);
    }
</style>