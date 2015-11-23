package com.tracebucket.tron.embedded.mysql;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class EmbeddedMysqlDatabase extends DriverManagerDataSource {
/*    private final Logger logger = LoggerFactory.getLogger(EmbeddedMysqlDatabase.class);
    private final MysqldResource mysqldResource;

    public EmbeddedMysqlDatabase(MysqldResource mysqldResource) {
        this.mysqldResource = mysqldResource;
    }

    public void shutdown() {
        if (mysqldResource != null) {
            mysqldResource.shutdown();
            if (!mysqldResource.isRunning()) {
                logger.info(">>>>>>>>>> DELETING MYSQL BASE DIR [{}] <<<<<<<<<<", mysqldResource.getBaseDir());
                try {
                    FileUtils.forceDelete(mysqldResource.getBaseDir());
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }*/
}