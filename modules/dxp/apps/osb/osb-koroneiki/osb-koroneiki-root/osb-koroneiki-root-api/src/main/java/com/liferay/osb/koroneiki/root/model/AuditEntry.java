/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.root.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the AuditEntry service. Represents a row in the &quot;Koroneiki_AuditEntry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see AuditEntryModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.osb.koroneiki.root.model.impl.AuditEntryImpl"
)
@ProviderType
public interface AuditEntry extends AuditEntryModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.osb.koroneiki.root.model.impl.AuditEntryImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<AuditEntry, Long> AUDIT_ENTRY_ID_ACCESSOR =
		new Accessor<AuditEntry, Long>() {

			@Override
			public Long get(AuditEntry auditEntry) {
				return auditEntry.getAuditEntryId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<AuditEntry> getTypeClass() {
				return AuditEntry.class;
			}

		};

}