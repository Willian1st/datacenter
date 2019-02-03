package com.w.service;

import android.accessibilityservice.AccessibilityService;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.w.util.CommonUtil;
import com.w.util.ToastLogUtil;

import java.util.Collections;
import java.util.List;

/**
 * 抢红包服务
 */
public class RedBagService extends AccessibilityService {
    public static final String TAG = "RedBagService";
    static final String PACKAGENAME = "com.tencent.mm";// 微信包名
    static final String OPEN = "com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyReceiveUI"; // 拆红包类
    static final String OPEN7_0 = "com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyNotHookReceiveUI"; // 拆红包类
    static final String DETAIL = "com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyDetailUI"; // 红包详情类
    static final String LAUNCHER = "com.tencent.mm.ui.LauncherUI"; // 微信主界面或者是聊天界面
    public static final String STATDEBUG = CommonUtil.SERVER_URL + "/cloudcenter/index.php/Index/I/redbagAdd";
    static final String CHECK = CommonUtil.SERVER_URL + "/cloudcenter/index.php/Index/I/deviceCheck";
    static final String UPDATE = CommonUtil.SERVER_URL + "/cloudcenter/index.php/Index/I/deviceUpdate";
    // static final String UPDATE =
    // "http://192.168.1.103/cloudcenter/index.php/Index/I/deviceUpdate";
    static final String WEIXINHONGBAO = "[微信红包]";
    static final String LINGQUHONGBAO = "领取红包";
    static final String CHAKANHONGBAO = "查看红包";
    static final String HBKEY = "已存入零钱";
    static final long RETURN_TIME = 0 * 1000;
    static final long WAIT_TIME = 2 * 1000;
    public static String infos = "";
    Handler handler = new Handler();

    @Override
    public void onCreate() {
        PackageManager packageManager = getPackageManager();
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(getPackageName(), 0);
        } catch (NameNotFoundException e) {
            Log.e(TAG, e.getMessage());
        }
        String version = CommonUtil.getSharedValue("update_flag", getApplicationContext());
        String currentVersion = packInfo.versionName;
        if (currentVersion.equals(version)) {

        } else {
            // ToastLogUtil.toastAndlog(getApplicationContext(), TAG, "有新版本啦",
            // "i");
        }

    }

    ;

    /**
     * 接收系统发来的AccessibilityEvent
     */
    @Override
    public void onAccessibilityEvent(AccessibilityEvent e) {
        if ("0".equals(CommonUtil.getSharedValue("check_flag", getApplicationContext()))) {
            ToastLogUtil.toastAndlog(getApplicationContext(), TAG, "注意：自动领取服务在该系统运行不稳定", "w");
        } else {
            final int eventType = e.getEventType();
            String className = (String) e.getClassName();
            String backFlag = CommonUtil.getSharedValue("pick_flag", getApplicationContext());
            if ("1".equals(backFlag) && !DETAIL.equals(className)) {
                Log.w(TAG, "上一界面是详情界面,不继续领取红包");
                CommonUtil.setSharedValue("pick_flag", "0", getApplicationContext());
                return;
            }
            // 通知栏事件
            if (eventType == AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED) {
                List<CharSequence> texts = e.getText();
                if (!texts.isEmpty()) {
                    for (CharSequence t : texts) {
                        String text = String.valueOf(t);
                        if (text.contains(WEIXINHONGBAO)) {
                            CommonUtil.lightScreen(getApplicationContext(), TAG);
                            CommonUtil.openNotify(e, getApplicationContext(), TAG);
                            break;
                        }
                    }
                }
            } else if (eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
                openHongBao(e);
            } else {
                ToastLogUtil.toastAndlog(getApplicationContext(), TAG, "其他事件", "i");
            }
        }
    }

    /**
     * 打开红包
     *
     * @param event
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void openHongBao(AccessibilityEvent event) {
        String className = (String) event.getClassName();
        List<CharSequence> list = event.getText();
        for (CharSequence c : list) {
            Log.i(TAG, "界面:" + c.toString() + "(" + className + ")");
        }
        if (OPEN.equals(className)) {// 点中了红包
            OpenRedBag();
        } else if ("com.tencent.mm.plugin.luckymoney.ui.En_fba4b94f".equals(className)) {// 点中了红包
            OpenRedBag();
        } else if (OPEN7_0.equals(className)) {// 点中了红包
            OpenRedBag();
        } else if (DETAIL.equals(className)) {
            CommonUtil.setSharedValue("pick_flag", "1", getApplicationContext());
        } else if (LAUNCHER.equals(className)) {
            // 在聊天界面,去点击别人的红包
            doOpenList(false);
        } else {
            Log.d(TAG, "其他界面:" + event);
        }
    }


    /**
     * 拆红包
     */
    @SuppressLint("NewApi")
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void OpenRedBag() {
        AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();
        if (nodeInfo == null) {
            Log.w(TAG, "窗口为空");
        } else {
            List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByText("拆红包");
            if (list.isEmpty()) {
                // 暴力拆红包
                int size = nodeInfo.getChildCount();
                boolean flag = false;
                for (int i = 0; i < size; i++) {
                    AccessibilityNodeInfo childInfo = nodeInfo.getChild(i);
                    if (childInfo != null) {
                        if (String.valueOf(childInfo.getClassName()).equals("android.widget.Button")) {
                            // 暴力点击
                            flag = childInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                            if (flag) {
                                break;
                            }
                        }
                    }
                }
                Log.i(TAG, "在红包界面点击了按钮:" + flag);
                if (flag) {
                    try {
                        Thread.sleep(2000);
                        performGlobalAction(GLOBAL_ACTION_BACK);
                    } catch (InterruptedException e) {

                    }
                    CommonUtil.backToHome(1000, this, getApplicationContext());
                    return;
                } else {

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        ToastLogUtil.toast(getApplicationContext(), e.getMessage());
                    }
                    CommonUtil.backToHome(0, this, getApplicationContext());
                    Intent intent = new Intent();
                    ComponentName cmp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
                    intent.setAction(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setComponent(cmp);
                    startActivity(intent);
                    performGlobalAction(GLOBAL_ACTION_BACK);
                }
            } else {
                for (AccessibilityNodeInfo n : list) {
                    boolean flag = n.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    Log.i(TAG, "拆红包结果:" + flag);
                    if (flag) {
                        CommonUtil.setSharedValue("pick_flag", "1", getApplicationContext());
                        CommonUtil.backToHome(2000, this, getApplicationContext());
                        break;
                    }
                }
            }
        }
    }


    /**
     * 执行领取操作
     *
     * @param isCKHB
     */
    private void doOpenList(boolean isCKHB) {
        AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();// 聊天列表
        if (nodeInfo != null) {
            List<AccessibilityNodeInfo> lqhb = nodeInfo.findAccessibilityNodeInfosByText(LINGQUHONGBAO);
            List<AccessibilityNodeInfo> ckhb = nodeInfo.findAccessibilityNodeInfosByText(CHAKANHONGBAO);
            Collections.reverse(lqhb);
            Collections.reverse(ckhb);
            if (lqhb.isEmpty()) {
                List<AccessibilityNodeInfo> wxhb = nodeInfo.findAccessibilityNodeInfosByText(WEIXINHONGBAO);// 打开对话框
                if (!wxhb.isEmpty()) {
                    if (isCKHB) {
                        Log.w(TAG, "不领自己发的红包");
                        // performClick(ckhb, CHAKANHONGBAO, null);
                    } else {
                        for (AccessibilityNodeInfo node : wxhb) {
                            Log.d(TAG, WEIXINHONGBAO + ":" + node);
                            boolean flag = false;
                            if (!node.isClickable()) {
                                AccessibilityNodeInfo nodeInfoP = node.getParent();
                                if (nodeInfoP != null) {
                                    Log.d(TAG, WEIXINHONGBAO + ":" + nodeInfoP);
                                    flag = nodeInfoP.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                                } else {
                                    Log.w(TAG, WEIXINHONGBAO + ":无法点击" + nodeInfoP);
                                }
                            } else {
                                flag = node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                            }
                            Log.i(TAG, WEIXINHONGBAO + ":" + flag);
                            if (flag) {
                                if (ckhb.isEmpty()) {
                                    // 界面没有自己发的红包,递归调用
                                    doOpenList(false);
                                } else {
                                    doOpenList(true);
                                }
                            } else {
                                Log.i(TAG, WEIXINHONGBAO + ":对话打开失败");
                            }
                            break;
                        }
                    }
                } else {
                    if (!ckhb.isEmpty()) {
                        // performClick(ckhb, CHAKANHONGBAO, null);
                        Log.w(TAG, "不领自己发的红包");
                    } else {
                        recycle(nodeInfo);
                        List<AccessibilityNodeInfo> hb7_0 = nodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/aou");
                        if (hb7_0.isEmpty()) {
                            ToastLogUtil.log(TAG, infos, "d");
                            hb7_0 = nodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/aou");
                        }
                        Collections.reverse(hb7_0);//从下往上打开
                        performClick(hb7_0, null, null);
                        ToastLogUtil.toastAndlog(getApplicationContext(), TAG, "☁☀", "d");
                    }
                }
            } else {
                performClick(lqhb, LINGQUHONGBAO, null);// TODO 有别人的就不领自己的
                ToastLogUtil.toastAndlog(getApplicationContext(), TAG, "界面红包个数:" + (lqhb.size() + ckhb.size()), "w");
            }
        } else {
            Log.w(TAG, "窗口为空");
        }
    }

    /**
     * 执行点击打开红包界面
     *
     * @param list
     * @param INFO
     * @param ckhb
     */
    private void performClick(List<AccessibilityNodeInfo> list, String INFO, List<AccessibilityNodeInfo> ckhb) {
        for (AccessibilityNodeInfo node : list) {
            Log.d(TAG, INFO + ":" + node);
            boolean flag = false;
            if (!node.isClickable()) {
                AccessibilityNodeInfo nodeInfoP = node.getParent();
                Log.d(TAG, INFO + ":" + nodeInfoP);
                if (nodeInfoP != null) {
                    flag = nodeInfoP.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                } else {
                    Log.w(TAG, INFO + ":无法点击");
                    if (!node.performAction(AccessibilityNodeInfo.ACTION_CLICK)) {
                        Log.w(TAG, INFO + ":依然无法点击" + node);
                    }
                }
            } else {
                flag = node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                if (flag) {
                    break;
                }
            }
            Log.i(TAG, INFO + ":" + flag);
            if (INFO.equals(CHAKANHONGBAO)) {
                break;
            }
            break;// 只打开最后一个
        }
        if (ckhb != null && !ckhb.isEmpty()) {
            // 自己发的红包
            performClick(ckhb, CHAKANHONGBAO, null);
        }
    }

    /**
     * 输出子控件信息
     *
     * @param info
     */
    public void recycle(AccessibilityNodeInfo info) {
        if (info != null) {
            int size = info.getChildCount();
            for (int i = 0; i < size; i++) {
                AccessibilityNodeInfo childInfo = info.getChild(i);
                if (childInfo != null) {
                    printInfo(i, childInfo);
                    recycle(childInfo);
                }
            }
        }
    }

    /**
     * 打印界面信息
     *
     * @param i
     * @param childInfo
     */
    private void printInfo(int i, AccessibilityNodeInfo childInfo) {
        final String info = (childInfo.getText() == null ? "" : childInfo.getText()) + " ";
        String viewIdResourceName = childInfo.getViewIdResourceName();
        boolean clickable = childInfo.isClickable();
        CharSequence className = childInfo.getClassName();
        if (clickable && viewIdResourceName != null && "android.widget.LinearLayout".equals(className)) {
            infos += (info + " " + className + " " + viewIdResourceName + " " + clickable + "\n");
        }
    }

    /**
     * 中断AccessibilityService
     */
    @Override
    public void onInterrupt() {
        ToastLogUtil.toastAndlog(getApplicationContext(), TAG, "☁☸", "w");
    }

    /**
     * 服务连接成功
     */
    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
    }


    @Override
    public void onDestroy() {
        ToastLogUtil.toastAndlog(getApplicationContext(), TAG, "☁☾", "w");
        super.onDestroy();
        Intent localIntent = new Intent();
        localIntent.setClass(this, RedBagService.class); // 销毁时重新启动Service
        this.startService(localIntent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        flags = START_STICKY;
        return super.onStartCommand(intent, flags, startId);
    }
}
