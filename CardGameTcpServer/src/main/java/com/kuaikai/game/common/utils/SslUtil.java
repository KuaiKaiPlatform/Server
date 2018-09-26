package com.kuaikai.game.common.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;

public class SslUtil {
	private static volatile SSLContext sslContext = null;

	public static SSLContext createSSLContext(String type, String path, String password) throws Exception {
		if (null == sslContext) {
			synchronized (SslUtil.class) {
				if (null == sslContext) {

					KeyStore ks = KeyStore.getInstance(type); /// "JKS"
					InputStream ksInputStream = new FileInputStream(path); /// 证书存放地址
					ks.load(ksInputStream, password.toCharArray());
					KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
					kmf.init(ks, password.toCharArray());
					sslContext = SSLContext.getInstance("TLS");
					sslContext.init(kmf.getKeyManagers(), null, null);
				}
			}
		}
		return sslContext;
	}
}
