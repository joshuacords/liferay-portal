/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.info.list.provider;

import com.liferay.info.display.contributor.InfoDisplayObjectProvider;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;

import java.util.Optional;

/**
 * @author Jorge Ferrer
 */
public class DefaultInfoListProviderContext implements InfoListProviderContext {

	public DefaultInfoListProviderContext(Company company, User user) {
		_company = company;
		_user = user;
	}

	public DefaultInfoListProviderContext(Group group, User user) {
		_group = group;
		_user = user;

		try {
			_company = CompanyLocalServiceUtil.getCompany(group.getCompanyId());
		}
		catch (PortalException portalException) {
			throw new RuntimeException(portalException);
		}
	}

	@Override
	public Company getCompany() {
		return _company;
	}

	@Override
	public Optional<Group> getGroupOptional() {
		return Optional.of(_group);
	}

	@Override
	public Optional<InfoDisplayObjectProvider>
		getInfoDisplayObjectProviderOptional() {

		return Optional.ofNullable(_infoDisplayObjectProvider);
	}

	@Override
	public Optional<Layout> getLayoutOptional() {
		return Optional.of(_layout);
	}

	@Override
	public User getUser() {
		return _user;
	}

	public void setInfoDisplayObjectProvider(
		InfoDisplayObjectProvider infoDisplayObjectProvider) {

		_infoDisplayObjectProvider = infoDisplayObjectProvider;
	}

	public void setLayout(Layout layout) {
		_layout = layout;
	}

	private final Company _company;
	private Group _group;
	private InfoDisplayObjectProvider _infoDisplayObjectProvider;
	private Layout _layout;
	private final User _user;

}