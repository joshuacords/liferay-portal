/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet;

import com.liferay.petra.xml.XMLUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.xml.simple.Element;

import java.io.IOException;
import java.io.Serializable;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;

/**
 * @author Alexander Chow
 * @author Shuyang Zhou
 */
public abstract class BasePreferencesImpl implements Serializable {

	public BasePreferencesImpl(
		long ownerId, int ownerType, String xml,
		Map<String, Preference> preferences) {

		_ownerId = ownerId;
		_ownerType = ownerType;

		_originalXML = xml;
		_originalPreferences = preferences;
	}

	public Map<String, String[]> getMap() {
		Map<String, Preference> preferences = getPreferences();

		if (preferences.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<String, String[]> map = new HashMap<>();

		for (Map.Entry<String, Preference> entry : preferences.entrySet()) {
			String key = entry.getKey();

			Preference preference = entry.getValue();

			map.put(key, getActualValues(preference.getValues()));
		}

		return map;
	}

	public Enumeration<String> getNames() {
		Map<String, Preference> preferences = getPreferences();

		return Collections.enumeration(preferences.keySet());
	}

	public long getOwnerId() {
		return _ownerId;
	}

	public int getOwnerType() {
		return _ownerType;
	}

	public String getValue(String key, String def) {
		if (key == null) {
			throw new IllegalArgumentException();
		}

		Map<String, Preference> preferences = getPreferences();

		Preference preference = preferences.get(key);

		if (preference == null) {
			return def;
		}

		String[] values = preference.getValues();

		if (isNull(values)) {
			return def;
		}

		return getActualValue(values[0]);
	}

	public String[] getValues(String key, String[] def) {
		if (key == null) {
			throw new IllegalArgumentException();
		}

		Map<String, Preference> preferences = getPreferences();

		Preference preference = preferences.get(key);

		if (preference == null) {
			return def;
		}

		String[] values = preference.getValues();

		if (isNull(values)) {
			return def;
		}

		return getActualValues(values);
	}

	public boolean isReadOnly(String key) {
		if (key == null) {
			throw new IllegalArgumentException();
		}

		Map<String, Preference> preferences = getPreferences();

		Preference preference = preferences.get(key);

		if ((preference != null) && preference.isReadOnly()) {
			return true;
		}

		return false;
	}

	public void reset() {
		_modifiedPreferences = null;
	}

	public abstract void reset(String key) throws ReadOnlyException;

	public void setValue(String key, String value) throws ReadOnlyException {
		if (key == null) {
			throw new IllegalArgumentException();
		}

		value = getXMLSafeValue(value);

		Map<String, Preference> modifiedPreferences = getModifiedPreferences();

		Preference preference = modifiedPreferences.get(key);

		if (preference == null) {
			preference = new Preference(key, value);

			modifiedPreferences.put(key, preference);

			return;
		}

		if (preference.isReadOnly()) {
			throw new ReadOnlyException(key);
		}

		preference = (Preference)preference.clone();

		modifiedPreferences.put(key, preference);

		preference.setValues(new String[] {value});
	}

	public void setValues(String key, String[] values)
		throws ReadOnlyException {

		if (key == null) {
			throw new IllegalArgumentException();
		}

		values = getXMLSafeValues(values);

		Map<String, Preference> modifiedPreferences = getModifiedPreferences();

		Preference preference = modifiedPreferences.get(key);

		if (preference == null) {
			preference = new Preference(key, values);

			modifiedPreferences.put(key, preference);

			return;
		}

		if (preference.isReadOnly()) {
			throw new ReadOnlyException(key);
		}

		preference = (Preference)preference.clone();

		modifiedPreferences.put(key, preference);

		preference.setValues(values);
	}

	public int size() {
		Map<String, Preference> preferences = getPreferences();

		return preferences.size();
	}

	public abstract void store() throws IOException, ValidatorException;

	protected String getActualValue(String value) {
		if ((value == null) || value.equals(_NULL_VALUE)) {
			return null;
		}

		return XMLUtil.fromCompactSafe(value);
	}

	protected String[] getActualValues(String[] values) {
		if (values == null) {
			return null;
		}

		if (values.length == 1) {
			String actualValue = getActualValue(values[0]);

			if (actualValue == null) {
				return null;
			}
			else if (actualValue.equals(_NULL_ELEMENT)) {
				return new String[] {null};
			}

			return new String[] {actualValue};
		}

		String[] actualValues = new String[values.length];

		for (int i = 0; i < actualValues.length; i++) {
			actualValues[i] = getActualValue(values[i]);
		}

		return actualValues;
	}

	protected Map<String, Preference> getModifiedPreferences() {
		if (_modifiedPreferences == null) {
			_modifiedPreferences = new ConcurrentHashMap<>(
				_originalPreferences);
		}

		return _modifiedPreferences;
	}

	protected Map<String, Preference> getOriginalPreferences() {
		return _originalPreferences;
	}

	protected String getOriginalXML() {
		return _originalXML;
	}

	protected Map<String, Preference> getPreferences() {
		if (_modifiedPreferences != null) {
			return _modifiedPreferences;
		}

		return _originalPreferences;
	}

	protected String getXMLSafeValue(String value) {
		if (value == null) {
			return _NULL_VALUE;
		}

		return XMLUtil.toCompactSafe(value);
	}

	protected String[] getXMLSafeValues(String[] values) {
		if (values == null) {
			return new String[] {_NULL_VALUE};
		}

		if ((values.length == 1) && (values[0] == null)) {
			return new String[] {_NULL_ELEMENT};
		}

		String[] xmlSafeValues = new String[values.length];

		for (int i = 0; i < xmlSafeValues.length; i++) {
			xmlSafeValues[i] = getXMLSafeValue(values[i]);
		}

		return xmlSafeValues;
	}

	protected boolean isNull(String[] values) {
		if (ArrayUtil.isEmpty(values) ||
			((values.length == 1) && (getActualValue(values[0]) == null))) {

			return true;
		}

		return false;
	}

	protected void setOriginalPreferences(
		Map<String, Preference> originalPreferences) {

		_originalPreferences = originalPreferences;
	}

	protected void setOriginalXML(String originalXML) {
		_originalXML = originalXML;
	}

	protected String toXML() {
		if ((_modifiedPreferences == null) && (_originalXML != null)) {
			return _originalXML;
		}

		Map<String, Preference> preferences = getPreferences();

		Element portletPreferencesElement = new Element(
			"portlet-preferences", false);

		for (Map.Entry<String, Preference> entry : preferences.entrySet()) {
			Preference preference = entry.getValue();

			Element preferenceElement = portletPreferencesElement.addElement(
				"preference");

			preferenceElement.addElement("name", preference.getName());

			for (String value : preference.getValues()) {
				preferenceElement.addElement("value", value);
			}

			if (preference.isReadOnly()) {
				preferenceElement.addElement("read-only", Boolean.TRUE);
			}
		}

		return portletPreferencesElement.toXMLString();
	}

	private static final String _NULL_ELEMENT = "NULL_ELEMENT";

	private static final String _NULL_VALUE = "NULL_VALUE";

	private Map<String, Preference> _modifiedPreferences;
	private Map<String, Preference> _originalPreferences;
	private String _originalXML;
	private final long _ownerId;
	private final int _ownerType;

}