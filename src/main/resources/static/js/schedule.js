window.onload = function() {
    $("#left-nav-header").height($("#time-navigator-row").height());

    for (var i = 0; i < mainObjects.length; i++) {
        $("#left-nav-row-" + mainObjects[i].id).height($("#content-row-" + mainObjects[i].id).height());
    }
}

$(document).ready(function() {
    $('#sidebarCollapse').on('click', function() {
        $('#sidebar').toggleClass('active');
    });

        $('#controlPanelCollapse').on('click', function() {
            $('#control-panel').toggleClass('active');
        });
});

$('.decades-of-desire-row').on('click', function(){
    clickedDesireId = $(this).attr('id').substring(12);
    clickedDesire = mainObjects[clickedDesireId - 1];
    clickedDecadeJob = mainObjects[clickedDesireId - 1].decadeJobs[clickedDecadeJobId - 1];
})

$('.decade-row').on('click', function(){
    clickedDecadeJobId = $(this).attr('id').substring(7);
})