/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.model.impl;

import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.service.CommerceOrderItemLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.UnicodeProperties;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceSubscriptionEntryImpl
	extends CommerceSubscriptionEntryBaseImpl {

	public CommerceSubscriptionEntryImpl() {
	}

	@Override
	public CommerceOrderItem fetchCommerceOrderItem() {
		return CommerceOrderItemLocalServiceUtil.fetchCommerceOrderItem(
			getCommerceOrderItemId());
	}

	@Override
	public CPDefinition fetchCPDefinition() throws PortalException {
		CommerceOrderItem commerceOrderItem = fetchCommerceOrderItem();

		if (commerceOrderItem == null) {
			return null;
		}

		return commerceOrderItem.getCPDefinition();
	}

	@Override
	public CPInstance fetchCPInstance() {
		CommerceOrderItem commerceOrderItem = fetchCommerceOrderItem();

		if (commerceOrderItem == null) {
			return null;
		}

		return commerceOrderItem.fetchCPInstance();
	}

	@Override
	public long getCPDefinitionId() throws PortalException {
		CPDefinition cpDefinition = fetchCPDefinition();

		if (cpDefinition == null) {
			return 0;
		}

		return cpDefinition.getCPDefinitionId();
	}

	@Override
	public long getCPInstanceId() {
		CPInstance cpInstance = fetchCPInstance();

		if (cpInstance == null) {
			return 0;
		}

		return cpInstance.getCPInstanceId();
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
	public void setDeliverySubscriptionTypeSettingsProperties(
		UnicodeProperties deliverySubscriptionTypeSettingsProperties) {

		_deliverySubscriptionTypeSettingsProperties =
			deliverySubscriptionTypeSettingsProperties;

		if (_deliverySubscriptionTypeSettingsProperties == null) {
			_deliverySubscriptionTypeSettingsProperties =
				new UnicodeProperties();
		}

		super.setSubscriptionTypeSettings(
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