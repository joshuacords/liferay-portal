/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mail.reader.internal.mailbox;

import com.liferay.mail.reader.constants.MailPortletKeys;
import com.liferay.mail.reader.mailbox.Mailbox;
import com.liferay.mail.reader.mailbox.MailboxFactory;
import com.liferay.mail.reader.model.Account;
import com.liferay.osgi.service.tracker.collections.map.ServiceReferenceMapper;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Component;

/**
 * @author Scott Lee
 */
@Component(
	immediate = true, property = "javax.portlet.name=" + MailPortletKeys.MAIL,
	service = MailboxFactoryUtil.class
)
public class MailboxFactoryUtil {

	public static Mailbox getMailbox(
			User user, Account account, String password)
		throws PortalException {

		MailboxFactory mailboxFactory = _mailboxFactories.getService(
			account.getProtocol());

		if (mailboxFactory == null) {
			throw new IllegalArgumentException(
				"Invalid protocol " + account.getProtocol());
		}

		return mailboxFactory.getMailbox(user, account, password);
	}

	public static Mailbox getMailbox(User user, String protocol)
		throws PortalException {

		MailboxFactory mailboxFactory = _mailboxFactories.getService(protocol);

		if (mailboxFactory == null) {
			throw new IllegalArgumentException("Invalid protocol " + protocol);
		}

		return mailboxFactory.getMailbox(user, protocol);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MailboxFactoryUtil.class);

	private static final ServiceTrackerMap<String, MailboxFactory>
		_mailboxFactories;

	static {
		Bundle bundle = FrameworkUtil.getBundle(MailboxFactoryUtil.class);

		final BundleContext bundleContext = bundle.getBundleContext();

		_mailboxFactories = ServiceTrackerMapFactory.openSingleValueMap(
			bundleContext, MailboxFactory.class, null,
			new ServiceReferenceMapper<String, MailboxFactory>() {

				@Override
				public void map(
					ServiceReference<MailboxFactory> serviceReference,
					ServiceReferenceMapper.Emitter<String> emitter) {

					MailboxFactory mailboxFactory = bundleContext.getService(
						serviceReference);

					try {
						mailboxFactory.initialize();

						emitter.emit(mailboxFactory.getMailboxFactoryName());
					}
					catch (PortalException portalException) {
						_log.error(
							"Unable to initialize mail box factory",
							portalException);
					}
					finally {
						bundleContext.ungetService(serviceReference);
					}
				}

			});
	}

}