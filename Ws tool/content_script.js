$(function () {
    window.alert = layer.msg;
    hotkeys('shift+a', function (event, handler) {
        submit('admin02');
    });
    hotkeys('shift+y', function (event) {
        submit('sys');
    });
    hotkeys('shift+s', function (event) {
        submit('sysAdmin');
    });
    hotkeys('fn+s', function (event) {
        submit('sysAdmin', "zq123456");
    });
    hotkeys('shift+m', function (event) {
        submit('mlmkAdmin');
    });
    hotkeys('shift+w', function (event) {
        submit('wfhg');
    });
    hotkeys('shift+b', function (event) {
        submit('bjyyt');
    });
    hotkeys('shift+l', function (event) {
        submit('lpsAdmin');
    });
    hotkeys('shift+z', function (event) {
        submit('lps_zsq_Admin');
    });
    hotkeys('shift+g', function (event) {
        submit('gzrjaqkj');
    });
    hotkeys('fn+esc', function () {
        var button = document.getElementById("logoutHref");
        button.click();
    });
    hotkeys('alt+1', function () {
        location.href = "http://localhost:7070/cas/login";
    });
    hotkeys('alt+2', function () {
        location.href = "http://www.gzaqy.com/cas/login";
    });

    hotkeys('alt+h', function () {
        chrome.extension.sendRequest({
            cmd: "history"
        }, function (response) {
            console.log(response.result);
        });
    });
    hotkeys('shift+tab', function () {
        chrome.extension.sendRequest({
            cmd: "tab"
        }, function (response) {
            console.log(response.result);
        });
    });
    hotkeys('shift+ctrl+alt+s', function () {
        // 打卡-suning
        chrome.extension.sendRequest({
            cmd: "suning"
        }, function (response) {
            return;
            console.log(response.result);
        });
    });

    hotkeys('shift+q', function () {
        // 打卡-guomei
        chrome.extension.sendRequest({
            cmd: "guomei"
        }, function (response) {
            console.log(response.result);
            return;
            setTimeout(function () {
                if ($("#tabRight")) {
                    $("#tabRight").click();
                }
            }, 3000);
        });
    });

    hotkeys('shift+x', function () {
		xuexi();
        chrome.extension.sendRequest({
            cmd: "xuexi"
        });
    });

    hotkeys('ctrl+shift+x', function () {        
        chrome.extension.sendRequest({
            cmd: "xuexi_video_news"
        });
    });

    hotkeys('ctrl+shift+z', function () {
        chrome.extension.sendRequest({
            cmd: "xuexi_jifen"
        });
    });

});
submit = function (userName, passWord) {
    var users = [{
        name: 'sys',
        code: 'longruan++'
    }, {
        name: 'sysAdmin',
        code: 'aqy+123'
    }, {
        name: 'admin02',
        code: 'admin02'
    }, {
        name: 'mlmkAdmin',
        code: '123456'
    }, {
        name: 'wfhg',
        code: '123456'
    }, {
        name: 'gzrjaqkj',
        code: '123456'
    }, {
        name: 'lpsAdmin',
        code: '123456'
    }, {
        name: 'qxnzxrajj',
        code: '123456'
    }, {
        name: 'lps_zsq_Admin',
        code: '123456'
    }, {
        name: 'zyajj',
        code: '123456'
    }, {
        name: 'bjsajj',
        code: '123456'
    }, {
        name: 'sysAdmin_zq',
        code: 'zq123456'
    }, {
        name: 'bjyyt',
        code: '888888'
    }, {
        name: 'bzqmtzajbckm',
        code: '123456'
    }, {
        name: 'xlczajz',
        code: '123456'
    }];
    setTimeout(
        function () {
            var username = $("[name='username']");
            if (username.length == 0) {
                username = $(document.getElementsByClassName('username')[0]);
            }
            if (username.length == 0) {
                username = document.getElementsByTagName('iframe')[0].contentWindow.document
                    .getElementById('username');
            }
            var password = $("[name='password']");
            if (password.length == 0) {
                password = $(document.getElementsByClassName('password')[0]);
            }
            if (password.length == 0) {
                password = document.getElementsByTagName('iframe')[0].contentWindow.document
                    .getElementById('password');
            }
            if (!(username instanceof jQuery)) {
                username = $(username);
            }
            if (!(password instanceof jQuery)) {
                password = $(password);
            }
            username.focus().val('');
            password.focus().val('');
            var form = document.getElementById('fm1')
                || document.getElementById('fm11') || document.getElementById('fm2');
            if (!form) {
                form = document.getElementsByClassName('login-btn')[0];
            }
            if (!form && document.getElementsByTagName('iframe')[0]) {
                form = document.getElementsByTagName('iframe')[0].contentWindow.document
                    .getElementById('fm1');
            }
            username.val(userName.replace("_zq", ""));
            password.val(passWord || (function (userName) {
                var code = '';
                $.each(users, function (i, v) {
                    if (v.name == userName) {
                        code = v.code;
                    }
                });
                return code;
            })(userName));
            if (form) {
                if (form.submit) {
                    form.submit();
                } else {
                    form.click();
                }
                var gointo = document.getElementsByTagName('iframe')[0].contentWindow.document
                    .getElementById('go-success');
                setInterval(
                    function () {
                        if (gointo) {
                            gointo.click();
                        } else {
                            gointo = document
                                .getElementsByTagName('iframe')[0].contentWindow.document
                                .getElementById('go-success');
                        }
                    }, 1000);
            }
        }, 1000);
};
sendMessage = function (keys) {
    // 给background和popup发送数据
    chrome.extension.sendRequest({
        cmd: JSON.stringify(keys)
    }, function (response) {
        return;
        console.log(response.result);
    });
};
// 接收popup发来的消息
chrome.extension.onMessage.addListener(function (request, sender, sendResponse) {
    if (request.message == "logout") {
        var button = document.getElementById("logoutHref");
        if (button) {
            button.click();
            layer.msg("退出");
        }
    } else if (request.message == "clean") {
        var url = location.href;
        if (url) {
            var reg = new RegExp("(^|;)jsessionid=([^#]*)");
            var r = url.substr(1).match(reg);
            if (r != null) {
                var result = ";jsessionid=" + unescape(r[2]);
                history.pushState(null, null, url.replace(result, ""));
            }
        }
    } else if (request.message) {
        submit(request.message);
    }
});

// 获取url中的参数
function getUrlParam(name) {
    var reg = new RegExp("(^|;)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;
}

if (location.href.indexOf("localhost") == -1) {
    // $("body").append("<style>h1,h2,h3,h4,h5,p,body,div,span,label,a,i,form,table,th,tr,td,ul,li,figure,pre,button,header,code,textarea{background-color:#2b2b2b!important;color:#bbbbbb;}</style>");
}
var xuexi = function () {
    if (location.href.indexOf("www.xuexi.cn") != -1) {
        //获取新闻列表
        var section = $("[data-data-id]").siblings("div section");
		
        var listNews = [];
        if (section.length) {
            section.each(function () {
                var title = $(this).find(".text-link-item-title .text-wrap:first");
                if (title && title.length && $.trim(title.text()) != '') {
                    listNews.push(title);
                }
            });
        } else {
            listNews = $(".text-wrap");
        }
        //打开前六个文字新闻
        $.each(listNews, function (i, v) {
            v = $(v);
            if (i < 12) {
                setTimeout(function () {
                    v.click();
                    console.log("打开第" + (i + 1) + "篇文章")
                }, i * 125000);
            }
        });
    }
};

$(function () {
    if (location.href.indexOf("lgpage/detail/index.html?id=") != -1) {
        var documentHeight = $(document).height();
        var windowHeight = window.innerHeight;
        var backHeight = -windowHeight;
        var scrollHeight = documentHeight - windowHeight;
        var flag = setInterval(function () {
            scrollHeight = scrollHeight - 50;
            if (scrollHeight < 0) {
                clearInterval(flag);
                window.scrollBy(0, backHeight);
            } else {
                window.scrollBy(0, 50);
            }
        }, 1000);
        //新闻2分钟后关闭
        setTimeout(function () {
            window.close();
        }, 125000);
    } else if (location.href.indexOf("4426aa87b0b64ac671c96379a3a8bd26/db086044562a57b441c24f2af1c8e101.html#11c4o0tv7nb-5") != -1) {
        //新闻联播列表界面
        setTimeout(function () {
            $(".tab-wrapper div:contains('新闻联播')").click();
            setTimeout(function () {
                var videoNews = $(".grid-cell .textWrapper");
                //打开前四个视频新闻
                $.each(videoNews, function (i, v) {
                    v = $(v);
                    if (i < 8) {
                        setTimeout(function () {
                            v.click();
                            console.log("打开第" + (i + 1) + "个视频")
                        }, i * 187000);
                    }
                });
            }, 3000);
        }, 3000);
    } else if (location.href.indexOf("7f9f27c65e84e71e1b7189b7132b4710.html") != -1) {
        var documentHeight = $(document).height();
        var windowHeight = window.innerHeight;
        var backHeight = -windowHeight;
        var scrollHeight = documentHeight - windowHeight;
        var flag = setInterval(function () {
            scrollHeight = scrollHeight - 50;
            if (scrollHeight < 0) {
                clearInterval(flag);
                window.scrollBy(0, backHeight);
            } else {
                window.scrollBy(0, 50);
            }
        }, 1000);
        setTimeout(function () {
            var video = document.getElementsByTagName("video")[0];
            video.muted = true;
            video.play();
        }, 3000);
        //新闻联播视频3分钟后关闭
        setTimeout(function () {
            window.close();
        }, 185000);
    }
});