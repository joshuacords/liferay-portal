/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.editor.alloyeditor.web.internal.editor.embed;

import com.liferay.frontend.editor.embed.EditorEmbedProvider;
import com.liferay.frontend.editor.embed.EditorEmbedProviderTypeConstants;
import com.liferay.petra.string.StringBundler;

import org.osgi.service.component.annotations.Component;

/**
 * @author Sergio González
 */
@Component(
	immediate = true,
	property = "type=" + EditorEmbedProviderTypeConstants.VIDEO,
	service = EditorEmbedProvider.class
)
public class FacebookEditorEmbedProvider implements EditorEmbedProvider {

	@Override
	public String getId() {
		return "facebook";
	}

	@Override
	public String getTpl() {
		return StringBundler.concat(
			"<iframe allowFullScreen=\"true\" allowTransparency=\"true\" ",
			"frameborder=\"0\" height=\"315\" ",
			"src=\"https://www.facebook.com/plugins/video.php?href={embedId}",
			"&show_text=0&width=560&height=315\" scrolling=\"no\" ",
			"style=\"border:none;overflow:hidden\" width=\"560\"></iframe>");
	}

	@Override
	public String[] getURLSchemes() {
		return new String[] {
			"(https?:\\/\\/(?:www\\.)?facebook.com\\/\\S*\\/videos\\/\\S*)"
		};
	}

}