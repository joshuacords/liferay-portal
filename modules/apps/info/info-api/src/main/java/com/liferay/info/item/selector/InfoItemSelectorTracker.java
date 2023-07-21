/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.info.item.selector;

import java.util.List;
import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Eudaldo Alonso
 */
@ProviderType
public interface InfoItemSelectorTracker {

	public InfoItemSelector getInfoItemSelector(String key);

	public List<InfoItemSelector> getInfoItemSelectors();

	public List<InfoItemSelector> getInfoItemSelectors(String itemClassName);

	public Set<String> getInfoItemSelectorsClassNames();

}