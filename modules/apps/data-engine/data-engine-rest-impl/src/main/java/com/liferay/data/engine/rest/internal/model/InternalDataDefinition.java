/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.data.engine.rest.internal.model;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.model.ClassedModel;

import java.io.Serializable;

/**
 * @author Jeyvison Nascimento
 */
public class InternalDataDefinition implements ClassedModel, Serializable {

	@Override
	public ExpandoBridge getExpandoBridge() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Class<?> getModelClass() {
		return InternalDataDefinition.class;
	}

	@Override
	public String getModelClassName() {
		return InternalDataDefinition.class.getName();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _dataDefinitionId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_dataDefinitionId = (long)primaryKeyObj;
	}

	private long _dataDefinitionId;

}