/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.nio.intraband.proxy;

import com.liferay.portal.kernel.io.Deserializer;
import com.liferay.portal.kernel.nio.intraband.BaseAsyncDatagramReceiveHandler;
import com.liferay.portal.kernel.nio.intraband.Datagram;
import com.liferay.portal.kernel.nio.intraband.RegistrationReference;

/**
 * @author Shuyang Zhou
 */
public class IntrabandProxyDatagramReceiveHandler
	extends BaseAsyncDatagramReceiveHandler {

	@Override
	protected void doReceive(
		RegistrationReference registrationReference, Datagram datagram) {

		Deserializer deserializer = new Deserializer(
			datagram.getDataByteBuffer());

		String skeletonId = deserializer.readString();

		IntrabandProxySkeleton intrabandProxySkeleton =
			IntrabandProxySkeletonRegistryUtil.get(skeletonId);

		if (intrabandProxySkeleton == null) {
			throw new IllegalStateException(
				"Unable to find skeleton with ID " + skeletonId);
		}

		intrabandProxySkeleton.dispatch(
			registrationReference, datagram, deserializer);
	}

}