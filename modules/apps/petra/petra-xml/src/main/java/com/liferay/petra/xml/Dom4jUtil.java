/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.petra.xml;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;
import com.liferay.portal.kernel.io.unsync.UnsyncStringReader;
import com.liferay.portal.kernel.security.xml.SecureXMLFactoryProviderUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import org.xml.sax.XMLReader;

/**
 * @author Brian Wing Shun Chan
 * @author Alan Zimmerman
 * @see    com.liferay.util.xml.Dom4jUtil
 */
public class Dom4jUtil {

	public static String toString(Node node) throws IOException {
		return toString(node, StringPool.TAB);
	}

	public static String toString(Node node, String indent) throws IOException {
		return toString(node, StringPool.TAB, false);
	}

	public static String toString(
			Node node, String indent, boolean expandEmptyElements)
		throws IOException {

		return toString(node, indent, expandEmptyElements, true);
	}

	public static String toString(
			Node node, String indent, boolean expandEmptyElements,
			boolean trimText)
		throws IOException {

		UnsyncByteArrayOutputStream unsyncByteArrayOutputStream =
			new UnsyncByteArrayOutputStream();

		OutputFormat outputFormat = OutputFormat.createPrettyPrint();

		outputFormat.setExpandEmptyElements(expandEmptyElements);
		outputFormat.setIndent(indent);
		outputFormat.setLineSeparator(StringPool.NEW_LINE);
		outputFormat.setTrimText(trimText);

		XMLWriter xmlWriter = new XMLWriter(
			unsyncByteArrayOutputStream, outputFormat);

		xmlWriter.write(node);

		String content = unsyncByteArrayOutputStream.toString(StringPool.UTF8);

		// LEP-4257

		//content = StringUtil.replace(content, "\n\n\n", "\n\n");

		if (content.endsWith("\n\n")) {
			content = content.substring(0, content.length() - 2);
		}

		if (content.endsWith("\n")) {
			content = content.substring(0, content.length() - 1);
		}

		while (content.contains(" \n")) {
			content = StringUtil.replace(content, " \n", "\n");
		}

		if (content.startsWith("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")) {
			content = StringUtil.replaceFirst(
				content, "<?xml version=\"1.0\" encoding=\"UTF-8\"?>",
				"<?xml version=\"1.0\"?>");
		}

		return content;
	}

	public static String toString(String xml)
		throws DocumentException, IOException {

		return toString(xml, StringPool.TAB);
	}

	public static String toString(String xml, String indent)
		throws DocumentException, IOException {

		XMLReader xmlReader = null;

		if (SecureXMLFactoryProviderUtil.getSecureXMLFactoryProvider() !=
				null) {

			xmlReader = SecureXMLFactoryProviderUtil.newXMLReader();
		}

		SAXReader saxReader = new SAXReader(xmlReader);

		Document document = saxReader.read(new UnsyncStringReader(xml));

		return toString(document, indent);
	}

}