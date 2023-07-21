/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.html.preview.internal.processor;

import com.liferay.html.preview.processor.HtmlPreviewProcessor;
import com.liferay.html.preview.processor.HtmlPreviewProcessorTracker;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

/**
 * @author Pavel Savinov
 */
@Component(immediate = true, service = HtmlPreviewProcessorTracker.class)
public class HtmlPreviewProcessorTrackerImpl
	implements HtmlPreviewProcessorTracker {

	@Override
	public HtmlPreviewProcessor getHtmlPreviewProcessor(String mimeType) {
		return _htmlPreviewProcessors.get(mimeType);
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC
	)
	protected void setHtmlPreviewProcessor(
		HtmlPreviewProcessor htmlPreviewProcessor) {

		_htmlPreviewProcessors.put(
			htmlPreviewProcessor.getMimeType(), htmlPreviewProcessor);
	}

	protected void unsetHtmlPreviewProcessor(
		HtmlPreviewProcessor htmlPreviewProcessor) {

		_htmlPreviewProcessors.remove(htmlPreviewProcessor.getMimeType());
	}

	private final Map<String, HtmlPreviewProcessor> _htmlPreviewProcessors =
		new ConcurrentHashMap<>();

}