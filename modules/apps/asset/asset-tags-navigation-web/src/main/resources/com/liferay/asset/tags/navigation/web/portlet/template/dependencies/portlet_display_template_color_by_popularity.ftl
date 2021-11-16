<#if entries?has_content>
	<ul class="tag-items tag-list">
		<#assign
			scopeGroupId = getterUtil.getLong(scopeGroupId, themeDisplay.getScopeGroupId())
			classNameId = getterUtil.getLong(classNameId, 0)
			scopedAssetCounts = assetTagsNavigationDisplayContext.getScopedAssetCounts()

			maxCount = 1
			minCount = 1
		/>

		<#list entries as entry>
			<#if scopedAssetCounts??>
				<#assign assetCount = scopedAssetCounts[entry.getName()] />
			<#else>
				<#assign assetCount = entry.getAssetCount() />
			</#if>

			<#if (assetCount > 0) || getterUtil.getBoolean(showZeroAssetCount)>
				<#assign
					maxCount = liferay.max(maxCount, assetCount)
					minCount = liferay.min(minCount, assetCount)
				/>
			</#if>
		</#list>

		<#assign multiplier = 1 />

		<#if maxCount != minCount>
			<#assign multiplier = 3 / (maxCount - minCount) />
		</#if>

		<#list entries as entry>
			<#if scopedAssetCounts??>
				<#assign assetCount = scopedAssetCounts[entry.getName()] />
			<#else>
				<#assign assetCount = entry.getAssetCount() />
			</#if>

			<#if (assetCount > 0) || getterUtil.getBoolean(showZeroAssetCount)>
				<li class="taglib-asset-tags-summary">
					<#assign popularity = (maxCount - (maxCount - (assetCount - minCount))) * multiplier />

					<#if popularity < 1>
						<#assign color = "green" />
					<#elseif (popularity >= 1) && (popularity < 2)>
						<#assign color = "orange" />
					<#else>
						<#assign color = "red" />
					</#if>

					<#assign tagURL = renderResponse.createRenderURL() />

					${tagURL.setParameter("resetCur", "true")}
					${tagURL.setParameter("tag", entry.getName())}

					<a class="tag" href="${tagURL}" style="color: ${color};">
						${entry.getName()}

						<#if assetCount?? && getterUtil.getBoolean(showAssetCount)>
							<span class="tag-asset-count">(${assetCount})</span>
						</#if>
					</a>
				</li>
			</#if>
		</#list>
	</ul>

	<br style="clear: both;" />
</#if>