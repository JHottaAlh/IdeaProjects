/**
 * 数字のカウントダウン
 */
$(function(){
    var countMax = 500;
    $('textarea').bind('keydown keyup keypress change',function(){
        var thisValueLength = $(this).val().length;
        var countDown = (countMax)-(thisValueLength);
        $('.count').html(countDown);
 
        if(countDown < 0){
            $('.count').css({color:'#FF1493',fontWeight:'bold'});
        } else {
            $('.count').css({color:'#ffffff',fontWeight:'normal'});
        }
    });
    $(window).load(function(){
        $('.count').html(countMax);
    });
});