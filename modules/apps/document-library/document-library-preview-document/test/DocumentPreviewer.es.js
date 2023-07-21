/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import DocumentPreviewer from '../src/main/resources/META-INF/resources/preview/js/DocumentPreviewer.es';

let component;

const defaultDocumentPreviewerConfig = {
	baseImageURL: '/document-images/',
	spritemap: 'icons.svg'
};

describe('document-library-preview-document', () => {
	afterEach(() => {
		if (component) {
			component.dispose();
		}
	});

	it('renders a document previewer with ten pages and the first page rendered', () => {
		component = new DocumentPreviewer({
			...defaultDocumentPreviewerConfig,
			currentPage: 1,
			totalPages: 10
		});

		expect(component).toMatchSnapshot();
	});

	it('renders a document previewer with nineteen pages and the fifth page rendered', () => {
		component = new DocumentPreviewer({
			...defaultDocumentPreviewerConfig,
			currentPage: 5,
			totalPages: 19
		});

		expect(component).toMatchSnapshot();
	});
});
