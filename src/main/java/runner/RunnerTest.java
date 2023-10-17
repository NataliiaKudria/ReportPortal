package runner;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.StoryReporter;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.spring.SpringStepsFactory;
import reporter.CustomStoryReporter;
import java.util.List;

public class RunnerTest extends JUnitStories {

    private static final String APPLICATION_CONFIGURATION_FOR_SPRING = "applicationContext.xml";

    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration()
            .useStoryLoader(new LoadFromClasspath(this.getClass()))
            .useStoryReporterBuilder(getStoryReporterBuilder());
    }

    private StoryReporterBuilder getStoryReporterBuilder() {
        return new StoryReporterBuilder() {
            @Override
            public StoryReporter build(String storyPath) {
                return new CustomStoryReporter();
            }
        };
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        String[] locations = {APPLICATION_CONFIGURATION_FOR_SPRING};
        ContextInitializer initializer = new ContextInitializer();
        return new SpringStepsFactory(configuration(), initializer.initialize(locations));
    }

    @Override
    public List<String> storyPaths() {
        return List.of("./stories/Login.story");
    }
}

