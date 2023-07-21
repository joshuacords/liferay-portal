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
public class VimeoEditorEmbedProvider implements EditorEmbedProvider {

	@Override
	public String getId() {
		return "vimeo";
	}

	@Override
	public String getTpl() {
		return StringBundler.concat(
			"<iframe allowfullscreen frameborder=\"0\" height=\"315\" ",
			"mozallowfullscreen ",
			"src=\"https://player.vimeo.com/video/{embedId}\" ",
			"webkitallowfullscreen width=\"560\"></iframe>");
	}

	@Override
	public String[] getURLSchemes() {
		return new String[] {
			"https?:\\/\\/(?:www\\.)?vimeo\\.com\\/album\\/.*\\/video\\/(\\S*)",
			"https?:\\/\\/(?:www\\.)?vimeo\\.com\\/channels\\/.*\\/(\\S*)",
			"https?:\\/\\/(?:www\\.)?vimeo\\.com\\/groups\\/.*\\/videos\\" +
				"/(\\S*)",
			"https?:\\/\\/(?:www\\.)?vimeo\\.com\\/(\\S*)$"
		};
	}

}