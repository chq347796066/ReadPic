var currentIndex=0





function nextPic(){
    var image=document.getElementById("img_pic")
    if(currentIndex<size-1){
        currentIndex=currentIndex+1
        image.src="/showpic?currentIndex="+currentIndex
    }else {
        alert("最后一张")
    }
}
function onPic(){
    var image=document.getElementById("img_pic")
    if(currentIndex>0){
        currentIndex=currentIndex-1
        image.src="/showpic?currentIndex="+currentIndex
    }else {
        alert("第一张")
    }
}