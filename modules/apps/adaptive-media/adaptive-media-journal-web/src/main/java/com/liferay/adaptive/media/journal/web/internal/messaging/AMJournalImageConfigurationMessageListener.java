/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.adaptive.media.journal.web.internal.messaging;

import com.liferay.adaptive.media.image.constants.AMImageDestinationNames;
import com.liferay.journal.util.JournalContent;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alejandro Tardín
 */
@Component(
	immediate = true,
	property = "destination.name=" + AMImageDestinationNames.ADAPTIVE_MEDIA_IMAGE_CONFIGURATION,
	service = MessageListener.class
)
public class AMJournalImageConfigurationMessageListener
	extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		_journalContent.clearCache();
	}

	@Reference
	private JournalContent _journalContent;

}