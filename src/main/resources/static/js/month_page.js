$(document).ready(function(){
    $('.desire-add-btn').on('click', function(){
        window.location.replace(window.location.protocol + '//' + window.location.host + '/desires/form/2');
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

    $('.month-left-arrow').on('click', function(){
            var uriProtocol = window.location.protocol + '//' + window.location.host;

            console.log("startYear => " + startYear);
            console.log("startMonth => " + startMonth);

            // sync between Thymeleaf dates format with Java Date class
            startMonth--;

            // apply left move
            if (startMonth == 0){
                startYear--;
                startMonth = 11;
            } else {
                startMonth--;
            }

            console.log("After...");
            console.log("startYear => " + startYear);
            console.log("startMonth => " + startMonth);

            var uri = uriProtocol + "/months/page/" + startYear + "/" + startMonth;

            window.location.replace(uri);
        });

    $('.month-right-arrow').on('click', function(){
        var uriProtocol = window.location.protocol + '//' + window.location.host;

        // sync between Thymeleaf dates format with Java Date class
        startMonth--;

        // apply right move
        if (startMonth == 11){
            startYear++;
            startMonth = 0;
        } else {
            startMonth++;
        }

        var uri = uriProtocol + "/months/page/" + startYear + "/" + startMonth;

        window.location.replace(uri);
    });

//    $('.month-add-btn').on('click', function(){
//        window.location.replace(window.location.protocol + '//' + window.location.host +
//            '/months/form?desireId=' + $(this).val());
//    });

    $('#add-month-growth-dialog').dialog({
        autoOpen: false,
        closeOnEscape: false
    });

    $('.month-add-btn').on('click', function(){
        $('#add-month-growth-dialog').load(window.location.protocol + '//' + window.location.host +
                                                        '/months/form?desireId=' + $(this).val(), function(){
            $('#add-month-growth-dialog').dialog("open", "resize", "auto");
        });
    });

    $('.month-delete-btn').on('click', function(){
        var rootURL = window.location.protocol + '//' + window.location.host;
        var deleteURL =  rootURL + '/api/schedules/month_job/' + $(this).val();
        console.log('Delete URL : ' + deleteURL);

        $.ajax({
            url: deleteURL,
            type: 'DELETE',
            success: function(result){
                console.log('Delete moth job succeed');
                window.location.reload();
            }
        });
    });
});

