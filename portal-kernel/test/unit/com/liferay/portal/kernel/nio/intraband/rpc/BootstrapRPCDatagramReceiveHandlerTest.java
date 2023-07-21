/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.nio.intraband.rpc;

import com.liferay.portal.kernel.io.Deserializer;
import com.liferay.portal.kernel.io.Serializer;
import com.liferay.portal.kernel.nio.intraband.Datagram;
import com.liferay.portal.kernel.nio.intraband.SystemDataType;
import com.liferay.portal.kernel.nio.intraband.test.MockIntraband;
import com.liferay.portal.kernel.nio.intraband.test.MockRegistrationReference;
import com.liferay.portal.kernel.process.ProcessCallable;
import com.liferay.portal.kernel.process.ProcessException;
import com.liferay.portal.kernel.test.rule.CodeCoverageAssertor;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;

import java.util.List;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 */
public class BootstrapRPCDatagramReceiveHandlerTest {

	@ClassRule
	public static final CodeCoverageAssertor codeCoverageAssertor =
		new CodeCoverageAssertor() {

			@Override
			public void appendAssertClasses(List<Class<?>> assertClasses) {
				assertClasses.add(RPCResponse.class);
			}

		};

	@Test
	public void testReceive() throws Exception {
		Class<?> clazz = getClass();

		PortalClassLoaderUtil.setClassLoader(clazz.getClassLoader());

		BootstrapRPCDatagramReceiveHandler bootstrapRPCDatagramReceiveHandler =
			new BootstrapRPCDatagramReceiveHandler();

		MockIntraband mockIntraband = new MockIntraband();

		Serializer serializer = new Serializer();

		serializer.writeObject(new TestProcessCallable());

		SystemDataType systemDataType = SystemDataType.RPC;

		bootstrapRPCDatagramReceiveHandler.receive(
			new MockRegistrationReference(mockIntraband),
			Datagram.createRequestDatagram(
				systemDataType.getValue(), serializer.toByteBuffer()));

		Datagram responseDatagram = mockIntraband.getDatagram();

		Deserializer deserializer = new Deserializer(
			responseDatagram.getDataByteBuffer());

		RPCResponse rpcResponse = deserializer.readObject();

		Assert.assertEquals(
			TestProcessCallable.class.getName(), rpcResponse.getResult());
		Assert.assertNull(rpcResponse.getException());

		serializer = new Serializer();

		serializer.writeObject(new ErrorTestProcessCallable());

		bootstrapRPCDatagramReceiveHandler.receive(
			new MockRegistrationReference(mockIntraband),
			Datagram.createRequestDatagram(
				systemDataType.getValue(), serializer.toByteBuffer()));

		responseDatagram = mockIntraband.getDatagram();

		deserializer = new Deserializer(responseDatagram.getDataByteBuffer());

		rpcResponse = deserializer.readObject();

		Assert.assertNull(rpcResponse.getResult());

		Exception exception = rpcResponse.getException();

		Assert.assertSame(ProcessException.class, exception.getClass());
		Assert.assertEquals(
			ErrorTestProcessCallable.class.getName(), exception.getMessage());
	}

	private static class ErrorTestProcessCallable
		implements ProcessCallable<String> {

		@Override
		public String call() throws ProcessException {
			throw new ProcessException(
				ErrorTestProcessCallable.class.getName());
		}

		private static final long serialVersionUID = 1L;

	}

	private static class TestProcessCallable
		implements ProcessCallable<String> {

		@Override
		public String call() {
			return TestProcessCallable.class.getName();
		}

		private static final long serialVersionUID = 1L;

	}

}