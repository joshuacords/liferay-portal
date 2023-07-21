/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.nio.intraband.proxy;

import com.liferay.portal.events.StartupHelperUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.nio.intraband.RegistrationReference;
import com.liferay.portal.kernel.nio.intraband.rpc.IntrabandRPCUtil;
import com.liferay.portal.kernel.process.ProcessCallable;

import java.util.concurrent.Future;

/**
 * @author Shuyang Zhou
 */
public class StubHolder<T> {

	public StubHolder(
		T originalT, String stubId, RegistrationReference registrationReference,
		StubCreator<T> stubCreator) {

		_originalT = originalT;
		_stubId = stubId;
		_registrationReference = registrationReference;
		_stubCreator = stubCreator;
	}

	public T getStub() {
		if (_stub != null) {
			return doGetStub();
		}

		synchronized (_registrationReference) {
			if (_stub != null) {
				return doGetStub();
			}

			Future<Boolean> future = IntrabandRPCUtil.execute(
				_registrationReference, _startupFinishedProcessCallable);

			boolean startupFinished = false;

			try {
				startupFinished = future.get();
			}
			catch (Exception exception) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to detect SPI's startup status", exception);
				}
			}

			if (!startupFinished) {
				return _originalT;
			}

			try {
				_stub = _stubCreator.createStub(
					_stubId, _originalT, _registrationReference);

				return _stub;
			}
			catch (Exception exception) {
				if (_log.isWarnEnabled()) {
					_log.warn("Unable to create stub", exception);
				}

				return _stubCreator.onCreationFailure(
					_stubId, _originalT, exception);
			}
		}
	}

	public interface StubCreator<T> {

		public T createStub(
				String stubId, T originalT,
				RegistrationReference registrationReference)
			throws Exception;

		public T onCreationFailure(
			String stubId, T originalT, Exception exception);

		public T onInvalidation(String stubId, T originalT, T stub);

	}

	protected T doGetStub() {
		if (_registrationReference.isValid()) {
			return _stub;
		}

		return _stubCreator.onInvalidation(_stubId, _stub, _originalT);
	}

	private static final Log _log = LogFactoryUtil.getLog(StubHolder.class);

	private static final ProcessCallable<Boolean>
		_startupFinishedProcessCallable = new StartupFinishedProcessCallable();

	private final T _originalT;
	private final RegistrationReference _registrationReference;
	private volatile T _stub;
	private final StubCreator<T> _stubCreator;
	private final String _stubId;

	private static class StartupFinishedProcessCallable
		implements ProcessCallable<Boolean> {

		@Override
		public Boolean call() {
			return StartupHelperUtil.isStartupFinished();
		}

		private static final long serialVersionUID = 1L;

	}

}