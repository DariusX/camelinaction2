package camelinaction.karaf;

import java.util.List;

import camelinaction.DeprecatedValidator;
import org.apache.felix.gogo.commands.Command;
import org.apache.karaf.shell.console.OsgiCommandSupport;

/**
 * A Karaf command that checks if any Camel application is using deprecated Camel components
 * <p/>
 * See also the file src/main/resources/OSGI-INF/blueprint/blueprint-command.xml where the command is setup for Karaf.
 */
@Command(scope = "oldstuff", name = "deprecated-components",
         description = "Lists all deprecated components in use.")
public class DeprecatedComponentsCommand extends OsgiCommandSupport {

    @Override
    protected Object doExecute() throws Exception {
        // create the validator to use which is from the chapter19-custom-tooling module
        DeprecatedValidator validator = new DeprecatedValidator();
        // find all the deprecated component names that are in use
        List<String> names = validator.findDeprecatedComponents();

        if (names.isEmpty()) {
            System.out.println("No deprecated Camel components found.");
        } else {
            System.out.println("Deprecated Camel components found:");
            for (String name : names) {
                System.out.println("\t" + name);
            }
        }

        return null;
    }

}
