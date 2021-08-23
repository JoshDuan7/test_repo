package cn.tedu.sp09.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.web.util.JsonResult;

//通知指定服务id，可以知道，向哪一台主机转发调用
// http://localhost:8001
// http://localhost:8002
@FeignClient(name="item-service", fallback = ItemFeignServiceFB.class)
public interface ItemFeignService {
	/*
	 * Feign利用Spring MVC注解,来反向生成访问路径
	 * 
	 * 根据Mapping中指定的路径，在主机地址后面拼接路径
	 * 
	 * http://localhost:8001/{orderId}
	 * 
	 * 根据@PathVariable,把参数数据拼接到路径当中，假设参数是"123"
	 * http://localhost:8001/123
	 * 
	 * 向拼接的路径，来转发调用
	 */
	@GetMapping("/{orderId}")
	JsonResult<List<Item>> getItems(@PathVariable String orderId);
	
	/*
	 * 根据配置，拼接下面的路径，并向下面路径转发请求
	 * 在协议体中，携带商品参数
	 * http://localhost:8001/decreaseNumber
	 */
	@PostMapping("/decreaseNumber")
	JsonResult decreaseNumber(@RequestBody List<Item> items);
}
