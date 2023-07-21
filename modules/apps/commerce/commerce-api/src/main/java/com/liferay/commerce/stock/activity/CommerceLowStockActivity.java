/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.stock.activity;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.product.model.CPInstance;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.Locale;

/**
 * @author Alessio Antonio Rendina
 * @author Luca Pellizzon
 */
@ProviderType
public interface CommerceLowStockActivity {

	public void execute(CPInstance cpInstance) throws PortalException;

	public String getKey();

	public String getLabel(Locale locale);

}