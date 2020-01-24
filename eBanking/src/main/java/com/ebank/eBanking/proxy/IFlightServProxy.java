package com.ebank.eBanking.proxy;

import java.util.List;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="flight-service")
@RibbonClient(name="flight-service")
public interface IFlightServProxy {
	@GetMapping("/flight-service/service-instances/{applicationName}")
	List<ServiceInstance> serviceInstancesByApplicationNam(@PathVariable("applicationName") String applicationName);
}
