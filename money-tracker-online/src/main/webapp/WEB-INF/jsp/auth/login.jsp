<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Money Tracker | Login</title>

    <meta name="google-signin-scope" content="email">
    <meta name="google-signin-client_id" content="579578888422-s2am38top0sk6ouqb4dc5lncsfn2onv7.apps.googleusercontent.com">
    <script src="https://apis.google.com/js/platform.js" async defer></script>

    <%@include file="../common/style-includes.jsp" %>

</head>
<body>

<div class="pageWrapper">
    <jsp:include page="../common/nav-bar.jsp"/>

    <div class="pageBody">
        <div class="container">
            <div class="row">
                <div class="col-md-4 col-md-offset-4">
                    <div class="panel">
                        <div class="panel-heading">
                            <h3 class="text-center">Login to start Trackin yo' Monies</h3>
                        </div>
                        <div class="panel-body">
                            <div class="g-signin2" data-width="328" data-height="65" data-longtitle="true" data-onsuccess="onSignIn" data-theme="dark"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    function onSignIn(googleUser) {
        var profile = googleUser.getBasicProfile();
        document.getElementById('loginVal').value = profile.getEmail();
        document.getElementById('frmLogin').submit();
    };
</script>

<form method="POST" id="frmLogin">
    <input id="loginVal" type="hidden" name="emailAddress"/>
</form>

</body>
</html>