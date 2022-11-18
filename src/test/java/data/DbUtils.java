package data;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;

@UtilityClass
public class DbUtils {

    private static QueryRunner runner = new QueryRunner();

    @SneakyThrows
    private static Connection getConn() {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/app_db", "user", "password");
    }

    @SneakyThrows
    public static void cleanDB() {
        String authCodeSQL = "DELETE FROM auth_codes";
        String cardTransactionsSQL = "DELETE FROM card_transactions";
        String cardsSQL = "DELETE FROM cards";
        String usersSQL = "DELETE FROM users";
        try (var conn = getConn()) {
            runner.execute(conn, authCodeSQL);
            runner.execute(conn, cardTransactionsSQL);
            runner.execute(conn, cardsSQL);
            runner.execute(conn, usersSQL);
        }
    }

    @SneakyThrows
    public String getVerificationCode() {
        var codeSQL = "SELECT code FROM auth_codes ORDER BY created DESC LIMIT 1;";
        var runner = new QueryRunner();

        try (var conn = getConn()) {
            var code = runner.query(conn, codeSQL, new ScalarHandler<String>());
            return String.valueOf(Integer.parseInt(code));
        }
    }
}
