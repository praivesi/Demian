$(document).ready(function(){
    console.log("BEFORE edit-btn-container height => " + $('.edit-btn-container').height());
    console.log("job-nav-container height => " + $('.job-nav-container').height());

    jobNavContainerHeight = $('.job-nav-container').height();
    $('.edit-btn-container').height(jobNavContainerHeight);

    console.log("AFTER edit-btn-container height => " + $('.edit-btn-container').height());
    console.log("job-nav-container height => " + $('.job-nav-container').height());

    $('.desire-add-btn').on('click', function(){
        window.location.replace(window.location.protocol + '//' + window.location.host + '/desires/form');
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
            var uri = uriProtocol + "/decades/page/" + (startYear - 10);

            window.location.replace(uri);
        });

    $('.decade-right-arrow').on('click', function(){
        var uriProtocol = window.location.protocol + '//' + window.location.host;
        var uri = uriProtocol + "/decades/page/" + (startYear + 10);

        window.location.replace(uri);
    });

    $('.decade-add-btn').on('click', function(){
        window.location.replace(window.location.protocol + '//' + window.location.host +
            '/decades/form?desireId=' + $(this).val());
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

    $('.year-left-arrow').on('click', function(){
                var uriProtocol = window.location.protocol + '//' + window.location.host;
                var uri = uriProtocol + "/years/page/" + (startYear - 1);

                window.location.replace(uri);
            });

        $('.year-right-arrow').on('click', function(){
            var uriProtocol = window.location.protocol + '//' + window.location.host;
            var uri = uriProtocol + "/years/page/" + (startYear + 1);

            window.location.replace(uri);
        });

        $('.year-add-btn').on('click', function(){
            window.location.replace(window.location.protocol + '//' + window.location.host +
                '/years/form?desireId=' + $(this).val());
        });

        $('.year-delete-btn').on('click', function(){
            var rootURL = window.location.protocol + '//' + window.location.host;
            var deleteURL =  rootURL + '/api/schedules/year_job/' + $(this).val();
            console.log('Delete URL : ' + deleteURL);

            $.ajax({
                url: deleteURL,
                type: 'DELETE',
                success: function(result){
                    console.log('Delete year job succeed');
                    window.location.reload();
                }
            });
        });
});

