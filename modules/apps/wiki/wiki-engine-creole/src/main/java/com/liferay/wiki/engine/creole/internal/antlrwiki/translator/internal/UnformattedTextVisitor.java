/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.engine.creole.internal.antlrwiki.translator.internal;

import com.liferay.petra.string.StringBundler;
import com.liferay.wiki.engine.creole.internal.parser.ast.BoldTextNode;
import com.liferay.wiki.engine.creole.internal.parser.ast.FormattedTextNode;
import com.liferay.wiki.engine.creole.internal.parser.ast.ItalicTextNode;
import com.liferay.wiki.engine.creole.internal.parser.ast.NoWikiInlineNode;
import com.liferay.wiki.engine.creole.internal.parser.ast.NoWikiSectionNode;
import com.liferay.wiki.engine.creole.internal.parser.ast.UnformattedTextNode;
import com.liferay.wiki.engine.creole.internal.parser.ast.link.LinkNode;
import com.liferay.wiki.engine.creole.internal.parser.visitor.BaseASTVisitor;

/**
 * @author Miguel Pastor
 */
public abstract class UnformattedTextVisitor extends BaseASTVisitor {

	public String getText() {
		return _sb.toString();
	}

	@Override
	public void visit(BoldTextNode boldTextNode) {
		if (boldTextNode.getContent() != null) {
			write(boldTextNode.getContent());
		}
		else {
			super.visit(boldTextNode);
		}
	}

	@Override
	public void visit(FormattedTextNode formattedTextNode) {
		if (formattedTextNode.getContent() != null) {
			write(formattedTextNode.getContent());
		}
		else {
			super.visit(formattedTextNode);
		}
	}

	@Override
	public void visit(ItalicTextNode italicTextNode) {
		if (italicTextNode.getContent() != null) {
			write(italicTextNode.getContent());
		}
		else {
			super.visit(italicTextNode);
		}
	}

	@Override
	public void visit(LinkNode linkNode) {
		String link = linkNode.getLink();

		if (link != null) {
			write(link);
		}

		super.visit(linkNode);
	}

	@Override
	public void visit(NoWikiInlineNode noWikiInlineNode) {
		write(noWikiInlineNode.getContent());
	}

	@Override
	public void visit(NoWikiSectionNode noWikiSectionNode) {
		write(noWikiSectionNode.getContent());
	}

	@Override
	public void visit(UnformattedTextNode unformattedTextNode) {
		if (unformattedTextNode.hasContent()) {
			write(unformattedTextNode.getContent());
		}
		else {
			super.visit(unformattedTextNode);
		}
	}

	protected void write(String text) {
		_sb.append(text);
	}

	private final StringBundler _sb = new StringBundler();

}