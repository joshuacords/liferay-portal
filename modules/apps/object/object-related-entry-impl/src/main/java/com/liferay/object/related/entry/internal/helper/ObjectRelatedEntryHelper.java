/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.related.entry.internal.helper;

import com.liferay.object.constants.ObjectRelationshipConstants;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.model.ObjectField;
import com.liferay.object.model.ObjectRelationship;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.object.service.ObjectFieldLocalService;
import com.liferay.object.service.ObjectRelationshipLocalService;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.MapUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Joshua Cords
 */
@Component(service = ObjectRelatedEntryHelper.class)
public class ObjectRelatedEntryHelper {

	public ObjectDefinition fetchParentObjectDefinition(
		ObjectRelationship objectRelationship) {

		return _fetchRelatedEntryObjectDefinition(
			objectRelationship.getObjectDefinitionId1());
	}

	public long getParentObjectEntryId(
		ObjectEntry objectEntry, ObjectRelationship objectRelationship) {

		ObjectField objectField = _objectFieldLocalService.fetchObjectField(
			objectRelationship.getObjectFieldId2());

		if (objectField == null) {
			return 0;
		}

		return MapUtil.getLong(objectEntry.getValues(), objectField.getName());
	}

	public List<ObjectRelationship> getParentObjectRelationships(
		ObjectDefinition objectDefinition) {

		List<ObjectRelationship> objectRelationships = new ArrayList<>();

		for (ObjectRelationship objectRelationship :
				_objectRelationshipLocalService.
					getObjectRelationshipsByObjectDefinitionId2(
						objectDefinition.getObjectDefinitionId(),
						ObjectRelationshipConstants.TYPE_ONE_TO_MANY)) {

			if (fetchParentObjectDefinition(objectRelationship) == null) {
				continue;
			}

			objectRelationships.add(objectRelationship);
		}

		return ListUtil.sort(
			objectRelationships,
			Comparator.comparing(ObjectRelationship::getName));
	}

	public boolean isRelatedEntryObjectDefinition(
		ObjectDefinition objectDefinition) {

		if ((objectDefinition == null) || !objectDefinition.isActive() ||
			!objectDefinition.isApproved() ||
			!objectDefinition.isEnableIndexSearch() ||
			objectDefinition.isSystem()) {

			return false;
		}

		return true;
	}

	private ObjectDefinition _fetchRelatedEntryObjectDefinition(
		long objectDefinitionId) {

		ObjectDefinition objectDefinition =
			_objectDefinitionLocalService.fetchObjectDefinition(
				objectDefinitionId);

		if (!isRelatedEntryObjectDefinition(objectDefinition)) {
			return null;
		}

		return objectDefinition;
	}

	@Reference
	private ObjectDefinitionLocalService _objectDefinitionLocalService;

	@Reference
	private ObjectFieldLocalService _objectFieldLocalService;

	@Reference
	private ObjectRelationshipLocalService _objectRelationshipLocalService;

}