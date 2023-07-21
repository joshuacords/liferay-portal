/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.metadata;

import com.liferay.document.library.kernel.util.AudioProcessorUtil;
import com.liferay.document.library.kernel.util.VideoProcessorUtil;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xuggler.XugglerUtil;

import com.xuggle.xuggler.IContainer;

import java.io.File;
import java.io.InputStream;

import java.text.DecimalFormat;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.XMPDM;

/**
 * @author Juan González
 * @author Alexander Chow
 */
public class XugglerRawMetadataProcessor extends BaseRawMetadataProcessor {

	@Override
	public void exportGeneratedFiles(
		PortletDataContext portletDataContext, FileEntry fileEntry,
		Element fileEntryElement) {
	}

	@Override
	public void importGeneratedFiles(
		PortletDataContext portletDataContext, FileEntry fileEntry,
		FileEntry importedFileEntry, Element fileEntryElement) {
	}

	protected String convertTime(long microseconds) {
		long milliseconds = microseconds / 1000L;

		StringBundler sb = new StringBundler(7);

		sb.append(_decimalFormatter.format(milliseconds / Time.HOUR));
		sb.append(StringPool.COLON);
		sb.append(
			_decimalFormatter.format(milliseconds % Time.HOUR / Time.MINUTE));
		sb.append(StringPool.COLON);
		sb.append(
			_decimalFormatter.format(milliseconds % Time.MINUTE / Time.SECOND));
		sb.append(StringPool.PERIOD);
		sb.append(_decimalFormatter.format(milliseconds % Time.SECOND / 10));

		return sb.toString();
	}

	protected Metadata extractMetadata(File file) throws Exception {
		IContainer container = IContainer.make();

		try {
			Metadata metadata = new Metadata();

			int result = container.open(
				file.getCanonicalPath(), IContainer.Type.READ, null);

			if (result < 0) {
				throw new IllegalArgumentException("Could not open stream");
			}

			if (container.queryStreamMetaData() < 0) {
				throw new IllegalStateException(
					"Could not query stream metadata");
			}

			long microseconds = container.getDuration();

			metadata.set(XMPDM.DURATION, convertTime(microseconds));

			return metadata;
		}
		finally {
			if (container.isOpened()) {
				container.close();
			}
		}
	}

	@Override
	protected Metadata extractMetadata(
		String extension, String mimeType, File file) {

		if (!isSupported(mimeType)) {
			return null;
		}

		try {
			return extractMetadata(file);
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}

		return null;
	}

	@Override
	protected Metadata extractMetadata(
		String extension, String mimeType, InputStream inputStream) {

		if (!isSupported(mimeType)) {
			return null;
		}

		File file = null;

		try {
			file = FileUtil.createTempFile(extension);

			FileUtil.write(file, inputStream);

			return extractMetadata(file);
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}
		finally {
			FileUtil.delete(file);
		}

		return null;
	}

	protected boolean isSupported(String mimeType) {
		if (XugglerUtil.isEnabled()) {
			if (AudioProcessorUtil.isAudioSupported(mimeType)) {
				return true;
			}

			if (VideoProcessorUtil.isVideoSupported(mimeType)) {
				return true;
			}
		}

		return false;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		XugglerRawMetadataProcessor.class);

	private static final DecimalFormat _decimalFormatter = new DecimalFormat(
		"00");

}