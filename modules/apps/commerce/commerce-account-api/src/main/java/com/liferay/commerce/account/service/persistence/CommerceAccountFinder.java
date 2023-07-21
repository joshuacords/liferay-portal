/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.account.service.persistence;

import aQute.bnd.annotation.ProviderType;

/**
 * @author Marco Leo
 * @generated
 */
@ProviderType
public interface CommerceAccountFinder {

	public int countByU_P(
		java.util.List<Long> organizationIds, long userId,
		com.liferay.portal.kernel.dao.orm.QueryDefinition
			<com.liferay.commerce.account.model.CommerceAccount>
				queryDefinition);

	public java.util.List<com.liferay.commerce.account.model.CommerceAccount>
		findByU_P(
			java.util.List<Long> organizationIds, long userId,
			com.liferay.portal.kernel.dao.orm.QueryDefinition
				<com.liferay.commerce.account.model.CommerceAccount>
					queryDefinition);

	public com.liferay.commerce.account.model.CommerceAccount findByU_C(
		java.util.List<Long> organizationIds, long userId,
		long commerceAccountId);

}