<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="context" value="${pageContext.request.contextPath}" />

<c:import url="/jsp/layout/header.jsp"/>
<a href="${context}/fc?command=show-questions">Questions</a>
<div class="row">
    <div class="col-6 offset-3">
        <div class="card">
            <h5 class="card-title">${question.question}</h5>
            <div class="card-body">
                <form action="<c:url value="/fc?command=answer"/>" method="post">
                    <div class="form-check" style="margin-top: 15px;">
                        <input type="radio" name="option" value="1" class="form-check-input" id="option1-field" checked>
                        <label class="form-check-label" for="option1-field">${question.option1}</label>
                    </div>
                    <div class="form-check" style="margin-top: 15px;">
                        <input type="radio" name="option" value="2" class="form-check-input" id="option2-field">
                        <label class="form-check-label" for="option2-field">${question.option2}</label>
                    </div>
                    <div class="form-check" style="margin-top: 15px;">
                        <input type="radio" name="option" value="3" class="form-check-input" id="option3-field">
                        <label class="form-check-label" for="option3-field">${question.option3}</label>
                    </div>
                    <input type="hidden" name="question_id" value="${question.id}"/>
                    <button type="submit" class="btn btn-primary">Answer</button>
                </form>
            </div>
        </div>
    </div>
</div>

<c:import url="/jsp/layout/footer.jsp"/>