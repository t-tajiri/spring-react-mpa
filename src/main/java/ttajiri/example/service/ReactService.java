package ttajiri.example.service;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ReactService {
    private static final String APP_FILE_NAME = "app.js";
    private final Context polyglot;

    public ReactService() {
        polyglot = Context.newBuilder("js")
                          .allowAllAccess(true)
                          .build();
    }

    public Value render() throws IOException {
        var defineServerFunction = getSource("static/server.js");
        var defineAppFunction= getSource("templates/" + APP_FILE_NAME);
        polyglot.eval(defineServerFunction);
        polyglot.eval(defineAppFunction);
        return polyglot.getBindings("js").getMember("app").execute();
    }

    private Source getSource(String path) throws IOException {
        var file = new ClassPathResource(path).getFile();
        var language = Source.findLanguage(file);
        return Source.newBuilder(language, file).build();
    }

}
