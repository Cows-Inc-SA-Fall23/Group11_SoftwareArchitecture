package madasi.controlPanel.util;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import madasi.controlPanel.service.ProducerService;

public class CustomUtil {

	private final static Logger logger = LoggerFactory.getLogger(CustomUtil.class);

	public static final int DEFAULT_TIMEOUT = 30;

	private static Map<String, CompletableFuture<String>> requestMap = new ConcurrentHashMap<>();

	public static CompletableFuture<String> getFromRequestMap(String id) {
		return requestMap.get(id);
	}

	public static CompletableFuture<String> removeFromRequestMap(String id) {
		return requestMap.remove(id);
	}

	public static String convertObjectToJson(Object obj) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString(obj);
		} catch (Exception e) {
			logger.error("convertObjectToJson error: ", e);
			return null;
		}
	}

	public static Object convertJsonToObject(String json, Class<?> c) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.readValue(json, c);
		} catch (Exception e) {
			logger.error("convertJsonToObject error: ", e);
			return null;
		}
	}

	public static String sendAndReceiveKafkaMessage(String stringToSend, String requestId, ProducerService prodService)
			throws InterruptedException, ExecutionException, TimeoutException {
		CompletableFuture<String> future = new CompletableFuture<>();
		// Store the future object in a map or similar structure, keyed by requestId
		requestMap.put(requestId, future);
		// Send message to Kafka, including the requestId
		String requestPayload = stringToSend;
		prodService.sendMessage("silo_data_request", requestPayload);

		// Wait for the response
		return future.get(CustomUtil.DEFAULT_TIMEOUT, TimeUnit.SECONDS); // Timeout after 30 seconds
	}
}
