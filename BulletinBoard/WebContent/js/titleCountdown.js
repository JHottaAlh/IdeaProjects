/**
 * 数字のカウントダウン
 */
$(function(){
    var countMax = 50;
    $('input').bind('keydown keyup keypress change',function(){
        var thisValueLength = $(this).val().length;
        var countDown = (countMax)-(thisValueLength);
        $('.titlecount').html(countDown);
 
        if(countDown < 0){
            $('.titlecount').css({color:'#FF1493',fontWeight:'bold'});
        } else {
            $('.titlecount').css({color:'#ffffff',fontWeight:'normal'});
        }
    });
    $(window).load(function(){
        $('.titlecount').html(countMax);
    });
});