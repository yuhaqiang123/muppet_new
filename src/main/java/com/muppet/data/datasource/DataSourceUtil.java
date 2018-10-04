package com.muppet.data.datasource;

import com.muppet.data.core.DataSourceException;
import com.muppet.data.sqlgenerate.ParamCanNotBeNullException;
import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.UUID;

public class DataSourceUtil {


    /**
     * &generateSimpleParameterMetadata=true
     */
    private String driverName = "com.mysql.jdbc.Driver";
    private String DRIVERNAME = "drivername";
    private String username = "root";
    private String USERNAME = "username";

    private String password = "yuhaiqiang";
    private String PASSWORD = "password";

    private String url = "jdbc:mysql://localhost:3306/data?Unicode=true&characterEncoding=utf-8";
    private String URL = "url";

    private int initialPoolSize = 5;
    private String INITIALPOOLSIZE = "initialpoolsize";

    private boolean autocommit = true;
    private String AUTOCOMMIT = "aotocommit";

    private int maxactive = 20;
    private String MAXACTIVE = "maxactive";

    private int maxidle = 5;
    private String MAXIDLE = "maxidle";

    private String MINIDLE = "minidle";
    private int minidle = 1;

    private String MAXWAIT = "maxwait";
    private int maxwait = 10000;

    private final String CAN_NOT_CONNECTED = "无法获取数据库连接，检查请检查数据库配置是否出现问题";

    private BasicDataSource source = new BasicDataSource();
    private boolean isinit = false;

    private DataSourceListener dataSourceListener;

    /**
     * 每次获取Connection时，将ConnectionId放到局部变量中
     */
    private final ThreadLocal<ConnectionRecord> connectionIdLocal = new ThreadLocal<>();

    public ThreadLocal<ConnectionRecord> getConnectionIdLocal() {
        return connectionIdLocal;
    }


    public DataSourceListener getDataSourceListener() {
        return dataSourceListener;
    }

    /**
     * 数据源key
     */
    private String dataSourceKey;


    public String getDataSourceKey() {
        return dataSourceKey;
    }


    public void isConnected() {
        Connection connection = null;
        try {
            connection = source.getConnection();

            DataSourceEvent event = new DataSourceEvent();
            event.setKey(dataSourceKey);
            event.setType(DataSourceListener.Type.BOOT_CONNECTED_SUCCESS);
            ConnectionRecord connectionRecord = new ConnectionRecord();
            connectionRecord.setConnectionId(UUID.randomUUID().toString());
            connectionRecord.setConnectionStartTime(System.currentTimeMillis());
            connectionRecord.setDataSourceKey(dataSourceKey);
            connectionIdLocal.set(connectionRecord);
            event.setConnectionRecord(connectionRecord);
            dataSourceListener.event(event);

        } catch (Throwable throwable) {
            DataSourceException e = new DataSourceException(throwable, CAN_NOT_CONNECTED);

            DataSourceEvent event = new DataSourceEvent();
            event.setError(e);
            event.setKey(dataSourceKey);
            event.setType(DataSourceListener.Type.BOOT_CONNECTED_ERROR);
            ConnectionRecord connectionRecord = new ConnectionRecord();
            connectionRecord.setConnectionStartTime(System.currentTimeMillis());
            connectionRecord.setConnectionEndTime(System.currentTimeMillis());
            connectionRecord.setConnectionId(UUID.randomUUID().toString());
            connectionRecord.setDataSourceKey(dataSourceKey);
            event.setConnectionRecord(connectionRecord);

            dataSourceListener.event(event);
            throw e;
        } finally {
            if (connection != null) {
                DataSourceEvent event = new DataSourceEvent();
                try {
                    connection.close();
                    event.setKey(dataSourceKey);
                    event.setType(DataSourceListener.Type.BOOT_CONNECTION_CLOSED_SUCCESS);

                    ConnectionRecord connectionRecord = connectionIdLocal.get();
                    connectionRecord.setConnectionEndTime(System.currentTimeMillis());
                    event.setConnectionRecord(connectionRecord);
                } catch (SQLException e) {
                    event.setKey(dataSourceKey);
                    event.setType(DataSourceListener.Type.BOOT_CONNECTION_CLOSED_ERROR);
                    event.setError(e);

                    ConnectionRecord connectionRecord = connectionIdLocal.get();
                    connectionRecord.setConnectionEndTime(System.currentTimeMillis());
                    event.setConnectionRecord(connectionRecord);
                }
                connectionIdLocal.remove();
                dataSourceListener.event(event);
            }
        }
    }

    /**
     * @param properties         数据源配置
     * @param dataSourceListener 监听数据源事件的处理器
     */
    public DataSourceUtil(Properties properties, DataSourceListener dataSourceListener) {
        setDataSourceListener(dataSourceListener);

        if (properties == null) {
            new ParamCanNotBeNullException("properties").printStackTrace();
            return;
        } else {


            if (properties.containsKey("datasource_name")) {
                dataSourceKey = properties.getProperty("datasource_name");
            }

            if (properties.containsKey(DRIVERNAME)) {
                driverName = properties.getProperty(DRIVERNAME);
            }

            if (properties.containsKey(AUTOCOMMIT)) {
                if (properties.getProperty(AUTOCOMMIT).equals("true")) {
                    autocommit = true;
                } else {
                    autocommit = false;
                }
            }

            if (properties.containsKey(USERNAME)) {
                username = properties.getProperty(USERNAME);
            }

            if (properties.containsKey(PASSWORD)) {
                password = properties.getProperty(PASSWORD);
            }

            if (properties.containsKey(INITIALPOOLSIZE)) {
                initialPoolSize = Integer.parseInt(properties.getProperty(INITIALPOOLSIZE));
            }

            if (properties.containsKey(URL)) {

                url = properties.getProperty(URL);
            }

            if (properties.containsKey(MAXWAIT)) {
                maxwait = Integer.parseInt(properties.getProperty(MAXWAIT));
            }

            if (properties.containsKey(MAXACTIVE)) {
                maxactive = Integer.parseInt(properties.getProperty(MAXACTIVE));
            }

            if (properties.containsKey(MAXIDLE)) {
                maxidle = Integer.parseInt(properties.getProperty(MAXIDLE));
            }

        }

        initial();
    }

    @SuppressWarnings("deprecation")
    private void initial() {
        /**
         * 初始化数据源
         */
        {
            // 2. 设置连接池对象
            // source.
            if (isinit == true) {
                return;
            }

            isinit = true;

            source.setDriverClassName(driverName);

            source.setUrl(url);

            source.setUsername(username);

            source.setPassword(password);

            source.setDefaultAutoCommit(autocommit);

            /**
             * ?
             */
            source.setDefaultTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

            source.setInitialSize(initialPoolSize);

            /**
             * 数据库最大连接数
             */
            source.setMaxActive(maxactive);

            /**
             * 最大空闲时间
             */
            source.setMaxIdle(maxidle);

            /**
             * 最小空闲时间
             */
            source.setMinIdle(minidle);

            /**
             * 最大等待时间
             */
            source.setMaxWait(maxwait);
        }
    }

    public void setDataSourceListener(DataSourceListener dataSourceListener) {
        this.dataSourceListener = dataSourceListener;
    }

    public Connection getConnection() throws SQLException {
        //initial();
        Connection connection = null;
        DataSourceEvent dataSourceEvent = new DataSourceEvent();
        try {
            connection = source.getConnection();

            dataSourceEvent.setType(DataSourceListener.Type.CONNECTED_SUCCESS);
            dataSourceEvent.setKey(dataSourceKey);
            String connectionId = UUID.randomUUID().toString();
            ConnectionRecord connectionRecord = new ConnectionRecord();
            connectionRecord.setConnectionId(connectionId);
            connectionRecord.setConnectionStartTime(System.currentTimeMillis());
            connectionRecord.setDataSourceKey(dataSourceKey);
            connectionRecord.setConnectionEndTime(-1);
            this.connectionIdLocal.set(connectionRecord);
            dataSourceEvent.setConnectionRecord(connectionRecord);
            dataSourceListener.event(dataSourceEvent);
        } catch (Throwable throwable) {
            DataSourceException e = new DataSourceException(throwable, CAN_NOT_CONNECTED);
            dataSourceEvent.setError(e);
            dataSourceEvent.setKey(dataSourceKey);

            dataSourceEvent.setType(DataSourceListener.Type.CONNECTED_ERROR);
            ConnectionRecord connectionRecord = new ConnectionRecord();
            connectionRecord.setConnectionStartTime(System.currentTimeMillis());
            connectionRecord.setDataSourceKey(dataSourceKey);
            connectionRecord.setConnectionEndTime(System.currentTimeMillis());
            dataSourceEvent.setConnectionRecord(connectionRecord);

            dataSourceListener.event(dataSourceEvent);
            throw e;
        }
        return connection;
    }


}
