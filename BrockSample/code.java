public void addRequestComment(ActionRequest request, ActionResponse response) throws IOException, PortletException {
        TranslationServiceUtil i18n = TranslationServiceUtil.getInstance();
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        String language = themeDisplay.getLanguageId();
        try {
            String requestId = PortletUtil.getParameter(request, "request-id");
            String comment = PortletUtil.getParameter(request, "request-comment");
            User user = themeDisplay.getUser();
            String communitiesUserName = VMwareCommunitiesUtil.getUserNameByEmail(user.getEmailAddress());
            if (StringUtils.isNotBlank(requestId) && StringUtils.isNotBlank(communitiesUserName) && StringUtils.isNotBlank(comment)) {
                sampleService.addRequestComment(user, requestId, communitiesUserName, comment);
            }
            response.sendRedirect(UrlUtil.getUrl(request) + "?request&id=" + requestId + "#comments");
        } catch (Exception e) {
            _log.warn(e);
            request.setAttribute("errorMessage", i18n.translateText("dc.error.UnableToAddSampleRequestComment", "Unable to add the specified comment to the sample request.", language));
            response.setRenderParameter("jspPage", HTML_PATH + "/error.jsp");
        }
    }
