/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.powwow.service.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.powwow.model.PowwowServer;
import com.liferay.powwow.provider.PowwowServiceProviderUtil;
import com.liferay.powwow.service.base.PowwowServerLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;

/**
 * @author Shinn Lok
 */
public class PowwowServerLocalServiceImpl
	extends PowwowServerLocalServiceBaseImpl {

	@Override
	public PowwowServer addPowwowServer(
			long userId, String name, String providerType, String url,
			String apiKey, String secret, ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		Date now = new Date();

		long powwowServerId = counterLocalService.increment();

		PowwowServer powwowServer = powwowServerPersistence.create(
			powwowServerId);

		powwowServer.setCompanyId(user.getCompanyId());
		powwowServer.setUserId(user.getUserId());
		powwowServer.setUserName(user.getFullName());
		powwowServer.setCreateDate(serviceContext.getCreateDate(now));
		powwowServer.setModifiedDate(serviceContext.getModifiedDate(now));
		powwowServer.setName(name);
		powwowServer.setProviderType(providerType);
		powwowServer.setUrl(formatURL(url));
		powwowServer.setApiKey(apiKey);
		powwowServer.setSecret(secret);
		powwowServer.setActive(
			PowwowServiceProviderUtil.isServerActive(powwowServer));

		return powwowServerPersistence.update(powwowServer);
	}

	@Override
	public void checkPowwowServers() {
		List<PowwowServer> powwowServers = powwowServerPersistence.findAll();

		for (PowwowServer powwowServer : powwowServers) {
			powwowServer.setActive(
				PowwowServiceProviderUtil.isServerActive(powwowServer));

			powwowServerPersistence.update(powwowServer);
		}
	}

	@Override
	public PowwowServer deletePowwowServer(long powwowServerId)
		throws PortalException {

		PowwowServer powwowServer = powwowServerPersistence.findByPrimaryKey(
			powwowServerId);

		return deletePowwowServer(powwowServer);
	}

	@Override
	public PowwowServer deletePowwowServer(PowwowServer powwowServer) {
		powwowServerPersistence.remove(powwowServer);

		return powwowServer;
	}

	@Override
	public List<PowwowServer> getPowwowServers(
		int start, int end, OrderByComparator obc) {

		return powwowServerPersistence.findAll(start, end, obc);
	}

	@Override
	public List<PowwowServer> getPowwowServers(
		String providerType, boolean active) {

		return powwowServerPersistence.findByPT_A(providerType, active);
	}

	@Override
	public int getPowwowServersCount() {
		return powwowServerPersistence.countAll();
	}

	@Override
	public int getPowwowServersCount(String providerType, boolean active) {
		return powwowServerPersistence.countByPT_A(providerType, active);
	}

	@Override
	public PowwowServer updatePowwowServer(
			long powwowServerId, String name, String providerType, String url,
			String apiKey, String secret, ServiceContext serviceContext)
		throws PortalException {

		PowwowServer powwowServer = powwowServerPersistence.findByPrimaryKey(
			powwowServerId);

		powwowServer.setModifiedDate(serviceContext.getModifiedDate(null));
		powwowServer.setName(name);
		powwowServer.setProviderType(providerType);
		powwowServer.setUrl(formatURL(url));
		powwowServer.setApiKey(apiKey);
		powwowServer.setSecret(secret);
		powwowServer.setActive(
			PowwowServiceProviderUtil.isServerActive(powwowServer));

		return powwowServerPersistence.update(powwowServer);
	}

	protected String formatURL(String url) {
		if (url.equals(StringPool.BLANK)) {
			return StringPool.BLANK;
		}

		if (!url.endsWith(StringPool.SLASH)) {
			url += StringPool.SLASH;
		}

		return url;
	}

}