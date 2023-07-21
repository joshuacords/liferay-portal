/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.web.internal.display.context;

import java.util.Optional;

/**
 * @author André de Oliveira
 */
public interface SearchResultPreferences {

	public Optional<String> getFieldsToDisplayOptional();

	public boolean isDisplayResultsInDocumentForm();

	public boolean isHighlightEnabled();

	public boolean isViewInContext();

}