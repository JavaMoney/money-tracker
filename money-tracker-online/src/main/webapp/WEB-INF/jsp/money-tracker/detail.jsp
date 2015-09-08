<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>

    <%@include file="../common/style-includes.jsp"%>
</head>
<body>
    <div class="pageWrapper container">
        <div class="pageHeading">
            <div class="row">
                Tracker Breakdown
            </div>
        </div>

        <div class="pageBody">

            <div class="row">
                Starting Amount: ${tracker.moneyForTheMonth}
            </div>
            <div class="row">
                Total spent: ${tracker.moneyForTheMonth - tracker.totalRemaining}
            </div>
            <div class="row">
                Balance: ${tracker.totalRemaining}
            </div>

            <div class="row">
                <a class="button" href="<c:url value="/money-tracker/${tracker.uuid}/add-transaction"/>">Add Transaction</a>
            </div>

            <div class="row">
                Transaction Breakdown:
                <c:forEach items="${tracker.transactionHistory}" var="transactionListing">
                    <div>${transactionListing.key} - Total Spent: ${tracker.getMoneySpentOn(transactionListing.key)}</div>
                    <ul>
                        <c:forEach items="${transactionListing.value}" var="transaction">
                            <li>${transaction.amount}</li>
                        </c:forEach>
                    </ul>

                </c:forEach>
            </div>
        </div>
    </div>
</body>
</html>