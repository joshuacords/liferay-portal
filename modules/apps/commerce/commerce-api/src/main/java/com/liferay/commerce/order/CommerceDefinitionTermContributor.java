/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.order;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Luca Pellizzon
 */
@ProviderType
public interface CommerceDefinitionTermContributor {

	public Map<String, String> getDefinitionTerms(Locale locale);

	public String getFilledTerm(String term, Object object, Locale locale)
		throws PortalException;

	public String getLabel(String term, Locale locale);

	public List<String> getTerms();

}