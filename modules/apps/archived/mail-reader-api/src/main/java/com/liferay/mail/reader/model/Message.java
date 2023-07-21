/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mail.reader.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the Message service. Represents a row in the &quot;Mail_Message&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see MessageModel
 * @generated
 */
@ImplementationClassName("com.liferay.mail.reader.model.impl.MessageImpl")
@ProviderType
public interface Message extends MessageModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.mail.reader.model.impl.MessageImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Message, Long> MESSAGE_ID_ACCESSOR =
		new Accessor<Message, Long>() {

			@Override
			public Long get(Message message) {
				return message.getMessageId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Message> getTypeClass() {
				return Message.class;
			}

		};

	public long getGroupId()
		throws com.liferay.portal.kernel.exception.PortalException;

	public boolean hasAttachments();

	public boolean hasFlag(int flag);

}