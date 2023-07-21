/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.root.service.impl;

import com.liferay.osb.koroneiki.root.model.ExternalLink;
import com.liferay.osb.koroneiki.root.permission.ModelPermissionRegistry;
import com.liferay.osb.koroneiki.root.service.base.ExternalLinkServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 * @author Amos Fong
 */
@Component(
	property = {
		"json.web.service.context.name=koroneiki",
		"json.web.service.context.path=ExternalLink"
	},
	service = AopService.class
)
public class ExternalLinkServiceImpl extends ExternalLinkServiceBaseImpl {

	public ExternalLink addExternalLink(
			long classNameId, long classPK, String domain, String entityName,
			String entityId)
		throws PortalException {

		_modelPermissionRegistry.check(
			getPermissionChecker(), classNameId, classPK, ActionKeys.UPDATE);

		return externalLinkLocalService.addExternalLink(
			getUserId(), classNameId, classPK, domain, entityName, entityId);
	}

	public ExternalLink deleteExternalLink(long externalLinkId)
		throws PortalException {

		ExternalLink externalLink = externalLinkLocalService.getExternalLink(
			externalLinkId);

		_modelPermissionRegistry.check(
			getPermissionChecker(), externalLink.getClassNameId(),
			externalLink.getClassPK(), ActionKeys.UPDATE);

		return externalLinkLocalService.deleteExternalLink(externalLink);
	}

	public ExternalLink deleteExternalLink(String externalLinkKey)
		throws PortalException {

		ExternalLink externalLink = externalLinkLocalService.getExternalLink(
			externalLinkKey);

		_modelPermissionRegistry.check(
			getPermissionChecker(), externalLink.getClassNameId(),
			externalLink.getClassPK(), ActionKeys.UPDATE);

		return externalLinkLocalService.deleteExternalLink(externalLink);
	}

	public ExternalLink getExternalLink(long externalLinkId)
		throws PortalException {

		ExternalLink externalLink = externalLinkLocalService.getExternalLink(
			externalLinkId);

		_modelPermissionRegistry.check(
			getPermissionChecker(), externalLink.getClassNameId(),
			externalLink.getClassPK(), ActionKeys.VIEW);

		return externalLink;
	}

	public ExternalLink getExternalLink(String externalLinkKey)
		throws PortalException {

		ExternalLink externalLink = externalLinkLocalService.getExternalLink(
			externalLinkKey);

		_modelPermissionRegistry.check(
			getPermissionChecker(), externalLink.getClassNameId(),
			externalLink.getClassPK(), ActionKeys.VIEW);

		return externalLink;
	}

	public List<ExternalLink> getExternalLinks(
			long classNameId, long classPK, int start, int end)
		throws PortalException {

		_modelPermissionRegistry.check(
			getPermissionChecker(), classNameId, classPK, ActionKeys.VIEW);

		return externalLinkLocalService.getExternalLinks(
			classNameId, classPK, start, end);
	}

	public int getExternalLinksCount(long classNameId, long classPK)
		throws PortalException {

		_modelPermissionRegistry.check(
			getPermissionChecker(), classNameId, classPK, ActionKeys.VIEW);

		return externalLinkLocalService.getExternalLinksCount(
			classNameId, classPK);
	}

	public ExternalLink updateExternalLink(long externalLinkId, String entityId)
		throws PortalException {

		ExternalLink externalLink = externalLinkLocalService.getExternalLink(
			externalLinkId);

		_modelPermissionRegistry.check(
			getPermissionChecker(), externalLink.getClassNameId(),
			externalLink.getClassPK(), ActionKeys.UPDATE);

		return externalLinkLocalService.updateExternalLink(
			externalLinkId, entityId);
	}

	@Reference
	private ModelPermissionRegistry _modelPermissionRegistry;

}