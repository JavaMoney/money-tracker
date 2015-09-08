<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>

    <%@include file="../common/style-includes.jsp"%>
</head>
<body>

<div class="pageWrapper container">
    <div class="pageHeader">New Money Tracker</div>

    <div class="pageBody">

        <div class="col-md-6 col-md-offset-3">
            <form method="POST">

                <label for="input_startAmount">Starting amount:</label>
                <input type="number" id="input_startAmount" name="startAmount"/>
                <button type="submit" name="submit">Create money tracker</button>

            </form>
        </div>

    </div>
</div>


</body>
</html>