/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.internal.util;

import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;

import java.util.List;
import java.util.Map;

/**
 * @author Dennis Ju
 */
@JSON
public class SyncContext {

	public String getAuthType() {
		return _authType;
	}

	public String getLanCertificate() {
		return _lanCertificate;
	}

	public String getLanKey() {
		return _lanKey;
	}

	public String getLanServerUuid() {
		return _lanServerUuid;
	}

	public String getOAuthConsumerKey() {
		return _oAuthConsumerKey;
	}

	public String getOAuthConsumerSecret() {
		return _oAuthConsumerSecret;
	}

	public String getPluginVersion() {
		return _pluginVersion;
	}

	public int getPortalBuildNumber() {
		return _portalBuildNumber;
	}

	@JSON
	public Map<String, String> getPortletPreferencesMap() {
		return _portletPreferencesMap;
	}

	@JSON
	public User getUser() {
		return _user;
	}

	@JSON
	public List<Group> getUserSitesGroups() {
		return _userSitesGroups;
	}

	public boolean isLanEnabled() {
		return _lanEnabled;
	}

	public boolean isOAuthEnabled() {
		return _oAuthEnabled;
	}

	public boolean isSocialOfficeInstalled() {
		return _socialOfficeInstalled;
	}

	public void setAuthType(String authType) {
		_authType = authType;
	}

	public void setLanCertificate(String lanCertificate) {
		_lanCertificate = lanCertificate;
	}

	public void setLanEnabled(boolean lanEnabled) {
		_lanEnabled = lanEnabled;
	}

	public void setLanKey(String lanKey) {
		_lanKey = lanKey;
	}

	public void setLanServerUuid(String lanServerUuid) {
		_lanServerUuid = lanServerUuid;
	}

	public void setOAuthConsumerKey(String oAuthConsumerKey) {
		_oAuthConsumerKey = oAuthConsumerKey;
	}

	public void setOAuthConsumerSecret(String oAuthConsumerSecret) {
		_oAuthConsumerSecret = oAuthConsumerSecret;
	}

	public void setOAuthEnabled(boolean oAuthEnabled) {
		_oAuthEnabled = oAuthEnabled;
	}

	public void setPluginVersion(String pluginVersion) {
		_pluginVersion = pluginVersion;
	}

	public void setPortalBuildNumber(int portalBuildNumber) {
		_portalBuildNumber = portalBuildNumber;
	}

	public void setPortletPreferencesMap(
		Map<String, String> portletPreferencesMap) {

		_portletPreferencesMap = portletPreferencesMap;
	}

	public void setSocialOfficeInstalled(boolean socialOfficeInstalled) {
		_socialOfficeInstalled = socialOfficeInstalled;
	}

	public void setUser(User user) {
		_user = user;
	}

	public void setUserSitesGroups(List<Group> userSitesGroups) {
		_userSitesGroups = userSitesGroups;
	}

	private String _authType;
	private String _lanCertificate;
	private boolean _lanEnabled;
	private String _lanKey;
	private String _lanServerUuid;
	private String _oAuthConsumerKey;
	private String _oAuthConsumerSecret;
	private boolean _oAuthEnabled;
	private String _pluginVersion;
	private int _portalBuildNumber;
	private Map<String, String> _portletPreferencesMap;
	private boolean _socialOfficeInstalled;
	private User _user;
	private List<Group> _userSitesGroups;

}