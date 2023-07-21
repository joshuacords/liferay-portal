/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.html.preview.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the HtmlPreviewEntry service. Represents a row in the &quot;HtmlPreviewEntry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see HtmlPreviewEntryModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.html.preview.model.impl.HtmlPreviewEntryImpl"
)
@ProviderType
public interface HtmlPreviewEntry
	extends HtmlPreviewEntryModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.html.preview.model.impl.HtmlPreviewEntryImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<HtmlPreviewEntry, Long>
		HTML_PREVIEW_ENTRY_ID_ACCESSOR =
			new Accessor<HtmlPreviewEntry, Long>() {

				@Override
				public Long get(HtmlPreviewEntry htmlPreviewEntry) {
					return htmlPreviewEntry.getHtmlPreviewEntryId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<HtmlPreviewEntry> getTypeClass() {
					return HtmlPreviewEntry.class;
				}

			};

	public String getImagePreviewURL(
		com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay);

}