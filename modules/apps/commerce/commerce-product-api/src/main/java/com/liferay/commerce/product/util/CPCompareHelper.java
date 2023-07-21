/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.util;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

import javax.servlet.http.HttpSession;

/**
 * @author Alec Sloan
 */
@ProviderType
public interface CPCompareHelper {

	public void addCompareProduct(
			long groupId, long commerceAccountId, long cpDefinitionId,
			HttpSession httpSession)
		throws PortalException;

	public List<Long> getCPDefinitionIds(
			long groupId, long commerceAccountId, HttpSession httpSession)
		throws PortalException;

	public void removeCompareProduct(
			long groupId, long commerceAccountId, long cpDefinitionId,
			HttpSession httpSession)
		throws PortalException;

	public void setCPDefinitionIds(
		long groupId, List<Long> cpDefinitionIds, HttpSession httpSession);

}