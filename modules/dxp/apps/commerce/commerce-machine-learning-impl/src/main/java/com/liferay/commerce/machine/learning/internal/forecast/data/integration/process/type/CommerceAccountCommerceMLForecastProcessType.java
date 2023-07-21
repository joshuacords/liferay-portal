/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.machine.learning.internal.forecast.data.integration.process.type;

import com.liferay.commerce.data.integration.process.type.ProcessType;
import com.liferay.commerce.machine.learning.internal.data.integration.BaseCommerceMLProcessType;

import org.osgi.service.component.annotations.Component;

/**
 * @author Riccardo Ferrari
 */
@Component(
	immediate = true,
	property = {
		"commerce.data.integration.process.type.key=" + CommerceAccountCommerceMLForecastProcessType.KEY,
		"commerce.data.integration.process.type.order=100"
	},
	service = ProcessType.class
)
public class CommerceAccountCommerceMLForecastProcessType
	extends BaseCommerceMLProcessType {

	public static final String KEY = "commerce-account-commerce-ml-forecast";

	@Override
	public String getKey() {
		return KEY;
	}

}