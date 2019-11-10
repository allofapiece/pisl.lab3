<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="context" value="${pageContext.request.contextPath}" />

<c:import url="/jsp/layout/header.jsp"/>

<a href="${context}/fc?command=show-random">I want to ask</a>
<a href="${context}/jsp/pages/addQuestion.jsp">Add new question</a>
<div id="content-title" style="margin-top: 15px;">
    <h2>Questions</h2>
    <hr>
</div>
<div class="d-flex flex-wrap flex-row">
    <c:forEach items="${wraps}" var="wrap">
        <c:import url="question/item.jsp">
            <c:param name="question" value="${wrap.question.question}"/>
            <c:param name="option1" value="${wrap.question.option1}"/>
            <c:param name="option2" value="${wrap.question.option2}"/>
            <c:param name="option3" value="${wrap.question.option3}"/>
            <c:param name="stat1" value="${wrap.stat1}"/>
            <c:param name="stat2" value="${wrap.stat2}"/>
            <c:param name="stat3" value="${wrap.stat3}"/>
        </c:import>
    </c:forEach>
</div>


<c:import url="/jsp/layout/footer.jsp"/>