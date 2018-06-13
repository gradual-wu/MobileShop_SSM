$(document).ready(function () {
    $("#image > li").hover(function () {
        var imgSrc = $(this).find("img").attr("src");
        $(".image-main").attr("src",imgSrc);
        $(this).find("img").css("border-color","black").parent().siblings().find("img").css("border-color","transparent");
    });
});