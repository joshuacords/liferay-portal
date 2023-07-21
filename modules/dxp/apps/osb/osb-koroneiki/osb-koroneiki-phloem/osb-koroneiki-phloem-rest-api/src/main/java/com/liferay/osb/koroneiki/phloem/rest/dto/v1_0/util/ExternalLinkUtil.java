/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ExternalLink;

/**
 * @author Amos Fong
 */
public class ExternalLinkUtil {

	public static ExternalLink toExternalLink(
			com.liferay.osb.koroneiki.root.model.ExternalLink externalLink)
		throws Exception {

		return new ExternalLink() {
			{
				dateCreated = externalLink.getCreateDate();
				domain = externalLink.getDomain();
				entityId = externalLink.getEntityId();
				entityName = externalLink.getEntityName();
				key = externalLink.getExternalLinkKey();
				url = externalLink.getUrl();
			}
		};
	}

}