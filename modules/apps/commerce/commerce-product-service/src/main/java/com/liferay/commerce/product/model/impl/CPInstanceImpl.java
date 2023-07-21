/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.model.impl;

import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPSubscriptionInfo;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.service.CPDefinitionLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.UnicodeProperties;

/**
 * @author Marco Leo
 * @author Andrea Di Giorgi
 * @author Alessio Antonio Rendina
 * @author Luca Pellizzon
 */
public class CPInstanceImpl extends CPInstanceBaseImpl {

	public CPInstanceImpl() {
	}

	@Override
	public CommerceCatalog getCommerceCatalog() throws PortalException {
		CPDefinition cpDefinition = getCPDefinition();

		return cpDefinition.getCommerceCatalog();
	}

	@Override
	public CPDefinition getCPDefinition() throws PortalException {
		return CPDefinitionLocalServiceUtil.getCPDefinition(
			getCPDefinitionId());
	}

	@Override
	public CPSubscriptionInfo getCPSubscriptionInfo() throws PortalException {
		if (isOverrideSubscriptionInfo() &&
			(isSubscriptionEnabled() || isDeliverySubscriptionEnabled())) {

			return new CPSubscriptionInfo(
				getSubscriptionLength(), getSubscriptionType(),
				getSubscriptionTypeSettingsProperties(),
				getMaxSubscriptionCycles(), getDeliverySubscriptionLength(),
				getDeliverySubscriptionType(),
				getDeliverySubscriptionTypeSettingsProperties(),
				getDeliveryMaxSubscriptionCycles());
		}
		else if (!isOverrideSubscriptionInfo()) {
			CPDefinition cpDefinition = getCPDefinition();

			if (cpDefinition.isSubscriptionEnabled()) {
				return new CPSubscriptionInfo(
					cpDefinition.getSubscriptionLength(),
					cpDefinition.getSubscriptionType(),
					cpDefinition.getSubscriptionTypeSettingsProperties(),
					cpDefinition.getMaxSubscriptionCycles(),
					cpDefinition.getDeliverySubscriptionLength(),
					cpDefinition.getDeliverySubscriptionType(),
					cpDefinition.
						getDeliverySubscriptionTypeSettingsProperties(),
					cpDefinition.getDeliveryMaxSubscriptionCycles());
			}
		}

		return null;
	}

	@Override
	public UnicodeProperties getDeliverySubscriptionTypeSettingsProperties() {
		if (_deliverySubscriptionTypeSettingsProperties == null) {
			_deliverySubscriptionTypeSettingsProperties = new UnicodeProperties(
				true);

			_deliverySubscriptionTypeSettingsProperties.fastLoad(
				getDeliverySubscriptionTypeSettings());
		}

		return _deliverySubscriptionTypeSettingsProperties;
	}

	@Override
	public UnicodeProperties getSubscriptionTypeSettingsProperties() {
		if (_subscriptionTypeSettingsProperties == null) {
			_subscriptionTypeSettingsProperties = new UnicodeProperties(true);

			_subscriptionTypeSettingsProperties.fastLoad(
				getSubscriptionTypeSettings());
		}

		return _subscriptionTypeSettingsProperties;
	}

	@Override
	public void setDeliverySubscriptionTypeSettings(
		String subscriptionTypeSettings) {

		super.setDeliverySubscriptionTypeSettings(subscriptionTypeSettings);

		_deliverySubscriptionTypeSettingsProperties = null;
	}

	@Override
	public void setDeliverySubscriptionTypeSettingsProperties(
		UnicodeProperties deliverySubscriptionTypeSettingsProperties) {

		_deliverySubscriptionTypeSettingsProperties =
			deliverySubscriptionTypeSettingsProperties;

		if (_deliverySubscriptionTypeSettingsProperties == null) {
			_deliverySubscriptionTypeSettingsProperties =
				new UnicodeProperties();
		}

		super.setDeliverySubscriptionTypeSettings(
			_deliverySubscriptionTypeSettingsProperties.toString());
	}

	@Override
	public void setSubscriptionTypeSettings(String subscriptionTypeSettings) {
		super.setSubscriptionTypeSettings(subscriptionTypeSettings);

		_subscriptionTypeSettingsProperties = null;
	}

	@Override
	public void setSubscriptionTypeSettingsProperties(
		UnicodeProperties subscriptionTypeSettingsProperties) {

		_subscriptionTypeSettingsProperties =
			subscriptionTypeSettingsProperties;

		if (_subscriptionTypeSettingsProperties == null) {
			_subscriptionTypeSettingsProperties = new UnicodeProperties();
		}

		super.setSubscriptionTypeSettings(
			_subscriptionTypeSettingsProperties.toString());
	}

	private UnicodeProperties _deliverySubscriptionTypeSettingsProperties;
	private UnicodeProperties _subscriptionTypeSettingsProperties;

}