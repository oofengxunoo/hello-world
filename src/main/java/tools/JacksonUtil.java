package tools;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtil {
	
	private static final Logger LOG = LoggerFactory.getLogger(JacksonUtil.class);
	
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	/**
	 * 将实体对象转换为json字符串
	 * @author GC-ZGY
	 * @param o
	 * @return
	 */
	public static String object2Json(Object o){
		try {
			return objectMapper.writeValueAsString(o);
		} catch (JsonProcessingException e) {
			LOG.error("对象转换JSON字符串失败", e);
			throw new RuntimeException("对象转换JSON字符串失败",e);
		}
	}
	
	/**
	 * 实体对象转换为map
	 * @author GC-ZGY
	 * @param o
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> object2Map(Object o){
		return objectMapper.convertValue(o, Map.class);
	}
	
	/**
	 * 实体对象转换为<code>MultiValueMap</code>，用以作为<code>Resttemplate</code>中请求实体发送
	 * @author GC-ZGY
	 * @param o
	 * @return
	 */
	public static MultiValueMap<String, String> object2MultiValueMap(Object o){
		Map<String, String> map = object2Map(o);
		MultiValueMap<String , String> mmap = new LinkedMultiValueMap<>();
		mmap.setAll(map);
		return mmap;
	}

}
