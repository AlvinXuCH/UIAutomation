<html>
<header>
    <title>UI自动化测试报告</title>
    <!-- CSS Code -->
    <style type="text/css" scoped>
        table.GeneratedTable {
            width:100%;
            background-color:#FFFFFF;
            border-collapse:collapse;border-width:1px;
            border-color:#336600;
            border-style:solid;
            color:#009900;
        }

        table.GeneratedTable td, table.GeneratedTable th {
            border-width:1px;
            border-color:#336600;
            border-style:solid;
            padding:3px;
        }

        table.GeneratedTable thead {
            background-color:#CCFF99;
        }
    </style>
</header>
<body>
<!-- Codes by HTML.am -->
<p1><b>UI自动化测试报告</b></p1>
<p>Totol:   ${testsummary.total}</p>
<p>Pass:    ${testsummary.pass}</p>
<p>Fail:    ${testsummary.fail}</p>
<p>Skip:    ${testsummary.skip}</p>

<!-- HTML Code -->
<table class="GeneratedTable" id="testdetails">
    <thead>
    <tr>
        <th>类名</th>
        <th>测试方法</th>
        <th>开始时间</th>
        <th>时长</th>
        <th>执行结果</th>
        <th>错误信息</th>
    </tr>
    </thead>
    <tbody>
    <#list testresults as testresult>
    <tr>
        <td>${testresult.className}</td>
        <td>${testresult.methodName}</td>
        <td>${testresult.startTime}</td>
        <td>${testresult.duration}</td>
        <td>${testresult.result}</td>
        <td>${testresult.exception}</td>
    </tr>
    </#list>
    <tr>
        <td>Row 1, Cell 1</td>
        <td>Row 1, Cell 2</td>
        <td>Row 1, Cell 3</td>
        <td>Row 1, Cell 4</td>
        <td>Row 1, Cell 5</td>
        <td>Row 1, Cell 6</td>
    </tr>
    </tbody>
</table>

</body>
</html>
