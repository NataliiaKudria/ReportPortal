package reporter;

import org.jbehave.core.model.Story;
import org.jbehave.core.reporters.NullStoryReporter;
import org.jbehave.core.steps.Timing;
import steps.BaseSteps;
import utils.CustomLogger;

public class CustomStoryReporter extends NullStoryReporter {

    @Override
    public void successful(String step) {
        super.successful(step);
        CustomLogger.getLogger().info("Finished: {}", step);
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
        new BaseSteps().closeDriver();
        super.afterScenario(timing);
    }

    private void clearEmptySteps(Story story, boolean givenStory) {
        if (!"BeforeStories".equals(story.getPath()) && !"AfterStories".equals(story.getPath())) {
            super.beforeStory(story, givenStory);
        }
    }
}
