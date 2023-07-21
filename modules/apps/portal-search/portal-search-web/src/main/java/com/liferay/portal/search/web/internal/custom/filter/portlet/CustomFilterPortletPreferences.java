/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.web.internal.custom.filter.portlet;

import java.util.Optional;

/**
 * @author Igor Nazar
 * @author Luan Maoski
 */
public interface CustomFilterPortletPreferences {

	public static final String PREFERENCE_KEY_BOOST = "boost";

	public static final String PREFERENCE_KEY_CUSTOM_HEADING = "customHeading";

	public static final String PREFERENCE_KEY_DISABLED = "disabled";

	public static final String PREFERENCE_KEY_FEDERATED_SEARCH_KEY =
		"federatedSearchKey";

	public static final String PREFERENCE_KEY_FILTER_FIELD = "filterField";

	public static final String PREFERENCE_KEY_FILTER_QUERY_TYPE =
		"filterQueryType";

	public static final String PREFERENCE_KEY_FILTER_VALUE = "filterValue";

	public static final String PREFERENCE_KEY_IMMUTABLE = "immutable";

	public static final String PREFERENCE_KEY_INVISIBLE = "invisible";

	public static final String PREFERENCE_KEY_OCCUR = "occur";

	public static final String PREFERENCE_KEY_PARAMETER_NAME = "parameterName";

	public static final String PREFERENCE_KEY_PARENT_QUERY_NAME =
		"parentQueryName";

	public static final String PREFERENCE_KEY_QUERY_NAME = "queryName";

	public Optional<String> getBoostOptional();

	public String getBoostString();

	public Optional<String> getCustomHeadingOptional();

	public String getCustomHeadingString();

	public Optional<String> getFederatedSearchKeyOptional();

	public String getFederatedSearchKeyString();

	public Optional<String> getFilterFieldOptional();

	public String getFilterFieldString();

	public String getFilterQueryType();

	public Optional<String> getFilterValueOptional();

	public String getFilterValueString();

	public String getOccur();

	public Optional<String> getParameterNameOptional();

	public String getParameterNameString();

	public Optional<String> getParentQueryNameOptional();

	public String getParentQueryNameString();

	public Optional<String> getQueryNameOptional();

	public String getQueryNameString();

	public boolean isDisabled();

	public boolean isImmutable();

	public boolean isInvisible();

}