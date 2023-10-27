package reporter;

import static drivermanager.CustomWebDriverManager.closeDriver;
import static drivermanager.CustomWebDriverManager.getDriverInstance;

import org.jbehave.core.model.Scenario;
import org.jbehave.core.model.Story;
import org.jbehave.core.reporters.NullStoryReporter;
import org.jbehave.core.steps.Timing;
import utils.CustomLogger;

public class CustomStoryReporter extends NullStoryReporter {

    @Override
    public void successful(String step) {
        super.successful(step);
        CustomLogger.getLogger().info("Finished: {}", step);
    }

    @Override
    public void failed(String step, Throwable cause) {
        super.failed(step, cause);
        CustomLogger.getLogger().error("Failed: {}", step);
        CustomLogger.getLogger().error("Cause: {}", cause.getCause().getMessage());
    }

    @Override
    public void notPerformed(String step) {
        CustomLogger.getLogger().warn("Skipped: {}", step);
        super.notPerformed(step);
    }

    @Override
    public void beforeStory(Story story, boolean givenStory) {
        clearEmptySteps(story, givenStory);
    }

    @Override
    public void afterScenario(Timing timing) {
        closeDriver();
        super.afterScenario(timing);
    }

    @Override
    public void beforeScenario(Scenario scenario) {
        getDriverInstance();
        super.beforeScenario(scenario);
    }

    private void clearEmptySteps(Story story, boolean givenStory) {
        if (!"BeforeStories".equals(story.getPath()) && !"AfterStories".equals(story.getPath())) {
            super.beforeStory(story, givenStory);
        }
    }
}
