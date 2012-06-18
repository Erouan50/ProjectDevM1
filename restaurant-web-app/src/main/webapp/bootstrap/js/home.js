var id=0;
var orders = Array();


$(document).ready(function(){
	
	$(".button-order").live("click",function(){
		$(this).effect("transfer",{to:$("#infoOrder")},500,function(){

            var order = orders[parseInt($(this).attr("id").substr(1))];
            var message;
            message = "<b><u>Order n°"+order.id+" table n°"+order.tableId+"</b></u><br/><br/><u>Details :</u><br/><br/>";
            for(var menu in order.menus)
            {
                message+="- "+order.menus[menu].name+"<br/><br/>";
            }

			var footer = '<div><input type="button" value="Validate this order." class="validate btn btn-success" id="'+$(this).attr("id")+'" /></div>';
			var content ='<div id="i'+$(this).attr("id")+'">'+message+' <br/> '+footer+' <br/></div>';
			$("#infoOrder").html(content);

            order.status = 1;
            updateOrder(order);
		});		
		$(this).removeClass("btn-primary").addClass("btn-warning");
	});
	
	$(".validate").live("click",function(){
		$("#"+$(this).attr('id')).hide('drop',{direction: "down"}, 500,function(){
            var order = orders[parseInt($(this).attr("id").substr(1))];

            order.status = 2;
            updateOrder(order);
		});

	});

});



function updateOrder(order) {
    $.ajax({
        type: 'PUT',
        contentType: 'application/json',
        url: 'resources/orders/'+order.id,
        dataType: "json",
        data: order.toJson(),
        success:function(data)
        {
            if(order.status==2)
            {
                $("#c"+order.id).remove();
                $("#ic"+order.id).remove();
            }
        }


    });
}