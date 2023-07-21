/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.payment.method.money.order.internal.configuration.definition;

import com.liferay.commerce.payment.method.money.order.internal.configuration.MoneyOrderGroupServiceConfiguration;
import com.liferay.portal.kernel.settings.definition.ConfigurationBeanDeclaration;

import org.osgi.service.component.annotations.Component;

/**
 * @author Andrea Di Giorgi
 */
@Component(service = ConfigurationBeanDeclaration.class)
public class
	MoneyOrderCommercePaymentEngineGroupServiceConfigurationBeanDeclaration
		implements ConfigurationBeanDeclaration {

	@Override
	public Class<?> getConfigurationBeanClass() {
		return MoneyOrderGroupServiceConfiguration.class;
	}

}