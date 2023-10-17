package reporter;

import org.jbehave.core.model.Story;
import org.jbehave.core.reporters.NullStoryReporter;
import steps.BaseSteps;
import utils.CustomLogger;

public class CustomStoryReporter extends NullStoryReporter {

    @Override
    public void successful(String step) {
        super.successful(step);
        CustomLogger.getLogger().warn("Finished: {}", step);
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
    public void afterStory(boolean givenStory) {
        new BaseSteps().closeDriver();
        super.afterStory(givenStory);
    }

    private void clearEmptySteps(Story story, boolean givenStory) {
        if (!"BeforeStories".equals(story.getPath()) && !"AfterStories".equals(story.getPath())) {
            super.beforeStory(story, givenStory);
        }
    }
}
