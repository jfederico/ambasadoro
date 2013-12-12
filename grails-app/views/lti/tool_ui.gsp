<html>
    <head>
        <title>Test ui</title>
    </head>
    <body>
        <h1>ExtraParameters UI</h1>
        <form name="extrap_form" action="/ambasadoro/lti/tool?oauth_nonce=${params.oauth_nonce}">
        <g:each in="${extraParameters}" status="i" var="extraParameter">
            <div>${extraParameter.name?.encodeAsHTML()}</div>&nbsp;&nbsp;
            <g:if test="${extraParameter.type == 'boolean'}"><input id='${extraParameter.name?.encodeAsHTML()}' name='${extraParameter.name?.encodeAsHTML()}' type='checkbox'<g:if test="${extraParameter.defaultValue == 'true'}"> checked</g:if>></g:if>
            <g:if test="${extraParameter.type == 'text'}"><input id='${extraParameter.name?.encodeAsHTML()}' name='${extraParameter.name?.encodeAsHTML()}' type='text' value='1'></g:if>
            <g:if test="${extraParameter.type == 'number'}"><input id='${extraParameter.name?.encodeAsHTML()}' name='${extraParameter.name?.encodeAsHTML()}' type='text' value='${extraParameter.defaultValue?.encodeAsHTML()}'></g:if>
            <br>
            <!-- ${extraParameter?.encodeAsHTML()} -->
        </g:each>
        </form>
    </body>
</html>