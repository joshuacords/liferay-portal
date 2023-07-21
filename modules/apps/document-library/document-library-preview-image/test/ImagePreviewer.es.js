/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ImagePreviewer from '../src/main/resources/META-INF/resources/preview/js/ImagePreviewer.es';

let component;

describe('document-library-preview-image', () => {
	afterEach(() => {
		if (component) {
			component.dispose();
		}
	});

	it('renders an image previewer', () => {
		component = new ImagePreviewer({
			imageURL: 'image.jpg',
			spritemap: 'icons.svg'
		});

		expect(component).toMatchSnapshot();
	});
});
