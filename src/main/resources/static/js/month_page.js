$(document).ready(function(){
    jobNavContainerHeight = $('.job-nav-container').height();
    $('.edit-btn-container').height(jobNavContainerHeight);

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
            var uri = uriProtocol + "/months/page/" + (startYear - 1);

            window.location.replace(uri);
        });

    $('.month-right-arrow').on('click', function(){
        var uriProtocol = window.location.protocol + '//' + window.location.host;
        var uri = uriProtocol + "/months/page/" + (startYear + 1);

        window.location.replace(uri);
    });

    $('.month-add-btn').on('click', function(){
        window.location.replace(window.location.protocol + '//' + window.location.host +
            '/months/form?desireId=' + $(this).val());
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

