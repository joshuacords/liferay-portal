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
 * The extended model interface for the ExternalLink service. Represents a row in the &quot;Koroneiki_ExternalLink&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ExternalLinkModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.osb.koroneiki.root.model.impl.ExternalLinkImpl"
)
@ProviderType
public interface ExternalLink extends ExternalLinkModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.osb.koroneiki.root.model.impl.ExternalLinkImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ExternalLink, Long> EXTERNAL_LINK_ID_ACCESSOR =
		new Accessor<ExternalLink, Long>() {

			@Override
			public Long get(ExternalLink externalLink) {
				return externalLink.getExternalLinkId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ExternalLink> getTypeClass() {
				return ExternalLink.class;
			}

		};

	public String getUrl();

}