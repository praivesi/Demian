$(document).ready(function(){
    console.log("BEFORE edit-btn-container height => " + $('.edit-btn-container').height());
    console.log("job-nav-container height => " + $('.job-nav-container').height());

    jobNavContainerHeight = $('.job-nav-container').height();
    $('.edit-btn-container').height(jobNavContainerHeight);

//    $('.edit-btn-container').height($('.job-nav-container').height() + 1);
//    $('.edit-btn-container').css('height', $('job-nav-container').height() + 'px !important');
    console.log("AFTER edit-btn-container height => " + $('.edit-btn-container').height());
    console.log("job-nav-container height => " + $('.job-nav-container').height());

//    console.log("BEFORE edit-btn-container-id height => " + $('#edit-btn-container-id').height());
//    console.log("job-nav-container height => " + $('.job-nav-container').height());
//    $('#edit-btn-container-id').height($('.job-nav-container').height());
//    console.log("AFTER edit-btn-container-id height => " + $('#edit-btn-container-id').height());
//    console.log("job-nav-container height => " + $('.job-nav-container').height());

//    $('.desire-container').css('margin-top', $('.job-nav-container').height() + 'px');
//    $('.desire-container').height($('.desire-container').height() - $('.job-nav-container').height());

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
        window.location.replace(window.location.protocol + '//' + window.location.host + '/schedule/decade_new/desireForm');
    });

    $('#job-add-btn').on('click', function(){
        window.location.replace(window.location.protocol + '//' + window.location.host + '/schedule/decade_new/jobForm');
    });
});