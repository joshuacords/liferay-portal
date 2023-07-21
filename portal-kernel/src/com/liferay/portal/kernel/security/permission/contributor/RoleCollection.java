/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.security.permission.contributor;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.UserBag;

import org.osgi.annotation.versioning.ProviderType;

/**
 * RoleCollection is used as the argument to {@link
 * RoleContributor#contribute(RoleCollection)}. It holds the managed collection
 * of roleIds starting with the <em>initial</em> set calculated from persisted
 * role assignment and role inheritance.
 *
 * @author Carlos Sierra Andrés
 * @author Raymond Augé
 * @review
 */
@ProviderType
public interface RoleCollection {

	/**
	 * Add a roleId to the collection.
	 *
	 * @param  roleId to add to the collection
	 * @return <code>true</code> if the roleId was added to the collection
	 */
	public boolean addRoleId(long roleId);

	/**
	 * Get the companyId of the Company being checked.
	 *
	 * @return the companyId of the Company being checked
	 */
	public long getCompanyId();

	/**
	 * Get the groupId of the Group currently being permission checked.
	 *
	 * @return the groupId of the Group currently being permission checked
	 */
	public long getGroupId();

	/**
	 * Get the initial set of roles calculated from persisted assignment and
	 * inheritance.
	 *
	 * @return the initial set of roles calculated from persisted assignment and
	 *         inheritance
	 */
	public long[] getInitialRoleIds();

	public User getUser();

	public UserBag getUserBag();

	/**
	 * Check if a Role is already in the collection by roleId.
	 *
	 * @param  roleId the roleId to check
	 * @return <code>true</code> of the Role is in the collection
	 */
	public boolean hasRoleId(long roleId);

	/**
	 * Returns <code>true</code> if the user is signed in.
	 *
	 * @return <code>true</code> if the user is signed in; <code>false</code>
	 *         otherwise
	 */
	public boolean isSignedIn();

	public boolean removeRoleId(long roleId);

}