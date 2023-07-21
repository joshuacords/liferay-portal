/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.vldap.server.internal.internal.instance.lifecycle;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.model.ExpandoColumnConstants;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.instance.lifecycle.BasePortalInstanceLifecycleListener;
import com.liferay.portal.instance.lifecycle.PortalInstanceLifecycleListener;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.UnicodeProperties;

import org.osgi.service.component.annotations.Component;

/**
 * @author Jonathan McCann
 */
@Component(immediate = true, service = PortalInstanceLifecycleListener.class)
public class AddSambaExpandoBridgePortalInstanceLifecycleListener
	extends BasePortalInstanceLifecycleListener {

	@Override
	public void portalInstanceRegistered(Company company)
		throws PortalException {

		ExpandoBridge expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(
			company.getCompanyId(), User.class.getName());

		if (!expandoBridge.hasAttribute(_SAMBA_LM_PASSWORD)) {
			expandoBridge.addAttribute(_SAMBA_LM_PASSWORD, false);
		}

		if (!expandoBridge.hasAttribute(_SAMBA_NT_PASSWORD)) {
			expandoBridge.addAttribute(_SAMBA_NT_PASSWORD, false);
		}

		UnicodeProperties properties = new UnicodeProperties();

		properties.put(ExpandoColumnConstants.PROPERTY_HIDDEN, StringPool.TRUE);

		expandoBridge.setAttributeProperties(
			_SAMBA_LM_PASSWORD, properties, false);
		expandoBridge.setAttributeProperties(
			_SAMBA_NT_PASSWORD, properties, false);
	}

	private static final String _SAMBA_LM_PASSWORD = "sambaLMPassword";

	private static final String _SAMBA_NT_PASSWORD = "sambaNTPassword";

}