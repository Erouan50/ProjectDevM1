$.stream.setup({enableXDR:true});

$(function () {
    $.stream("notifications", {
        type:"http",
        dataType:"json",
        open:function (event, stream) {

        },
        message:function (event) {
            var order = new Order();
            order.toObject(event.data);
            orders[order.id]=order;
            $("#order").prepend('<input type="button" id="c'+order.id+'" class="button-order btn btn-large btn-primary" style="margin:5px;display:none;" value="Commande n°'+order.id+'"/>');
            $("#c"+order.id).show('drop',{direction:"up"},1000);
        },
        error:function () {

        },
        close:function () {

        }
    });

});

function Order()
{
    this.id;
    this.status;
    this.tableId;
    this.creationDate;
    this.menus=new Array();

    this.toObject = function(json)
    {
        this.id = json['id'];
        this.status=json['status'];
        this.tableId=json['tableId'];
        this.creationDate=json['creationDate'];
        for(var menuJson in json['menus']){
            var menu = new Menu();
            menu.ToObject(json['menus'][menuJson]);
            this.menus.push(menu);
        };

    };

    this.toJson = function()
    {
        return JSON.stringify(this);
    }
}

function Menu()
{
    this.id;
    this.name;
    this.description;

    this.ToObject = function(json)
    {
        this.id = json["id"];
        this.name = json["name"];
        this.description = json["description"];
    };
}