//yyyy-MM-dd HH:mm:ss
function getNowFormatDate1() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + date.getHours() + seperator2 + date.getMinutes()
            + seperator2 + date.getSeconds();
    return currentdate;
}

//yyyyMMddHHmmss
function getNowFormatDate2() {
    var date = new Date();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var min = ""+date.getMinutes();
    if(date.getMinutes()>=0 && date.getMinutes()<=9){
        min = "0"+date.getMinutes();
    }
    var currentdate = date.getFullYear() + month + strDate
            + date.getHours() + min
            + date.getSeconds();
    return currentdate;
}