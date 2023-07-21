/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.internal.configuration;

import com.liferay.commerce.product.configuration.CPDefinitionLinkTypeSettings;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = CPDefinitionLinkTypeSettings.class)
public class CPDefinitionLinkTypeSettingsImpl
	implements CPDefinitionLinkTypeSettings {

	@Override
	public String[] getTypes() {
		return ArrayUtil.toStringArray(
			_cpDefinitionLinkTypeConfigurationWrappers.keySet());
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected void addCPDefinitionLinkTypeConfigurationWrapper(
		CPDefinitionLinkTypeConfigurationWrapper
			cpDefinitionLinkTypeConfigurationWrapper) {

		_cpDefinitionLinkTypeConfigurationWrappers.put(
			cpDefinitionLinkTypeConfigurationWrapper.getType(),
			cpDefinitionLinkTypeConfigurationWrapper);
	}

	protected CPDefinitionLinkTypeConfigurationWrapper
		getCPDefinitionLinkTypeConfigurationWrapper(String type) {

		CPDefinitionLinkTypeConfigurationWrapper
			cpDefinitionLinkTypeConfigurationWrapper =
				_cpDefinitionLinkTypeConfigurationWrappers.get(type);

		if (cpDefinitionLinkTypeConfigurationWrapper == null) {
			_log.error("Unable to get cpDefinitionLink type: " + type);
		}

		return cpDefinitionLinkTypeConfigurationWrapper;
	}

	protected void removeCPDefinitionLinkTypeConfigurationWrapper(
		CPDefinitionLinkTypeConfigurationWrapper
			cpDefinitionLinkTypeConfigurationWrapper) {

		_cpDefinitionLinkTypeConfigurationWrappers.remove(
			cpDefinitionLinkTypeConfigurationWrapper.getType());
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CPDefinitionLinkTypeSettingsImpl.class);

	private final Map<String, CPDefinitionLinkTypeConfigurationWrapper>
		_cpDefinitionLinkTypeConfigurationWrappers = new ConcurrentHashMap<>();

}