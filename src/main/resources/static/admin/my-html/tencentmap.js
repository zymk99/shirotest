var _mapinit=function(){
    var map = new AMap.Map('container', {
        mapStyle: 'amap://styles/1de318cbb8d12c02303a22c550b9ccc9',
        viewMode: '3D',
        resizeEnable: true,
        center: [116.397428, 39.90923],
        zoom: 12,
    });

    var layer = new Loca.HexagonLayer({
        map: map,
        fitView: true,
        eventSupport: true
    });
    var data=[
        {"lnglat":[116.397428, 39.90923],"count":200},
        {"lnglat":[116.397438, 39.90924],"count":200}
    ];
    layer.setData(data, {
        type: 'json',
        lnglat:"lnglat",
        value: 'count'
    });

    layer.setOptions({
        unit: 'meter',
        mode: 'sum',
        style: {
            radius: 2,
            height: 20
        }
    }).render();
    layer.on("mousemove",function(arg){
        debugger
    })
}

var nzzmap=new Vue({
    el:'#_container',
    data:{},
    created:function(){
        setTimeout(_mapinit,'500');
    }
});
