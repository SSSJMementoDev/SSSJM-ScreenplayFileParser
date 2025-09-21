package cn.monocur.sssjmscreenplayfileparser.services;

import cn.monocur.sssjmscreenplayfileparser.SPFileParserBundle;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import java.util.concurrent.ThreadLocalRandom;

@Service(Service.Level.PROJECT)
public final class SPFileParserProjectService {
    private static final Logger LOG = Logger.getInstance(SPFileParserProjectService.class);

    public SPFileParserProjectService(Project project) {
        LOG.info(SPFileParserBundle.message("projectService", project.getName()));
        LOG.warn("Don't forget to remove all non-needed sample code files with their corresponding registration entries in `plugin.xml`.");
    }

    public int getRandomNumber() {
        return ThreadLocalRandom.current().nextInt(1, 101);
    }
}