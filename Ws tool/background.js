var Notification = window.Notification || window.mozNotification || window.webkitNotification;
var notify = new window.Notification('欢迎使用！');
if (window.Notification.permission == 'granted') {
    notify.onclick = function () {
        // 如果通知消息被点击,通知窗口将被激活
        window.focus();
    };
    notify.onerror = function () {
        console.log("桌面消息出错！");
    };
    notify.onshow = function () {
        setTimeout(function () {
            notify.close();
        }, 2000);
    };
    notify.onclose = function () {
        console.log("桌面消息关闭！");
    };
} else {
    console.log("桌面消息未打开！");
}
chrome.extension.onRequest.addListener(function (request, sender, sendResponse) {
    if (request.cmd == "history") {
        chrome.history.search({
            text: '',
            maxResults: 10
        }, function (result) {
            var url = [];
            $.each(result, function (i, v) {
                url.push(v.url);
            });
            sendResponse({
                result: url
            });
            var content = "";
            $.each(url, function (i, v) {
                content += (v + "<br>");
            });
            var notice = new window.Notification("Tips", {
                icon: "icon16.png",
                body: "页面已打开：" + content
            });
            notice.onshow = function () {
                setTimeout(function () {
                    notice.close();
                }, 2000);
            };
        })
    } else if (request.cmd == "tab") {
        chrome.tabs.getAllInWindow(function (result) {
            console.log(result)
        });
        chrome.tabs.create({
            url: "chrome://extensions/"
        }, function (result) {
            var notice = new window.Notification("Tips", {
                icon: "icon16.png",
                body: "页面已打开：" + result.url
            });
            notice.onshow = function () {
                setTimeout(function () {
                    notice.close();
                }, 2000);
            };
        });
        sendResponse({
            result: request.cmd
        });
    } else if (request.cmd == "suning") {
        chrome.tabs.create({
            url: "http://sign.suning.com/sign/welcome.do"
        }, function (result) {
            var notice = new window.Notification("Tips", {
                icon: "icon16.png",
                body: "页面已打开：" + result.url
            });
            $(".starttip").click();
            notice.onshow = function () {
                setTimeout(function () {
                    notice.close();
                }, 2000);
            };
        });
        sendResponse({
            result: request.cmd
        });
    } else if (request.cmd == "guomei") {
        chrome.tabs.create({
            url: "http://club.gome.com.cn/member/memberClub"
        }, function (result) {
        });
        sendResponse({
            result: request.cmd
        });
    } else if (request.cmd == "xuexi") {
        chrome.tabs.create({
            url: "https://www.xuexi.cn/"
        }, function (result) {
            var notice = new window.Notification("Tips", {
                icon: "icon16.png",
                body: "准备开始学习..."
            });
            notice.onshow = function () {
                setTimeout(function () {
                    notice.close();
                }, 3000);
            };
        });
        sendResponse({
            result: request.cmd
        });
    } else if (request.cmd == "xuexi_jifen") {
        $.ajax({
            url: "https://pc-api.xuexi.cn/open/api/score/today/queryrate",
            success: function (data) {
                var dayScoreDtos = data.data.dayScoreDtos;
                var text = "";
                text += (dayScoreDtos[8].name + ":" + dayScoreDtos[8].currentScore + "/" + dayScoreDtos[8].dayMaxScore + " ");
                text += (dayScoreDtos[0].name + ":" + dayScoreDtos[0].currentScore + "/" + dayScoreDtos[0].dayMaxScore + "\r");
                text += (dayScoreDtos[1].name + ":" + dayScoreDtos[1].currentScore + "/" + dayScoreDtos[1].dayMaxScore + " ");
                text += (dayScoreDtos[7].name + ":" + dayScoreDtos[7].currentScore + "/" + dayScoreDtos[7].dayMaxScore + "\r");
                text += (dayScoreDtos[9].name + ":" + dayScoreDtos[9].currentScore + "/" + dayScoreDtos[9].dayMaxScore);
                var notice = new window.Notification("积分情况如下", {
                    icon: "icon16.png",
                    body: text
                });
                notice.onshow = function () {
                    setTimeout(function () {
                        notice.close();
                    }, 5000);
                };
            }
        });
    } else if (request.cmd == "xuexi_news") {
        var notice = new window.Notification("Tips", {
            icon: "icon16.png",
            body: "正在学习文字新闻..."
        });
        notice.onshow = function () {
            setTimeout(function () {
                notice.close();
            }, 5000);
        };
        sendResponse({
            result: request.cmd
        });
    } else if (request.cmd == "xuexi_video_news") {
        chrome.tabs.create({
            url: "https://www.xuexi.cn/4426aa87b0b64ac671c96379a3a8bd26/db086044562a57b441c24f2af1c8e101.html#11c4o0tv7nb-5"
        }, function (result) {
            var notice = new window.Notification("Tips", {
                icon: "icon16.png",
                body: "准备开始学习视频..."
            });
            notice.onshow = function () {
                setTimeout(function () {
                    notice.close();
                }, 5000);
            };
        });
        sendResponse({
            result: request.cmd
        });
    } else {
        sendResponse({
            result: "hi"
        });
    }
});

chrome.contextMenus.create({
    type: "normal",
    contexts: ["image"],
    title: "预览",
    onclick: function (info, tab) {
        console.log(info);
        if (info.mediaType == 'image') {
            chrome.tabs.query({
                title: "配置"
            }, function (tabs) {
                console.table(tabs)
                if (tabs && tabs[0]) {
                    chrome.extension.sendRequest({
                        cmd: 'internetUrl',
                        internetUrl: info.srcUrl
                    }, function (response) {
                        console.log(response);
                    });
                } else {
                    chrome.tabs.create({
                        url: "chrome-extension://" + chrome.runtime.id + "/options.html#/map?internetUrl="
                            + info.srcUrl
                    }, function (result) {
                        console.log("预览:" + result);
                    });
                }

            })

        } else {
        }
    }
}, function (result) {
    console.log("右键菜单初始化完成");
});
