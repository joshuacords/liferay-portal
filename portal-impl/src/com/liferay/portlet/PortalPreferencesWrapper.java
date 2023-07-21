/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet;

import com.liferay.portal.kernel.model.MVCCModel;

import java.io.IOException;
import java.io.Serializable;

import java.util.Enumeration;
import java.util.Map;

import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;

/**
 * @author Alexander Chow
 */
public class PortalPreferencesWrapper
	implements Cloneable, MVCCModel, PortletPreferences, Serializable {

	public PortalPreferencesWrapper(
		PortalPreferencesImpl portalPreferencesImpl) {

		_portalPreferencesImpl = portalPreferencesImpl;
	}

	@Override
	public PortalPreferencesWrapper clone() {
		return new PortalPreferencesWrapper(_portalPreferencesImpl.clone());
	}

	@Override
	public Map<String, String[]> getMap() {
		return _portalPreferencesImpl.getMap();
	}

	@Override
	public long getMvccVersion() {
		return _portalPreferencesImpl.getMvccVersion();
	}

	@Override
	public Enumeration<String> getNames() {
		return _portalPreferencesImpl.getNames();
	}

	public PortalPreferencesImpl getPortalPreferencesImpl() {
		return _portalPreferencesImpl;
	}

	@Override
	public String getValue(String key, String def) {
		return _portalPreferencesImpl.getValue(null, key, def);
	}

	@Override
	public String[] getValues(String key, String[] def) {
		return _portalPreferencesImpl.getValues(null, key, def);
	}

	@Override
	public boolean isReadOnly(String key) {
		return _portalPreferencesImpl.isReadOnly(key);
	}

	@Override
	public void reset(String key) throws ReadOnlyException {
		_portalPreferencesImpl.reset(key);
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setValue(String key, String value) throws ReadOnlyException {
		_portalPreferencesImpl.setValue(key, value);
	}

	@Override
	public void setValues(String key, String[] values)
		throws ReadOnlyException {

		_portalPreferencesImpl.setValues(key, values);
	}

	@Override
	public void store() throws IOException, ValidatorException {
		_portalPreferencesImpl.store();
	}

	private final PortalPreferencesImpl _portalPreferencesImpl;

}