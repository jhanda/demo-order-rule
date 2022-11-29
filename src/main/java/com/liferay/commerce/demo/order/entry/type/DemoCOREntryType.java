package com.liferay.commerce.demo.order.entry.type;

import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.order.rule.entry.type.COREntryType;
import com.liferay.commerce.order.rule.model.COREntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import org.osgi.service.component.annotations.Component;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Jeff Handa
 */
@Component(
        immediate = true,
        property = {
                "commerce.order.rule.entry.type.key=" + DemoCOREntryType.KEY,
                "commerce.order.rule.entry.type.order:Integer=10"
        },
        service = COREntryType.class
)
public class DemoCOREntryType implements COREntryType{

    public static final String KEY = "demo-order-rule";

    @Override
    public boolean evaluate(COREntry corEntry, CommerceOrder commerceOrder) throws PortalException {
        return false;
    }

    @Override
    public String getErrorMessage(COREntry corEntry, CommerceOrder commerceOrder, Locale locale) throws PortalException {
        ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
                "content.Language", locale, getClass());
        return LanguageUtil.get(resourceBundle, "demo-order-rule-error-message");
    }

    @Override
    public String getKey() {
        return KEY;
    }

    @Override
    public String getLabel(Locale locale) {
        ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
                "content.Language", locale, getClass());

        return LanguageUtil.get(resourceBundle, "demo-order-rule");
    }

}