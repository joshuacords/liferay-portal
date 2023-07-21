/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.root.service.impl;

import com.liferay.osb.koroneiki.root.exception.ExternalLinkDomainException;
import com.liferay.osb.koroneiki.root.exception.ExternalLinkEntityIdException;
import com.liferay.osb.koroneiki.root.exception.ExternalLinkEntityNameException;
import com.liferay.osb.koroneiki.root.model.ExternalLink;
import com.liferay.osb.koroneiki.root.service.base.ExternalLinkLocalServiceBaseImpl;
import com.liferay.osb.koroneiki.root.util.ModelKeyGenerator;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Kyle Bischof
 */
@Component(
	property = "model.class.name=com.liferay.osb.koroneiki.root.model.ExternalLink",
	service = AopService.class
)
public class ExternalLinkLocalServiceImpl
	extends ExternalLinkLocalServiceBaseImpl {

	public ExternalLink addExternalLink(
			long userId, long classNameId, long classPK, String domain,
			String entityName, String entityId)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		validate(domain, entityName, entityId);

		ExternalLink curExternalLink =
			externalLinkPersistence.fetchByC_C_D_EN_EI(
				classNameId, classPK, domain, entityName, entityId);

		if (curExternalLink != null) {
			return curExternalLink;
		}

		long externalLinkId = counterLocalService.increment();

		ExternalLink externalLink = externalLinkPersistence.create(
			externalLinkId);

		externalLink.setCompanyId(user.getCompanyId());
		externalLink.setExternalLinkKey(
			ModelKeyGenerator.generate(externalLinkId));
		externalLink.setClassNameId(classNameId);
		externalLink.setClassPK(classPK);
		externalLink.setDomain(domain);
		externalLink.setEntityName(entityName);
		externalLink.setEntityId(entityId);

		return externalLinkPersistence.update(externalLink);
	}

	public ExternalLink addExternalLink(
			long userId, String className, long classPK, String domain,
			String entityName, String entityId)
		throws PortalException {

		return addExternalLink(
			userId, classNameLocalService.getClassNameId(className), classPK,
			domain, entityName, entityId);
	}

	public void deleteExternalLinks(long classNameId, long classPK) {
		externalLinkPersistence.removeByC_C(classNameId, classPK);
	}

	public ExternalLink getExternalLink(String externalLinkKey)
		throws PortalException {

		return externalLinkPersistence.findByExternalLinkKey(externalLinkKey);
	}

	public List<ExternalLink> getExternalLinks(
		long classNameId, long classPK, int start, int end) {

		return externalLinkPersistence.findByC_C(
			classNameId, classPK, start, end);
	}

	public List<ExternalLink> getExternalLinks(
			long classNameId, String domain, String entityName, String entityId,
			int start, int end)
		throws PortalException {

		return externalLinkPersistence.findByC_D_EN_EI(
			classNameId, domain, entityName, entityId, start, end);
	}

	public List<ExternalLink> getExternalLinks(
		String className, long classPK, int start, int end) {

		return getExternalLinks(
			classNameLocalService.getClassNameId(className), classPK, start,
			end);
	}

	public int getExternalLinksCount(long classNameId, long classPK) {
		return externalLinkPersistence.countByC_C(classNameId, classPK);
	}

	public int getExternalLinksCount(
			long classNameId, String domain, String entityName, String entityId)
		throws PortalException {

		return externalLinkPersistence.countByC_D_EN_EI(
			classNameId, domain, entityName, entityId);
	}

	public int getExternalLinksCount(String className, long classPK) {
		return getExternalLinksCount(
			classNameLocalService.getClassNameId(className), classPK);
	}

	public List<String> search(String domain) {
		return externalLinkFinder.findByDomain(
			domain, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	public List<String> search(String domain, String entityName) {
		return externalLinkFinder.findByD_EN(
			domain, entityName, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	public ExternalLink updateExternalLink(long externalLinkId, String entityId)
		throws PortalException {

		ExternalLink externalLink = externalLinkPersistence.findByPrimaryKey(
			externalLinkId);

		validate(
			externalLink.getDomain(), externalLink.getEntityName(), entityId);

		externalLink.setEntityId(entityId);

		return externalLinkPersistence.update(externalLink);
	}

	protected void validate(String domain, String entityName, String entityId)
		throws PortalException {

		if (Validator.isNull(domain)) {
			throw new ExternalLinkDomainException();
		}

		if (Validator.isNull(entityName)) {
			throw new ExternalLinkEntityNameException();
		}

		if (Validator.isNull(entityId)) {
			throw new ExternalLinkEntityIdException();
		}
	}

}