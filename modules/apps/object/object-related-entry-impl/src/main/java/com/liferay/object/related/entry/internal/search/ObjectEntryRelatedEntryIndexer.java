/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.related.entry.internal.search;

import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.model.ObjectRelationship;
import com.liferay.object.related.entry.internal.helper.ObjectRelatedEntryHelper;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.portal.kernel.search.BaseRelatedEntryIndexer;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.List;

/**
 * @author Joshua Cords
 */
public class ObjectEntryRelatedEntryIndexer extends BaseRelatedEntryIndexer {

	public ObjectEntryRelatedEntryIndexer(
		ObjectDefinition objectDefinition,
		ObjectEntryLocalService objectEntryLocalService,
		ObjectRelatedEntryHelper objectRelatedEntryHelper) {

		_objectDefinition = objectDefinition;
		_objectEntryLocalService = objectEntryLocalService;
		_objectRelatedEntryHelper = objectRelatedEntryHelper;
	}

	@Override
	public boolean isVisibleRelatedEntry(long classPK, int status)
		throws Exception {

		List<ObjectRelationship> objectRelationships =
			_objectRelatedEntryHelper.getParentObjectRelationships(
				_objectDefinition);

		if (objectRelationships.isEmpty()) {
			return true;
		}

		ObjectEntry objectEntry = _objectEntryLocalService.fetchObjectEntry(
			classPK);

		if (objectEntry == null) {
			return false;
		}

		for (ObjectRelationship objectRelationship : objectRelationships) {
			long parentObjectEntryId =
				_objectRelatedEntryHelper.getParentObjectEntryId(
					objectEntry, objectRelationship);

			if (parentObjectEntryId == 0) {
				continue;
			}

			ObjectEntry parentObjectEntry =
				_objectEntryLocalService.fetchObjectEntry(parentObjectEntryId);

			if ((parentObjectEntry == null) ||
				!_isVisible(parentObjectEntry.getStatus(), status)) {

				return false;
			}
		}

		return true;
	}

	private boolean _isVisible(int entryStatus, int queryStatus) {
		if (((queryStatus != WorkflowConstants.STATUS_ANY) &&
			 (entryStatus == queryStatus)) ||
			(entryStatus != WorkflowConstants.STATUS_IN_TRASH)) {

			return true;
		}

		return false;
	}

	private final ObjectDefinition _objectDefinition;
	private final ObjectEntryLocalService _objectEntryLocalService;
	private final ObjectRelatedEntryHelper _objectRelatedEntryHelper;

}