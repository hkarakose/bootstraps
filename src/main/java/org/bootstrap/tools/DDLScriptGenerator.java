package org.bootstrap.tools;

import org.bootstrap.bean.Dummy;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 * User: halil
 * Date: 9/19/13
 * Time: 7:10 PM
 */
public class DDLScriptGenerator {
    public static void main(String[] args) {
        runSchemaGeneration();
    }

    public static void runSchemaGeneration() {
        Configuration cfg = new Configuration();
        cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
        cfg.setProperty("hibernate.format_sql", "true");
        cfg.addAnnotatedClass(Dummy.class);

        SchemaExport export = new SchemaExport(cfg);
        export.setOutputFile("src/main/resources/sql/ddl.sql");
        export.setDelimiter(";");
        export.execute(true, false, false, true);
    }
}
