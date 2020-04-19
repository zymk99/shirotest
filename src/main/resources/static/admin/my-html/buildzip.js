(function () {

    // document.write("<script src='/admin/javascript/FileSaver.min.js'></script>");
    // document.write("<script src='/admin/javascript/jszip.min.js'></script>");

    let leng=0;
    let url='';
    let downloadList=[];
    let zip = new JSZip();
    debugger
    for(let i=1;i<=16;i++)
    {
        downloadList.push(String(i)+".png");
    }
    leng=0;

    function image_downloaded(imgData) {
        zip.file(downloadList[leng], imgData, {base64: true});
        if (++leng >= downloadList.length) {
            //var content = zip.generate({type:"blob"});
            //saveAs(content, galleryname_to_download+".zip");
            zip.generateAsync({type:"blob"}).then(function(content) {
                saveAs(content, +"zzz.zip");
            });
        } else {
            ajax_download_blob();
        }
    }
    function ajax_download_blob() {
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                image_downloaded(this.response);
            }
        }
        xhr.open('GET', url+downloadList[leng]);
        //xhr.setRequestHeader("Access-Control-Allow-Origin","");
        xhr.responseType = 'arraybuffer';
        xhr.send();
    }
    ajax_download_blob();
})(window)