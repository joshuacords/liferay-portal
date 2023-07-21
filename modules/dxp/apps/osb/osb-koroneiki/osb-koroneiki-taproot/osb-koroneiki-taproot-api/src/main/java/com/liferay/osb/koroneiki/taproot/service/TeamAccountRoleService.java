/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service;

import com.liferay.osb.koroneiki.taproot.model.TeamAccountRole;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Transactional;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the remote service interface for TeamAccountRole. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see TeamAccountRoleServiceUtil
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface TeamAccountRoleService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.liferay.osb.koroneiki.taproot.service.impl.TeamAccountRoleServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the team account role remote service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link TeamAccountRoleServiceUtil} if injection and service tracking are not available.
	 */
	public TeamAccountRole addTeamAccountRole(
			long teamId, long accountId, long teamRoleId)
		throws PortalException;

	public TeamAccountRole addTeamAccountRole(
			String teamKey, String accountKey, String teamRoleKey)
		throws PortalException;

	public TeamAccountRole deleteTeamAccountRole(
			long teamId, long accountId, long teamRoleId)
		throws PortalException;

	public TeamAccountRole deleteTeamAccountRole(
			String teamKey, String accountKey, String teamRoleKey)
		throws PortalException;

	public void deleteTeamAccountRoles(long teamId, long accountId)
		throws PortalException;

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

}