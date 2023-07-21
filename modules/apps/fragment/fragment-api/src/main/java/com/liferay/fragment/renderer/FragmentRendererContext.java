/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.renderer;

import com.liferay.fragment.model.FragmentEntryLink;

import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Jorge Ferrer
 */
@ProviderType
public interface FragmentRendererContext {

	public Optional<Map<String, Object>> getFieldValuesOptional();

	public FragmentEntryLink getFragmentEntryLink();

	public Locale getLocale();

	public String getMode();

	public long getPreviewClassPK();

	public int getPreviewType();

	public long[] getSegmentsExperienceIds();

}