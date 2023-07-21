/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.catalog.internal.resource.v1_0;

import com.liferay.commerce.product.exception.NoSuchCPOptionException;
import com.liferay.commerce.product.model.CPOption;
import com.liferay.commerce.product.model.CPOptionValue;
import com.liferay.commerce.product.service.CPOptionService;
import com.liferay.commerce.product.service.CPOptionValueService;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Option;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.OptionValue;
import com.liferay.headless.commerce.admin.catalog.internal.dto.v1_0.converter.OptionValueDTOConverter;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.OptionValueResource;
import com.liferay.headless.commerce.core.util.LanguageUtils;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.vulcan.dto.converter.DefaultDTOConverterContext;
import com.liferay.portal.vulcan.fields.NestedField;
import com.liferay.portal.vulcan.fields.NestedFieldSupport;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/option-value.properties",
	scope = ServiceScope.PROTOTYPE,
	service = {NestedFieldSupport.class, OptionValueResource.class}
)
public class OptionValueResourceImpl
	extends BaseOptionValueResourceImpl implements NestedFieldSupport {

	@Override
	public Page<OptionValue> getOptionByExternalReferenceCodeOptionValuesPage(
			String externalReferenceCode, Pagination pagination)
		throws Exception {

		CPOption cpOption = _cpOptionService.fetchByExternalReferenceCode(
			contextCompany.getCompanyId(), externalReferenceCode);

		if (cpOption == null) {
			throw new NoSuchCPOptionException(
				"Unable to find Option with externalReferenceCode: " +
					externalReferenceCode);
		}

		List<CPOptionValue> cpOptionValues =
			_cpOptionValueService.getCPOptionValues(
				cpOption.getCPOptionId(), pagination.getStartPosition(),
				pagination.getEndPosition());

		int totalItems = _cpOptionValueService.getCPOptionValuesCount(
			cpOption.getCPOptionId());

		return Page.of(_toOptionValues(cpOptionValues), pagination, totalItems);
	}

	@NestedField(parentClass = Option.class, value = "values")
	@Override
	public Page<OptionValue> getOptionIdOptionValuesPage(
			Long id, Pagination pagination)
		throws Exception {

		CPOption cpOption = _cpOptionService.getCPOption(id);

		List<CPOptionValue> cpOptionValues =
			_cpOptionValueService.getCPOptionValues(
				cpOption.getCPOptionId(), pagination.getStartPosition(),
				pagination.getEndPosition());

		int totalItems = _cpOptionValueService.getCPOptionValuesCount(
			cpOption.getCPOptionId());

		return Page.of(_toOptionValues(cpOptionValues), pagination, totalItems);
	}

	@Override
	public OptionValue postOptionByExternalReferenceCodeOptionValue(
			String externalReferenceCode, OptionValue optionValue)
		throws Exception {

		CPOption cpOption = _cpOptionService.fetchByExternalReferenceCode(
			contextCompany.getCompanyId(), externalReferenceCode);

		if (cpOption == null) {
			throw new NoSuchCPOptionException(
				"Unable to find Option with externalReferenceCode: " +
					externalReferenceCode);
		}

		return _addOrUpdateOptionValue(cpOption, optionValue);
	}

	@Override
	public OptionValue postOptionIdOptionValue(Long id, OptionValue optionValue)
		throws Exception {

		return _addOrUpdateOptionValue(
			_cpOptionService.getCPOption(id), optionValue);
	}

	private OptionValue _addOrUpdateOptionValue(
			CPOption cpOption, OptionValue optionValue)
		throws Exception {

		CPOptionValue cpOptionValue = _cpOptionValueService.upsertCPOptionValue(
			cpOption.getCPOptionId(),
			LanguageUtils.getLocalizedMap(optionValue.getName()),
			GetterUtil.get(optionValue.getPriority(), 0D), optionValue.getKey(),
			optionValue.getExternalReferenceCode(),
			_serviceContextHelper.getServiceContext());

		return _toOptionValue(cpOptionValue.getCPOptionValueId());
	}

	private OptionValue _toOptionValue(Long cpOptionValueId) throws Exception {
		return _optionValueDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				cpOptionValueId, contextAcceptLanguage.getPreferredLocale()));
	}

	private List<OptionValue> _toOptionValues(
			List<CPOptionValue> cpOptionValues)
		throws Exception {

		List<OptionValue> productOptionValues = new ArrayList<>();

		for (CPOptionValue cpOptionValue : cpOptionValues) {
			productOptionValues.add(
				_toOptionValue(cpOptionValue.getCPOptionValueId()));
		}

		return productOptionValues;
	}

	@Reference
	private CPOptionService _cpOptionService;

	@Reference
	private CPOptionValueService _cpOptionValueService;

	@Reference
	private OptionValueDTOConverter _optionValueDTOConverter;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}