<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <link href="/lib/bulma.min.css" rel="stylesheet"/>
    <link href="/css/spellcard.css" rel="stylesheet">
    <title>${spell.name}</title>
    <script src="https://kit.fontawesome.com/ee8c5603b4.js" crossorigin="anonymous"></script>
</head>
<body>

<div class="spellcard columns">
    <div class="card face column scroll-texture-0">
        <div class="card-header">
            <div class="flagpole">
                <c:forEach items="${spell.classRanks}" var="classRank">
                    <c:choose>
                        <c:when test="${classRank.playClass eq 'PRIEST'}">
                            <c:set var="playClassColor" value="#cccccc"/>
                        </c:when>
                        <c:when test="${classRank.playClass eq 'BARD'}">
                            <c:set var="playClassColor" value="#0003a8"/>
                        </c:when>
                        <c:when test="${classRank.playClass eq 'BARBARIAN'}">
                            <c:set var="playClassColor" value="#940000"/>
                        </c:when>
                        <c:when test="${classRank.playClass eq 'WARRIOR'}">
                            <c:set var="playClassColor" value="#734900"/>
                        </c:when>
                        <c:when test="${classRank.playClass eq 'WIZARD'}">
                            <c:set var="playClassColor" value="#00bfe6"/>
                        </c:when>
                        <c:when test="${classRank.playClass eq 'DRUID'}">
                            <c:set var="playClassColor" value="#f58f00"/>
                        </c:when>
                        <c:when test="${classRank.playClass eq 'MONK'}">
                            <c:set var="playClassColor" value="#00f5bc"/>
                        </c:when>
                        <c:when test="${classRank.playClass eq 'PALADIN'}">
                            <c:set var="playClassColor" value="#fa89eb"/>
                        </c:when>
                        <c:when test="${classRank.playClass eq 'ROUGE'}">
                            <c:set var="playClassColor" value="#dec400"/>
                        </c:when>
                        <c:when test="${classRank.playClass eq 'RANGER'}">
                            <c:set var="playClassColor" value="#008f00"/>
                        </c:when>
                        <c:when test="${classRank.playClass eq 'SORCERER'}">
                            <c:set var="playClassColor" value="#5d00a8"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="playClassColor" value="#000000"/>
                        </c:otherwise>
                    </c:choose>
                    <div class="fas fa-bookmark spell-class-rank" style="color:${playClassColor}">
                        <span>${classRank.rank}</span>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="card-header">
            <p class="card-header-title">
                ${spell.name}
            </p>
        </div>
        <div class="spell-params">
            <c:forEach items="${spell.parameters}" var="spellParameters">
                <div><b>${spellParameters.key}</b>: ${spellParameters.value}<br></div>
            </c:forEach>
        </div>
    </div>

    <div class="card back column scroll-texture-0">
        <div class="spell-description">
            <div class="content">
                ${spell.description}
            </div>
        </div>
    </div>
</div>

</body>
</html>