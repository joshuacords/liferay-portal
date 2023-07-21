/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.license.messaging;

import com.liferay.portal.kernel.messaging.Message;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Igor Beslic
 */
public class LicenseManagerMessageTypeTest {

	@Test
	public void testCreateMessage() {
		LicenseManagerMessageType validateLCSLicenseManagerMessageType =
			LicenseManagerMessageType.LCS_AVAILABLE;

		Message message = validateLCSLicenseManagerMessageType.createMessage();

		Object payload = message.getPayload();

		Assert.assertEquals("liferay/lcs_status", message.getDestinationName());

		Assert.assertTrue(
			"message payload is string", payload instanceof String);

		Assert.assertEquals("{\"type\": \"LCS_AVAILABLE\"}", payload);

		message = validateLCSLicenseManagerMessageType.createMessage(
			LCSPortletState.NO_SUBSCRIPTION);

		payload = message.getPayload();

		Assert.assertTrue(
			"message payload is string", payload instanceof String);

		Assert.assertEquals(
			"{\"state\": 4, \"type\": \"LCS_AVAILABLE\"}", payload);
	}

}