package ru.netology.data;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtilsData {
    private DbUtilsData() {
    }

    public static void cleanDatabase() {
        val delOrderEntity = "DELETE FROM order_entity;";
//        val delPaymentEntity = "DELETE FROM payment_entity;";
//        val delCreditRequestEntity = "DELETE FROM credit_request_entity;";
        val runner = new QueryRunner();

        try (
                val conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                );
        ) {
            val orderEntity = runner.update(conn, delOrderEntity);
//            val paymentEntity = runner.update(conn, delPaymentEntity);
//            val creditRequestEntity = runner.update(conn, delCreditRequestEntity);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static String getStatus(String dataCard) throws SQLException {
        val statusSQL = "SELECT status FROM payment_entity WHERE numberCard = ?;";
        val runner = new QueryRunner();
        String status = "APPROVED";

        try (
                val conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                );
        ) {
            val statusDb = runner.query(conn, statusSQL, new ScalarHandler<>(), dataCard);
            status = (String) statusDb;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    public static String getStatusDb(String dataCard) throws SQLException {
        val statusSQL = "SELECT status FROM credit_request_entity WHERE numberCard = ?;";
        val runner = new QueryRunner();
        String status = "DECLINED";

        try (
                val conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                );
        ) {
            val statusDb = runner.query(conn, statusSQL, new ScalarHandler<>(), dataCard);
            status = (String) statusDb;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    public static String countTotal() throws SQLException {
        val countSQL = "SELECT COUNT(*) FROM order_entity;";
        val runner = new QueryRunner();
        Long count = null;

        try (
                val conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                );
        ) {
            count = runner.query(conn, countSQL, new ScalarHandler<>());
        }
        return Long.toString(count);
    }
}