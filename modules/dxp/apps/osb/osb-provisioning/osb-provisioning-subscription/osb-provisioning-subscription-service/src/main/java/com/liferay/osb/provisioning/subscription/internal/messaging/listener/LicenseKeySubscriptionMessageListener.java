/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.provisioning.subscription.internal.messaging.listener;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactWebService;
import com.liferay.osb.provisioning.license.model.LicenseKey;
import com.liferay.osb.provisioning.license.service.LicenseKeyLocalService;
import com.liferay.osb.provisioning.subscription.model.SubscriptionEntry;
import com.liferay.osb.provisioning.subscription.service.SubscriptionEntryLocalService;
import com.liferay.osb.provisioning.util.CustomerPortalRelease;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.SchedulerEntry;
import com.liferay.portal.kernel.scheduler.SchedulerEntryImpl;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.service.ClassNameLocalService;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author William Newbury
 */
@Component(
	immediate = true, service = LicenseKeySubscriptionMessageListener.class
)
public class LicenseKeySubscriptionMessageListener extends BaseMessageListener {

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		Class<?> clazz = getClass();

		String className = clazz.getName();

		Trigger trigger = _triggerFactory.createTrigger(
			className, className, null, null, "0 0 0 * * ?");

		SchedulerEntry schedulerEntry = new SchedulerEntryImpl(
			className, trigger);

		_schedulerEngineHelper.register(
			this, schedulerEntry, DestinationNames.SCHEDULER_DISPATCH);
	}

	@Deactivate
	protected void deactivate() {
		_schedulerEngineHelper.unregister(this);
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		_sendActivationKeyEmails(30);
		_sendActivationKeyEmails(14);
		_sendActivationKeyEmails(0);
	}

	private void _sendActivationKeyEmails(
			int licenseKeyExpirationDateOffset)
		throws Exception {

		try {
			Date now = new Date();

			Calendar calendar = Calendar.getInstance();

			calendar.add(Calendar.DATE, licenseKeyExpirationDateOffset);

			Date expirationDateGT = calendar.getTime();

			calendar.add(Calendar.DATE, 1);

			List<LicenseKey> licenseKeys = _licenseKeyLocalService.search(
				null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, expirationDateGT, calendar.getTime(),
				new LinkedHashMap<String, Object>(), false, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

			for (LicenseKey licenseKey : licenseKeys) {
				if (!licenseKey.isActive()) {
					return;
				}

				if (greaterThanDays == 30) {
					Date licenseKeyExpirationDate =
						licenseKey.getExpirationDate();

					calendar.setTime(now);
					calendar.add(Calendar.DATE, 60);

					if (licenseKeyExpirationDate.before(calendar.getTime())) {
						return;
					}
				}

				if (licenseKey.getAccountKey() == null) {
					return;
				}

				Account account = _accountWebService.fetchAccount(
					licenseKey.getAccountKey());

				if (account == null) {
					return;
				}

				long classNameId = _classNameLocalService.getClassNameId(
					LicenseKey.class);

				List<SubscriptionEntry> subscriptionEntries =
					_subscriptionEntryLocalService.getSubscriptionEntries(
						classNameId, licenseKey.getLicenseKeyId());

				for (SubscriptionEntry subscriptionEntry :
						subscriptionEntries) {

					Contact contact = _contactWebService.fetchContactByUuid(
						subscriptionEntry.getContactUuid());

					_customerPortalRelease.sendContactAccountActivationKeyEmail(
						contact, account, licenseKey);
				}
			}
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RuntimeException(e);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LicenseKeySubscriptionMessageListener.class);

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private ContactWebService _contactWebService;

	@Reference
	private CustomerPortalRelease _customerPortalRelease;

	@Reference
	private LicenseKeyLocalService _licenseKeyLocalService;

	@Reference
	private SchedulerEngineHelper _schedulerEngineHelper;

	@Reference
	private SubscriptionEntryLocalService _subscriptionEntryLocalService;

	@Reference
	private TriggerFactory _triggerFactory;

}