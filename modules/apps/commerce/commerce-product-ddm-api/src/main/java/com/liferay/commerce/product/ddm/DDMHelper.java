/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.ddm;

import com.liferay.commerce.product.model.CPDefinitionOptionRel;
import com.liferay.commerce.product.model.CPDefinitionOptionValueRel;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Igor Beslic
 */
public interface DDMHelper {

	public DDMForm getCPAttachmentFileEntryDDMForm(
		Locale locale,
		Map<CPDefinitionOptionRel, List<CPDefinitionOptionValueRel>>
			cpDefinitionOptionRelCPDefinitionOptionValueRels);

	public DDMForm getCPInstanceDDMForm(
		Locale locale, boolean ignoreSKUCombinations,
		Map<CPDefinitionOptionRel, List<CPDefinitionOptionValueRel>>
			cpDefinitionOptionRelCPDefinitionOptionValueRels);

	public DDMForm getPublicStoreDDMForm(
		long groupId, long commerceAccountId, long cpDefinitionId,
		Locale locale, boolean ignoreSKUCombinations,
		Map<CPDefinitionOptionRel, List<CPDefinitionOptionValueRel>>
			cpDefinitionOptionRelCPDefinitionOptionValueRels,
		long companyId, long userId);

	public String renderCPAttachmentFileEntryOptions(
			long cpDefinitionId, String json, RenderRequest renderRequest,
			RenderResponse renderResponse,
			Map<CPDefinitionOptionRel, List<CPDefinitionOptionValueRel>>
				cpDefinitionOptionRelCPDefinitionOptionValueRels)
		throws PortalException;

	public String renderCPInstanceOptions(
			long cpDefinitionId, String json, boolean ignoreSKUCombinations,
			RenderRequest renderRequest, RenderResponse renderResponse,
			Map<CPDefinitionOptionRel, List<CPDefinitionOptionValueRel>>
				cpDefinitionOptionRelCPDefinitionOptionValueRels)
		throws PortalException;

	public String renderPublicStoreOptions(
			long cpDefinitionId, String json, boolean ignoreSKUCombinations,
			RenderRequest renderRequest, RenderResponse renderResponse,
			Map<CPDefinitionOptionRel, List<CPDefinitionOptionValueRel>>
				cpDefinitionOptionRelCPDefinitionOptionValueRels)
		throws PortalException;

}