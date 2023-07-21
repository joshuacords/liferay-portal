/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import VideoPreviewer from '../src/main/resources/META-INF/resources/preview/js/VideoPreviewer.es';

let component;

describe('document-library-preview-video', () => {
	afterEach(() => {
		if (component) {
			component.dispose();
		}
	});

	it('renders a video player', () => {
		component = new VideoPreviewer({
			videoPosterURL: 'poster.jpg',
			videoSources: [
				{
					type: 'video/mp4',
					url: '//video.mp4'
				},
				{
					type: 'video/ogv',
					url: '//video.ogv'
				}
			]
		});

		expect(component).toMatchSnapshot();
	});
});
