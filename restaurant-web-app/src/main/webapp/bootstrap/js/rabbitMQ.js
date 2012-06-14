$.stream.setup({enableXDR:true});

$(function () {
    $.stream("notifications", {
        type:"http",
        dataType:"json",
        open:function (event, stream) {

        },
        message:function (event) {
            alert(event)
        },
        error:function () {

        },
        close:function () {

        }
    });

});