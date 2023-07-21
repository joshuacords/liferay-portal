/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.contributor;

import com.liferay.fragment.model.FragmentEntry;
import com.liferay.portal.kernel.util.ResourceBundleLoader;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Jürgen Kappler
 */
@ProviderType
public interface FragmentCollectionContributorTracker {

	public FragmentCollectionContributor getFragmentCollectionContributor(
		String fragmentCollectionKey);

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by #getFragmentEntries
	 */
	@Deprecated
	public Map<String, FragmentEntry> getFragmentCollectionContributorEntries();

	public List<FragmentCollectionContributor>
		getFragmentCollectionContributors();

	public Map<String, FragmentEntry> getFragmentEntries();

	public default Map<String, FragmentEntry> getFragmentEntries(
		Locale locale) {

		return getFragmentEntries();
	}

	public FragmentEntry getFragmentEntry(String fragmentEntryKey);

	public ResourceBundleLoader getResourceBundleLoader();

}