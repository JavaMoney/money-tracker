<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>

    <%@include file="../common/style-includes.jsp" %>
</head>
<body>

<div class="pageWrapper">
    <%--<jsp:include page="../common/nav-bar.jsp"/>--%>

    <div class="pageBody">

        <div class="container">
            <div class="panel">
                <div class="panel-body">
                    <div class="row">
                        <div class="col-xs-2"><a href="/money-tracker-online/money-tracker">Home</a></div>
                        <div class="col-xs-8">
                            <div class="text-center h4">Total Remaining:</div>
                            <div class="text-center h3">${tracker.totalRemaining}</div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="panel">
                <div class="panel-body">
                    <div class="striped-list">
                        <div class="listing-item row">
                            <div class="col-xs-6">Total Spent:</div>
                            <div class="col-xs-6">${tracker.moneyForTheMonth - tracker.totalRemaining}</div>
                        </div>
                        <div class="listing-item row">
                            <div class="col-xs-6">Initial Amount:</div>
                            <div class="col-xs-6">${tracker.moneyForTheMonth}</div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="panel">
                <div class="panel-body">
                    <div class="row push-bottom-hard text-center">
                        <div class="col-xs-2">
                            <a class="transactionTrigger" href="#">
                                <span class="glyphicon glyphicon-chevron-down"></span>
                            </a>
                        </div>
                        <div class="col-xs-8">
                            <a class="transactionTrigger" href="#">
                                <span class="triggerText h5">Show Transaction History</span>
                            </a>
                        </div>
                        <div class="col-xs-2">
                            <a class="transactionTrigger" href="#">
                                <span class="glyphicon glyphicon-chevron-down"></span>
                            </a>
                        </div>
                    </div>

                    <div class="striped-list" id="transactionHistory" style="display:none;">
                        <c:forEach items="${tracker.transactionHistory}" var="transactionListing">
                            <div class="listing-item row">
                                <div class="col-xs-12">
                                    ${transactionListing.key} : ${tracker.getMoneySpentOn(transactionListing.key)}
                                </div>
                            </div>
                            <c:forEach items="${transactionListing.value}" var="transaction">
                                <div class="listing-item row">
                                    <div class="col-xs-offset-2">${transaction.amount}</div>
                                </div>
                            </c:forEach>
                        </c:forEach>
                    </div>
                </div>
            </div>

            <div class="panel">
                <div class="panel-body">
                    <div class="row">
                        <div class="col-xs-12">
                            <a class="btn btn-default" style="width:100%" href="<c:url value="/money-tracker/${tracker.uuid}/add-transaction"/>">Add Transaction</a>
                        </div>
                    </div>
                </div>
            </div>

        </div>


    </div>

</div>
<script type="text/javascript">
    $(document).ready(function(){
        $('a.transactionTrigger').on('click', function(){
            if(!$(this).hasClass('toggled')){
                $('#transactionHistory').slideDown();
                $(this).addClass('toggled');
                $('.transactionTrigger .glyphicon').removeClass('glyphicon-chevron-down').addClass('glyphicon-chevron-up');
                $('.transactionTrigger .triggerText').html('Hide Transaction History');
            }else{
                $('#transactionHistory').slideUp();
                $(this).removeClass('toggled');
                $('.transactionTrigger .glyphicon').removeClass('glyphicon-chevron-up').addClass('glyphicon-chevron-down');
                $('.transactionTrigger .triggerText').html('Show Transaction History');
            }
        })
    })
</script>

</body>
</html>