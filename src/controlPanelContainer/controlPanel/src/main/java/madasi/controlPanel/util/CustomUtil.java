package madasi.controlPanel.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomUtil {

	private final static Logger logger = LoggerFactory.getLogger(CustomUtil.class);

	public static String convertObjectToJson(Object obj) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString(obj);
		} catch (Exception e) {
			logger.error("convertObjectToJson error: ", e);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static Object convertJsonToObject(String json, Class c) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.readValue(json, c);
		} catch (Exception e) {
			logger.error("convertJsonToObject error: ", e);
			return null;
		}
	}
}
