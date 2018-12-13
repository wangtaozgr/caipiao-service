package com.atao.caipiao.http;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.atao.caipiao.util.HttpUrlConnectUtils;
import com.atao.util.StringUtils;

public class ChunqiuHttp {
	private static Logger logger = LoggerFactory.getLogger(ChunqiuHttp.class);
	private static CookieManager manager = new CookieManager();
	static {
		manager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
		CookieHandler.setDefault(manager);
	}

	public static void main(String[] args) {
		// prelogin();
		// login();
		retriveTodayCaiPiaoKaijianInfo();
	}

	public static boolean login() {
		JSONObject object = prelogin();
		String url = "https://www.chunqiu2.com/authority/signin";
		String postData = "_token=" + object.getString("token") + "&_random=" + object.getString("random")
				+ "&username=wangtaocp&password=fd4ad7954378c45e083c09eaf708bc4c";
		Map<String, String> heads = new HashMap<String, String>();
		heads.put("Referer", "https://www.chunqiu2.com/authority/signin");
		heads.put("X-Requested-With", "XMLHttpRequest");
		String content = HttpUrlConnectUtils.httpPost(url, postData, heads, null);
		logger.info(content);
		if (content.startsWith("https://www.chunqiu2.com")) {
			return true;
		}
		return false;
	}

	public static JSONObject prelogin() {
		JSONObject object = new JSONObject();
		String url = "https://www.chunqiu2.com/authority/signin";
		Map<String, String> heads = new HashMap<String, String>();
		String content = HttpUrlConnectUtils.httpGet(url, true, heads);
		Document doc = Jsoup.parse(content);
		Elements token = doc.getElementsByAttributeValue("name", "_token");
		Elements random = doc.getElementsByAttributeValue("name", "_random");
		object.put("token", token.val());
		object.put("random", random.val());
		return object;
	}

	public static JSONArray retriveTodayCaiPiaoKaijianInfo() {
		String url = "https://www.chunqiu2.com/user-trends/trend-data?&lottery_id=1&num_type=5&count=30&_="
				+ new Date().getTime();
		Map<String, String> heads = new HashMap<String, String>();
		heads.put("Referer", "https://www.chunqiu2.com/user-trends/trend-view/1");
		heads.put("X-Requested-With", "XMLHttpRequest");
		String content = HttpUrlConnectUtils.httpGet(url, false, heads);
		if (StringUtils.isNotBlank(content)) {
			JSONObject result = JSONObject.parseObject(content);
			if (result.getBooleanValue("isSuccess")) {
				JSONArray data = result.getJSONObject("data").getJSONArray("data");
				return data;
			} else {
				login();
				return retriveTodayCaiPiaoKaijianInfo();
			}
		}
		return null;

	}

}
