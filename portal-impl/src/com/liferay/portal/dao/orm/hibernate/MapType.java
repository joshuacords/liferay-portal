/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.dao.orm.hibernate;

import com.liferay.portal.json.JSONFactoryImpl;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.Serializable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

import org.hibernate.engine.SessionImplementor;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.hibernate.usertype.CompositeUserType;

/**
 * @author Cristina González
 */
public class MapType implements CompositeUserType, Serializable {

	@Override
	public Object assemble(
		Serializable cached, SessionImplementor session, Object owner) {

		return cached;
	}

	@Override
	public Object deepCopy(Object obj) {
		return obj;
	}

	@Override
	public Serializable disassemble(Object value, SessionImplementor session) {
		return (Serializable)value;
	}

	@Override
	public boolean equals(Object x, Object y) {
		return Objects.equals(x, y);
	}

	@Override
	public String[] getPropertyNames() {
		return new String[0];
	}

	@Override
	public Type[] getPropertyTypes() {
		return new Type[] {StandardBasicTypes.STRING};
	}

	@Override
	public Object getPropertyValue(Object component, int property) {
		return component;
	}

	@Override
	public int hashCode(Object x) {
		return x.hashCode();
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Object nullSafeGet(
			ResultSet rs, String[] names, SessionImplementor session,
			Object owner)
		throws SQLException {

		String json = (String)StandardBasicTypes.STRING.nullSafeGet(
			rs, names, session, owner);

		try {
			return _jsonFactory.deserialize(json);
		}
		catch (Exception exception) {
			_log.error("Unable to process JSON " + json, exception);

			return Collections.emptyMap();
		}
	}

	@Override
	public void nullSafeSet(
			PreparedStatement ps, Object target, int index,
			SessionImplementor session)
		throws SQLException {

		String json = _jsonFactory.serialize(target);

		StandardBasicTypes.STRING.nullSafeSet(ps, json, index, session);
	}

	@Override
	public Object replace(
		Object original, Object target, SessionImplementor session,
		Object owner) {

		return original;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public Class<Map> returnedClass() {
		return Map.class;
	}

	@Override
	public void setPropertyValue(Object component, int property, Object value) {
	}

	private static final Log _log = LogFactoryUtil.getLog(MapType.class);

	private static final JSONFactory _jsonFactory = new JSONFactoryImpl();

}