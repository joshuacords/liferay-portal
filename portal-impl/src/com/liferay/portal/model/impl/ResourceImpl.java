/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.model.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.Resource;

/**
 * @author Brian Wing Shun Chan
 */
public class ResourceImpl implements Resource {

	public ResourceImpl() {
	}

	@Override
	public long getCodeId() {
		return _codeId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public String getPrimKey() {
		return _primKey;
	}

	@Override
	public long getResourceId() {
		return _resourceId;
	}

	@Override
	public int getScope() {
		return _scope;
	}

	@Override
	public void setCodeId(long codeId) {
		_codeId = codeId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	@Override
	public void setName(String name) {
		_name = name;
	}

	@Override
	public void setPrimKey(String primKey) {
		_primKey = primKey;
	}

	@Override
	public void setResourceId(long resourceId) {
		_resourceId = resourceId;
	}

	@Override
	public void setScope(int scope) {
		_scope = scope;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{companyid=");
		sb.append(_companyId);
		sb.append(", name=");
		sb.append(_name);
		sb.append(", primKey=");
		sb.append(_primKey);
		sb.append(", scope=");
		sb.append(_scope);
		sb.append("}");

		return sb.toString();
	}

	private long _codeId;
	private long _companyId;
	private String _name;
	private String _primKey;
	private long _resourceId;
	private int _scope;

}