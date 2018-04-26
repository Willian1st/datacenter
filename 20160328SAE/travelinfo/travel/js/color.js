$(function() {
	$(".color").on("click", function() {
		$("#colorCode").html($(this).html());
		$("#colour").css("background-color", $(this).html());
	});
});
