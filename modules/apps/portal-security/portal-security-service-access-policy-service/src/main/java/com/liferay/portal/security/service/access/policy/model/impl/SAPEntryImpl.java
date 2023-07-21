/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.service.access.policy.model.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProviderUtil;
import com.liferay.portal.kernel.settings.CompanyServiceSettingsLocator;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.security.service.access.policy.configuration.SAPConfiguration;
import com.liferay.portal.security.service.access.policy.constants.SAPConstants;

import java.util.List;
import java.util.Objects;

/**
 * @author Brian Wing Shun Chan
 */
public class SAPEntryImpl extends SAPEntryBaseImpl {

	@Override
	public List<String> getAllowedServiceSignaturesList() {
		String[] allowedServiceSignatures = StringUtil.split(
			getAllowedServiceSignatures(), StringPool.NEW_LINE);

		return ListUtil.fromArray(allowedServiceSignatures);
	}

	@Override
	public boolean isSystem() throws ConfigurationException {
		SAPConfiguration sapConfiguration =
			ConfigurationProviderUtil.getConfiguration(
				SAPConfiguration.class,
				new CompanyServiceSettingsLocator(
					getCompanyId(), SAPConstants.SERVICE_NAME));

		if (Objects.equals(
				getName(), sapConfiguration.systemDefaultSAPEntryName())) {

			return true;
		}

		if (Objects.equals(
				getName(), sapConfiguration.systemUserPasswordSAPEntryName())) {

			return true;
		}

		return false;
	}

}