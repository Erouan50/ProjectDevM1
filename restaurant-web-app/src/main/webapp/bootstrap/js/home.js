var id=0;
var orders = Array();


$(document).ready(function(){
	/*$("#webService").click(function(){
		$("#order").prepend('<input type="button" id="c'+id+'" class="button-order btn btn-large btn-primary" style="margin:5px;display:none;" value="Commande n°'+id+'"/>');
		$("#c"+id).show('drop',{direction:"up"},1000);
		id++;
	});*/
	
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
			var content ='<div id="'+$(this).attr("id")+'">'+message+' <br/> '+footer+' <br/></div>';
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

			$("#"+$(this).attr('id')).remove();
			$("#"+$(this).attr('id')).remove();

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
        error: function(data)
        {
            alert("error"+data);
        },
        success:function(data)
        {
            alert("success"+data);
        }


    });
}