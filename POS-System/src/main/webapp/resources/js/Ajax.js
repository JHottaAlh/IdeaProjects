/**
 * 
 */
$(function() {
	
// submitボタンをクリック
	$("#send").click(function(){
		var postData = {};
		postData.search = $("input[name=code]").val();
		var postUrl = "goodsScan";			//検索先のURL
		$.ajax({
			url: postUrl,			//URLをここで指定
			type: "POST",			//送信方法
			dataType: "json",		//json形式でデータを受け取る
			data: postData,			//送信されてきた商品コード
			success: function(json){
				if(json["status"] == "error"){
					alert(json["message"]);
					return false;
				}
				for(var j in json){
					setjsondata(j, json[j]);
				}
			}
		});
	});

	$("#send").click(function(){
		alert("hi!");
	});

});