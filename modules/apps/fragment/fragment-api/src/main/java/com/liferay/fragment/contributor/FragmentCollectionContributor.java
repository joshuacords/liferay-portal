/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.contributor;

import aQute.bnd.annotation.ProviderType;

import com.liferay.fragment.model.FragmentEntry;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.liferay.portal.kernel.util.ResourceBundleLoaderUtil;

import java.util.List;
import java.util.Locale;

/**
 * @author Jürgen Kappler
 */
@ProviderType
public interface FragmentCollectionContributor {

	public String getFragmentCollectionKey();

	public List<FragmentEntry> getFragmentEntries(int type);

	public default List<FragmentEntry> getFragmentEntries(
		int type, Locale locale) {

		return getFragmentEntries(type);
	}

	public String getName();

	public default String getName(Locale locale) {
		return getName();
	}

	public default ResourceBundleLoader getResourceBundleLoader() {
		return ResourceBundleLoaderUtil.getPortalResourceBundleLoader();
	}

}