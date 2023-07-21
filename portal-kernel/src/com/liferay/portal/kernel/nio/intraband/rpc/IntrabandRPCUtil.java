/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.nio.intraband.rpc;

import com.liferay.portal.kernel.concurrent.DefaultNoticeableFuture;
import com.liferay.portal.kernel.concurrent.NoticeableFuture;
import com.liferay.portal.kernel.io.Deserializer;
import com.liferay.portal.kernel.io.Serializer;
import com.liferay.portal.kernel.nio.intraband.CompletionHandler;
import com.liferay.portal.kernel.nio.intraband.Datagram;
import com.liferay.portal.kernel.nio.intraband.Intraband;
import com.liferay.portal.kernel.nio.intraband.RegistrationReference;
import com.liferay.portal.kernel.nio.intraband.SystemDataType;
import com.liferay.portal.kernel.process.ProcessCallable;

import java.io.IOException;
import java.io.Serializable;

import java.util.EnumSet;

/**
 * @author Shuyang Zhou
 */
public class IntrabandRPCUtil {

	public static <V extends Serializable> NoticeableFuture<V> execute(
		RegistrationReference registrationReference,
		ProcessCallable<V> processCallable) {

		Intraband intraband = registrationReference.getIntraband();

		SystemDataType systemDataType = SystemDataType.RPC;

		Serializer serializer = new Serializer();

		serializer.writeObject(processCallable);

		Datagram datagram = Datagram.createRequestDatagram(
			systemDataType.getValue(), serializer.toByteBuffer());

		DefaultNoticeableFuture<V> defaultNoticeableFuture =
			new DefaultNoticeableFuture<>();

		intraband.sendDatagram(
			registrationReference, datagram, null, repliedEnumSet,
			new FutureCompletionHandler<V>(defaultNoticeableFuture));

		return defaultNoticeableFuture;
	}

	protected static EnumSet<CompletionHandler.CompletionType> repliedEnumSet =
		EnumSet.of(CompletionHandler.CompletionType.REPLIED);

	protected static class FutureCompletionHandler<V extends Serializable>
		implements CompletionHandler<Object> {

		@Override
		public void delivered(Object attachment) {
		}

		@Override
		public void failed(Object attachment, IOException ioException) {
			_defaultNoticeableFuture.setException(ioException);
		}

		@Override
		public void replied(Object attachment, Datagram datagram) {
			Deserializer deserializer = new Deserializer(
				datagram.getDataByteBuffer());

			try {
				RPCResponse rpcResponse = deserializer.readObject();

				Exception exception = rpcResponse.getException();

				if (exception != null) {
					_defaultNoticeableFuture.setException(exception);
				}
				else {
					_defaultNoticeableFuture.set((V)rpcResponse.getResult());
				}
			}
			catch (ClassNotFoundException classNotFoundException) {
				_defaultNoticeableFuture.setException(classNotFoundException);
			}
		}

		@Override
		public void submitted(Object attachment) {
		}

		@Override
		public void timedOut(Object attachment) {
			_defaultNoticeableFuture.cancel(true);
		}

		protected FutureCompletionHandler(
			DefaultNoticeableFuture<V> defaultNoticeableFuture) {

			_defaultNoticeableFuture = defaultNoticeableFuture;
		}

		private final DefaultNoticeableFuture<V> _defaultNoticeableFuture;

	}

}