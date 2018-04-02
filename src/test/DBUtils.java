package test;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.junit.Test;
import test.domain.User;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 *
 */
public class DBUtils {
    static ComboPooledDataSource dataSource = null;
    static QueryRunner qr = null;
    static {
        dataSource = new ComboPooledDataSource("mysql");
        qr = new QueryRunner(dataSource);
    }

    /**
     *
     * @param username
     * @param password
     * @return
     */
    public static boolean checkLogin(String username, String password){
        String sql = "select * from tbl_user where uname=? and upassword=?";
        try {
            User user = qr.query(sql,new BeanHandler<User>(User.class),username,password);
            if (user != null){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     *
     * @param username
     * @param password
     * @param email
     * @return
     */
    public static boolean checkReg(String username, String password, String email){
        String sql = "insert into tbl_user values(null,?,?,null,?,?,null);";
        try {
            int cols = qr.execute(sql,username,password,email,UUID.randomUUID().toString());    //uuid返回StringBuilder对象
            if (cols > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Object query(String username, String col){
        String sql = "select * from tbl_user where uname=?;";
        try {
            List<Object> results = qr.query(sql, new ColumnListHandler<>(col),username);
            if (!results.isEmpty()){
                return results.get(0);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static boolean updateMailAuth(String username){
        String sql = "update tbl_user set mailAuth=? where uname=?;";
        try {
            int cols = qr.update(sql,true,username);
            if (cols == 1){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /*@Test
    public void test(){
        for (int i = 8; i < 9;i++){
            String sql = "update tbl_user set uuid='"+ UUID.randomUUID()+"' where uid="+i;
            QueryRunner queryRunner = new QueryRunner(new ComboPooledDataSource("mysql"));
            try {
                int cols = queryRunner.update(sql);
                if (cols <= 0){
                    System.err.println("error"+i);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }*/
}
