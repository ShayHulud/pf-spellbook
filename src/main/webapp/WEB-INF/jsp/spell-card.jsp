<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <link href="./lib/bulma.min.css" rel="stylesheet"/>
    <link href="./css/spellcard.css" rel="stylesheet">
    <title>${spell.name}</title>
</head>
<body>

<div class="spellcard columns">
    <div class="card face column scroll-texture-0">
        <div class="card-header">
            <div class="flagpole">
                <c:forEach items="${spell.classRanks}" var="classRank">
                    <c:choose>
                        <c:when test="${classRank.playClass == PlayClass.PRIEST}">
                            <c:set var="playClassColor" value="#ffffff"/>
                        </c:when>
                        <c:when test="${classRank.playClass == PlayClass.BARD}">
                            <c:set var="playClassColor" value="#0003a8"/>
                        </c:when>
                        <c:when test="${classRank.playClass eq PlayClass.BARBARIAN}">
                            <c:set var="playClassColor" value="#940000"/>
                        </c:when>
                        <c:when test="${classRank.playClass eq PlayClass.WARRIOR}">
                            <c:set var="playClassColor" value="#734900"/>
                        </c:when>
                        <c:when test="${classRank.playClass eq PlayClass.WIZARD}">
                            <c:set var="playClassColor" value="#00bfe6"/>
                        </c:when>
                        <c:when test="${classRank.playClass eq PlayClass.DRUID}">
                            <c:set var="playClassColor" value="#f58f00"/>
                        </c:when>
                        <c:when test="${classRank.playClass eq PlayClass.MONK}">
                            <c:set var="playClassColor" value="#00f5bc"/>
                        </c:when>
                        <c:when test="${classRank.playClass eq PlayClass.PALADIN}">
                            <c:set var="playClassColor" value="#fa89eb"/>
                        </c:when>
                        <c:when test="${classRank.playClass eq PlayClass.ROUGE}">
                            <c:set var="playClassColor" value="#dec400"/>
                        </c:when>
                        <c:when test="${classRank.playClass eq PlayClass.RANGER}">
                            <c:set var="playClassColor" value="#008f00"/>
                        </c:when>
                        <c:when test="${classRank.playClass eq PlayClass.SORCERER}">
                            <c:set var="playClassColor" value="#5d00a8"/>
                        </c:when>
                    </c:choose>
                    <div class="spell-class-rank" style="background-color:${playClassColor}">
                            ${classRank.rank}
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="card-header">
            <p class="card-header-title">
                ${spell.name}
            </p>
        </div>
        <div class="spell-params">prop</div>
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