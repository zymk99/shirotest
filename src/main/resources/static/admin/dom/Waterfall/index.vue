<!--       自定义瀑布流     -->
<template>
    <div >
        <ul class="water_ul">
            <li  v-for="item in mylistdata" :style="itemwidth" >{{item.height}}</li>
        </ul>
    </div>
</template>

<script>
    module.exports={
        props:{
            rownum:Number,
            listdata:Array
        },
        data(){             //自定义组件的data是个方法
            return{
                spacing:15,        //间距
                scrollDlt:10,     //滚动条事件触发间隔   px
                lastScroll:0,       //上一次滚动条位置
                lastRow:[],     //上一行数据的位置
                itemwidth:"width:"+(100/this.rownum-2)+"%;",
                itemwidthpx:0,                  //实际宽度
                myrownum:this.rownum,
                mylistdata:this.listdata,
                addnumber:this.listdata.length,
                addDataFlag:false
            }
        },
        created(){
        },
        mounted(){                    //组件装配后
            this.AdditionalData();
            this.InitData();
            this._data.addDataFlag=true;
            this.setItemHeight();
            window.onscroll=this.TouchBottom;

        },
        methods:{
            //初始化数据
            InitData(){
                let list=[];
                for(let i=0;i<this.rownum;i++){
                    list.push([10,i]);
                }
                this._data.lastRow=list;
            },
            //设置刚加入数据的位置
            setItemHeight(){
                if(this.$el.getElementsByTagName("ul") ){
                    let lastrow=this._data.lastRow;
                    let mydata=this._data.mylistdata
                    let number=this._data.addnumber;  //刚添加进来的数据数
                    let dataIndex=mydata.length-number;     //向mydata里塞入el的起始位置
                    let Items=this.$el.getElementsByTagName("ul")[0].children;
                    if(Items.length>0 && Items.length>=number){
                        if(this._data.itemwidthpx==0){
                            this._data.itemwidthpx=Items[0].clientWidth;
                        }
                        let widthpx=this._data.itemwidthpx
                        //debugger
                        let c=0;
                        for(let index=Items.length-number;index<Items.length;index++){
                            let pos=lastrow.shift();
                            Items[index].style.height=mydata[index].height+"px";
                            Items[index].style.top=pos[0]+this._data.spacing+"px";
                            Items[index].style.left=(widthpx+this._data.spacing)*pos[1]+"px";
                            //记录
                            lastrow.push([parseInt(Items[index].style.height)+parseInt(Items[index].style.top),pos[1]]);
                            lastrow.sort((a,b)=>{return a[0]-b[0];});
                            mydata[dataIndex+(c++)].el=Items[index];
                        }
                        //修改ul高度
                        if(this._data.addDataFlag){
                            this.$el.getElementsByTagName("ul")[0].style.height=lastrow[lastrow.length-1][0]+"px";
                        }
                    }
                    this._data.addnumber=this._data.myrownum;
                    this._data.addDataFlag=false;
                }
            },
            add(){
                for(let i=0;i<this._data.myrownum;i++){
                    let h=parseInt(Math.random()*150)+250;
                    this._data.mylistdata.push({height:h});
                }
                this._data.addDataFlag=true;
                window.setTimeout(this.setItemHeight,100);
            },
            //是否触底
            TouchBottom(){
                let scrollTop = document.documentElement.scrollTop||document.body.scrollTop;
                //变量windowHeight是可视区的高度
                let windowHeight = document.documentElement.clientHeight || document.body.clientHeight;
                //变量scrollHeight是滚动条的总高度
                let scrollHeight = document.documentElement.scrollHeight||document.body.scrollHeight;
                let top=scrollTop+windowHeight;
                //滚动条到底部的条件
                if(top==scrollHeight){
                    this.add();
                }else{
                    if(Math.abs(top-this._data.lastScroll)>this._data.scrollDlt){
                        /************懒加载*************/
                            let data=this._data.mylistdata;
                            //计算展示出来的个数
                            let num=this._data.myrownum*4;
                            //计算开始展示的Index
                            //let index= parseInt((windowHeight*windowHeight/scrollHeight+scrollTop)/windowHeight*this._data.mylistdata.length)-num;
                            let index=parseInt( (windowHeight+scrollTop)/scrollHeight*this._data.mylistdata.length )-num+this._data.myrownum;
                            index=(index>=0? index : 0 );
                            let ul=this.$el.getElementsByTagName("ul")[0];
                            if(ul){
                                ul.innerHTML="";
                                for(let i=0;i<num && i+index<data.length;i++){
                                    ul.appendChild(data[i+index].el);
                                }
                            }
                        /*******************************/
                        this._data.lastScroll=top;
                    }
                }
            },
            //追加数据
            AdditionalData(){
            }
        }
    }
</script>

<style >
    .water_ul{
        position: absolute;
        width: 100%;
    }
    .water_ul li{
        border:1px #000 solid;
        list-style:none;
        float:left;
        margin: .1rem;
        position: absolute;
    }
</style>