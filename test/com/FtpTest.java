/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com;

import static org.junit.Assert.assertEquals;
import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;
import it.sauronsoftware.ftp4j.FTPReply;

import java.io.IOException;
import java.net.InetAddress;

import org.junit.Test;

/**
 * @author JIN Benli et ZHAO Xuening
 * 
 */
public class FtpTest {
	private FTPClient client = null;

	/**
	 * Test for processUSER
	 * 
	 * @throws FTPIllegalReplyException
	 * @throws IllegalStateException
	 * @throws FTPException
	 * @throws IOException
	 */
	@Test
	public void FtpUSERTest() throws IllegalStateException,
			FTPIllegalReplyException, FTPException, IOException {
		client = new FTPClient();
		client.connect(InetAddress.getLocalHost().getHostAddress().toString(),
				35001);
		FTPReply reply1 = client.sendCustomCommand("USER root");
		int s1 = reply1.getCode();
		assertEquals(331, s1);
	}

	/**
	 * Test for processPASS
	 * 
	 * @throws FTPIllegalReplyException
	 * @throws IOException
	 * @throws IllegalStateException
	 * @throws FTPException
	 */
	@Test
	public void FtpPASSTest() throws IllegalStateException, IOException,
			FTPIllegalReplyException, FTPException {
		client = new FTPClient();
		client.connect(InetAddress.getLocalHost().getHostAddress().toString(),
				35001);
		FTPReply reply1 = client.sendCustomCommand("USER root");
		int s1 = reply1.getCode();
		assertEquals(331, s1);
		FTPReply reply2 = client.sendCustomCommand("PASS root");
		int s2 = reply2.getCode();
		assertEquals(230, s2);
		client.disconnect(true);
	}

	/**
	 * Test for processPORT
	 * 
	 * @throws FTPException
	 * @throws FTPIllegalReplyException
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@Test
	public void FtpPORTTest() throws IllegalStateException, IOException,
			FTPIllegalReplyException, FTPException {
		client = new FTPClient();
		client.connect(InetAddress.getLocalHost().getHostAddress().toString(),
				35001);
		client.login("root", "root");
		String host = client.getHost();
		int port = client.getPort();
		int n1 = port / 256;
		int n2 = port % 256;
		String hostCommand = host.replace('.', ',');
		String portCommand = hostCommand + "," + n1 + "," + n2;
		FTPReply reply1 = client.sendCustomCommand("PORT " + portCommand);
		int s1 = reply1.getCode();
		assertEquals(200, s1);
		client.disconnect(true);
	}

	/**
	 * Test for processLIST
	 * 
	 * @throws FTPException
	 * @throws FTPIllegalReplyException
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@Test
	public void FtpLISTTest() throws IllegalStateException, IOException,
			FTPIllegalReplyException, FTPException {
		System.setProperty("java.net.preferIPv4Stack", "true");
		client = new FTPClient();
		client.connect(InetAddress.getLocalHost().getHostAddress().toString(),
				35001);
		client.login("root", "root");
		String host = client.getHost();
		int port = client.getPort();
		int n1 = port / 256;
		int n2 = port % 256;
		String hostCommand = host.replace('.', ',');
		String portCommand = hostCommand + "," + n1 + "," + n2;
		FTPReply reply1 = client.sendCustomCommand("PORT " + portCommand);
		int s1 = reply1.getCode();
		assertEquals(200, s1);
		FTPReply reply2 = client.sendCustomCommand("LIST");
		int s2 = reply2.getCode();
		assertEquals(125, s2);
		client.disconnect(true);
	}

	/**
	 * Test for processRETR
	 * 
	 * @throws FTPException
	 * @throws FTPIllegalReplyException
	 * @throws IOException
	 * @throws IllegalStateException
	 * @throws FTPAbortedException
	 * @throws FTPDataTransferException
	 */
	@Test
	public void FtpRETRTest() throws IllegalStateException, IOException,
			FTPIllegalReplyException, FTPException, FTPDataTransferException,
			FTPAbortedException {
		System.setProperty("java.net.preferIPv4Stack", "true");
		client = new FTPClient();
		client.connect(InetAddress.getLocalHost().getHostAddress().toString(),
				35001);
		client.login("root", "root");
		client.changeDirectory("/tmp");
		String host = client.getHost();
		int port = client.getPort();
		int n1 = port / 256;
		int n2 = port % 256;
		String hostCommand = host.replace('.', ',');
		String portCommand = hostCommand + "," + n1 + "," + n2;
		client.sendCustomCommand("PORT " + portCommand);
		FTPReply reply1 = client.sendCustomCommand("RETR test.txt");
		int s1 = reply1.getCode();
		assertEquals(150, s1);
	}

	/**
	 * Test for processSTOR
	 * 
	 * @throws FTPException
	 * @throws FTPIllegalReplyException
	 * @throws IOException
	 * @throws IllegalStateException
	 * @throws FTPAbortedException
	 * @throws FTPDataTransferException
	 */
	@Test
	public void FtpSTORTest() throws IllegalStateException, IOException,
			FTPIllegalReplyException, FTPException, FTPDataTransferException,
			FTPAbortedException {
		System.setProperty("java.net.preferIPv4Stack", "true");
		client = new FTPClient();
		client.connect(InetAddress.getLocalHost().getHostAddress().toString(),
				35001);
		client.login("root", "root");
		client.changeDirectory("/tmp");
		String host = client.getHost();
		int port = client.getPort();
		int n1 = port / 256;
		int n2 = port % 256;
		String hostCommand = host.replace('.', ',');
		String portCommand = hostCommand + "," + n1 + "," + n2;
		client.sendCustomCommand("PORT " + portCommand);
		FTPReply reply1 = client.sendCustomCommand("STOR test.txt");
		int s1 = reply1.getCode();
		assertEquals(150, s1);
		client.disconnect(true);
	}

	/**
	 * Test for processQUIT
	 * 
	 * @throws FTPException
	 * @throws FTPIllegalReplyException
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@Test
	public void FtpQUITTest() throws IllegalStateException, IOException,
			FTPIllegalReplyException, FTPException {
		client = new FTPClient();
		client.connect(InetAddress.getLocalHost().getHostAddress().toString(),
				35001);
		client.login("root", "root");
		FTPReply reply1 = client.sendCustomCommand("QUIT");
		int s1 = reply1.getCode();
		assertEquals(221, s1);
	}

	/**
	 * Test for processPWD
	 * 
	 * @throws FTPException
	 * @throws FTPIllegalReplyException
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@Test
	public void FtpPWDTest() throws IllegalStateException, IOException,
			FTPIllegalReplyException, FTPException {
		client = new FTPClient();
		client.connect(InetAddress.getLocalHost().getHostAddress().toString(),
				35001);
		client.login("root", "root");
		FTPReply reply1 = client.sendCustomCommand("PWD");
		int s1 = reply1.getCode();
		assertEquals(257, s1);
		client.disconnect(true);
	}

	/**
	 * Test for processCWD
	 * 
	 * @throws FTPException
	 * @throws FTPIllegalReplyException
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@Test
	public void FtpCWDTest() throws IllegalStateException, IOException,
			FTPIllegalReplyException, FTPException {
		client = new FTPClient();
		client.connect(InetAddress.getLocalHost().getHostAddress().toString(),
				35001);
		client.login("root", "root");
		FTPReply reply1 = client.sendCustomCommand("CWD /home");
		int s1 = reply1.getCode();
		assertEquals(250, s1);
		client.disconnect(true);
	}

	/**
	 * Test for processCDUP
	 * 
	 * @throws FTPException
	 * @throws FTPIllegalReplyException
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@Test
	public void FtpCDUPTest() throws IllegalStateException, IOException,
			FTPIllegalReplyException, FTPException {
		client = new FTPClient();
		client.connect(InetAddress.getLocalHost().getHostAddress().toString(),
				35001);
		client.login("root", "root");
		client.changeDirectory("/home");
		FTPReply reply1 = client.sendCustomCommand("CDUP");
		int s1 = reply1.getCode();
		assertEquals(200, s1);
		client.disconnect(true);
	}
}

