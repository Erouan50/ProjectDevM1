var id=0;

$(document).ready(function(){
	/*$("#webService").click(function(){
		$("#order").prepend('<input type="button" id="c'+id+'" class="button-order btn btn-large btn-primary" style="margin:5px;display:none;" value="Commande n°'+id+'"/>');
		$("#c"+id).show('drop',{direction:"up"},1000);
		id++;
	});*/
	
	$(".button-order").live("click",function(){
		$(this).effect("transfer",{to:$("#infoOrder")},500,function(){
			var message = "Info Commande : "+$(this).attr("id");
			var footer = '<div><input type="button" value="Validate the order." class="validate btn btn-success" id="'+$(this).attr("id")+'" /></div>';
			var content ='<div id="'+$(this).attr("id")+'">'+message+' <br/> '+footer+' <br/></div>';
			$("#infoOrder").html(content);
		});		
		$(this).removeClass("btn-primary").addClass("btn-warning");
	});
	
	$(".validate").live("click",function(){
	
		$("#"+$(this).attr('id')).hide('drop',{direction: "down"}, 500,function(){
			$("#"+$(this).attr('id')).remove();
			$("#"+$(this).attr('id')).remove();
			$("#"+$(this).attr('id')).remove();
		});

	});

    var id = 102;
    $("#webService").click(function(){
        $.get("http://localhost:8080/restaurant-web-app-0.1/menus/"+id,
            function(data){
                alert("Data Loaded: " +   data);
            }
        );
    })


});