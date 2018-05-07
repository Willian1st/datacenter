$(function () {
    $(document).off('click.bs.dropdown.data-api');
    // 鼠标经过导航栏
    $(".dropdown").mouseover(function () {
        $(this).addClass("open");
    });

    $(".dropdown").mouseleave(function () {
        $(this).removeClass("open");
    })
})