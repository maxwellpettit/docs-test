package org.jumpmind.pos;

import org.apache.commons.io.FileUtils;
import org.jumpmind.db.model.Column;
import org.jumpmind.db.model.Table;
import org.jumpmind.pos.persist.TableDef;
import org.jumpmind.pos.persist.impl.DatabaseSchema;
import org.jumpmind.pos.persist.impl.ModelClassMetaData;
import org.jumpmind.pos.service.IModule;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.jumpmind.pos.ModelMarkDownGeneratorConstants.*;
import static org.jumpmind.pos.service.util.ClassUtils.getClassesForPackageAndAnnotation;

public class ModelMarkDownGenerator {

    boolean autoGenerateExamples = true;
    String outputDir = "";

    StringBuilder markdown = new StringBuilder();

    Queue<Class<?>> models = new LinkedList<Class<?>>();

    public ModelMarkDownGenerator() {
    }

    public void setAutoGenerateExamples(boolean autoGenerateExamples) {
        this.autoGenerateExamples = autoGenerateExamples;
    }

    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }

    public void document(Class<?> moduleClass, String name) throws Exception {

        IModule module = (IModule) moduleClass.newInstance();
        String id = module.getName().toLowerCase();

        markdown.append("## ").append(name).append("\n\n");
        
        List<Class<?>> models = getClassesForPackageAndAnnotation(moduleClass.getPackage().getName(), TableDef.class);
        Set<Table> tables = new TreeSet<>();
        for (Class<?> model : models) {
            List<ModelClassMetaData> metadatas = DatabaseSchema.createMetaData(model, Collections.emptyList()).getModelClassMetaData();
            for (ModelClassMetaData entityMetaData : metadatas) {
                tables.add(entityMetaData.getTable());
            }
        }

        for (Table table : tables) {
            document(module.getTablePrefix(),table);
            markdown.append("\\newpage\n");
        }
    }

    public void document(String prefix, Table table) {
        markdown.append(MODEL_HEADING_START + (prefix + "_" + table.getName()).toUpperCase()).append(LINE_SKIP);
        markdown.append(table.getDescription()).append(LINE_SKIP);
        markdown.append(COLUMN_TABLE_HEADING + "\n");
        markdown.append(COLUMN_TABLE_DIVIDER + "\n");
        Column[] columns = table.getPrimaryKeyColumns();
        for (Column column : columns) {
            documentColumn(column);
        }

        columns = table.getColumns();
        for (Column column : columns) {
            if (!column.isPrimaryKey()) {
                documentColumn(column);
            }
        }
        markdown.append(LINE_SKIP);
    }
    
    protected void documentColumn(Column column) {
        markdown.append(TABLE_DIVISION);
        markdown.append(column.getName().toUpperCase());
        markdown.append(TABLE_DIVISION);
        markdown.append(column.isPrimaryKey() ? "X" : " ");
        markdown.append(TABLE_DIVISION);
        markdown.append(column.getJdbcTypeName());
        markdown.append(TABLE_DIVISION);
        markdown.append(column.getSize() == null ? "" : column.getSize());
        markdown.append(TABLE_DIVISION);

        markdown.append(column.getDescription());
        markdown.append(TABLE_DIVISION);
        markdown.append("\n");
    }

    public void finish(String filename) throws IOException {
        String header = FileUtils.readFileToString(new File("src/main/resources/datamodel-header.md"), "UTF-8").
                replaceAll("\\$\\{date\\}", new SimpleDateFormat("MMM d, yyyy").format(new java.util.Date()));

        BufferedWriter writer = null;
        if (outputDir != null)
            writer = new BufferedWriter(new FileWriter(outputDir + filename));
        else
            writer = new BufferedWriter(new FileWriter(filename));
        writer.write(header);
        writer.write(markdown.toString());
        writer.close();
        markdown.setLength(0);
    }
}
