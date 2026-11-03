/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.search.experiences.rest.internal.batch.engine.action;

import com.liferay.batch.engine.action.ItemReaderPostAction;
import com.liferay.batch.engine.model.BatchEngineImportTask;
import com.liferay.search.experiences.rest.dto.v1_0.SXPBlueprint;
import com.liferay.search.experiences.rest.dto.v1_0.SXPElement;
import com.liferay.search.experiences.rest.dto.v1_0.util.RangeQueryUtil;

import java.io.Serializable;

import java.util.Map;

import org.osgi.service.component.annotations.Component;

/**
 * @author Joshua Cords
 */
@Component(enabled = false, service = ItemReaderPostAction.class)
public class RangeQueryItemReaderPostAction implements ItemReaderPostAction {

	@Override
	public void run(
		BatchEngineImportTask batchEngineImportTask,
		Map<String, Serializable> extendedProperties, Object item) {

		if (item instanceof SXPBlueprint sxpBlueprint) {
			SXPBlueprint upgradedSXPBlueprint = SXPBlueprint.unsafeToDTO(
				RangeQueryUtil.upgradeRangeQueries(sxpBlueprint.toString()));

			sxpBlueprint.setConfiguration(
				upgradedSXPBlueprint.getConfiguration());
			sxpBlueprint.setElementInstances(
				upgradedSXPBlueprint.getElementInstances());
		}
		else if (item instanceof SXPElement sxpElement) {
			SXPElement upgradedSXPElement = SXPElement.unsafeToDTO(
				RangeQueryUtil.upgradeRangeQueries(sxpElement.toString()));

			sxpElement.setElementDefinition(
				upgradedSXPElement.getElementDefinition());
		}
	}

}