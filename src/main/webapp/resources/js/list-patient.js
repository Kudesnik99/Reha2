$(document).ready(function(){
    console.log("hello")
});

$(document).on('click', 'a.discharge-link', function (e) {
    e.preventDefault();
    $('#myModal').modal('show');
    var link = $(this).attr('discharge-patient-id');
    console.log("link: " + link);
    $("#editTreatmentBtn").attr('onclick',"window.location.href='../treatment/list?patientId=" + link + "'");
    $("#dischargeBtn").attr('onclick',"window.location.href='../patient/dischargeForced?patientId=" + link + "'");
});