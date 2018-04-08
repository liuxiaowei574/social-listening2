package com.china180.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.china180.thrift.ThriftClient;

/**
 * thrift客户端配置
 * 
 * @author shaowei.liu
 *
 */
@Configuration
public class RpcClientConfig {

	@Value("${thrift.host}")
	private String host;

	@Value("${thrift.port}")
	private int port;

	@Bean(initMethod = "init")
	public ThriftClient thriftClient() {
		ThriftClient thriftClient = new ThriftClient();
		thriftClient.setHost(host);
		thriftClient.setPort(port);
		return thriftClient;
	}
}