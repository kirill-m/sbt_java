package ru.sbt.homework17.notifier.report_builder;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by kirill on 16.09.16
 */
public class HtmlPageBuilder implements ReportBuilder {
    private final ResultSet result;

    public HtmlPageBuilder(ResultSet result) {
        this.result = result;
    }

    public String build() {
        StringBuilder resultingHtml = new StringBuilder();
        addHeader(resultingHtml);
        double totals = processQuery(resultingHtml);
        addTotal(resultingHtml, totals);
        addFooter(resultingHtml);

        return resultingHtml.toString();
    }

    private void addHeader(StringBuilder sb) {
        sb.append(HtmlConst.TABLE_HEADER);
    }

    private void addStartTag(StringBuilder sb) {
        sb.append("<tr>");
    }

    private void addEndTag(StringBuilder sb) {
        sb.append("</tr>");
    }

    private void addEmployeeName(StringBuilder sb) throws SQLException {
        sb.append("<td>").append(result.getString("emp_name")).append("</td>");
    }

    private void addEmployeeSalary(StringBuilder sb) throws SQLException {
        sb.append("<td>").append(result.getDouble("salary")).append("</td>");
    }

    private Double getSalary() throws SQLException {
        return result.getDouble("salary");
    }

    private Double processQuery(StringBuilder sb) {
        double totals = 0;

        try {
            while (result.next()) {
                addStartTag(sb);
                addEmployeeName(sb);
                addEmployeeSalary(sb);
                addEndTag(sb);
                totals += getSalary();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Exception during getting data from Result Set", e);
        }

        return totals;
    }

    private void addTotal(StringBuilder sb, double total) {
        sb.append(HtmlConst.TABLE_TOTAL).append(total).append("</td></tr>");
    }

    private void addFooter(StringBuilder sb) {
        sb.append(HtmlConst.PAGE_FOOTER);
    }

}
