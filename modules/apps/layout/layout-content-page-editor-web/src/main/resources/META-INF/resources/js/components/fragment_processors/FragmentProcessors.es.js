/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import EditableBackgroundImageFragmentProcessor from './EditableBackgroundImageProcessor.es';
import EditableHTMLFragmentProcessor from './EditableHTMLFragmentProcessor.es';
import EditableImageFragmentProcessor from './EditableImageFragmentProcessor.es';
import EditableLinkFragmentProcessor from './EditableLinkFragmentProcessor.es';
import EditableRichTextFragmentProcessor from './EditableRichTextFragmentProcessor.es';
import EditableTextFragmentProcessor from './EditableTextFragmentProcessor.es';

const FragmentProcessors = {
	backgroundImage: {
		destroy: EditableBackgroundImageFragmentProcessor.destroy,
		getFloatingToolbarButtons:
			EditableBackgroundImageFragmentProcessor.getFloatingToolbarButtons,
		init: EditableBackgroundImageFragmentProcessor.init,
		render: EditableBackgroundImageFragmentProcessor.render
	},

	fallback: {
		destroy: EditableRichTextFragmentProcessor.destroy,
		getFloatingToolbarButtons:
			EditableRichTextFragmentProcessor.getFloatingToolbarButtons,
		init: EditableRichTextFragmentProcessor.init,
		render: EditableRichTextFragmentProcessor.render
	},

	html: {
		destroy: EditableHTMLFragmentProcessor.destroy,
		getFloatingToolbarButtons:
			EditableHTMLFragmentProcessor.getFloatingToolbarButtons,
		init: EditableHTMLFragmentProcessor.init,
		render: EditableHTMLFragmentProcessor.render
	},

	image: {
		destroy: EditableImageFragmentProcessor.destroy,
		getFloatingToolbarButtons:
			EditableImageFragmentProcessor.getFloatingToolbarButtons,
		init: EditableImageFragmentProcessor.init,
		render: EditableImageFragmentProcessor.render
	},

	link: {
		destroy: EditableLinkFragmentProcessor.destroy,
		getFloatingToolbarButtons:
			EditableLinkFragmentProcessor.getFloatingToolbarButtons,
		init: EditableLinkFragmentProcessor.init,
		render: EditableLinkFragmentProcessor.render
	},

	text: {
		destroy: EditableTextFragmentProcessor.destroy,
		getFloatingToolbarButtons:
			EditableTextFragmentProcessor.getFloatingToolbarButtons,
		init: EditableTextFragmentProcessor.init,
		render: EditableTextFragmentProcessor.render
	}
};

export {FragmentProcessors};
export default FragmentProcessors;
