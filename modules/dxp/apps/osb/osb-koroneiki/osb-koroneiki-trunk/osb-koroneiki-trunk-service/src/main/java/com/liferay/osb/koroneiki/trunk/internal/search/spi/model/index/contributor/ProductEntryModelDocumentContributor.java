/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.internal.search.spi.model.index.contributor;

import com.liferay.osb.koroneiki.root.model.ExternalLink;
import com.liferay.osb.koroneiki.root.service.ExternalLinkLocalService;
import com.liferay.osb.koroneiki.trunk.model.ProductEntry;
import com.liferay.osb.koroneiki.trunk.model.ProductField;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.search.spi.model.index.contributor.ModelDocumentContributor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = "indexer.class.name=com.liferay.osb.koroneiki.trunk.model.ProductEntry",
	service = ModelDocumentContributor.class
)
public class ProductEntryModelDocumentContributor
	implements ModelDocumentContributor<ProductEntry> {

	@Override
	public void contribute(Document document, ProductEntry productEntry) {
		try {
			_contribute(document, productEntry);
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}
	}

	private void _contribute(Document document, ProductEntry productEntry)
		throws PortalException {

		document.addKeyword(Field.COMPANY_ID, productEntry.getCompanyId());
		document.addDate(Field.CREATE_DATE, productEntry.getCreateDate());
		document.addDate(Field.MODIFIED_DATE, productEntry.getModifiedDate());
		document.addText(Field.NAME, productEntry.getName());
		document.addText("productEntryKey", productEntry.getProductEntryKey());

		document.addTextSortable(Field.NAME, productEntry.getName());

		_contributeExternalLinks(document, productEntry.getProductEntryId());
		_contributeProductFields(document, productEntry);
	}

	private void _contributeExternalLinks(
			Document document, long productEntryId)
		throws PortalException {

		Set<String> externalLinkDomains = new HashSet<>();
		Set<String> externalLinkEntityIds = new HashSet<>();
		Set<String> externalLinkEntityNames = new HashSet<>();

		List<ExternalLink> externalLinks =
			_externalLinkLocalService.getExternalLinks(
				ProductEntry.class.getName(), productEntryId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);

		for (ExternalLink externalLink : externalLinks) {
			String entityName =
				externalLink.getDomain() + StringPool.UNDERLINE +
					externalLink.getEntityName();

			String entityId =
				entityName + StringPool.UNDERLINE + externalLink.getEntityId();

			externalLinkDomains.add(externalLink.getDomain());
			externalLinkEntityIds.add(entityId);
			externalLinkEntityNames.add(entityName);
		}

		document.addKeyword(
			"externalLinkDomains",
			ArrayUtil.toStringArray(externalLinkDomains.toArray()));
		document.addKeyword(
			"externalLinkEntityIds",
			ArrayUtil.toStringArray(externalLinkEntityIds.toArray()));
		document.addKeyword(
			"externalLinkEntityNames",
			ArrayUtil.toStringArray(externalLinkEntityNames.toArray()));
	}

	private void _contributeProductFields(
			Document document, ProductEntry productEntry)
		throws PortalException {

		List<ProductField> productFields = productEntry.getProductFields();

		for (ProductField productField : productFields) {
			document.addKeyword(
				"property_" + productField.getName(), productField.getValue());
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ProductEntryModelDocumentContributor.class);

	@Reference
	private ExternalLinkLocalService _externalLinkLocalService;

}