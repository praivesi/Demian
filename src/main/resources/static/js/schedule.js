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