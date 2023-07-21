/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phytohormone.web.internal.portlet;

import com.liferay.osb.koroneiki.root.service.ExternalLinkLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
public abstract class BaseAdminPortlet extends MVCPortlet {

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException {

		try {
			String resourceID = resourceRequest.getResourceID();

			if (resourceID.equals("externalLinkDomains")) {
				serveExternalLinkDomains(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("externalLinkEntityNames")) {
				serveExternalLinkEntityNames(resourceRequest, resourceResponse);
			}
		}
		catch (Exception exception) {
			throw new PortletException(exception);
		}
	}

	protected void serveExternalLinkDomains(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException {

		String domain = ParamUtil.getString(resourceRequest, "domain");

		if (Validator.isNotNull(domain)) {
			if (domain.length() == 1) {
				domain += StringPool.PERCENT;
			}
			else {
				domain = StringPool.PERCENT + domain + StringPool.PERCENT;
			}

			List<String> domains = externalLinkLocalService.search(domain);

			JSONArray domainsArray = JSONFactoryUtil.createJSONArray();

			for (String curDomain : domains) {
				domainsArray.put(curDomain);
			}

			writeJSON(resourceRequest, resourceResponse, domainsArray);
		}
	}

	protected void serveExternalLinkEntityNames(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException {

		String entityName = ParamUtil.getString(resourceRequest, "entityName");

		if (Validator.isNotNull(entityName)) {
			if (entityName.length() == 1) {
				entityName += StringPool.PERCENT;
			}
			else {
				entityName =
					StringPool.PERCENT + entityName + StringPool.PERCENT;
			}

			String domain = ParamUtil.getString(resourceRequest, "domain");

			List<String> entityNames = externalLinkLocalService.search(
				domain, entityName);

			JSONArray entityNamesArray = JSONFactoryUtil.createJSONArray();

			for (String curEntityName : entityNames) {
				entityNamesArray.put(curEntityName);
			}

			writeJSON(resourceRequest, resourceResponse, entityNamesArray);
		}
	}

	@Reference
	protected ExternalLinkLocalService externalLinkLocalService;

}