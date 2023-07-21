/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.xml;

import com.liferay.portal.kernel.xml.Entity;
import com.liferay.portal.kernel.xml.Visitor;

/**
 * @author Brian Wing Shun Chan
 */
public class EntityImpl extends NodeImpl implements Entity {

	public EntityImpl(org.dom4j.Entity entity) {
		super(entity);

		_entity = entity;
	}

	@Override
	public <T, V extends Visitor<T>> T accept(V visitor) {
		return visitor.visitEntity(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EntityImpl)) {
			return false;
		}

		EntityImpl entityImpl = (EntityImpl)obj;

		org.dom4j.Entity entity = entityImpl.getWrappedEntity();

		return _entity.equals(entity);
	}

	public org.dom4j.Entity getWrappedEntity() {
		return _entity;
	}

	@Override
	public int hashCode() {
		return _entity.hashCode();
	}

	@Override
	public String toString() {
		return _entity.toString();
	}

	private final org.dom4j.Entity _entity;

}