/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.wiki.model.WikiPageResource;
import com.liferay.wiki.service.base.WikiPageResourceLocalServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
@Component(
	property = "model.class.name=com.liferay.wiki.model.WikiPageResource",
	service = AopService.class
)
public class WikiPageResourceLocalServiceImpl
	extends WikiPageResourceLocalServiceBaseImpl {

	@Override
	public WikiPageResource addPageResource(
		long groupId, long nodeId, String title) {

		long pageResourcePrimKey = counterLocalService.increment();

		WikiPageResource pageResource = wikiPageResourcePersistence.create(
			pageResourcePrimKey);

		pageResource.setGroupId(groupId);
		pageResource.setNodeId(nodeId);
		pageResource.setTitle(title);

		return wikiPageResourcePersistence.update(pageResource);
	}

	/**
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 *             #addPageResource(long, long, String)}
	 */
	@Deprecated
	@Override
	public WikiPageResource addPageResource(long nodeId, String title) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deletePageResource(long nodeId, String title)
		throws PortalException {

		wikiPageResourcePersistence.removeByN_T(nodeId, title);
	}

	@Override
	public WikiPageResource fetchPageResource(long nodeId, String title) {
		return wikiPageResourcePersistence.fetchByN_T(nodeId, title);
	}

	@Override
	public WikiPageResource fetchPageResource(String uuid) {
		return wikiPageResourcePersistence.fetchByUuid_First(uuid, null);
	}

	@Override
	public WikiPageResource getPageResource(long pageResourcePrimKey)
		throws PortalException {

		return wikiPageResourcePersistence.findByPrimaryKey(
			pageResourcePrimKey);
	}

	@Override
	public WikiPageResource getPageResource(long nodeId, String title)
		throws PortalException {

		return wikiPageResourcePersistence.findByN_T(nodeId, title);
	}

	@Override
	public long getPageResourcePrimKey(
		long groupId, long nodeId, String title) {

		WikiPageResource pageResource = wikiPageResourcePersistence.fetchByN_T(
			nodeId, title);

		if (pageResource == null) {
			pageResource = wikiPageResourceLocalService.addPageResource(
				groupId, nodeId, title);
		}

		return pageResource.getResourcePrimKey();
	}

	/**
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 *             #getPageResourcePrimKey(long, long, String)}
	 */
	@Deprecated
	@Override
	public long getPageResourcePrimKey(long nodeId, String title) {
		WikiPageResource pageResource = wikiPageResourcePersistence.fetchByN_T(
			nodeId, title);

		if (pageResource == null) {
			pageResource = wikiPageResourceLocalService.addPageResource(
				nodeId, title);
		}

		return pageResource.getResourcePrimKey();
	}

}