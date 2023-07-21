/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.model.listener;

import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.koroneiki.taproot.model.ContactRole;
import com.liferay.portal.kernel.model.ModelListener;

import java.util.concurrent.Callable;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = {
		"create.topic=koroneiki.contactrole.create",
		"remove.topic=koroneiki.contactrole.delete",
		"update.topic=koroneiki.contactrole.update"
	},
	service = ModelListener.class
)
public class ContactRoleModelListener
	extends BaseXylemModelListener<ContactRole> {

	@Override
	protected Callable<Message> getCallable(ContactRole contactRole)
		throws Exception {

		return () -> messageFactory.create(contactRole);
	}

}