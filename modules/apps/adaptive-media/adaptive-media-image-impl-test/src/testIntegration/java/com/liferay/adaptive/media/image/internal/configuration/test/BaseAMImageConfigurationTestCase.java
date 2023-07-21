/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.adaptive.media.image.internal.configuration.test;

import com.liferay.adaptive.media.image.configuration.AMImageConfigurationEntry;
import com.liferay.adaptive.media.image.configuration.AMImageConfigurationHelper;
import com.liferay.adaptive.media.image.constants.AMImageDestinationNames;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.test.rule.Inject;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

/**
 * @author Alejandro Hernández
 */
public abstract class BaseAMImageConfigurationTestCase {

	@Before
	public void setUp() throws Exception {
		deleteAllConfigurationEntries();
	}

	@After
	public void tearDown() throws Exception {
		deleteAllConfigurationEntries();
	}

	protected void assertDisabled(
		Optional<AMImageConfigurationEntry> amImageConfigurationEntryOptional) {

		Assert.assertTrue(amImageConfigurationEntryOptional.isPresent());

		AMImageConfigurationEntry amImageConfigurationEntry =
			amImageConfigurationEntryOptional.get();

		Assert.assertFalse(amImageConfigurationEntry.isEnabled());
	}

	protected void assertEnabled(
		Optional<AMImageConfigurationEntry> amImageConfigurationEntryOptional) {

		Assert.assertTrue(amImageConfigurationEntryOptional.isPresent());

		AMImageConfigurationEntry amImageConfigurationEntry =
			amImageConfigurationEntryOptional.get();

		Assert.assertTrue(amImageConfigurationEntry.isEnabled());
	}

	protected List<Message> collectConfigurationMessages(
			CheckedRunnable runnable)
		throws Exception {

		List<Message> messages = new ArrayList<>();

		MessageListener messageListener = messages::add;

		_messageBus.registerMessageListener(
			AMImageDestinationNames.ADAPTIVE_MEDIA_IMAGE_CONFIGURATION,
			messageListener);

		try {
			runnable.run();
		}
		finally {
			_messageBus.unregisterMessageListener(
				AMImageDestinationNames.ADAPTIVE_MEDIA_IMAGE_CONFIGURATION,
				messageListener);
		}

		return messages;
	}

	protected void deleteAllConfigurationEntries()
		throws IOException, PortalException {

		AMImageConfigurationHelper amImageConfigurationHelper =
			getAMImageConfigurationHelper();

		Collection<AMImageConfigurationEntry> amImageConfigurationEntries =
			amImageConfigurationHelper.getAMImageConfigurationEntries(
				TestPropsValues.getCompanyId(),
				amImageConfigurationEntry -> true);

		for (AMImageConfigurationEntry amImageConfigurationEntry :
				amImageConfigurationEntries) {

			amImageConfigurationHelper.forceDeleteAMImageConfigurationEntry(
				TestPropsValues.getCompanyId(),
				amImageConfigurationEntry.getUUID());
		}
	}

	protected abstract AMImageConfigurationHelper
		getAMImageConfigurationHelper();

	@FunctionalInterface
	protected interface CheckedRunnable {

		public void run() throws Exception;

	}

	@Inject
	private MessageBus _messageBus;

}