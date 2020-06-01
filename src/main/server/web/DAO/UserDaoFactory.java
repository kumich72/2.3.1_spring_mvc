package web.DAO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UserDaoFactory {
    public static IUserDAO CreateDao() {
        IUserDAO userDAO = null;
        try {
            String daoType = getDaoType();
            if (daoType.equals( "UserHibernateDAO")) {
                userDAO = new UserHibernateDAO(web.utils.DBHelper.getConfiguration());
            }
            if (daoType.equals("UserJdbcDAO")) {
                userDAO = new UserJdbcDAO(web.utils.DBHelper.getConnection());
            }
        }
        catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
        return userDAO;
    }

    private static String getDaoType() throws IOException {
        String result = "UserHibernateDAO";
        try {
            InputStream inputStream;

            Properties prop = new Properties();
            String propFileName = ".properties";

            inputStream = UserDaoFactory.class.getClassLoader().getResourceAsStream(propFileName);
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            result = prop.getProperty("daotype");
        }catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return result;
    }
}
