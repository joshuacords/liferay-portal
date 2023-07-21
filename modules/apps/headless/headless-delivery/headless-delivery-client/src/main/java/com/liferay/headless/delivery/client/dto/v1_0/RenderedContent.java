/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.delivery.client.dto.v1_0;

import com.liferay.headless.delivery.client.function.UnsafeSupplier;
import com.liferay.headless.delivery.client.serdes.v1_0.RenderedContentSerDes;

import java.io.Serializable;

import java.util.Map;
import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
public class RenderedContent implements Cloneable, Serializable {

	public static RenderedContent toDTO(String json) {
		return RenderedContentSerDes.toDTO(json);
	}

	public String getRenderedContentURL() {
		return renderedContentURL;
	}

	public void setRenderedContentURL(String renderedContentURL) {
		this.renderedContentURL = renderedContentURL;
	}

	public void setRenderedContentURL(
		UnsafeSupplier<String, Exception> renderedContentURLUnsafeSupplier) {

		try {
			renderedContentURL = renderedContentURLUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String renderedContentURL;

	public String getRenderedContentValue() {
		return renderedContentValue;
	}

	public void setRenderedContentValue(String renderedContentValue) {
		this.renderedContentValue = renderedContentValue;
	}

	public void setRenderedContentValue(
		UnsafeSupplier<String, Exception> renderedContentValueUnsafeSupplier) {

		try {
			renderedContentValue = renderedContentValueUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String renderedContentValue;

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public void setTemplateName(
		UnsafeSupplier<String, Exception> templateNameUnsafeSupplier) {

		try {
			templateName = templateNameUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String templateName;

	public Map<String, String> getTemplateName_i18n() {
		return templateName_i18n;
	}

	public void setTemplateName_i18n(Map<String, String> templateName_i18n) {
		this.templateName_i18n = templateName_i18n;
	}

	public void setTemplateName_i18n(
		UnsafeSupplier<Map<String, String>, Exception>
			templateName_i18nUnsafeSupplier) {

		try {
			templateName_i18n = templateName_i18nUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Map<String, String> templateName_i18n;

	@Override
	public RenderedContent clone() throws CloneNotSupportedException {
		return (RenderedContent)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof RenderedContent)) {
			return false;
		}

		RenderedContent renderedContent = (RenderedContent)object;

		return Objects.equals(toString(), renderedContent.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return RenderedContentSerDes.toJSON(this);
	}

}