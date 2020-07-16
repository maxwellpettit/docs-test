package org.jumpmind.pos;

import org.jumpmind.pos.context.ContextModule;
import org.jumpmind.pos.customer.CustomerModule;
import org.jumpmind.pos.devices.DevicesModule;
import org.jumpmind.pos.document.DocumentModule;
import org.jumpmind.pos.i18n.i18nModule;
import org.jumpmind.pos.item.ItemModule;
import org.jumpmind.pos.ops.OpsModule;
import org.jumpmind.pos.payment.PaymentModule;
import org.jumpmind.pos.promotion.PromotionModule;
import org.jumpmind.pos.sales.SalesModule;
import org.jumpmind.pos.tax.TaxModule;
import org.jumpmind.pos.user.UserModule;
import org.jumpmind.pos.vertex.VertexModule;

public class GenerateDocs {
    public static void main(String...args) throws Exception {
        ApplicationYamlDocumenter yaml = new ApplicationYamlDocumenter();
        yaml.render("/application.yml", "build/docs/JumpMind-Commerce-Parameters.html");

        ModelMarkDownGenerator modelGenerator = new ModelMarkDownGenerator();
        modelGenerator.setOutputDir("build/docs/");
        modelGenerator.document(DevicesModule.class, "Devices Module");
        modelGenerator.document(ContextModule.class, "Context Module");
        modelGenerator.document(UserModule.class, "User Module");
        modelGenerator.document(ItemModule.class, "Item Module");
        modelGenerator.document(PromotionModule.class, "Promotions Module");
        modelGenerator.document(TaxModule.class, "Tax Module");
        modelGenerator.document(SalesModule.class, "Sales Module");
        modelGenerator.document(OpsModule.class, "Operations Module");
        modelGenerator.document(CustomerModule.class, "Customer Module");
        modelGenerator.document(PaymentModule.class, "Payment Module");
        modelGenerator.document(DocumentModule.class, "Document Module");
        modelGenerator.document(i18nModule.class, "i18n Module");
        modelGenerator.document(VertexModule.class, "Vertex Module");
        modelGenerator.finish("JumpMind-Commerce-DataModel.md");
    }
}
