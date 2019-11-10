<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card" style="max-width: 18rem; min-width: 12rem; margin-right: .5rem;">
    <div class="card-body">
        <h5 class="card-title">${param.question}</h5>
        <p class="card-text">1. ${param.option1}(${param.stat1}%)</p>
        <p class="card-text">2. ${param.option2}(${param.stat2}%)</p>
        <p class="card-text">3. ${param.option3}(${param.stat3}%)</p>
    </div>
</div>
