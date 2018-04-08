package com.china180.thrift;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import caenginee.CAEnginee;
import caenginee.CAEnginee.Client;

public class ThriftClient {

	protected Logger logger = LogManager.getLogger(ThriftClient.class);

	private String host;

	private int port;

	private TTransport transport;

	private TProtocol protocol;

	private Client client;

	public void init() {
		transport = new TSocket(host, port);
		protocol = new TBinaryProtocol(transport);
		client = new CAEnginee.Client(protocol);
	}

	public void open() throws TTransportException {
		transport.open();
	}

	public int sendTaskToEngine(int taskType, String parametersAsJSON) {
		String res = null;
		try {
			int count = client.ping();
			logger.debug("ping result: {}", count);
			res = client.call(taskType, parametersAsJSON);
			logger.debug("call rpc result: {}", res);
		} catch (TException e) {
			e.printStackTrace();
			logger.info("sendTaskToEngine exception", e);
		}

		if (res != null) {
			return 0;
		} else {
			return 1;
		}
	}

	public String queryFromEngine(String parametersAsJSON) {
		String res = null;
		try {
			res = client.call(2, parametersAsJSON);
			logger.debug("call rpc result: {}", res);
		} catch (TException e) {
			e.printStackTrace();
			logger.info("queryFromEngine exception", e);
		}

		return res;
	}

	@Override
	protected void finalize() {
		transport.close();
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
