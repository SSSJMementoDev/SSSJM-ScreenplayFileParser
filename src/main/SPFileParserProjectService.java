package cn.monocur.sssjmscreenplayfileparser;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.components.Service;

@Service
public final class SPFileParserProjectService {
    private final Project project;

    public SPFileParserProjectService(Project project) {
        this.project = project;
    }

    public void doSomething() {
        // 业务逻辑
    }
}