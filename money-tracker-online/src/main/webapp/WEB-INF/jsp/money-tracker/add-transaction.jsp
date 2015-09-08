<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>

    <%@include file="../common/style-includes.jsp"%>
</head>
<body>

    <div class="pageWrapper container">
        <div class="pageHeading">New Transaction</div>
        <div class="pageBody">
            <div class="row">
                <form method="POST">
                    <div class="col-md-4 col-md-offset-2">
                        <label for="input_amount">Transaction amount:</label>
                        <input type="number" id="input_amount" name="amount"/>
                    </div>
                    <div class="col-md-4">
                        <label for="input_category">Category:</label>
                        <input type="text" id="input_category" name="category"/>
                    </div>
                    <div class="col-md-2">
                        <button type="submit">Create</button>
                    </div>

                </form>
            </div>
        </div>
    </div>

</body>
</html>