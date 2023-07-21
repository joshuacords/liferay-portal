/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.exportimport.internal.portlet.data.handler.provider;

import com.liferay.exportimport.kernel.lar.PortletDataHandler;
import com.liferay.exportimport.portlet.data.handler.provider.PortletDataHandlerProvider;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.service.PortletLocalService;
import com.liferay.portal.kernel.util.Validator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Máté Thurzó
 */
@Component(immediate = true, service = PortletDataHandlerProvider.class)
public class PortletDataHandlerProviderImpl
	implements PortletDataHandlerProvider {

	@Override
	public PortletDataHandler provide(long companyId, String portletId) {
		if ((companyId <= 0) || Validator.isNull(portletId)) {
			return null;
		}

		Portlet portlet = _portletLocalService.getPortletById(
			companyId, portletId);

		return doProvide(portlet);
	}

	@Override
	public PortletDataHandler provide(Portlet portlet) {
		return doProvide(portlet);
	}

	@Override
	public PortletDataHandler provide(String portletId) {
		if (Validator.isNull(portletId)) {
			return null;
		}

		Portlet portlet = _portletLocalService.getPortletById(portletId);

		return doProvide(portlet);
	}

	protected PortletDataHandler doProvide(Portlet portlet) {
		if ((portlet == null) || !portlet.isActive() ||
			portlet.isUndeployedPortlet()) {

			return null;
		}

		try {
			return portlet.getPortletDataHandlerInstance();
		}
		catch (Exception exception) {
			return null;
		}
	}

	@Reference
	private PortletLocalService _portletLocalService;

}