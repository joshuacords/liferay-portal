/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.context;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.portal.kernel.exception.PortalException;

import java.io.Serializable;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
@ProviderType
public interface CommerceContext extends Serializable {

	public CommerceAccount getCommerceAccount() throws PortalException;

	public long[] getCommerceAccountGroupIds() throws PortalException;

	public long getCommerceChannelGroupId() throws PortalException;

	public long getCommerceChannelId() throws PortalException;

	public CommerceCurrency getCommerceCurrency() throws PortalException;

	public CommerceOrder getCommerceOrder() throws PortalException;

	public int getCommerceSiteType();

}