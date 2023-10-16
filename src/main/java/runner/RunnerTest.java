package runner;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.CONSOLE;
import static org.jbehave.core.reporters.Format.TXT;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.spring.SpringStepsFactory;
import java.util.List;

public class RunnerTest extends JUnitStories {

    private static final String APPLICATION_CONFIGURATION_FOR_SPRING = "applicationContext.xml";

    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration()
            .useStoryLoader(new LoadFromClasspath(this.getClass()))
            .useStoryReporterBuilder(new StoryReporterBuilder()
                .withCodeLocation(codeLocationFromClass(this.getClass()))
                .withFormats(CONSOLE, TXT)
                .withDefaultFormats());
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

