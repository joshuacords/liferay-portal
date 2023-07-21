/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.web.internal.facet.display.context;

import com.liferay.portal.kernel.exception.PortalException;

import java.io.Serializable;

/**
 * @author Lino Alves
 */
public class FolderSearchFacetTermDisplayContext implements Serializable {

	public String getDisplayName() throws PortalException {
		return _displayName;
	}

	public long getFolderId() {
		return _folderId;
	}

	public int getFrequency() {
		return _frequency;
	}

	public boolean isFrequencyVisible() {
		return _frequencyVisible;
	}

	public boolean isSelected() {
		return _selected;
	}

	public void setDisplayName(String displayName) {
		_displayName = displayName;
	}

	public void setFolderId(long folderId) {
		_folderId = folderId;
	}

	public void setFrequency(int frequency) {
		_frequency = frequency;
	}

	public void setFrequencyVisible(boolean frequencyVisible) {
		_frequencyVisible = frequencyVisible;
	}

	public void setSelected(boolean selected) {
		_selected = selected;
	}

	private String _displayName;
	private long _folderId;
	private int _frequency;
	private boolean _frequencyVisible;
	private boolean _selected;

}