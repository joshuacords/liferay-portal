/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.marketplace.store.web.internal.oauth.util;

import com.liferay.expando.kernel.exception.DuplicateColumnNameException;
import com.liferay.expando.kernel.exception.DuplicateTableNameException;
import com.liferay.expando.kernel.model.ExpandoColumnConstants;
import com.liferay.expando.kernel.model.ExpandoTable;
import com.liferay.expando.kernel.model.ExpandoValue;
import com.liferay.expando.kernel.service.ExpandoColumnLocalService;
import com.liferay.expando.kernel.service.ExpandoTableLocalService;
import com.liferay.expando.kernel.service.ExpandoValueLocalService;
import com.liferay.marketplace.store.web.internal.configuration.MarketplaceStoreWebConfigurationValues;
import com.liferay.marketplace.store.web.internal.oauth.api.MarketplaceApi;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.service.CompanyLocalService;

import java.util.List;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import org.scribe.builder.api.Api;
import org.scribe.model.OAuthConfig;
import org.scribe.model.SignatureType;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

/**
 * @author Ryan Park
 */
@Component(immediate = true, service = OAuthManager.class)
public class OAuthManager {

	public void deleteAccessToken(User user) throws PortalException {
		_expandoValueLocalService.deleteValue(
			user.getCompanyId(), User.class.getName(), "MP", "accessSecret",
			user.getUserId());
		_expandoValueLocalService.deleteValue(
			user.getCompanyId(), User.class.getName(), "MP", "accessToken",
			user.getUserId());
	}

	public void deleteRequestToken(User user) throws PortalException {
		_expandoValueLocalService.deleteValue(
			user.getCompanyId(), User.class.getName(), "MP", "requestSecret",
			user.getUserId());
		_expandoValueLocalService.deleteValue(
			user.getCompanyId(), User.class.getName(), "MP", "requestToken",
			user.getUserId());
	}

	public Token getAccessToken(User user) throws PortalException {
		ExpandoValue secretExpandoValue = _expandoValueLocalService.getValue(
			user.getCompanyId(), User.class.getName(), "MP", "accessSecret",
			user.getUserId());
		ExpandoValue tokenExpandoValue = _expandoValueLocalService.getValue(
			user.getCompanyId(), User.class.getName(), "MP", "accessToken",
			user.getUserId());

		if ((secretExpandoValue == null) || (tokenExpandoValue == null)) {
			return null;
		}

		return new Token(
			tokenExpandoValue.getString(), secretExpandoValue.getString());
	}

	public OAuthService getOAuthService() {
		Api api = new MarketplaceApi();

		OAuthConfig oAuthConfig = new OAuthConfig(
			MarketplaceStoreWebConfigurationValues.MARKETPLACE_KEY,
			MarketplaceStoreWebConfigurationValues.MARKETPLACE_SECRET,
			MarketplaceStoreWebConfigurationValues.MARKETPLACE_URL,
			SignatureType.Header, null, null);

		return api.createService(oAuthConfig);
	}

	public Token getRequestToken(User user) throws PortalException {
		ExpandoValue secretExpandoValue = _expandoValueLocalService.getValue(
			user.getCompanyId(), User.class.getName(), "MP", "requestSecret",
			user.getUserId());
		ExpandoValue tokenExpandoValue = _expandoValueLocalService.getValue(
			user.getCompanyId(), User.class.getName(), "MP", "requestToken",
			user.getUserId());

		if ((secretExpandoValue == null) || (tokenExpandoValue == null)) {
			return null;
		}

		return new Token(
			tokenExpandoValue.getString(), secretExpandoValue.getString());
	}

	public void updateAccessToken(User user, Token token)
		throws PortalException {

		_expandoValueLocalService.addValue(
			user.getCompanyId(), User.class.getName(), "MP", "accessSecret",
			user.getUserId(), token.getSecret());
		_expandoValueLocalService.addValue(
			user.getCompanyId(), User.class.getName(), "MP", "accessToken",
			user.getUserId(), token.getToken());
	}

	public void updateRequestToken(User user, Token token)
		throws PortalException {

		_expandoValueLocalService.addValue(
			user.getCompanyId(), User.class.getName(), "MP", "requestSecret",
			user.getUserId(), token.getSecret());
		_expandoValueLocalService.addValue(
			user.getCompanyId(), User.class.getName(), "MP", "requestToken",
			user.getUserId(), token.getToken());
	}

	@Activate
	protected void activate() {
		List<Company> companys = _companyLocalService.getCompanies();

		for (Company company : companys) {
			try {
				setupExpando(company.getCompanyId());
			}
			catch (Exception exception) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						StringBundler.concat(
							"Unable to setup Marketplace for company ",
							company.getCompanyId(), ": ",
							exception.getMessage()));
				}
			}
		}
	}

	@Reference(unbind = "-")
	protected void setCompanyLocalService(
		CompanyLocalService companyLocalService) {

		_companyLocalService = companyLocalService;
	}

	@Reference(unbind = "-")
	protected void setExpandoColumnLocalService(
		ExpandoColumnLocalService expandoColumnLocalService) {

		_expandoColumnLocalService = expandoColumnLocalService;
	}

	@Reference(unbind = "-")
	protected void setExpandoTableLocalService(
		ExpandoTableLocalService expandoTableLocalService) {

		_expandoTableLocalService = expandoTableLocalService;
	}

	@Reference(unbind = "-")
	protected void setExpandoValueLocalService(
		ExpandoValueLocalService expandoValueLocalService) {

		_expandoValueLocalService = expandoValueLocalService;
	}

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	protected void setupExpando(long companyId) throws Exception {
		ExpandoTable table = null;

		try {
			table = _expandoTableLocalService.addTable(
				companyId, User.class.getName(), "MP");
		}
		catch (DuplicateTableNameException duplicateTableNameException) {

			// LPS-52675

			if (_log.isDebugEnabled()) {
				_log.debug(
					duplicateTableNameException, duplicateTableNameException);
			}

			table = _expandoTableLocalService.getTable(
				companyId, User.class.getName(), "MP");
		}

		try {
			_expandoColumnLocalService.addColumn(
				table.getTableId(), "accessSecret",
				ExpandoColumnConstants.STRING);
			_expandoColumnLocalService.addColumn(
				table.getTableId(), "accessToken",
				ExpandoColumnConstants.STRING);
			_expandoColumnLocalService.addColumn(
				table.getTableId(), "requestSecret",
				ExpandoColumnConstants.STRING);
			_expandoColumnLocalService.addColumn(
				table.getTableId(), "requestToken",
				ExpandoColumnConstants.STRING);
		}
		catch (DuplicateColumnNameException duplicateColumnNameException) {

			// LPS-52675

			if (_log.isDebugEnabled()) {
				_log.debug(
					duplicateColumnNameException, duplicateColumnNameException);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(OAuthManager.class);

	private CompanyLocalService _companyLocalService;
	private ExpandoColumnLocalService _expandoColumnLocalService;
	private ExpandoTableLocalService _expandoTableLocalService;
	private ExpandoValueLocalService _expandoValueLocalService;

}