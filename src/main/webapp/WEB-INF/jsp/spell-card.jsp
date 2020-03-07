<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" %>
<%@taglib prefix="spell" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
    <link href="/lib/bulma.min.css" rel="stylesheet"/>
    <link href="/css/spellcard.css" rel="stylesheet">
    <title>${spell.name}</title>
    <script src="https://kit.fontawesome.com/ee8c5603b4.js" crossorigin="anonymous"></script>
</head>
<body>
<spell:card-render-template spell="${spell}"/>
</body>
</html>