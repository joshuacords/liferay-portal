/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

/**
 * The Crop Region Utility
 *
 * @deprecated As of Athanasius(7.3.x), replaced by Liferay.Util.getCropRegion
 * @module liferay-crop-region
 */

AUI.add(
	'liferay-crop-region',
	A => {
		var Lang = A.Lang;

		var CropRegion = function() {};

		CropRegion.prototype = {
			_getCropRegion(imagePreview, region) {
				var instance = this;
				var cropRegion;

				if (Liferay.Util.getCropRegion) {
					cropRegion = Liferay.Util.getCropRegion(
						imagePreview,
						region
					);
				}
				else {
					var naturalSize = instance._getImgNaturalSize(imagePreview);

					var scaleX = naturalSize.width / imagePreview.width();
					var scaleY = naturalSize.height / imagePreview.height();

					var regionHeight = region.height
						? region.height * scaleY
						: naturalSize.height;
					var regionWidth = region.width
						? region.width * scaleX
						: naturalSize.width;

					var regionX = region.x ? Math.max(region.x * scaleX, 0) : 0;
					var regionY = region.y ? Math.max(region.y * scaleY, 0) : 0;

					cropRegion = {
						height: regionHeight,
						width: regionWidth,
						x: regionX,
						y: regionY
					};
				}

				return cropRegion;
			},

			_getImgNaturalSize(img) {
				var imageHeight = img.get('naturalHeight');
				var imageWidth = img.get('naturalWidth');

				if (
					Lang.isUndefined(imageHeight) ||
					Lang.isUndefined(imageWidth)
				) {
					var tmp = new Image();

					tmp.src = img.attr('src');

					imageHeight = tmp.height;
					imageWidth = tmp.width;
				}

				return {
					height: imageHeight,
					width: imageWidth
				};
			}
		};

		Liferay.CropRegion = CropRegion;
	},
	'',
	{
		requires: ['aui-base']
	}
);
