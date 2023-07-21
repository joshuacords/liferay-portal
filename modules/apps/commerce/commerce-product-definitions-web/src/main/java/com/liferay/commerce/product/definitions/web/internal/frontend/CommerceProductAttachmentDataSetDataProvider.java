/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.definitions.web.internal.frontend;

import com.liferay.commerce.account.constants.CommerceAccountConstants;
import com.liferay.commerce.frontend.CommerceDataSetDataProvider;
import com.liferay.commerce.frontend.Filter;
import com.liferay.commerce.frontend.Pagination;
import com.liferay.commerce.frontend.model.ImageField;
import com.liferay.commerce.frontend.model.LabelField;
import com.liferay.commerce.media.CommerceMediaResolverUtil;
import com.liferay.commerce.product.definitions.web.internal.model.ProductMedia;
import com.liferay.commerce.product.model.CPAttachmentFileEntry;
import com.liferay.commerce.product.model.CPAttachmentFileEntryConstants;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.service.CPAttachmentFileEntryService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = "commerce.data.provider.key=" + CommerceProductDataSetConstants.COMMERCE_DATA_SET_KEY_PRODUCT_ATTACHMENTS,
	service = CommerceDataSetDataProvider.class
)
public class CommerceProductAttachmentDataSetDataProvider
	implements CommerceDataSetDataProvider<ProductMedia> {

	@Override
	public int countItems(HttpServletRequest httpServletRequest, Filter filter)
		throws PortalException {

		long cpDefinitionId = ParamUtil.getLong(
			httpServletRequest, "cpDefinitionId");

		return _cpAttachmentFileEntryService.getCPAttachmentFileEntriesCount(
			_portal.getClassNameId(CPDefinition.class), cpDefinitionId,
			CPAttachmentFileEntryConstants.TYPE_OTHER,
			WorkflowConstants.STATUS_ANY);
	}

	@Override
	public List<ProductMedia> getItems(
			HttpServletRequest httpServletRequest, Filter filter,
			Pagination pagination, Sort sort)
		throws PortalException {

		List<ProductMedia> productMedia = new ArrayList<>();

		Locale locale = _portal.getLocale(httpServletRequest);

		long cpDefinitionId = ParamUtil.getLong(
			httpServletRequest, "cpDefinitionId");

		List<CPAttachmentFileEntry> cpAttachmentFileEntries =
			_cpAttachmentFileEntryService.getCPAttachmentFileEntries(
				_portal.getClassNameId(CPDefinition.class), cpDefinitionId,
				CPAttachmentFileEntryConstants.TYPE_OTHER,
				WorkflowConstants.STATUS_ANY, pagination.getStartPosition(),
				pagination.getEndPosition());

		for (CPAttachmentFileEntry cpAttachmentFileEntry :
				cpAttachmentFileEntries) {

			long cpAttachmentFileEntryId =
				cpAttachmentFileEntry.getCPAttachmentFileEntryId();

			String title = cpAttachmentFileEntry.getTitle(
				LanguageUtil.getLanguageId(locale));

			FileEntry fileEntry = cpAttachmentFileEntry.getFileEntry();

			Date modifiedDate = cpAttachmentFileEntry.getModifiedDate();

			String modifiedDateDescription = LanguageUtil.getTimeDescription(
				httpServletRequest,
				System.currentTimeMillis() - modifiedDate.getTime(), true);

			String statusDisplayStyle = StringPool.BLANK;

			if (cpAttachmentFileEntry.getStatus() ==
					WorkflowConstants.STATUS_APPROVED) {

				statusDisplayStyle = "success";
			}

			productMedia.add(
				new ProductMedia(
					cpAttachmentFileEntryId,
					new ImageField(
						title, "rounded", "lg",
						CommerceMediaResolverUtil.getThumbnailURL(
							CommerceAccountConstants.ACCOUNT_ID_GUEST,
							cpAttachmentFileEntryId)),
					HtmlUtil.escape(title),
					HtmlUtil.escape(fileEntry.getExtension()),
					cpAttachmentFileEntry.getPriority(),
					LanguageUtil.format(
						httpServletRequest, "x-ago", modifiedDateDescription,
						false),
					new LabelField(
						statusDisplayStyle,
						LanguageUtil.get(
							httpServletRequest,
							WorkflowConstants.getStatusLabel(
								cpAttachmentFileEntry.getStatus())))));
		}

		return productMedia;
	}

	@Reference
	private CPAttachmentFileEntryService _cpAttachmentFileEntryService;

	@Reference
	private Portal _portal;

}