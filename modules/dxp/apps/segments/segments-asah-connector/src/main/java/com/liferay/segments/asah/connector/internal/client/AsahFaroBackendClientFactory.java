/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.segments.asah.connector.internal.client;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.PortalPreferencesLocalService;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.segments.asah.connector.internal.util.AsahUtil;

import java.util.Optional;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Sarai Díaz
 */
@Component(immediate = true, service = AsahFaroBackendClientFactory.class)
public class AsahFaroBackendClientFactory {

	public Optional<AsahFaroBackendClient> createAsahFaroBackendClient() {
		Company company = _companyLocalService.fetchCompany(
			_portal.getDefaultCompanyId());

		if (!AsahUtil.isAnalyticsEnabled(company.getCompanyId())) {
			if (_log.isInfoEnabled()) {
				_log.info("Unable to configure Asah Faro backend client");
			}

			return Optional.empty();
		}

		return Optional.of(
			new AsahFaroBackendClientImpl(
				_jsonWebServiceClient,
				AsahUtil.getAsahFaroBackendDataSourceId(company.getCompanyId()),
				AsahUtil.getAsahFaroBackendSecuritySignature(
					company.getCompanyId()),
				AsahUtil.getAsahFaroBackendURL(company.getCompanyId()),
				AsahUtil.getAsahProjectId(company.getCompanyId())));
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AsahFaroBackendClientFactory.class);

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference
	private JSONWebServiceClient _jsonWebServiceClient;

	@Reference
	private Portal _portal;

	@Reference
	private PortalPreferencesLocalService _portalPreferencesLocalService;

}