$(document).ready(function(){
    console.log("BEFORE edit-btn-container height => " + $('.edit-btn-container').height());
    console.log("job-nav-container height => " + $('.job-nav-container').height());

    jobNavContainerHeight = $('.job-nav-container').height();
    $('.edit-btn-container').height(jobNavContainerHeight);

    console.log("AFTER edit-btn-container height => " + $('.edit-btn-container').height());
    console.log("job-nav-container height => " + $('.job-nav-container').height());

    $('#decade-left-arrow').on('click', function(){
        var uriProtocol = window.location.protocol + '//' + window.location.host;
        var uri = uriProtocol + "/schedule/decade_new/" + (startYear - 10);

        window.location.replace(uri);
    });

    $('#decade-right-arrow').on('click', function(){
        var uriProtocol = window.location.protocol + '//' + window.location.host;
        var uri = uriProtocol + "/schedule/decade_new/" + (startYear + 10);

        window.location.replace(uri);
    });

    $('#desire-add-btn').on('click', function(){
        window.location.replace(window.location.protocol + '//' + window.location.host + '/schedule/desire/desireForm');
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

    $('.job-add-btn').on('click', function(){
        console.log("on job-add-btn click value => " + $(this).val());

        window.location.replace(window.location.protocol + '//' + window.location.host +
            '/schedule/decade_new/jobForm?desireId=' + $(this).val());
    });
});

