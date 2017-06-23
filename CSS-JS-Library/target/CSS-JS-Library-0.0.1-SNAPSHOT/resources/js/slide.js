/**
 * ページスライド
 */

var $anchors = $(" a[href^='#']");
var $doc     = $($.browser.safari ? 'body' : 'html');
var speed    = 600;
     
$anchors.each(function(){
  var $anchor   = $(this);
  var anchorID  = $anchor.attr("href");
  var $target   = $(anchorID);
       
  $anchor.click(function(e){
    var targetPositionTop = $target.offset().top;
     
    $doc.stop().animate({
      scrollTop: targetPositionTop
    },
    1000,
    'swing'
    );
     
    return false;
  });
});