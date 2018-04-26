package w.g.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import w.g.bean.SecurityBean;
import w.g.common.DatabaseHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class SecurityDao {
	private DatabaseHelper helper = null;
	SQLiteDatabase database = null;

	public SecurityDao(Context context) {
		helper = new DatabaseHelper(context);
	}

	/**
	 * 更新已查询次数
	 */
	void updateQueriedTime(SecurityBean securityBean) {
		Log.i("SecurityDao", "更新security表的已查询次数");
		String intSql = "update security set queried_times=queried_times+1 where user_type=?";
		try {
			// 创建或打开一个读写数据库
			database = helper.getWritableDatabase();
			database.beginTransaction();
			database.execSQL(intSql,
					new Object[] { securityBean.getUserType() });
			database.setTransactionSuccessful();
		} catch (Exception e) {
			Log.e("SecurityDao", "数据库更新失败:" + e.toString());
		} finally {
			database.endTransaction();
			database.close();
		}
	}

	/**
	 * 更新已重置次数
	 */
	public void updateResetedTime(SecurityBean securityBean) {
		Log.i("SecurityDao", "更新security表已重置次数");
		String intSql = "update security set reseted_times=reseted_times+1 where user_type=?";
		try {
			// 创建或打开一个读写数据库
			database = helper.getWritableDatabase();
			database.beginTransaction();
			database.execSQL(intSql,
					new Object[] { securityBean.getUserType() });
			database.setTransactionSuccessful();
		} catch (Exception e) {
			Log.e("SecurityDao", "数据库更新失败:" + e.toString());
		} finally {
			database.endTransaction();
			database.close();
		}
	}

	/**
	 * 检测当前用户状态
	 */
	public List<SecurityBean> queryStatus() {
		SQLiteDatabase database = null;
		List<SecurityBean> securityBean = new ArrayList<SecurityBean>();
		Map<String, String> map = new HashMap<String, String>();
		String sql = "select * from security order by id asc;";
		/*try {*/
			database = helper.getWritableDatabase();
			Cursor cursor = database.rawQuery(sql, null);
			int cumCount = cursor.getColumnCount();
			while (cursor.moveToNext()) {
				SecurityBean item = new SecurityBean();
				for (int i = 0; i < cumCount; i++) {
					String cols_name = cursor.getColumnName(i);
					String cols_value = cursor.getString(cursor
							.getColumnIndex(cols_name));
					if (cols_value == null) {
						cols_value = "";
					}
					map.put(cols_name, cols_value);
				}
				item.setId(map.get("id"));
				item.setUserType(map.get("user_type"));
				item.setActiveCode(map.get("active_code"));
				item.setActiveTime(map.get("active_time"));
				item.setQueryTimes(Integer.parseInt(map.get("query_times")));
				item.setQueriedTimes(Integer.parseInt(map.get("queried_times")));
				item.setResetedTimes(Integer.parseInt(map.get("reset_times")));
				item.setResetedTimes(Integer.parseInt(map.get("reseted_times")));
				securityBean.add(item);
			}
		/*} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (database != null) {
				database.close();
			}
		}*/
		return securityBean;
	}

	/**
	 * 获取当前用户的最大权限
	 */
	public String queryUserStatus() {
		SQLiteDatabase database = null;
		List<SecurityBean> securityBean = new ArrayList<SecurityBean>();
		Map<String, String> map = new HashMap<String, String>();
		String sql = "select user_type from  security order by id asc;";
		try {
			database = helper.getWritableDatabase();
			Cursor cursor = database.rawQuery(sql, null);
			int cumCount = cursor.getColumnCount();
			while (cursor.moveToNext()) {
				SecurityBean item = new SecurityBean();
				for (int i = 0; i < cumCount; i++) {
					String cols_name = cursor.getColumnName(i);
					String cols_value = cursor.getString(cursor
							.getColumnIndex(cols_name));
					if (cols_value == null) {
						cols_value = "";
					}
					map.put(cols_name, cols_value);
				}
				item.setId(map.get("id"));
				item.setUserType(map.get("user_type"));
				item.setActiveCode(map.get("active_code"));
				item.setActiveTime(map.get("active_time"));
				item.setQueryTimes(Integer.parseInt(map.get("query_times")));
				item.setQueriedTimes(Integer.parseInt(map.get("queried_times")));
				item.setResetedTimes(Integer.parseInt(map.get("reset_times")));
				item.setResetedTimes(Integer.parseInt(map.get("reseted_times")));
				securityBean.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (database != null) {
				database.close();
			}
		}
		return null;
	}
}
