/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.model.impl;

/**
 * @author Brian Wing Shun Chan
 */
public class JournalFeedImpl extends JournalFeedBaseImpl {

	/**
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 *             #getDDMRendererTemplateKey()}
	 */
	@Deprecated
	@Override
	public String getRendererTemplateId() {
		return getDDMRendererTemplateKey();
	}

	/**
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 *             #getDDMStructureKey()}
	 */
	@Deprecated
	@Override
	public String getStructureId() {
		return getDDMStructureKey();
	}

	/**
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 *             #getDDMTemplateKey()}
	 */
	@Deprecated
	@Override
	public String getTemplateId() {
		return getDDMTemplateKey();
	}

	/**
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 *             #setDDMRendererTemplateKey(String)}
	 */
	@Deprecated
	@Override
	public void setRendererTemplateId(String rendererTemplateKey) {
		setDDMRendererTemplateKey(rendererTemplateKey);
	}

	/**
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 *             #setDDMStructureKey(String)}
	 */
	@Deprecated
	@Override
	public void setStructureId(String structureKey) {
		setDDMStructureKey(structureKey);
	}

	/**
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 *             #setDDMTemplateKey(String)}
	 */
	@Deprecated
	@Override
	public void setTemplateId(String templateKey) {
		setDDMTemplateKey(templateKey);
	}

}