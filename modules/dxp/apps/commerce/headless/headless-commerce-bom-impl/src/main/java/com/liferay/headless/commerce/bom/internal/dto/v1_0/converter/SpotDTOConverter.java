/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.bom.internal.dto.v1_0.converter;

import com.liferay.commerce.bom.model.CommerceBOMEntry;
import com.liferay.commerce.bom.service.CommerceBOMEntryService;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPInstanceService;
import com.liferay.headless.commerce.bom.dto.v1_0.Spot;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = "model.class.name=com.liferay.commerce.bom.model.CommerceBOMEntry",
	service = {DTOConverter.class, SpotDTOConverter.class}
)
public class SpotDTOConverter implements DTOConverter<CommerceBOMEntry, Spot> {

	@Override
	public String getContentType() {
		return Spot.class.getSimpleName();
	}

	public Spot toDTO(DTOConverterContext dtoConverterContext)
		throws Exception {

		CommerceBOMEntry commerceBOMEntry =
			_commerceBOMEntryService.getCommerceBOMEntry(
				(Long)dtoConverterContext.getId());

		CPInstance cpInstance = _cpInstanceService.fetchCProductInstance(
			commerceBOMEntry.getCProductId(),
			commerceBOMEntry.getCPInstanceUuid());

		return new Spot() {
			{
				id = commerceBOMEntry.getCommerceBOMEntryId();
				number = commerceBOMEntry.getNumber();
				position = _positionDTOConverter.toDTO(dtoConverterContext);
				productId = commerceBOMEntry.getCPInstanceUuid();

				if (cpInstance == null) {
					sku = StringPool.BLANK;
				}
				else {
					sku = cpInstance.getSku();
				}
			}
		};
	}

	@Reference
	private CommerceBOMEntryService _commerceBOMEntryService;

	@Reference
	private CPInstanceService _cpInstanceService;

	@Reference
	private PositionDTOConverter _positionDTOConverter;

}