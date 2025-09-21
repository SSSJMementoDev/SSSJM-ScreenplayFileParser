package cn.monocur.sssjmscreenplayfileparser.toolWindow;

import cn.monocur.sssjmscreenplayfileparser.SPFileParserBundle;
import cn.monocur.sssjmscreenplayfileparser.services.SPFileParserProjectService;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBPanel;
import javax.swing.JButton;
import java.awt.FlowLayout;

public class SPFileParserToolWindowFactory implements ToolWindowFactory {
    private static final Logger LOG = Logger.getInstance(SPFileParserToolWindowFactory.class);

    public SPFileParserToolWindowFactory() {
        LOG.warn("Don't forget to remove all non-needed sample code files with their corresponding registration entries in `plugin.xml`.");
    }

    @Override
    public void createToolWindowContent(Project project, ToolWindow toolWindow) {
        MyToolWindow myToolWindow = new MyToolWindow(toolWindow);
        var content = ContentFactory.getInstance().createContent(myToolWindow.getContent(), null, false);
        toolWindow.getContentManager().addContent(content);
    }

    @Override
    public boolean shouldBeAvailable(Project project) {
        return true;
    }

    public static class MyToolWindow {
        private final SPFileParserProjectService service;
        private final ToolWindow toolWindow;

        public MyToolWindow(ToolWindow toolWindow) {
            this.toolWindow = toolWindow;
            this.service = ServiceManager.getService(toolWindow.getProject(), SPFileParserProjectService.class);
        }

        public JBPanel<?> getContent() {
            JBPanel<?> panel = new JBPanel<>(new FlowLayout());
            JBLabel label = new JBLabel(SPFileParserBundle.message("randomLabel", "?"));
            JButton button = new JButton(SPFileParserBundle.message("shuffle"));

            button.addActionListener(e ->
                    label.setText(SPFileParserBundle.message("randomLabel", service.getRandomNumber()))
            );

            panel.add(label);
            panel.add(button);
            return panel;
        }
    }
}
