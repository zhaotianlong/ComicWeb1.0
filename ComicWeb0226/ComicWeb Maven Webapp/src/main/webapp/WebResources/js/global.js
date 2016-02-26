/*
*Athor:lolo
*fuction:test
*createDate:16/1/5
*/
function t(str){
	alert("successful tt");
}
(function($){
	$.fn.extend({
		myPlugName:function(){
			$(this).ready(function(){
				alert("傻叉");

			});    
		}
	});

	$.fn.tt=function(){
		alert("successful tt");
	}

	$.fn.Test=function(){
		$(this).click(function(){
			alert("ssss");
			s();
		});
	}
	function s(){
		alert("lot");
	}    
})(jQuery);


/*
*Author:lolo
*Function:PlayCrtcle
* CreateDate:16/1/5
*/
	var t = n = 0, count;
	function showAuto()
	{
		n = n >=(count - 1) ? 0 : ++n;
		$("#banner li").eq(n).trigger('click');
	}
(function($){

	$.fn.PlayCrtcle=function(){
		$(this).ready(function(){
			alert("s-s");
			str="#"+"banner_list";
			count=$(str+" a").length;
			$("#banner_list a:not(:first-child)").hide();
		//获取alt值
		$("#banner_info").html($("#banner_list a:first-child").find("img").attr('alt'));
		$("#banner_info").click(function(){window.open($("#banner_list a:first-child").attr('href'), "_blank")});
		
		$("#banner li").click(function() {
			var i = $(this).text() - 1;//获取Li元素内的值，即1，2，3，4
			n = i;
			alert("you");
			if (i >= count) return;
			$("#banner_info").html($("#banner_list a").eq(i).find("img").attr('alt'));
			$("#banner_info").unbind().click(function(){window.open($("#banner_list a").eq(i).attr('href'), "_blank")})
			$("#banner_list a").filter(":visible").fadeOut(500).parent().children().eq(i).fadeIn(1000);
			
			document.getElementById("banner").style.background="";
			$(this).toggleClass("on");
			$(this).siblings().removeAttr("class");

		});
		t = setInterval("showAuto()", 4000);
		$("#banner").hover(function(){clearInterval(t)}, function(){t = setInterval("showAuto()", 4000);});
		
	});	
}
})(jQuery);