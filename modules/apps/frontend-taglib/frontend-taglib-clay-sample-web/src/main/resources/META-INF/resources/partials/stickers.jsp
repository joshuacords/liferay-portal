<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<blockquote>
	<p>Stickers are a visual way to quickly identify content in a different way than badges and labels.</p>
</blockquote>

<h3>SQUARE</h3>

<div class="mb-3 row text-center">
	<div class="col-md-1">
		<clay:sticker
			label="JPG"
		/>
	</div>

	<div class="col-md-1">
		<clay:sticker
			icon="picture"
		/>
	</div>
</div>

<h3>ROUND</h3>

<div class="mb-3 row text-center">
	<div class="col-md-1">
		<clay:sticker
			label="JPG"
			shape="circle"
		/>
	</div>

	<div class="col-md-1">
		<clay:sticker
			icon="picture"
			shape="circle"
		/>
	</div>
</div>

<h3>POSITION</h3>

<div class="mb-3 row">
	<div class="col-md-2">
		<div class="aspect-ratio">
			<img class="aspect-ratio-item-fluid" src="https://claycss.com/images/thumbnail_hot_air_ballon.jpg" />

			<clay:sticker
				label="PDF"
				position="top-left"
				style="danger"
			/>
		</div>
	</div>

	<div class="col-md-2">
		<div class="aspect-ratio">
			<img class="aspect-ratio-item-fluid" src="https://claycss.com/images/thumbnail_hot_air_ballon.jpg" />

			<clay:sticker
				label="PDF"
				position="bottom-left"
				style="danger"
			/>
		</div>
	</div>

	<div class="col-md-2">
		<div class="aspect-ratio">
			<img class="aspect-ratio-item-fluid" src="https://claycss.com/images/thumbnail_hot_air_ballon.jpg" />

			<clay:sticker
				label="PDF"
				position="top-right"
				style="danger"
			/>
		</div>
	</div>

	<div class="col-md-2">
		<div class="aspect-ratio">
			<img class="aspect-ratio-item-fluid" src="https://claycss.com/images/thumbnail_hot_air_ballon.jpg" />

			<clay:sticker
				label="PDF"
				position="bottom-right"
				style="danger"
			/>
		</div>
	</div>
</div>