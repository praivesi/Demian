window.onload = function() {
    $("#desire-header").height($("#decade-navigator-row").height());

    for (var i = 0; i < desires.length; i++) {
        $("#desire-row-" + desires[i].id).height($("#job-row-" + desires[i].id).height());
    }
}

$(document).ready(function() {
    $('#sidebarCollapse').on('click', function() {
        $('#sidebar').toggleClass('active');
    });
});