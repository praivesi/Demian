$(document).ready(function(){
    $('.desire-add-btn').on('click', function(){
        window.location.replace(window.location.protocol + '//' + window.location.host + '/desires/form/0');
    });

    $('.desire-delete-btn').on('click', function(){
        var rootURL = window.location.protocol + '//' + window.location.host;
        var deleteURL =  rootURL + '/api/schedules/desire/' + $(this).val();
        console.log('Delete URL : ' + deleteURL);

        $.ajax({
            url: deleteURL,
            type: 'DELETE',
            success: function(result){
                console.log('Delete desire succeed');
                window.location.reload();
            }
        });
    });

    $('.decade-left-arrow').on('click', function(){
        var uriProtocol = window.location.protocol + '//' + window.location.host;
        var uri = uriProtocol + "/decades/page/" + (startDecade - 10);

        window.location.replace(uri);
    });

    $('.decade-right-arrow').on('click', function(){
        var uriProtocol = window.location.protocol + '//' + window.location.host;
        var uri = uriProtocol + "/decades/page/" + (startDecade + 10);

        window.location.replace(uri);
    });

    $('#add-decade-growth-dialog').dialog({
        autoOpen: false,
        closeOnEscape: false
    });

    $('.decade-add-btn').on('click', function(){
        $('#add-decade-growth-dialog').load(window.location.protocol + '//' + window.location.host + '/decades/form?desireId=' + $(this).val(), function(){
            $('#add-decade-growth-dialog').dialog("open", "resize", "auto");
        });
    });

    $('.decade-delete-btn').on('click', function(){
        var rootURL = window.location.protocol + '//' + window.location.host;
        var deleteURL =  rootURL + '/api/schedules/decade_job/' + $(this).val();
        console.log('Delete URL : ' + deleteURL);

        $.ajax({
            url: deleteURL,
            type: 'DELETE',
            success: function(result){
                console.log('Delete decade job succeed');
                window.location.reload();
            }
        });
    });
});



