<html>
    <head>
        <title>Test ui</title>
    </head>
    <body>
        <h1>ExtraParameters UI</h1>
        <!-- ${isLearner?.encodeAsHTML()} -->
        <g:if test="${!isLearner}">
        <form name="extrap_form" action="/ambasadoro/lti/tool">
            <input name="nonce" type="hidden" value="${nonce}">
        <g:each in="${extraParameters}" status="i" var="extraParameter">
            <div>${extraParameter.name?.encodeAsHTML()}</div>&nbsp;&nbsp;
            <g:if test="${extraParameter.type == 'boolean'}"><input id='${extraParameter.name?.encodeAsHTML()}' name='${extraParameter.name?.encodeAsHTML()}' type='checkbox'<g:if test="${extraParameter.defaultValue == 'true'}"> checked</g:if>></g:if>
            <g:if test="${extraParameter.type == 'text'}"><input id='${extraParameter.name?.encodeAsHTML()}' name='${extraParameter.name?.encodeAsHTML()}' type='text' value='1'></g:if>
            <g:if test="${extraParameter.type == 'number'}"><input id='${extraParameter.name?.encodeAsHTML()}' name='${extraParameter.name?.encodeAsHTML()}' type='text' value='${extraParameter.defaultValue?.encodeAsHTML()}'></g:if>
            <br>
            <!-- ${extraParameter?.encodeAsHTML()} -->
        </g:each>
            <button name="submit" id="frm-save" type="submit" value="submit" class="btn-primary">Save</button>
            <button name="submit" id="frm-cancel" type="submit" value="cancel" class="btn-secondary">Cancel</button>
        </form>
        </g:if>
        <g:else>
        This launch has not been configured. An action from the instructor is necessary. 
        </g:else>
    </body>
</html>