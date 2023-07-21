/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.account.model.impl;

import com.liferay.commerce.account.constants.CommerceAccountConstants;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.model.CommerceAccountOrganizationRel;
import com.liferay.commerce.account.model.CommerceAccountUserRel;
import com.liferay.commerce.account.service.CommerceAccountLocalServiceUtil;
import com.liferay.commerce.account.service.CommerceAccountOrganizationRelLocalServiceUtil;
import com.liferay.commerce.account.service.CommerceAccountUserRelLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;

import java.util.List;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
public class CommerceAccountImpl extends CommerceAccountBaseImpl {

	public CommerceAccountImpl() {
	}

	@Override
	public Group getCommerceAccountGroup() throws PortalException {
		return CommerceAccountLocalServiceUtil.getCommerceAccountGroup(
			getCommerceAccountId());
	}

	@Override
	public long getCommerceAccountGroupId() throws PortalException {
		Group group = CommerceAccountLocalServiceUtil.getCommerceAccountGroup(
			getCommerceAccountId());

		return group.getGroupId();
	}

	@Override
	public List<CommerceAccountOrganizationRel>
		getCommerceAccountOrganizationRels() {

		return CommerceAccountOrganizationRelLocalServiceUtil.
			getCommerceAccountOrganizationRels(getCommerceAccountId());
	}

	@Override
	public List<CommerceAccountUserRel> getCommerceAccountUserRels() {
		return CommerceAccountUserRelLocalServiceUtil.
			getCommerceAccountUserRels(getCommerceAccountId());
	}

	@Override
	public CommerceAccount getParentCommerceAccount() throws PortalException {
		if (isRoot()) {
			return null;
		}

		return CommerceAccountLocalServiceUtil.getCommerceAccount(
			getParentCommerceAccountId());
	}

	@Override
	public boolean isBusinessAccount() {
		if (getType() == CommerceAccountConstants.ACCOUNT_TYPE_BUSINESS) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isPersonalAccount() {
		if (getType() == CommerceAccountConstants.ACCOUNT_TYPE_PERSONAL) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isRoot() {
		if (getParentCommerceAccountId() ==
				CommerceAccountConstants.DEFAULT_PARENT_ACCOUNT_ID) {

			return true;
		}

		return false;
	}

}