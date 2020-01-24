package com.ebank.eBanking.contoller;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ebank.eBanking.model.CustomerEntity;
import com.ebank.eBanking.model.FundTransferModel;
import com.ebank.eBanking.model.OTPModel;
import com.ebank.eBanking.model.PayeeEntity;
import com.ebank.eBanking.proxy.IFlightServProxy;
import com.ebank.eBanking.proxy.IOtpProxy;
import com.ebank.eBanking.service.CustomerService;
import com.ebank.eBanking.service.ICustomerService;
import com.ebank.eBanking.service.IPayeeService;
import com.ebank.eBanking.service.PayeeService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/ebank")
@Api(value = "eBanking Management System", description = "Operations pertaining to customer in eBanking Management System")
public class EBankCustomerCtrl {
	@Autowired
	CustomerService service;

	@Autowired
	ICustomerService cusomerService;

	@Autowired
	PayeeService payeeService;

	@Autowired
	IPayeeService iPayeeService;

	@Autowired
	IOtpProxy otpProxy;

	@Autowired
	IFlightServProxy flightProxy;

	@Autowired
	private EurekaClient discoveryClient;

	@GetMapping("/getcustomers")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "The request has succeeded or (your message)"),
	        @ApiResponse(code = 401, message = "The request requires user authentication or (your message)"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden or (your message)"),
	        @ApiResponse(code = 404, message = "The server has not found anything matching the Request-URI or (your message)")})
	@ResponseBody
	public List<CustomerEntity> getCustomers() {
		List<CustomerEntity> customers = service.getAllCustomers();
		return customers;
	}

	@PostMapping("/createcustomer")
	@ResponseBody
	public CustomerEntity createCustomer(@RequestBody CustomerEntity customer) {
		CustomerEntity customerEntity = cusomerService.searchCstByAccountNo(customer.getAccountno());
		System.out.println(customerEntity);
		if (customerEntity == null)
			return service.createCustomer(customer);
		else
			return customerEntity;
	}

	@PostMapping("/addpayee")
	@ResponseBody
	public PayeeEntity addPayee(@RequestBody PayeeEntity payeeEntity) {
		PayeeEntity entity = payeeService.createPayee(payeeEntity);
		return entity;
	}

	@PostMapping("/transfermoney")
	@ResponseBody
	public String transferMoney(@RequestBody FundTransferModel fundTransferModel) {
		JSONObject responseObj = new JSONObject();
		try {
			CustomerEntity customerEntity = cusomerService.searchCstByAccountNo(fundTransferModel.getAccountno());
			if (customerEntity != null) {
				PayeeEntity payee = iPayeeService.payee(fundTransferModel.getPayeeAccNo());
				if (payee != null) {
					if (customerEntity.getBalance() < fundTransferModel.getAmount()) {
						responseObj.put("message", "Requested amount is greater than available balance");
					} else {
						cusomerService.updateBalance(fundTransferModel.getAmount(), fundTransferModel.getAccountno());
						iPayeeService.updatePayeeBalance(fundTransferModel.getAmount(), payee.getAccountNo());
						responseObj.put("message",
								"Transfer of Rs. " + fundTransferModel.getAmount() + " is successful");
					}
				} else {
					responseObj.put("message", "Payee details not found");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseObj.toString();
	}

	@PostMapping("/getpayee")
	public PayeeEntity getPayee(@RequestBody PayeeEntity entity) {
		PayeeEntity payeeEntity = iPayeeService.payee(entity.getAccountNo());
		return payeeEntity;
	}

	@GetMapping("/greeting")
	public String greeting() throws Exception {
		Thread.sleep(10000);
		return "Welcome to eBanking";
	}

	@PostMapping("/getotp")
	public OTPModel getOtp(@RequestBody OTPModel otpModel) {
		OTPModel otpModel2 = otpProxy.getOtp(otpModel);
		return otpModel2;
	}

	@GetMapping("flight-services/service-instances/{applicationName}")
	@HystrixCommand(fallbackMethod = "getServiceInstancesByApplicationName")
	public List<ServiceInstance> serviceInstancesByApplicationName(@PathVariable String applicationName) {
		InstanceInfo instance = discoveryClient.getNextServerFromEureka("flight-service", false);
		System.out.println("[-------- flight-services/service-instances/{applicationName} -------- ]");
		System.out.println("SSID :: " + instance.getSID());
		System.out.println("getAppName :: " + instance.getAppName());
		System.out.println("getHomePageUrl :: " + instance.getHomePageUrl());
		System.out.println("getIPAddr :: " + instance.getIPAddr());
		List<ServiceInstance> response = flightProxy.serviceInstancesByApplicationNam(applicationName);
		return response;
	}

	List<ServiceInstance> getServiceInstancesByApplicationName(String applicationName) throws JSONException {
		// List<ServiceInstance> response =
		// flightProxy.serviceInstancesByApplicationNam(applicationName);
		InstanceInfo instance = discoveryClient.getNextServerFromEureka("flight-service", false);
		System.out.println("[-------- fall back mechanisim -------- ]");
		System.out.println("SSID :: " + instance.getSID());
		System.out.println("getAppName :: " + instance.getAppName());
		System.out.println("getHomePageUrl :: " + instance.getHomePageUrl());
		System.out.println("getIPAddr :: " + instance.getIPAddr());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("SSID", instance.getSID());
		jsonObject.put("getHomePageUrl :: ", instance.getHomePageUrl());
		jsonObject.put("getIPAddr :: ", instance.getIPAddr());
		System.out.println(jsonObject.toString());
		return null;
	}
}