window.onload = function() {
    $("#left-nav-header").height($("#time-navigator-row").height());
//
//    for (var i = 0; i < mainObjects.length; i++) {
//        $("#left-nav-row-" + mainObjects[i].id).height($("#content-row-" + mainObjects[i].id).height());
//    }

    console.log("scheduleMap => " + scheduleMap);

    for (var schedule in scheduleMap)
    {
        $("#left-nav-row-" + schedule.key.id).height($("#content-row-" + schedule.key.id).height());
    }
}

$(document).ready(function() {
    $('#sidebarCollapse').on('click', function() {
        $('#sidebar').toggleClass('active');
    });

    $('#controlPanelCollapse').on('click', function() {
        $('#decade-job-control-panel').toggleClass('active');
    });

    $('#desireControlPanelCollapse').on('click', function(){
        $('#desire-control-panel').toggleClass('active');
    });
});

$('.decades-of-desire-row').on('click', function(){
    if($('#desire-control-panel').hasClass('active'))
    {
        $('#desire-control-panel').removeClass('active');
    }

    if($('#decade-job-control-panel').hasClass('active') == false)
    {
        $('#decade-job-control-panel').addClass('active');
    }


    clickedDesireId = $(this).attr('id').substring(12);
    clickedDesire = mainObjects[clickedDesireId - 1];
    clickedDecadeJob = mainObjects[clickedDesireId - 1].decadeJobs[clickedDecadeJobId - 1];

    $('#control-panel-job-id').val(clickedDecadeJob.id);
    $('#control-panel-job-title').val(clickedDecadeJob.title);
    $('#control-panel-job-content').text(clickedDecadeJob.content);

    console.log('decades-of-desire-row clicked => ' + clickedDesire);
    console.log('decades-of-desire-row clicked => ' + clickedDecadeJob);
})

$('.decade-row').on('click', function(){
    clickedDecadeJobId = $(this).attr('id').substring(7);
})

$('.left-desire-row').on('click', function(){
    if($('#decade-job-control-panel').hasClass('active'))
    {
        $('#decade-job-control-panel').removeClass('active');
    }

    if($('#desire-control-panel').hasClass('active') == false)
    {
        $('#desire-control-panel').addClass('active');
    }

    clickedDesireId = $(this).attr('id').substring(13);
    clickedDesire = mainObjects[clickedDesireId - 1];

    $('#control-panel-desire-id').val(clickedDesire.id);
    $('#control-panel-desire-title').val(clickedDesire.title);
    $('#control-panel-desire-content').text(clickedDesire.content);

    console.log('left-desire-row clicked => ' + clickedDesire);
    console.log('left-desire-row clicked => ' + clickedDecadeJob);
})