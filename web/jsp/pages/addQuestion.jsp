<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="context" value="${pageContext.request.contextPath}" />

<c:import url="/jsp/layout/header.jsp"/>

<a href="${context}/fc?command=show-questions">Go back</a>
<div id="content-title" style="margin-top: 15px;">
    <h2>Add question</h2>
    <hr>
</div>
<div class="row">
    <div class="col-6">
        <form action="<c:url value="/fc?command=add-question"/>" method="post">
            <div class="form-group" style="margin-top: 15px;">
                <label for="question-field">Question</label>
                <input type="text" name="question" class="form-control" id="question-field" placeholder="Question">
            </div>
            <div class="form-group" style="margin-top: 15px;">
                <label for="option1-field">First option</label>
                <input type="text" name="option1" class="form-control" id="option1-field" placeholder="First option"/>
            </div>
            <div class="form-group" style="margin-top: 15px;">
                <label for="option2-field">Second option</label>
                <input type="text" name="option2" class="form-control" id="option2-field" placeholder="Second option">
            </div>
            <div class="form-group" style="margin-top: 15px;">
                <label for="option3-field">Third option</label>
                <input type="text" name="option3" class="form-control" id="option3-field" placeholder="Third option">
            </div>
            <button type="submit" class="btn btn-primary">Save question</button>
        </form>
    </div>
</div>

<c:import url="/jsp/layout/footer.jsp"/>