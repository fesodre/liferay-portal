/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.oauth.client.admin.web.internal.portlet.action;

import com.liferay.oauth.client.admin.web.internal.constants.OAuthClientAdminPortletKeys;
import com.liferay.oauth.client.persistence.model.OAuthClientASLocalMetadata;
import com.liferay.oauth.client.persistence.service.OAuthClientASLocalMetadataService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Arthur Chan
 */
@Component(
	property = {
		"javax.portlet.name=" + OAuthClientAdminPortletKeys.OAUTH_CLIENT_ADMIN,
		"mvc.command.name=/oauth_client_admin/update_oauth_client_as_local_metadata"
	},
	service = MVCActionCommand.class
)
public class UpdateOAuthClientASLocalMetadataMVCActionCommand
	implements MVCActionCommand {

	@Override
	public boolean processAction(
		ActionRequest actionRequest, ActionResponse actionResponse) {

		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			String localWellKnownURI = ParamUtil.getString(
				actionRequest, "localWellKnownURI");
			String metadataJSON = ParamUtil.getString(
				actionRequest, "metadataJSON");

			OAuthClientASLocalMetadata oAuthClientASLocalMetadata = null;

			if (Validator.isNull(localWellKnownURI)) {
				oAuthClientASLocalMetadata =
					_oAuthClientASLocalMetadataService.
						addOAuthClientASLocalMetadata(
							themeDisplay.getUserId(), metadataJSON,
							"openid-configuration");
			}
			else {
				oAuthClientASLocalMetadata =
					_oAuthClientASLocalMetadataService.
						getOAuthClientASLocalMetadata(
							themeDisplay.getCompanyId(), localWellKnownURI);

				_oAuthClientASLocalMetadataService.
					updateOAuthClientASLocalMetadata(
						oAuthClientASLocalMetadata.
							getOAuthClientASLocalMetadataId(),
						metadataJSON, "openid-configuration");
			}

			return true;
		}
		catch (PortalException portalException) {
			if (_log.isDebugEnabled()) {
				_log.debug(portalException);
			}

			Class<?> clazz = portalException.getClass();

			SessionErrors.add(actionRequest, clazz.getName(), portalException);

			return false;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UpdateOAuthClientASLocalMetadataMVCActionCommand.class);

	@Reference
	private OAuthClientASLocalMetadataService
		_oAuthClientASLocalMetadataService;

}