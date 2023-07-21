/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.bom.internal.dto.v1_0.converter;

import com.liferay.commerce.bom.model.CommerceBOMEntry;
import com.liferay.commerce.bom.service.CommerceBOMEntryService;
import com.liferay.headless.commerce.bom.dto.v1_0.Position;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = "model.class.name=commerceBOMEntryPosition",
	service = {DTOConverter.class, PositionDTOConverter.class}
)
public class PositionDTOConverter
	implements DTOConverter<CommerceBOMEntry, Position> {

	@Override
	public String getContentType() {
		return Position.class.getSimpleName();
	}

	public Position toDTO(DTOConverterContext dtoConverterContext)
		throws Exception {

		CommerceBOMEntry commerceBOMEntry =
			_commerceBOMEntryService.getCommerceBOMEntry(
				(Long)dtoConverterContext.getId());

		return new Position() {
			{
				x = commerceBOMEntry.getPositionX();
				y = commerceBOMEntry.getPositionY();
			}
		};
	}

	@Reference
	private CommerceBOMEntryService _commerceBOMEntryService;

}