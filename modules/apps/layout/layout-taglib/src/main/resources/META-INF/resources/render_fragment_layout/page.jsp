<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/render_fragment_layout/init.jsp" %>

<%
Layout draftLayout = (Layout)request.getAttribute("liferay-layout:render-fragment-layout:draftLayout");
Map<String, Object> fieldValues = (Map<String, Object>)request.getAttribute("liferay-layout:render-fragment-layout:fieldValues");
String mode = (String)request.getAttribute("liferay-layout:render-fragment-layout:mode");
List<String> nonIndexableFragmentEntryLinkIds = (List<String>)request.getAttribute("liferay-layout:render-fragment-layout:nonIndexableFragmentEntryLinkIds");
long previewClassPK = (long)request.getAttribute("liferay-layout:render-fragment-layout:previewClassPK");
int previewType = (int)request.getAttribute("liferay-layout:render-fragment-layout:previewType");
long[] segmentsExperienceIds = (long[])request.getAttribute("liferay-layout:render-fragment-layout:segmentsExperienceIds");
JSONArray structureJSONArray = (JSONArray)request.getAttribute("liferay-layout:render-fragment-layout:structureJSONArray");

RenderFragmentLayoutDisplayContext renderFragmentLayoutDisplayContext = new RenderFragmentLayoutDisplayContext(request, response);
%>

<c:if test="<%= structureJSONArray != null %>">
	<div class="layout-content portlet-layout" id="main-content" role="main">

		<%
		try {
			request.setAttribute(WebKeys.PORTLET_DECORATE, Boolean.FALSE);

			if (draftLayout != null) {
				request.setAttribute("DRAFT_LAYOUT", draftLayout);
			}

			for (int i = 0; i < structureJSONArray.length(); i++) {
				JSONObject rowJSONObject = structureJSONArray.getJSONObject(i);

				JSONObject rowConfigJSONObject = rowJSONObject.getJSONObject("config");

				if (rowConfigJSONObject != null) {
					boolean nonIndexable = rowConfigJSONObject.getBoolean("nonIndexable", false);

					if (nonIndexable && Objects.equals(mode, FragmentEntryLinkConstants.SEARCH)) {
						continue;
					}
				}

				int type = rowJSONObject.getInt("type", FragmentConstants.TYPE_COMPONENT);
		%>

				<c:choose>
					<c:when test="<%= type == FragmentConstants.TYPE_COMPONENT %>">

						<%
						String backgroundColorCssClass = StringPool.BLANK;
						String backgroundImage = StringPool.BLANK;
						boolean columnSpacing = true;
						String containerType = StringPool.BLANK;
						long paddingHorizontal = 3L;
						long paddingVertical = 3L;

						if (rowConfigJSONObject != null) {
							backgroundColorCssClass = rowConfigJSONObject.getString("backgroundColorCssClass");
							backgroundImage = renderFragmentLayoutDisplayContext.getBackgroundImage(rowConfigJSONObject);
							columnSpacing = rowConfigJSONObject.getBoolean("columnSpacing", true);
							containerType = rowConfigJSONObject.getString("containerType");
							paddingHorizontal = rowConfigJSONObject.getLong("paddingHorizontal", paddingHorizontal);
							paddingVertical = rowConfigJSONObject.getLong("paddingVertical", paddingVertical);
						}
						%>

						<section class="bg-<%= backgroundColorCssClass %>" style="<%= Validator.isNotNull(backgroundImage) ? "background-image: url(" + backgroundImage + "); background-position: 50% 50%; background-repeat: no-repeat; background-size: cover;" : StringPool.BLANK %>">
							<div class="<%= Objects.equals(containerType, "fluid") ? "container-fluid" : "container" %> px-<%= paddingHorizontal %> py-<%= paddingVertical %>">
								<div class="row <%= !columnSpacing ? "no-gutters" : StringPool.BLANK %>">

									<%
									JSONArray columnsJSONArray = rowJSONObject.getJSONArray("columns");

									for (int j = 0; j < columnsJSONArray.length(); j++) {
										JSONObject columnJSONObject = columnsJSONArray.getJSONObject(j);

										String size = columnJSONObject.getString("size");
									%>

										<div class="<%= Validator.isNotNull(size) ? "col-md-" + size : StringPool.BLANK %>">

											<%
											JSONArray fragmentEntryLinkIdsJSONArray = columnJSONObject.getJSONArray("fragmentEntryLinkIds");

											for (int k = 0; k < fragmentEntryLinkIdsJSONArray.length(); k++) {
												long fragmentEntryLinkId = fragmentEntryLinkIdsJSONArray.getLong(k);

												if (fragmentEntryLinkId <= 0) {
													continue;
												}

												FragmentEntryLink fragmentEntryLink = FragmentEntryLinkLocalServiceUtil.fetchFragmentEntryLink(fragmentEntryLinkId);

												if (fragmentEntryLink == null) {
													continue;
												}

												if (Objects.equals(mode, FragmentEntryLinkConstants.SEARCH) && nonIndexableFragmentEntryLinkIds.contains(String.valueOf(fragmentEntryLinkId))) {
													continue;
												}

												FragmentRendererController fragmentRendererController = (FragmentRendererController)request.getAttribute(FragmentActionKeys.FRAGMENT_RENDERER_CONTROLLER);

												DefaultFragmentRendererContext defaultFragmentRendererContext = new DefaultFragmentRendererContext(fragmentEntryLink);

												defaultFragmentRendererContext.setFieldValues(fieldValues);
												defaultFragmentRendererContext.setLocale(locale);
												defaultFragmentRendererContext.setMode(mode);
												defaultFragmentRendererContext.setPreviewClassPK(previewClassPK);
												defaultFragmentRendererContext.setPreviewType(previewType);
												defaultFragmentRendererContext.setSegmentsExperienceIds(segmentsExperienceIds);
											%>

												<%= fragmentRendererController.render(defaultFragmentRendererContext, request, response) %>

											<%
											}
											%>

										</div>

									<%
									}
									%>

								</div>
							</div>
						</section>
					</c:when>
					<c:otherwise>
						<section>

							<%
							JSONArray columnsJSONArray = rowJSONObject.getJSONArray("columns");

							for (int j = 0; j < columnsJSONArray.length(); j++) {
								JSONObject columnJSONObject = columnsJSONArray.getJSONObject(j);

								JSONArray fragmentEntryLinkIdsJSONArray = columnJSONObject.getJSONArray("fragmentEntryLinkIds");

								for (int k = 0; k < fragmentEntryLinkIdsJSONArray.length(); k++) {
									long fragmentEntryLinkId = fragmentEntryLinkIdsJSONArray.getLong(k);

									if (fragmentEntryLinkId <= 0) {
										continue;
									}

									FragmentEntryLink fragmentEntryLink = FragmentEntryLinkLocalServiceUtil.fetchFragmentEntryLink(fragmentEntryLinkId);

									if (fragmentEntryLink == null) {
										continue;
									}

									if (Objects.equals(mode, FragmentEntryLinkConstants.SEARCH) && nonIndexableFragmentEntryLinkIds.contains(String.valueOf(fragmentEntryLinkId))) {
										continue;
									}

									FragmentRendererController fragmentRendererController = (FragmentRendererController)request.getAttribute(FragmentActionKeys.FRAGMENT_RENDERER_CONTROLLER);

									DefaultFragmentRendererContext defaultFragmentRendererContext = new DefaultFragmentRendererContext(fragmentEntryLink);

									defaultFragmentRendererContext.setFieldValues(fieldValues);
									defaultFragmentRendererContext.setLocale(locale);
									defaultFragmentRendererContext.setMode(mode);
									defaultFragmentRendererContext.setPreviewClassPK(previewClassPK);
									defaultFragmentRendererContext.setPreviewType(previewType);
									defaultFragmentRendererContext.setSegmentsExperienceIds(segmentsExperienceIds);
							%>

									<%= fragmentRendererController.render(defaultFragmentRendererContext, request, response) %>

							<%
								}
							}
							%>

						</section>
					</c:otherwise>
				</c:choose>

		<%
			}
		}
		finally {
			request.removeAttribute(WebKeys.PORTLET_DECORATE);
		}
		%>

	</div>
</c:if>