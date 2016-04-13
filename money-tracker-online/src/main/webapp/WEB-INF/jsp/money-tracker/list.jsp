<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>

    <%@include file="../common/style-includes.jsp" %>
    <style>
        .row.listing-item:nth-of-type(odd) {
            background: #e0e0e0;
        }
    </style>

</head>
<body>

<div class="pageWrapper">
    <jsp:include page="../common/nav-bar.jsp"/>

    <div class="pageBody">

        <div class="container panel">
            <div class="panel-body">
                <h2>Your Money Trackers</h2>

                <div class="striped-list">
                    <div class="row listing-item">
                        <div class="col-xs-4">
                            Date Created
                        </div>
                        <div class="col-xs-4">
                            Starting Amount
                        </div>
                        <div class="col-xs-4">
                            Total Remaining
                        </div>
                    </div>
                    <c:forEach items="${user.moneyTrackers}" var="tracker">
                        <div class="row listing-item">
                            <a href="/money-tracker-online/money-tracker/${tracker.uuid}">

                                <div class="col-xs-4">
                                        ${tracker.dateTimeCreated}
                                </div>
                                <div class="col-xs-4">
                                        ${tracker.moneyForTheMonth}
                                </div>
                                <div class="col-xs-4">
                                        ${tracker.totalRemaining}
                                </div>
                            </a>

                        </div>
                    </c:forEach>
                </div>


                <div class="row">
                    <div class="col-md-2 push-top-hard">
                        <a class="btn btn-default" href="<c:url value='/money-tracker/create'/>">New Tracker</a>
                    </div>
                </div>
            </div>
        </div>

    </div>

</div>

</body>
</html>