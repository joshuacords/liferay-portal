/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the CPFriendlyURLEntry service. Represents a row in the &quot;CPFriendlyURLEntry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Marco Leo
 * @see CPFriendlyURLEntryModel
 * @deprecated As of Athanasius (7.3.x), replaced by {@link
 com.liferay.friendly.url.model.impl.FriendlyURLEntryImpl}
 * @generated
 */
@Deprecated
@ImplementationClassName(
	"com.liferay.commerce.product.model.impl.CPFriendlyURLEntryImpl"
)
@ProviderType
public interface CPFriendlyURLEntry
	extends CPFriendlyURLEntryModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.commerce.product.model.impl.CPFriendlyURLEntryImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CPFriendlyURLEntry, Long>
		CP_FRIENDLY_URL_ENTRY_ID_ACCESSOR =
			new Accessor<CPFriendlyURLEntry, Long>() {

				@Override
				public Long get(CPFriendlyURLEntry cpFriendlyURLEntry) {
					return cpFriendlyURLEntry.getCPFriendlyURLEntryId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<CPFriendlyURLEntry> getTypeClass() {
					return CPFriendlyURLEntry.class;
				}

			};

	public java.util.Locale getLocale();

}