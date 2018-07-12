function refresh(){
    $.ajax({
        type:'POST',
        url:getPath()+'/createAllIndex',
        async:false,    // 同步请求，将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
        success:function(data){
            alert("生成索引成功！");
        }
    });
}