/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.atom;

/**
 * @author Igor Spasic
 */
public class AtomEntryContent {

	public AtomEntryContent() {
	}

	public AtomEntryContent(String text) {
		_text = text;
	}

	public AtomEntryContent(String text, Type type) {
		_text = text;
		_type = type;
	}

	public AtomEntryContent(Type type) {
		_type = type;
	}

	public String getMimeType() {
		return _mimeType;
	}

	public String getSrcLink() {
		return _srcLink;
	}

	public String getText() {
		return _text;
	}

	public Type getType() {
		return _type;
	}

	public void setMimeType(String mimeType) {
		_mimeType = mimeType;
	}

	public void setSrcLink(String srcLink) {
		_srcLink = srcLink;
	}

	public void setText(String text) {
		_text = text;
	}

	public void setType(Type type) {
		_type = type;
	}

	public enum Type {

		HTML, MEDIA, TEXT, XHTML, XML

	}

	private String _mimeType;
	private String _srcLink;
	private String _text;
	private Type _type = Type.HTML;

}