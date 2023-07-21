/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.portlet.configuration.icon;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.registry.ServiceReference;
import com.liferay.registry.collections.ServiceReferenceMapper;
import com.liferay.registry.util.StringPlus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eudaldo Alonso
 */
public class PortletConfigurationIconServiceReferenceMapper
	implements ServiceReferenceMapper<String, PortletConfigurationIcon> {

	@Override
	public void map(
		ServiceReference<PortletConfigurationIcon> serviceReference,
		Emitter<String> emitter) {

		String portletId = (String)serviceReference.getProperty(
			"javax.portlet.name");

		if (Validator.isNull(portletId)) {
			portletId = StringPool.STAR;
		}

		List<String> paths = StringPlus.asList(
			serviceReference.getProperty("path"));

		if (ListUtil.isEmpty(paths)) {
			paths = new ArrayList<>();

			paths.add(StringPool.DASH);
		}

		for (String path : paths) {
			emitter.emit(getKey(portletId, path));
		}
	}

	protected String getKey(String portletId, String path) {
		return portletId + StringPool.COLON + path;
	}

}