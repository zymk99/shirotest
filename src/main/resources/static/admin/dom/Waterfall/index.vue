<!--       自定义瀑布流     -->
<template>
    <div >

        <ul class="water_ul" @click="ul_click">
            <li  v-for="(item,index) in mylistdata" :style="itemwidth" itemtype="item" :itemindex="index">
                <img class="ul_img" :src="item.src"/>
                <span class="font_bolder">{{item.title}}</span>
            </li>
        </ul>
    </div>
</template>

<script>
    module.exports={
        props:{
            rownum:Number,
            listdata:Array,
            adddatafunc:Function
        },
        data(){             //自定义组件的data是个方法
            return{
                pageN:1,             //页数
                spacing:15,        //间距
                scrollDlt:10,     //滚动条事件触发间隔   px
                lastScroll:0,       //上一次滚动条位置
                lastRow:[],     //上一行数据的位置
                itemwidth:"width:"+(100/this.rownum-2)+"%;",
                itemwidthpx:0,                  //实际宽度
                myrownum:this.rownum,
                mylistdata:this.listdata,
                addnumber:this.listdata.length,     //上一次加入数据的数目
                addDataFlag:false,
                itemAddLock:false              //保证每次添加后都能设置样式  避免连续添加漏掉样式
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
            ul_click(item){
                let c_1=item.path[0];
                let c_2=item.path[1];
                if(c_1.getAttribute("itemtype")=="item" || c_2.getAttribute("itemtype")=="item" ){
                    this.$emit('waterfall_itemclick',c_1.getAttribute("itemtype")=="item" ? c_1:c_2);
                }
            },
            //初始化数据
            InitData(){
                let list=[];
                for(let i=0;i<this.rownum;i++){
                    list.push([18,i]);
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
                this._data.itemAddLock=false;
            },
            add(){
                // for(let i=0;i<this._data.myrownum;i++){
                //     let h=parseInt(Math.random()*150)+250;
                //     this._data.mylistdata.push({height:h});
                // }
                this._data.pageN++;
                let list= this.adddatafunc(this._data.pageN,this._data.myrownum*3);
                if(list){
                    this._data.addnumber=list.length;
                    for(let i=0;i<list.length;i++){
                        this._data.mylistdata.push(list[i]);
                    }
                    this._data.addDataFlag=true;
                    window.setTimeout(this.setItemHeight,100);
                }
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
                if( (top+30)>=scrollHeight){
                    if(!this._data.itemAddLock)
                    {
                        this._data.itemAddLock=true;
                        this.add();
                    }
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
        text-align: center;
        border:1px #000 solid;
        list-style:none;
        float:left;
        margin: .1rem;
        position: absolute;
    }
    .font_bolder{
        font-family: serif;
        font-weight: bolder;
    }
    .water_ul .ul_img{
        height: 80%;
        width: 95%;
        margin: 2%;
    }
</style>