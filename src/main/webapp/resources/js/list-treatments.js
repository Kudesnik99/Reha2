$(document).ready(function(){
    console.log("hello")
});

$(document).on('click', 'input.discharge-link', function (e) {
    $('#myModal').modal('show');
    var link = $(this).attr('discharge-patient-id');
    console.log("link: " + link);
    $("#dischargeBtn").attr('onclick',"window.location.href='../patient/dischargeForced?patientId=" + link + "'");
});