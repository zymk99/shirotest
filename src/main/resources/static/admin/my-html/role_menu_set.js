var _transfer=new Vue({
    el:"._transfer",
    data:{
        data:[{
            label:"123",
            key:'1',
            data:123
        },{
            label:"345",
            key:'2',
            data:123
        }],
        value:['2']
    },
    methods:{
            filterMethod:function(query, item) {
                return item.label;
            }
    }
})







