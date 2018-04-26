package w.g.util;

import java.util.Map;
import java.util.Set;

import w.g.service.RedBagService;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class CommonUtil {

	public static String simpleMapToJsonStr(Map<String, String> map) {
		if (map == null || map.isEmpty()) {
			return "null";
		}
		String jsonStr = "{";
		Set<?> keySet = map.keySet();
		for (Object key : keySet) {
			jsonStr += "\"" + key + "\":\"" + map.get(key) + "\",";
		}
		jsonStr = jsonStr.substring(0, jsonStr.length() - 2);
		jsonStr += "}";
		return jsonStr;
	}

	public static void setSharedValue(String flag, String value, Context context) {
		try {
			if (!value.equals(getSharedValue(flag, context))) {
				SharedPreferences sp = context.getSharedPreferences("RedBag", Context.MODE_PRIVATE);
				Editor editor = sp.edit();
				editor.putString(flag, value);
				editor.commit();
				if (value.equals(getSharedValue(flag, context))) {
					Log.i(RedBagService.TAG, flag + "已修改为:" + value);
				}
			}
		} catch (Exception e) {
			Log.d(RedBagService.TAG, flag + "修改失败:" + value);
		}
	}

	public static String getSharedValue(String flag, Context context) {
		SharedPreferences sp = context.getSharedPreferences("RedBag", Context.MODE_PRIVATE);
		return sp.getString(flag, "0");
	}
}
