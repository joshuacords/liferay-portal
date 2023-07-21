/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import Component from 'metal-component';
import Soy from 'metal-soy';

import templates from './ImageEditorLoading.soy';

/**
 * ImageEditor Loading Component
 * @review
 */
class ImageEditorLoading extends Component {}

Soy.register(ImageEditorLoading, templates);

export default ImageEditorLoading;
