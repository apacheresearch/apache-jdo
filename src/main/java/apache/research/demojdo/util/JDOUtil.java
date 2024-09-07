package apache.research.demojdo.util;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

public class JDOUtil {

    public static PersistenceManagerFactory persistenceManagerFactory =
            JDOHelper.getPersistenceManagerFactory("jdo-mysql.properties");

    public static PersistenceManagerFactory persistenceManagerFactoryExcel =
            JDOHelper.getPersistenceManagerFactory("jdo-excel.properties");

    public static PersistenceManagerFactory getPMFMysql() {
        return persistenceManagerFactory;
    }

    public static PersistenceManagerFactory getPMFExcel() {
        return persistenceManagerFactoryExcel;
    }
}
