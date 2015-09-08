<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>

    <%@include file="../common/style-includes.jsp" %>

</head>
<body>

    <div class="pageWrapper container">
        <div class="pageHeading">Your Money Trackers</div>

        <div class="pageBody">

            <div class="row">
                <div class="col-md-2">
                    <a class="button" href="<c:url value='/money-tracker/create'/>">New Tracker</a>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    Date Created
                </div>
                <div class="col-md-4">
                    Starting Amount
                </div>
                <div class="col-md-4">
                    Total Remaining
                </div>
            </div>
            <c:forEach items="${user.moneyTrackers}" var="tracker">
                <a href="/money-tracker-online/money-tracker/${tracker.uuid}">
                <div class="row">
                    <div class="col-md-4">
                            ${tracker.dateTimeCreated}
                    </div>
                    <div class="col-md-4">
                            ${tracker.moneyForTheMonth}
                    </div>
                    <div class="col-md-4">
                            ${tracker.totalRemaining}
                    </div>
                </div>
                </a>
                <%--<div>
                    Transaction Breakdown:
                    <c:forEach items="${tracker.transactionHistory}" var="transactionListing">
                        <div>${transactionListing.key}</div>
                        <ul>
                            <c:forEach items="${transactionListing.value}" var="transaction">
                                <li>${transaction.amount}</li>
                            </c:forEach>
                        </ul>

                    </c:forEach>
                </div>--%>
            </c:forEach>
        </div>

    </div>

</body>
</html>