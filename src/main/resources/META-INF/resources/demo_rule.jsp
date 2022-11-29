<%@ include file="init.jsp" %>
<%
    String holdFieldKey = (String)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<div class="row">
    <div class="col">
        <commerce-ui:panel
                bodyClasses="flex-fill"
                title='<%= LanguageUtil.get(request, "configuration") %>'
        >
            <div class="row">
                <div class="col">
                    <aui:input label="on-hold-custom-field-key"
                               name='<%= "type--settings--hold-field--" %>'
                               required="<%= true %>"
                               type="text"
                               helpMessage="on-hold-custom-field-help"
                               value="<%= holdFieldKey %>">

                    </aui:input>
                </div>
            </div>
        </commerce-ui:panel>
    </div>
</div>