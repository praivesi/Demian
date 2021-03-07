window.onload = function() {
    $("#left-nav-header").height($("#time-navigator-row").height());

    for(const [key,value] of Object.entries(schedules)){
        $("#left-nav-row-" + key).height($("#content-row-" + key).height());
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

    $('.desire-dropdown-item').on('click', function(){
        var clickedDesireDropItemText = $(this).text();

        for (var i = 0; i < desires.length; i++)
        {
            console.log("desires loop");
            if(desires[i].title === clickedDesireDropItemText)
            {
                console.log("title in");
                console.log("desires[" + i + "] => " + desires[i]);
                console.log("Before addedDecadeJob.desire => " + addedDecadeJob.desire);
                console.log("Before curDesire => " + curDesire);
                addedDecadeJob.desire = desires[i].id;
                curDesire = desires[i];
                console.log("After addedDecadeJob.desire => " + addedDecadeJob.desire);
                console.log("After curDesire => " + curDesire);

                break;
            }
        }
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