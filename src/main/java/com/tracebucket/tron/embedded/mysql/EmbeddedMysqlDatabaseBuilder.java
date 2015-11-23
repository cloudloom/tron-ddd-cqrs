package com.tracebucket.tron.embedded.mysql;

/**
 * Created by sadath on 09-Nov-2015.
 */
public class EmbeddedMysqlDatabaseBuilder {
/*    private final Logger LOG = LoggerFactory.getLogger(EmbeddedMysqlDatabaseBuilder.class);

    private final String baseDatabaseDir = System.getProperty("java.io.tmpdir");
    private String databaseName;
    private String host;
    private int port;
    private String username;
    private String password;
    private boolean foreignKeyCheck;

    private final ResourceLoader resourceLoader;
    private final ResourceDatabasePopulator databasePopulator;

    public EmbeddedMysqlDatabaseBuilder(final String host,
                                        final String dbName,
                                        final int port,
                                        final String username,
                                        final String password) {
        this.databaseName = dbName;
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        resourceLoader = new DefaultResourceLoader();
        databasePopulator = new ResourceDatabasePopulator();
        foreignKeyCheck = true;
    }

    private EmbeddedMysqlDatabase createDatabase(MysqldResource mysqldResource) {
        if (!mysqldResource.isRunning()) {
            LOG.error("MySQL instance not found... Terminating");
            throw new RuntimeException("Cannot get Datasource, MySQL instance not started.");
        }
        EmbeddedMysqlDatabase database = new EmbeddedMysqlDatabase(mysqldResource);
        database.setDriverClassName("com.mysql.jdbc.Driver");
        database.setUsername(username);
        database.setPassword(password);
        String url = "jdbc:mysql://" + host + ":" + port + "/" + databaseName + "?" + "createDatabaseIfNotExist=true";

        if (!foreignKeyCheck) {
            url += "&sessionVariables=FOREIGN_KEY_CHECKS=0";
        }
        LOG.debug("database url: {}", url);
        database.setUrl(url);
        return database;
    }

    private MysqldResource createMysqldResource() {
        if (LOG.isDebugEnabled()) {
            LOG.debug("=============== Starting Embedded MySQL using these parameters ===============");
            LOG.debug("baseDatabaseDir : " + baseDatabaseDir);
            LOG.debug("databaseName : " + databaseName);
            LOG.debug("host : " + host);
            LOG.debug("port : " + port);
            LOG.debug("username : " + username);
            LOG.debug("=============================================================================");
        }

        Map<String, String> databaseOptions = new HashMap<String, String>();
        databaseOptions.put(MysqldResourceI.PORT, Integer.toString(port));

        MysqldResource mysqldResource = new MysqldResource(new File(baseDatabaseDir, databaseName));
        mysqldResource.start("embedded-mysqld-thread-" + System.currentTimeMillis(), databaseOptions);

        if (!mysqldResource.isRunning()) {
            throw new RuntimeException("MySQL did not start.");
        }

        LOG.info("MySQL started successfully @ {}", System.currentTimeMillis());
        return mysqldResource;
    }

    private void populateScripts(EmbeddedMysqlDatabase database) {
        try {
            DatabasePopulatorUtils.execute(databasePopulator, database);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            database.shutdown();
        }
    }

    public EmbeddedMysqlDatabaseBuilder addSqlScript(String script) {
        databasePopulator.addScript(resourceLoader.getResource(script));
        return this;
    }

    *//**
     * whether to enable mysql foreign key check
     *
     * @param foreignKeyCheck
     *//*
    public EmbeddedMysqlDatabaseBuilder setForeignKeyCheck(boolean foreignKeyCheck) {
        this.foreignKeyCheck = foreignKeyCheck;
        return this;
    }

    *//**
     * @param databaseName
     *            the databaseName to set
     *//*
    public final void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public EmbeddedMysqlDatabase build() {
        EmbeddedMysqlDatabase database = null;
        try {
            MysqldResource mysqldResource = createMysqldResource();
            database = createDatabase(mysqldResource);
            populateScripts(database);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return database;
    }*/
}