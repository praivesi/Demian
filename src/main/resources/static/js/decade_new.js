$(document).ready(function(){
    $('.desire-container').css('margin-top', $('.job-nav-container').height() + 'px');
    $('.desire-container').height($('.desire-container').height() - $('.job-nav-container').height());

    $('#decade-left-arrow').on('click', function(){
        var uriProtocol = window.location.protocol + '//' + window.location.host;
        var uri = uriProtocol + "/schedule/decade_new/set_start_year/" + (startYear - 10);

        window.location.replace(uri);
    });

    $('#decade-right-arrow').on('click', function(){
        var uriProtocol = window.location.protocol + '//' + window.location.host;
        var uri = uriProtocol + "/schedule/decade_new/set_start_year/" + (startYear + 10);

        window.location.replace(uri);
    });
});