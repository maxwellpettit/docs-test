package org.jumpmind.pos;

import com.google.common.io.Resources;
import org.apache.commons.io.FileUtils;
import org.jumpmind.pos.app.state.sale.actionhandler.IItemRequiredActionHandler;
import org.jumpmind.pos.app.state.sale.actionhandler.ISaleScreenActionHandler;
import org.jumpmind.pos.app.state.selfcheckout.ISelfCheckoutSaleActionHandler;
import org.jumpmind.pos.app.state.user.StatePermission;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.sort;

public class SecurityMatrixGenerator {

    private static ApplicationContext applicationContext;

    public SecurityMatrixGenerator() {
    }

    public static void main(String[] args) throws Exception {

      //  listKnownPermissionIds();
        buildSecurityMatrix();

    }

    private static String wrapWithHtml(String htmlBody) {
        try {
            java.net.URL url = Resources.getResource("security-matrix-template.html");
            String templateText = null;
            templateText = Resources.toString(url, StandardCharsets.UTF_8);
            String html = templateText.replace("BODY_HERE", htmlBody);
            html = html.replace("<table>", "<table class=\"table\"");
            return html;
        } catch (IOException e) {
            throw new RuntimeException("Failed to generate HTML document ", e);
        }
    }

    private static void buildSecurityMatrix() throws IOException {
        OrderedProperties props = new OrderedProperties();
        props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("security-matrix.properties"));

        StringBuilder buff = new StringBuilder(1024);
        buff.append("<table class=\"table\">\n");
        for (String key : props.getOrderedKeys()) {
            buff.append("<tr>");
            buff.append("<td class=\"keyColumn\">").append(key).append("</td><td class=\"valueColumn\">").append(props.get(key)).append("</td>");
            buff.append("</tr>\n");
        }
        buff.append("</table>");

        String html = wrapWithHtml(buff.toString());
        File outputFile = new File("docs/security-matrix.html");
        FileUtils.write(outputFile, html, "UTF8");
        System.out.println(outputFile.getCanonicalPath());
    }

    private static void listKnownPermissionIds() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Set<String> printedPermissions = new HashSet<>();

        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider( true);

        provider.addIncludeFilter(new AssignableTypeFilter(IItemRequiredActionHandler.class));
        provider.addIncludeFilter(new AssignableTypeFilter(ISaleScreenActionHandler.class));

        provider.addIncludeFilter(new AnnotationTypeFilter(StatePermission.class));

        provider.setResourceLoader(new PathMatchingResourcePatternResolver(Thread.currentThread().getContextClassLoader()));

        final Set<BeanDefinition> candidates = provider.findCandidateComponents("org.jumpmind.pos");

        for (BeanDefinition beanDefinition : candidates) {
            Class<?> clazz = Thread.currentThread().getContextClassLoader().loadClass(beanDefinition.getBeanClassName());
            StatePermission statePermission = clazz.getAnnotation(StatePermission.class);
            if (statePermission != null) {
                if (!printedPermissions.contains(statePermission.permissionId())) {
                    printedPermissions.add(statePermission.permissionId());
                }
            }

            if (IItemRequiredActionHandler.class.isAssignableFrom(clazz)) {
                IItemRequiredActionHandler actionHandler = (IItemRequiredActionHandler) clazz.newInstance();

                String permissionId = actionHandler.getPermissionId();
                if (!printedPermissions.contains(permissionId)) {
                    printedPermissions.add(permissionId);
                }
            }
            if (ISaleScreenActionHandler.class.isAssignableFrom(clazz)) {
                ISaleScreenActionHandler actionHandler = (ISaleScreenActionHandler) clazz.newInstance();

                String permissionId = actionHandler.getPermissionId();
                if (!printedPermissions.contains(permissionId)) {
                    printedPermissions.add(permissionId);
                }
            }
            if (ISelfCheckoutSaleActionHandler.class.isAssignableFrom(clazz)) {
                ISelfCheckoutSaleActionHandler actionHandler = (ISelfCheckoutSaleActionHandler) clazz.newInstance();

                String permissionId = actionHandler.getPermissionId();
                if (!printedPermissions.contains(permissionId)) {
                    printedPermissions.add(permissionId);
                }
            }
        }

        String[] permissionIds = printedPermissions.toArray(new String[printedPermissions.size()]);
        sort(permissionIds);

        for (String permissionId : permissionIds) {
            System.out.println(permissionId + "=");
        }
    }
}
