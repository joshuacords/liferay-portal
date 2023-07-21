/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.scion.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the AuthenticationToken service. Represents a row in the &quot;Koroneiki_AuthenticationToken&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see AuthenticationTokenModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.osb.koroneiki.scion.model.impl.AuthenticationTokenImpl"
)
@ProviderType
public interface AuthenticationToken
	extends AuthenticationTokenModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.osb.koroneiki.scion.model.impl.AuthenticationTokenImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<AuthenticationToken, Long>
		AUTHENTICATION_TOKEN_ID_ACCESSOR =
			new Accessor<AuthenticationToken, Long>() {

				@Override
				public Long get(AuthenticationToken authenticationToken) {
					return authenticationToken.getAuthenticationTokenId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<AuthenticationToken> getTypeClass() {
					return AuthenticationToken.class;
				}

			};

	public String getMaskedToken();

}