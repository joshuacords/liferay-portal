/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.model.view;

import java.io.Serializable;

/**
 * @author Kyle Bischof
 */
public interface ProductPurchaseView {

	public long getAccountId();

	public long getCompanyId();

	public Class<?> getModelClass();

	public String getModelClassName();

	public Serializable getPrimaryKeyObj();

	public long getProductEntryId();

	public void setAccountId(long accountId);

	public void setCompanyId(long companyId);

	public void setProductEntryId(long productEntryId);

}