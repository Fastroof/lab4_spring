<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <jsp:include page="head.jsp"/>
    <title><c:out value="${msg}"/></title>
    <style>
        .btn {
            text-transform: unset !important;
        }
    </style>
</head>
<body>
<svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
    <symbol id="check-circle-fill" fill="currentColor" viewBox="0 0 16 16">
        <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"></path>
    </symbol>
    <symbol id="exclamation-triangle-fill" fill="currentColor" viewBox="0 0 16 16">
        <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"></path>
    </symbol>
</svg>
<div class="container" style="align-items: center; display: flex; flex-direction: column;">
    <c:choose>
        <c:when test="${message_number == 0}">
            <div class="modal modal-alert d-block bg-secondary py-5" tabindex="-1" role="dialog" id="modalChoice">
                <div class="modal-dialog" role="document">
                    <div class="modal-content rounded-3 shadow">
                        <div class="alert alert-success d-flex align-items-center pt-4 pb-4 m-0 rounded-0 rounded-top">
                            <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Success:"><use href="#check-circle-fill"></use></svg>
                            <p class="mb-0"><c:out value="${msg}"/></p>
                        </div>
                        <div class="modal-footer flex-nowrap justify-content-center p-0">
                            <c:if test="${link == '/login'}">
                                <a href="/login" type="button" class="btn btn-lg btn-link fs-6 text-decoration-none m-0 rounded-0"><strong><c:out value="${text}"/></strong></a>
                            </c:if>
                            <c:if test="${link == '/registration'}">
                                <a href="/registration" type="button" class="btn btn-lg btn-link fs-6 text-decoration-none m-0 rounded-0"><strong><c:out value="${text}"/></strong></a>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </c:when>
        <c:when test="${message_number == 1}">
            <div class="modal modal-alert d-block bg-secondary py-5" tabindex="-1" role="dialog" id="modalChoice">
                <div class="modal-dialog" role="document">
                    <div class="modal-content rounded-3 shadow">
                        <div class="alert alert-warning d-flex align-items-center pt-4 pb-4 m-0 rounded-0 rounded-top">
                            <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Warning:"><use href="#exclamation-triangle-fill"></use></svg>
                            <p class="mb-0"><c:out value="${msg}"/></p>
                        </div>
                        <div class="modal-footer flex-nowrap justify-content-center p-0">
                            <c:if test="${link == '/login'}">
                                <a href="/login" type="button" class="btn btn-lg btn-link fs-6 text-decoration-none m-0 rounded-0"><strong><c:out value="${text}"/></strong></a>
                            </c:if>
                            <c:if test="${link == '/registration'}">
                                <a href="/registration" type="button" class="btn btn-lg btn-link fs-6 text-decoration-none m-0 rounded-0"><strong><c:out value="${text}"/></strong></a>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </c:when>
        <c:when test="${message_number == null}">
            <h3><c:out value="${msg}"/></h3>
            <h4>
                <c:if test="${link == '/login'}">
                    <a href="/login"><c:out value="${text}"/></a>
                </c:if>
                <c:if test="${link == '/registration'}">
                    <a href="/registration"><c:out value="${text}"/></a>
                </c:if>
            </h4>
        </c:when>
        <c:otherwise>
            <h6>Упс, щось трапилось не те, чого очікували ви і ми</h6>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>