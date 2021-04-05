$(document).ready(function(){
//    $('#decade-left-arrow').on('click', function(){
//        console.log("aaa");
//        $.get("schedule/decade_new", {startDate: 2010});
//    });

    $('#decade-left-arrow').on('click', function(){
        var uri = "decade_new/set_start_year/" + 2010;
        $.get(uri, function(data){
            console.log(data);
            $(document.documentElement).html(data);
        });
    });
});

