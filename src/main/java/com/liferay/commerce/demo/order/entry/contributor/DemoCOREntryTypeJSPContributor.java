package com.liferay.commerce.demo.order.entry.contributor;

import com.liferay.commerce.order.rule.entry.type.COREntryTypeJSPContributor;
import com.liferay.commerce.order.rule.model.COREntry;
import com.liferay.commerce.order.rule.service.COREntryLocalService;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.portal.kernel.util.*;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Jeff Handa
 */
@Component(
        immediate = true,
        property = "commerce.order.rule.entry.type.jsp.contributor.key=" + DemoCOREntryTypeJSPContributor.KEY,
        service = COREntryTypeJSPContributor.class
)
public class DemoCOREntryTypeJSPContributor implements COREntryTypeJSPContributor {

    public static final String KEY = "demo-order-rule";

    @Override
    public void render(
            long corEntryId, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse)
            throws Exception {

        COREntry corEntry = _corEntryLocalService.getCOREntry(corEntryId);

        UnicodeProperties typeSettingsUnicodeProperties =
                UnicodePropertiesBuilder.fastLoad(
                        corEntry.getTypeSettings()
                ).build();

        String holdFieldKey = GetterUtil.getString(typeSettingsUnicodeProperties.getProperty("hold-field"));

        if (Validator.isNull(holdFieldKey)){
            holdFieldKey = "on-hold-test";
        }

        httpServletRequest.setAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT, holdFieldKey);

        _jspRenderer.renderJSP(
                _servletContext, httpServletRequest, httpServletResponse,
                "/demo_rule.jsp");
    }

    @Reference
    private JSPRenderer _jspRenderer;

    @Reference(
            target = "(osgi.web.symbolicname=com.liferay.commerce.demo.order.entry.type)"
    )
    private ServletContext _servletContext;

    @Reference
    private COREntryLocalService _corEntryLocalService;
}
