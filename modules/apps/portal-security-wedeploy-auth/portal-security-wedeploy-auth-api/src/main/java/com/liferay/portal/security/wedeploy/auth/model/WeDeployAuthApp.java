/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.wedeploy.auth.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the WeDeployAuthApp service. Represents a row in the &quot;WeDeployAuth_WeDeployAuthApp&quot; database table, with each column mapped to a property of this class.
 *
 * @author Supritha Sundaram
 * @see WeDeployAuthAppModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.portal.security.wedeploy.auth.model.impl.WeDeployAuthAppImpl"
)
@ProviderType
public interface WeDeployAuthApp extends PersistedModel, WeDeployAuthAppModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.portal.security.wedeploy.auth.model.impl.WeDeployAuthAppImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<WeDeployAuthApp, Long>
		WE_DEPLOY_AUTH_APP_ID_ACCESSOR = new Accessor<WeDeployAuthApp, Long>() {

			@Override
			public Long get(WeDeployAuthApp weDeployAuthApp) {
				return weDeployAuthApp.getWeDeployAuthAppId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<WeDeployAuthApp> getTypeClass() {
				return WeDeployAuthApp.class;
			}

		};

}