/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.root.audit.model.listener;

import com.liferay.osb.koroneiki.root.audit.model.BaseAuditModelListener;
import com.liferay.osb.koroneiki.root.model.ExternalLink;
import com.liferay.osb.koroneiki.root.service.ExternalLinkLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ModelListener;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = ModelListener.class)
public class ExternalLinkModelListener
	extends BaseAuditModelListener<ExternalLink> {

	@Override
	protected long getClassNameId(ExternalLink externalLink) {
		return externalLink.getClassNameId();
	}

	@Override
	protected long getClassPK(ExternalLink externalLink) {
		return externalLink.getClassPK();
	}

	@Override
	protected ExternalLink getModel(long classPK) throws PortalException {
		return _externalLinkLocalService.getExternalLink(classPK);
	}

	@Override
	protected boolean isSkipFieldUpdate(
		String field, Object oldValue, Object newValue) {

		if (field.equals("domain") || field.equals("entityId") ||
			field.equals("entityName")) {

			return false;
		}

		return super.isSkipFieldUpdate(field, oldValue, newValue);
	}

	@Reference
	private ExternalLinkLocalService _externalLinkLocalService;

}