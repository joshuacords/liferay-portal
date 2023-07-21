/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.blogs.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the BlogsStatsUser service. Represents a row in the &quot;BlogsStatsUser&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see BlogsStatsUserModel
 * @generated
 */
@ImplementationClassName("com.liferay.blogs.model.impl.BlogsStatsUserImpl")
@ProviderType
public interface BlogsStatsUser extends BlogsStatsUserModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.blogs.model.impl.BlogsStatsUserImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<BlogsStatsUser, Long> STATS_USER_ID_ACCESSOR =
		new Accessor<BlogsStatsUser, Long>() {

			@Override
			public Long get(BlogsStatsUser blogsStatsUser) {
				return blogsStatsUser.getStatsUserId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<BlogsStatsUser> getTypeClass() {
				return BlogsStatsUser.class;
			}

		};

}