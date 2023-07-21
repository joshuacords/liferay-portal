/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.security.access.control;

import com.liferay.petra.lang.CentralizedThreadLocal;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.internal.security.access.control.AllowedIPAddressesValidator;
import com.liferay.portal.kernel.internal.security.access.control.AllowedIPAddressesValidatorFactory;
import com.liferay.portal.kernel.security.auth.AccessControlContext;
import com.liferay.portal.kernel.security.auth.AuthException;
import com.liferay.portal.kernel.security.auth.verifier.AuthVerifierResult;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceTracker;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Tomas Polesovsky
 * @author Michael C. Han
 * @author Raymond Augé
 */
public class AccessControlUtil {

	public static AccessControl getAccessControl() {
		return _accessControlUtil._serviceTracker.getService();
	}

	public static AccessControlContext getAccessControlContext() {
		return _accessControlContext.get();
	}

	public static void initAccessControlContext(
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse, Map<String, Object> settings) {

		getAccessControl().initAccessControlContext(
			httpServletRequest, httpServletResponse, settings);
	}

	public static void initContextUser(long userId) throws AuthException {
		getAccessControl().initContextUser(userId);
	}

	public static boolean isAccessAllowed(
		HttpServletRequest httpServletRequest, Set<String> hostsAllowed) {

		if (hostsAllowed.isEmpty()) {
			return true;
		}

		String remoteAddr = httpServletRequest.getRemoteAddr();

		for (String hostAllowed : hostsAllowed) {
			AllowedIPAddressesValidator allowedIPAddressesValidator =
				AllowedIPAddressesValidatorFactory.create(hostAllowed);

			if (allowedIPAddressesValidator.isAllowedIPAddress(remoteAddr)) {
				return true;
			}
		}

		Set<String> computerAddresses = PortalUtil.getComputerAddresses();

		if (computerAddresses.contains(remoteAddr) &&
			hostsAllowed.contains(_SERVER_IP)) {

			return true;
		}

		return false;
	}

	public static void setAccessControlContext(
		AccessControlContext accessControlContext) {

		_accessControlContext.set(accessControlContext);
	}

	public static AuthVerifierResult.State verifyRequest()
		throws PortalException {

		return getAccessControl().verifyRequest();
	}

	private AccessControlUtil() {
		Registry registry = RegistryUtil.getRegistry();

		_serviceTracker = registry.trackServices(AccessControl.class);

		_serviceTracker.open();
	}

	private static final String _SERVER_IP = "SERVER_IP";

	private static final ThreadLocal<AccessControlContext>
		_accessControlContext = new CentralizedThreadLocal<>(
			AccessControlUtil.class + "._accessControlContext");
	private static final AccessControlUtil _accessControlUtil =
		new AccessControlUtil();

	private final ServiceTracker<?, AccessControl> _serviceTracker;

}