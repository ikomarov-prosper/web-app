<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <title>Pyramid</title>
    <link rel="stylesheet" type="text/css" href="js/main.css">
</head>
<body>
<div id="header">
    <span>Brick Symbol : </span>
    <select name="brickSymbol" id="brickSymbol">
        <option value="#">#</option>
        <option value="@">@</option>
        <option value="X">X</option>
        <option value="$">$</option>
    </select>

    <span>How high? </span>
    <input type="range" id="pyramidHeigh" value="5" min="1" max="10" step="1"></input>
    <span id="pyramidHeighLabel"></span>
</div>

<div>
    <table id="table"></table>
</div>

<div id='imageTest'></div>
<script src="js/main.js"></script>
</body>
</html>