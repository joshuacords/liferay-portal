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

package com.liferay.osb.provisioning.subscription.service.impl;

import com.liferay.osb.provisioning.subscription.exception.NoSuchSubscriptionEntryException;
import com.liferay.osb.provisioning.subscription.exception.SubscriptionEntryClassNameIdException;
import com.liferay.osb.provisioning.subscription.exception.SubscriptionEntryClassPKException;
import com.liferay.osb.provisioning.subscription.exception.SubscriptionEntryContactUuidException;
import com.liferay.osb.provisioning.subscription.model.SubscriptionEntry;
import com.liferay.osb.provisioning.subscription.service.base.SubscriptionEntryLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Jenny Chen
 */
@Component(
	property = "model.class.name=com.liferay.osb.provisioning.subscription.model.SubscriptionEntry",
	service = AopService.class
)
public class SubscriptionEntryLocalServiceImpl
	extends SubscriptionEntryLocalServiceBaseImpl {

	public SubscriptionEntry addSubscriptionEntry(
			long classNameId, long classPK, String contactUuid)
		throws Exception {

		Date now = new Date();

		validate(classNameId, classPK, contactUuid);

		SubscriptionEntry subscriptionEntry =
			subscriptionEntryPersistence.fetchByC_C_CU(
				classNameId, classPK, contactUuid);

		if (subscriptionEntry != null) {
			return subscriptionEntry;
		}

		long subscriptionEntryId = counterLocalService.increment();

		subscriptionEntry = subscriptionEntryPersistence.create(
			subscriptionEntryId);

		subscriptionEntry.setCreateDate(now);
		subscriptionEntry.setClassNameId(classNameId);
		subscriptionEntry.setClassPK(classPK);
		subscriptionEntry.setContactUuid(contactUuid);

		return subscriptionEntryPersistence.update(subscriptionEntry);
	}

	public void deleteSubscriptionEntry(
			long classNameId, long classPK, String contactUuid)
		throws NoSuchSubscriptionEntryException {

		subscriptionEntryPersistence.removeByC_C_CU(
			classNameId, classPK, contactUuid);
	}

	public List<SubscriptionEntry> getSubscriptionEntries(
		long classNameId, long classPK) {

		return subscriptionEntryPersistence.findByC_C(classNameId, classPK);
	}

	public List<SubscriptionEntry> getSubscriptionEntries(String contactUuid) {
		return subscriptionEntryPersistence.findByContactUuid(contactUuid);
	}

	public SubscriptionEntry getSubscriptionEntry(
			long classNameId, long classPK, String contactUuid)
		throws NoSuchSubscriptionEntryException {

		return subscriptionEntryPersistence.findByC_C_CU(
			classNameId, classPK, contactUuid);
	}

	protected void validate(long classNameId, long classPK, String contactUuid)
		throws Exception {

		if (classNameId <= 0) {
			throw new SubscriptionEntryClassNameIdException();
		}

		if (classPK <= 0) {
			throw new SubscriptionEntryClassPKException();
		}

		if (Validator.isNull(contactUuid)) {
			throw new SubscriptionEntryContactUuidException();
		}
	}

}